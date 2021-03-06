package Library;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

/**
 * The time clock keeps track of when the library should be open or closed as well as handling the advancing of time for
 * use in testing of the entire system.
 *
 * @author Nikolas Tilley
 * @author Kyler Freas
 */
public class TimeClock implements java.io.Serializable
{
    private int dayOffset;
    private int hourOffset;
    private static String file = "files/TimeClock.ser";

    /**
     * Creates a Time clock with no offsets applied.
     */
    public TimeClock()
    {
        dayOffset = 0;
        hourOffset = 0;
    }

    /**
     * Creates a TimeClock with offsets for advancements of time.
     *
     * @param dayOffset - The number of days offset from the current date.
     * @param hourOffset - The number of hours offset from the current time.
     */
    public TimeClock(int dayOffset, int hourOffset)
    {
        this.dayOffset = dayOffset;
        this.hourOffset = hourOffset;
    }

    /**
     * Gets the number of offset days.
     *
     * @return The number of days the calendar has been advanced for simulations.
     */
    public int getOffDay()
    {
        return dayOffset;
    }

    /**
     * Gets the number of offset hours.
     *
     * @return The number of hours the calendar has been advanced for simulations.
     */
    public int getOffHour()
    {
        return hourOffset;
    }

    /**
     * Gets the date and time for the instance the method is invoked.
     *
     * @return A Date object that accounts for the the day and hour offsets.
     */
    public Date getCurrentDateTime()
    {
        Calendar c = Calendar.getInstance();
        c.add(c.DAY_OF_WEEK, dayOffset);
        c.add(c.HOUR_OF_DAY, hourOffset);
        return c.getTime();
    }

    /**
     * Gets the date in calendar format.
     *
     * @return A Calendar representing the day of the week and hour of the day.
     */
    public Calendar getCalendarDate()
    {
        Calendar c = Calendar.getInstance();
        c.add(c.DAY_OF_WEEK, dayOffset);
        c.add(c.HOUR_OF_DAY, hourOffset);
        return c;
    }

    /**
     * Formats the current date in the form YYYY/MM/DD.
     *
     * @return A String representing the formatted date.
     */
    public String getFormattedDate()
    {
        Calendar c = getCalendarDate();
        return c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Formats the current time in the form HH:MM:SS.
     *
     * @return A String representing the formatted time.
     */
    public String getFormattedTime()
    {
        Calendar c = getCalendarDate();
        return c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
    }

    /**
     * Formats the current date and time in the form YYYY/MM/DD,HH:MM:SS.
     *
     * @return A String representing the formatted date and time.
     */
    public String getFormattedDateTime()
    {
        Calendar c = getCalendarDate();
        return c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH) + "," +
                c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
    }

    /**
     * Advances the clock forward in Time. The number of days can be between 0 and 7, and the number of hours can be
     * between 0 and 23.
     *
     * @param days - The number of days to advance.
     * @param hours - The number of hours to advance.
     */
    public void advanceTime(int days, int hours)
    {
        dayOffset += days;
        hourOffset += hours;
    }

    /**
     * Serialize the time clock and save it to a text file at library shutdown.
     */
    public void serialize()
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }

    /**
     * Deserializes a TimeClock from the file.
     *
     * @return An instance of VisitorStorage generated from the previously saved .ser file.
     */
    public static TimeClock deserialize()
    {
        try
        {
            // Read from the file into input stream
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // Initialize storage from data
            TimeClock timeClock = (TimeClock) in.readObject();

            // Close the streams and return
            in.close();
            fileIn.close();
            return timeClock;
        }
        catch (EOFException eof)
        {
            // Start a fresh storage
            return new TimeClock();
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("TimeClock could not be found");
            c.printStackTrace();
        }

        // If an error occurs, return an empty storage
        return new TimeClock();
    }


    /**
     * Main method for testing.
     */
    public static void main(String[] args)
    {
        TimeClock time = new TimeClock(2, 4);
        //TimeClock time = new TimeClock();

        // Test 1
        if (time.getOffHour() == 4)
            System.out.println("PASSED Test - 1");
        else
            System.out.println("FAILED Test - 1: Expected 4, got " + time.getOffHour());

        // Test 2
        time.advanceTime(1, 0);
        if (time.getOffDay() == 3)
            System.out.println("PASSED Test - 2");
        else
            System.out.println("FAILED Test - 2: Expected 3, got " + time.getOffDay());

        // Test 3
        if (time.getCurrentDateTime().compareTo(Calendar.getInstance().getTime()) > 0)
            System.out.println("PASSED Test - 3");
        else
            System.out.println("FAILED Test - 3: Expected our calendar with time advanced" +
                    "\nto be greater than the current time, got : " + time.getCurrentDateTime());


        System.out.println(time.getFormattedDateTime());
        System.out.println(time.getFormattedDate());
        System.out.println(time.getFormattedTime());

        /**
         * CAREFUL OF TESTING THIS! MIGHT OVER WRITE IMPORTANT DATA
        // Test Serialization of Time
        time.serialize();

        TimeClock serClock = TimeClock.deserialize() ;
        // Test 4
        if (serClock.getOffHour() == 4)
            System.out.println("PASSED Test - 4");
        else
            System.out.println("FAILED Test - 4: Expected 4, got " + deserialize().getOffHour());
        // Test 5
        if (serClock.getOffDay() == 3)
            System.out.println("PASSED Test - 5");
        else
            System.out.println("FAILED Test - 5: Expected 3, got " + serClock.getOffDay());

        System.out.println("REMEMBER TO DELETE SER AFTER TEST");
        */
    }
}