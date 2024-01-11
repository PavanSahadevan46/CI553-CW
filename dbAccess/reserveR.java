package dbAccess;

import middle.ReserveReader;
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
   * Checks the amount of reservations in the table  
   * ReserveTable contains an auto incrementing id called ReserveID 
   * using count function returns the number of rows that matches reserveID
   * @return returns count of reserveID 
   * @throws reserveException
   */
  public synchronized int checkReservationCount()
                throws reserveException
  {
    int count = 0; // var to store result of query
    try
    {
      // sql query counts number of records in ReserveTable
      ResultSet rs   = getStatementObject().executeQuery(
        "SELECT COUNT(reserveID) AS count FROM ReserveTable"
      );
      // iterate through result set and get count value
      while(rs.next()){
        count = rs.getInt("count");
        }

        // return the count value 
      return count;
    } catch ( SQLException e )
    {
      throw new reserveException( "SQL exists: " + e.getMessage() );
    }
    
  }


   // variable for getter to get reserve id
  int globalReserveID;

  /** retrieves upcoming reserve id from ReserveTable and passes it to global getter
   *@return returns result set containing the reserveID
   *@throws SQLException if error with executing sql query
   */

  public  synchronized ResultSet getReserveID() throws SQLException  {
    
    try {
      ResultSet rs =getStatementObject().executeQuery(
        "SELECT reserveID FROM ReserveTable");
       // Iterate through the result set and update global getter var 
       while (rs.next()) {
        globalReserveID = rs.getInt("reserveID");
        System.out.println("ReserveID: " + globalReserveID);
    }
    
      } catch (SQLException e) {
      System.out.println("error :" + e);
    }
    return null;
  }

  /**getter for getting the next upcoming id
   * 
   * @return global reserveid
   */

  public int getGlobalReserveID(){
    return globalReserveID;
  };


}
