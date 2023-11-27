// package dbAccess;
// import java.sql.*;

// import middle.StockException;

// public class reservation {
//     //TODO create a connection to db
//     throws StockException
//   {
//     try
//     {
//       DBAccess dbDriver = (new DBAccessFactory()).getNewDBAccess();
//       dbDriver.loadDriver();
    
//       theCon  = DriverManager.getConnection
//                   ( dbDriver.urlOfDatabase(), 
//                     dbDriver.username(), 
//                     dbDriver.password() );

//       theStmt = theCon.createStatement();
//       theCon.setAutoCommit( true );
//     }
//     catch ( SQLException e )
//     {
//       throw new StockException( "SQL problem:" + e.getMessage() );
//     }
//     catch ( Exception e )
//     {
//       throw new StockException("Can not load database driver.");
//     }

//     //sql count() function returns a count of rows 
//     //basic select update statement that increments based on previous row e.g. if no rows exist than assume 1 then for next user +1 so id = 2
//     //find a way to connect this to customer view so that it enables the user to actually use this
//     //return details about reservation through the customer view pane





// }
  
