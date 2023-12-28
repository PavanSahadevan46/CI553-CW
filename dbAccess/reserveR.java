package dbAccess;

import catalogue.Product;
import debug.DEBUG;
import middle.ReserveReader;
import middle.StockException;
import middle.reserveException;

import java.sql.*;

public class reserveR implements ReserveReader
{
    private Connection theCon    = null;      // Connection to database
    private Statement  theStmt   = null;      // Statement object
        

    /**
     * Connecting to database
     * @throws reserveException if error
     */
    public reserveR()
    throws reserveException{   
      try{
        DBAccess dbDriver = (new DBAccessFactory()).getNewDBAccess();
        dbDriver.loadDriver();
      
        theCon  = DriverManager.getConnection
                    ( dbDriver.urlOfDatabase(), 
                      dbDriver.username(), 
                      dbDriver.password() );

        theStmt = theCon.createStatement();
        theCon.setAutoCommit( true );
      }
      catch ( SQLException e )
      {
        throw new reserveException( "SQL problem:" + e.getMessage() );
      }
      catch ( Exception e )
      {
        throw new reserveException("Can not load database driver.");
      }
  }
  /**
   * Returns a statement object that is used to process SQL statements
   * @return A statement object used to access the database
   */
  
      protected Statement getStatementObject()
      {
          return theStmt;
      }

      /**
       * Returns a connection object that is used to process
       * requests to the DataBase
       * @return a connection object
       */

      protected Connection getConnectionObject()
      {
          return theCon;
      }
  
  /**
   * Checks if any rows exist to see if any reservations are made already 
   * ReserveTable contains an auto incrementing id called ReserveID 
   * 
   * @param rID
   * @return
   * @throws reserveException
   */
  public synchronized boolean exists(String rID)
                throws reserveException
  {
    
    try
    {
      ResultSet rs   = getStatementObject().executeQuery(
        "select * from ReserveTable"
       
      );
      boolean res = rs.next();
      DEBUG.trace( "DB StockR: exists(%s) -> %s", 
                    rID, ( res ? "T" : "F" ) );
      return res;
    } catch ( SQLException e )
    {
      throw new reserveException( "SQL exists: " + e.getMessage() );
    }
  }


   
  int globalReserveID;
  public ResultSet getReserveID() throws SQLException  {
    
    try {
      ResultSet rs =getStatementObject().executeQuery(
        "SELECT T1.reserveID FROM ReserveTable T1 JOIN ReserveTable T2 ON T1.reserveID = T2.reserveID + 1 ORDER BY T1.reserveID");
       // Iterate through the result set and print values
       while (rs.next()) {
        // int reserveID = rs.getInt("reserveID");
        globalReserveID = rs.getInt("reserveID");
        // System.out.println("ReserveID: " + globalReserveID);
    }
    
      } catch (SQLException e) {
      System.out.println("error :" + e);
    }
    return null;
  }

  public int getGlobalReserveID(){
    return globalReserveID;
  };


}
