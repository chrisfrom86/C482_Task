/**
 * Supplied class Part.java
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Christopher Sequeira
 */
public class Product {
    /**
     * @author Christopher Sequeira
     *
     * RUNTIME ERROR
     * C:\Users\chris\IdeaProjects\C482_Task\src\Product.java:101:13
     * java: cannot find symbol
     *   symbol: variable inventory
     *
     * RUNTIME ERROR FIX
     * Intent: int stock refers to the inventory level of the item
     * Problem: variable is referred to as "Inventory" when output to the program, but "stock" as the internal integer
     * Fix: made consistent references to int stock as 'stock' everywhere internally to code and "Inventory" when output
     *
     * FUTURE ENHANCEMENT
     * setStock() is never used, it could be removed from the code to save compilation time and memory
     */

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    private ObservableList<Part> productParts;


    public Product(int id, String name, double price, int stock, int min, int max, ObservableList<Part> productParts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.productParts = productParts;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    public ObservableList<Part> getProductParts() { return productParts; }

    public void setProductParts(ObservableList<Part> productParts) { this.productParts = productParts; }

}