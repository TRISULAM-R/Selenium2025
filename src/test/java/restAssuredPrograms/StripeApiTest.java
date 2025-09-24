package restAssuredPrograms;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class StripeApiTest
{

    // Replace with your actual API key and token
    private final String API_KEY = "your_api_key";
    private final String USER_NAME = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";
    private final String BASE_URL = "https://api.stripe.com/v1";

    @Test
    public void createCustomerTest() {

        Response response = RestAssured
                .given().baseUri(BASE_URL)
                    .auth()
                    .preemptive()
                    .basic(USER_NAME, "")
                .when().post("/customers")
                .then().statusCode(200)
                    .extract()
                    .response();

        String id = response.jsonPath().get("id");
        System.out.println("id : "+id);
        Assert.assertTrue(id.startsWith("cus"));
    }
    String id;
    @Test
    public void createPaymentIntentTest() {

        Map map = new HashMap<>();
        map.put("amount",2000);
        map.put("currency","usd");
        map.put("automatic_payment_methods[enabled]",true);

        Response response = RestAssured
                .given().baseUri(BASE_URL)
                .auth()
                .preemptive()
                .basic(USER_NAME, "")
                .queryParams(map)
                .contentType("application/x-www-form-urlencoded")
                .when().post("/payment_intents")
                .then().statusCode(200)
                .extract()
                .response();

        String expectedStaus = "requires_payment_method";
        String status = response.jsonPath().get("status");
        id = response.jsonPath().get("id");
        System.out.println("status : "+status);
        Assert.assertEquals(status, expectedStaus);
    }

    @Test
    public void addCardDetails() {

        Map map = new HashMap<>();
        map.put("type","us_bank_account");
        map.put("us_bank_account[account_holder_type]","individual");
        map.put("us_bank_account[account_number]","000123456789");
        map.put("us_bank_account[routing_number]",110000000);
        map.put("billing_details[name]","John Doe");
        Response response = RestAssured
                .given().baseUri(BASE_URL)
                .auth()
                .preemptive()
                .basic(USER_NAME, "")
                .queryParams(map)
                .contentType("application/x-www-form-urlencoded")
                .when().post("/payment_methods")
                .then().statusCode(200)
                .extract()
                .response();
    }

    @Test(dependsOnMethods = "createPaymentIntentTest")
    public void cancelPaymentIntent() {

        Response response = RestAssured
                .given().baseUri(BASE_URL)
                .auth()
                .preemptive()
                .basic(USER_NAME, "")
                .contentType("application/x-www-form-urlencoded")
                .when().post("/payment_intents/"+id+"/cancel")
                .then().statusCode(200)
                .extract()
                .response();

        String expectedStaus = "canceled";
        String status = response.jsonPath().get("status");
        System.out.println("status : "+status);
        Assert.assertEquals(status, expectedStaus);
    }
}
