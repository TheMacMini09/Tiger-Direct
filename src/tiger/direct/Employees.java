/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiger.direct;

import tiger.direct.TigerDirect;
import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import static tiger.direct.TigerDirect.*;

/**
 *
 * @author michael
 */
public class Employees {
    
    public static int numItems;
    public static int numLogins;
    public static int numSections;
    public static int numSubsections;
    static boolean exit = false;
    static boolean restart = true;
    static EmployeeLogin[] logins;
    
    public static void writeFile(String name, String toWrite) throws FileNotFoundException{
        PrintWriter file = new PrintWriter(new File(name));
        
        file.print(toWrite);
        file.close();
    }
    
    public static boolean checkUsername(String username) {
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
        int position = ID-1;
        char correct;
        
        System.out.println("Editing username for " + logins[position].username + ".");
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
        while(correct != 'y'){
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
        
        toWrite += (numLogins + ";;" + "\n");
        
        for(int i = 0; i < numLogins; i++){
            if(i == position){
                toWrite += ((i+1) + ";;" + username + ";;" + logins[position].password + ";;" + logins[position].level + ";;" + "\n");
            } else {
                toWrite += (logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;" + "\n");
            }
        }
        writeFile("authentication", toWrite);
        System.out.println("Username changed. Please log back in.");
        exit = true;
        restart = true;
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
        toWrite += (numLogins + ";;" + "\n");
        for(int i = 0; i < numLogins; i++){
            if(i == position){
                toWrite += ((i+1) + ";;" + logins[position].username + ";;" + password + ";;" + logins[position].level + ";;" + "\n");
            } else {
                toWrite += (logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;" + "\n");
            }
        }
        writeFile("authentication", toWrite);
        System.out.println("Password changed. Please log back in.");
        exit = true;
        restart = true;
    }
    
    public static void addUser(int userLevel) throws FileNotFoundException{
        //Variable declaration
        String username;
        String password;
        String toWrite = "";
        int level;
        char userEntry;
        
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
        level = Methods.checkNumber(1, userLevel);
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
        toWrite += ((numLogins + 1) + ";;" + "\n");
        for(int i = 0; i < numLogins; i++){
            toWrite += (logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;" + "\n");
        }
        toWrite += (numLogins+1 + ";;" + username + ";;" + password + ";;" + level + ";;" + "\n");
        writeFile("authentication", toWrite);
        System.out.println("User created.");
    }
    
    public static void removeUser(int ownID) throws FileNotFoundException{
        //Variable declaration
        int userID;
        int position;
        
        System.out.print("Which user ID would you like to remove? # or 0 to display users: ");
        userID = Methods.checkNumber(0, logins.length);
        if(userID == 0){
            for(int i = 0; i < numLogins; i++){
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
        PrintWriter file = new PrintWriter(new File("authentication"));  //Also overwrites file.
        file.println(logins.length-1 + ";;");
        for(int i = 0; i < logins.length; i++){
            if(i > position-1){
                file.println(logins[i].ID-1 + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[position].level + ";;");
            } else if(i == position-1){
                //Do nothing
            } else {
                file.println(logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;");
            }
        }
        file.close();
        System.out.print("User removed.");
        if(userID == ownID){
            System.out.println(" Please log in as a new user.");
            exit = true;
            restart = true;
        } else {
            System.out.println();
            exit = false;
        }
    }
    
    public static void changeLevel(int userID) throws FileNotFoundException {
        //Variable declaration
        int level;
        
        System.out.print("To what level would you like to change " + logins[userID-1].username + " to?\nTheir current level is " + logins[userID-1].level + ": ");
        level = Methods.checkNumber(1, 6);
        while(level == -1){
            System.out.print("Invalid selection! Please choose a new level for " + logins[userID-1].username + ": ");
            level = Methods.checkNumber(1, 6);
        }
        PrintWriter file = new PrintWriter(new File("authentication"));
        file.println((logins.length + 1) + ";;");
        for(int i = 0; i < logins.length; i++){
            if(logins[i].ID == userID){
                file.println(logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + level + ";;");
            } else {
                file.println(logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;");
            }
        }
        file.close();
        System.out.println("Level changed. Please log back in.");
        exit = true;
        restart = true;
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
        int ID;
        int section;
        int subsection;
        int dollar;
        int cent;
        int stock;
        int numItems;
        int numKeywords = 0;
        int numSubsectionsInSection = 0;
        char userConf;
        
        //Array declarations
        SubSections[] subsectionsInSection = new SubSections[subsections.length];
        int[] validSubsectionIDs = new int[subsections.length];
        
        System.out.print("Please enter the section of the item, or 0 to view sections: ");
        section = Methods.checkNumber(0, numSections);

        while(section == -1){
            System.out.print("Invalid selection! Please enter a number between 0 and " + numSections + ": ");
            section = Methods.checkNumber(0, numSections);
        }

        if(section == 0){
            System.out.println();
            for(int i = 0; i < numSections; i++){
                System.out.println(sections[i].ID + ". " + sections[i].name + " - " + sections[i].description);
            }
            System.out.println();
            System.out.print("Please enter the section of the item: ");
            section = Methods.checkNumber(1, numSections);

            while(section == -1){
                System.out.print("Invalid selection! Please enter a number between 1 and " + numSections + ": ");
                section = Methods.checkNumber(1, numSections);
            }
        }
        
        System.out.print("Is " + sections[section].name + " the section you intended? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userConf != 'y'){
            System.out.print("Please enter the section of the item: ");
            section = Methods.checkNumber(1, numSections);

            while(section == -1){
                System.out.print("Invalid selection! Please enter a number between 1 and " + numSections + ": ");
                section = Methods.checkNumber(1, numSections);
            }
            System.out.print("Is " + sections[section].name + " the section you intended? y/n: ");
            userConf = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        for(int i = 0; i < numSubsections; i++){
            if(subsections[i].sectionID == section){
                subsectionsInSection[numSubsectionsInSection] = subsections[i];
                validSubsectionIDs[i] = subsections[i].ID;
                numSubsectionsInSection++;
            }
        }
        
        Arrays.sort(validSubsectionIDs);
        
        System.out.print("Please enter the subsection of the item, or 0 to view subsections: ");
        subsection = Methods.checkNumber(0, validSubsectionIDs[validSubsectionIDs.length-1]);

        while(section == -1){
            System.out.print("Invalid selection! Please enter a number between " + validSubsectionIDs[0] + " and " + validSubsectionIDs[validSubsectionIDs.length-1] + ": ");
            subsection = Methods.checkNumber(validSubsectionIDs[0], validSubsectionIDs[validSubsectionIDs.length-1]);
        }

        if(subsection == 0){
            System.out.println();
            for(int i = 0; i < numSubsectionsInSection; i++){
                System.out.println(subsectionsInSection[i].ID + ". " + subsectionsInSection[i].name + " - " + subsectionsInSection[i].description);
            }
            System.out.println();
            System.out.print("Please enter the subsection of the item: ");
            subsection = Methods.checkNumber(validSubsectionIDs[0], validSubsectionIDs[validSubsectionIDs.length-1]);

            while(section == -1 || Arrays.binarySearch(validSubsectionIDs, subsection) < 0){
                System.out.print("Invalid selection! Please enter a number between " + validSubsectionIDs[0] + " and " + validSubsectionIDs[validSubsectionIDs.length-1] + ": ");
                subsection = Methods.checkNumber(validSubsectionIDs[0], validSubsectionIDs[validSubsectionIDs.length-1]);
            }
        }
        
        System.out.print("Is " + subsections[subsection-1].name + " the subsection you intended? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userConf != 'y'){
            System.out.print("Please enter the subsection of the item, or 0 to view subsections: ");
            subsection = Methods.checkNumber(0, validSubsectionIDs[validSubsectionIDs.length-1]);

            while(section == -1){
                System.out.print("Invalid selection! Please enter a number between " + validSubsectionIDs[0] + " and " + validSubsectionIDs[validSubsectionIDs.length-1] + ": ");
                subsection = Methods.checkNumber(validSubsectionIDs[0], validSubsectionIDs[validSubsectionIDs.length-1]);
            }

            if(subsection == 0){
                System.out.println();
                for(int i = 0; i < numSubsectionsInSection; i++){
                    System.out.println(subsectionsInSection[i].ID + ". " + subsectionsInSection[i].name + " - " + subsectionsInSection[i].description);
                }
                System.out.println();
                System.out.print("Please enter the subsection of the item: ");
                section = Methods.checkNumber(validSubsectionIDs[0], validSubsectionIDs[validSubsectionIDs.length-1]);

                while(subsection == -1 || Arrays.binarySearch(validSubsectionIDs, subsection) < 0){
                    System.out.print("Invalid selection! Please enter a number between " + validSubsectionIDs[0] + " and " + validSubsectionIDs[validSubsectionIDs.length-1] + ": ");
                    subsection = Methods.checkNumber(validSubsectionIDs[0], validSubsectionIDs[validSubsectionIDs.length-1]);
                }
            }
            System.out.print("Is " + subsections[subsection-1].name + " the subsection you intended? y/n: ");
            userConf = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        System.out.print("Please enter the name of the item: ");
        name = user.nextLine();
        System.out.print("Is " + name + " the name you intended? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userConf != 'y'){
            System.out.print("Please enter the name of the item: ");
            name = user.next();
            user.nextLine();
            System.out.print("Is " + name + " the name you intended? y/n: ");
            userConf = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        System.out.print("Please enter the dollar price of the item: ");
        dollar = user.nextInt();
        user.nextLine();
        System.out.print("Is " + dollar + " the dollar price you intended? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userConf != 'y'){
            System.out.print("Please enter the dollar price of the item: ");
            dollar = user.nextInt();
            user.nextLine();
            System.out.print("Is " + dollar + " the dollar price you intended? y/n: ");
            userConf = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        System.out.print("Please enter the cent price of the item: ");
        cent = user.nextInt();
        user.nextLine();
        System.out.print("Is " + cent + " the cent price you intended? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userConf != 'y'){
            System.out.print("Please enter the cent price of the item: ");
            cent = user.nextInt();
            user.nextLine();
            System.out.print("Is " + cent + " the cent price you intended? y/n: ");
            userConf = user.next().trim().toLowerCase().charAt(0);
            user.nextLine();
        }
        
        System.out.print("Please enter the current stock of the item: ");
        stock = user.nextInt();
        user.nextLine();
        System.out.print("Is " + stock + " the stock you intended? y/n: ");
        userConf = user.next().trim().toLowerCase().charAt(0);
        user.nextLine();
        while(userConf != 'y'){
            System.out.print("Please enter the current stock of the item: ");
            stock = user.nextInt();
            user.nextLine();
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
            File itemsFile = new File("items");
            Scanner itemScanner = new Scanner(itemsFile);
            itemScanner.useDelimiter(";;");
            
            numItems = itemScanner.nextInt();
            itemScanner.nextLine();
            ID = numItems + 1;
            for(int i = 0; i < numItems; i++){
                items[i] = new Items(itemScanner.next(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt());
                itemScanner.nextLine();
            }
            
            PrintWriter file = new PrintWriter(itemsFile);
            file.println((numItems + 1) + ";;");
            for(int i = 0; i < numItems; i++){
                file.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;");
            }
            file.println(name + ";;" + ID + ";;" + section + ";;" + subsection + ";;" + dollar + ";;" + cent + ";;" + stock + ";;");
            file.close();
            
            System.out.print("Please enter the description of the item, on one line: ");
            description = user.nextLine();
            System.out.print("Please enter a short description for the item: ");
            shortDescription = user.nextLine();
            while(shortDescription.length() > 100){
                System.out.print("Invalid! Please enter a short description (less than 100 chars): ");
                shortDescription = user.nextLine();
            }
            
            ReadFiles.readDescriptions();
            File descriptionsFile = new File("descriptions");
            Scanner descriptionScanner = new Scanner(descriptionsFile);
            PrintWriter descriptionsWriter = new PrintWriter(descriptionsFile);
            
            descriptionsWriter.println((numItems + 1) + ";;");
            for(int i = 0; i < numItems; i++){
                descriptionsWriter.println(descriptions[i].ID + ";;" + descriptions[i].description + ";;" + descriptions[i].shortDescription + ";;");
            }
            descriptionsWriter.println((numItems + 1) + ";;" + description + ";;" + shortDescription + ";;");
            descriptionsWriter.close();
            
            System.out.print("Please enter the keywords of the item, separated by two semicolons (;;): ");
            keywordsString = user.nextLine();
            
            ReadFiles.readKeywords();
            File keywordsFile = new File("keywords");
            Scanner keywordScanner = new Scanner(keywordsFile);
            Scanner userKeywordScanner = new Scanner(keywordsString);
            PrintWriter keywordsWriter = new PrintWriter(keywordsFile);
            
            userKeywordScanner.useDelimiter(";;");
            while(userKeywordScanner.hasNext()){
                numKeywords++;
                userKeywordScanner.next();
            }
            
            keywordsWriter.println((numItems + 1) + ";;");
            for(int i = 0; i < numItems; i++){
                keywordsWriter.println(keywords[i].ID + ";;" + keywords[i].numKeywords + ";;" + keywords[i].keywords);
            }
            keywordsWriter.println((numItems + 1) + ";;" + numKeywords + ";;" + keywordsString);
            keywordsWriter.close();
        }
        ReadFiles.readItems();
        ReadFiles.readDescriptions();
        ReadFiles.readKeywords();
    }
    
    public static void removeItem() throws IOException {
        //Variable declaration
        int userChoice;
        int numItems;

        File itemsFile = new File("items");
        Scanner itemScanner = new Scanner(itemsFile);
        itemScanner.useDelimiter(";;");

        numItems = itemScanner.nextInt();
        itemScanner.nextLine();
        for(int i = 0; i < numItems; i++){
            items[i] = new Items(itemScanner.next(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt());
            itemScanner.nextLine();
        }
        
        System.out.print("Which item would you like to remove? 0 to display items: ");
        userChoice = Methods.checkNumber(00, numItems);
        while(userChoice == -1){
            System.out.print("Invalid selection! Please enter a number between 0 and " + numItems + ": ");
            userChoice = Methods.checkNumber(0, numItems);
        }
        if(userChoice == 0){
            for(int i = 0; i < numItems; i++){
                System.out.println(items[i].ID + ". " + items[i].name);
            }
            System.out.print("Which item would you like to remove? ");
            userChoice = Methods.checkNumber(1, numItems);
        }
        while(userChoice < 1 || userChoice > items.length){
            System.out.print("Invalid selection! Please enter a number between 1 and " + numItems + ": ");
            userChoice = Methods.checkNumber(1, numItems);
        }
        
        PrintWriter file = new PrintWriter(itemsFile);
        file.println(numItems + ";;");
        for(int i = 0; i < numItems; i++){
            if(i == userChoice -1){
                file.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + "-1" + ";;");
            } else {
                file.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;");
            }
        }
        file.close();
        System.out.println("Item removed.");
    }
    
    public static void changeStock() throws IOException {
        //Variable declaration
        int userChoice = 0;
        int numItems;
        int stock;
        int ID;

        File itemsFile = new File("items");
        Scanner itemScanner = new Scanner(itemsFile);
        itemScanner.useDelimiter(";;");

        numItems = itemScanner.nextInt();
        itemScanner.nextLine();
        for(int i = 0; i < numItems; i++){
            items[i] = new Items(itemScanner.next(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt());
            itemScanner.nextLine();
        }
        
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
            
        ID = userChoice - 1;
        
        while(items[ID].stock == -1){
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
        System.out.print("To what would you like to change the stock to? Current stock is " + items[ID].stock + ": ");
        stock = Methods.checkNumber(items[ID].stock, 2147483647);
        while(stock == -1){
            System.out.print("Invalid selection! The new stock must be equal to or greater than the current stock: ");
            stock = Methods.checkNumber(items[ID].stock, 2147483647);
        }

        PrintWriter file = new PrintWriter(itemsFile);
        file.println(numItems + ";;");
        for(int i = 0; i < numItems; i++){
            if(i == userChoice - 1){
                file.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + stock + ";;");
            } else {
                file.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;");
            }
        }
        file.close();
        System.out.println("Item stock changed.");
        
    }
    
    public static void addSection() throws IOException {
        //Variable declaration
        String section;
        String subsection;
        String description;
        int addChoice;
        int sectionID;      //Parent of the subsection
        char userEntry;
        
        System.out.print("Would you like to add a section (1) or a subsection (2)? ");
        addChoice = Methods.checkNumber(1, 2);
        while(addChoice == -1){
            System.out.print("Invalid selection! Please enter 1 or 2: ");
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
            
            PrintWriter file = new PrintWriter(new File("sections")); //Also overwrites file.
            file.println((numSections + 1) + ";;");
            for(int i = 0; i < numSections; i++){
                file.println(sections[i].ID + ";;" + sections[i].name + ";;" + sections[i].description + ";;");
            }
            file.println((numSections+1) + ";;" + section + ";;" + description + ";;");
            file.close();
            System.out.println("Section created.");
            ReadFiles.readSections();
        } else {
            System.out.print("Please enter the ID of the parent section (0 to view sections): ");
            sectionID = Methods.checkNumber(0, numSections);
            
            while(sectionID == -1){
                System.out.print("Invalid selection! Please enter a number between 0 and " + numSections + ": ");
                sectionID = Methods.checkNumber(0, numSections);
            }
            
            if(sectionID == 0){
                System.out.println();
                for(int i = 0; i < numSections; i++){
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
            
            PrintWriter file = new PrintWriter(new File("subsections")); //Also overwrites file.
            file.println((numSubsections + 1) + ";;");
            for(int i = 0; i < numSubsections; i++){
                file.println(subsections[i].ID + ";;" + subsections[i].name + ";;" + subsections[i].description + ";;" + subsections[i].sectionID + ";;");
            }
            file.println((numSubsections+1) + ";;" + subsection + ";;" + description + ";;" + sectionID + ";;");
            file.close();
            System.out.println("Subsection created.");
            ReadFiles.readSubsections();
        }
    }
    
    static void menu(int userID, int userLevel) throws IOException{
        //Variable declaration
        int menuSelection = 0;
        int numUsers;
        File authentication;
        
        exit = false;
        
        while(!exit){
            ReadFiles.readDescriptions();
            ReadFiles.readItems();
            ReadFiles.readKeywords();
            ReadFiles.readSections();
            ReadFiles.readSubsections();
            
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
            
            switch(userLevel){
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
            ReadFiles.readAuthentication();
            
            System.out.print("Please enter your username: ");
            username = user.next();
            user.nextLine();

            System.out.print("Please enter your password: ");
            password = user.nextLine();

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