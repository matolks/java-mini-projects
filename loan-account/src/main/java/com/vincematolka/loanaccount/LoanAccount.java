package com.vincematolka.loanaccount;

public class LoanAccount {
    private static double annualInterestRate;
    private double principal;
    
    public LoanAccount(double principal) {
        this.principal = principal;
    }


    public double calculateMonthlyPayment(int numberOfPayments) {
        double monthlyInterest = annualInterestRate/12;
        double monthlyPayment = principal * ( monthlyInterest / (1 - Math.pow(1 + monthlyInterest, -numberOfPayments)));
        return monthlyPayment;
    }


    public static void setAnnualInterestRate(double rate) {
        annualInterestRate = rate;
    }
    
    public static void main(String[] args) {
    	 LoanAccount loan1 = new LoanAccount(5000.00);
         LoanAccount loan2 = new LoanAccount(31000.00);
         
         // 1% interest rate
         setAnnualInterestRate(.01); 
         System.out.println("Monthly payments for loan1 of $" + loan1.principal + " and loan2 $" + loan2.principal + " for 3, 5, and 6 year loans at 1% interest.");
         System.out.println("Loan	3 years		5 years		6 years");
         System.out.println("Loan1	" + Math.round(loan1.calculateMonthlyPayment(36) * 100.0) / 100.0 + "		" + Math.round(loan1.calculateMonthlyPayment(60) * 100.0) / 100.0 + "		" + Math.round(loan1.calculateMonthlyPayment(72) * 100.0) / 100.0);
         System.out.println("Loan2	" + Math.round(loan2.calculateMonthlyPayment(36) * 100.0) / 100.0 + "		" + Math.round(loan2.calculateMonthlyPayment(60) * 100.0) / 100.0 + "		" + Math.round(loan2.calculateMonthlyPayment(72) * 100.0) / 100.0);
         
         System.out.println("\n");
         // 5% interest rate
         setAnnualInterestRate(.05); 
         System.out.println("Monthly payments for loan1 of $" + loan1.principal + " and loan2 $" + loan2.principal + " for 3, 5, and 6 year loans at 5% interest.");
         System.out.println("Loan	3 years		5 years		6 years");
         System.out.println("Loan1	" + Math.round(loan1.calculateMonthlyPayment(36) * 100.0) / 100.0 + "		" + Math.round(loan1.calculateMonthlyPayment(60) * 100.0) / 100.0 + "		" + Math.round(loan1.calculateMonthlyPayment(72) * 100.0) / 100.0);
         System.out.println("Loan2	" + Math.round(loan2.calculateMonthlyPayment(36) * 100.0) / 100.0 + "		" + Math.round(loan2.calculateMonthlyPayment(60) * 100.0) / 100.0 + "		" + Math.round(loan2.calculateMonthlyPayment(72) * 100.0) / 100.0);
            
    }
}
