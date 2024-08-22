package com.vti.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Product", catalog = "ProductManagement1")
public class Product implements Serializable {

	@Column(name = "ProductId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;

	@Column(name = "ProductName", length = 50, nullable = false, unique = true)
	private String name;

	@Column(name = "ProductPrice", length = 50, nullable = false)
	private String price;

	@Column(name = "ProductInfo", length = 200, nullable = false)
	private String info;

	@Column(name = "ProductDetail", length = 500)
	private String detail;

	@Column(name = "RatingStar")
	private short ratingStar;

	@Column(name = "ProductImageName", length = 50, nullable = false)
	private String imageName;

	@ManyToOne
	@JoinColumn(name = "CategoryId", nullable = false)
	private Category category;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public short getRatingStar() {
		return ratingStar;
	}

	public void setRatingStar(short ratingStar) {
		this.ratingStar = ratingStar;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
