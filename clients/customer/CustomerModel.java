package clients.customer;

import catalogue.Basket;
import catalogue.Product;
import debug.DEBUG;
import middle.*;

import javax.swing.*;

import java.sql.SQLException;
import java.util.Observable;

/**
 * Implements the Model of the customer client
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */
public class CustomerModel extends Observable
{
  private Product     theProduct = null;          // Current product
  private Basket      theBasket  = null;          // Bought items

  private String      pn = "";                    // Product being processed
  private Product      pr = null;                    // Product being processed 
 
  private ReserveReadWriter theReserve = null; //  creating object of reserve read writer 

  private StockReader     theStock     = null;
  private StockReadWriter theStockRW     = null;

  private OrderProcessing theOrder     = null;
  private ImageIcon       thePic       = null;

  /*
   * Construct the model of the Customer
   * @param mf The factory to create the connection objects
   */
  public CustomerModel(MiddleFactory mf)
  {
    try                                           
    {  
      theStock = mf.makeStockReader();           // Database access
      theStockRW = mf.makeStockReadWriter();           // Database access for read write
      theReserve = mf.makeReserveReadWriter();
    } catch ( Exception e )
    {
      DEBUG.error("CustomerModel.constructor\n" +
                  "Database not created?\n%s\n", e.getMessage() );
    }
    theBasket = makeBasket();                    // Initial Basket
  }
  
  /**
   * return the Basket of products
   * @return the basket of products
   */
  public Basket getBasket()
  {
    return theBasket;
  }

  /**
   * Check if the product is in Stock
   * @param productNum The product number
   */
  public void doCheck(String productNum )
  {
    theBasket.clear();                          // Clear s. list
    String theAction = "";
    pn  = productNum.trim();                    // Product no.
    int    amount  = 1;                         //  & quantity
    try
    {
      if ( theStock.exists( pn ) )              // Stock Exists?
      {                                         // T
        Product pr = theStock.getDetails( pn ); //  Product
        if ( pr.getQuantity() >= amount )       //  In stock?
        { 
          theAction =                           //   Display 
            String.format( "%s : %7.2f (%2d) ", //
              pr.getDescription(),              //    description
              pr.getPrice(),                    //    price
              pr.getQuantity() );               //    quantity
          pr.setQuantity( amount );             //   Require 1
          theBasket.add( pr );                  //   Add to basket
          thePic = theStock.getImage( pn );     //    product
        } else {                                //  F
          theAction =                           //   Inform
            pr.getDescription() +               //    product not
            " not in stock" ;                   //    in stock
        }
      } else {                                  // F
        theAction =                             //  Inform Unknown
          "Unknown product number " + pn;       //  product number
      }
    } catch( StockException e )
    {
      DEBUG.error("CustomerClient.doCheck()\n%s",
      e.getMessage() );
    }
    setChanged(); notifyObservers(theAction);
  }

  /**
   * Clear the products from the basket
   */
  public void doClear()
  {
    String theAction = "";
    theBasket.clear();                        // Clear s. list
    theAction = "Enter Product Number";       // Set display
    thePic = null;                            // No picture
    setChanged(); notifyObservers(theAction);
  }
  
  public void doReserve()
  {
    int    amount  = 1;   // amount to decrement stock by
    String theAction = ""; // product currently being processed 
    
    // if stock exists of entered product number
    try { 
      if ( theStock.exists( pn ) )
      {
      Product pr = theStock.getDetails( pn ); // get desciription of product
      if ( pr.getQuantity() >= amount ) // check if quantity is greater than 1
      {
      theStockRW.buyStock(pn,amount); // decrement 1 from stock of product
      theReserve.reserveItem(pn,amount); // reserve item with the product number and amount
      theReserve.getReserveID(); // call to get reservationid
      theAction = ("Your Reservation id is: " + String.valueOf(theReserve.getGlobalReserveID())); // updating text area to give user back their id
      theReserve.checkReservationCount(); // used for staff to see total amount of reservationss
      System.out.println("Amount of reservations is: " + theReserve.checkReservationCount()); 
      setChanged(); notifyObservers(theAction);
      };
    };
    
      
    } catch (StockException|SQLException|reserveException e) {
      System.out.println("Error whilst reserving item: " + e);
      e.printStackTrace();
    }

  }
  /**
   * Return a picture of the product
   * @return An instance of an ImageIcon
   */ 
  public ImageIcon getPicture()
  {
    return thePic;
  }
  
  /**
   * ask for update of view callled at start
   */
  private void askForUpdate()
  {
    setChanged(); notifyObservers("START only"); // Notify
  }

  /**
   * Make a new Basket
   * @return an instance of a new Basket
   */
  protected Basket makeBasket()
  {
    return new Basket();
  }
}

