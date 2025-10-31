package seleniumPractice;

import POJO.Customers;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;

public class FetchResultsFromDbConvertJson
{

    public static final String url = "jdbc:mysql://localhost:3306";
    public static final String userName = "root";
    public static final String password = "admin";
    public static void main(String[] args) throws SQLException
    {
        String sql = "SELECT * FROM bank_management_system.Customers";
        fetchDataFromDB(sql);
    }

    public static void fetchDataFromDB(String query) throws SQLException
    {
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try
        {
            con = DriverManager.getConnection(url, userName, password);
            statement = con.createStatement();
            resultSet = statement.executeQuery(query);

            ArrayList<Customers> arrayList = new ArrayList<Customers>();
            ArrayList<Customers> arrayList1 = new ArrayList<>();

            while (resultSet.next())
            {
                Customers customers1 = new Customers(resultSet.getInt("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getLong("phone"),
                        resultSet.getString("date_of_birth"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state")
                        );

                Customers customers = new Customers();
                customers.setCustomer_id(resultSet.getInt("customer_id"));
                customers.setName(resultSet.getString("name"));
                customers.setEmail(resultSet.getString("email"));
                customers.setPhone(resultSet.getLong("phone"));
                customers.setDate_of_birth(resultSet.getString("date_of_birth"));
                customers.setAddress(resultSet.getString("address"));
                customers.setCity(resultSet.getString("city"));
                customers.setState(resultSet.getString("state"));


                arrayList.add(customers);
                arrayList1.add(customers1);
            }
            JsonArray jsonArray = new JsonArray();
            JsonArray jsonArray1 = new JsonArray();
            for (int i = 0; i < arrayList.size(); i++)
            {
                jsonArray.add(new Gson().toJson(arrayList.get(i)));
                jsonArray1.add(new Gson().toJson(arrayList1.get(i)));
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data",jsonArray);

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("data",jsonArray1);
            System.out.println(jsonObject.toJSONString().replace("\\\"","\"").replace("\"{","{").replace("}\"","}"));
            System.out.println(jsonObject1.toJSONString().replace("\\\"","\"").replace("\"{","{").replace("}\"","}"));
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

