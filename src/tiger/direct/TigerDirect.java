/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;
import java.util.Scanner;
import java.io.*;
/*
 * File by: Michael Barker, Max Farfaras
 */
public class TigerDirect {
    
    public static Scanner user = new Scanner(System.in);
    
    //Member objects; These are used by nearly every class. It is more efficient
    //to declare them this way than to continually pass them back and forth. To
    //use them, `import tiger.direct.TigerDirect;`
    public static CartRecord[] cart = new CartRecord[1000];
    public static Descriptions[] descriptions = new Descriptions[1000];
    public static Items[] items = new Items[10000];
    public static Sections[] sections = new Sections[100];
    public static SubSections[] subsections = new SubSections[1000];
    public static Keywords[] keywords = new Keywords[1000];
    public static int numItemsInCart;
    public static int counter2 = 0;
    
    public static void main(String[] args) throws IOException {
        //Variable declaration
        char employee; //Whether the user is an employee (y/n)
        int section;
        int subsection;
        String contShopping = "y";
        
        System.out.print("Are you an employee? y/n: ");
        employee = user.next().trim().toLowerCase().charAt(0);
        
        if(employee == 'y'){
            Employees.Employees();
        } else {
            //When calling the shopping section, while it returns "y", continue
            while(contShopping.equals("y")){
                ReadFiles.readSections();
                ReadFiles.readSubsections();
                ReadFiles.readDescriptions();
                ReadFiles.readItems();
                section = Shopping.section(sections);
                subsection = Shopping.subsection(section);
                contShopping = Shopping.itemFinder(items, subsection, descriptions);
            }
            Checkout.checkout(cart, numItemsInCart);
        }
    }
}