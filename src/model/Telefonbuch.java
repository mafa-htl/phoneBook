/**class Telefonbuch
 * @author Matteo Falkenberg
 * @version 1.1, 18.02.2021
 */

package model;

import java.util.List;

public class Telefonbuch {

    private List<Person> persons;

    public Telefonbuch(){

        for(int i = 0; i < 20; i++){

        }

    }


    public void load(){

    }


    public void save(){

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
