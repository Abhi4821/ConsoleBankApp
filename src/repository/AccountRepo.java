package repository;

import domain.Account;

import java.util.*;

public class AccountRepo {
    Map<String, Account> accountObjList=new HashMap<>();
    public void save(Account account){
        accountObjList.put(account.getAccountNumber(),account);
    }


    public List<Account> findAll() {
        return new ArrayList<>(accountObjList.values());
    }

    public Optional<Account> findByNumber(String accountNo) {
        return Optional.ofNullable(accountObjList.get(accountNo));
    }

    public List<Account> findByCustomerId(String id) {
        List<Account> result=new ArrayList<>();
        for(Account account:accountObjList.values()){
            if(account.getCustomerID().equals(id)){
                result.add(account);
            }
        }
        return result;
    }
}
