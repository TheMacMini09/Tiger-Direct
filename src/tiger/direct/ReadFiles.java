/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import static tiger.direct.Employees.logins;
import static tiger.direct.Employees.numItems;
import static tiger.direct.Employees.numLogins;
import static tiger.direct.Employees.numSections;
import static tiger.direct.Employees.numSubsections;
import static tiger.direct.TigerDirect.descriptions;
import static tiger.direct.TigerDirect.items;
import static tiger.direct.TigerDirect.keywords;
import static tiger.direct.TigerDirect.sections;
import static tiger.direct.TigerDirect.subsections;

/*
 * File by: Michael Barker
 */
public class ReadFiles {
    
    //Most of these are exactly the same. The first one will be commented, and then any subsequent major changes will also be documented.
    
    public static void readItems() throws FileNotFoundException{
        try{    //Try reading the data...
            Scanner itemScanner = new Scanner(new File("items"));   //Setup the `items` scanner so that
            itemScanner.useDelimiter(";;");                        //it uses `;;` as the delimeter
            numItems = itemScanner.nextInt();   //Read the very first element (number of items) into the feild variable `numItems`
            itemScanner.nextLine();     //Move on to the next line (to start reading data)
            for(int i = 0; i < numItems; i++){  //Cycle through the `items` file and add the data to the array of item objects.
                items[i] = new Items(itemScanner.next(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt());
                itemScanner.nextLine();
            }
            PrintWriter file = new PrintWriter(new File("items.bak"));  //Initialize the backup file
            file.println(numItems + ";;");      //Print the # of items to the backup file
            for(int i = 0; i < numItems; i++){      //Cycle through the items and write them to the backup file
                file.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;");
            }
            file.close();   //Write the file
        } catch(Exception e) {  //Catches corruption of any sort (missing data/not enough/too many lines)
            try{    //Trying to restore from the backup...
                System.out.println("File corruption! Restoring backup.");
                Scanner itemScanner = new Scanner(new File("items.bak"));   //Init the `items.back` scanner, and make sure
                itemScanner.useDelimiter(";;");                            //that it uses the `;;` delimeter

                numItems = itemScanner.nextInt();   //Read the # of items into the variable
                itemScanner.nextLine();
                for(int i = 0; i < numItems; i++){  //Read through the items in the backup file, write them into the items array
                    items[i] = new Items(itemScanner.next(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt());
                    itemScanner.nextLine();
                }
                System.out.println("Backup file OK. Overwriting main file.");
                
                //Write the backup data to the main file (same as above)
                PrintWriter file = new PrintWriter(new File("items"));
                file.println(numItems + ";;");
                for(int i = 0; i < numItems; i++){
                    file.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;");
                }
                file.close();   //Write the file
                
                //Write the new file, using the backup data. (same as above)
                System.out.println("Main file restored. Creating new backup file...");
                PrintWriter fileBackup = new PrintWriter(new File("items.bak"));
                fileBackup.println(numItems + ";;");
                for(int i = 0; i < numItems; i++){
                    fileBackup.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;");
                }
                fileBackup.close(); //Write the file
                System.out.println("Backup created. Data should now be correct.");
            } catch(Exception x) {  //Catch backup corruption
                System.out.println("Unrecoverable data corruption. Please try fixing the files manually.");
            }
        }
    }
    
    public static void readAuthentication() throws FileNotFoundException{
        try{
            Scanner itemScanner = new Scanner(new File("authentication"));
            itemScanner.useDelimiter(";;");
            numLogins = itemScanner.nextInt();
            itemScanner.nextLine();
            logins = new EmployeeLogin[numLogins];
            for(int i = 0; i < numLogins; i++){
                logins[i] = new EmployeeLogin(itemScanner.nextInt(), itemScanner.next(), itemScanner.next(), itemScanner.nextInt());
                itemScanner.nextLine();
            }
            PrintWriter file = new PrintWriter(new File("authentication.bak"));
            file.println(numLogins + ";;");
            for(int i = 0; i < numLogins; i++){
                file.println(logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;");
            }
            file.close();
        } catch(Exception e) {
            try{
                System.out.println("File corruption! Restoring backup.");
                Scanner itemScanner = new Scanner(new File("authentication.bak"));
                itemScanner.useDelimiter(";;");

                numLogins = itemScanner.nextInt();
                itemScanner.nextLine();
                for(int i = 0; i < numLogins; i++){
                    logins[i] = new EmployeeLogin(itemScanner.nextInt(), itemScanner.next(), itemScanner.next(), itemScanner.nextInt());
                    itemScanner.nextLine();
                }
                System.out.println("Backup file OK. Overwriting main file.");
                
                PrintWriter file = new PrintWriter(new File("authentication"));
                file.println(numLogins + ";;");
                for(int i = 0; i < numLogins; i++){
                    file.println(logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;");
                }
                file.close();
                
                System.out.println("Main file restored. Creating new backup file...");
                PrintWriter fileBackup = new PrintWriter(new File("authentication.bak"));
                fileBackup.println(numLogins + ";;");
                for(int i = 0; i < numLogins; i++){
                    fileBackup.println(logins[i].ID + ";;" + logins[i].username + ";;" + logins[i].password + ";;" + logins[i].level + ";;");
                }
                fileBackup.close();
                System.out.println("Backup created. Data should now be correct.");
            } catch(Exception x) {
                System.out.println("Unrecoverable data corruption. Please try fixing the files manually.");
            }
        }
    }
    
    public static void readDescriptions() throws FileNotFoundException{
        try{
            Scanner itemScanner = new Scanner(new File("descriptions"));
            itemScanner.useDelimiter(";;");
            numItems = itemScanner.nextInt();   //Won't actually change, since there is one description per item
            itemScanner.nextLine();
            for(int i = 0; i < numItems; i++){
                descriptions[i] = new Descriptions(itemScanner.nextInt(), itemScanner.next(), itemScanner.next());
                itemScanner.nextLine();
            }
            PrintWriter file = new PrintWriter(new File("descriptions.bak"));
            file.println(numItems + ";;");
            for(int i = 0; i < numItems; i++){
                file.println(descriptions[i].ID + ";;" + descriptions[i].description + ";;" + descriptions[i].shortDescription + ";;");
            }
            file.close();
        } catch(Exception e) {
            try{
                System.out.println("File corruption! Restoring backup.");
                Scanner itemScanner = new Scanner(new File("descriptions.bak"));
                itemScanner.useDelimiter(";;");

                numItems = itemScanner.nextInt();
                itemScanner.nextLine();
                for(int i = 0; i < numItems; i++){
                    descriptions[i] = new Descriptions(itemScanner.nextInt(), itemScanner.next(), itemScanner.next());
                    itemScanner.nextLine();
                }
                System.out.println("Backup file OK. Overwriting main file.");
                
                PrintWriter file = new PrintWriter(new File("descriptions"));
                file.println(numItems + ";;");
                for(int i = 0; i < numItems; i++){
                    file.println(descriptions[i].ID + ";;" + descriptions[i].description + ";;" + descriptions[i].shortDescription + ";;");
                }
                file.close();
                
                System.out.println("Main file restored. Creating new backup file...");
                PrintWriter fileBackup = new PrintWriter(new File("descriptions.bak"));
                fileBackup.println(numItems + ";;");
                for(int i = 0; i < numItems; i++){
                    fileBackup.println(descriptions[i].ID + ";;" + descriptions[i].description + ";;" + descriptions[i].shortDescription + ";;");
                }
                fileBackup.close();
                System.out.println("Backup created. Data should now be correct.");
            } catch(Exception x) {
                System.out.println("Unrecoverable data corruption. Please try fixing the files manually.");
            }
        }
    }
    
    public static void readKeywords() throws FileNotFoundException{
        //Variable declaration
        int numKeywords;
        int ID;
        String[] keywordsArray;
        
        try{
            Scanner itemScanner = new Scanner(new File("keywords"));
            itemScanner.useDelimiter(";;");

            numItems = itemScanner.nextInt();
            itemScanner.nextLine();
            for(int i = 0; i < numItems; i++){
                ID = itemScanner.nextInt();             //Grab data into
                numKeywords = itemScanner.nextInt();    //temp variables.
                keywordsArray = new String[numKeywords];    //Create an array exactly as long as the number of keywords
                for(int x = 0; x < numKeywords; x++){
                    keywordsArray[x] = itemScanner.next();  //Read the keywords array from the file into a temp array
                }
                keywords[i] = new Keywords(ID, numKeywords, keywordsArray);     //Add temp variables into the keywords array
                itemScanner.nextLine();
            }
            PrintWriter file = new PrintWriter(new File("keywords.bak"));
            file.println(numItems + ";;");
            for(int i = 0; i < numItems; i++){
                file.print(keywords[i].ID + ";;" + keywords[i].numKeywords + ";;");
                for(int x = 0; x < keywords[i].keywords.length; x++){   //Write keywords to the file
                    file.print(keywords[i].keywords[x] + ";;");
                }
                file.println();
            }
            file.close();
        } catch(Exception e) {
            try{
                //Same as above.
                System.out.println("File corruption! Restoring backup.");
                Scanner itemScanner = new Scanner(new File("keywords.bak"));
                itemScanner.useDelimiter(";;");

                numItems = itemScanner.nextInt();
                itemScanner.nextLine();
                for(int i = 0; i < numItems; i++){
                    ID = itemScanner.nextInt();
                    numKeywords = itemScanner.nextInt();
                    keywordsArray = new String[numKeywords];
                    for(int x = 0; x < numKeywords; x++){
                        keywordsArray[x] = itemScanner.next();
                    }
                    keywords[i] = new Keywords(ID, numKeywords, keywordsArray);
                    itemScanner.nextLine();
                }
                System.out.println("Backup file OK. Overwriting main file.");
                PrintWriter file = new PrintWriter(new File("keywords"));
                file.println(numItems + ";;");
                for(int i = 0; i < numItems; i++){
                    file.print(keywords[i].ID + ";;" + keywords[i].numKeywords + ";;");
                    for(int x = 0; x < keywords[i].keywords.length; x++){
                        file.print(keywords[i].keywords[x] + ";;");
                    }
                    file.println();
                }
                file.close();
                
                System.out.println("Main file restored. Creating new backup file...");
                PrintWriter fileBackup = new PrintWriter(new File("keywords.bak"));
                fileBackup.println(numItems + ";;");
                for(int i = 0; i < numItems; i++){
                    fileBackup.print(keywords[i].ID + ";;" + keywords[i].numKeywords + ";;");
                    for(int x = 0; x < keywords[i].keywords.length; x++){
                        fileBackup.print(keywords[i].keywords[x] + ";;");
                    }
                    fileBackup.println();
                }
                fileBackup.close();
                System.out.println("Backup created. Data should now be correct.");
            } catch(Exception x) {
                System.out.println("Unrecoverable data corruption. Please try fixing the files manually.");
            }
        }
    }
    
    public static void readSections() throws FileNotFoundException{
        try{
            Scanner itemScanner = new Scanner(new File("sections"));
            itemScanner.useDelimiter(";;");
            numSections = itemScanner.nextInt();
            itemScanner.nextLine();
            for(int i = 0; i < numSections; i++){
                sections[i] = new Sections(itemScanner.nextInt(), itemScanner.next(), itemScanner.next());
                itemScanner.nextLine();
            }
            PrintWriter file = new PrintWriter(new File("sections.bak"));
            file.println(numSections + ";;");
            for(int i = 0; i < numSections; i++){
                file.println(sections[i].ID + ";;" + sections[i].name + ";;" + sections[i].description + ";;");
            }
            file.close();
        } catch(Exception e) {
            try{
                System.out.println("File corruption! Restoring backup.");
                Scanner itemScanner = new Scanner(new File("sections.bak"));
                itemScanner.useDelimiter(";;");

                numSections = itemScanner.nextInt();
                itemScanner.nextLine();
                for(int i = 0; i < numSections; i++){
                    sections[i] = new Sections(itemScanner.nextInt(), itemScanner.next(), itemScanner.next());
                    itemScanner.nextLine();
                }
                System.out.println("Backup file OK. Overwriting main file.");
                
                PrintWriter file = new PrintWriter(new File("sections"));
                file.println(numSections + ";;");
                for(int i = 0; i < numSections; i++){
                    file.println(sections[i].ID + ";;" + sections[i].name + ";;" + sections[i].description + ";;");
                }
                file.close();
                
                System.out.println("Main file restored. Creating new backup file...");
                PrintWriter fileBackup = new PrintWriter(new File("sections.bak"));
                fileBackup.println(numSections + ";;");
                for(int i = 0; i < numSections; i++){
                    fileBackup.println(sections[i].ID + ";;" + sections[i].name + ";;" + sections[i].description + ";;");
                }
                fileBackup.close();
                System.out.println("Backup created. Data should now be correct.");
            } catch(Exception x) {
                System.out.println("Unrecoverable data corruption. Please try fixing the files manually.");
            }
        }
    }
    
    public static void readSubsections() throws FileNotFoundException{
        try{
            Scanner itemScanner = new Scanner(new File("subsections"));
            itemScanner.useDelimiter(";;");
            numSubsections = itemScanner.nextInt();
            itemScanner.nextLine();
            for(int i = 0; i < numSubsections; i++){
                subsections[i] = new SubSections(itemScanner.nextInt(), itemScanner.next(), itemScanner.next(), itemScanner.nextInt());
                itemScanner.nextLine();
            }
            PrintWriter file = new PrintWriter(new File("subsections.bak"));
            file.println(numSubsections + ";;");
            for(int i = 0; i < numSubsections; i++){
                file.println(subsections[i].ID + ";;" + subsections[i].name + ";;" + subsections[i].description + ";;" + subsections[i].sectionID + ";;");
            }
            file.close();
        } catch(Exception e) {
            try{
                System.out.println("File corruption! Restoring backup.");
                Scanner itemScanner = new Scanner(new File("subsections.bak"));
                itemScanner.useDelimiter(";;");

                numSubsections = itemScanner.nextInt();
                itemScanner.nextLine();
                for(int i = 0; i < numSubsections; i++){
                    subsections[i] = new SubSections(itemScanner.nextInt(), itemScanner.next(), itemScanner.next(), itemScanner.nextInt());
                    itemScanner.nextLine();
                }
                System.out.println("Backup file OK. Overwriting main file.");
                
                PrintWriter file = new PrintWriter(new File("subsections"));
                file.println(numSubsections + ";;");
                for(int i = 0; i < numSubsections; i++){
                    file.println(subsections[i].ID + ";;" + subsections[i].name + ";;" + subsections[i].description + ";;" + subsections[i].sectionID + ";;");
                }
                file.close();
                
                System.out.println("Main file restored. Creating new backup file...");
                PrintWriter fileBackup = new PrintWriter(new File("subsections.bak"));
                fileBackup.println(numSubsections + ";;");
                for(int i = 0; i < numSubsections; i++){
                    fileBackup.println(subsections[i].ID + ";;" + subsections[i].name + ";;" + subsections[i].description + ";;" + subsections[i].sectionID + ";;");
                }
                fileBackup.close();
                System.out.println("Backup created. Data should now be correct.");
            } catch(Exception x) {
                System.out.println("Unrecoverable data corruption. Please try fixing the files manually.");
            }
        }
    }
}