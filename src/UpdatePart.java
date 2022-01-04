import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdatePart implements Initializable {
    /**
     * @author Christopher Sequeira
     *
     * LOGICAL ERROR
     * Outsourced and In-House radio buttons do not select based on the selected part from the main form
     *
     * LOGICAL ERROR FIX
     * Intent: Outsourced and In-House subclass objects should determine which radio button is selected on the Update Part form
     * Problem: The radio button is not automatically selected based on the part selected from the main form
     * Fix: Added lines in the if statements depending on type of subclass object passed to Update Part form
     *      partInHouseRadioButton.setSelected(true); // for InHouse subclass objects
     *      partOutsourcedRadioButton.setSelected(true); // for Outsourced subclass objects
     *
     * FUTURE ENHANCEMENT
     *  errorLabel.setText(errorLabel.getText() + "Text.\n");
     *  This line is similar many times in the code. It could be made into a method that takes a String parameter.
     *  public void errorLabel(String string) {
     *      errorLabel.setText(errorLabel.getText() + string + "\n");
     *  }
     *  This could then be called as such
     *  errorLabel("Error text.");
     */

    public TextField partIDText;
    public TextField partNameText;
    public TextField partInventoryText;
    public TextField partPriceText;
    public TextField partMaxText;
    public TextField partMachineIDText;
    public TextField partMinText;
    public RadioButton partInHouseRadioButton;
    public RadioButton partOutsourcedRadioButton;
    public ToggleGroup partSourceToggleGroup;
    public Label machineIDLabel;
    public Button partSave;
    public Button partCancel;
    public Label addPartFormLabel;
    public Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getSelectedPart();
    }

    public void getSelectedPart() {
        int sPartID = 0;
        String sPartName = null;
        double sPartPrice = 0;
        int sPartStock = 0;
        int sPartMin = 0;
        int sPartMax = 0;
        int sPartMachine;
        String sPartCo;

        if (Controller.inHouseForUpdate != null) {
            partInHouseRadioButton.setSelected(true);
            sPartID = Controller.inHouseForUpdate.getId();
            sPartName = Controller.inHouseForUpdate.getName();
            sPartPrice = Controller.inHouseForUpdate.getPrice();
            sPartStock = Controller.inHouseForUpdate.getStock();
            sPartMin = Controller.inHouseForUpdate.getMin();
            sPartMax = Controller.inHouseForUpdate.getMax();
            sPartMachine = Controller.inHouseForUpdate.getMachineID();
            partMachineIDText.setText(String.valueOf(sPartMachine));
            machineIDLabel.setText("Machine ID");
        }
        else if (Controller.outsourcedForUpdate != null) {
            partOutsourcedRadioButton.setSelected(true);
            sPartID = Controller.outsourcedForUpdate.getId();
            sPartName = Controller.outsourcedForUpdate.getName();
            sPartPrice = Controller.outsourcedForUpdate.getPrice();
            sPartStock = Controller.outsourcedForUpdate.getStock();
            sPartMin = Controller.outsourcedForUpdate.getMin();
            sPartMax = Controller.outsourcedForUpdate.getMax();
            sPartCo = Controller.outsourcedForUpdate.getCompanyName();
            partMachineIDText.setText(sPartCo);
            machineIDLabel.setText("Company Name");
        }

        partIDText.setText(String.valueOf(sPartID));
        partNameText.setText(sPartName);
        partInventoryText.setText(String.valueOf(sPartStock));
        partPriceText.setText(String.valueOf(sPartPrice));
        partMinText.setText(String.valueOf(sPartMin));
        partMaxText.setText(String.valueOf(sPartMax));
        ;
    }

    public void onAddPartID() {}

    public String onAddPartName() {
        return partNameText.getText();
    }

    public int onAddPartInventory() {
        return Integer.parseInt(partInventoryText.getText());
    }

    public double onAddPartPrice() {
        return Double.parseDouble(partPriceText.getText());
    }

    public int onAddPartMax() {
        return Integer.parseInt(partMaxText.getText());
    }

    public int onAddPartMin() {
        return Integer.parseInt(partMinText.getText());
    }

    public String onAddMachineID() {
        return partMachineIDText.getText();
    }

    public void onPartInHouseRadioButton() {
        machineIDLabel.setText("Machine ID");
    }

    public void onPartOutsourcedRadioButton() {
        machineIDLabel.setText("Company Name");
    }

    ObservableList<Part> allParts = Controller.getAllParts();
    public void onPartSave() {
        errorLabel.setText("");
        boolean updatePart = true;
        int id = Integer.parseInt(partIDText.getText());

        String name = partNameText.getText();
        if (name == null || partNameText.getText().trim().isEmpty()) {
            errorLabel.setText(errorLabel.getText() + "Enter a name.\n");
            updatePart = false;
        }
        double price = -1;
        try {
            price = Double.parseDouble(partPriceText.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(errorLabel.getText() + "Price must be a number.\n");
            updatePart = false;
        } catch (Exception e) {
            System.out.println("price exception: " + e);
            errorLabel.setText(errorLabel.getText() + "Price must be a number.\n");
            updatePart = false;
        }
        int stock = -1;
        try {
            stock = Integer.parseInt(partInventoryText.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(errorLabel.getText() + "Inventory must be a number.\n");
            updatePart = false;
        } catch (Exception e) {
            System.out.println("inventory exception: " + e);
            errorLabel.setText(errorLabel.getText() + "Inventory must be a number.\n");
            updatePart = false;
        }
        int min = -1;
        try {
            min = Integer.parseInt(partMinText.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(errorLabel.getText() + "Min must be a number.\n");
            updatePart = false;
        } catch (Exception e) {
            System.out.println("min exception: " + e);
            errorLabel.setText(errorLabel.getText() + "Min must be a number.\n");
            updatePart = false;
        }
        int max = -2;
        try {
            max = Integer.parseInt(partMaxText.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText(errorLabel.getText() + "Max must be a number.\n");
            updatePart = false;
        } catch (Exception e) {
            System.out.println("max exception: " + e);
            errorLabel.setText(errorLabel.getText() + "Max must be a number.\n");
            updatePart = false;
        }

        int intSource = -1;
        String stringSource = null;
        if (partInHouseRadioButton.isSelected()) {
            try {
                intSource = Integer.parseInt(partMachineIDText.getText());
                System.out.println("Part made in house");
            } catch (NumberFormatException e) {
                errorLabel.setText(errorLabel.getText() + "Machine ID must be a number.\n");
                updatePart = false;
            }
            catch (Exception e) {
                System.out.println("inhouse error: " + e);
                errorLabel.setText(errorLabel.getText() + "Machine ID must be a number.\n");
            }
        } else if (partOutsourcedRadioButton.isSelected()){
            stringSource = partMachineIDText.getText();
            if (name == null || partMachineIDText.getText().trim().isEmpty()) {
                errorLabel.setText(errorLabel.getText() + "Enter company name.\n");
                updatePart = false;
            }
        }

        if (min >= max) {
            errorLabel.setText(errorLabel.getText() + "Min must be less than Max.\n");
            updatePart = false;
        }
        if (stock < min || stock > max)  {
            errorLabel.setText(errorLabel.getText() + "Inventory must be between Min and Max.\n");
            updatePart = false;
        }

        Part newPart = null;
        if (updatePart) {
            if (partInHouseRadioButton.isSelected()) {
                System.out.println("Part made in house");
                if (Controller.inHouseForUpdate != null) {
                    allParts.removeIf(part -> (part.getId() == Controller.inHouseForUpdate.getId()));
                }
                else if (Controller.outsourcedForUpdate != null) {
                    allParts.removeIf(part -> (part.getId() == Controller.outsourcedForUpdate.getId()));
                }
                newPart = new InHouse(id, name, price, stock, min, max, intSource);
            } else if (partOutsourcedRadioButton.isSelected()){
                System.out.println("Part outsourced");
                if (Controller.inHouseForUpdate != null) {
                    allParts.removeIf(part -> (part.getId() == Controller.inHouseForUpdate.getId()));
                }
                else if (Controller.outsourcedForUpdate != null) {
                    allParts.removeIf(part -> (part.getId() == Controller.outsourcedForUpdate.getId()));
                }                newPart = new Outsourced(id, name, price, stock, min, max, stringSource);
            }

            allParts.add(newPart);
            Stage stage = (Stage) partSave.getScene().getWindow();
            stage.close();
        }
    }

    public void onPartCancel() {
        Stage stage = (Stage) partCancel.getScene().getWindow();
        stage.close();
    }

}
