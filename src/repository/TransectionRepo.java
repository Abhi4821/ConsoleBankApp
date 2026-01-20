package repository;

//import domain.Account;
import domain.Account;
import domain.Transaction;

import java.util.*;

public class TransectionRepo {

    private final Map<String, List<Transaction>> transectionMap = new HashMap<>();

    public void add(Transaction trans) {
        transectionMap
                .computeIfAbsent(trans.getTransAccNo(), k -> new ArrayList<>())
                .add(trans);
    }

    public List<Transaction> findByAcc(String accountNo) {
        return new ArrayList<>(transectionMap.getOrDefault(accountNo, Collections.emptyList()));
    }
}

