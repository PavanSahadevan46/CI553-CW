package catalogue;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

/**
 * Write a description of class BetterBasket here.
 * checks existing products and increments the quantity if an additional product is added of the same type
 * sorts using collections util 
 * @author  Pavan Sahadevan
 * @version 1.0
 */
public class BetterBasket extends Basket implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  @Override
  public boolean add( Product pr )
  {                              
    for (Product productExists : this) { //iterate through each product in current list
      if(productExists.getProductNum().equals(pr.getProductNum())){ // check if a product already exists 
        // System.out.println(pr);
        // System.out.println("test2");
        productExists.setQuantity(productExists.getQuantity()+1);// if it exists, print the product and update its quantity
        return true;// return true to indicate that the product was found and updated
      }
    }
    // makes it so product is added then sorted otherwise new element doesnt get sorted 
    boolean temp = super.add(pr); 
    basketSort();
    return temp; 
  }

 //sort the basket
  public void basketSort(){
    // Basket.sort( this, Comparator.comparing(Product::getProductNum));
    Collections.sort(this, (i1, i2) -> i1.getProductNum().compareTo(i2.getProductNum())); 
     //uses lambda operator to compare item 1 and item 2 in the basket then sort it
  }
  
}
