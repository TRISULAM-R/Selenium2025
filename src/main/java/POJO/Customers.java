package POJO;

public class Customers
{
    private int customer_id;
    private String name;
    private String email;
    private long phone;
    private String date_of_birth;
    private String address;
    private String city;
    private String state;

    public Customers()
    {

    }
    public Customers(int customer_id, String name, String email, long phone, String date_of_birth, String address, String city, String state)
    {
        this.customer_id = customer_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public int getCustomer_id()
    {
        return customer_id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public long getPhone()
    {
        return phone;
    }

    public String getDate_of_birth()
    {
        return date_of_birth;
    }

    public String getAddress()
    {
        return address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhone(long phone)
    {
        this.phone = phone;
    }

    public void setDate_of_birth(String date_of_birth)
    {
        this.date_of_birth = date_of_birth;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }
}
