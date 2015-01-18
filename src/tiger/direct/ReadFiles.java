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
    
    public static void readItems() throws FileNotFoundException{
        try{
            Scanner itemScanner = new Scanner(new File("items"));
            itemScanner.useDelimiter(";;");
            numItems = itemScanner.nextInt();
            itemScanner.nextLine();
            for(int i = 0; i < numItems; i++){
                items[i] = new Items(itemScanner.next(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt());
                itemScanner.nextLine();
            }
            PrintWriter file = new PrintWriter(new File("items.bak"));
            file.println(numItems + ";;");
            for(int i = 0; i < numItems; i++){
                file.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;");
            }
            file.close();
        } catch(Exception e) {
            try{
                System.out.println("File corruption! Restoring backup.");
                Scanner itemScanner = new Scanner(new File("items.bak"));
                itemScanner.useDelimiter(";;");

                numItems = itemScanner.nextInt();
                itemScanner.nextLine();
                for(int i = 0; i < numItems; i++){
                    items[i] = new Items(itemScanner.next(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt());
                    itemScanner.nextLine();
                }
                System.out.println("Backup file OK. Overwriting main file.");
                
                PrintWriter file = new PrintWriter(new File("items"));
                file.println(numItems + ";;");
                for(int i = 0; i < numItems; i++){
                    file.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;");
                }
                file.close();
                
                System.out.println("Main file restored. Creating new backup file...");
                PrintWriter fileBackup = new PrintWriter(new File("items.bak"));
                fileBackup.println(numItems + ";;");
                for(int i = 0; i < numItems; i++){
                    fileBackup.println(items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;");
                }
                fileBackup.close();
                System.out.println("Backup created. Data should now be correct.");
            } catch(Exception x) {
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
                ID = itemScanner.nextInt();
                numKeywords = itemScanner.nextInt();
                keywordsArray = new String[numKeywords];
                for(int x = 0; x < numKeywords; x++){
                    keywordsArray[x] = itemScanner.next();
                }
                keywords[i] = new Keywords(ID, numKeywords, keywordsArray);
                itemScanner.nextLine();
            }
            PrintWriter file = new PrintWriter(new File("keywords.bak"));
            file.println(numItems + ";;");
            for(int i = 0; i < numItems; i++){
                file.println(keywords[i].ID + ";;" + keywords[i].numKeywords + ";;" + keywords[i].keywords + ";;");
            }
            file.close();
        } catch(Exception e) {
            try{
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
                    file.println(keywords[i].ID + ";;" + keywords[i].numKeywords + ";;" + keywords[i].keywords + ";;");
                }
                file.close();
                
                System.out.println("Main file restored. Creating new backup file...");
                PrintWriter fileBackup = new PrintWriter(new File("keywords.bak"));
                fileBackup.println(numItems + ";;");
                for(int i = 0; i < numItems; i++){
                    fileBackup.println(keywords[i].ID + ";;" + keywords[i].numKeywords + ";;" + keywords[i].keywords + ";;");
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
                file.println(subsections[i].ID + ";;" + subsections[i].name + ";;" + subsections[i].description + ";;" + ";;" + subsections[i].sectionID + ";;");
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
                    file.println(subsections[i].ID + ";;" + subsections[i].name + ";;" + subsections[i].description + ";;" + ";;" + subsections[i].sectionID + ";;");
                }
                file.close();
                
                System.out.println("Main file restored. Creating new backup file...");
                PrintWriter fileBackup = new PrintWriter(new File("subsections.bak"));
                fileBackup.println(numSubsections + ";;");
                for(int i = 0; i < numSubsections; i++){
                    fileBackup.println(subsections[i].ID + ";;" + subsections[i].name + ";;" + subsections[i].description + ";;" + ";;" + subsections[i].sectionID + ";;");
                }
                fileBackup.close();
                System.out.println("Backup created. Data should now be correct.");
            } catch(Exception x) {
                System.out.println("Unrecoverable data corruption. Please try fixing the files manually.");
            }
        }
    }
}