package dbAccess;

import catalogue.Product;
import debug.DEBUG;
import middle.ReserveReader;
import middle.StockException;
import middle.reserveException;

import java.sql.*;

import javax.naming.spi.DirStateFactory.Result;

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
   * Checks the amount of reservations in the table  
   * ReserveTable contains an auto incrementing id called ReserveID 
   * 
   * @param 
   * @return
   * @throws reserveException
   */
  public synchronized int checkReservationCount()
                throws reserveException
  {
    int count = 0;
    try
    {
      ResultSet rs   = getStatementObject().executeQuery(
        "SELECT COUNT(reserveID) AS count FROM ReserveTable"
      );
      while(rs.next()){
        count = rs.getInt("count");
        }


      return count;
    } catch ( SQLException e )
    {
      throw new reserveException( "SQL exists: " + e.getMessage() );
    }
    
  }


   
  int globalReserveID;
  public ResultSet getReserveID() throws SQLException  {
    
    try {
      ResultSet rs =getStatementObject().executeQuery(
        "SELECT reserveID FROM ReserveTable");
       // Iterate through the result set and print values
       while (rs.next()) {
        globalReserveID = rs.getInt("reserveID");
        System.out.println("ReserveID: " + globalReserveID);
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
