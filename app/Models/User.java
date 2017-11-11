package Models;


import java.util.*;
import play.db.*;


public class User {

    private String navn;
    private String Username;
    private String phone;
    private String name;
    private String email;
    private String Password;
    private int[] rating;
    List<Bid> bids;
    List<Product> products;
    List<Product> boughtproducts;

    public String getNavn() {
        return navn;
    }
    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getUsername() {return Username; }
    public void setUsername(String username) { Username = username;}

    public String getPhone() { return phone;}
    public void setPhone(String phone) {this.phone = phone; }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}
    public void setEmail(String email) { this.email = email; }

    public String getPassword() {return Password; }
    public void setPassword(String password) {Password = password; }

    public int[] getRating() {return rating; }
    public void setRating(int[] rating) {this.rating = rating;}

    public List<Bid> getBids() { return bids;}
    public void setBids(List<Bid> bids) {this.bids = bids;}

    public List<Product> getProducts() {return products;}
    public void setProducts(List<Product> products) {this.products = products;}

    public List<Product> getBoughtproducts() {return boughtproducts;}
    public void setBoughtproducts(List<Product> boughtproducts) {this.boughtproducts = boughtproducts;}

}
