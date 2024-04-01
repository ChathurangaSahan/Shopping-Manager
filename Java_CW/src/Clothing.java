//IIT No: 20220334
//UoW No: w1953208
//Name  : H.S.C.Samarasinghe

//implementing electronics child class of product class
public class Clothing extends Product{
    //encapsulating class attributes
    private String size;
    private String colour;
    private String brand;
    //creating class constructor
    public Clothing(String prdId, String prdNm, int avlAmt, double prc, String siz, String color, String brnd) {
        super(prdId, prdNm, avlAmt, prc);
        size = siz;
        colour = color;
        brand = brnd;
    }
    //overriding superclass methods
    @Override
    public String getInfo(){
        return size+", "+brand+","+colour;
    }
    @Override
    public String getType(){
        return "Clothing";
    }
    @Override
    public String getProductDetails(){
        return "Product Id: "+getProductId()+"\nCategory: Clothing\n" +
                "Name: "+getProductName()+"\nSize: "+size+"\nColour: "+colour+"\n" +
                "Brand: "+brand+"\nItems Available: "+getAvailableAmount();
    }
    //Overriding default toString method
    @Override
    public String toString(){
        return super.toString()+"Clothing size   : "+this.size+"\n" +
                "Clothing colour : "+this.colour+"\n"+
                "Clothing brand  : "+this.brand+"\n";
    }
}
