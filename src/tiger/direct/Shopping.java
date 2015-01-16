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
import static tiger.direct.TigerDirect.numItemsInCart;

public class Shopping {
    public static Sections section (Sections sections[]){
        int sectionNumber = 0;
        
        System.out.println("Welcome to Tiger Direct.  Please make a selection from our wide array of section to begin shopping");
        for (int i=0; i < sections.length-1; i++){
            System.out.println((i+1) + ". " + sections[i].name);
        }
        sectionNumber = mymethods.Console.getInt("Enter the corresponing number to the section you are visiting: ");
        return sections[sectionNumber];
    }
    public static SubSections subsection (Sections section, SubSections subsections[]){
        int subsectionNumber = 0;
        int counter = 0;
        SubSections inSection[] = new SubSections[subsections.length];
        
        System.out.println("You are currently browsing in " + section.name + " section.");
        for (int i=0; i > subsections.length; i++){
            if (subsections[i].sectionID == section.ID){
                counter++;
                System.out.println((i+1) + ". " + subsections[i].name);
                inSection[counter] = subsections[i];
            }
        }
        subsectionNumber = mymethods.Console.getInt("Enter the corresponing number to the section you are visiting: ");
    return inSection[subsectionNumber];
    }
    
    
    public static String itemFinder (Items itemList [], SubSections subsection, Descriptions descriptions[]){
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
        
        while (stay.equals("y")){
            System.out.println("This is the list of items in the " + subsection.name + " subsection");


            for (int i=0; i < itemList.length; i++){
                currentItem = itemList[i];

                if (currentItem.subsection == subsection.ID && currentItem.stock > 0){
                    counter ++;
                    System.out.println(counter + 1 + ". " + currentItem.name);
                    inSubsection[counter] = currentItem;
                }

            }
            listNum = mymethods.Console.getInt("Enter the corresponing number to the item you are selecting: ");
            System.out.println(inSubsection[listNum].name);
            System.out.println("Item ID: " + inSubsection[listNum].ID);
            System.out.println("Price: " + inSubsection[listNum].dollarPrice + "." + inSubsection[listNum].centPrice);
            for (int i = 0; i<descriptions.length; i++){
                if (descriptions[i].ID == inSubsection[listNum].ID){
                    System.out.println("Description: " + descriptions[i].description);
                }
            }
            System.out.print("Are you sure you would like to purchase this product?");
            buy = user.nextLine();
            user.nextLine();
            while ((!(buy.trim().toLowerCase().substring(0, 1).equals("y")) || (!(buy.trim().toLowerCase().substring(0, 1).equals("n"))))){
                System.out.println("Please enter either y or n.");
                System.out.print("Are you sure you would like to purchase this product?");
                buy = user.nextLine();
                user.nextLine();
            
            }
            if (buy.trim().toLowerCase().substring(0, 1).equals("y")){
                itemBought = mymethods.Console.getInt("Please enter how many of this item you would like to purchase: ");
                counter2 ++;
                inCart[counter2] = new CartRecord(inSubsection[listNum].name, inSubsection[listNum].ID,inSubsection[listNum].section, inSubsection[listNum].subsection,inSubsection[listNum].dollarPrice, inSubsection[listNum].centPrice, itemBought);
                System.out.println("The item has been added to your cart.");
            }
        System.out.print("Would you like to continue shopping in this subsection?");
        stay = user.nextLine();
        user.nextLine();
        while ((!(stay.trim().toLowerCase().substring(0, 1).equals("y")) || (!(stay.trim().toLowerCase().substring(0, 1).equals("n"))))){
            System.out.println("Please enter either y or n.");
            System.out.print("Would you like to continue shopping in this subsection?");
            stay = user.nextLine();
            user.nextLine();    
        }    
        
        }
   
    System.out.print("Would you like to continue shopping in this section?");
    backToTop = user.nextLine();
    user.nextLine();
        
    while ((!(backToTop.trim().toLowerCase().substring(0, 1).equals("y")) || (!(backToTop.trim().toLowerCase().substring(0, 1).equals("n"))))){
        System.out.println("Please enter either y or n.");
        System.out.print("Would you like to continue shopping in this section?");
        backToTop = user.nextLine();
        user.nextLine();    
    }    
    
    numItemsInCart = counter2;
    return backToTop.trim().toLowerCase().substring(0, 1);
    }
}
