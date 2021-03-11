/**class PhonebookC
 * @author Matteo Falkenberg
 * @version 1.8, 11.03.2021
 */

package viewcontroller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import model.Person;
import model.Telefonbuch;

public class PhonebookC {

    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField phoneField;
    @FXML private Label posLabel;
    @FXML private Label errLabel;
    @FXML private Label exLabel;

    private Telefonbuch phoneBook = new Telefonbuch();
    private int position = 1;


    @FXML
    public void initialize() {
        loadFromCSV();
        main.Main.getPrimaryStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                phoneBook.saveCSV();
            }
        });
        updatePage();
    }


    private void updatePage(){
        posLabel.setText(position + "/" + phoneBook.getSize());

        Person person = phoneBook.getPerson(position);
        nameField.setText(person.getName());
        addressField.setText(person.getAddress());
        phoneField.setText(person.getTelNum());
    }


    private boolean saveChanges(){
        String name = nameField.getText();
        String address = addressField.getText();
        String telNum = phoneField.getText();
        phoneBook.changePerson(position, name, address, telNum);

        if (checkVal(name, address, telNum) == true)
            return true;
        return false;
    }


    private boolean checkVal(String name, String address, String telNum){
        if (!name.matches("^\\p{Lu}\\p{L}*(?:[\\s-]\\p{L}*)*$")) {
            errLabel.setText("Please enter a valid value for Name!");
            exLabel.setText("");
            return false;
        }
        else if (!address.matches("^\\p{Lu}\\p{L}*(?:[\\s-]\\p{L}*)*,?\\s\\d{3,10}$")) {
            errLabel.setText("Please enter a valid value for Address!");
            exLabel.setText("\"placeName, postcode\" or \"placeName postcode\"");
            return false;
        }
        else if (!telNum.matches("^\\+\\d{1,3}\\s\\d{1,3}\\s\\d{7}$")) {
            errLabel.setText("Please enter a valid value for Phone!");
            exLabel.setText("+123 123 1234567");
            return false;
        }

        errLabel.setText("");
        exLabel.setText("");
        return true;
    }


    @FXML
    private void nextPerson(){
        if(saveChanges() == true) {
            position++;

            if (position > phoneBook.getSize())
                position = 1;
        }
        updatePage();
    }

    @FXML
    private void lastPerson(){
        if(saveChanges() == true) {
            position--;

            if (position < 1)
                position = phoneBook.getSize();
        }
        updatePage();
    }


    @FXML
    private void addPerson(){
        if(saveChanges() == true) {
            phoneBook.addEmpty();
            position = phoneBook.getSize();
        }
        updatePage();
    }


    @FXML
    private void deletePerson(){
        phoneBook.delete(position);
        if(phoneBook.getSize() == 0) {    //if list empty
            phoneBook.addEmpty();
            position = 1;
        }
        else if(position > phoneBook.getSize())
            position = phoneBook.getSize();
        updatePage();
    }


    private void loadFromCSV(){
        phoneBook.load();
        position = 1;

        if(phoneBook.getSize() == 0) {    //if list empty
            phoneBook.addEmpty();
        }

        updatePage();
    }

}
