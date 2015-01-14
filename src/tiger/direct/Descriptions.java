/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;
/*
 * File by: Michael Barker
 */
public class Descriptions {
    public int ID;                  //Item ID corresponding to the description
    public String description;      //Description of the item
    public String shortDescription; //Short description of the item
    
    public Descriptions(int i, String d, String s){
        this.ID = i;
        this.description = d;
        this.shortDescription = s;
    }
}
