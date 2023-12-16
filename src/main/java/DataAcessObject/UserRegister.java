
package DataAcessObject;

import java.util.Random;

/**
 *
 * @author deiv
 */
class UserRegister {
    //Objeto Random para gerar o User ID (UID)
    Random randomUID = new Random();
    
    //variaveis de dados para usuarios
    private String uid, name, email, address, phone;
    
    //construtor para usuarios
    UserRegister(String name, String email, String address, int phone) {
        int intUID = randomUID.nextInt(10_000, 100_000);
        
        uid = String.format("UID%d", intUID);
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = String.format("%d", phone);
    };
    
    //getters...
    String getUID() { return uid; };
    String getName() { return name; };
    String getEmail() { return email; };
    String getAddress() { return address; };
    String getPhone() { return phone; };
    
    //setters...
    void setUID(int newUID) {
        uid = String.format("UID%d", newUID);
    };
    
    void setName(String newName) {
        name = newName;
    };
    
    void setEmail(String newEmail) {
        email = newEmail;
    };
    
    void setAddress(String newAddress) {
        address = newAddress;
    };
    
    void setPhone(int newPhone) {
        phone = String.format("%d", newPhone);
    };
};