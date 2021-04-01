package com.sample.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Product entity
 * @author qe3nrt
 *
 */
@Entity
@Table(name="PRODUCT")
@NamedQuery(query = "select product from Product product order by product.id", name = "query_find_all")
public class Product implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7662657658328555403L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="version_id")
    private String versionId;
    
    @NotNull(message = "Product name is required.")
    @Column(name="product_name")
    private String productName;
    
    @NotNull(message = "Price is required.")
    @Column(name="price")
    private Double price;

    @Column(name="picture_url")
    private String pictureUrl;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
	private User user;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the versionId
	 */
	public String getVersionId() {
		return versionId;
	}

	/**
	 * @param versionId the versionId to set
	 */
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the pictureUrl
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * @param pictureUrl the pictureUrl to set
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", versionId=" + versionId + ", productName=" + productName + ", price="
				+ price + ", pictureUrl=" + pictureUrl + "]";
	}

}