
package com.systemgen.librarysystem.librarysystem2;

import DataAcessObject.BookSearch;
import DataAcessObject.Create;
import DataAcessObject.UserSearch;
import java.util.Scanner;

/**
 *
 * @author deiv
 */
public class LibrarySystem2 {
    
    //instancia de Scanner para testar funcoes simulando o uso de um usuario e possiveis implementacoes
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
      
        //criando varios objetos para testar algumas configuracoes relacionadas a quantidade e verificacao...
        Create.pushUserRegisterToArray("John Senna", "John337_Doe@hotmail.com", "California St", 5559923);
        Create.pushUserRegisterToArray("John Doe", "John337_Doe@hotmail.com", "California St", 5559923);
        Create.pushUserRegisterToArray("Jane Doe", "John337_Doe@hotmail.com", "California St", 5559923);
        Create.pushUserRegisterToArray("John Senna", "John337_Doe@hotmail.com", "California St", 5559923);
        Create.pushUserRegisterToArray("Morgan Doe", "John337_Doe@hotmail.com", "California St", 5559923);
        Create.pushUserRegisterToArray("John Doe", "John337_Doe@hotmail.com", "California St", 5559923);
        Create.pushUserRegisterToArray("Jane Doe seagan", "John337_Doe@hotmail.com", "California St", 5559923);
        Create.pushUserRegisterToArray("John Senna", "John337_Doe@hotmail.com", "California St", 5559923);
        Create.pushUserRegisterToArray("Morgan Doe", "John337_Doe@hotmail.com", "California St", 5559923);
        Create.pushUserRegisterToArray("Morgan Doe", "John337_Doe@hotmail.com", "California St", 5559923);
        Create.pushUserRegisterToArray("Morgan Doe", "John337_Doe@hotmail.com", "California St", 5559923);
        
        Create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        Create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        Create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        Create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        Create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        Create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        Create.pushBookRegisterToArray("A Vida de John Doe", "John Doe", "Ficcao", 2013, 566);
        
        //testando metodo para verificar quantos objetos foram instanciados e salvos
        System.out.println(UserSearch.getMaxUsersRegistered());
        System.out.println(BookSearch.getMaxBooksRegistered());
        
        //metodo de filtragem para livros e filtragem para usuarios
        System.out.println(BookSearch.filteredBookSearch(10, scanner.nextLine()));
        System.out.println(UserSearch.filteredUserSearch(5, scanner.nextLine()));
    };
};
