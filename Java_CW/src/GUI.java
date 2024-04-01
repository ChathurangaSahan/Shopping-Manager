//IIT No: 20220334
//UoW No: w1953208
//Name  : H.S.C.Samarasinghe

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//implementing main GUI of the system
public class GUI extends JFrame implements ActionListener{
    //encapsulating and initializing class attributes
    private static ArrayList<Product> productList;
    private static ArrayList<Product> currentList;
    private JLabel label1 = new JLabel("Select Product Category");
    private JLabel label2 = new JLabel("Selected Product - Details");
    private JTextArea textArea = new JTextArea("");
    private JButton shoppingCartBtn = new JButton("Shopping Cart");
    private JButton addToShoppingCartBtn = new JButton("Add to Shopping Cart");
    private JComboBox productType = new JComboBox();
    private DefaultTableModel tableModel;
    private JTable productsTable;
    private int tableShow = 0;
    private int selectedRow=0;
    private User user;
    private Product selectedProductWhole;
//creating main JFrame of the GUI
    JFrame mainFrame = new JFrame();
    public GUI(User currentUser){
        //getting products array and user array
    productList=WestministerShoppingManager.getProductsArray();
    user=currentUser;
//setting up main frame
        mainFrame.setTitle("Westminster Shopping Centre");
        mainFrame.setSize(700,700);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        //making frame un-resizable for easier handling of components
        mainFrame.setResizable(false);
        //dividing main frame into two, upper and lower parts for better implementation
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(700,350));
        topPanel.setBounds(0,0,700,350);
        topPanel.setLayout(null);
        //adding top panel to main frame
        mainFrame.add(topPanel);
        //adding "select product category" label to top panel
        topPanel.add(label1);
        label1.setBounds(10,25,200,20);
        //creating combo box for category options
        String[] prdTypes = {"All","Electronics","Clothing"};
        productType = new JComboBox(prdTypes);
        productType.addActionListener(this);
        //adding combobox to top panel
        topPanel.add(productType);
        productType.setBounds(220,20,200,30);
        //adding shopping cart button to top panel
        topPanel.add(shoppingCartBtn);
        shoppingCartBtn.setPreferredSize(new Dimension(100,20));
        shoppingCartBtn.setFocusable(false);
        shoppingCartBtn.setBounds(450,20,200,30);
        //adding event listeners to get user input
        shoppingCartBtn.addActionListener(this);
        //creating default table for products
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Price(\u00A3)");
        tableModel.addColumn("Info");
        //adding table rows
        addTableRows(tableShow);
        //adding table model as a JTable
        productsTable = new JTable(tableModel);
        productsTable.setRowHeight(60);
        //creating Jscrollpane JTable
        JScrollPane scrollPane = new JScrollPane(productsTable);
        topPanel.add(scrollPane);
        scrollPane.setBounds(10,60,670,280);

        //creating product details sections
        ListSelectionModel model=productsTable.getSelectionModel();
        //adding event listener to get user selection from JTable
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //checking if user selection is empty
                if(!model.isSelectionEmpty()){
                    //getting user selected row number
                    int selectedRowtem = model.getMinSelectionIndex();
                    selectedRow = selectedRowtem;
                    //adding selected products details to text area
                    textArea.setText(getSelectedRowText(selectedRow));
                }
            }
        });
        //creating bottom panel of main frame
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(700,350));
        bottomPanel.setBounds(0,350,700,350);
        bottomPanel.setLayout(null);
        //adding bottom panel to main frame
        mainFrame.add(bottomPanel);
        //adding 'selected products - details' label
        bottomPanel.add(label2);
        label2.setFont(new Font("Calibri",Font.BOLD,18));
        label2.setBounds(30,5, 300, 30);
        //adding text area for product details
        bottomPanel.add(textArea);
        textArea.setBounds(30,35,600,200);
        textArea.setFont(new Font("Calibri",Font.PLAIN,18));
        textArea.setBackground(null);
        //adding add to shopping cart button to bottom panel
        bottomPanel.add(addToShoppingCartBtn);
        addToShoppingCartBtn.setPreferredSize(new Dimension(100,20));
        addToShoppingCartBtn.setFocusable(false);
        addToShoppingCartBtn.setBounds(200,280,300,30);
        //adding event listeners to get user input when selecting add to cart option
        addToShoppingCartBtn.addActionListener(this);
    }
    //creating actionPerformed class to respond to user inputs
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==shoppingCartBtn){
//            mainFrame.dispose();
            ShoppingCartGUI shoppingCartGUI = new ShoppingCartGUI(user);
        } else if (e.getSource()==productType) {
            tableShow=productType.getSelectedIndex();
            int m =tableModel.getRowCount();
            for(int i=0; i<m; i++){
                tableModel.removeRow(0);
            }
            addTableRows(tableShow);
        }else if (e.getSource()==addToShoppingCartBtn){
            user.addToUserCart(selectedProductWhole);
        }
    }
    //creating method to filter products based on category
    public ArrayList<Product> getProducts(int ProductType){
        ArrayList<Product> temp = new ArrayList<Product>();
        switch (ProductType){
            case 0:{
                for(int i=0;i<productList.size();i++){
                    temp.add(productList.get(i));
                }
                break;
            }
            case 1:{
                for (Product product : productList) {
                    if (product instanceof Electronics) {
                        temp.add(product);
                    }
                }
                break;
            }
            case 2:{
                for (Product product : productList) {
                    if (product instanceof Clothing) {
                        temp.add(product);
                    }
                }
                break;
            }
        }
        return temp;
    }
    //creating method to add table rows to productsTable
    public void addTableRows(int productType){
        ArrayList<Product> tempArray = getProducts(productType);
        currentList = getProducts(productType);
        //creates a list with the row data and adds the list to the table
        String[] list = new String[5];
        for(int i=0; i<tempArray.size(); i++){
            list[0]=tempArray.get(i).getProductId();
            list[1]=tempArray.get(i).getProductName();
            list[2]=tempArray.get(i).getType();
            list[3]= String.valueOf(tempArray.get(i).getPrice());
            list[4]=tempArray.get(i).getInfo();

            tableModel.addRow(list);
        }
    }
    //creating method to get product details of the selected row of productsTable
    public String getSelectedRowText(int rownum){
        Product selectedProduct = currentList.get(rownum);
        selectedProductWhole=selectedProduct;
        return selectedProduct.getProductDetails();
    }
}
