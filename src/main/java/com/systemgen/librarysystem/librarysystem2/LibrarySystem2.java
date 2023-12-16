
package com.systemgen.librarysystem.librarysystem2;

import DataAcessObject.Create;
import DataAcessObject.Search;

/**
 *
 * @author deiv
 */
public class LibrarySystem2 {

    public static void main(String[] args) {
        
        Create create = new Create();
        Search search = new Search();
        
        //criando varios objetos para testar algumas configuracoes...
        create.pushUserRegisterToArray("John Doe", "John337_Doe@hotmail.com", "California St", 5559923);
        create.pushUserRegisterToArray("John Doe", "John337_Doe@hotmail.com", "California St", 5559923);
        create.pushUserRegisterToArray("John Doe", "John337_Doe@hotmail.com", "California St", 5559923);
        create.pushUserRegisterToArray("John Doe", "John337_Doe@hotmail.com", "California St", 5559923);
        create.pushUserRegisterToArray("John Doe", "John337_Doe@hotmail.com", "California St", 5559923);
        
        create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        
        //adicionando um parametro fora do escopo do tratamento de casos no switch, resultara no retorno de uma mensagem de erro, presente no caso default
        String[] filter = {"uid", "name", "phone"};
        System.out.println(search.getAUserListWithDataFiltered(filter, 3));
    };
};
