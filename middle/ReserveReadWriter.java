package middle;

/**
  * interface for reserving items and retrieving the global reserveID
  * @author  Pavan Sahadevan
  * @version 2.0
  */

public interface ReserveReadWriter extends ReserveReader {
   
  /**
     * reserves a specified amount of an item with the given product number
     *
     * @param pNum    the product number of the item to be reserved
     * @param amount  the amount of the item to be reserved
     * @throws reserveException if an exception related to reservation occurs
     */
    void reserveItem(String pNum, int amount) throws reserveException;
    
    /**
     * retrieves the global reserveID
     *
     * @return The global reserveID
     */
    int getGlobalReserveID();
}
