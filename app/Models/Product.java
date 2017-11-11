package Models;


import java.util.*;
import play.db.*;


public class Product {

    private Long productId;
    private String productName;
    private String picture;
    private String features;
    private Date timestamp;
    private Date databaseTimestamp;
    private Bid currentBid;
    private User seller;
    private User buyer;
    private boolean isactive;
    private boolean ispurchased;
    private String category;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Bid getCurrentBid() {
        return currentBid;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return seller;
    }

    public void setUser(User user) {
        this.seller = user;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean isIspurchased() {
        return ispurchased;
    }

    public void setIspurchased(boolean ispurchased) {
        this.ispurchased = ispurchased;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public Long getId() {
        return productId;
    }

    public void setId(Long id) {
        this.productId = id;
    }

    public void setCurrentBid(Bid currentBid) {
        this.currentBid = currentBid;
    }

    public Date getDatabaseTimestamp() {
        return databaseTimestamp;
    }

    public void setDatabaseTimestamp(Date databaseTimestamp) {
        this.databaseTimestamp = databaseTimestamp;
    }

    @Override
    public String toString() {
        return "Entities.ProductHandler[ id=" + productId + " ]";
    }


}
