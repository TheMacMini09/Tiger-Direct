/*
 * A joint project between Michael Barker, Max Farfaras, and Nigel Young.
 * Start date: January 6th, 2015
 * Completion date: January 20th, 2015
 */
package tiger.direct;
/*
 * File by: Michael Barker
 */
public class SubSections {
    public int ID;                  //Subsection ID corresponding to the description
    public int sectionID;           //Section that the subsection belongs to
    public String name;             //Name of the ID
    public String description;      //Description of the ID
    
    public SubSections(int i, String n, String d, int si){
        this.ID = i;
        this.sectionID = si;
        this.name = n;
        this.description = d;
    }
}