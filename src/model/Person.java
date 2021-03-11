/**class Person
 * @author Matteo Falkenberg
 * @version 1.8, 11.03.2021
 */

package model;

public class Person {

    private String name;
    private String address;
    private String telNum;

    public Person(){

    }


    public String toCSVString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.name + ";");
        sb.append(this.address + ";");
        sb.append(this.telNum.replaceAll("\\s", "/")); //replace all whitespaces with /

        return sb.toString();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

}
