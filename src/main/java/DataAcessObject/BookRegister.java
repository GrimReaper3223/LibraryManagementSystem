
package DataAcessObject;

import java.util.Random;

/**
 *
 * @author deiv
 */
class BookRegister {
    //objeto Random para gerar o Book ID (BID)
    Random randomBID = new Random();
    
    //variaveis de dados para livros
    private String bid, title, author, genre, publishYear, length;
    
    //construtor para livros
    BookRegister(String title, String author, String genre, int publishYear, int length) {
        int intBID = randomBID.nextInt(1, 10_000);
        if(intBID >= 1 && intBID < 1000) {
           bid = String.format("BID%04d", intBID);
        } else {
           bid = String.format("BID%d", intBID);
        }
        
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishYear = String.format("%4d", publishYear);
        this.length = String.format("%d Pages", length);
    };
    
    //getters...
    String getBID() {return bid;};
    String getTitle() {return title;};
    String getAuthor() {return author;};
    String getGenre() {return genre;};
    String getPublishYear() {return publishYear;};
    String getLength() {return length;};

    //setters...
    void setBid(String newBid) {
        bid = newBid;
    };

    void setTitle(String newTitle) {
        title = newTitle;
    };

    void setAuthor(String newAuthor) {
        author = newAuthor;
    };

    void setGenre(String newGenre) {
        genre = newGenre;
    };

    void setPublishYear(String newPublishYear) {
        publishYear = newPublishYear;
    };

    public void setLength(String newLength) {
        length = newLength;
    };
};
