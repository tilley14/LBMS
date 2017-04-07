package LBMSCommands;

import Library.Library;
import java.util.ArrayList;

/**
 * Returns a book borrowed by a library visitor.
 *
 * Command format: clientID,return,visitor ID,id[,ids];
 *
 * @author Nikolas Tilley
 * @author Kyler Freas
 */
public class ReturnBook implements LBMSCommand
{
    private Library library;
    // todo private Long clientID
    private Long visitorID;
    private ArrayList<String> bookID;

    /**
     * Creates a ReturnBook command, which will return a book for a registered visitor.
     *
     * @param library - The library that the book is being returned to.
     * @param visitorID - The ID of the visitor that is returning the book.
     * @param bookID - The temporary ID of the book that is to be returned.
     */
    public ReturnBook(Library library, Long visitorID, ArrayList<String> bookID)
    {
        this.library = library;
        this.visitorID = visitorID;
        this.bookID = bookID;
    }

    /**
     * Executes the ReturnBook command on the library.
     */
    public void execute()
    {
        this.library.returnBooks(this.visitorID, this.bookID);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }





    private void parse(String s) {
        ArrayList<String> args = new ArrayList<String>();
        // List of book IDS

        String arg = "";


        for (char c : s.toCharArray()) {

            if (c == ',' || c == ';') {
                args.add(arg);
                arg = "";
            } else
                arg += c;
        }
        // Todo, when you see {} start adding to list of ids
        // Todo cast to proper types and set use try/catches
    }
}
