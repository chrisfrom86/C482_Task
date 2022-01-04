import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class UpdateProduct implements Initializable {
    /**
     * @author Christopher Sequeira
     *
     * RUNTIME ERROR
     *C:\Users\chris\IdeaProjects\C482_Task\src\UpdateProduct.java:244:31
     * java: constructor Product in class Product cannot be applied to given types;
     *   required: int,java.lang.String,double,int,int,int,javafx.collections.ObservableList<Part>
     *   found: int,java.lang.String,double,int,int,int
     *   reason: actual and formal argument lists differ in length
     *
     * RUNTIME ERROR FIX
     * Intent: generate a Product object with no associated parts, because associated parts are added on the next line.
     * Problem: instantiated a Product object without the proper constructors (did not include ObservableList<Part>)
     * Fix: instantiated the Product with 'null' as the ObservableList constructor, next lines of code add the correct items to the list
     *
     * FUTURE ENHANCEMENT
     * The if statement checking that inventory int is between min int and max int is the same code across all classes where it is used.
     * I could add the if statement to a method and call that method every place it is needed.
     * If any update is needed for this code, it would then only need to be changed once.
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
    public Button updateProductButton;
    public Button updateProductCancelButton;
    public TableView assocPartTableView;
    public TableColumn assocPartID;
    public TableColumn assocPartName;
    public TableColumn assocPartInventory;
    public TableColumn assocPartPrice;
    public TextField updateProductID;
    public TextField updateProductName;
    public TextField updateProductInventory;
    public TextField updateProductPrice;
    public TextField updateProductMax;
    public TextField updateProductMin;
    public Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Update Product screen opened");
        allParts = Controller.getAllParts();
        getSelectedProduct();
        getPartsTable();
        getAssocPartsTable();
    }

    public void getSelectedProduct() {
        int sProdID = Controller.productForUpdate.getId();
        String sProdName = Controller.productForUpdate.getName();
        double sProdPrice = Controller.productForUpdate.getPrice();
        int sProdStock = Controller.productForUpdate.getStock();
        int sProdMin = Controller.productForUpdate.getMin();
        int sProdMax = Controller.productForUpdate.getMax();
        ObservableList<Part> sProdPartsList = Controller.productForUpdate.getProductParts();

        updateProductID.setText(String.valueOf(sProdID));
        updateProductName.setText(sProdName);
        updateProductInventory.setText(String.valueOf(sProdStock));
        updateProductPrice.setText(String.valueOf(sProdPrice));
        updateProductMin.setText(String.valueOf(sProdMin));
        updateProductMax.setText(String.valueOf(sProdMax));
        assocParts = sProdPartsList;
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

    ObservableList<Product> allProducts = Controller.getAllProducts(null);
    public void onUpdateProductButton() {
        System.out.println("Product Updated");

        errorLabel.setText("");
        boolean updateProduct = true;

        int id = Integer.parseInt(updateProductID.getText());

        String name = updateProductName.getText();
        if (name == null || updateProductName.getText().trim().isEmpty()) {
            errorLabel.setText(errorLabel.getText() + "Enter a name.\n");
            updateProduct = false;
        }

        double price = -1;
        try {
            price = Double.parseDouble(updateProductPrice.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(errorLabel.getText() + "Price must be a number.\n");
            updateProduct = false;
        } catch (Exception e) {
            System.out.println("price exception: " + e);
            errorLabel.setText(errorLabel.getText() + "Price must be a number.\n");
            updateProduct = false;
        }

        int stock = -1;
        try {
            stock = Integer.parseInt(updateProductInventory.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(errorLabel.getText() + "Inventory must be a number.\n");
            updateProduct = false;
        } catch (Exception e) {
            System.out.println("inventory exception: " + e);
            errorLabel.setText(errorLabel.getText() + "Inventory must be a number.\n");
            updateProduct = false;
        }

        int min = -1;
        try {
            min = Integer.parseInt(updateProductMin.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(errorLabel.getText() + "Min must be a number.\n");
            updateProduct = false;
        } catch (Exception e) {
            System.out.println("min exception: " + e);
            errorLabel.setText(errorLabel.getText() + "Min must be a number.\n");
            updateProduct = false;
        }

        int max = -2;
        try {
            max = Integer.parseInt(updateProductMax.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(errorLabel.getText() + "Max must be a number.\n");
            updateProduct = false;
        } catch (Exception e) {
            System.out.println("max exception: " + e);
            errorLabel.setText(errorLabel.getText() + "Max must be a number.\n");
            updateProduct = false;
        }


        if (min >= max || min == -1 || max == -2) {
            errorLabel.setText(errorLabel.getText() + "Min must be less than Max.\n");
            updateProduct = false;
        }
        if (stock < min || stock > max)  {
            errorLabel.setText(errorLabel.getText() + "Inventory must be between Min and Max.\n");
            updateProduct = false;
        }

        if (updateProduct) {
            System.out.println("Product updated");
            allProducts.removeIf(part -> part.getId() == id);
            Product product = new Product(id, name, price, stock, min, max, null);
            product.setProductParts(FXCollections.observableArrayList(assocParts));
            Controller.getAllProducts(product);
            assocParts.clear();
            System.out.println(product + " modified");
            Stage stage = (Stage) updateProductButton.getScene().getWindow();
            stage.close();
        }




    }

    public void onUpdateProductCancelButton(ActionEvent event) {
        System.out.println("Cancel product add");
        Stage stage = (Stage) updateProductCancelButton.getScene().getWindow();
        stage.close();
    }

}
