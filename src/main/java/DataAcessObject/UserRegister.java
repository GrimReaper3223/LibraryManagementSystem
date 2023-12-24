
package DataAcessObject;

import java.util.Random;

/**
 *
 * @author deiv
 */
class UserRegister {
    //Objeto Random para gerar o User ID (UID)
    Random randomUID = new Random();
    
    //variavel de classe que incrementa + 1 para cada objeto user instanciado da classe. essa variavel sera usada para pesquisar o indice do objeto
    private static int numberOfUserObject = 0;
    
    //variaveis de dados para usuarios (REGISTER_INDEX e uma variavel de instancia e sera inicializada com o valor apropriado assim que um objeto for instanciado)
    private final String REGISTER_INDEX;
    private String uid, name, email, address, phone;

    
    //construtor para usuarios
    UserRegister(String name, String email, String address, int phone) {
        int intUID = randomUID.nextInt(10_000, 100_000);
        
        this.REGISTER_INDEX = String.format("%d", numberOfUserObject);
        this.uid = String.format("UID%d", intUID);
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = String.format("%d", phone);
        
        if(!verifyIfHasSpaceToRegisterUser()) {
            ++numberOfUserObject;
        }
    };
    
    //getters...
    static int getQuantityOfUserInstances() { return numberOfUserObject; };     //usado para retornar a quantidade maxima de usuarios registrados
    String getRegisterIndex() { return this.REGISTER_INDEX; };               //retorna o indice que essa instancia de usuario esta registrado
    String getUID() { return this.uid; };
    String getName() { return this.name; };
    String getEmail() { return this.email; };
    String getAddress() { return this.address; };
    String getPhone() { return this.phone; };
    
    
    //setters...
    static boolean verifyIfHasSpaceToRegisterUser() {
        boolean verification = numberOfUserObject == Database.USER.length - 1;
        return verification;
    };  //verifica se existe espaco na array de usuario para iterar + 1 para armazenar o proximo objeto e para verificar se o espaco esta vazio
    
    static void decreaseUserRegisterIndex() {
        --numberOfUserObject;
    };  //esse metodo so sera chamado caso o metodo de remocao de usuario da estrutura de dados seja chamado//esse metodo so sera chamado caso o metodo de remocao de usuario da estrutura de dados seja chamado
    
    void setUID(int uid) {
        this.uid = String.format("UID%d", uid);
    };  //decidir sobre permanencia da troca de id (pendente)
    
    void setName(String name) {
        this.name = name;
    };
    
    void setEmail(String email) {
        this.email = email;
    };
    
    void setAddress(String address) {
        this.address = address;
    };
    
    void setPhone(int phone) {
        this.phone = String.format("%d", phone);
    };
    
};