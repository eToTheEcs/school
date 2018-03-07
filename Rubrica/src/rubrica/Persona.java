/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.util.Map;
import java.util.Objects;
import javax.swing.JTextArea;

/**
 *
 * @author Nicolas Benatti
 */
public class Persona implements Comparable<Persona> {
 
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
    
    public Persona(Persona other) {
        
        this.lastname = other.lastname;
        this.firstname = other.firstname;
        this.address = other.address;
        this.city = other.city;
        this.state = other.state;
        this.zip = other.zip;
        this.phone = other.phone;
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
    
    @Override
    public String toString() {
        
        return "nome: " + this.firstname + "\ncognome: " + this.lastname + 
                "\nindirzzo: " + this.address + "\ncity: " + this.city + 
                "\ncountry: " + this.state + "\nzip: " + this.zip + 
                "\nphone number: " + this.phone + "\n";
    }
    
    public Persona compareDiff(Persona other, Boolean[] hasNameChanged) {
        
        Persona res = new Persona(this);
                
        if(other.firstname.compareTo("") != 0 && this.firstname.compareTo(other.firstname) != 0) {
            hasNameChanged[1] = true;
            res.firstname = other.firstname;
        }
        if(other.lastname.compareTo("") != 0 && this.lastname.compareTo(other.lastname) != 0) {
            hasNameChanged[1] = true;
            res.lastname = other.lastname;
        }
        if(other.address.compareTo("") != 0 && this.address.compareTo(other.address) != 0) {
            
            res.address = other.address;
        }
        if(other.city.compareTo("") != 0 && this.city.compareTo(other.city) != 0) {
        
            res.city = other.city;
        }
        if(other.state.compareTo("") != 0 && this.state.compareTo(other.state) != 0) {
            
            res.state = other.state;
        }
        if(other.zip.compareTo("") != 0 && this.zip.compareTo(other.zip) != 0) {
            
            res.zip = other.zip;
        } 
        if(other.phone.compareTo("") != 0 && this.phone.compareTo(other.phone) != 0) {
            
            res.phone = other.phone;
        }
        
        return res;
    }
    
    // fill a <String, JLabel> Map with Person data
    
    public void fillShowCaseArray(Map<String, JTextArea> sca) {
        
        sca.get("Firstname").setText(firstname);
        sca.get("Lastname").setText(lastname);
        sca.get("Address").setText(address);
        sca.get("City").setText(city);
        sca.get("State").setText(state);
        sca.get("Zip code").setText(zip);
        sca.get("Phone").setText(phone);
    }

    @Override
    public int compareTo(Persona t) {
        
        return this.zip.compareTo(t.getZip());
    }

    @Override
    public boolean equals(Object o) {
        
        if( !(o instanceof Persona) )
            return false;
        
        Persona other = (Persona) o;
        
        return this.zip.equals(other.getZip());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.zip);
        return hash;
    }
}
