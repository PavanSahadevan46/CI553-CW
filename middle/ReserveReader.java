package middle;

import java.sql.*;

// import catalogue.Product;


public interface ReserveReader {
   
    /**
   * Checks the count of  reservations in table
   * @throws reserveException if issue
   */
  int checkReservationCount() throws reserveException;

  ResultSet getReserveID() throws SQLException;
}
