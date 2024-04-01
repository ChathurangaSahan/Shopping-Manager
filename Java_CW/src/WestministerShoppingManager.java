//IIT No: 20220334
//UoW No: w1953208
//Name  : H.S.C.Samarasinghe

import java.io.*;
import java.util.*;
public class WestministerShoppingManager implements ShoppingManager, Serializable {
    //initializing static arraylists to hold entered products and registered users.
    //encapsulating class data
    private static ArrayList<Product> products;
    private static ArrayList<User> users;
    public WestministerShoppingManager(){
        Scanner scanner = new Scanner(System.in);
        //loading saved product and user data
        products = loadFromFile("products.dat");
        users = loadAccounts();

        String exit="";
        //implementing console menu
        while(!exit.equals("5")){
            System.out.println("""
                    Select option (enter number of selected option):
                        1.Add new product
                        2.Delete product
                        3.Print product list
                        4.Save to File
                        5.Exit
                        6.Open GUI""");
            String option = scanner.nextLine();
            //validating input
            while(true) {
                if(!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4") && !option.equals("5") && !option.equals("6")){
                    System.out.println("Invalid input!\nPlease try again.");
                    option = scanner.nextLine();
                }else {break;}
            }
            //using a switch to navigate implementation of menu options
            switch (option){
                case "1":{
                    //making sure no more than 50 products in system
                    if(products.size()<50){
                        getProductDetails();
                    }else {
                        System.out.println("Sorry! maximum amount of products reached.\n" +
                                "Delete unwanted products to input new products.");
                    }
                    break;
                }
                case "2":{
                    getDeleteProductDetails();
                    System.out.println(products.size()+" Products in system.");
                    break;
                }
                case "3":{
                    printProductList();
                    break;
                }
                case "4":{
                    saveToFile(products,"products.dat");
                    System.out.println("Saved successfully.");
                    break;
                }
                case "5":{
                    exit="5";
                    break;
                }
                case "6":{
                    User loguser = userAccount();
                    //validating successful login
                    if(loguser!=null){
                        new GUI(loguser);
                    }
                    break;
                }
            }
        }
        //exit message
        System.out.println("Thank you for using this app!\nHave a nice day :)");
    }

