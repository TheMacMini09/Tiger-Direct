/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;
import java.util.Scanner;
import java.io.*;
/*
 * File by: Michael Barker
 */
public class TigerDirect {
    
    public static Scanner user = new Scanner(System.in);
    
    //Member objects; These are used by nearly every class. It is more efficient
    //to declare them this way than to continually pass them back and forth. To
    //use them, `import tiger.direct.TigerDirect;`
    public static CartRecord[] cart = new CartRecord[1000];
    public static Descriptions[] descriptions = new Descriptions[1000];
    public static Items[] items = new Items[1000];
    public static Sections[] sections = new Sections[100];
    public static SubSections[] subsections = new SubSections[10000];
    public static Keywords[] keywords = new Keywords[1000];
    
    public static void main(String[] args) throws IOException {
        //Variable declaration
        char employee; //Whether the user is an employee (y/n)
        Sections section;
        SubSections subsection;
        String contShopping = "y";
        
        System.out.print("Are you an employee? y/n: ");
        employee = user.next().trim().toLowerCase().charAt(0);
        
        if(employee == 'y'){
            Employees.Employees();
        }
        
//        //When calling the shopping section, while it returns "y", continue
//        while(contShopping == "y"){
//            section = Shopping.section(sections);
//            subsection = Shopping.subsection(section, subsections);
//            Shopping.itemFinder(items, subsection, descriptions);
//        }
    }
}
