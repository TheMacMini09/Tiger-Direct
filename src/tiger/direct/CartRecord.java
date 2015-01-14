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
public class CartRecord implements Comparable<CartRecord> {
    public String name;
    public int ID;
    public int section;
    public int subsection;
    public int dollarPrice;
    public int centPrice;
    public int quantity;
    
    public CartRecord(String n, int i, int s, int ss, int d, int c, int q){
        this.name = n;
        this.ID = i;
        this.section = s;
        this.subsection = ss;
        this.dollarPrice = d;
        this.centPrice = c;
        this.quantity = q;
    }
    
    public CartRecord(){
        this.name = "";
        this.ID = 0;
        this.section = 0;
        this.subsection = 0;
        this.dollarPrice = 0;
        this.centPrice = 0;
        this.quantity = 0;
    }
    
    @Override
    public int compareTo(CartRecord C){
        if(this.ID < C.ID){
            return -1;
        } else if (this.ID == C.ID){
            return 0;
        } else {
            return 1;
        }
    }
}