    //method used to get product details from user
    public void getProductDetails(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Select type of product to add (enter number of selected type):
                    1.Clothing
                    2.Electronics""");
        String typeString = scanner.nextLine();
        //validating input
        while(true) {
            if(!typeString.equals("1") && !typeString.equals("2")){
                System.out.println("Invalid input!\nPlease try again.");
                typeString = scanner.nextLine();
            }else {break;}
        }
        int type=Integer.parseInt(typeString);
        //collecting the detail common to all products first
        System.out.println("Enter product Id: ");
        String prdId = scanner.nextLine();
        while(true) {
            if(prdId.isEmpty()){
                System.out.println("Invalid input!\nPlease try again.");
                prdId = scanner.nextLine();
            }else {break;}
        }
        System.out.println("Enter product name: ");
        String prdNm = scanner.nextLine();
        while(true) {
            if(prdNm.isEmpty()){
                System.out.println("Invalid input!\nPlease try again.");
                prdNm = scanner.nextLine();
            }else {break;}
        }
        System.out.println("Enter available stock amount: ");
        int avlAmt;
        //validating input
        while (true) {
            String avlAmtString = scanner.nextLine();
            try {
                avlAmt = Integer.parseInt(avlAmtString);
                if(avlAmt<0){
                    System.out.println("Invalid input!\nPlease enter a valid integer.");
                }
                else{break;}
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!\nPlease enter a valid integer.");
            }
        }
        System.out.println("Enter product unit price: ");
        double prc;
        //validating input
        while (true) {
            String prcString = scanner.nextLine();
            try {
                prc = Double.parseDouble(prcString);
                if(prc<0){
                    System.out.println("Invalid input!\nPlease enter a valid integer.");
                }
                else{break;}
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!\nPlease enter a valid double.");
            }
        }
        //using switch to collect clothing and electronics unique data
        switch (type){
            case 1: {
                System.out.println("Enter clothing size: ");
                String siz = scanner.nextLine();
                while(true) {
                    if(siz.isEmpty()){
                        System.out.println("Invalid input!\nPlease try again.");
                        siz = scanner.nextLine();
                    }else {break;}
                }
                System.out.println("Enter clothing colour: ");
                String color = scanner.nextLine();
                while(true) {
                    if(color.isEmpty()){
                        System.out.println("Invalid input!\nPlease try again.");
                        color = scanner.nextLine();
                    }else {break;}
                }
                System.out.println("Enter clothing brand: ");
                String brnd = scanner.nextLine();
                while(true) {
                    if(brnd.isEmpty()){
                        System.out.println("Invalid input!\nPlease try again.");
                        brnd = scanner.nextLine();
                    }else {break;}
                }
                //creating and adding new clothing to system
                addNewProduct(prdId, prdNm, avlAmt, prc, siz, color, brnd);
                break;
            }
            case 2: {
                System.out.println("Enter electronics brand: ");
                String brnd = scanner.nextLine();
                while(true) {
                    if(brnd.isEmpty()){
                        System.out.println("Invalid input!\nPlease try again.");
                        brnd = scanner.nextLine();
                    }else {break;}
                }
                System.out.println("Enter warranty period (\"x years\", \"y months\" or \" x weeks\"): ");
                String warntPrd;
                while(true){
                    warntPrd = scanner.nextLine();
                    //validating user input meets requirements
                    if((warntPrd.contains("years" )|| warntPrd.contains("months"))||(warntPrd.contains("weeks" ))){
                        break;
                    }else{
                        System.out.println("Invalid input!\nPlease enter in \"x years\", \"y months\" or \" x weeks\" form.");
                    }
                }
                //creating and adding new electronics to system
                addNewProduct(prdId, prdNm, avlAmt, prc, brnd, warntPrd);
                break;
            }
        }
        System.out.println("New product added!");
    }
    //implementing delete method to remove added products
    public void getDeleteProductDetails(){
        Scanner scanner = new Scanner(System.in);
        //checking whether product to be deleted exists
        if(products.isEmpty()){
            System.out.println("No products to delete!");
        }
        else {
            System.out.println("Enter product Id of product to delete: ");
            String productId = scanner.nextLine();

            deleteProduct(productId);
        }
    }
    //method to create and add clothing to products array
    public void addNewProduct(String prdId, String prdNm, int avlAmt, double prc, String siz, String color, String brnd) {
        Clothing cloth = new Clothing(prdId, prdNm, avlAmt, prc, siz, color, brnd);
        products.add(cloth);
    }
    //method to create and add electronics to products array
    public void addNewProduct(String prdId, String prdNm, int avlAmt, double prc, String brnd, String warntPrd) {
        Electronics electronic = new Electronics(prdId, prdNm, avlAmt, prc, brnd, warntPrd);
        products.add(electronic);
    }
//    method that can be used to directly enter given product to products array. Not used in current version.
//    public void addNewProduct(Product product){
//        products.add(product);
//    }
    //creating method to remove products from products array based on the input product ID
    public void deleteProduct(String productId) {
        boolean productDeleted=false;
        for(int i=0; i<products.size(); i++){
            if(Objects.equals(products.get(i).getProductId(), productId)){
                products.remove(i);
                productDeleted=true;
                System.out.println("Product "+productId+" successfully deleted!");
                break;
            }
        }
        //informing user about product based on given ID not existing
        if (!productDeleted) {
            System.out.println("Product "+productId+" not found!");
        }
    }
    //creating method to print products array to string
    public void printProductList() {
        Collections.sort(products, Comparator.comparing(Product::getProductId));
        if(products.isEmpty()){
            System.out.println("No products to display!");
        }
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }
    //creating method to save products array to outside file
    public void saveToFile(ArrayList<Product> products, String fileName) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(products);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    //creating method to load saved products array data from outside file
    public ArrayList<Product> loadFromFile(String fileName){
        ArrayList<Product> products = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            products = (ArrayList<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }
    //creating method to handle user account related data
    public User userAccount(){
        User userAccount=null;
        Scanner scanner = new Scanner(System.in);
        //giving user the option to login to previously registered accounts and create new accounts
        System.out.println("""
                Select option:
                    1.Login to user account
                    2.Create new account""");
        String option = scanner.nextLine();
        //validating user input
        while(true) {
            if(!option.equals("1") && !option.equals("2")){
                System.out.println("Invalid input!\nPlease try again.");
                option = scanner.nextLine();
            }else {break;}
        }
        //using switch to implement user login and account creation
        switch (option){
            case "1": {
                userAccount = userLogin();
                break;
            }
            case "2":{
                userAccount = userCreation();
                userAccount.changeFirstTime();
                break;
            }
        }
        return userAccount;
    }
    //creating user login method
    public User userLogin(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String usrNm = scanner.nextLine();
        System.out.println("Enter password:");
        String pswrd = scanner.nextLine();

        boolean loggedIn=false;
        boolean accFound=false;
        User user = null;
        //checking username and password
        for(int i=0; i<users.size(); i++){
            if(Objects.equals(users.get(i).getUsername(), usrNm)){
                accFound=true;
                if(!Objects.equals(users.get(i).getPassword(), pswrd)){
                    System.out.println("Incorrect password!");
                    user=null;
                }
                else {
                    user=users.get(i);
                    System.out.println("Successfully logged in!");
                }
                break;
            }
        }
        //informing user of account not existing
        if (!accFound) {
            System.out.println("Account not found!");
            user=null;
        }
        return user;
    }
    //creating method to create user account
    public User userCreation(){
        Scanner scanner = new Scanner(System.in);
        //getting new username and password
        String usrNm;
        //making sure of availability of username
        while(true){
            System.out.println("Enter new username:");
            usrNm = scanner.nextLine();
            boolean usrAva = true;
            for(int i=0;i<users.size();i++){
                if(Objects.equals(usrNm, users.get(i).getUsername())){
                    usrAva=false;
                }
            }
            if(!usrAva){
                System.out.println("Username not available!\nPlease try again.");
            }else{break;}
        }
        //getting new password
        System.out.println("Enter new password:");
        String pswrd = scanner.nextLine();
        //creating new account
        User newUser = new User(usrNm,pswrd);
        System.out.println("Successfully create account!\n" +
                "   Account username is "+usrNm+
                "   Account password is "+pswrd);
        users.add(newUser);
        saveAccounts(users);
        return newUser;
    }
    //creating method to save account details to an external file
    public void saveAccounts(ArrayList<User> users){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userAccounts.dat"))){
            oos.writeObject(users);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    //creating method to loading user accounts from external file
    public ArrayList<User> loadAccounts(){
        ArrayList<User> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userAccounts.dat"))) {
            users = (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
    //creating method to sort products based on product ID and return sorted array
    public static ArrayList<Product> getProductsArray(){
        Collections.sort(products, Comparator.comparing(Product::getProductId));
        return products;
    }
}


