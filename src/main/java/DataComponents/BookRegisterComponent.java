package DataComponents;

/**
 * Registra um livro no sistema
 * O tipo de registro e um record.
 * 
 * Este record contem todas as informacoes de um livro
 * 
 * @author deiv
 */
public record BookRegisterComponent(String title, String author, String genre, int publishYear, int length, long bid) {
    
    static long bookSeed = 2000L;        //max = 7000L - 500 livros
}
