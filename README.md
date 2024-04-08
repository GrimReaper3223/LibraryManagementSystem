# Library System Management

This project aims to simulate a library management system (but can be refactored, depending on the needs of what needs to be managed).

The project is currently undergoing intense refactoring, which aims to achieve greater security in registration and login operations and also more efficiency in data handling.

## To run the project, follow the following instructions:

### Cloning the functional repository:

To download the refactoring version of the project (latest and working), paste or type the following code into your terminal/cmd

```shell
> $ git clone https://github.com/GrimReaper3223/LibraryManagementSystem/tree/refactory
```
After downloading the repository, go to the 'target' folder and run the following command in your terminal/cmd:

```shell
> $ java -jar LibrarySystem2-X.X.jar  --> 'X' is a newest version
```

* NOTE: This project was developed using JDK 21, and does not have any backward compatibility with Java 20 or later. Therefore, to run this application, have the latest version of Java installed (Java 22) or Java 21 itself;


* ~ATTENTION: DO NOT EXECUTE THIS PROJECT IN ANY WAY OTHER THAN THROUGH THE TERMINAL. IF THIS IS DONE, THE CONSOLE INSTANCE MAY NOT EXIST AND END UP BY CLOSING THE APPLICATION ABRUPTLY WITH CODE 1 WHEN THE LOGIN/REGISTRATION METHOD IS CALLED. PLEASE RUN THIS APPLICATION FROM THE TERMINAL~

> that is no longer true. Now, regardless of how the application is launched, you will still be able to log in and register using Scanner instances. Even so, I still strongly recommend launching the application through a terminal, as the Console allows greater security when typing confidential data, such as passwords, for example, because the Console does not have typing echo;
