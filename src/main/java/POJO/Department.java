package POJO;

public class Department
{
  private Customers customers;

    public Department()
    {

    }
    public Department(Customers customers)
    {
        this.customers = customers;
    }

    public Customers getCustomers()
    {
        return customers;
    }

    public void setCustomers(Customers customers)
    {
        this.customers = customers;
    }
}
