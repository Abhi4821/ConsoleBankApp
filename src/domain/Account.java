package domain;

public class Account {
    private String accountNumber;
    private String CustomerID;
    private  String name;

    private double balance;
    private String accountType;
    private String email;

    public Account(String accountNumber, String CustomerID, double balance,String name, String accountType, String email) {
        this.accountNumber = accountNumber;
        this.CustomerID = CustomerID;
        this.balance = balance;
        this.accountType = accountType;
        this.email = email;
        this.name = name;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public double getAccBalance() {
        return this.balance;
    }

    public void setAccBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerID() {
        return CustomerID;
    }
}
