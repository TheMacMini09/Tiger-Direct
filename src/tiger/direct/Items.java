/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;
/*
 * File by: Michael Barker
 */
public class Items {
    public String name;
    public int ID;
    public int section;
    public int subsection;
    public int dollarPrice;
    public int centPrice;
    public int stock;
    
    public Items(String n, int i, int s, int ss, int d, int c, int st){
        this.name = n;
        this.ID = i;
        this.section = s;
        this.subsection = ss;
        this.dollarPrice = d;
        this.centPrice = c;
        this.stock = st;
    }
    public Items(){
        this.name = "";
        this.ID = 0;
        this.section = 0;
        this.subsection = 0;
        this.dollarPrice = 0;
        this.centPrice = 0;
        this.stock = 0;
    }
}