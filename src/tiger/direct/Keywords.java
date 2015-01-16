/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;
import java.util.Arrays;
/*
 * File by: Michael Barker
 */
public class Keywords {
    public int ID;              //Item ID of which the keywords correspond to
    public int numKeywords;     //Number of keywords in the file
    public String[] keywords;   //String array of keywords, alphabetical
    
    public Keywords(int i, int n, String[] k){
        this.ID = i;
        this.numKeywords = n;
        this.keywords = k;
        Arrays.sort(keywords);
    }
}
