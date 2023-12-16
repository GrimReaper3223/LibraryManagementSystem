
package DataAcessObject;

import static DataAcessObject.Database.book;
import static DataAcessObject.Database.user;

/**
 *
 * @author deiv
 */
public class Search {
    
    public Search() {};
    
    
    //obtem a quantidade total de usuarios ou livros registrados no banco de dados
    //o parametro option pode receber dois argumentos: user(s) ou book(s). O valor retornado sera correspondente ao argumento passado
    public String getLengthOfValidRegistersInDB(String option) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        
        return switch(option.toLowerCase()) {
            case "user", "users" -> {
                for(UserRegister userObjectIterator : user) {
                    if(userObjectIterator != null) {
                        ++count;
                    } else {
                        break;
                    }
                }

                sb.append("Foram encontrados ").append(count).append(" usuarios cadastrados no sistema.");
                yield sb.toString();
            }
            
            case "book", "books" -> {
                for(BookRegister bookObjectIterator : book) {
                    if(bookObjectIterator != null) {
                        ++count;
                    } else {
                        break;
                    }
                }
        
                sb.append("Foram encontrados ").append(count).append(" livros cadastrados no sistema.");
                yield sb.toString();
            }
            
            default -> "Opcao invalida";
        };
    };
    
    
    //pesquisa por um usuario ou livro em um determinado indice
    //o parametro option pode receber dois argumentos: user(s) ou book(s). O valor retornado sera correspondente ao argumento passado
    public String getObjectAtIndex(String option, int index) {
        StringBuilder sb = new StringBuilder();
        
        return switch(option.toLowerCase()) {
            case "user", "users" -> {
                UserRegister userAtIndex = user[index];
                
                sb.append("Dados retornados do usuario ").append(++index).append(" no indice pesquisado ").append(--index).append(":\n\n");
                String[] sequenceData = {
                    "UID: " + userAtIndex.getUID(),
                    "Name: " + userAtIndex.getName(),
                    "E-Mail: " + userAtIndex.getEmail(),
                    "Address: " + userAtIndex.getAddress(),
                    "Phone: " + userAtIndex.getPhone(),
                };

                for(String toAppend : sequenceData) {
                    sb.append(toAppend).append(";\n");
                }

                yield sb.toString();
            }
            
            case "book", "books" -> {
                BookRegister bookAtIndex = book[index];
        
                sb.append("Dados retornados do livro ").append(++index).append(" no indice pesquisado ").append(--index).append(":\n\n");
                String[] sequenceData = {
                    "BID: " + bookAtIndex.getBID(),
                    "Title: " + bookAtIndex.getTitle(),
                    "Author: " + bookAtIndex.getAuthor(),
                    "Genre: " + bookAtIndex.getGenre(),
                    "Publish Year: " + bookAtIndex.getPublishYear(),
                    "Page Quantity: " + bookAtIndex.getLength()
                };

                for(String toAppend : sequenceData) {
                    sb.append(toAppend).append(";\n");
                }

                yield sb.toString();
            }
            
            default -> "Opcao invalida";
        };
    };
    
    
    
    //obtem uma lista com todos os dados passados como argumento em uma array de Strings
    public String getAUserListWithDataFiltered(String[] filterList, int searchRange) {
        StringBuilder sb = new StringBuilder();
        String[][] objectList = new String[filterList.length][searchRange];
        
        //variaveis de controle de fluxo
        int i = 0;
        int j = 0;
        int k = 1;
        
        for(String parameters : filterList) {
            switch(parameters.toLowerCase()) {
                case "uid", "id":
                    while(i < searchRange) {
                        UserRegister uid = user[i];
                        if(uid != null) {
                            objectList[j][i] = uid.getUID();
                            ++i;
                        } else {
                            break;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                    
                case "name":
                    while(i < searchRange) {
                        UserRegister name = user[i];
                        if(name != null) {
                            objectList[j][i] = name.getName();
                            ++i;
                        } else {
                            break;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                    
                case "email", "e-mail":
                     while(i < searchRange) {
                        UserRegister email = user[i];
                        if(email != null) {
                            objectList[j][i] = email.getEmail();
                            ++i;
                        } else {
                            break;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                    
                case "address":
                     while(i < searchRange) {
                        UserRegister address = user[i];
                        if(address != null) {
                            objectList[j][i] = address.getAddress();
                            ++i;
                        } else {
                            break;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                    
                case "phone", "tel":
                     while(i < searchRange) {
                        UserRegister phone = user[i];
                        if(phone != null) {
                            objectList[j][i] = phone.getPhone();
                            ++i;
                        } else {
                            break;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                    
                default: return "Erro: Foram encontrados alguns parametros invalidos. Por favor, revise os dados fornecidos e tente novamente";
            }
        }
        
        
        for(String[] toAppend : objectList) {
            sb.append('\n').append(filterList[i].toUpperCase()).append(": \n");
            for(String append : toAppend) {
                if(append != null) {
                    sb.append('\t').append(k).append(". ").append(append).append(";\n");
                    ++k;
                } else {
                    break;
                }
            }
            k = 1;
            ++i;
        }
        
        return sb.toString();
    };
};
