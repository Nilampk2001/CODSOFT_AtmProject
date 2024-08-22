package AtmProject;

import java.util.Scanner;

public class Account
{
	    private long accBalance;
	    private long accNo;
	    private int pin;
	    private static Scanner sc = new Scanner(System.in);

	    public Account(long accNo, int pin, long accBalance) {
	        this.accNo = accNo;
	        this.pin = pin;
	        this.accBalance = accBalance;
	    }

	    public boolean validateCredentials(long uacc, int upin) {
	        return this.accNo == uacc && this.pin == upin;
	    }

	    public long getAccBalance() {
	        return accBalance;
	    }

	    public void withdraw(long money) {
	        if (accBalance >= money) {
	            accBalance -= money;
	            System.out.println("Withdraw successfully");
	        } else {
	            System.out.println("Insufficient account balance..Please check it");
	        }
	    }

	    public void deposit(long money) {
	        accBalance += money;
	        System.out.println("Deposit successfully");
	    }

	    public void checkBalance() {
	        System.out.println("Account balance: " + accBalance);
	    }

	    public static Account createAccount() {
	        System.out.println("Enter the Account Number");
	        long accNo = sc.nextLong();
	        System.out.println("Enter the Pin");
	        int pin = sc.nextInt();
	        System.out.println("Enter the Account Balance");
	        long accBalance = sc.nextLong();
	        return new Account(accNo, pin, accBalance);
	    }
	}


