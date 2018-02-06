/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

/**
 *
 * @author Nicolas Benatti
 */
public class Persona {
 
    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    
    // il "form" manderà i dati per costruire le nuove persone

    public Persona(String firstname, String lastname, String address, String city, String state, String zip, String phone) {
        
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setFirstname(String fistname) {
        this.firstname = fistname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String toString() {
        
        return "nome: " + this.firstname + "\ncognome: " + this.lastname + 
                "\nindirzzo: " + this.address + "\ncity: " + this.city + 
                "\ncountry: " + this.state + "\nzip: " + this.zip + 
                "\nphone number: " + this.phone + "\n";
    }
}
