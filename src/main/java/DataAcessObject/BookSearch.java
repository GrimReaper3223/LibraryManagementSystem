
package DataAcessObject;

import java.util.ArrayList;
import java.util.Scanner;

//TODO: modificar o metodo getAllBookData para que ele armazene todos os dados de livros encontrados correspondente aos dados passados para pesquisa, e nao somente 1 unico livro.

/**
 * Essa classe contem as ferramentas de pesquisas de um livro. 
 * Resolvi separar da classe de ferramentas de pesquisas de usuario por conta do tamanho...
 * Os metodos sao os mesmos da classe UserSearch, mas com adaptacoes para os campos dos livros registrados
 * 
 * @author deiv
 */
public class BookSearch {
    
    //obtem quantos livros estao registrados no sistema (no indice 0 esta o livro 1, no indice 1, livro 2... )
    public static String getMaxBooksRegistered() {
        String message = "Existem " + BookRegister.getQuantityOfBookInstances() + " livros cadastrados."; 
        return message;
    };
    
    //E executado por chamada do case correspondente no metodo 'bookSearchBy'
    private static String getAllBookData(BookRegister bookObject) {
        StringBuilder sb = new StringBuilder();
        
        String[] data = new String[] {
            "Indice de Registro: " + bookObject.getRegisterIndex(),
            "BID: " + bookObject.getBID(),
            "Title: " + bookObject.getTitle(),
            "Autor: " + bookObject.getAuthor(),
            "Genero: " + bookObject.getGenre(),
            "Ano de Publicacao: " + bookObject.getPublishYear(),
            "Tamanho: " + bookObject.getLength() + "Paginas"
        };
        
        for(String toAppend : data) {
            sb.append(toAppend).append(";\n");
        }
        
        return sb.toString();
    };
    
