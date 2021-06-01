package Beans;

public class Product {

	private int productID;
	private String productName;
	private Double productPrice;
	private String productText;
	private int productQuantity;
	private String productImg;
	private String productCategory;

	public Product(String productName, Double productPrice, String productText, int productQuantity, String productImg, String productCategory) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.productText = productText;
		this.productQuantity = productQuantity;
		this.productImg = productImg;
		this.productCategory = productCategory;
	}

	public Product() {
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductText() {
		return productText;
	}

	public void setProductText(String productText) {
		this.productText = productText;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
}
