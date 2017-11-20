package Models;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Jakob
 * Entity class for UserInstance.
 */
@Entity
@Table(name="userinstance")
public class UserInstance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "username")
    private String Username;

    private String phone;
    private String name;
    private String email;

    @Column(name = "password")
    private String Password;
    private int[] rating;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    List<BidInstance> bids;

    @OneToMany(mappedBy="seller", cascade = CascadeType.ALL)
    List<ProductInstance> products;

    @OneToMany(mappedBy="buyer", cascade = CascadeType.ALL)
    List<ProductInstance> boughtproducts;

    public List<ProductInstance> getBoughtproducts() {
        return boughtproducts;
    }

    public void setBoughtproducts(List<ProductInstance> boughtproducts) {
        this.boughtproducts = boughtproducts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int[] getRating() {
        return rating;
    }

    public double getAverage() {
        if(rating == null || rating.length == 0){
            return 0.0;
        }
        double total = 0;
        int count = 0;

        for(int i =1; i<rating.length; i++){
            total = total + rating[i]*i;
            count = count + rating[i];
        }

        total = (double) total/count;
        return total;
    }
    public int getRatingAtIndex(int i) {
        if(i< 0 || i< 6) {
            if(rating == null){
                return 0 ;
            }
            return rating[i];
        }
        return 0;
    }

    public void setRating(int[] rating) {
        this.rating = rating;
    }

    public List<BidInstance> getBids() {
        return bids;
    }

    public void setBids(ArrayList<BidInstance> bids) {
        this.bids = bids;
    }

    public List<ProductInstance> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductInstance> products) {
        this.products = products;
    }



    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Username != null ? Username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserInstance)) {
            return false;
        }
        UserInstance other = (UserInstance) object;
        if ((this.Username == null && other.Username != null) || (this.Username != null && !this.Username.equals(other.Username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "navn: " + getName() +", tlf: " +
                getPhone()+ ", passord: " + getPassword()
                 + ", username: " + getUsername();
    }

}
