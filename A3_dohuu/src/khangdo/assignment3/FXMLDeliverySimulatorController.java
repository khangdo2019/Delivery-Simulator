/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangdo.assignment3;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import jchodoriwsky.prog24178.assignments.Item;
import jchodoriwsky.prog24178.assignments.Item.Vehicle;

/**
 * FXML Controller class
 *
 * @author Khang Do 2020
 */
public class FXMLDeliverySimulatorController implements Initializable {

    @FXML
    private Label lblVanItems, lblVanValue, lblCarItems, lblCarValue,
            lblUnicycleItems, lblUnicycleValue, lblErrorMessage, 
            lblNextItem, lblItemName, lblItemValue, lblAnnouncement;

    @FXML
    private Button btnDelivery, btnQuit, btnLoadItem;

    @FXML
    private TextArea txtResult;

    @FXML
    private RadioButton optA, optB, optC;

    @FXML
    private ImageView imgItem;

    private ArrayList<Item> itemList = new ArrayList<>();
    private ArrayList<Item> itemListA = new ArrayList<>();
    private ArrayList<Item> itemListB = new ArrayList<>();
    private ArrayList<Item> itemListC = new ArrayList<>();

    private ArrayList<Vehicle> vehicleList = new ArrayList<>();

    private int counter = 1, vanItem, carItem, unicycleItem, sum;

    private double vanValue, carValue, unicycleValue;

    private String str;

    @FXML
    private void loadItems(ActionEvent event) {
        if(sum < itemList.size()){
        //If users select vehicle A and A is not full
        if (optA.isSelected() && vanItem < vehicleList.get(0).getCapacity()) {
            vanItem++;
            str = String.format("Holding %d items/%d max", vanItem, 
                    vehicleList.get(0).getCapacity());
            lblVanItems.setText(str);
            sum++;
            itemListA.add(itemList.get(counter - 1));
            System.out.println("A. Name of added item: " + 
                    itemList.get(counter - 1).getName());
            vanValue += itemList.get(counter - 1).getValue();
            str = String.format("Contents value $%4.2f", vanValue);
            lblVanValue.setText(str);
        }
        //If users select vehicle B and B is not full
        if (optB.isSelected() && carItem < vehicleList.get(1).getCapacity()) {
            carItem++;
            str = String.format("Holding %d items/%d max", carItem, 
                    vehicleList.get(1).getCapacity());
            lblCarItems.setText(str);
            sum++;
            itemListB.add(itemList.get(counter - 1));
            System.out.println("B. Name of added item: " + 
                    itemList.get(counter - 1).getName());
            carValue += itemList.get(counter - 1).getValue();
            str = String.format("Contents value $%4.2f", carValue);
            lblCarValue.setText(str);
        }
        //If users select vehicle C and C is not full
        if (optC.isSelected() && unicycleItem < 
                vehicleList.get(2).getCapacity()) {
            unicycleItem++;
            str = String.format("Holding %d items/%d max", unicycleItem, 
                    vehicleList.get(2).getCapacity());
            lblUnicycleItems.setText(str);
            sum++;
            System.out.println("C. Name of added item: " + 
                    itemList.get(counter - 1).getName());
            itemListC.add(itemList.get(counter-1));
            unicycleValue += itemList.get(counter - 1).getValue();
            str = String.format("Contents value $%4.2f", unicycleValue);
            lblUnicycleValue.setText(str);
        }
        }
    }

