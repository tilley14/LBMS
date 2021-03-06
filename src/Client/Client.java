package Client;

import Books.Book;
import BooksCatalog.BookCatalog;
import BooksCatalog.FlatFileBookCatalog;
import BooksCatalog.GoogleBooks;
import Visitors.Visitor;
import java.io.File;
import java.util.ArrayList;

/**
 * Description
 *
 * @author Nikolas Tilley
 * @author Jake Bashaw
 */
public class Client
{
    private Long clientId;
    private Visitor visitor;
    private String status;
    private ArrayList<Book> lastStoreSearch;
    private ArrayList<Book> lastStorageSearch;
    private BookCatalog bookCatalog;
    private final File FLATFILE = new File("files/books.txt");
    /**
     * The display string of 
     */

    /**
     * Constructs a Client object.
     *
     * @param clientID -
     */
    public Client(Long clientID)
    {
        this.clientId = clientID;
        this.visitor = null;
        this.bookCatalog = new FlatFileBookCatalog(FLATFILE);
    }


    // -------------------- Getters and setters -------------------- //

    public Long getClientId()
    {
        return clientId;
    }

    public ArrayList<Book> getLastStoreSearch()
    {
        return lastStoreSearch;
    }

    public ArrayList<Book> getLastStorageSearch()
    {
        return lastStoreSearch;
    }

    public void setLastStoreSearch(ArrayList<Book> books)
    {
        this.lastStoreSearch = books;
    }

    public void setClientId(Long clientId)
    {
        this.clientId = clientId;
    }

    public Visitor getVisitor()
    {
        return this.visitor;
    }

    public void setVisitor(Visitor v)
    {
        this.visitor = v;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public BookCatalog getBookCatalog(){
        return this.bookCatalog;
    }

    /**
     * Switches the catalog state between the flat file catalog and the web services catalog.
     */
    public boolean switchCatalogState(String service){
        if(service.equalsIgnoreCase("google")){
            this.bookCatalog = new GoogleBooks();
            return true;
        }
        else if (service.equalsIgnoreCase("local")){
            this.bookCatalog = new FlatFileBookCatalog(FLATFILE);
            return true;
        }
        else
            return false;
    }
}
