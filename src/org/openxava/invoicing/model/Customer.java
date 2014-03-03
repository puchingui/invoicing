package org.openxava.invoicing.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class Customer {

	@Id
	@Column(length=6)
	private int number;
	
	@Column(length=50)
	@Required
	private String name;
	
	@Embedded
	@NoFrame
	private Address address;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Address getAddress() {
		if(address == null) address = new Address();
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}