package com.human.dto;

import java.util.Date;

public class CartVO {
	
	private int cartnum;		// īƮ ��ȣ
	private String id;			// �� ���̵�
	private int dressId;		// ��ǰ ��ȣ
	private String dressname;	// ��ǰ �̸� 
	private int amount;		// ����
	private int price; 		// ��ǰ�ݾ�
	private int sum;		// �� �ݾ�
	private String dressimg;
	private Date addDate; 		// �ֹ� ��¥
	private int totalCart;
	
	public CartVO() {}

	public CartVO(int cartnum, String id, int dressId, String dressname, int amount, int price, int sum,
			String dressimg, Date addDate, int totalCart) {
		super();
		this.cartnum = cartnum;
		this.id = id;
		this.dressId = dressId;
		this.dressname = dressname;
		this.amount = amount;
		this.price = price;
		this.sum = sum;
		this.dressimg = dressimg;
		this.addDate = addDate;
		this.totalCart = totalCart;
	}

	public int getCartnum() {
		return cartnum;
	}

	public void setCartnum(int cartnum) {
		this.cartnum = cartnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDressId() {
		return dressId;
	}

	public void setDressId(int dressId) {
		this.dressId = dressId;
	}

	public String getDressname() {
		return dressname;
	}

	public void setDressname(String dressname) {
		this.dressname = dressname;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getDressimg() {
		return dressimg;
	}

	public void setDressimg(String dressimg) {
		this.dressimg = dressimg;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public int getTotalCart() {
		return totalCart;
	}

	public void setTotalCart(int totalCart) {
		this.totalCart = totalCart;
	}
	
	
}
	