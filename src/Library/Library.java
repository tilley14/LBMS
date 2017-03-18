package Library;

import Books.BookStorage;
import Books.Book;
import Visitors.VisitorStorage;
import Visitors.Visitor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Library class serves as the "brain" of the LBMS system so-to-say. Interactions between the library's internal
 * storage of visitors and books is facilitated within this class.
 *
 * @author Kyler Freas
 * @author Jake Bashaw
 * @author Nikolas Tilley
 * @author Tyler Reimold
 * @author Kyle Kaniecki
 */
public class Library
{
    private VisitorStorage visitorStorage;
    private BookStorage bookStorage;

    /**
     * Initializes visitor and book storage from existing files.
     */
    public Library()
    {
        // TODO: Add catalog, purchases, state
        this.visitorStorage = VisitorStorage.deserialize();
        this.bookStorage = BookStorage.deserialize();
    }

    /**
     * Performs a search for books owned by the library.
     *
     * @param searchCriteria - The search criteria supplied by the user
     * @return An arrayList representing the books that meet the supplied search criteria.
     */
    public ArrayList<Book> bookSearch(String[] searchCriteria)
    {
        return this.bookStorage.bookSearch(searchCriteria);
    }

    /**
     * Registers a new visitor in the library. The response is the newly registered visitor.
     *
     * @param firstName - The first name of the visitor to be registered
     * @param lastName - the last name of the visitor to be registered.
     * @param address - The address of the visitor to be registered.
     * @param phoneNumber - The phone number of the visitor to be registered.
     * @return The newly registered visitor.
     */
    public Visitor registerVisitor(String firstName, String lastName, String address, String phoneNumber)
    {
        return this.visitorStorage.registerVisitor(firstName, lastName, address, phoneNumber);
    }

    /**
     * Getter method for retrieving a registered library visitor
     *
     * @param visitorID - The ID of the desired visitor.
     * @return The desired visitor
     */
    public Visitor getVisitor(Integer visitorID)
    {
        return this.visitorStorage.getVisitor(visitorID);
    }

    /**
     * Begin a visit in the library for a registered visitor.
     *
     * @param visitorID - The ID of the visitor that is starting their visit.
     */
    public void beginVisit(Integer visitorID)
    {
        this.visitorStorage.startVisit(visitorID);
    }

    /**
     * Ends a visit for a registered visitor.
     *
     * @param visitorID - The ID of the visitor currently at the library.
     */
    public void endVisit(Integer visitorID)
    {
        this.visitorStorage.endVisit(visitorID);
    }

    /**
     * Shut down the system, persisting all data created in flat files.
     */
    public void shutdown()
    {
        //TODO: Serialize all other entities to be persisted
        this.visitorStorage.serialize();
        this.bookStorage.serialize();
    }

    /**
     * Generates a statistical report of the library
     *
     * @return A String representing the statistical report for the library.
     */
    public String generateReport()
    {
        //TODO: Add in the rest of the report data needed
        LocalDate localDate = LocalDate.now();
        String report = DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate) + "\n" + this.bookStorage.generateReport() + "\n" + this.visitorStorage.generateReport() + "\n";
        return report;
    }

    //TODO: Add remaining commands
}