    @FXML
    private void makeDelivery(ActionEvent event) {

        btnDelivery.setOnMouseClicked((MouseEvent e) -> {
            //Check vowel for a/an in the result text
            char[] vowelList = {'a', 'o', 'i', 'e', 'u', 
                'A', 'O', 'I', 'E', 'U'};
            boolean grammarCheck = false;
            
            //If make a delivery for vehicle A
            if (optA.isSelected() && vanItem == vehicleList.get(0)
                    .getCapacity()) {
                vanItem = 0;
                vanValue = 0;
                str = String.format("Holding %d items/%d max", vanItem, 
                        vehicleList.get(0).getCapacity());
                lblVanItems.setText(str);
                str = String.format("Contents value $%4.2f", vanValue);
                lblVanValue.setText(str);
                str = "Vehicle A (Cargo Van) has delivered the "
                        + "following items: ";
                int max = 0;
                //Find the highest value in the list, 
                //print and remove from the array
                for (int i = 0; i < vehicleList.get(0).getCapacity(); i++) {
                    for (int j = 0; j < itemListA.size(); j++) {
                        if (itemListA.get(j).getValue() > 
                                itemListA.get(max).getValue()) {
                            max = j;
                        }
                    }
                    //Check the first letter of the name for a/an
                    for (char check : vowelList) {
                        if (check == itemListA.get(max).getName().charAt(0)) {
                            grammarCheck = true;
                        }
                    }
                    if (grammarCheck) {
                        str += String.format("an %s worth $%4.2f, ", 
                                itemListA.get(max).getName(), 
                                itemListA.get(max).getValue());
                    } else {
                        str += String.format("a %s worth $%4.2f, ", 
                                itemListA.get(max).getName(), 
                                itemListA.get(max).getValue());
                    }
                    itemListA.remove(max);
                    max = 0;
                    grammarCheck = false;
                }
                txtResult.setText(str);
            }
            //If make a delivery for vehicle B
            if (optB.isSelected() && carItem == vehicleList.get(1)
                    .getCapacity()) {
                carItem = 0;
                carValue = 0;
                str = String.format("Holding %d items/%d max", carItem, 
                        vehicleList.get(1).getCapacity());
                lblCarItems.setText(str);
                str = String.format("Contents value $%4.2f", carValue);
                lblCarValue.setText(str);
                str = "Vehicle B (Regular Car) has delivered the "
                        + "following items: ";
                int max = 0;
                //Find the highest value in the list, 
                //print and remove from the array
                for (int i = 0; i < vehicleList.get(1).getCapacity(); i++) {
                    for (int j = 0; j < itemListB.size(); j++) {
                        if (itemListB.get(j).getValue() > 
                                itemListB.get(max).getValue()) {
                            max = j;
                        }
                    }
                    //Check the first letter of the name for a/an
                    for (char check : vowelList) {
                        if (check == itemListB.get(max).getName().charAt(0)) {
                            grammarCheck = true;
                        }
                    }
                    if (grammarCheck) {
                        str += String.format("an %s worth $%4.2f, ", 
                                itemListB.get(max).getName(), 
                                itemListB.get(max).getValue());
                    } else {
                        str += String.format("a %s worth $%4.2f, ", 
                                itemListB.get(max).getName(), 
                                itemListB.get(max).getValue());
                    }
                    itemListB.remove(max);
                    max = 0;
                    grammarCheck = false;
                }
                txtResult.setText(str);
            }
            //If make a delivery for vehicle C
            if (optC.isSelected() && unicycleItem == vehicleList.get(2)
                    .getCapacity()) {
                unicycleItem = 0;
                unicycleValue = 0;
                str = String.format("Holding %d items/%d max", unicycleItem, 
                        vehicleList.get(2).getCapacity());
                lblUnicycleItems.setText(str);
                str = String.format("Contents value $%4.2f", unicycleValue);
                lblUnicycleValue.setText(str);
                str = "Vehicle C (Unicycle) has delivered the following item: ";
                //Check the first letter of the name for a/an
                for (char check : vowelList) {
                    if (check == itemListC.get(0).getName().charAt(0)) {
                        grammarCheck = true;
                    }
                }
                if (grammarCheck) {
                    str += String.format("an %s worth $%4.2f, ", 
                            itemListC.get(0).getName(), 
                            itemListC.get(0).getValue());
                } else {
                    str += String.format("a %s worth $%4.2f, ", 
                            itemListC.get(0).getName(), 
                            itemListC.get(0).getValue());
                }
                itemListC.remove(0);
                grammarCheck = false;
                txtResult.setText(str);
            }
        });
        btnDelivery.setVisible(false);
    }
    
