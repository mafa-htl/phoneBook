/**class Telefonbuch
 * @author Matteo Falkenberg
 * @version 1.2, 18.02.2021
 */

package model;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Telefonbuch {

    private List<Person> persons;

    public Telefonbuch(){

        for(int i = 0; i < 20; i++){

        }

    }


    public void load(){
        try {
            Scanner sc = new Scanner(new File("addressBook.csv"));
            persons.clear();

            while(sc.hasNext()){
                String[] data = sc.next().split(";");
                add(data[0], data[1], data[2]);
            }
            sc.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void save(){
        try {
            FileWriter fw = new FileWriter("addressBook.csv");
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i < persons.size(); i++){
                bw.write(persons.get(i).toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void add(String name, String address, String telNum){
        Person pers = new Person();
        pers.setName(name);
        pers.setAddress(address);
        pers.setTelNum(telNum);

        persons.add(pers);
    }


    public void delete(int pos){
        persons.remove(pos-1);  //starts at 0
    }

}
