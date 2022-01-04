import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Outsourced extends Part {
    /**
     * @author Christopher Sequeira
     *
     * LOGICAL ERROR
     * All Outsourced Part objects have "companyName" as their companyName string
     *
     * LOGICAL ERROR FIX
     * Intent: getCompanyName() method should return an object's companyName attribute
     * Problem: getCompanyName() method was written with double quotes as -> return "companyName";
     * Fix: removed quotes from the return statement, so that the attribute is returned and not a string literal
     *
     * FUTURE ENHANCEMENT
     * setOutsourced() and setCompanyName() are never used, they could be removed from the code to save compilation time and memory
     */

    private String companyName;
    public static ObservableList<Outsourced> allOutsourced = FXCollections.observableArrayList();

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    public static ObservableList<Outsourced> getOutsourced() {
        return allOutsourced;
    }

    public void setOutsourced(Outsourced outsourced) {
        allOutsourced.add(outsourced);
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}
