//IIT No: 20220334
//UoW No: w1953208
//Name  : H.S.C.Samarasinghe

//implementing electronics child class of product class
import java.io.Serializable;
import java.util.ArrayList;
//creating shoppingCart class to record purchase data
public class ShoppingCart implements Serializable {
    //encapsulating class attributes
    private ArrayList<Product> cartProducts;
    //creating getters and setters
    public ShoppingCart(){
        cartProducts = new ArrayList<>();
    }
    public ArrayList<Product> getCartProductsArray(){
        return cartProducts;
    }
    //creating method to add products to cart
    public void addToCart(Product product){
        boolean bought=false;
        //checking whether the same product has been bought before
        for(int i=0; i < cartProducts.size(); i++){
            if(cartProducts.get(i) == product){
                //if product bought before increase bought quantity
                cartProducts.get(i).productBought();
                bought = true;
            }
        }
        if(!bought){
           cartProducts.add(product);
           product.productBought();
        }
    }
    //method to calculate total price of products in cart without discounts
    public double getTotalPrice(){
        double total=0;
        Product product;
        for (Product cartProduct : this.cartProducts) {
            product = cartProduct;
            total = total + (product.getPrice()*product.getQuantityBought());
        }
        return total;
    }
    //checking eligibility of getting discount for buying 3 or more products of same category
    public boolean getDiscountElligibility(){
        boolean discount = false;
        int clothNum=0;
        int elcNum=0;
        for (int i=0; i<cartProducts.size(); i++){
            if(cartProducts.get(i) instanceof Clothing){
                clothNum=cartProducts.get(i).getQuantityBought()+clothNum;
            }
            else{
                elcNum=cartProducts.get(i).getQuantityBought()+clothNum;
            }
        }
        if(clothNum>2 || elcNum>2){
            discount = true;
        }
        return discount;
    }
    //method to calculate final price with discounts
    public double getFinalPrice(User user){
        boolean firsttime= user.userFirstTime();
        boolean discount=user.getCart().getDiscountElligibility();
        double total=getTotalPrice();
        if(discount && !firsttime) {
            total = total * 0.8;
        }
        if(discount && firsttime){
            total = total * 0.7;
        }
        if(!discount && firsttime){
            total = total * 0.9;
        }
        return total;
    }
}
