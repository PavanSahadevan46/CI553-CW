package catalogue;

import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BetterBasketTest {
   
    // BetterBasket basket;

    // @BeforeEach
    // void setupBasket(){
    //     BetterBasket basket = new BetterBasket();
    // }
    
    
    
    @Test
    
    void testBetterBasket(){

        BetterBasket basket = new BetterBasket();
        
        Product p1 = new Product("0001", "TV", 10.00, 1);
        Product p2 = new Product("0001", "TV", 10.00, 1);
        Product p3 = new Product("0002", "Watch", 12.00, 1);
        Product p4 = new Product("0002", "Watch", 12.00, 2);

         //test p1 and p4 get merged properly
        basket.add(p1);
        basket.add(p2);
        assertEquals(1, basket.size() ,"Did not merge properly");
        // checking expected value of 1 at index 0 in arraylist retrieved by getQuantity();
        assertEquals(2, basket.get(0).getQuantity() ,"Did not merge properly"); 
        
        
        // checking size of array after adding a DIFFERENT product
        basket.add(p3);
        assertEquals(2, basket.size(),"Size of arraylist is incorrect without merging");
       
        //checking quanity of watch updated to 3
        basket.add(p4);
        assertEquals(3,basket.get(1).getQuantity(),"merging of p3 and p4 did not work");
        
        // using stream to iterate over elements in arraylsit to and print the items
        basket.stream().forEach(p -> System.out.println(p.getQuantity())); 


    }

    @Test
    void testSortBasket(){
        BetterBasket basket = new BetterBasket();

        Product p1 = new Product("0001", "TV", 10.00, 1);
        Product p2 = new Product("0002", "Mouse", 10.00, 1);
        Product p3 = new Product("0003", "Watch", 12.00, 1);
        Product p4 = new Product("0004", "Keyboard", 12.00, 1);
        // Adding products to the basket
        basket.add(p3);
        basket.add(p1);
        basket.add(p4);
        basket.add(p2);

        // Sorting the basket
        basket.basketSort();

        // Asserting the order after sorting
        assertEquals(p1, basket.get(0), "First product should be TV");
        assertEquals(p2, basket.get(1), "Second product should be Mouse");
        assertEquals(p3, basket.get(2), "Third product should be Watch");
        assertEquals(p4, basket.get(3), "Fourth product should be Keyboard");
      
        // using stream to iterate over elements in arraylsit to and print the item's descriptions
        basket.stream().forEach(p -> System.out.println(p.getDescription())); 




    }








}
