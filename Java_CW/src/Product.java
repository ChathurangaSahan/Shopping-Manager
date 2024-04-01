//IIT No: 20220334
//UoW No: w1953208
//Name  : H.S.C.Samarasinghe

import java.io.Serializable;
//creating product super class. implementing Serializable to accommodate save and load features
public abstract class Product implements Serializable {
    //encapsulating class data
    private String productId;
    private String productName;
    private int availableAmount;
    private double price;
    private int quantityBought=0;
    //creating class constructor
    public Product(String prdId, String prdNm, int avlAmt, double prc){
        productId = prdId;
        productName = prdNm;
        availableAmount = avlAmt;
        price = prc;
    }
    //creating method to get price of products
    public double getPrice(){
        return this.price;
    }
    //creating method to get product ID of products
    public String getProductId(){
        return this.productId;
    }
    //creating method to get available amount of products
    public int getAvailableAmount() {
        return this.availableAmount;
    }
    //creating method to get product name of products
    public String getProductName(){
        return this.productName;
    }
    //creating method to record multiple selections of same product
    public void productBought(){
        quantityBought++;
    }
    //creating method to get selected products purchased quantity
    public int getQuantityBought(){
        return quantityBought;
    }
    //initiating methods to be used in sub/child class
    public String getInfo(){return "";}
    public String getType(){return "";}
    public String getProductDetails(){return "";}
    //creating new toString method
    @Override
    public String toString() {
        return "Product Id      : " + this.getProductId() + "\n" +
                "Product name    : " + this.getProductName() + "\n" +
                "Available stock : " + this.getAvailableAmount() + "\n" +
                "Unit price      : " + this.getPrice() + "\n";
    }
}
