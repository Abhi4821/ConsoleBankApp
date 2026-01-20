package domain;

public class Customer {
    private String id;
    private String AccountNo;
    private String name;
    private String email;

    public Customer(String id, String AccNo ,String name, String email) {
        this.id = id;
        AccountNo = AccNo;
        this.email = email;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