    @FXML
    private void quit(ActionEvent event){
        btnQuit.setOnMouseClicked((MouseEvent e) -> {
           Platform.exit();
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //Initiate 20 items in the item list.
        itemList.add(new Item("Instant Electric Pressure Cooker", 87.50,
                new Image(getClass().getResource("images/Instant_Pot_Duo.jpg")
                        .toExternalForm())));
        itemList.add(new Item("Echo Dot 3rd Gen", 45.00,
                new Image(getClass().getResource("images/echo_dot.jpg")
                        .toExternalForm())));
        itemList.add(new Item("Kindle Paperwhite Waterproof", 140.00, 
                new Image(getClass().getResource("images/kindle_paperwhite.jpg")
                .toExternalForm())));
        itemList.add(new Item("TaoTronics Cool Mist Humidifier", 58.80,
                new Image(getClass().getResource("images/humidifiers.jpg")
                        .toExternalForm())));
        itemList.add(new Item("Refrigerator Water Filter Replacement", 44.60,
                new Image(getClass().getResource
        ("images/refrigerator_water_filter.jpg").toExternalForm())));
        itemList.add(new Item("Keurig K-Compact Coffee Maker", 67.1,
                new Image(getClass().getResource("images/coffee_maker.jpg")
                        .toExternalForm())));
        itemList.add(new Item("LEVOIT Air Purifier", 176.60,
                new Image(getClass().getResource("images/air_purifier.jpg")
                        .toExternalForm())));
        itemList.add(new Item("Magic Bullet Blender", 45.00,
                new Image(getClass().getResource("images/blender.jpg")
                        .toExternalForm())));
        itemList.add(new Item("Twin Mattress Inofia 8 Inch", 165.20,
                new Image(getClass().getResource("images/mattress.jpg")
                        .toExternalForm())));
        itemList.add(new Item("Shark Rocket Vacuum Cleaner", 162.20,
                new Image(getClass().getResource("images/vacuum_cleaner.jpg")
                        .toExternalForm())));
        itemList.add(new Item("KILLABEE Massage Gaming Chair", 192.00,
                new Image(getClass().getResource("images/gaming_chair.jpg")
                        .toExternalForm())));
        itemList.add(new Item("iRobot Braava Jet M6", 540.00,
                new Image(getClass().getResource("images/iRobot.jpg")
                        .toExternalForm())));
        itemList.add(new Item("Ninja Air Fryer", 168.00, new Image(getClass()
                .getResource("images/air_fryer.jpg").toExternalForm())));
        itemList.add(new Item("AmazonBasics Electric Hot Water", 26.10, new 
        Image(getClass().getResource("images/electric_hot_water.jpg")
                .toExternalForm())));
        itemList.add(new Item("Instant Pot Circulator", 71.70, new Image
        (getClass().getResource("images/circulator.png").toExternalForm())));
        itemList.add(new Item("Sunbeam Heating Pad", 30.60, new Image(getClass()
                .getResource("images/heating_pad.jpg").toExternalForm())));
        itemList.add(new Item("Hamilton Beach Slice Toaster", 17.10, new Image
        (getClass().getResource("images/slice_toaster.jpg").toExternalForm())));
        itemList.add(new Item("Hamilton Beach Food Dehydrator", 55.40, new Image
        (getClass().getResource("images/food_dehydrator.jpg")
                .toExternalForm())));
        itemList.add(new Item("Bodum Electric Milk Frother", 32.40, new Image
        (getClass().getResource("images/milk_frother.jpg").toExternalForm())));
        itemList.add(new Item("BEAUTURAL Handheld Steamer for Clothes", 36.40,
                new Image(getClass().getResource("images/handheld_steamer.jpg")
                        .toExternalForm())));

        //Initiate the list of vehicle
        Item item = new Item();
        vehicleList.add(item.new Vehicle("Cargo Van", 8, new Image(getClass()
                .getResource("images/van.jpg").toExternalForm())));
        vehicleList.add(item.new Vehicle("Regular Car", 4, new Image(getClass()
                .getResource("images/car.jpg").toExternalForm())));
        vehicleList.add(item.new Vehicle("Unicycle", 1, new Image(getClass()
                .getResource("images/unicycle.jpg").toExternalForm())));
        
        //Initiate the top announcment
        double totalValue = 0;
        for(Item list: itemList){
            totalValue += list.getValue();
        }
        str = String.format("Today the fleet has delivered a total of %d items "
                + "altogether worth $%4.2f.", itemList.size(), totalValue);
        lblAnnouncement.setText(str);

        //Initiate the first item
        imgItem.setImage(itemList.get(0).getImage());
        lblItemName.setText(itemList.get(0).getName());
        str = String.format("Value: $%4.2f", itemList.get(0).getValue());
        lblItemValue.setText(str);

        //Load the new item when user click the "Load item button"
        btnLoadItem.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Counter: " + counter);
            System.out.println("Sum: " + sum);            
            if (counter <= sum) {
                if (counter < itemList.size()) {
                    imgItem.setImage(itemList.get(counter).getImage());
                    lblItemName.setText(itemList.get(counter).getName());
                    str = String.format("Value: $%4.2f",
                            itemList.get(counter).getValue());
                    lblItemValue.setText(str);
                    counter++;

                } else {
                    //When there is no more item. It shows a blank image and 
                    //a notification that "No more items to deliver"
                    imgItem.setImage(new Image(getClass().
                            getResource("images/blank.png").toExternalForm()));
                    lblNextItem.setText("");
                    lblItemValue.setText("");
                    lblItemName.setText("No more items to deliver");
                    
                    //Set visible for Quit button
                    btnQuit.setVisible(true);
                }
            } else {
                if (vanItem == 8 || carItem == 4 || unicycleItem == 1) {
                    lblErrorMessage.setText("Your selected vehicle is full. "
                            + "Please make a delivery or choose "
                            + "another vehicle");
                } else {
                    lblErrorMessage.setText("");
                }
            }
            if (sum == itemList.size()){
                //When there is no more item. It shows a blank image and 
                    //a notification that "No more items to deliver"
                    imgItem.setImage(new Image(getClass().
                            getResource("images/blank.png").toExternalForm()));
                    lblNextItem.setText("");
                    lblItemValue.setText("");
                    lblItemName.setText("No more items to deliver");
                    
                    //Set visible for Quit button
                    btnQuit.setVisible(true);
            }
            //Set visible for Delivery button
            boolean deliveryButton = vanItem == vehicleList.get(0).getCapacity()
                    || carItem == vehicleList.get(1).getCapacity()
                    || unicycleItem == vehicleList.get(2).getCapacity();
            if (deliveryButton) {
                btnDelivery.setVisible(true);
            } else {
                btnDelivery.setVisible(false);
            }
        });
    }
}
