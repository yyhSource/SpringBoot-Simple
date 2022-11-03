package com.yyh.demo.entity;

public class House {

	private String id;
	private String ownerName;
	private String type;
	private String address1;
	private String address2;
	private String postCode;
	private Double price;
	
	public House() {
		
	}
	
	public House(String id, String ownerName, String type, String address1, String address2, String postCode, Double price) {
		
		this.id = id;
		this.ownerName = ownerName;
		this.type = type;
		this.address1 = address1;
		this.address2 = address2;
		this.postCode = postCode;
		this.price = price;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	
}
