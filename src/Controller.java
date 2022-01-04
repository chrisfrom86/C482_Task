import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Controller implements Initializable {
    /**
     * @author Christopher Sequeira
     *
     * LOGICAL ERROR
     *  getPartsTable(); // defined the TableView columns, but did not populate data
     *  getProductTable(); // defined the TableView columns, but did not populate data
     *
     * RUNTIME ERROR FIX
     * Intent: when getPartsTable() or getProductsTable() is called, populate the current allParts or allProducts ObservableList
     * Problem: TableViews load correctly but no data is added after adding parts and products
     * Fix: productTable.setItems(allProducts); //added to getProductsTable();
     *      partsTable.setItems(allParts); //added to getPartsTable();
     *
     * FUTURE ENHANCEMENT
     * combine the add/modify/delete buttons. I'd have to make sure that there is only one item selected at a time,
     *      or I could allow selection/deselection of items, and when modify/delete button is clicked, all corresponding stages/scenes would pop up
     */

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    public TableView<Part> partsTable;
    public TableColumn partIDColumn;
    public TableColumn partNameColumn;
    public TableColumn partInventoryColumn;
    public TableColumn partPriceColumn;
    public TableView<Product> productTable;
    public TableColumn productIDColumn;
    public TableColumn productNameColumn;
    public TableColumn productInventoryColumn;
    public TableColumn productPriceColumn;
    public Button deletePart;
    public Button updatePart;
    public Button addPart;
    public Button deleteProduct;
    public Button updateProduct;
    public Button addProduct;
    public TextField partSearch;
    public TextField productSearch;
    public static Product productForUpdate;
    public static Outsourced outsourcedForUpdate;
    public static InHouse inHouseForUpdate;
    public Button exitButton;
    public Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");

        getPartsTable();
        getProductTable();

    }

    public void addPart(ActionEvent event) throws IOException {
        System.out.println("onAddPart clicked");
        errorLabel.setText("");
        Parent root = FXMLLoader.load(getClass().getResource("/AddPart.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Part");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addProduct() throws IOException {
        System.out.println("addProduct clicked");
        errorLabel.setText("");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AddProduct.fxml")));
        Stage stage = new Stage();
        stage.setTitle("Add Product");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void lookupPart() {
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
        ObservableList<Part> allParts = getAllParts();

        for (Part part : allParts) {
            if (part.getId() == partialName) {
                partsTable.getSelectionModel().select(part);
            }
        }
        return namedParts;
    }

    public ObservableList<Part> lookupPart(String inputName) {

        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = getAllParts();
        String partialName = inputName.toLowerCase(Locale.ROOT);

        for (Part part : allParts) {
            if (part.getName().toLowerCase(Locale.ROOT).contains(partialName))
                namedParts.add(part);

            partsTable.setItems(namedParts);
            partSearch.setText("");
        }
        return namedParts;
    }

    public void lookupProduct() {
        try {
            Integer.parseInt(productSearch.getText());
            System.out.println(productSearch.getText() + " is a number");
            lookupProduct(Integer.parseInt(productSearch.getText()));
        } catch (NumberFormatException e) {
            System.out.println(productSearch.getText() + " is not a number");
            lookupProduct(productSearch.getText());
        }
    }

    public ObservableList<Product> lookupProduct(int partialName) {

        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = getAllProducts(null);

        for (Product product : allProducts) {
            if (product.getId() == partialName) {
                productTable.getSelectionModel().select(product);
            }
        }
        return namedProducts;
    }

    public ObservableList<Product> lookupProduct(String partialName) {

        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = getAllProducts(null);

        for (Product product : allProducts) {
            if (product.getName().contains(partialName))
                namedProducts.add(product);

            productTable.setItems(namedProducts);
            productSearch.setText("");
        }
        return namedProducts;
    }

    public void updatePart() throws IOException {
        System.out.println("updatePart clicked");
        errorLabel.setText("");
        if (partsTable.getSelectionModel().getSelectedItem() instanceof InHouse) {
            inHouseForUpdate = (InHouse) partsTable.getSelectionModel().getSelectedItem();
            outsourcedForUpdate = null;
        } else if (partsTable.getSelectionModel().getSelectedItem() instanceof Outsourced) {
            outsourcedForUpdate = (Outsourced) partsTable.getSelectionModel().getSelectedItem();
            inHouseForUpdate = null;
        }
        if (!(inHouseForUpdate == null && outsourcedForUpdate == null)) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/UpdatePart.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Update Part");
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            errorLabel.setText("Select a part to update.");
        }
    }

    public void updateProduct() throws IOException {
        System.out.println("updateProduct clicked");
        errorLabel.setText("");
        productForUpdate = productTable.getSelectionModel().getSelectedItem();
        if (!(productForUpdate == null)) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/UpdateProduct.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Update Product");
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            errorLabel.setText("Select a product to update.");
        }
    }

    public void deletePart() {
        System.out.println("deletePart clicked");
        errorLabel.setText("");
        if (partsTable.getSelectionModel().getSelectedItem() instanceof InHouse) {
            inHouseForUpdate = (InHouse) partsTable.getSelectionModel().getSelectedItem();
            outsourcedForUpdate = null;
        } else if (partsTable.getSelectionModel().getSelectedItem() instanceof Outsourced) {
            outsourcedForUpdate = (Outsourced) partsTable.getSelectionModel().getSelectedItem();
            inHouseForUpdate = null;
        }

        boolean deletePart = true;
        if (!(inHouseForUpdate == null && outsourcedForUpdate == null)) {
            for (Product product : allProducts)
                if (product.getProductParts().contains(inHouseForUpdate) || product.getProductParts().contains(outsourcedForUpdate)) {
                    errorLabel.setText("Error: Part is associated with Product ID " + product.getId());
                    System.out.println();
                    deletePart = false;
                }
        }  else {
            errorLabel.setText("Select a part to delete.");
        }

        if (deletePart) {
            ConfirmBox.display("Delete Part", "Delete selected part?");
            if (ConfirmBox.answer) {
                allParts.remove(partsTable.getSelectionModel().getSelectedItem());
                System.out.println(partsTable.getSelectionModel().getSelectedItem() + " removed");
                getPartsTable();
            }
        }
    }

    public void onDeleteProduct(ActionEvent event) {
        System.out.println("deleteProduct clicked");
        errorLabel.setText("");
        if (!(productTable.getSelectionModel().getSelectedItem() == null)) {
            if ((productTable.getSelectionModel().getSelectedItem().getProductParts() == null) || productTable.getSelectionModel().getSelectedItem().getProductParts().isEmpty()) {
                ConfirmBox.display("Delete Part", "Delete selected part?");
                if (ConfirmBox.answer) {
                    productTable.getSelectionModel().getSelectedItem().setProductParts(null);
                    allProducts.remove(productTable.getSelectionModel().getSelectedItem());
                    System.out.println(productTable.getSelectionModel().getSelectedItem() + " removed");
                    getProductTable();
                }
            } else {
                errorLabel.setText("Remove parts from product before deleting.\n");
                errorLabel.setText(errorLabel.getText() + productTable.getSelectionModel().getSelectedItem().getProductParts());
            }
        } else {
            errorLabel.setText("Select a product to delete.");
        }
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(Product product) {
        if (product != null) {
            allProducts.add(product);
            System.out.println(product.getProductParts());
        }
        return allProducts;
    }

    public TableView<Part> getPartsTable() {
        partsTable.setItems(allParts);
            partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
            partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        return partsTable;
    }

    public TableView<Product> getProductTable() {
        productTable.setItems(allProducts);
            productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
            productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        return productTable;
    }

    public void onExitButton() {
        System.out.println("onExitButton clicked");
        ConfirmBox.display("Exit Program", "Are you sure you want to exit?");
        if (ConfirmBox.answer) {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        }

    }


}
