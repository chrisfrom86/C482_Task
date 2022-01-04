import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddPart {
    /**
     * @author Christopher Sequeira
     *
     * RUNTIME ERROR
     *  Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
     * 	at javafx.fxml/javafx.fxml.FXMLLoader$MethodHandler.invoke(FXMLLoader.java:1857)
     * 	at javafx.fxml/javafx.fxml.FXMLLoader$ControllerMethodEventHandler.handle(FXMLLoader.java:1724)
     * 	at javafx.base/com.sun.javafx.event.CompositeEventHandler.dispatchBubblingEvent(CompositeEventHandler.java:86)
     * 	at javafx.base/com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:234)
     * 	at javafx.base/com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:191)
     * 	at javafx.base/com.sun.javafx.event.CompositeEventDispatcher.dispatchBubblingEvent(CompositeEventDispatcher.java:59)
     * 	at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:58)
     * 	at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
     * 	at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
     * 	at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
     * 	at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
     * 	at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
     * 	at javafx.base/com.sun.javafx.event.EventUtil.fireEventImpl(EventUtil.java:74)
     * 	at javafx.base/com.sun.javafx.event.EventUtil.fireEvent(EventUtil.java:49)
     * 	at javafx.base/javafx.event.Event.fireEvent(Event.java:198)
     * 	at javafx.graphics/javafx.scene.Node.fireEvent(Node.java:8792)
     * 	at javafx.controls/javafx.scene.control.Button.fire(Button.java:203)
     * 	at javafx.controls/com.sun.javafx.scene.control.behavior.ButtonBehavior.mouseReleased(ButtonBehavior.java:208)
     * 	at javafx.controls/com.sun.javafx.scene.control.inputmap.InputMap.handle(InputMap.java:274)
     * 	at javafx.base/com.sun.javafx.event.CompositeEventHandler$NormalEventHandlerRecord.handleBubblingEvent(CompositeEventHandler.java:247)
     * 	at javafx.base/com.sun.javafx.event.CompositeEventHandler.dispatchBubblingEvent(CompositeEventHandler.java:80)
     * 	at javafx.base/com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:234)
     * 	at javafx.base/com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:191)
     * 	at javafx.base/com.sun.javafx.event.CompositeEventDispatcher.dispatchBubblingEvent(CompositeEventDispatcher.java:59)
     * 	at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:58)
     * 	at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
     * 	at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
     * 	at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
     * 	at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
     * 	at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
     * 	at javafx.base/com.sun.javafx.event.EventUtil.fireEventImpl(EventUtil.java:74)
     * 	at javafx.base/com.sun.javafx.event.EventUtil.fireEvent(EventUtil.java:54)
     * 	at javafx.base/javafx.event.Event.fireEvent(Event.java:198)
     * 	at javafx.graphics/javafx.scene.Scene$MouseHandler.process(Scene.java:3897)
     * 	at javafx.graphics/javafx.scene.Scene.processMouseEvent(Scene.java:1878)
     * 	at javafx.graphics/javafx.scene.Scene$ScenePeerListener.mouseEvent(Scene.java:2623)
     * 	at javafx.graphics/com.sun.javafx.tk.quantum.GlassViewEventHandler$MouseEventNotification.run(GlassViewEventHandler.java:411)
     * 	at javafx.graphics/com.sun.javafx.tk.quantum.GlassViewEventHandler$MouseEventNotification.run(GlassViewEventHandler.java:301)
     * 	at java.base/java.security.AccessController.doPrivileged(Native Method)
     * 	at javafx.graphics/com.sun.javafx.tk.quantum.GlassViewEventHandler.lambda$handleMouseEvent$2(GlassViewEventHandler.java:450)
     * 	at javafx.graphics/com.sun.javafx.tk.quantum.QuantumToolkit.runWithoutRenderLock(QuantumToolkit.java:424)
     * 	at javafx.graphics/com.sun.javafx.tk.quantum.GlassViewEventHandler.handleMouseEvent(GlassViewEventHandler.java:449)
     * 	at javafx.graphics/com.sun.glass.ui.View.handleMouseEvent(View.java:557)
     * 	at javafx.graphics/com.sun.glass.ui.View.notifyMouse(View.java:943)
     * 	at javafx.graphics/com.sun.glass.ui.win.WinApplication._runLoop(Native Method)
     * 	at javafx.graphics/com.sun.glass.ui.win.WinApplication.lambda$runLoop$3(WinApplication.java:184)
     * 	at java.base/java.lang.Thread.run(Thread.java:834)
     * Caused by: java.lang.reflect.InvocationTargetException
     * 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * 	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
     * 	at com.sun.javafx.reflect.Trampoline.invoke(MethodUtil.java:77)
     * 	at jdk.internal.reflect.GeneratedMethodAccessor2.invoke(Unknown Source)
     * 	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
     * 	at javafx.base/com.sun.javafx.reflect.MethodUtil.invoke(MethodUtil.java:275)
     * 	at javafx.fxml/com.sun.javafx.fxml.MethodHelper.invoke(MethodHelper.java:84)
     * 	at javafx.fxml/javafx.fxml.FXMLLoader$MethodHandler.invoke(FXMLLoader.java:1854)
     * 	... 46 more
     * Caused by: java.lang.NumberFormatException: For input string: "f"
     * 	at java.base/jdk.internal.math.FloatingDecimal.readJavaFormatString(FloatingDecimal.java:2054)
     * 	at java.base/jdk.internal.math.FloatingDecimal.parseDouble(FloatingDecimal.java:110)
     * 	at java.base/java.lang.Double.parseDouble(Double.java:543)
     * 	at AddPart.onPartSave(AddPart.java:91)
     * 	... 57 more
     * onExitButton clicked
     *
     * Process finished with exit code 0
     *
     * RUNTIME ERROR FIX
     * Intent: price should only be double as input and output text to prompt the user if non-double is entered
     * Problem: double price = Double.parseDouble(partPriceText.getText()); //does not catch non-double input, just outputs exception
     * Fix: added a try/catch statement to the variable initialization to output text to the errorLabel Label in case of program exception
     *
     * FUTURE ENHANCEMENT
     * the cancel button does is very similar code in every class that it's used, could create a single method and call it
     *
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

    public void onAddPartID() {

        //return x + 1;
    }

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

        int x = 0;
        for (Part part : allParts) {
            if (part.getId() > x)
                x = part.getId();
        } x += 1;
            int id = x;

        errorLabel.setText("");
        boolean createPart = true;
        String name = partNameText.getText();
        if (name == null || partNameText.getText().trim().isEmpty()) {
            errorLabel.setText(errorLabel.getText() + "Enter a name.\n");
            createPart = false;
        }
        double price = -1;
            try {
                price = Double.parseDouble(partPriceText.getText());
            } catch (NumberFormatException e) {
                errorLabel.setText(errorLabel.getText() + "Price must be a number.\n");
                createPart = false;
            } catch (Exception e) {
                System.out.println("price exception: " + e);
                errorLabel.setText(errorLabel.getText() + "Price must be a number.\n");
                createPart = false;
            }
        int stock = -1;
            try {
                stock = Integer.parseInt(partInventoryText.getText());
            } catch (NumberFormatException e) {
                errorLabel.setText(errorLabel.getText() + "Inventory must be a number.\n");
                createPart = false;
            } catch (Exception e) {
                System.out.println("inventory exception: " + e);
                errorLabel.setText(errorLabel.getText() + "Inventory must be a number.\n");
                createPart = false;
            }
        int min = -1;
            try {
                min = Integer.parseInt(partMinText.getText());
            } catch (NumberFormatException e) {
                errorLabel.setText(errorLabel.getText() + "Min must be a number.\n");
                createPart = false;
            } catch (Exception e) {
                System.out.println("min exception: " + e);
                errorLabel.setText(errorLabel.getText() + "Min must be a number.\n");
                createPart = false;
            }
        int max = -2;
            try {
                max = Integer.parseInt(partMaxText.getText());
            } catch (NumberFormatException e) {
                errorLabel.setText(errorLabel.getText() + "Max must be a number.\n");
                createPart = false;
            } catch (Exception e) {
                System.out.println("max exception: " + e);
                errorLabel.setText(errorLabel.getText() + "Max must be a number.\n");
                createPart = false;
            }

        int intSource = -1;
        String stringSource = null;
        if (partInHouseRadioButton.isSelected()) {
            try {
                intSource = Integer.parseInt(partMachineIDText.getText());
                System.out.println("Part made in house");
            } catch (NumberFormatException e) {
                errorLabel.setText(errorLabel.getText() + "Machine ID must be a number.\n");
                createPart = false;
            }
            catch (Exception e) {
                System.out.println("inhouse error: " + e);
                errorLabel.setText(errorLabel.getText() + "Machine ID must be a number.\n");
            }
        } else if (partOutsourcedRadioButton.isSelected()){
            stringSource = partMachineIDText.getText();
            if (name == null || partMachineIDText.getText().trim().isEmpty()) {
                errorLabel.setText(errorLabel.getText() + "Enter company name.\n");
                createPart = false;
            }
        }

        if (min >= max) {
            errorLabel.setText(errorLabel.getText() + "Min must be less than Max.\n");
            createPart = false;
        }
        if (stock < min || stock > max)  {
            errorLabel.setText(errorLabel.getText() + "Inventory must be between Min and Max.\n");
            createPart = false;
        }

        Part newPart = null;
        if (createPart) {
            if (partInHouseRadioButton.isSelected()) {
                System.out.println("Part made in house");
                newPart = new InHouse(id, name, price, stock, min, max, intSource);
            } else if (partOutsourcedRadioButton.isSelected()){
                System.out.println("Part outsourced");
                newPart = new Outsourced(id, name, price, stock, min, max, stringSource);
            }
            allParts.add(newPart);
            Stage stage = (Stage) partSave.getScene().getWindow();
            stage.close();
            Controller.getAllParts();
        }


    }

    public void onPartCancel() {
        Stage stage = (Stage) partCancel.getScene().getWindow();
        stage.close();
    }
}
