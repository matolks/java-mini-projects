package com.vincematolka.loanaccounthierarchy;
import java.util.ArrayList;

public class Customer {
    private String firstName;
    private String lastName;
    private String SSN;
    private ArrayList<LoanAccount> loanAccounts;

    public Customer(String firstName, String lastName, String SSN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        this.loanAccounts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSSN() {
        return SSN;
    }

    public void addLoanAccount(LoanAccount account) {
        loanAccounts.add(account);
    }

    public void printMonthlyReport() {
        System.out.println("Account Report for Customer: " + firstName + " " + lastName + " with SSN " + SSN + "\n");
        for (LoanAccount account : loanAccounts) {
            System.out.println(account.toString());
        }
    }
}
