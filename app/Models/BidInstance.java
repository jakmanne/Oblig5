package Models;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Jakob
 * Entity class for BidInstance
 */
@Entity
@Table(name="bidinstance")
public class BidInstance implements Serializable {

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(generator = "system-uuid")
    //@GenericGenerator(name = "system-uuid", strategy = "uuid")
    private Long bidId;

    @OneToOne(mappedBy="currentBid", cascade = CascadeType.ALL)
    private ProductInstance product;

    @ManyToOne
    private UserInstance user;

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

    public ProductInstance getProduct() {
        return product;
    }

    public void setProduct(ProductInstance product) {
        this.product = product;
    }

    public UserInstance getUser() {
        return user;
    }

    public void setUser(UserInstance user) {
        this.user = user;
    }

    public Long getId() {
        return bidId;
    }

    public void setId(Long id) {
        this.bidId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bidId != null ? bidId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BidInstance)) {
            return false;
        }
        BidInstance other = (BidInstance) object;
        if ((this.bidId == null && other.bidId != null) || (this.bidId != null && !this.bidId.equals(other.bidId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.BidInstance[ id=" + bidId + " ]";
    }

}