//IIT No: 20220334
//UoW No: w1953208
//Name  : H.S.C.Samarasinghe

import java.util.ArrayList;

//creating an interface for shopping manager
public interface ShoppingManager {
    void addNewProduct(String prdId, String prdNm, int avlAmt, double prc, String siz, String color, String brnd);
    void addNewProduct(String prdId, String prdNm, int avlAmt, double prc, String brnd, String warntPrd);
    void deleteProduct(String productId);
    void printProductList();
    void saveToFile(ArrayList<Product> products, String fileName);
}
