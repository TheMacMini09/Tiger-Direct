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
import static tiger.direct.Employees.writeFile;
import static tiger.direct.TigerDirect.items;
import static tiger.direct.TigerDirect.numItemsInCart;
/*
 * File by: Michael Barker
 MICHAEL BE SURE TO MAKE IT SO THAT IF THEY REMOVE AN ITEM FROM THE CART, IT WILL ADD THE QUANTITY BACK TO THE STOCK
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
        String toWrite = "";
        
        System.out.println("Welcome to the Tiger Direct shopping cart!");
        ReadFiles.readDescriptions(); //reads descriptions from items
        while(changeCart == 'y'){ //while the user wants to change the cart
            if(numItemsInCart <= 0){ // if the cart is empty
                System.out.println("Empty cart. Exiting cart.");
                changeCart = 'n'; // close cart
            } else { // otherwise
                System.out.println("Here are your items:\n");
                for(int i = 0; i < numItemsInCart; i++){ // for all the items in the array,
                    System.out.println("\t" + (i+1) + ". " + "$" + cart[i].dollarPrice + "." + cart[i].centPrice + " " + cart[i].name + " - " + descriptions[cart[i].ID-1].shortDescription + " Qty: " + cart[i].quantity); // print item info
                }
                System.out.print("\nWould you like to change your cart? y/n: ");
                changeCart = user.next().trim().toLowerCase().charAt(0); // checks if they want to change their cart
                user.nextLine();

                if(changeCart == 'y'){ // if they do
                    System.out.print("Which item would you like to change/remove? Enter the number: ");
                    itemNumber = Methods.checkNumber(1, numItemsInCart);
                    while(itemNumber == -1){ // while entry is invalid (Methods.checkNumber returns -1)
                        System.out.print("Invalid selection! Please enter a number between 1 and " + numItemsInCart + ": ");
                        itemNumber = Methods.checkNumber(1, numItemsInCart);
                    }
                    System.out.print("Would you like to change the quantity of items,\nor remove it entirely? 'c' for change, 'r' to remove, 'q' to quit: ");
                    removeChange = user.next().trim().toLowerCase().charAt(0);
                    user.nextLine();
                    while(removeChange != 'c' && removeChange != 'r' && removeChange != 'q'){ // while invalid entry
                        System.out.print("Invalid selection! Please enter 'r' to remove, 'c' to change, or 'q' to exit the modifier:");
                        removeChange = user.next().trim().toLowerCase().charAt(0);
                        user.nextLine();
                    }
                    if(removeChange == 'c'){ // if they want to change quantity of items
                        System.out.print("How many of the item " + cart[itemNumber-1].name + " would you like? There are\ncurrently " + (items[cart[itemNumber-1].ID].stock-cart[itemNumber-1].quantity) + " in stock, available to be purchased: "); // shows current stock
                        newNumberOfItems = Methods.checkNumber(1, (items[cart[itemNumber-1].ID].stock-cart[itemNumber-1].quantity)); // makes sure entry is valid (less than the total stock and greater than 1)
                        while(newNumberOfItems == -1){ // while invalid entry
                            System.out.print("Invalid selection! Please enter a number between 1 and " + (items[cart[itemNumber-1].ID].stock-cart[itemNumber-1].quantity) + ": ");
                            newNumberOfItems = Methods.checkNumber(1, (items[cart[itemNumber-1].ID].stock-cart[itemNumber-1].quantity)); // check if valid
                        }
                        
                        itemsRemoved = cart[itemNumber].quantity - newNumberOfItems; // removes the number of items chosen by the user
                        cart[itemNumber-1] = new CartRecord(cart[itemNumber-1].name, cart[itemNumber-1].ID, cart[itemNumber-1].section, cart[itemNumber-1].subsection, cart[itemNumber-1].dollarPrice, cart[itemNumber-1].centPrice, newNumberOfItems); // updates cart
                        
                        toWrite += (numItems + ";;" + "\n");
                        
                        for(int i = 0; i < numItems; i++){      //Remove stock from items file
                            if(i == cart[itemNumber].ID - 1){
                                toWrite += (items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + (items[i].stock-cart[itemNumber].quantity) + ";;" + "\n");
                            } else {
                                toWrite += (items[i].name + ";;" + items[i].ID + ";;" + items[i].section + ";;" + items[i].subsection + ";;" + items[i].dollarPrice + ";;" + items[i].centPrice + ";;" + items[i].stock + ";;" + "\n");
                            }
                        }

                        writeFile("items", toWrite);
                    } else if(removeChange == 'r'){ // while they want to remove an item
                        itemsRemoved = cart[itemNumber].quantity; 
                        cart[itemNumber] = new CartRecord();
                        Arrays.sort(cart,0,numItemsInCart);
                        
                        numItemsInCart--; // remove item
                    }
                    items[itemNumber] = new Items(items[itemNumber].name, items[itemNumber].ID, items[itemNumber].section, items[itemNumber].subsection, items[itemNumber].dollarPrice, items[itemNumber].centPrice, items[itemNumber].stock + itemsRemoved); // updates
                } else {
                    System.out.println("Exiting cart.");
                }
            }
        }
    }
}