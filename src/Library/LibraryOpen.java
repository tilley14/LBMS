package Library;

import Books.Book;
import Books.BookStorage;
import Visitors.Visit;
import Visitors.Visitor;
import Visitors.VisitorStorage;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Implements how state dependent commands are executed when the library is open.
 *
 * @author Nikolas Tilley
 */
public class LibraryOpen implements LibraryState
{
    /**
     * Constructor for the open library state.
     */
    public LibraryOpen()
    {

    }

    /**
     * Starts a visit for a registered user at the library.
     *
     * @param visitorID - The ID of the visitor who is starting their visit.
     * @param visitorStorage - The storage of visitors that is being accessed.
     * @return A String representing the output that will displayed to the user.
     */
    public String stateBeginVisit(Long visitorID, VisitorStorage visitorStorage)
    {
        String response = "arrive,";

        if(visitorStorage.getVisitor(visitorID) == null)
            return response + "invalid-id;";

        Visit visit = visitorStorage.startVisit(visitorID);

        if (visit != null)
        {
            response += visitorID + "," + visit.getFormattedDate(visit.getStartDateTime()) + "," + visit.getFormattedTime(visit.getStartDateTime()) + ";";
        }
        else
        {
            response += "duplicate;";
        }
        return response;
    }

    /**
     * Checks out a book for a registered visitor at the library.
     *
     * @param bkID - the bookID that is beign checked out
     * @param vID - the visitorID
     * @param visitorStorage - the visitor storage that is being manipulated
     * @param timeClock - the timeclock
     * @param bookStorage - the book storage
     * @return A String representing the output that will displayed to the user.
     */
    public String stateCheckOutBook(ArrayList<String> bkID, Long vID, VisitorStorage visitorStorage, TimeClock timeClock, BookStorage bookStorage)
    {
        Visitor currentV = visitorStorage.getVisitor(vID);
        ArrayList<Book> books = new ArrayList<>();

        if(bookStorage.getLastSearch() == null || bookStorage.getLastSearch().size() == 0)
            return "borrow,no-recent-search-found;";

        for(String id : bkID)
        {
            for (Book bk : bookStorage.getLastSearch())
            {
                if (bk.getIsbn().compareTo(id) == 0)
                {
                    books.add(bk);
                    break;
                }
            }
        }
        currentV.checkOutBooks(books,timeClock.getCalendarDate());

        Calendar c = timeClock.getCalendarDate();
        c.add(Calendar.DAY_OF_WEEK, 7);
        return "borrow," + c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH)
                + ";" ;
    }

    /**
     * The method that undoes the checkout of books based on the state that it is currently in
     *
     * @param bkID - the BookID that is currently being undone
     * @param vID - the visitorID associated with this
     * @param visitorStorage - the visitorStorage that contains the visitor
     * @param timeClock - the TimeClock of the library
     * @param bookStorage - the book storage being manipulated
     * @return - the status string with pass/fail
     */
    public String stateUndoCheckout(ArrayList<String> bkID,Long vID, VisitorStorage visitorStorage, TimeClock timeClock, BookStorage bookStorage)
    {
        Visitor currentv = visitorStorage.getVisitor(vID);
        ArrayList<Book> books = new ArrayList<>();

        for(String id : bkID)
        {
            for (Book bk : bookStorage.getLastSearch())
            {
                if (bk.getIsbn().compareTo(id) == 0)
                {
                    books.add(bk);
                    break;
                }
            }
        }

        Calendar c = timeClock.getCalendarDate();

        currentv.returnBooks(books,c);

        return "borrow," + c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH)
                + ";" ;

    }
}
