package Models;


import java.util.*;
import play.db.*;


public class Bid {

    private Long bidId;
    private Product product;
    private User user;
    private Integer amount;

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getBidId() {
        return bidId;
    }

    public Integer getAmount() {
        return amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return bidId;
    }

    public void setId(Long id) {
        this.bidId = id;
    }


    @Override
    public String toString() {
        return "Models.Bid [ id=" + bidId + " ]";
    }
}