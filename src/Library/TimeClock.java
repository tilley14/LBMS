package Library;

/**
 *	@author Nikolas Tilley
 * @version 0.1
 */
public class TimeClock
{
    private int offDay ;
    private int offHour ;
    // Need a list of Observers to notify... or one observers

	 public TimeClock()
	 {
	     offDay = 0 ;
        offHour = 0 ;
	 
	 }
    
    /**
     *
     *
     * @param offDay the number of days offset fromt the current date
     * @param offHour the number of hours offset from the current time
	  */
	 public TimeClock(int offDay, int offHour)
	 {
        this.offDay = offDay ;
        this.offHour = offHour ; 
	 }
    
 
    // Add Days to offset
    // Get offset number of days
    public int getOffDay()
    {
        return offDay ;
    }
    
    
    // Add Hours to offset
    // Get offset number of hours
    public int getOffHour()
    {
        return offHour ;
    }
    
    
    /**
     * Notifies the list of subscribers to check the time
     *
     */
    public void Notify()
    {
    }
    
    
    
    
    
    
   public static void main (String [] args) {
       TimeClock time = new TimeClock(2, 4) ;
       
       // Test 1
       if (time.getOffHour() == 4)
           System.out.println("PASSED Test - 1") ;
       else
           System.out.println("FAILED Test - 1: Expected 4, got " + time.getOffHour()) ;
   
   }
    
}
