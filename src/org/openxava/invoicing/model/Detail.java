package org.openxava.invoicing.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.*;

@Entity
public class Detail {

	@ManyToOne
	private Invoice parent;
	
	@Id @Hidden
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String oid;
	
	private int quantity;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	private Product product;

	public Invoice getParent() {
		return parent;
	}

	public void setParent(Invoice parent) {
		this.parent = parent;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
