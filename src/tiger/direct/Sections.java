/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;
/*
 * File by: Michael Barker
 */
public class Sections {
    public int ID;                  //Section ID corresponding to the description
    public String name;             //Name of the ID
    public String description;        //Description of the ID
    
    public Sections(int i, String n, String d){
        this.ID = i;
        this.name = n;
        this.description = d;
    }
}