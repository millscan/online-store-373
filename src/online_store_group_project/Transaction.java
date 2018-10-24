package online_store_group_project;

import java.util.Date;

public class Transaction {
	
	enum PaymentOption {
		CreditCard,
		DebitCard,
		Bitcoin
	}

	private int amount;
	private Date date;
	private User toUser;
	private User fromUser;
	private PaymentOption paymentOption;
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return this.amount;
	}
}
