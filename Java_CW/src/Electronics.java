//IIT No: 20220334
//UoW No: w1953208
//Name  : H.S.C.Samarasinghe

//implementing electronics child class of product class
public class Electronics extends Product{
    //encapsulating class attributes
private String brand;
private String warrantyPeriod;
//creating class constructor
    public Electronics(String prdId, String prdNm, int avlAmt, double prc, String brnd, String warntPrd) {
        super(prdId, prdNm, avlAmt, prc);
        brand = brnd;
        warrantyPeriod = warntPrd;
    }
    //overriding superclass methods
    @Override
    public String getInfo(){
        return brand+", "+warrantyPeriod+" warranty";
    }
    @Override
    public String getType(){
        return "Electronics";
    }
    @Override
    public String getProductDetails(){
        return "Product Id: "+getProductId()+"\nCategory: Clothing\n" +
                "Name: "+getProductName()+"\n" +
                "Brand: "+brand+"\nItem warranty: "+warrantyPeriod+"\nItems Available: "+getAvailableAmount();
    }
    //Overriding default toString method
    @Override
    public String toString(){
        return super.toString()+"Electronic brand: "+this.brand+"\n" +
                "Warranty period : "+this.warrantyPeriod+"\n";
    }
}