    //pesquisa por um livro passando algumas opcoes de busca e fornecendo alguns dados
    public static String bookSearchBy(int option) {
        Scanner scanner = new Scanner(System.in);
        
        return switch(option) {
            //pesquisar por indice >>> PASSED!
            case 1 -> {
                System.out.print("Digite um indice [0 - 999] para pesquisar: ");
                int indexInput = scanner.nextInt();

                if(indexInput < 0 || indexInput > 999) {
                    yield "Erro: Os dados inseridos estao fora do escopo da pesquisa";
                    
                } else if(Database.BOOK[indexInput] != null) {
                    yield getAllBookData(Database.BOOK[indexInput]);
                }
                
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            
            //Pesquisar por UID  >>> PASSED! 
            case 2 -> {
                System.out.print("Digite o ID de um livro [1 - 9999] para pesquisar: ");
                int bidInput = scanner.nextInt();
                
                if(bidInput < 1 || bidInput > 9999) {
                   yield "Erro: Os dados inseridos estao fora do escopo de identificacao"; 
                }
                
                String stringBID = String.format("BID%04d", bidInput);
                
                for(BookRegister bid : Database.BOOK) {
                    if(bid == null) {
                        break;
                    }
                    
                    if(bid.getBID().equals(stringBID)) {
                        yield getAllBookData(bid);
                    } 
                }
                
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            
            //Pesquisar por nome >>> PASSED!
            case 3 -> {
                System.out.print("Digite o titulo do livro para pesquisar: ");
                String titleInput = scanner.nextLine();
                
                if(!titleInput.isBlank()) {
                    for(BookRegister title : Database.BOOK) {
                        if(title == null) {
                            break;
                        }

                        if(title.getTitle().equalsIgnoreCase(titleInput)) {
                            yield getAllBookData(title);
                        }
                    }
                }
                
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            
            //Pesquisar por Email >>> PASSED!
            case 4 -> {
                System.out.print("Digite o nome de um autor para pesquisar: ");
                String authorInput = scanner.nextLine();
                
                if(!authorInput.isBlank()) {
                    for(BookRegister author : Database.BOOK) {
                        if(author == null) {
                            break;
                        }
                        
                        if(author.getAuthor().equalsIgnoreCase(authorInput)) {
                            yield getAllBookData(author);
                        }
                    }
                }
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            
            //Pesquisar por endereco >>> PASSED!
            case 5 -> {
                System.out.print("Digite um genero para pesquisar: ");
                String genreInput = scanner.nextLine();
                
                if(!genreInput.isBlank()) {
                    for(BookRegister genre : Database.BOOK) {
                        if(genre == null) {
                            break;
                        }
                        
                        if(genre.getGenre().equalsIgnoreCase(genreInput)) {
                            yield getAllBookData(genre);
                        }
                    }
                }
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            
            //Pesquisar por telefone --- pendente
            case 6 -> {
                System.out.print("Digite um ano para pesquisar todos os livros registrados no ano correspondente: ");
                int publishYearInput = scanner.nextInt();
                
                String stringPublishYear = String.format("%d", publishYearInput);
                
                for(BookRegister publishYear : Database.BOOK) {
                    if(publishYear == null) {
                        break;
                    }
                    
                    if(publishYear.getPublishYear().equals(stringPublishYear)) {
                        yield getAllBookData(publishYear);
                    }
                }
                
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            case 7 -> {
                System.out.print("Digite uma quantidade de paginas para pesquisar por todos os livros de tamanhos correspondentes: ");
                int lengthInput = scanner.nextInt();
                
                String stringLength = String.format("%d", lengthInput);
                
                for(BookRegister length : Database.BOOK) {
                    if(length == null) {
                        break;
                    }
                    
                    if(length.getLength().equals(stringLength)) {
                        yield getAllBookData(length);
                    }
                }
                
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            default -> "Opcao invalida. Tente novamente";
        };
    };
    
    //pesquisa por um livro de acordo com quantos dados foram selecionados para filtragem
    public static String filteredBookSearch(int maxRange, String filterArguments) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> sequenceOfFilteredArguments = new ArrayList();
        Scanner scanner = new Scanner(filterArguments).useDelimiter(", ");
        
        //i e j sao variaveis que controlam o fluxo e a iteracao de cada indice
        //k e a variavel que vai indicar que no indice 0 existe 1 usuario e assim por diante
        int i = 0;
        int j = 0;
        int k = 1;
        
        while(scanner.hasNext()) {
            sequenceOfFilteredArguments.add(scanner.next());
        }
        
        String[][] booksDataRecovered = new String[sequenceOfFilteredArguments.size()][maxRange];
        
        //o loop vai iterar sobre a arraylist de argumentos e vai passar cada argumento iterado em cada case correspondente
        for(String toSearch : sequenceOfFilteredArguments) {
            switch(toSearch.toLowerCase()) {
                case "indice", "registro": 
                    while(i < maxRange) {
                        BookRegister registerIndex = Database.BOOK[i];
                        if(registerIndex == null) {
                            break;
                        } else {
                            booksDataRecovered[j][i] = registerIndex.getRegisterIndex();
                            ++i;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                case "bid", "id": 
                        while(i < maxRange) {
                            BookRegister bid = Database.BOOK[i];
                            if(bid == null) {
                                break;
                            } else {
                                booksDataRecovered[j][i] = bid.getBID();
                                ++i;
                            }
                        }
                    i = 0;
                    ++j;
                    break;
                case "titulo":
                    while(i < maxRange) {
                        BookRegister title = Database.BOOK[i];
                        if(title == null) {
                            break;
                        } else {
                            booksDataRecovered[j][i] = title.getTitle();
                            ++i;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                case "autor", "escritor":
                    while(i < maxRange) {
                        BookRegister author = Database.BOOK[i];
                        if(author == null) {
                            break;
                        } else {
                            booksDataRecovered[j][i] = author.getAuthor();
                            ++i;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                case "genero", "tipo":
                    while(i < maxRange) {
                        BookRegister genre = Database.BOOK[i];
                        if(genre == null) {
                            break;
                        } else {
                            booksDataRecovered[j][i] = genre.getGenre();
                            ++i;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                case "publicacao", "ano":
                    while(i < maxRange) {
                        BookRegister publishYear = Database.BOOK[i];
                        if(publishYear == null) {
                            break;
                        } else {
                            booksDataRecovered[j][i] = publishYear.getPublishYear();
                            ++i;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                case "tamanho", "paginas":
                    while(i < maxRange) {
                        BookRegister length = Database.BOOK[i];
                        if(length == null) {
                            break;
                        } else {
                            booksDataRecovered[j][i] = length.getLength();
                            ++i;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                default: return "Alguns argumentos do filtro foram digitados errado. Tente novamente";
            }
        }
        
        //iteracao para organizar os dados na tela em um objeto StringBuilder
        for(String[] firstDimension : booksDataRecovered) {
            sb.append('\n').append(sequenceOfFilteredArguments.toArray()[i].toString().toUpperCase()).append(": \n");
            for(String secondDimension : firstDimension) {
                //Descomplicando a visualizacao dos dados de pesquisa por indice / registro
                if(sequenceOfFilteredArguments.toArray()[i].toString().equalsIgnoreCase("indice") ||
                        sequenceOfFilteredArguments.toArray()[i].toString().equalsIgnoreCase("registro") && secondDimension != null) {
                    sb.append(String.format("\tLivro %d -> Indice %s;\n",  k,secondDimension));
                    ++k;
                    continue;
                }
                
                if(secondDimension != null) {
                    sb.append(String.format("\t%d. %s;\n",  k,secondDimension));
                    ++k;
                } else {
                    break;
                }
            }
            k = 1;
            ++i;
        }
        return sb.toString();
    }
}
