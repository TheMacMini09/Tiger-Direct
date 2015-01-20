/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20, 2015
 */

package tiger.direct;
/*
 * File by: Nigel
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import static tiger.direct.Employees.numItems;
import static tiger.direct.Employees.numSections;
import static tiger.direct.Employees.numSubsections;
import static tiger.direct.TigerDirect.cart;
import static tiger.direct.TigerDirect.numItemsInCart;
import static tiger.direct.TigerDirect.sections;
import static tiger.direct.TigerDirect.subsections;
import static tiger.direct.TigerDirect.counter2;
import static tiger.direct.TigerDirect.items;

public class Shopping {
    
    /// Section Selector called by main///
    public static int section (Sections sections[]){
        int sectionNumber = 0;
        int counter = 0;
        System.out.println("Please make a selection from our wide array of sections to begin shopping!");
        for (int i=0; i < numSections; i++){ ///Output for sections
            System.out.println((i+1) + ". " + sections[i].name);
            counter++;
        }
        sectionNumber = mymethods.Console.getInt("Enter the corresponing number to the section you are visiting: " , 1, counter); /// Input from user
        return sectionNumber; /// return sectionNumber for subsection method
    }
    
    /// Subsection Selector called by main ///
    public static int subsection (int sectionID){
        int subsectionNumber = 0;
        int counter = 0;
        int smallestID = 0;
        int largestID = 0;
        SubSections[] inSection = new SubSections[subsections.length];
        sectionID--;
        
        System.out.println("You are currently browsing in " + sections[sectionID].name + ".");
        for (int i=0; i < numSubsections; i++){             ///Scanning for subsections in the section;
            if (subsections[i].sectionID == sectionID+1){
                if(counter == 0){
                    smallestID = subsections[i].ID;
                    largestID = smallestID;
                } else {
                    largestID++;
                }
                counter++;
                System.out.println((i+1) + ". " + subsections[i].name);
                inSection[counter] = subsections[i];
            }
        }
        subsectionNumber = mymethods.Console.getInt("Enter the corresponing number to the subsection you are visiting: ", smallestID, largestID); ///Input in certain range
        return subsectionNumber; ///return subsectionNumber for ItemFinder
    }
    
    
    /// Item selector called by main ///
    public static String itemFinder (Items itemList [], int subsection, Descriptions descriptions[]) throws FileNotFoundException{
        Scanner user = new Scanner(System.in);
        String stay = "y";
        
        int listNum = 0;
        int counter = 0;
        Items currentItem;
        Items inSubsection[] = new Items[itemList.length];

        String buy = "";
        int itemBought = 0;
        String backToTop = "";
        boolean addTo = false;
        
        subsection--;
        
        /// While loop for shopping in subsection ///
        while (stay.charAt(0) == 'y'){
            System.out.println("This is the list of items in the " + subsections[subsection].name + " subsection.");
            counter = 0;
            for (int i=0; i < numItems; i++){ ///Checks which items are in subsection and prints them
                currentItem = itemList[i];
                
                if (itemList[i].subsection == subsections[subsection+1].ID && itemList[i].stock > 0){
                    counter ++;
                    System.out.println(counter + ". " + itemList[i].name);
                    inSubsection[counter] = itemList[i];
                }
            }
            
            listNum = mymethods.Console.getInt("Enter the corresponing number to the item you are selecting: ", 1, counter);
            /// Print out selected items information ///
            System.out.println(inSubsection[listNum].name);
            System.out.println("Item ID: " + inSubsection[listNum].ID);
            System.out.println("Price: " + inSubsection[listNum].dollarPrice + "." + inSubsection[listNum].centPrice);
            System.out.println("In Stock: " + inSubsection[listNum].stock);
            for (int i = 0; i < numItems; i++){
                if (descriptions[i].ID == inSubsection[listNum].ID){
                    System.out.println("Description: " + descriptions[i].description);
                }
            }
            
            /// Confirmation ///
            System.out.print("Are you sure you would like to purchase this product?: ");
            buy = user.nextLine();
            while (buy.trim().toLowerCase().charAt(0) != 'y' && buy.trim().toLowerCase().charAt(0) != 'n'){
                System.out.println("Please enter either y or n.");
                System.out.print("Are you sure you would like to purchase this product?: ");
                buy = user.nextLine();
            }
            if (buy.trim().toLowerCase().charAt(0) == 'y'){
                
                /// amount purchased ///
                itemBought = mymethods.Console.getInt("Please enter how many of this item you would like to purchase: ");
                //making sure input is not zero and they are not buying more product than available ///
                while (itemBought > inSubsection[listNum].stock || itemBought == 0){
                    if (itemBought > inSubsection[listNum].stock){
                        itemBought = mymethods.Console.getInt("There is not that many items of that type in stock, please enter a num no greater than " + inSubsection[listNum].stock + ": ");
                    }
                    else if (itemBought == 0){
                        itemBought = mymethods.Console.getInt("You cannot purchase 0 items.\nPlease enter a valid number: ");
                                
                    }
                        
                }
                /// Makes it so multiple orders of items does not leave duplicates in the cart
                if(numItemsInCart > 0){
                    for (int i = 0; i < numItemsInCart; i++){
                        if (inSubsection[listNum].ID == cart[i].ID){
                            cart[i].quantity+=itemBought;
                            addTo = true;
                        }
                    }
                }
                /// Adds to cart if no duplicate
                if (addTo == false){
                    cart[counter2] = new CartRecord(inSubsection[listNum].name, inSubsection[listNum].ID,inSubsection[listNum].section, inSubsection[listNum].subsection,inSubsection[listNum].dollarPrice, inSubsection[listNum].centPrice, itemBought);
                    System.out.println("The item has been added to your cart.");
                    
                    //Variable declaration
                    int x;
                    String toWrite = "";

                    ReadFiles.readItems();
                    
                    toWrite += (numItems + ";;" + "\n");
                    for(int i = 0; i < numItems; i++){
                        if(i+1 == inSubsection[listNum].ID){
                            toWrite += (items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + (items[i].stock - itemBought) + ";;" + "\n");
                        } else {
                            toWrite += (items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;" + "\n");
                        }
                    }
                    Employees.writeFile("items", toWrite);
                }
            counter2 ++;
            addTo = false; /// reset boolean
            }
        numItemsInCart = counter2;
        ///loop in subsection
        System.out.print("Would you like to continue shopping in this subsection?: ");
        stay = user.nextLine().trim().toLowerCase();
        while (stay.charAt(0) != 'y' && stay.charAt(0) != 'n'){
            System.out.println("Please enter either y or n.");
            System.out.print("Would you like to continue shopping in this subsection?: ");
            stay = user.nextLine().trim().toLowerCase();
        }
    }
    /// sets variable to send to main for repeated shopping ///
    System.out.print("Would you like to continue shopping?: ");
    backToTop = user.nextLine().trim().toLowerCase();
        
    while (backToTop.charAt(0) != 'y' && backToTop.charAt(0) != 'n'){
        System.out.println("Please enter either y or n.");
        System.out.print("Would you like to continue shopping?: ");
        backToTop = user.nextLine();
    }
    return backToTop.trim().toLowerCase().substring(0, 1);
    }
}