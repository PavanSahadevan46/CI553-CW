package middle;

import catalogue.Product;
import dbAccess.reserveRW;

/**
  * Interface for read/write access to the reserve list.
  * @author  Pavan Sahadevan
  * @version 2.0
  */

public interface ReserveReadWriter extends ReserveReader {

    void reserveItem(String pNum, int amount) throws reserveException;
    int getGlobalReserveID();
}
