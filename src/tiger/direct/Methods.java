/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;
import java.util.Scanner;
/*
 * File by: Michael Barker
 */
public class Methods {
    public static Scanner user = new Scanner(System.in);
    
    public static int checkNumber(int low, int high){
        //Returns true if user.nextInt() is valid, returns false if user.nextInt() is invalid
        if(!user.hasNextInt()){
            user.nextLine();
            return -1;
        }
        int number = user.nextInt();
        user.nextLine();
        if (number < low || number > high){
            return -1;
        } else {
            return number;
        }
    }
}