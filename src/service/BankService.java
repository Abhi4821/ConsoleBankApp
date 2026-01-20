package service;

import domain.Account;
import domain.Transaction;

import java.util.List;

public interface BankService {
    String openAccount(String name, String email ,double balance,String type);

    List<Account>listOfAccounts();

    void deposit(String accountNo, Double amount, String deposit);


    void withdraw(String accountNo, Double amount, String withdraw);

    void transfer(String from, String to, Double amount, String transfer);

    List<Transaction> getStatement(String accountNo);

    List<Account> searchByCustmerName(String name);
}
