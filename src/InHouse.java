import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InHouse extends Part {
    /**
     * @author Christopher Sequeira
     *
     * RUNTIME ERROR
     * C:\Users\chris\IdeaProjects\C482_Task\src\InHouse.java:27:24
     * java: cannot find symbol
     *   symbol:   variable Part
     *   location: class InHouse
     *
     * RUNTIME ERROR FIX
     * Intent: setInHouse() method should add an InHouse class object to the ObservableList<InHouse>
     * Problem: setInHouse() method was set to allInHouse.add(InHouse); which literally adds the class, but is a runtime error
     * Fix: changed the InHouse class call to a passed-in parameter from the method called inHouse (difference in case for initial I)
     *
     * FUTURE ENHANCEMENT
     * setMachineID(), getInHouse(), and setInHouse() are never used, they could be removed from the code to save compilation time and memory
     */

    private int machineID;
    public static ObservableList<InHouse> allInHouse = FXCollections.observableArrayList();

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    public int getMachineID() {
        return machineID;
    }

    public static ObservableList<InHouse> getInHouse() {
        return allInHouse;
    }

    public void setInHouse(InHouse inHouse) {
        allInHouse.add(inHouse);
    }
}
