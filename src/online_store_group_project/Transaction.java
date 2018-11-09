package online_store_group_project;

import java.util.Date;
import java.util.UUID;

import data_storage.StoreDataIO;

public class Transaction {

	private Store store;
	private String id;
	private double amount;
	private Date date;
	private User toUser;
	private User fromUser;
	private PaymentOptions paymentOption;
	
	public Transaction(Store store, double amount, Date date, User fromUser, User toUser,PaymentOptions paymentOption) {
		this.store = store;
		this.id = UUID.randomUUID().toString();
		this.amount = amount;
		this.date = date;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.paymentOption = paymentOption;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
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
	
	public void setPaymentOption(PaymentOptions po) {
		this.paymentOption = po;
	}
	
	public PaymentOptions getPaymentOption() {
		return this.paymentOption;
	}
	
	public String toCsvString() {
		return String.format("%s#%s#%s#%s#%s", id, StoreDataIO.dateFormat.format(date), fromUser.getId(), toUser.getId(), paymentOption);
	}
}
