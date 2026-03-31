package com.vincematolka.loanaccounthierarchy;

public class PrimaryMortgage extends LoanAccount {
    private double PMIMonthlyAmount;
    private Address address;

    public PrimaryMortgage(double principal, double annualInterestRate, int months, double PMIMonthlyAmount, Address address) {
        super(principal, annualInterestRate, months);
        this.PMIMonthlyAmount = PMIMonthlyAmount;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("Primary Mortgage Loan with:%n%sPMI Monthly Amount: $%.2f%nProperty Address: %n%s",
                super.toString(), PMIMonthlyAmount, address);
    }
}
