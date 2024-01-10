package catalogue;

import java.io.Serializable;
import java.util.Collections;

/**
 * checks existing products and increments the quantity if an additional product is added of the same type
 * sorts using collections  
 * @author  Pavan Sahadevan
 * @version 1.0
 */
public class BetterBasket extends Basket implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  /**
   * adds a product to the list, updating quantity if the product already exists,
   * and making sure  the list is sorted after addition
   *
   * @param pr The product to be added to the list
   * @return true if the product was found and updated, otherwise false
   */
  @Override
  public boolean add( Product pr )
  {                              
    for (Product productExists : this) { //iterate through each product in current list
      if(productExists.getProductNum().equals(pr.getProductNum())){ // check if a product already exists 
        productExists.setQuantity(pr.getQuantity() + productExists.getQuantity());// if it exists, print the product and update its quantity
        return true;// return true to indicate that the product was found and updated
      }
    }
    // makes it so product is added then sorted otherwise new element doesnt get sorted 
    boolean temp = super.add(pr); 
    basketSort();
    return temp; 
  }
  /**
   * sorts the list of products using the product numbers in ascending order
   * uses the lambda operator to compare item 1 and item 2 in the basket for sorting
   */
  public void basketSort(){
    //uses lambda operator to compare item 1 and item 2 in the basket then sort it
    Collections.sort(this, (i1, i2) -> i1.getProductNum().compareTo(i2.getProductNum())); 
  }
  
}
