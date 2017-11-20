package Models;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class ProductInstance implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "productid")
    private Long productId;

    private String category;

    @Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "databasetimestamp")
    private Date databaseTimestamp;

    private String features;
    private boolean isactive;
    private boolean ispurchased;
    private String picture;

    //@Column(name = "productname")
    private String productName;
    //These are needed to create the web service.
    private int highestbid;
   // @Column(name = "sellername")
    private String sellerName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @OneToOne
    @JoinColumn(name = "bid_fk")
    private BidInstance currentBid;

    @ManyToOne
    @JoinColumn(name = "user_seller_pk")
    private UserInstance seller;

    @ManyToOne
    @JoinColumn(name = "user_buyer_pk")
    private UserInstance buyer;

    @Transient
    private String time;

    public String getTime() {return time;}
    public void setTime(String time) { this.time = time;}

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

    public UserInstance getSeller() {
        return seller;
    }
    public void setSeller(UserInstance seller) {
        this.seller = seller;
    }

    public BidInstance getCurrentBid() {
        return currentBid;
    }
    public void setCurrentBid(BidInstance currentBid) { this.currentBid = currentBid; }

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public UserInstance getBuyer() {
        return buyer;
    }
    public void setBuyer(UserInstance buyer) {
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

    public UserInstance getUser() {
        return seller;
    }
    public void setUser(UserInstance user) {
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

    public String getSellerName() {
        return seller.getName();
    }
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Long getId() {
        return productId;
    }
    public void setId(Long id) {
        this.productId = id;
    }

    public Date getDatabaseTimestamp() {
        return databaseTimestamp;
    }
    public void setDatabaseTimestamp(Date databaseTimestamp) {
        this.databaseTimestamp = databaseTimestamp;
    }

    public int getHighestbid() {
        if(getCurrentBid() == null )
            return 0;
        return getCurrentBid().getAmount();
    }
    public void setHighestbid(int highestbid) {
        this.highestbid = highestbid;
    }

    @Override
    public String toString() {
        return "pruductname: " + productName + ", picture: " + picture +
                ", features: " + features + ", Timestamp " +timestamp.toString() +
                ", databasetimestamp: " + databaseTimestamp.toString() + ", User: " + seller.toString() +
                ", isactive: " + isactive + " ispurchased: " + ispurchased + ", category: " +
                category;
    }



}
