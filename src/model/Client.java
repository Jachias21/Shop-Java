package model;

import main.Payable;

public class Client extends Person implements Payable {
	
	private int memberId;
	private Amount balance;
	
	public Client(String name) {
		super(name);
		this.memberId = MEMBER_ID;
		this.balance = BALANCE;
	}
	
	public Client() {
		
	}
	private static final int MEMBER_ID = 456;
	private static final Amount BALANCE = new Amount(50.00, "€");
	
	

	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public Amount getBalance() {
		return balance;
	}
	public void setBalance(Amount balance) {
		this.balance = balance;
	}
	

	public boolean pay(Amount amount) {
        Amount finalBalance = new Amount(balance.getValue() - amount.getValue(), balance.getCurrency());

        if (finalBalance.getValue() >= 0) {
            balance = finalBalance;
            return true;
        } else {
            return false;
        }
    }
	
}
