package service;

import CustomException.AccountNotFound;
import CustomException.InsufficientFundException;
import CustomException.ValidationException;
import domain.Account;
import domain.Customer;
import domain.Transaction;
import domain.Type;
import repository.AccountRepo;
import repository.CustomerRepo;
import repository.TransectionRepo;
import util.Validation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServImple implements BankService{
    private final AccountRepo accountRepo=new AccountRepo();
    private final TransectionRepo transectionRepo=new TransectionRepo();
    private final CustomerRepo customerRepo=new CustomerRepo();


    private final Validation<String>validationName=name->{
        if(name==null||name.isBlank()){
            throw new ValidationException("Name is null or blank");
        }
    };
    private final Validation<String>validationEmail=email->{
        if(email==null|| !email.contains("@"))throw new ValidationException("Email is null or blank not accepted");
    };
    private final Validation<String>validationType=type->{
        if(type == null ||
                (!type.equalsIgnoreCase("SAVING") && !type.equalsIgnoreCase("CURRENT")))
            throw new ValidationException("Type must be SAVING or CURRENT");
    };

    private final Validation<Double>validationAmount=amount->{
      if(amount==null||amount<=0)throw new ValidationException("Please enter a valid amount");
    };



    //====================== 1 Account Open==================
    @Override
    public String openAccount(String name, String email, double balance, String type) {

        validationName.validate(name);
        validationType.validate(type);
        validationEmail.validate(email);
        validationAmount.validate(balance);
        String customerId= UUID.randomUUID().toString();

        //        Create Account by enter data on console
        String accountNumber=getAccountNumber();
        Account account=new Account(accountNumber,customerId,balance,name,type,email);

//        Create customer
        Customer c=new Customer(customerId,accountNumber,name,email);
        customerRepo.save(c);

//        Save Account
        accountRepo.save(account);
        return accountNumber;
    }
    private String getAccountNumber(){
        int size=accountRepo.findAll().size()+1;
        String accountNumber=String.format("AC%06d",size);
        return accountNumber;
    }



    //====================== 2 Deposit==================
    @Override
    public void deposit(String accountNo, Double amount, String note) {
        validationAmount.validate(amount);

//        Checking Deposit Account is valid or not
        Account account=accountRepo.findByNumber(accountNo)
                .orElseThrow(()->new AccountNotFound("Account not Found : "+accountNo));
        account.setAccBalance(account.getAccBalance()+amount);

//        Store Transection In transection map
        Transaction transObj=new Transaction(account.getAccountNumber(),amount,UUID.randomUUID().toString(),note, LocalDateTime.now(), Type.DEPOSIT);
        transectionRepo.add(transObj);
    }

    //====================== 3 withdraw==================
    @Override
    public void withdraw(String accountNo, Double amount, String note) {
        validationAmount.validate(amount);
        Account account=accountRepo.findByNumber(accountNo)
                .orElseThrow(()->new AccountNotFound("Account not Found : "+accountNo));
        if(account.getAccBalance()<=amount){
            throw new InsufficientFundException("Insufficient Balance");
        }
        account.setAccBalance(account.getAccBalance()-amount);
//        Store Transection In transection map
        Transaction transObj=new Transaction(account.getAccountNumber(),amount,UUID.randomUUID().toString(),note, LocalDateTime.now(), Type.WITHDRAW);
        transectionRepo.add(transObj);
    }

    //====================== 4 transfer==================
    @Override
    public void transfer(String fromAcc, String toAcc, Double amount, String note) {
        validationAmount.validate(amount);
        if(fromAcc.equals(toAcc)){
            System.out.println("=====Transection Not Possible in same Account Try Again=====");
            return;
        }
        Account from=accountRepo.findByNumber(fromAcc)
                .orElseThrow(()->new AccountNotFound("Account not Found : "+fromAcc));
        Account to=accountRepo.findByNumber(toAcc)
                .orElseThrow(()->new AccountNotFound("Account not Found : "+toAcc));
        if (from.getAccBalance()<amount){
            throw new InsufficientFundException("Insufficient Balance");
        }
        from.setAccBalance(from.getAccBalance()-amount);
        to.setAccBalance(to.getAccBalance()+amount);
        transectionRepo.add(new Transaction(from.getAccountNumber(),amount,UUID.randomUUID().toString(),note, LocalDateTime.now(), Type.TRANSFER_OUT));
        transectionRepo.add(new Transaction(to.getAccountNumber(),amount,UUID.randomUUID().toString(),note, LocalDateTime.now(), Type.TRANSFER_IN));

    }

    //====================== 5 getStatement==================
    @Override
    public List<Transaction> getStatement(String accountNo) {
        return transectionRepo.findByAcc(accountNo).stream()
                .sorted(Comparator.comparing(Transaction::getLocalDateTime))
                .collect(Collectors.toList());
    }
    //====================== 6 Show All Account==================
    @Override
    public List<Account> listOfAccounts() {
        return accountRepo.findAll()
                .stream().sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }

    //====================== 7 searchByCustmerName==================
    @Override
    public List<Account> searchByCustmerName(String name) {
        String qury=(name==null)?"" : name.toString().trim();
        List<Account>result=new ArrayList<>();
        for(Customer c:customerRepo.findAll()){
            if(c.getName().toLowerCase().contains(qury)){
                result.addAll(accountRepo.findByCustomerId(c.getId()));
            }
        }
        result.sort(Comparator.comparing(Account::getAccountNumber));
        return result;
    }

}
