
package az.mm.Classes;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author MM
 */
public class ExtraMethods {
    
//------------------------------------------------------------------------------ 
    public Map<Integer, Integer> countDrink(){
      Map<Integer,Integer> count = new LinkedHashMap<Integer, Integer>();
        for(int b = 1; b < 100; b++  ){
          count.put(b, b);
        }
      return count;
    }
    
    
//------------------------------------------------------------------------------    
    public Map<String, String> getDrinkNames(){
      Map<String,String> drink_list = new LinkedHashMap<String, String>();
          drink_list.put("Fanta", "1");
          drink_list.put("Coca-Cola", "2");
          drink_list.put("Pepsi", "3");
          drink_list.put("Sprite", "4");
      return drink_list;
    } 

    
//------------------------------------------------------------------------------    
    public Map<String, String> getBoxSize(){
      Map<String,String> box_size = new LinkedHashMap<String, String>();
          box_size.put("4x5", "4x5");
          box_size.put("5x5", "5x5");
          box_size.put("3x6", "3x6");
          box_size.put("7x3", "7x3");
          box_size.put("8x7", "8x7");
          box_size.put("6x4", "6x4");
          box_size.put("2x8", "2x8");
      return box_size;
    }
    
    
}
