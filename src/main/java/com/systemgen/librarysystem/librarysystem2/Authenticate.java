package com.systemgen.librarysystem.librarysystem2;

import DataComponents.*;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *
 * @author deiv
 */
public interface Authenticate {
    
    // metodo de autenticacao da interface
    // retorna uma instancia box de um tipo desconhecido que implementa a interface authenticate
    default Box<? extends Authenticate> authenticate(String email, char[] password) {
        String stringPass = new String(password);
        
        //obtem o iterador das listas de usuario/admin
        Iterator<UserRegisterComponent> userIterator = DataStorage.user.listIterator();
        Iterator<AdminRegisterComponent> adminIterator = DataStorage.admin.listIterator();
        
        //itera sobre elementos de usuario
        while(userIterator.hasNext()) {
            //armazena o proximo usuario, se houver
            UserRegisterComponent user = userIterator.next();
            
            //validacao
            if(user.email().equals(email) && user.password().equals(stringPass)) {
                return new Box<>(user);
            }
        }
        
        //itera sobre elementos de admin
        while(adminIterator.hasNext()) {
            //armazena o proximo admin, se houver
            AdminRegisterComponent admin = adminIterator.next();
            
            //validacao
            if(admin.email().equals(email) && admin.password().equals(stringPass)) {
                return new Box<>(admin);
            }
        }
        
        //se nenhum usuario for encontrado, lancar uma excessao
        throw new NoSuchElementException("Error: User not found");
    };
}
