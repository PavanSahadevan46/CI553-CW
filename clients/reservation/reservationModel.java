package clients.reservation;

import java.util.Observable;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dbAccess.DBAccess;
import dbAccess.DBAccessFactory;
import middle.MiddleFactory;
import middle.reserveException;

public class reservationModel  extends Observable  {
    
    private DefaultTableModel tableModel;

    public reservationModel(MiddleFactory mlf) {
    tableModel = new DefaultTableModel();
    tableModel.addColumn("reserveID");
    tableModel.addColumn("productNo");
    tableModel.addColumn("Quantity");
    }

    private Connection theCon    = null;      // Connection to database
    private Statement  theStmt   = null;      // Statement object
   /**
     * Connecting to database
     * @throws reserveException if error
     */
    public reservationModel()
    throws reserveException
    {   
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

    public synchronized ResultSet retrieveTableData()  {
        try{
            ResultSet rs =getStatementObject().executeQuery( "SELECT * FROM ReserveTable");
        
            //clear existing data 
            tableModel.setRowCount(0);
        
            //populate table with data from ReserveTable
            while (rs.next()) {
                Object[] row = new Object[3];
                row[0] = rs.getObject(1);
                row[1] = rs.getObject(2);
                row[2] = rs.getObject(3);
                tableModel.addRow(row);
            }    
            
            setChanged();
            notifyObservers();
        }catch(SQLException e){
            e.printStackTrace();
        } 
        return null; 
    }  
  
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

}
