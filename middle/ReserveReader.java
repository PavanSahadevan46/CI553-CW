package middle;

import java.sql.*;

public interface ReserveReader {
   
  /** Checks the count of  reservations in table
   * @throws reserveException if issue
   */
  int checkReservationCount() throws reserveException;

  /** Get upcoming reserveID, achieved through getter var in method
   * 
   * @throws SQLException
   */

  ResultSet getReserveID() throws SQLException;
}
