package middle;

import java.sql.SQLException;
import java.sql.*;

// import catalogue.Product;


public interface ReserveReader {
   
    /**
   * Checks if a reservation exists
   * @param rID reservation ID autoincremented in database
   * @return true if exists otherwise false
   * @throws reserveException if issue
   */
  boolean exists(String rID) throws reserveException;

  ResultSet getReserveID() throws SQLException;
}
