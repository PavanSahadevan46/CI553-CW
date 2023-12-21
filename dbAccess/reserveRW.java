package dbAccess;


import catalogue.Product;
import middle.reserveException;
import middle.ReserveReadWriter;

import java.sql.*;


public class reserveRW  extends reserveR implements ReserveReadWriter{

    public reserveRW() throws reserveException {
        super();
    }


    public synchronized void reserveItem(String pNum, int amount)
    throws reserveException{
        
    try 
    {
       int rowsAffected = getStatementObject().executeUpdate( 
        "INSERT INTO ReserveTable (ProductNo, Quantity) VALUES ('" + pNum + "', '" + amount + "')"

            // "insert into ReserveTable values('" + pNum + "', " + "'" + amount + "' " + ")"
        );

        // check if properly inserted
        if (rowsAffected > 0){
            System.out.println(rowsAffected + "row(s) inserted successfully.");
        }else{
            System.out.println("No rows Inserted.");
        }

        // checking if data was updated to table successfully
        ResultSet rs = getStatementObject().executeQuery(
                "select * from ReserveTable"
        );
        
        while (rs.next()) {
            String reserveID = rs.getString("reserveID");
            String productNo = rs.getString("ProductNo");
            int quantity = rs.getInt("Quantity");
            
            System.out.println("ID: " + reserveID + " " +"ProductNo: " + productNo + ", Quantity: " + quantity);
        }
        rs.close();
    } catch (SQLException e) {
        throw new reserveException("SQL reserveItem: " + e.getMessage());
    }
    
    }




}

   
