/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;
import java.util.Comparator;
/*
 * File by: Michael Barker
 */
public class EmployeeLogin implements Comparable<EmployeeLogin> {
    public int ID;
    public String username;
    public String password;
    public int level;
    
    public EmployeeLogin(int i, String u, String p, int l){
        this.ID = i;
        this.username = u;
        this.password = p;
        this.level = l;
    }
    
    public EmployeeLogin(){
        this.ID = 0;
        this.username = "";
        this.password = "";
        this.level = 0;
    }

    public static Comparator<EmployeeLogin> EmployeeLoginUsernameComparator = new Comparator<EmployeeLogin>() {
        @Override
        public int compare(EmployeeLogin first, EmployeeLogin second) {
            String firstUsername, secondUsername;
            
            firstUsername = first.username;
            secondUsername = second.username;
            
            return firstUsername.compareTo(secondUsername);
        }
    };
    
    public static Comparator<EmployeeLogin> EmployeeLoginPasswordComparator = new Comparator<EmployeeLogin>() {
        @Override
        public int compare(EmployeeLogin first, EmployeeLogin second) {
            String firstPassword, secondPassword;
            
            firstPassword = first.password;
            secondPassword = second.password;
            
            return firstPassword.compareTo(secondPassword);
        }
    };

    @Override
    public int compareTo(EmployeeLogin L){
        if(this.level < L.level){
            return -1;
        } else if (this.level == L.level){
            return 0;
        } else {
            return 1;
        }
    }
}