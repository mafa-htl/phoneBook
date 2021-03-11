/**class Telefonbuch
 * @author Matteo Falkenberg
 * @version 1.8, 11.03.2021
 */

package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Telefonbuch {

    private List<Person> persons = new ArrayList<Person>();

    public Telefonbuch(){
        /*
        addDistinct("Hans Meier", "Wels 4600", "+43 676 7195417");
        addDistinct("Fritz Ober", "Linz 4020", "+43 650 8191028");
        addDistinct("Petra Aller", "Vöcklabruck 4840", "+43 664 0620367");
        addDistinct("Marie Weger", "Attersee 4864", "+43 676 9018757");
        */
    }


    public Person getPerson(int index){
        return persons.get(index - 1);  //starts at 0
    }


    public int getSize(){
        return persons.size();
    }


    public void load(){
        try {
            Scanner sc = new Scanner(new File("addressBook.csv"));
            persons.clear();

            String line;
            while(sc.hasNextLine() && (line = sc.nextLine()) != null){
                String[] data = line.split(";");
                if(data.length == 3) {
                    if (!data[0].isEmpty() && !data[1].isEmpty() && !data[2].isEmpty() && data[0].matches("^\\p{Lu}\\p{L}*(?:[\\s-]\\p{L}*)*$") && data[1].matches("^\\p{Lu}\\p{L}*(?:[\\s-]\\p{L}*)*,?\\s\\d{3,10}$") && data[2].matches("^\\+\\d{1,3}/\\d{1,3}/\\d{7}$")) {
                        addDistinct(data[0], data[1], data[2].replaceAll("/", " "));
                    }
                }
            }
            sc.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveCSV(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("addressBook.csv"))) {

            for(int i = 0; i < persons.size(); i++){
                bw.write(persons.get(i).toCSVString());
                bw.newLine();
            }
            System.out.println("Save successful!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void changePerson(int pos, String name, String address, String telNum){
        persons.get(pos-1).setName(name);
        persons.get(pos-1).setAddress(address);
        persons.get(pos-1).setTelNum(telNum);
    }


    public void addDistinct(String name, String address, String telNum){
        Person pers = new Person();
        pers.setName(name);
        pers.setAddress(address);
        pers.setTelNum(telNum);

        persons.add(pers);
    }

    public void addEmpty(){
        Person pers = new Person();
        pers.setName("");
        pers.setAddress("");
        pers.setTelNum("");
        persons.add(pers);
    }


    public void delete(int pos){
        persons.remove(pos-1);  //starts at 0
    }

}
