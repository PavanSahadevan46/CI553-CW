package dbAccess;

import middle.reserveException;
import middle.ReserveReadWriter;

import java.sql.*;


public class reserveRW  extends reserveR implements ReserveReadWriter{
    // constructor for reserveRW
    public reserveRW() throws reserveException {
        super();
    }

    /**
     *  
     * 
     */
    public synchronized void reserveItem(String pNum, int amount)
    throws reserveException{
        
    try 
    {   // sql query, inserts into the columns of productNo and Quantity the values of pNum and amount 
        //pNum and amount values are gotten from performing the method in customer model 
       int rowsAffected = getStatementObject().executeUpdate( 
        "INSERT INTO ReserveTable (ProductNo, Quantity) VALUES ('" + pNum + "', '" + amount + "')"

        );

        // check if properly inserted for debug purposes
        if (rowsAffected > 0){
            System.out.println(rowsAffected + "row(s) inserted successfully.");
        }else{
            System.out.println("No rows Inserted.");
        }

        //  checking if data was updated to table successfully for debug purposes
        // backup if jtable stops working 

        // ResultSet rs = getStatementObject().executeQuery(
        //         "select * from ReserveTable"
        // );
        //  // iterating over result set to get table 
        // while (rs.next()) {
        //     String reserveID = rs.getString("reserveID");
        //     String productNo = rs.getString("ProductNo");
        //     int quantity = rs.getInt("Quantity");
            
        //     System.out.println("ID: " + reserveID + " " +"ProductNo: " + productNo + ", Quantity: " + quantity);
        // }
        // rs.close();
    } catch (SQLException e) {
        throw new reserveException("SQL reserveItem: " + e.getMessage());
    }
    
    }




}

   
