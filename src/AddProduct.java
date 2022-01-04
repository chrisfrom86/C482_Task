import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AddProduct implements Initializable {
    /**
     * @author Christopher Sequeira
     *
     * RUNTIME ERROR
     *C:\Users\chris\IdeaProjects\C482_Task\src\AddProduct.java:162:45
     * java: variable x might not have been initialized
     *
     * RUNTIME ERROR FIX
     * Intent: check all product IDs for highest number, then add 1 to generate new product ID
     * Problem: int x is created to iterate against all the product IDs, but is not initialized, therefore it cannot be compared to Product IDs
     * Fix: initialized x to 0, so that all Product IDs will be greater than x initially and the proper next highest int will be found
     *
     * FUTURE ENHANCEMENT
     * The Add Associated Part button in both the AddProduct and UpdateProduct classes is the same code
     * I could create a single method and use it on these two classes to simplify the code as well as simplifying any updates to this method
     */

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Part> assocParts = FXCollections.observableArrayList();
    public TextField partSearch;
    public TableView productPartTableView;
    public TableColumn productPartID;
    public TableColumn productPartName;
    public TableColumn productPartInventory;
    public TableColumn productPartPrice;
    public Button addAssocPartButton;
    public Button removeAssocPartButton;
    public Button newProductAddButton;
    public Button addProductCancelButton;
    public TableView assocPartTableView;
    public TableColumn assocPartID;
    public TableColumn assocPartName;
    public TableColumn assocPartInventory;
    public TableColumn assocPartPrice;
    public TextField newProductID;
    public TextField newProductName;
    public TextField newProductInventory;
    public TextField newProductPrice;
    public TextField newProductMax;
    public TextField newProductMin;
    public Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Product screen opened");
        allParts = Controller.getAllParts();
        getPartsTable();
    }

    public TableView<Part> getPartsTable() {
        productPartTableView.setItems(allParts);
        productPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        return productPartTableView;
    }

    public TableView<Part> getAssocPartsTable() {
        assocPartTableView.setItems(assocParts);
        assocPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        return assocPartTableView;
    }

    public void onProductPartSearch() {
        try {
            Integer.parseInt(partSearch.getText());
            System.out.println(partSearch.getText() + " is a number");
            lookupPart(Integer.parseInt(partSearch.getText()));
        } catch (NumberFormatException e) {
            System.out.println(partSearch.getText() + " is not a number");
            lookupPart(partSearch.getText());
        }
        partSearch.clear();
    }

    public ObservableList<Part> lookupPart(int partialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Controller.getAllParts();

        for (Part part : allParts) {
            if (part.getId() == partialName) {
                productPartTableView.getSelectionModel().select(part);
            }
        }
        return namedParts;
    }

    public ObservableList<Part> lookupPart(String inputName) {

        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Controller.getAllParts();
        String partialName = inputName.toLowerCase(Locale.ROOT);

        for (Part part : allParts) {
            if (part.getName().toLowerCase(Locale.ROOT).contains(partialName))
                namedParts.add(part);

            productPartTableView.setItems(namedParts);
            partSearch.setText("");
        }
        return namedParts;
    }

    public void onAddAssocPartButton(ActionEvent event) {
        errorLabel.setText("");
        Part Part = (Part) productPartTableView.getSelectionModel().getSelectedItem();
        if (!(Part == null)) {
            System.out.println("Part associated with Product");
            if (productPartTableView.getSelectionModel().getSelectedItem() instanceof InHouse) {
                System.out.println("InHouse: " + productPartTableView.getSelectionModel().getSelectedItem());
                InHouse addPart = (InHouse) productPartTableView.getSelectionModel().getSelectedItem();
                assocParts.add(addPart);
            } else if (productPartTableView.getSelectionModel().getSelectedItem() instanceof Outsourced) {
                System.out.println("Outsourced: " + productPartTableView.getSelectionModel().getSelectedItem());
                Outsourced addPart = (Outsourced) productPartTableView.getSelectionModel().getSelectedItem();
                assocParts.add(addPart);
            }
            getAssocPartsTable();
        } else {
            errorLabel.setText("Select a part to associate.");
        }
    }

    public void onRemoveAssocPartButton(ActionEvent event) {
        errorLabel.setText("");
        Part Part = (Part) assocPartTableView.getSelectionModel().getSelectedItem();
        if (!(Part == null)) {
            System.out.println("Part-Product association removed");
            Part part = (Part) assocPartTableView.getSelectionModel().getSelectedItem();
            System.out.println(part);
            ConfirmBox.display("Remove Part", "Remove Associated Part?");
            if (ConfirmBox.answer)
                assocParts.remove(part);
            getAssocPartsTable();
        } else {
            errorLabel.setText("Select associated part to remove.");
        }
    }

    public void onNewProductAddButton() throws IOException {
        ObservableList<Product> productsList = Controller.getAllProducts(null);
        int x = 0;

            for (Product product1 : productsList)
                if (product1 == null) {}
                else if (product1.getId() > x)
                    x = product1.getId();
            x += 1;
        int id = x;

        errorLabel.setText("");
        boolean createProduct = true;
        String name = newProductName.getText();
        if (name == null || newProductName.getText().trim().isEmpty()) {
            errorLabel.setText(errorLabel.getText() + "Enter a name.\n");
            createProduct = false;
        }
        double price = -1;
        try {
            price = Double.parseDouble(newProductPrice.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(errorLabel.getText() + "Price must be a number.\n");
            createProduct = false;
        } catch (Exception e) {
            System.out.println("price exception: " + e);
            errorLabel.setText(errorLabel.getText() + "Price must be a number.\n");
            createProduct = false;
        }
        int stock = -1;
        try {
            stock = Integer.parseInt(newProductInventory.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(errorLabel.getText() + "Inventory must be a number.\n");
            createProduct = false;
        } catch (Exception e) {
            System.out.println("inventory exception: " + e);
            errorLabel.setText(errorLabel.getText() + "Inventory must be a number.\n");
            createProduct = false;
        }
        int min = -1;
        try {
            min = Integer.parseInt(newProductMin.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(errorLabel.getText() + "Min must be a number.\n");
            createProduct = false;
        } catch (Exception e) {
            System.out.println("min exception: " + e);
            errorLabel.setText(errorLabel.getText() + "Min must be a number.\n");
            createProduct = false;
        }
        int max = -2;
        try {
            max = Integer.parseInt(newProductMax.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(errorLabel.getText() + "Max must be a number.\n");
            createProduct = false;
        } catch (Exception e) {
            System.out.println("max exception: " + e);
            errorLabel.setText(errorLabel.getText() + "Max must be a number.\n");
            createProduct = false;
        }

        if (min >= max || min == -1 || max == -2) {
            errorLabel.setText(errorLabel.getText() + "Min must be less than Max.\n");
            createProduct = false;
        }
        if (stock < min || stock > max)  {
            errorLabel.setText(errorLabel.getText() + "Inventory must be between Min and Max.\n");
            createProduct = false;
        }

        if (createProduct) {
            System.out.println("New Product saved");
            ObservableList<Part> noParts = null;
            Product product = new Product(id, name, price, stock, min, max, null);
            product.setProductParts(FXCollections.observableArrayList(assocParts));
            Controller.getAllProducts(product);
            assocParts.clear();
            Stage stage = (Stage) newProductAddButton.getScene().getWindow();
            stage.close();
        }
    }

    public void onAddProductCancelButton(ActionEvent event) {
        System.out.println("Cancel product add");
        Stage stage = (Stage) addProductCancelButton.getScene().getWindow();
        stage.close();
    }

}
