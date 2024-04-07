package com.systemgen.librarysystem.librarysystem2;

import java.util.Arrays;
import java.util.Formatter;
import java.util.Objects;
import java.util.function.DoubleToIntFunction;
import java.util.function.Predicate;

/**
 * Fornece implementacoes para validar/formatar dados
 * @author deiv
 */
public interface DataValidationAndFormatting {
    
    //declara o tamanho total de um numero de telefone
    final int NUMBER_LENGTH = 11;
    final int MIN_PASSWORD_LENGTH = 6;
    final int MAX_PASSWORD_LENGTH = 32;
    final DoubleToIntFunction toInt = d -> (int)d;
    
    //alguns provedores de email mais populares
    final String[] EMAIL_PROVIDERS = {
        "gmail", "hotmail", "outlook", "yahoo"
    };
    
    
    //predicado para teste padrao (null/empty)
    Predicate<String> isNotNull = Objects::nonNull;
    Predicate<String> isNotEmpty = Predicate.not(String::isEmpty);
    Predicate<String> def_predicate = isNotNull.or(isNotEmpty);
    
    //predicado para teste numerico
    Predicate<String> phoneLength = s -> s.length() == NUMBER_LENGTH;
    Predicate<String> phone_predicate = def_predicate.and(phoneLength);
    
    //predicado para teste de senha
    Predicate<String> passwordLength = pass -> {
        return pass.length() >= MIN_PASSWORD_LENGTH
                && pass.length() <= MAX_PASSWORD_LENGTH;
    };
    Predicate<String> pass_predicate = def_predicate.and(passwordLength);
    
    
    //valida e formata o nome de usuario    --- check
    default String formatAndValidateName(String name, String surname) {
        if(def_predicate.test(name) && def_predicate.test(surname)) {
            String nameTrim = name.trim();
            String surnameTrim = surname.trim();
            
            StringBuilder sbName = new StringBuilder(nameTrim);
            StringBuilder sbSurname = new StringBuilder(surnameTrim);
            
            String firstNameChar = nameTrim.substring(0, 1).toUpperCase();
            String secondNameChar = surnameTrim.substring(0, 1).toUpperCase();
            
            sbName.replace(0, 1, firstNameChar);
            sbSurname.replace(0, 1, secondNameChar);
            
            return sbName + " " + sbSurname;
        }
        
        throw new IllegalArgumentException("\nError: name/surname cannot be empty/null\n");
    }
    
    //valida e formata um numero de telefone    --- check
    default String formatAndValidatePhoneNumber(long phone) {
        //transforma o valor de phone para String para verificacao
        String phoneString = String.valueOf(phone);
        
        //realiza os testes na variavel phoneString
        if(phone_predicate.test(phoneString)) {
            //instancia um objeto de formatador
            try(Formatter formatter = new Formatter()) {
                //obtem o (ddd) numero1 - numero2;
                String ddd = phoneString.substring(0, 2);
                String phone1 = phoneString.substring(2, 7);
                String phone2 = phoneString.substring(7, NUMBER_LENGTH);
                
                //realiza a formatacao do numero e retorna a representacao em string
                return formatter.format("(%1$s) %2$s-%3$s", ddd, phone1, phone2).toString();
            }
        }
        throw new IllegalArgumentException("\nError: Number is not valid\n");
    }
    
    //recebe um email e valida  --- check
    default void validateEmail(String email) {
        //caracteres especiais de validacao de email
        String specialCharSep = "@";
        String dotSep = ".";
        
        //realiza os devidos testes
        if(def_predicate.test(email) && email.endsWith(".com")) {
            //remove os espacos em branco no inicio e no fim da string email
            String emailTrim = email.trim();
            
            int specialCharSepIndex = emailTrim.lastIndexOf(specialCharSep);
            int dotSepIndex = emailTrim.lastIndexOf(dotSep);
           
            //obtem o provedor de email fornecido pelo usuario
            String emailProvider = emailTrim.substring(specialCharSepIndex + 1, dotSepIndex);
            
            if (java.util.Arrays.binarySearch(EMAIL_PROVIDERS, emailProvider) < 0) {
                throw new IllegalArgumentException("\nError: Invalid mail provider\n");
            }
            
            //finaliza a execucao do metodo caso o email seja valido
            return;
        }
        
        throw new IllegalArgumentException("\nError: Invalid mail format\n");
    }
    
    //formata a senha substituindo alguns caracteres por *
    default String formatpassword(char[] password) {
        if(pass_predicate.test(String.valueOf(password))) {
            
            //guarda o valor do comprimento total / 2 do parametro
            int passLength;
            
            //verifica se a senha tem um length() par ou impar
            //se for par, atribui a variavel passLength stringPass.length() / 2
            //se for impar, chama a funcao toInt, que converte o double para int
            //e passa como argumento o metodo Math.floor(password.length / 2)
            //O metodo floor retorna o maior inteiro menor ou igual ao argumento
            if(password.length % 2 != 0) {
                passLength = toInt.applyAsInt(Math.floor(password.length / 2));
            } else {
                passLength = password.length / 2;
            }
            
            //realiza uma copia segura da array de chars do parametro, sem alterar a array-argumento password
            char[] newCharArrayPassword = Arrays.copyOfRange(password, 0, password.length);
            
            //repoe os caracteres da metade da array ate o fim por *
            Arrays.fill(newCharArrayPassword, passLength, newCharArrayPassword.length, '*');
            
            //retorna uma nova string da array modificada
            return new String(newCharArrayPassword);
        }
        
        throw new IllegalArgumentException("\nError: Password cannot be empty/null or outside the size standard\n");
    }
}
