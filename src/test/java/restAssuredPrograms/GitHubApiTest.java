package restAssuredPrograms;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class GitHubApiTest
{
    private final String BASE_URL = "https://api.github.com";
    private final String API_KEY = "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";
    private final String OWNER_NAME = "/TRISULAM-R/";
    private final String OWNER_NAME1 = "TRISULAM-R";
    private final String CREATE_REPO = "/user/repos";
    private final String CREATE_ISSUES = "/repos/{owner}/{repo}/issues";
    private final String ADD_COMMENT_ISSUE = "/repos/{owner}/{repo}/issues/{issue_number}/comments";
    private final String CLOSE_ISSUE = "/repos/{owner}/{repo}/issues/{issue_number}";
    private final String REPO_CONTENT = "/repos/{owner}/{repo}/contents/{path}";
    private final String UPDATE_DELETE_REPO = "/repos/{owner}/{repo}";
    private final String createRepoJsonPath = "src/test/resources/jsonFiles/CreateRepo.json";
    private final String updateRepoJsonPath = "src/test/resources/jsonFiles/UpdateRepo.json";
    private final String createIssueJsonPath = "src/test/resources/jsonFiles/createIssue.json";
    private final String addCommentToIssueJsonPath = "src/test/resources/jsonFiles/addCommentToIssue.json";
    private final String closeIssueJsonPath = "src/test/resources/jsonFiles/closeIssue.json";
    private final String createFileContentJsonPath = "src/test/resources/jsonFiles/createFileContent.json";
    private final String deleteFileJsonPath = "src/test/resources/jsonFiles/deleteFile.json";

    public String repoName = "automation-test-repo"+generateRandomNumber(1000);

    // 25-09-2025 Automation Task 1: Repository Management
    @Test
    public void createRepoTest() throws Exception {
        createRepo();
    }

    @Test(dependsOnMethods = "createRepoTest")
    public void updateRepoTest() throws Exception {

        String expectedDescription= "Updated repository description";
        RequestSpecification requestSpec = createAuthentication();

        String UPDATE_DELETE_REPO1 = UPDATE_DELETE_REPO.replace("{owner}",OWNER_NAME1).replace("{repo}",repoName);

        String template = new String(Files.readAllBytes(Paths.get(updateRepoJsonPath)));
        String payload = template.replace("{{description}}",expectedDescription);
        Response response= requestSpec.body(payload)
                .when().patch(UPDATE_DELETE_REPO1)
                .then().statusCode(200)
                .extract()
                .response();

        String actualDescription = response.jsonPath().get("description");
        System.out.println("description : "+actualDescription);
        Assert.assertEquals(actualDescription,expectedDescription);
        System.out.println("===================  Successfully updated repo "+repoName+" ===================");

        // Delete Repo to avoid confusion
        deleteRepo(repoName);
    }

    @Test(dependsOnMethods = "createRepoTest")
    public void deleteRepoTest() throws Exception {
        deleteRepo(repoName);
    }

    // Automation Task #2 - Issue and Comment Management
    @Test
    public void createIssueTest() throws Exception {
        createRepo();
        int issueNum = createIssue();
        addCommentToAnIssue(issueNum);
        closeAnIssue(issueNum);
    }

    // Task 3: Repository File Management
    @Test
    public void automateFileRepoTest() throws IOException
    {
        String repoName = "Selenium2025";
        String path = "src/test/java/DummyFile.txt";
        String sha = null;
        try{
            sha= getRepoContent(repoName, path);
            sha =createContent(repoName,path,sha);
            deleteFile(repoName,path,sha);
        }
        catch (Exception e)
        {
            sha =createContent(repoName,path,sha);
            deleteFile(repoName,path,sha);
        }
    }

    public RequestSpecification createAuthentication()
    {
        return RestAssured
                .given().baseUri(BASE_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .header("Accept", "application/vnd.github+json")
                .header("X-GitHub-Api-Version", "2022-11-28");
    }

    public void deleteRepo(String repoName)
    {
        RequestSpecification requestSpec = createAuthentication();
        String UPDATE_DELETE_REPO1 = UPDATE_DELETE_REPO.replace("{owner}",OWNER_NAME1).replace("{repo}",repoName);

        requestSpec.when().delete(UPDATE_DELETE_REPO1)
                .then().statusCode(204)
                .extract()
                .response();
        System.out.println("===================  Successfully Deleted repo "+repoName+" ===================");
    }
    public void createRepo() throws IOException
    {
        RequestSpecification requestSpec = createAuthentication();
        String expectedVisbility = "public";
        String template = new String(Files.readAllBytes(Paths.get(createRepoJsonPath)));
        String payload = template.replace("{{repoName}}",repoName);
        Response response= requestSpec.body(payload)
                .when().post(CREATE_REPO)
                .then().statusCode(201)
                .extract()
                .response();

        String name = response.jsonPath().get("name");
        String visibility = response.jsonPath().get("visibility");
        System.out.println("===================  Successfully created repo "+repoName+" ===================");
        Assert.assertEquals(name,repoName);
        Assert.assertEquals(visibility,expectedVisbility);
    }

    public int generateRandomNumber(int range)
    {
        Random rand = new Random();
        return rand.nextInt(range);
    }

    public int createIssue() throws Exception
    {
        String expectedTitle= "Bug found";
        String expectedBody= "Detailed bug description";
        RequestSpecification requestSpec = createAuthentication();

        String CREATE_ISSUES1 = CREATE_ISSUES.replace("{owner}",OWNER_NAME1).replace("{repo}",repoName);
        String template = new String(Files.readAllBytes(Paths.get(createIssueJsonPath)));
        String payload = template.replace("{{title}}",expectedTitle).replace("{{body}}",expectedBody);
        Response response= requestSpec.body(payload)
                .when().post(CREATE_ISSUES1)
                .then().statusCode(201)
                .extract()
                .response();

        String actualTitle = response.jsonPath().get("title");
        String actualBody = response.jsonPath().get("body");
        System.out.println("===================  Successfully created Issue "+actualTitle+" ===================");
        Assert.assertEquals(actualTitle,expectedTitle);
        Assert.assertEquals(actualBody,expectedBody);
        return response.jsonPath().get("number");
    }

    public void addCommentToAnIssue(int issueNumber) throws Exception
    {
        String expectedBody= "This is a comment on the issue";
        RequestSpecification requestSpec = createAuthentication();

        String ADD_COMMENT_ISSUE1 = ADD_COMMENT_ISSUE.replace("{owner}",OWNER_NAME1).replace("{repo}",repoName).replace("{issue_number}",String.valueOf(issueNumber));
        String template = new String(Files.readAllBytes(Paths.get(addCommentToIssueJsonPath)));
        String payload = template.replace("{{body}}",expectedBody);
        Response response= requestSpec.body(payload)
                .when().post(ADD_COMMENT_ISSUE1)
                .then().statusCode(201)
                .extract()
                .response();

        String actualBody = response.jsonPath().get("body");
        System.out.println("===================  Successfully added comment to an Issue "+actualBody+" ===================");
        Assert.assertEquals(actualBody,expectedBody);
    }

    public void closeAnIssue(int issueNumber) throws Exception
    {
        String expectedState= "closed";
        RequestSpecification requestSpec = createAuthentication();

        String CLOSE_ISSUE1 = CLOSE_ISSUE.replace("{owner}",OWNER_NAME1).replace("{repo}",repoName).replace("{issue_number}",String.valueOf(issueNumber));
        String template = new String(Files.readAllBytes(Paths.get(closeIssueJsonPath)));
        String payload = template.replace("{{state}}",expectedState);
        Response response= requestSpec.body(payload)
                .when().patch(CLOSE_ISSUE1)
                .then().statusCode(200)
                .extract()
                .response();

        String actualState = response.jsonPath().get("state");
        System.out.println("===================  Successfully closed an Issue "+actualState+" ===================");
        Assert.assertEquals(actualState,expectedState);
    }

    public String getRepoContent(String repo, String path)
    {
        RequestSpecification requestSpec = createAuthentication();
        String REPO_CONTENT1 = REPO_CONTENT.replace("{owner}",OWNER_NAME1).replace("{repo}",repo).replace("{path}",path);
        Response response= requestSpec
                .when().get(REPO_CONTENT1)
                .then().statusCode(200)
                .extract()
                .response();

        String actualPath = response.jsonPath().getString("path");
        String sha = response.jsonPath().getString("sha");
        System.out.println("===================  Successfully closed an Issue "+actualPath+" ===================");
        Assert.assertEquals(actualPath,path);
        return sha;
    }

    public String createContent(String repo, String path, String sha) throws IOException
    {
        RequestSpecification requestSpec = createAuthentication();
        String REPO_CONTENT1 = REPO_CONTENT.replace("{owner}",OWNER_NAME1).replace("{repo}",repo).replace("{path}",path);
        String template = new String(Files.readAllBytes(Paths.get(createFileContentJsonPath)));
        String payload = template.replace("{{sha}}",sha);
        Response response= requestSpec.body(payload)
                .when().put(REPO_CONTENT1)
                .then().statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .response();

        System.out.println(response.asPrettyString());
        String actualPath = response.jsonPath().getString("content.path");
        sha = response.jsonPath().getString("content.sha");
        System.out.println("sha = "+ sha);
        System.out.println("===================  Successfully created a Content "+actualPath+" ===================");
        Assert.assertEquals(actualPath,path);
        return sha;
    }

    public void deleteFile(String repo, String path, String sha) throws IOException
    {
        RequestSpecification requestSpec = createAuthentication();
        String REPO_CONTENT1 = REPO_CONTENT.replace("{owner}", OWNER_NAME1).replace("{repo}", repo).replace("{path}", path);
        String template = new String(Files.readAllBytes(Paths.get(deleteFileJsonPath)));
        String payload = template.replace("{{sha}}", sha);
        Response response = requestSpec.body(payload)
                .when().delete(REPO_CONTENT1)
                .then().statusCode(200)
                .extract()
                .response();
        System.out.println(response.asPrettyString());
        sha = response.jsonPath().getString("commit.sha");
        System.out.println("sha : "+ sha);
    }
}
