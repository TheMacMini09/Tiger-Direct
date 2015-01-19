/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;
import java.io.FileNotFoundException;
import tiger.direct.TigerDirect;
import java.util.Scanner;
import java.util.Arrays;
import static tiger.direct.Employees.numItems;
/*
 * File by: Michael Barker
 */
public class Cart {
    
    //Object declaration
    static Scanner user = new Scanner(System.in);
    static CartRecord[] cart = TigerDirect.cart;
    static Descriptions[] descriptions = TigerDirect.descriptions;
    static Items[] items = TigerDirect.items;
    
    public static void cart() throws FileNotFoundException{
        //Variable declaration
        char changeCart = 'y';
        char itemChange;
        char removeChange;
        int itemNumber;
        int newNumberOfItems;
        int itemsRemoved = 0;
        
        System.out.println("Welcome to the Tiger Direct shopping cart!");
        ReadFiles.readDescriptions();
        while(changeCart == 'y'){
            if(numItems <= 0){
                System.out.println("Empty cart. Exiting cart.");
                changeCart = 'n';
            } else {
                System.out.println("Here are your items:\n");
                for(int i = 0; i < numItems; i++){
                    System.out.println("\t" + (i+1) + ". " + "$" + cart[i].dollarPrice + "." + cart[i].centPrice + " " + cart[i].name + " - " + descriptions[cart[i].ID-1].shortDescription + " Qty: " + cart[i].quantity);
                }
                System.out.print("\nWould you like to change your cart? y/n: ");
                changeCart = user.next().trim().toLowerCase().charAt(0);
                user.nextLine();

                if(changeCart == 'y'){
                    System.out.print("Which item would you like to change/remove? Enter the number: ");
                    itemNumber = Methods.checkNumber(1, numItems);
                    while(itemNumber == -1){
                        System.out.print("Invalid selection! Please enter a number between 1 and " + numItems + ": ");
                        itemNumber = Methods.checkNumber(1, numItems);
                    }
                    System.out.print("Would you like to change the quantity of items,\nor remove it entirely? 'c' for change, 'r' to remove, 'q' to quit: ");
                    removeChange = user.next().trim().toLowerCase().charAt(0);
                    user.nextLine();
                    while(removeChange != 'c' && removeChange != 'r' && removeChange != 'q'){
                        System.out.print("Invalid selection! Please enter 'r' to remove, 'c' to change, or 'q' to exit the modifier:");
                        removeChange = user.next().trim().toLowerCase().charAt(0);
                        user.nextLine();
                    }
                    if(removeChange == 'c'){
                        System.out.print("How many of the item " + cart[itemNumber-1].name + " would you like? There are\ncurrently " + (items[cart[itemNumber-1].ID].stock-cart[itemNumber-1].quantity) + " in stock, available to be purchased: ");
                        newNumberOfItems = Methods.checkNumber(1, (items[cart[itemNumber-1].ID].stock-cart[itemNumber-1].quantity));
                        while(newNumberOfItems == -1){
                            System.out.print("Invalid selection! Please enter a number between 1 and " + (items[cart[itemNumber-1].ID].stock-cart[itemNumber-1].quantity) + ": ");
                            newNumberOfItems = Methods.checkNumber(1, (items[cart[itemNumber-1].ID].stock-cart[itemNumber-1].quantity));
                        }
                        itemsRemoved = cart[itemNumber].quantity - newNumberOfItems;
                        cart[itemNumber-1] = new CartRecord(cart[itemNumber-1].name, cart[itemNumber-1].ID, cart[itemNumber-1].section, cart[itemNumber-1].subsection, cart[itemNumber-1].dollarPrice, cart[itemNumber-1].centPrice, newNumberOfItems);
                    } else if(removeChange == 'r'){
                        itemsRemoved = cart[itemNumber].quantity;
                        cart[itemNumber] = new CartRecord();
                        Arrays.sort(cart,0,numItems);
                        numItems--;
                    }
                    items[itemNumber] = new Items(items[itemNumber].name, items[itemNumber].ID, items[itemNumber].section, items[itemNumber].subsection, items[itemNumber].dollarPrice, items[itemNumber].centPrice, items[itemNumber].stock + itemsRemoved);
                } else {
                    System.out.println("Exiting cart.");
                }
            }
        }
    }
}