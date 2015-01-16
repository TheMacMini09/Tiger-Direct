/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static tiger.direct.Employees.logins;
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
        //Variable declaration
        int numItems;
        
        Scanner itemScanner = new Scanner(new File("items"));
        itemScanner.useDelimiter(";;");

        numItems = itemScanner.nextInt();
        itemScanner.nextLine();
        for(int i = 0; i < numItems; i++){
            items[i] = new Items(itemScanner.next(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt(), itemScanner.nextInt());
            itemScanner.nextLine();
        }
    }
    
    public static void readAuthentication() throws FileNotFoundException{
        //Variable declaration
        int numLogins;
        
        Scanner itemScanner = new Scanner(new File("logins"));
        itemScanner.useDelimiter(";;");

        numLogins = itemScanner.nextInt();
        itemScanner.nextLine();
        for(int i = 0; i < numLogins; i++){
            logins[i] = new EmployeeLogin(itemScanner.nextInt(), itemScanner.next(), itemScanner.next(), itemScanner.nextInt());
            itemScanner.nextLine();
        }
    }
    
    public static void readDescriptions() throws FileNotFoundException{
        //Variable declaration
        int numItems;
        
        Scanner itemScanner = new Scanner(new File("descriptions"));
        itemScanner.useDelimiter(";;");

        numItems = itemScanner.nextInt();
        itemScanner.nextLine();
        for(int i = 0; i < numItems; i++){
            descriptions[i] = new Descriptions(itemScanner.nextInt(), itemScanner.next(), itemScanner.next());
            itemScanner.nextLine();
        }
    }
    
    public static void readKeywords() throws FileNotFoundException{
        //Variable declaration
        int numItems;
        int numKeywords;
        int ID;
        String[] keywordsArray;
        
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
    }
    
    public static void readSections() throws FileNotFoundException{
        //Variable declaration
        int numItems;
        
        Scanner itemScanner = new Scanner(new File("sections"));
        itemScanner.useDelimiter(";;");

        numItems = itemScanner.nextInt();
        itemScanner.nextLine();
        for(int i = 0; i < numItems; i++){
            sections[i] = new Sections(itemScanner.nextInt(), itemScanner.next(), itemScanner.next());
            itemScanner.nextLine();
            numSections++;
        }
    }
    
    public static void readSubsections() throws FileNotFoundException{
        //Variable declaration
        int numItems;
        
        Scanner itemScanner = new Scanner(new File("subsections"));
        itemScanner.useDelimiter(";;");

        numItems = itemScanner.nextInt();
        itemScanner.nextLine();
        for(int i = 0; i < numItems; i++){
            subsections[i] = new SubSections(itemScanner.nextInt(), itemScanner.next(), itemScanner.next(), itemScanner.nextInt());
            itemScanner.nextLine();
            numSubsections++;
        }
    }
}