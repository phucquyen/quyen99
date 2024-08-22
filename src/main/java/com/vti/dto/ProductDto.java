package com.vti.dto;

public class ProductDto {

	private short id;

	private String name;

	private String price;

	private String info;

	private String detail;

	private short ratingStar;

	private String imageName;

	private String categoryName;

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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String string) {
		this.categoryName = string;
	}

	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
