//IIT No: 20220334
//UoW No: w1953208
//Name  : H.S.C.Samarasinghe

//implementing electronics child class of product class
import java.io.Serializable;
import java.util.ArrayList;
//implementing user class representing the users
public class User implements Serializable {
    //encapsulating class attributes
    private String username;
    private String password;
    private ShoppingCart cart;
    private boolean firstTime=false;
    //creating class constructor
    public User(String usernme, String passwrd){
        username = usernme;
        password = passwrd;
        cart = new ShoppingCart();
    }
    //creating getter and setters
    public String getPassword(){
        return this.password;
    }
    public String getUsername(){
        return this.username;
    }
    public void addToUserCart(Product product){
        cart.addToCart(product);
    }
    public ArrayList<Product> getUserCartProductList(){
        return cart.getCartProductsArray();
    }
    //creating methods to manipulate boolean firstTime to facilitate discount calculations
    public void changeFirstTime(){
        this.firstTime=true;
    }
    public boolean userFirstTime(){
        return firstTime;
    }
    public ShoppingCart getCart(){
        return this.cart;
    }
}
