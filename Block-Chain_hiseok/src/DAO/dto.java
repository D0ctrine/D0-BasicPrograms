package DAO;

public class dto {
private String sender;
private String receiver;
private int money;
private String date;
private int flag=5;

public int getFlag() {
	return flag;
}
public void setFlag(int flag) {
	this.flag = flag;
}
public String getSender() {
	return sender;
}
public void setSender(String sender) {
	this.sender = sender;
}
public String getReceiver() {
	return receiver;
}
public void setReceiver(String receiver) {
	this.receiver = receiver;
}
public int getMoney() {
	return money;
}
public void setMoney(int money) {
	this.money = money;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
	
	
}
