package com.emc.vsi.json2Bean;

import java.util.Date;

public class Config {

	private boolean addValueAsComment = true;
	private String author = "weichx";
	private String date = Utils.formatDate(new Date());

	public boolean isAddValueAsComment() {
		return addValueAsComment;
	}

	public void setAddValueAsComment(boolean addValueAsComment) {
		this.addValueAsComment = addValueAsComment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
