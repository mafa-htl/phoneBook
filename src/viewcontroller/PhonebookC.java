/**class Controller
 * @author Matteo Falkenberg
 * @version 1.6, 04.03.2021
 */

package viewcontroller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Person;
import model.Telefonbuch;

public class PhonebookC {

    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField phoneField;
    @FXML private Label posLabel;
    @FXML private Label errLabel;

    private Telefonbuch phoneBook = new Telefonbuch();
    private int position = 1;


    @FXML
    public void initialize() {
        loadFromCSV();
        updatePage();
    }


    @FXML
    public void close(){
        saveChanges();
        saveToCSV();
        System.out.println("Stage is closing");
    }


    private void updatePage(){
        posLabel.setText(position + "/" + phoneBook.getSize());

        Person person = phoneBook.getPerson(position);
        nameField.setText(person.getName());
        addressField.setText(person.getAddress());
        phoneField.setText(person.getTelNum());
    }


    private void saveChanges(){
        String name = nameField.getText();
        String address = addressField.getText();
        String telNum = phoneField.getText();

        if (checkValEmpty(name, address, telNum) == true)
            phoneBook.changePerson(position, name, address, telNum);
    }


    private boolean checkValEmpty(String name, String address, String telNum){
        if (name.equals("") || name == null) {
            errLabel.setText("Bitte geben Sie für Name einen Wert ein!");
            return false;
        }
        else if (address.equals("") || address == null) {
            errLabel.setText("Bitte geben Sie für Adresse einen Wert ein!");
            return false;
        }
        else if (telNum.equals("") || telNum == null) {
            errLabel.setText("Bitte geben Sie für Telefonnummer einen Wert ein!");
            return false;
        }

        return true;
    }


    @FXML
    private void nextPerson(){
        saveChanges();
        position++;

        if(position > phoneBook.getSize())
            position = 1;

        updatePage();
    }

    @FXML
    private void lastPerson(){
        saveChanges();
        position--;

        if(position < 1)
            position = phoneBook.getSize();

        updatePage();
    }


    @FXML
    private void addPerson(){
        saveChanges();
        phoneBook.addEmpty();
        position = phoneBook.getSize();
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


    private void saveToCSV(){
        phoneBook.saveCSV();
    }


}
