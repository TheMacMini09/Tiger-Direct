/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;

import tiger.direct.TigerDirect;
import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import static tiger.direct.TigerDirect.*;

/*
 * File by: Michael Barker
 */
public class Employees {
    
    //Member variables (to be used throughout this class, or other classes [public])
    
    public static int numItems;
    public static int numLogins;
    public static int numSections;
    public static int numSubsections;
    static boolean exit = false;
    static boolean restart = true;
    static EmployeeLogin[] logins;
    
    public static void writeFile(String name, String toWrite) throws FileNotFoundException{
        //Method to write to the specified file (only used a few times)
        PrintWriter file = new PrintWriter(new File(name));
        
        file.print(toWrite);
        file.close();
    }
    
    public static boolean checkUsername(String username) {
        //Method returns true if the specified username is in the database
        for(int i = 0; i < numLogins; i++){
            if(logins[i].username.equals(username)){
                return true;
            }
        }
        return false;
    }
    
    public static void changeUsername(int ID) throws IOException {
        //Variable declaration
        String username;
        String toWrite = "";
        int position = ID-1;    //Position is the index of the array that contains ID,
        char correct;           //and ID is the ID of the user who's username is to be changed.
        
        System.out.println("Editing username for " + logins[position].username + ".");
        System.out.print("Please enter the new username: ");
        username = user.next();
        user.nextLine();
        while(checkUsername(username)){  //Check if the entered username has already been taken
            System.out.println("Username already exists! ");
            System.out.print("Please enter a new username: ");
            username = user.next();
            user.nextLine();
        }
        System.out.print("Is " + username + " the username you want? Case matters! y/n ");
        correct = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(correct != 'y'){ //Exact same as above
            System.out.print("Please enter the new username: ");
            username = user.next();
            user.nextLine();
            while(checkUsername(username)){
                System.out.println("Username already exists! ");
                System.out.print("Please enter a new username: ");
                username = user.next();
                user.nextLine();
            }
            System.out.print("Is " + username + " the username you want? Case matters! y/n ");
            correct = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        toWrite += (numLogins + ";;" + "\n");   //Add the number of logins to the "file" string
        
        for(int i = 0; i < numLogins; i++){     //Cycle through the logins
            if(i == position){  //If the loop is at the position of the ID, add all of the data to the "file" string but change to the new username.
                toWrite += ((i+1) + ";;" + username + ";;" + logins[position].password + ";;" + logins[position].level + ";;" + "\n");
            } else {            //Otherwise, just add all of the existing data of the other users.
                toWrite += (logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;" + "\n");
            }
        }
        writeFile("authentication", toWrite);   //Send the "file" string to the wrteFile() method.
        System.out.println("Username changed. Please log back in.");
        exit = true;    //Exit the employee interface,
        restart = true; //and restart it so that the user is forced to log back in.
    }
    
    public static void changePassword(int ID) throws FileNotFoundException {
        //Variable declaration
        String password;
        String toWrite = "";
        int position = ID-1;
        char correct;
        
        System.out.println("Editing password for " + logins[position].username + ".");
        System.out.print("Please enter the new password: ");
        password = user.nextLine();
        System.out.print("Is " + password + " the password you want? Case matters! y/n ");
        correct = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(correct != 'y'){
            System.out.print("Please enter the new password: ");
            password = user.nextLine();
            System.out.print("Is " + password + " the password you want? Case matters! y/n ");
            correct = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        toWrite += (numLogins + ";;" + "\n");   //Add the number of logins to the string to write
        for(int i = 0; i < numLogins; i++){     //Cycle through the logins
            if(i == position){  //If the current cycle is the position of the password to be changed, write the data the same as always, just with the changed password.
                toWrite += ((i+1) + ";;" + logins[position].username + ";;" + password + ";;" + logins[position].level + ";;" + "\n");
            } else {            //Otherwise, just write the data of the current login.
                toWrite += (logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;" + "\n");
            }
        }
        writeFile("authentication", toWrite);   //Write the authentication file
        System.out.println("Password changed. Please log back in.");
        exit = true;    //Force the user to log back in.
        restart = true;
    }
    
    public static void addUser(int userLevel) throws FileNotFoundException{
        //Variable declaration
        String username;
        String password;
        String toWrite = "";
        int level;
        char userEntry;
        
        //Self-explanatory. Simple user entry/verification.
        System.out.print("Please enter the desired username for the new user: ");
        username = user.next();
        user.nextLine();
        while(checkUsername(username)){
            System.out.println("Username already exists! ");
            System.out.print("Please enter a new username: ");
            username = user.next();
            user.nextLine();
        }
        System.out.print("Is " + username + " the intended username? y/n: ");
        userEntry = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userEntry != 'y'){
            System.out.print("Please enter the desired username for the new user: ");
            username = user.next();
            user.nextLine();
            while(checkUsername(username)){
                System.out.println("Username already exists! ");
                System.out.print("Please enter a new username: ");
                username = user.next();
                user.nextLine();
            }
            System.out.print("Is " + username + " the intended username? y/n: ");
            userEntry = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        System.out.print("Please enter the desired password for the new user: ");
        password = user.next();
        user.nextLine();
        System.out.print("Is " + password + " the intended password? y/n: ");
        userEntry = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userEntry != 'y'){
            System.out.print("Please enter the desired password for the new user: ");
            password = user.next();
            user.nextLine();
            System.out.print("Is " + password + " the intended password? y/n: ");
            userEntry = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        System.out.print("Please enter the desired level for the user: (1-" + userLevel + "): ");
        level = Methods.checkNumber(1, userLevel);  //Ensures that the user doesn't create another user with a higher level
        while(level == -1){
            System.out.print("Invalid! Please enter the desired level for the user: (1-" + userLevel + "): ");
            level = Methods.checkNumber(1, userLevel);
        }
        System.out.print("Is " + level + " the level you desired? y/n: ");
        userEntry = user.next().trim().toLowerCase().charAt(0);
        while(userEntry != 'y'){
            System.out.print("Please enter the desired level for the user: (1-" + userLevel + "): ");
            level = Methods.checkNumber(1, userLevel);
            while(level == -1){
                System.out.print("Invalid! Please enter the desired level for the user: (1-" + userLevel + "): ");
                level = Methods.checkNumber(1, userLevel);
            }
            System.out.print("Is " + level + " the level you desired? y/n: ");
            userEntry = user.next().trim().toLowerCase().charAt(0);
        }
        toWrite += ((numLogins + 1) + ";;" + "\n");     //Adds the number of users + 1 to the string to write
        for(int i = 0; i < numLogins; i++){     //Cycles through the existing users and adds them to the string
            toWrite += (logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;" + "\n");
        }
        //Adds the new user to the string, with all the new info.
        toWrite += (numLogins+1 + ";;" + username + ";;" + password + ";;" + level + ";;" + "\n");
        writeFile("authentication", toWrite);   //Write the file
        System.out.println("User created.");
    }
    
    public static void removeUser(int ownID) throws FileNotFoundException{
        //Variable declaration
        int userID;
        int position;
        String toWrite = "";
        
        System.out.print("Which user ID would you like to remove? # or 0 to display users: ");
        userID = Methods.checkNumber(0, logins.length);     //Make the user enter a valid user to remove
        if(userID == 0){
            for(int i = 0; i < numLogins; i++){     //Cycle through the list of users, and print them out for the user to chose from
                System.out.println(logins[i].ID + ". " + logins[i].username);
            }
            System.out.print("Which user ID would you like to remove? ");
            userID = Methods.checkNumber(1, logins.length);
        }
        while(userID == -1){
            System.out.print("Invalid user ID! Please enter an ID between 1 and " + (logins.length) + ": ");
            userID = Methods.checkNumber(1, logins.length);
        }
        
        position = userID;

        toWrite += (logins.length-1 + ";;" + "\n");
        for(int i = 0; i < logins.length; i++){     //Cycle through the logins, and update the file.
            if(i > position-1){
                toWrite += (logins[i].ID-1 + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[position].level + ";;" + "\n");  //If the current login is after the login to be removed, decrease the ID.
            } else if(i == position-1){
                //Do nothing, here to make sure that the else is not entered on the current position. Probably should be made more efficient at a later date. @todo
            } else {
                toWrite += (logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;" + "\n");           //Otherwise (for the users before the user to be removed) just print the existing data (no changes)
            }
        }
        writeFile("authentication", toWrite);   //Write the file using the method
        
        System.out.print("User removed.");
        if(userID == ownID){
            System.out.println(" Please log in as a new user.");    //If you remove yourself,
            exit = true;                                           //exit the interface,
            restart = true;                                       //but restart it, prompting
        } else {                                                 //for a login.
            System.out.println();
            exit = false;
        }
    }
    
    public static void changeLevel(int userID) throws FileNotFoundException {
        //Variable declaration
        int level;
        String toWrite = "";
        
        System.out.print("To what level would you like to change " + logins[userID-1].username + " to?\nTheir current level is " + logins[userID-1].level + ": ");
        level = Methods.checkNumber(1, 6);
        while(level == -1){
            System.out.print("Invalid selection! Please choose a new level for " + logins[userID-1].username + ": ");
            level = Methods.checkNumber(1, 6);
        }

        toWrite += (numLogins + ";;" + "\n");   //Add the new number of users to the string
        for(int i = 0; i < numLogins; i++){     //Cycle through the logins
            if(logins[i].ID == userID){         //If the current login is the user who's level was changed, add all of the same data except for the changed level.
                toWrite += (logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + level + ";;" + "\n");
            } else {                            //Otherwise, just add all of the data from the other users.
                toWrite += (logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;" + "\n");
            }
        }
        writeFile("authentication", toWrite);
        System.out.println("Level changed. Please log back in.");
        exit = true;        //Forces the user to log back in,
        restart = true;     //independent of whose ID they're changing.
    }
    
    public static void userSettings(int userLevel, int userID) throws IOException {
        //Variable declaration
        int userSelection;
        
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        System.out.println("What would you like to do?");
        switch(userLevel){
            case 1:
            case 2:
            case 3:
                System.out.println(" 1. Change username");
                System.out.println(" 2. Change password");
                System.out.println(" 3. Exit");
                System.out.print("Enter your choice: ");
                userSelection = Methods.checkNumber(1, 3);
                while(userSelection == -1){
                    System.out.print("Invalid selection! Please enter a number between 1 and 3: ");
                    userSelection = Methods.checkNumber(1, 3);
                }
                switch(userSelection){
                    case 1:
                        changeUsername(userID);
                        break;
                    case 2:
                        changePassword(userID);
                        restart = true;
                        break;
                    case 3:
                        break;
                }
                break;
            case 4:
                System.out.println(" 1. Change username");
                System.out.println(" 2. Change password");
                System.out.println(" 3. Add user");
                System.out.println(" 4. Exit");
                System.out.print("Enter your choice: ");
                userSelection = Methods.checkNumber(1, 4);
                while(userSelection == -1){
                    System.out.print("Invalid selection! Please enter and number between 1 and 4: ");
                    userSelection = Methods.checkNumber(1, 4);
                }
                switch(userSelection){
                    case 1:
                        changeUsername(userID);
                        break;
                    case 2:
                        changePassword(userID);
                        restart = true;
                        break;
                    case 3:
                        addUser(userLevel);
                        break;
                    case 4:
                        break;
                }
                break;
            case 5:
                System.out.println(" 1. Change username");
                System.out.println(" 2. Change password");
                System.out.println(" 3. Add user");
                System.out.println(" 4. Remove user");
                System.out.println(" 5. Exit");
                System.out.print("Enter your choice: ");
                userSelection = Methods.checkNumber(1, 5);
                while(userSelection == -1){
                    System.out.print("Invalid selection! Please enter a number between 1 and 5: ");
                    userSelection = Methods.checkNumber(1, 5);
                }
                switch(userSelection){
                    case 1:
                        changeUsername(userID);
                        break;
                    case 2:
                        changePassword(userID);
                        restart = true;
                        break;
                    case 3:
                        addUser(userLevel);
                        break;
                    case 4:
                        removeUser(userLevel);
                        break;
                }
                break;
            case 6:
                System.out.println(" 1. Change own username");
                System.out.println(" 2. Change own password");
                System.out.println(" 3. Add user");
                System.out.println(" 4. Remove user");
                System.out.println(" 5. Change others' username");
                System.out.println(" 6. Change others' password");
                System.out.println(" 7. Change employee's level");
                System.out.println(" 8. Exit");
                System.out.print("Enter your choice: ");
                userSelection = Methods.checkNumber(1, 8);
                while(userSelection == -1){
                    System.out.print("Invalid selection! Please enter a number between 1 and 8: ");
                    userSelection = Methods.checkNumber(1, 8);
                }
                switch(userSelection){
                    case 1:
                        changeUsername(userID);
                        break;
                    case 2:
                        changePassword(userID);
                        restart = true;
                        break;
                    case 3:
                        addUser(userLevel);
                        break;
                    case 4:
                        removeUser(userLevel);
                        break;
                    case 5:
                        System.out.print("Please enter the user ID who's username you would like to change,\nor 0 to display users: ");
                        userID = Methods.checkNumber(0, logins.length);
                        if(userID == 0){
                            for(int i = 0; i < numLogins; i++){
                                System.out.println(logins[i].ID + ". " + logins[i].username);
                            }
                            System.out.print("Please enter the user ID who's username you would like to change: ");
                            userID = Methods.checkNumber(1, logins.length);
                        }
                        while(userID == -1){
                            System.out.print("Invalid user ID! Please enter an ID between 1 and " + (logins.length) + ": ");
                            userID = Methods.checkNumber(1, logins.length);
                        }
                        changeUsername(userID);
                        break;
                    case 6:
                        System.out.print("Please enter the user ID who's password you would like to change,\nor 0 to display users: ");
                        userID = Methods.checkNumber(0, logins.length);
                        if(userID == 0){
                            for(int i = 0; i < numLogins; i++){
                                System.out.println(logins[i].ID + ". " + logins[i].username);
                            }
                            System.out.print("Please enter the user ID who's username you would like to change: ");
                            userID = Methods.checkNumber(1, logins.length);
                        }
                        while(userID == -1){
                            System.out.print("Invalid user ID! Please enter an ID between 1 and " + (logins.length) + ": ");
                            userID = Methods.checkNumber(1, logins.length);
                        }
                        changePassword(userID);
                        break;
                    case 7:
                        System.out.print("Please enter the user ID who's level you would like to change,\nor 0 to display users: ");
                        userID = Methods.checkNumber(0, logins.length);
                        if(userID == 0){
                            for(int i = 0; i < numLogins; i++){
                                System.out.println(logins[i].ID + ". " + logins[i].username);
                            }
                            System.out.print("Please enter the user ID who's level you would like to change: ");
                            userID = Methods.checkNumber(1, logins.length);
                        }
                        while(userID == -1){
                            System.out.print("Invalid user ID! Please enter an ID between 1 and " + (logins.length) + ": ");
                            userID = Methods.checkNumber(1, logins.length);
                        }
                        changeLevel(userID);
                        break;
                    case 8:
                        break;
                }
                break;
        }
    }
    
    public static void addItem() throws IOException {
        //Variable declaration
        String name;
        String description;
        String shortDescription;
        String keywordsString;
        int ID = 0;
        int section;
        int subsection;
        int dollar;
        int cent;
        int stock;
        int numKeywords = 0;
        int numSubsectionsInSection = 0;
        char userConf;
        
        //Array declarations
        SubSections[] subsectionsInSection = new SubSections[subsections.length];
        int[] validSubsectionIDs = new int[subsections.length];
        
        //Refresh files (in case they weren't updated before...)
        ReadFiles.readDescriptions();
        ReadFiles.readItems();
        ReadFiles.readKeywords();
        ReadFiles.readSections();
        ReadFiles.readSubsections();

        System.out.println("Here are the sections to choose from:");
        System.out.println();
        for(int i = 0; i < numSections; i++){   //Cycle through and print out all of the sections
            System.out.println(sections[i].ID + ". " + sections[i].name + " - " + sections[i].description);
        }
        System.out.println();
        System.out.print("Please enter the section of the item: ");
        section = Methods.checkNumber(1, numSections);

        while(section == -1){
            System.out.print("Invalid selection! Please enter a number between 1 and " + numSections + ": ");
            section = Methods.checkNumber(1, numSections);
        }
        
        System.out.print("Is " + sections[section-1].name + " the section you intended? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userConf != 'y'){
            System.out.print("Please enter the section of the item: ");
            section = Methods.checkNumber(1, numSections);

            while(section == -1){
                System.out.print("Invalid selection! Please enter a number between 1 and " + numSections + ": ");
                section = Methods.checkNumber(1, numSections);
            }

            System.out.print("Is " + sections[section-1].name + " the section you intended? y/n: ");
            userConf = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        for(int i = 0; i < numSubsections; i++){        //Cycle through all of the subsections, and add the ones in the current section to a new array.
            if(subsections[i].sectionID == section){
                subsectionsInSection[numSubsectionsInSection] = subsections[i];
                validSubsectionIDs[numSubsectionsInSection] = subsections[i].ID;
                numSubsectionsInSection++;
            }
        }
        
        Arrays.sort(validSubsectionIDs, 0, numSubsectionsInSection);    //Sort the IDs (shouldn't be necessary, just a precaution incase the files get messed up and ReadFiles doesn't catch it

        System.out.println("Here are the subsections to choose from:");
        
        System.out.println();
        for(int i = 0; i < numSubsectionsInSection; i++){   //Cycle through the valid subsections and print them out.
            System.out.println(subsectionsInSection[i].ID + ". " + subsectionsInSection[i].name + " - " + subsectionsInSection[i].description);
        }
        System.out.println();
        System.out.print("Please enter the subsection of the item: ");
        subsection = Methods.checkNumber(validSubsectionIDs[0], validSubsectionIDs[numSubsectionsInSection-1]);   //Check to make sure that the user entered a subsection in the proper range
        
        while(subsection == -1){ //If section is invalid
            System.out.print("Invalid selection! Please enter a number between " + validSubsectionIDs[0] + " and " + (validSubsectionIDs[0]+numSubsectionsInSection-1) + ": ");
            subsection = Methods.checkNumber(validSubsectionIDs[0], validSubsectionIDs[numSubsectionsInSection]);  //Check to make sure that the user entered a subsection in the proper range
        }
        
        System.out.print("Is " + subsections[subsection-1].name + " the subsection you intended? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userConf != 'y'){     //Re-run the above if the user made a mistake (stupid little user)
            System.out.print("Please enter the subsection of the item: ");
            subsection = Methods.checkNumber(validSubsectionIDs[0], validSubsectionIDs[numSubsectionsInSection]);

            while(subsection == -1){
                System.out.print("Invalid selection! Please enter a number between " + validSubsectionIDs[0] + " and " + (validSubsectionIDs[0]+numSubsectionsInSection-1) + ": ");
                subsection = Methods.checkNumber(validSubsectionIDs[0], validSubsectionIDs[numSubsectionsInSection]);
            }

            if(subsection < validSubsectionIDs[validSubsectionIDs.length-1]){
                System.out.println();
                for(int i = 0; i < numSubsectionsInSection; i++){
                    System.out.println(subsectionsInSection[i].ID + ". " + subsectionsInSection[i].name + " - " + subsectionsInSection[i].description);
                }
                System.out.println();
                System.out.print("Please enter the subsection of the item: ");
                subsection = Methods.checkNumber(validSubsectionIDs[0], validSubsectionIDs[numSubsectionsInSection]);

                while(subsection == -1){
                    System.out.print("Invalid selection! Please enter a number between " + validSubsectionIDs[0] + " and " + validSubsectionIDs[validSubsectionIDs.length-1] + ": ");
                    subsection = Methods.checkNumber(validSubsectionIDs[0], validSubsectionIDs[numSubsectionsInSection]);
                }
            }
            System.out.print("Is " + subsections[subsection-1].name + " the subsection you intended? y/n: ");
            userConf = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        System.out.print("Please enter the name of the item: ");
        name = user.nextLine();
        while(name.trim().equals("")){  //Check to make sure the user didn't enter a blank name
            System.out.print("You entered a blank name! Please enter the name of the item: ");
            name = user.nextLine();
        }
        System.out.print("Is '" + name + "' the name you intended? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userConf != 'y'){
            System.out.print("Please enter the name of the item: ");
            name = user.nextLine();
            System.out.print("Is " + name + " the name you intended? y/n: ");
            userConf = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        System.out.print("Please enter the dollar price of the item: ");
        dollar = Methods.checkNumber(0, 2147483647);    //Max price is the max int value
        while(dollar < 0){      //See Methods.checkNumber (if you hold control (command on Mac) and click on checkNumber it will take you there)
            System.out.print("Invalid selection! Please enter a number between 0 and 2147483647: ");
            dollar = Methods.checkNumber(0, 2147483647);
        }
        System.out.print("Is " + dollar + " the dollar price you intended? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userConf != 'y'){
            System.out.print("Please enter the dollar price of the item: ");
            dollar = Methods.checkNumber(0, 2147483647);
            while(dollar < 0){
                System.out.print("Invalid selection! Please enter a number between 0 and 2147483647: ");
                dollar = Methods.checkNumber(0, 2147483647);
            }
            System.out.print("Is " + dollar + " the dollar price you intended? y/n: ");
            userConf = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        System.out.print("Please enter the cent price of the item: ");
        cent = Methods.checkNumber(0, 99);
        while(cent < 0){
            System.out.print("Invalid selection! Please enter a number between 0 and 99: ");
            cent = Methods.checkNumber(0, 99);
        }
        System.out.print("Is " + cent + " the cent price you intended? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userConf != 'y'){
            System.out.print("Please enter the cent price of the item: ");
            cent = Methods.checkNumber(0, 99);
            while(cent < 0){
                System.out.print("Invalid selection! Please enter a number between 0 and 99: ");
                cent = Methods.checkNumber(0, 99);
            }
            System.out.print("Is " + cent + " the cent price you intended? y/n: ");
            userConf = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        System.out.print("Please enter the current stock of the item: ");
        stock = Methods.checkNumber(0, 2147483647);
        while(stock < 0){
            System.out.print("Invalid selection! Please enter a number between 0 and 2147483647: ");
            stock = Methods.checkNumber(0, 2147483647);
        }
        System.out.print("Is " + stock + " the stock you intended? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userConf != 'y'){
            System.out.print("Please enter the current stock of the item: ");
            stock = Methods.checkNumber(0, 2147483647);
            while(stock < 0){
                System.out.print("Invalid selection! Please enter a number between 0 and 2147483647: ");
                stock = Methods.checkNumber(0, 2147483647);
            }
            System.out.print("Is " + stock + " the stock you intended? y/n: ");
            userConf = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        System.out.println("The following data will be written to `items`:");
        System.out.println(" Name:\t\t" + name);
        System.out.println(" Section:\t" + section);
        System.out.println(" Subsection:\t" + subsection);
        System.out.println(" Price:\t\t$" + dollar + "." + cent);
        System.out.println(" Stock:\t\t" + stock);
        System.out.print("Continue? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        
        if(userConf == 'y'){
            String toWriteItems = "";           //No use declaring the
            String toWriteDescriptions = "";    //variable above if it's
            String toWriteKeywords = "";        //never going to be used.
            
            File itemsFile = new File("items");
            Scanner itemScanner = new Scanner(itemsFile);
            itemScanner.useDelimiter(";;");
            
            ReadFiles.readItems();      //Update from the file (array of items, number of items, etc.)
            
            //Most of this is exactly the same as above, jsut with different variables. See changeUsername()/changePassword()/changeLevel() for info.
            toWriteItems += ((numItems + 1) + ";;" + "\n");
            for(int i = 0; i < numItems; i++){
                toWriteItems += (items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;" + "\n");
            }
            toWriteItems += (name + ";;" + (numItems+1) + ";;" + section + ";;" + subsection + ";;" + dollar + ";;" + cent + ";;" + stock + ";;" + "\n");
            
            System.out.print("Please enter the description of the item, on one line: ");
            description = user.nextLine();
            System.out.print("Please enter a short description for the item: ");
            shortDescription = user.nextLine();
            while(shortDescription.length() > 100){
                System.out.print("Invalid! Please enter a short description (less than 100 chars): ");
                shortDescription = user.nextLine();
            }
            
            ReadFiles.readDescriptions();
            
            toWriteDescriptions += ((numItems + 1) + ";;" + "\n");
            for(int i = 0; i < numItems; i++){
                toWriteDescriptions += (descriptions[i].ID + ";;" + descriptions[i].description + ";;" + descriptions[i].shortDescription + ";;" + "\n");
            }
            toWriteDescriptions += ((numItems + 1) + ";;" + description + ";;" + shortDescription + ";;" + "\n");
            
            System.out.print("Please enter the keywords of the item, separated by two semicolons (;;),\nincluding after the final keyword: ");
            keywordsString = user.nextLine();
            
            ReadFiles.readKeywords();
            
            toWriteKeywords += ((numItems + 1) + ";;" + "\n");
            for(int i = 0; i < numItems; i++){
                toWriteKeywords += (keywords[i].ID + ";;" + keywords[i].numKeywords + ";;");
                    for(int x = 0; x < keywords[i].keywords.length; x++){
                        toWriteKeywords += (keywords[i].keywords[x] + ";;");
                    }
                    toWriteKeywords += "\n";
            }
            toWriteKeywords += ((numItems + 1) + ";;" + numKeywords + ";;" + keywordsString + "\n");
            
            writeFile("items", toWriteItems);
            writeFile("descriptions", toWriteDescriptions);
            writeFile("keywords", toWriteKeywords);
        } else {
            System.out.println("Abort.");
        }
        
        //Update the files again. (I really can't do this enough)
        ReadFiles.readItems();
        ReadFiles.readDescriptions();
        ReadFiles.readKeywords();
    }
    
    public static void removeItem() throws IOException {
        //Variable declaration
        int userChoice;
        String toWrite = "";
        
        ReadFiles.readItems();  //Update from the file
        
        //Get the item to remove from the user
        System.out.print("Which item would you like to remove? 0 to display items: ");
        userChoice = Methods.checkNumber(0, numItems);
        while(userChoice == -1){
            System.out.print("Invalid selection! Please enter a number between 0 and " + numItems + ": ");
            userChoice = Methods.checkNumber(0, numItems);
        }
        if(userChoice == 0){
            for(int i = 0; i < numItems; i++){  //Cycle through and print out the items
                System.out.println(items[i].ID + ". " + items[i].name);
            }
            System.out.print("Which item would you like to remove? ");
            userChoice = Methods.checkNumber(1, numItems);
        }
        while(userChoice < 1 || userChoice > items.length){
            System.out.print("Invalid selection! Please enter a number between 1 and " + numItems + ": ");
            userChoice = Methods.checkNumber(1, numItems);
        }
        
        toWrite += (numItems + ";;" + "\n");
        for(int i = 0; i < numItems; i++){
            if(i == userChoice -1){
                toWrite += (items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + "-1" + ";;" + "\n");
            } else {
                toWrite += (items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;" + "\n");
            }
        }
        writeFile("items", toWrite);
        System.out.println("Item removed.");
    }
    
    public static void changeStock() throws IOException {
        //Variable declaration
        int userChoice = 0;
        int stock;
        int ID;
        String toWrite = "";

        //Update items from file
        ReadFiles.readItems();
        
        //Get the item who's stock is going to change from the user
        System.out.print("Which item would you like to change the stock of? 0 to display items: ");
        userChoice = Methods.checkNumber(0, numItems);
        while(userChoice == -1){
            System.out.print("Invalid selection! Please enter a number between 0 and " + numItems + ": ");
            userChoice = Methods.checkNumber(0, numItems);
        }
        if(userChoice == 0){
            for(int i = 0; i < numItems; i++){  //Cycle through and print out the items
                if(items[i].stock != -1)System.out.println(items[i].ID + ". " + items[i].name);
            }
            System.out.print("Which item would you like to change the stock of? ");
            userChoice = Methods.checkNumber(1, numItems);
        }
        while(userChoice < 1 || userChoice > items.length){
            System.out.print("Invalid selection! Please enter a number between 1 and " + numItems + ": ");
            userChoice = Methods.checkNumber(1, numItems);
        }
            
        ID = userChoice - 1;
        
        while(items[ID].stock == -1){   //Repeat above.
            System.out.println("That item has been removed!");
            System.out.print("Which item would you like to change the stock of? 0 to display items: ");
            userChoice = Methods.checkNumber(0, numItems);
            while(userChoice == -1){
                System.out.print("Invalid selection! Please enter a number between 0 and " + numItems + ": ");
                userChoice = Methods.checkNumber(0, numItems);
            }
            if(userChoice == 0){
                for(int i = 0; i < numItems; i++){
                    if(items[i].stock != -1)System.out.println(items[i].ID + ". " + items[i].name);
                }
                System.out.print("Which item would you like to change the stock of? ");
                userChoice = Methods.checkNumber(1, numItems);
            }
            while(userChoice < 1 || userChoice > items.length){
                System.out.print("Invalid selection! Please enter a number between 1 and " + numItems + ": ");
                userChoice = Methods.checkNumber(1, numItems);
            }
        }
        //Get the new stock (must be greater than the current stock)
        System.out.print("To what would you like to change the stock to? Current stock is " + items[ID].stock + ": ");
        stock = Methods.checkNumber(items[ID].stock, 2147483647);
        while(stock == -1){
            System.out.print("Invalid selection! The new stock must be equal to or greater than the current stock: ");
            stock = Methods.checkNumber(items[ID].stock, 2147483647);
        }

        toWrite += (numItems + ";;" + "\n");
        for(int i = 0; i < numItems; i++){
            if(i == userChoice - 1){
                toWrite += (items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + stock + ";;" + "\n");
            } else {
                toWrite += (items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;" + "\n");
            }
        }
        
        writeFile("items", toWrite);
        
        System.out.println("Item stock changed.");
        
    }
    
    public static void addSection() throws IOException {
        //Variable declaration
        String section;
        String subsection;
        String description;
        String toWrite = "";
        int addChoice;
        int sectionID;      //Parent of the subsection
        char userEntry;
        
        //Determine whether the user wants a new section or subsection
        System.out.print("Would you like to add a section (1) or a subsection (2)? ");
        addChoice = Methods.checkNumber(1, 2);
        while(addChoice == -1){
            System.out.print("Invalid selection! Please enter either 1 or 2: ");
            addChoice = Methods.checkNumber(1, 2);
        }
        
        if(addChoice == 1){
            System.out.print("Please enter the desired name for the new section: ");
            section = user.nextLine();

            System.out.print("Is " + section + " the intended section name? y/n: ");
            userEntry = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
            while(userEntry != 'y'){
                System.out.print("Please enter the desired name for the new section: ");
                section = user.nextLine();
                System.out.print("Is " + section + " the intended section name? y/n: ");
                userEntry = user.next().trim().toLowerCase().charAt(0);
                user.nextLine();
            }
            
            System.out.println("Please enter the description for " + section + ": ");
            description = user.nextLine();
            
            toWrite += ((numSections + 1) + ";;" + "\n");
            for(int i = 0; i < numSections; i++){
                toWrite += (sections[i].ID + ";;" + sections[i].name + ";;" + sections[i].description + ";;" + "\n");
            }
            toWrite += ((numSections+1) + ";;" + section + ";;" + description + ";;" + "\n");
            
            writeFile("sections", toWrite);
            
            System.out.println("Section created.");
            ReadFiles.readSections();   //Update sections from file (required after making a new section)
        } else {
            System.out.print("Please enter the ID of the parent section (0 to view sections): ");
            sectionID = Methods.checkNumber(0, numSections);
            
            while(sectionID == -1){
                System.out.print("Invalid selection! Please enter a number between 0 and " + numSections + ": ");
                sectionID = Methods.checkNumber(0, numSections);
            }
            
            if(sectionID == 0){
                System.out.println();
                for(int i = 0; i < numSections; i++){   //Output the sections
                    System.out.println(sections[i].ID + ". " + sections[i].name + " - " + sections[i].description);
                }
                System.out.println();
                System.out.print("Please enter the ID of the parent section: ");
                sectionID = Methods.checkNumber(1, numSections);

                while(sectionID == -1){
                    System.out.print("Invalid selection! Please enter a number between 1 and " + numSections + ": ");
                    sectionID = Methods.checkNumber(1, numSections);
                }
            }
            
            System.out.println("Adding subsection to " + sections[sectionID-1].name + ".");
            
            System.out.print("Please enter the desired name for the new subsection: ");
            subsection = user.nextLine();

            System.out.print("Is " + subsection + " the intended subsection name? y/n: ");
            userEntry = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
            while(userEntry != 'y'){
                System.out.print("Please enter the desired name for the new subsection: ");
                subsection = user.nextLine();
                System.out.print("Is " + subsection + " the intended subsection name? y/n: ");
                userEntry = user.next().trim().toLowerCase().charAt(0);
                user.nextLine();
            }
            
            System.out.print("Please enter the description for " + subsection + ": ");
            description = user.nextLine();
            
            toWrite += ((numSubsections + 1) + ";;" + "\n");
            for(int i = 0; i < numSubsections; i++){
                toWrite += (subsections[i].ID + ";;" + subsections[i].name + ";;" + subsections[i].description + ";;" + subsections[i].sectionID + ";;" + "\n");
            }
            toWrite += ((numSubsections+1) + ";;" + subsection + ";;" + description + ";;" + sectionID + ";;" + "\n");
            
            writeFile("subsections", toWrite);
            
            System.out.println("Subsection created.");
            ReadFiles.readSubsections();    //Update subsections from file (required after making a new subsection)
        }
    }
    
    static void menu(int userID, int userLevel) throws IOException{
        //Variable declaration
        int menuSelection = 0;
        int numUsers;
        File authentication;
        exit = false;
        
        while(!exit){
            //Update all of the files
            ReadFiles.readDescriptions();
            ReadFiles.readItems();
            ReadFiles.readKeywords();
            ReadFiles.readSections();
            ReadFiles.readSubsections();
            
            //Scan the authentication file      (maybe should be switched to ReadFiles() at some point)
            authentication = new File("authentication");
            Scanner auth = new Scanner(authentication);
            auth.useDelimiter(";;");

            numLogins = auth.nextInt();
            auth.nextLine();
            logins = new EmployeeLogin[numLogins];
            numUsers = 0;
            while(auth.hasNext()){
                logins[numUsers] = new EmployeeLogin(auth.nextInt(), auth.next(),auth.next(),auth.nextInt());
                auth.nextLine();
                numUsers++;
            }
            
            switch(userLevel){      //Find out what the user wants to do, based on their level
                case 1:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nMenu:");
                    System.out.println(" 1. User settings");
                    System.out.println(" 2. Increase stock of item");
                    System.out.println(" 3. Exit");
                    System.out.print("Please enter your choice: ");
                    menuSelection = Methods.checkNumber(1, 3);
                    while(menuSelection == -1){
                        System.out.print("Invalid selection! Please enter a number between 1 and 3: ");
                        menuSelection = Methods.checkNumber(1, 3);
                    }
                    if(menuSelection == 1) userSettings(userLevel, userID);
                    else if(menuSelection == 2) changeStock();
                    else if(menuSelection == 3){
                        exit = true;
                        restart = false;
                    }
                    break;
                case 2:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nMenu:");
                    System.out.println(" 1. User settings");
                    System.out.println(" 2. Add item to stock");
                    System.out.println(" 3. Add section/subsection");
                    System.out.println(" 4. Increase stock of item");
                    System.out.println(" 5. Exit");
                    System.out.print("Please enter your choice: ");
                    menuSelection = Methods.checkNumber(1, 5);
                    while(menuSelection == -1){
                        System.out.print("Invalid selection! Please enter a number between 1 and 5: ");
                        menuSelection = Methods.checkNumber(1, 5);
                    }
                    if(menuSelection == 1) userSettings(userLevel, userID);
                    else if(menuSelection == 2) addItem();
                    else if(menuSelection == 3) addSection();
                    else if(menuSelection == 4) changeStock();
                    else if(menuSelection == 5){
                        exit = true;
                        restart = false;
                    }
                    break;
                case 3: case 4: case 5: case 6:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nMenu:");
                    System.out.println(" 1. User settings");
                    System.out.println(" 2. Add item to stock");
                    System.out.println(" 3. Add section/subsection");
                    System.out.println(" 4. Remove item from stock");
                    System.out.println(" 5. Increase stock of item");
                    System.out.println(" 6. Exit");
                    System.out.print("Please enter your choice: ");
                    menuSelection = Methods.checkNumber(1, 6);
                    while(menuSelection == -1){
                        System.out.print("Invalid selection! Please enter a number between 1 and 6: ");
                        menuSelection = Methods.checkNumber(1, 6);
                    }
                    if(menuSelection == 1) userSettings(userLevel, userID);
                    else if(menuSelection == 2) addItem();
                    else if(menuSelection == 3) addSection();
                    else if(menuSelection == 4) removeItem();
                    else if(menuSelection == 5) changeStock();
                    else if(menuSelection == 6) {
                        exit = true;
                        restart = false;
                    }
                    break;
            }
        }
    }
    
    public static void Employees() throws IOException {
        //Variable declaration
        String username;
        String password;
        int menuSelection;
        int userLevel = 0;      //1: Restocking, 2: Add new items/sections, 3: Remove items, 4: Add users, 5: Remove users, 6: Change usernames/passwords/levels
        int userID = 0;
        boolean usernameFound = false;
        
        while(restart){
            //Update authentication data
            ReadFiles.readAuthentication();
            
            System.out.print("Please enter your username: ");
            username = user.next();
            user.nextLine();

            System.out.print("Please enter your password: ");
            password = user.nextLine();
            
            //Search through login data for username/password
            for(int i = 0; i < numLogins; i++){
                if(logins[i].username.equals(username)){
                    usernameFound = true;
                    userID = logins[i].ID;
                    userLevel = logins[i].level;
                    if(!logins[i].password.equals(password)){
                        System.err.println("Incorrect password, exiting.");
                        System.exit(0);
                    }
                }
            }
            
            if(!usernameFound){
                System.err.println("Invalid username, exiting.");
                System.exit(0);
            } else {
                menu(userID, userLevel);
            }
        }
    }
}