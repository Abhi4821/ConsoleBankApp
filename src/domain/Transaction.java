package domain;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private LocalDateTime localDateTime;
    private String note;
    private Type type;

    public Transaction( String accountNumber, double amount,String id, String note, LocalDateTime localDateTime, Type type) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.localDateTime = localDateTime;
        this.note = note;
        this.type = type;
    }

    public String getTransAccNo() {
        return accountNumber;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }
}
