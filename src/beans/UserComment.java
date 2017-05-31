package beans;

import java.io.Serializable;
import java.util.Date;

public class UserComment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String account;
	private int messageId;
	private int userId;
	private String text;
	private Date insertDate;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}
	public void set(String account) {
		this.account = account;
	}

	public int getMessageId(int messageId) {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

}
