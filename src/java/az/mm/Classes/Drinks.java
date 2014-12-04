
package az.mm.Classes;

import java.io.Serializable;

/**
 *
 * @author MM
 */
public class Drinks implements Serializable {
    
    private boolean checked_fanta, checked_cola, checked_pepsi, checked_sprite, use_box_size;
    private int count_fanta, count_cola, count_pepsi, count_sprite;

    
    public Drinks() {
    }
  
    
//-- getter & setter -----------------------------------------------------------    
    public boolean isChecked_fanta() {
        return checked_fanta;
    }

    public void setChecked_fanta(boolean checked_fanta) {
        this.checked_fanta = checked_fanta;
    }

    public boolean isChecked_cola() {
        return checked_cola;
    }

    public void setChecked_cola(boolean checked_cola) {
        this.checked_cola = checked_cola;
    }

    public boolean isChecked_pepsi() {
        return checked_pepsi;
    }

    public void setChecked_pepsi(boolean checked_pepsi) {
        this.checked_pepsi = checked_pepsi;
    }

    public boolean isChecked_sprite() {
        return checked_sprite;
    }

    public void setChecked_sprite(boolean checked_sprite) {
        this.checked_sprite = checked_sprite;
    }

    public boolean isUse_box_size() {
        return use_box_size;
    }

    public void setUse_box_size(boolean use_box_size) {
        this.use_box_size = use_box_size;
    }

    public int getCount_fanta() {
        return count_fanta;
    }

    public void setCount_fanta(int count_fanta) {
        this.count_fanta = count_fanta;
    }

    public int getCount_cola() {
        return count_cola;
    }

    public void setCount_cola(int count_cola) {
        this.count_cola = count_cola;
    }

    public int getCount_pepsi() {
        return count_pepsi;
    }

    public void setCount_pepsi(int count_pepsi) {
        this.count_pepsi = count_pepsi;
    }

    public int getCount_sprite() {
        return count_sprite;
    }

    public void setCount_sprite(int count_sprite) {
        this.count_sprite = count_sprite;
    }
    
    
    
} //end class
