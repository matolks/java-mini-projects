package com.vincematolka.loanaccounthierarchy;

public class CarLoan extends LoanAccount {
    private String vehicleVIN;

    public CarLoan(double principal, double annualInterestRate, int months, String vehicleVIN) {
        super(principal, annualInterestRate, months);
        this.vehicleVIN = vehicleVIN;
    }

    @Override
    public String toString() {
        return String.format("Car Loan with:%n%sVehicle VIN: %s%n", super.toString(), vehicleVIN);
    }
}

