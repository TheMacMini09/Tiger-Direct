/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20, 2015
 */

package tiger.direct;
/*
 * File by: Nigel
 */

import java.util.Arrays;
import java.util.Scanner;
import static tiger.direct.Employees.numItems;
import static tiger.direct.Employees.numSections;
import static tiger.direct.Employees.numSubsections;
import static tiger.direct.TigerDirect.numItemsInCart;
import static tiger.direct.TigerDirect.sections;
import static tiger.direct.TigerDirect.subsections;

public class Shopping {
    public static int section (Sections sections[]){
        int sectionNumber = 0;
        int counter = 1;
        System.out.println("Welcome to Tiger Direct.  Please make a selection from our wide array of section to begin shopping");
        for (int i=0; i < numSections; i++){
            System.out.println((i+1) + ". " + sections[i].name);
            counter++;
        }
        sectionNumber = mymethods.Console.getInt("Enter the corresponing number to the section you are visiting: " , 1, counter);
        return sectionNumber;
    }
    
    public static int subsection (int sectionID){
        int subsectionNumber = 0;
        int counter = 0;
        SubSections[] inSection = new SubSections[subsections.length];
        sectionID--;
        
        System.out.println("You are currently browsing in " + sections[sectionID].name + ".");
        for (int i=0; i < numSubsections; i++){
            if (subsections[i].sectionID == sectionID+1){
                counter++;
                System.out.println((i+1) + ". " + subsections[i].name);
                inSection[counter] = subsections[i];
            }
        }
        subsectionNumber = mymethods.Console.getInt("Enter the corresponing number to the subsection you are visiting: ");
        return subsectionNumber;
    }
    
    public static String itemFinder (Items itemList [], int subsection, Descriptions descriptions[]){
        Scanner user = new Scanner(System.in);
        String stay = "y";
        Items selectedItem;
        int listNum = 0;
        int counter = 0;
        int counter2 = 0;
        Items currentItem;
        Items inSubsection[] = new Items[itemList.length];
        CartRecord[] inCart = new CartRecord[1000];
        String buy = "";
        int itemBought = 0;
        String backToTop = "";
        
        subsection--;
        
        while (stay.equals("y")){
            System.out.println("This is the list of items in the " + subsections[subsection].name + " subsection.");
            counter = 0;
            for (int i=0; i < numItems; i++){
                currentItem = itemList[i];

                if (itemList[i].subsection == subsections[subsection].ID && itemList[i].stock > 0){
                    counter ++;
                    System.out.println(counter + ". " + itemList[i].name);
                    inSubsection[counter] = itemList[i];
                }
            }
            
            listNum = mymethods.Console.getInt("Enter the corresponing number to the item you are selecting: ");
            System.out.println(inSubsection[listNum].name);
            System.out.println("Item ID: " + inSubsection[listNum].ID);
            System.out.println("Price: " + inSubsection[listNum].dollarPrice + "." + inSubsection[listNum].centPrice);
            for (int i = 0; i < numItems; i++){
                if (descriptions[i].ID == inSubsection[listNum].ID){
                    System.out.println("Description: " + descriptions[i].description);
                }
            }
            
            System.out.print("Are you sure you would like to purchase this product?");
            buy = user.nextLine();
            while (buy.trim().toLowerCase().charAt(0) != 'y' && buy.trim().toLowerCase().charAt(0) != 'n'){
                System.out.println("Please enter either y or n.");
                System.out.print("Are you sure you would like to purchase this product?");
                buy = user.nextLine();
            }
            if (buy.trim().toLowerCase().charAt(0) == 'y'){
                itemBought = mymethods.Console.getInt("Please enter how many of this item you would like to purchase: ");
                while (itemBought > inSubsection[listNum].stock){
                    itemBought = mymethods.Console.getInt("There is not that many items of that type in stock, please enter a valid number: ");
                }
                counter2 ++;
                inCart[counter2] = new CartRecord(inSubsection[listNum].name, inSubsection[listNum].ID,inSubsection[listNum].section, inSubsection[listNum].subsection,inSubsection[listNum].dollarPrice, inSubsection[listNum].centPrice, itemBought);
                System.out.println("The item has been added to your cart.");
            }
        System.out.print("Would you like to continue shopping in this subsection?");
        stay = user.nextLine().trim().toLowerCase();
        while (stay.charAt(0) != 'y' && stay.charAt(0) != 'n'){
            System.out.println("Please enter either y or n.");
            System.out.print("Would you like to continue shopping in this subsection?");
            stay = user.nextLine().trim().toLowerCase();
        }
    }
   
    System.out.print("Would you like to continue shopping?: ");
    backToTop = user.nextLine().trim().toLowerCase();
        
    while (backToTop.charAt(0) != 'y' && backToTop.charAt(0) != 'n'){
        System.out.println("Please enter either y or n.");
        System.out.print("Would you like to continue shopping: ");
        backToTop = user.nextLine();
    }    
    
    numItemsInCart = counter2;
    return backToTop.trim().toLowerCase().substring(0, 1);
    }
}
