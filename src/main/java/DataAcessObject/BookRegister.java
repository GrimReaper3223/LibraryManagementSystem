
package DataAcessObject;

import java.util.Random;

//TODO: refatorar/organizar todos os codigos em todas as classes e tambem os comentarios. 
//TODO: organizar e gerar javadocs.
//FIX: remover duplicidade desnecessaria no codigo e verificar outras ambiguidades
//TODO: DEPURAR CADA ETAPA DO CODIGO ASSIM QUE A PRIMEIRA FASE DE IMPLANTACAO DAS FERRAMENTAS FOR CONCLUIDA

/**
 *
 * @author deiv
 */
class BookRegister {
    //objeto Random para gerar o Book ID (BID)
    Random randomBID = new Random();
    
    //variavel de classe que incrementa + 1 para cada objeto book instanciado da classe. essa variavel sera usada para pesquisar o indice do objeto
    private static int numberOfBookObject = 0;
    
    //variaveis de dados para livros (REGISTER_INDEX e uma variavel de instancia e sera inicializada com um valor assim que um objeto for instanciado dessa classe)
    private final String REGISTER_INDEX;
    private String bid, title, author, genre, publishYear, length;
    
    //construtor para livros
    BookRegister(String title, String author, String genre, int publishYear, int length) {
        int intBID = randomBID.nextInt(1, 10_000);
        if(intBID >= 1 && intBID < 1000) {
           this.bid = String.format("BID%04d", intBID);
        } else {
           this.bid = String.format("BID%d", intBID);
        }
        
        this.REGISTER_INDEX = String.format("%d", numberOfBookObject);
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishYear = String.format("%4d", publishYear);
        this.length = String.format("%d Pages", length);
        
        if(!verifyIfHasSpaceToRegisterBook()) {
            ++numberOfBookObject;
        }
    };
    
    //getters...
    static int getQuantityOfBookInstances() { return numberOfBookObject; };
    String getRegisterIndex() { return this.REGISTER_INDEX; };
    String getBID() {return this.bid;};
    String getTitle() {return this.title;};
    String getAuthor() {return this.author;};
    String getGenre() {return this.genre;};
    String getPublishYear() {return this.publishYear;};
    String getLength() {return this.length;};

    //setters...
    static boolean verifyIfHasSpaceToRegisterBook() {
        boolean verification = numberOfBookObject == Database.BOOK.length - 1;
        return verification;
    }
    
    static void decreaseBookRegisterIndex() { --numberOfBookObject; };  //esse metodo so sera usado quando um metodo de remocao de um livro do registro for chamado.
    
    void setBid(String bid) {
        this.bid = bid;
    };

    void setTitle(String title) {
        this.title = title;
    };

    void setAuthor(String author) {
        this.author = author;
    };

    void setGenre(String genre) {
        this.genre = genre;
    };

    void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    };

    void setLength(String length) {
        this.length = length;
    };
};
