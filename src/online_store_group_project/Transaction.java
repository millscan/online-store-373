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
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	
	public User getToUser() {
		return this.toUser;
	}
	
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	
	public User getFromUser() {
		return this.fromUser;
	}
	
	public void setPaymentOption(PaymentOption po) {
		this.paymentOption = po;
	}
	
	public PaymentOption getPaymentOption() {
		return this.paymentOption;
	}
}
