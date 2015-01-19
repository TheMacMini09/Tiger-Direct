/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20, 2015
 */

package tiger.direct;
/*
 * File by: Nigel
 */

import java.util.Scanner;
import static tiger.direct.Employees.numItems;
import static tiger.direct.Employees.numSections;
import static tiger.direct.Employees.numSubsections;
import static tiger.direct.TigerDirect.cart;
import static tiger.direct.TigerDirect.numItemsInCart;
import static tiger.direct.TigerDirect.sections;
import static tiger.direct.TigerDirect.subsections;
import static tiger.direct.TigerDirect.counter2;

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
        SubSections[] inSection = new SubSections[subsections.length];
        sectionID--;
        
        System.out.println("You are currently browsing in " + sections[sectionID].name + ".");
        for (int i=0; i < numSubsections; i++){             ///Scanning for subsections in the section
            if (subsections[i].sectionID == sectionID+1){
                counter++;
                System.out.println((i+1) + ". " + subsections[i].name);
                inSection[counter] = subsections[i];
            }
        }
        subsectionNumber = mymethods.Console.getInt("Enter the corresponing number to the subsection you are visiting: ", 1,counter); ///Input in certain range
        return subsectionNumber; ///return subsectionNumber for ItemFinder
    }
    
    
    /// Item selector called by main ///
    public static String itemFinder (Items itemList [], int subsection, Descriptions descriptions[]){
        Scanner user = new Scanner(System.in);
        String stay = "y";
        Items selectedItem;
        int listNum = 0;
        int counter = 0;
        Items currentItem;
        Items inSubsection[] = new Items[itemList.length];

        String buy = "";
        int itemBought = 0;
        String backToTop = "";
        boolean addTo = false;
        
        subsection--;
        
        
        while (stay.charAt(0) == 'y'){
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
            
            listNum = mymethods.Console.getInt("Enter the corresponing number to the item you are selecting: ", 1, counter);
            System.out.println(inSubsection[listNum].name);
            System.out.println("Item ID: " + inSubsection[listNum].ID);
            System.out.println("Price: " + inSubsection[listNum].dollarPrice + "." + inSubsection[listNum].centPrice);
            for (int i = 0; i < numItems; i++){
                if (descriptions[i].ID == inSubsection[listNum].ID){
                    System.out.println("Description: " + descriptions[i].description);
                }
            }
            
            System.out.print("Are you sure you would like to purchase this product?: ");
            buy = user.nextLine();
            while (buy.trim().toLowerCase().charAt(0) != 'y' && buy.trim().toLowerCase().charAt(0) != 'n'){
                System.out.println("Please enter either y or n.");
                System.out.print("Are you sure you would like to purchase this product?: ");
                buy = user.nextLine();
            }
            if (buy.trim().toLowerCase().charAt(0) == 'y'){
                itemBought = mymethods.Console.getInt("Please enter how many of this item you would like to purchase: ");
                while (itemBought > inSubsection[listNum].stock || itemBought == 0){
                    if (itemBought > inSubsection[listNum].stock){
                        itemBought = mymethods.Console.getInt("There is not that many items of that type in stock, please enter a num no greater than " + inSubsection[listNum].stock + ": ");
                    }
                    else if (itemBought == 0){
                        itemBought = mymethods.Console.getInt("You cannot purchase 0 items.\nPlease enter a valid number: ");
                                
                    }
                        
                }
                if(numItemsInCart > 0){
                    for (int i = 0; i < cart.length; i++){
                        if (inSubsection[listNum].ID == cart[i].ID){
                            cart[i].quantity+=itemBought;
                            addTo = true;
                        }
                    }
                }
                if (addTo == false){
                    cart[counter2] = new CartRecord(inSubsection[listNum].name, inSubsection[listNum].ID,inSubsection[listNum].section, inSubsection[listNum].subsection,inSubsection[listNum].dollarPrice, inSubsection[listNum].centPrice, itemBought);
                    System.out.println("The item has been added to your cart.");
                }
                counter2 ++;
            addTo = false;
            }
        numItemsInCart = counter2;
        System.out.print("Would you like to continue shopping in this subsection?: ");
        stay = user.nextLine().trim().toLowerCase();
        while (stay.charAt(0) != 'y' && stay.charAt(0) != 'n'){
            System.out.println("Please enter either y or n.");
            System.out.print("Would you like to continue shopping in this subsection?: ");
            stay = user.nextLine().trim().toLowerCase();
        }
    }
   
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