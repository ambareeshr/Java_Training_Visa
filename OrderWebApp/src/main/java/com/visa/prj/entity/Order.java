package com.visa.prj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // To make the id as auto generate
	@Column(name = "oid")
	private int orderId;
	
	@Column(name="order_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate = new Date();
	
	private double total;
	
	@ManyToOne
	@JoinColumn(name="customer_email") //Foreign Key - introduce in the owning class
	private Customer customer;
	
	@OneToMany(cascade=CascadeType.ALL, fetch= FetchType.EAGER) //CASCADE : Saving the order, automatically saves its items
	//Deleting the order, automatically deletes its items
	//Saving or Deleting the Parent will S/D the Children
	@JoinColumn(name="order_id") //FK in items table (introduced in the other class)
	//Fetch Eager will do a single select to fetch order and its items.
	private List<Item> items = new ArrayList<>(); // one order has many items

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
