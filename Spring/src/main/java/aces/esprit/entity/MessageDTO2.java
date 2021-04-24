package aces.esprit.entity;

import java.util.Date;

public class MessageDTO2 {

	private int id;
	private String userName;
	private String image;
	private String msg;
	private Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public MessageDTO2(int id, String userName, String image, String msg, Date date) {
		super();
		this.id = id;
		this.userName = userName;
		this.image = image;
		this.msg = msg;
		this.date = date;
	}
	public MessageDTO2() {
		super();
	}
	
	
}
