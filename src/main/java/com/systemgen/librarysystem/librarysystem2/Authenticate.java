package com.systemgen.librarysystem.librarysystem2;

import DataComponents.*;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Interface de autenticacao de usuario
 * 
 * @author deiv
 */
public interface Authenticate {
    
    // metodo de autenticacao da interface
    // retorna uma instancia box de um tipo desconhecido que implementa a interface authenticate
    default Box<? extends Authenticate> authenticate(String email, char[] password) {
        String stringPass = String.valueOf(password);
        
        //obtem o iterador das listas de usuario/admin
        Iterator<UserRegisterComponent> userIterator = DataStorage.user.listIterator();
        Iterator<AdminRegisterComponent> adminIterator = DataStorage.admin.listIterator();
        
        //itera sobre elementos de usuario
        while(userIterator.hasNext()) {
            //armazena o proximo usuario, se houver
            UserRegisterComponent user = userIterator.next();
            
            //validacao
            if(user.email().equals(email) 
                    && user.password().equals(stringPass)) {
                return new Box<>(user);
            }
        }
        
        //itera sobre elementos de admin
        while(adminIterator.hasNext()) {
            //armazena o proximo admin, se houver
            AdminRegisterComponent admin = adminIterator.next();
            
            //validacao
            if(admin.email().equals(email) 
                    && admin.password().equals(stringPass)) {
                return new Box<>(admin);
            }
        }
        
        //se nenhum usuario for encontrado, lancar uma excessao
        throw new NoSuchElementException("\nError: User not found\n");
    };
    
    default Box<?> authenticateHelper(String email, String password) {
        //cria uma array de chars do tamanho da String password
        char[] charPassword = new char[password.length()];
        
        //obtem os caracteres da String password e os envia para a array charPassword
        password.getChars(0, password.length(), charPassword, 0);
        
        //retorna a instancia de um objeto box de tipo desconhecido que estende de Authenticate
        return authenticate(email, charPassword);
    }
}
