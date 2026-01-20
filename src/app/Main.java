package app;

import service.BankServImple;
import service.BankService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService bankService=new BankServImple();
        System.out.print("\n\n=============================<({[   Welcome to Console Bank App   ]})>=============================\n");
        boolean running = true;
        while (running) {
            System.out.print("""
╔════════════════════════════════════════════════════════════════════════════════════╗
║                     <==   --  CONSOLE BANK APP  --    ==>                          ║
╠════════════════════════════════════════════════════════════════════════════════════╣
║  1️⃣ Open Account   ║  2️⃣ Deposit        ║  3️⃣ Withdraw       ║  4️⃣ Transfer       ║
║  5️⃣ Statement      ║  6️⃣ List Accounts  ║  7️⃣ Search Account ║  0️⃣ Exit           ║
╠════════════════════════════════════════════════════════════════════════════════════╣
║               Secure • Simple • Fast • Trusted Banking System                      ║
╚════════════════════════════════════════════════════════════════════════════════════╝
""");       System.out.print("Select  : ");
            String choise=sc.nextLine().trim();
            if(choise.equals("0")) {
                running = false;
            }

            switch (choise) {
                case "0"->running = false;
                case "1"->openAccount(sc,bankService);
                case "2"->deposit(sc,bankService);
                case "3"->withdraw(sc,bankService);
                case "4"->transfer(sc,bankService);
                case "5"->statement(sc,bankService);
                case "6"->listAccounts(sc,bankService);
                case "7"->searchAccount(sc,bankService);
            }
        }
    }



    //====================== 1 Account Open==================
    private static void openAccount(Scanner sc, BankService bankService) {
        System.out.print("Customer Name : ");
        String name = sc.nextLine().trim();
        System.out.print("Customer email : ");
        String email = sc.nextLine().trim();
        System.out.print("Account Type (Current/Saving) : ");
        String type = sc.nextLine().trim();
        System.out.print("Initial deposit (Optional, blank for 0) : ");
        String amountStr = sc.nextLine().trim();
        if(amountStr==null) {amountStr = "0";}
        Double initialdepo=Double.valueOf(amountStr);
        String accountNo=bankService.openAccount(name,email,initialdepo,type);
//        if(initialdepo>0){bankService.deposit(accountNo,initialdepo,"Deposit");}
        System.out.println("-------Account Opened : "+accountNo+"---------");
    }




    //====================== 3 Deposit==================
    private static void deposit(Scanner sc, BankService bankService) {
        System.out.print("Account Number : ");
        String accountNo = sc.nextLine().trim();
        System.out.print("Amount to Deposit : ");
        Double amount = Double.valueOf(sc.nextLine().trim());
        bankService.deposit(accountNo,amount,"Deposit");
        System.out.println();
    }



    //====================== 4 withdraw ==================
    private static void withdraw(Scanner sc, BankService bankService) {
        System.out.print("Account Number : ");
        String accountNo = sc.nextLine().trim();
        System.out.print("Amount to Withdraw : ");
        Double amount = Double.valueOf(sc.nextLine().trim());
        bankService.withdraw(accountNo,amount,"Withdraw");
        System.out.println();
    }



    //====================== 5 Transfer ==================
    private static void transfer(Scanner sc, BankService bankService) {
        System.out.print("From Account : ");
        String from = sc.nextLine().trim();
        System.out.print("To Account : ");
        String to = sc.nextLine().trim();
        System.out.print("Transfer Amount  : ");
        Double amount = Double.valueOf(sc.nextLine().trim());
        bankService.transfer(from,to,amount,"transfer");
        System.out.println();
    }



    //====================== 6 List Accounts==================
    private static void statement(Scanner sc,BankService bankService) {
        System.out.print("Account Number : ");
        String accountNo = sc.nextLine().trim();
        bankService.getStatement(accountNo).forEach(c->{
            System.out.println(c.getLocalDateTime()+" | "+c.getType()+" | "+c.getAmount()+" | "+c.getNote());
        });
    }


    //====================== 6 Show All Account==================
    private static void listAccounts(Scanner sc,BankService bankService) {
        bankService.listOfAccounts().forEach(a->{
            System.out.println(a.getAccountNumber()+" | "+a.getAccountType()+" | "+a.getAccBalance());
        });

    }

    //====================== 7 Search Account by Customer Name  ==================
    private static void searchAccount(Scanner sc,BankService bankService) {
        System.out.println("SearchBy Custmer Name : ");
        String name = sc.nextLine().trim();
        bankService.searchByCustmerName(name)
                .forEach(account->{
                    System.out.println(account.getAccountNumber()+" | "+account.getAccountType()+" | "+account.getAccBalance());
                });
    }
}