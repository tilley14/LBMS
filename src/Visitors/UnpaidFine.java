package Visitors;
import java.util.Calendar;

/**
 * Provides a structure for persisting fines accumulated by library visitors
 *
 * @author Kyler Freas
 */
public class UnpaidFine implements java.io.Serializable
{
    private int amount;
    private Calendar dateAccumulated;

    /**
     * Default constructor. Date is initialized to current date.
     *
     * @param amount - amount charged due to the fine
     */
    public UnpaidFine(int amount, Calendar dateAccumulated)
    {
        this.amount = amount;
        this.dateAccumulated = dateAccumulated;
    }

    /**
     * Getter for fine's paid date
     *
     * @return fine's paid date
     */
    public Calendar getDateAccumulated()
    {
        return this.dateAccumulated;
    }

    /**
     * Getter for fine's amount
     *
     * @return fine's amount
     */
    public int getAmount()
    {
        return this.amount;
    }
}
