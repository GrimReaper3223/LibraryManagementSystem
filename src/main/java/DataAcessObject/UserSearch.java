
package DataAcessObject;

import java.util.ArrayList;
import java.util.Scanner;

//TODO: modificar o metodo getAllUserData para que ele armazene todos os dados de usuarios encontrados correspondente aos dados passados para pesquisa, e nao somente 1 unico usuario.

/**
 * Essa classe tera as ferramentas de busca de forma simplificada
 * 
 * @author deiv
 */
public class UserSearch {
    
    //obtem quantos usuarios estao registrados no sistema (no indice 0 esta o usuario 1, no indice 1, usuario 2... )
    public static String getMaxUsersRegistered() {
        String message = "Existem " + UserRegister.getQuantityOfUserInstances() + " usuarios cadastrados."; 
        return message;
    };
    
    
    //E executado por chamada do case correspondente no metodo 'userSearchBy'
    private static String getAllUserData(UserRegister userObject) {
        StringBuilder sb = new StringBuilder();
        
        String[] data = new String[] {
            "Indice de Registro: " + userObject.getRegisterIndex(),
            "UID: " + userObject.getUID(),
            "Nome: " + userObject.getName(),
            "E-mail: " + userObject.getEmail(),
            "Endereco: " + userObject.getAddress(),
            "Telefone: " + userObject.getPhone()
        };
        
        for(String toAppend : data) {
            sb.append(toAppend).append(";\n");
        }
        
        return sb.toString();
    };
    
    
    //pesquisa um usuario fornecendo alguns dados
    //pode receber diretamente um argumento scanner que obtem um numero digitado pelo usuario
    public static String userSearchBy(int option) {
        Scanner scanner = new Scanner(System.in);
        
        return switch(option) {
            //pesquisar por indice >>> PASSED!
            case 1 -> {
                System.out.print("Digite um indice [0 - 999] para pesquisar: ");
                int indexInput = scanner.nextInt();

                if(indexInput < 0 || indexInput > 999) {
                    yield "Erro: Os dados inseridos estao fora do escopo da pesquisa";
                    
                } else if(Database.USER[indexInput] != null) {
                    yield getAllUserData(Database.USER[indexInput]);
                }
                
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            
            //Pesquisar por UID  >>> PASSED! 
            case 2 -> {
                System.out.print("Digite um ID de usuario [10000 - 99999] para pesquisar: ");
                int uidInput = scanner.nextInt();
                
                if(uidInput < 10000 || uidInput > 99999) {
                   yield "Erro: Os dados inseridos estao fora do escopo de identificacao"; 
                }
                
                String stringUID = String.format("UID%5d", uidInput);
                
                for(UserRegister uid : Database.USER) {
                    if(uid == null) {
                        break;
                    }
                    
                    if(uid.getUID().equals(stringUID)) {
                        yield getAllUserData(uid);
                    } 
                }
                
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            
            //Pesquisar por nome >>> PASSED!
            case 3 -> {
                System.out.print("Digite o nome do usuario para pesquisar: ");
                String nameInput = scanner.nextLine();
                
                if(!nameInput.isBlank()) {
                    for(UserRegister name : Database.USER) {
                        if(name == null) {
                            break;
                        }

                        if(name.getName().equalsIgnoreCase(nameInput)) {
                            yield getAllUserData(name);
                        }
                    }
                }
                
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            
            //Pesquisar por Email >>> PASSED!
            case 4 -> {
                System.out.print("Digite um e-mail de usuario para pesquisar: ");
                String mailInput = scanner.nextLine();
                
                if(!mailInput.isBlank()) {
                    for(UserRegister mail : Database.USER) {
                        if(mail == null) {
                            break;
                        }
                        
                        if(mail.getEmail().equalsIgnoreCase(mailInput)) {
                            yield getAllUserData(mail);
                        }
                    }
                }
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            
            //Pesquisar por endereco >>> PASSED!
            case 5 -> {
                System.out.print("Digite um endereco de usuario para pesquisar: ");
                String addressInput = scanner.nextLine();
                
                if(!addressInput.isBlank()) {
                    for(UserRegister address : Database.USER) {
                        if(address == null) {
                            break;
                        }
                        
                        if(address.getAddress().equalsIgnoreCase(addressInput)) {
                            yield getAllUserData(address);
                        }
                    }
                }
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            
            //Pesquisar por telefone --- pendente
            case 6 -> {
                System.out.print("Digite um telefone de usuario para pesquisar: ");
                int phoneInput = scanner.nextInt();
                
                String stringPhone = String.format("%d", phoneInput);
                
                for(UserRegister phone : Database.USER) {
                    if(phone == null) {
                        break;
                    }
                    
                    if(phone.getPhone().equals(stringPhone)) {
                        yield getAllUserData(phone);
                    }
                }
                
                yield "Nao foram encontrados resultados relacionados com a pesquisa";
            }
            
            default -> "Opcao invalida. Tente novamente";
        };
    };
    
    
    //metodo que retorna uma tabela de dados de acordo com a lista de argumentos que o usuario deseja pesquisar.
    //maxRange vai corresponder ate que indice o usuario deseja pessquisar.
    //filterArgumentos vai corresponder a que dados pesquisar.
    public static String filteredUserSearch(int maxRange, String filterArguments) {
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
        
        String[][] usersDataRecovered = new String[sequenceOfFilteredArguments.size()][maxRange];
        
        //o for vai iterar sobre a lista de argumentros e vai passar cada argumento em cada case correspondente
        for(String toSearch : sequenceOfFilteredArguments) {
            switch(toSearch.toLowerCase()) {
                case "indice", "registro": 
                    while(i < maxRange) {
                        UserRegister registerIndex = Database.USER[i];
                        if(registerIndex == null) {
                            break;
                        } else {
                            usersDataRecovered[j][i] = registerIndex.getRegisterIndex();
                            ++i;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                case "uid", "id": 
                        while(i < maxRange) {
                            UserRegister uid = Database.USER[i];
                            if(uid == null) {
                                break;
                            } else {
                                usersDataRecovered[j][i] = uid.getUID();
                                ++i;
                            }
                        }
                    i = 0;
                    ++j;
                    break;
                case "nome":
                    while(i < maxRange) {
                        UserRegister name = Database.USER[i];
                        if(name == null) {
                            break;
                        } else {
                            usersDataRecovered[j][i] = name.getName();
                            ++i;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                case "email", "e-mail", "mail":
                    while(i < maxRange) {
                        UserRegister mail = Database.USER[i];
                        if(mail == null) {
                            break;
                        } else {
                            usersDataRecovered[j][i] = mail.getEmail();
                            ++i;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                case "endereco":
                    while(i < maxRange) {
                        UserRegister address = Database.USER[i];
                        if(address == null) {
                            break;
                        } else {
                            usersDataRecovered[j][i] = address.getAddress();
                            ++i;
                        }
                    }
                    i = 0;
                    ++j;
                    break;
                case "telefone":
                    while(i < maxRange) {
                        UserRegister phone = Database.USER[i];
                        if(phone == null) {
                            break;
                        } else {
                            usersDataRecovered[j][i] = phone.getPhone();
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
        for(String[] firstDimension : usersDataRecovered) {
            sb.append('\n').append(sequenceOfFilteredArguments.toArray()[i].toString().toUpperCase()).append(": \n");
            for(String secondDimension : firstDimension) {
                //Descomplicando a visualizacao dos dados de pesquisa por indice / registro
                if(sequenceOfFilteredArguments.toArray()[i].toString().equalsIgnoreCase("indice") ||
                        sequenceOfFilteredArguments.toArray()[i].toString().equalsIgnoreCase("registro") && secondDimension != null) {
                    sb.append(String.format("\tUsuario %d -> Indice %s;\n",  k,secondDimension));
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
    };
};
