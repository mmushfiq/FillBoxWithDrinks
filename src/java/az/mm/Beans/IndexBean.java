
package az.mm.Beans;

import az.mm.Classes.Drinks;
import az.mm.Classes.ExtraMethods;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author MM
 * 
 * İşləmə prinsipi:
 * 1.Seçilən içkilərin siyahısı listə yığılır (List drinks)
 * 2.İçkilərin tələb edilən ardıcılıqla yığılması üçün kletkanın ölçüsünə uyğun boş bir list yaradılır (fillDrinksOrderList() metodunun ichinde, drinks_order)
 * 3.İçkilərin siyahısı dövrə salınır və içkilərin siyahısı (listin elementləri) bir-bir oxunur
 * 4.Əvvəlcə içki siyahısında olan ilk içki boş listə əlavə olunur
 * 5.Sonra içki siyahısından dövrlə gələn müvafiq içki drinks_order listindəki sonuncu içki və üstündəki içki ilə eyni olub-olmaması müqayisə olunur
 * 6.Eyni içki olarsa, bu içki ötürülür və dövrə görə siyahıda olan digər içki yoxlanılır
 * 7.Eger eyni içki olmazsa drinks_order listinə əlavə edilir və əlavə edilmiş içkilər içkilərin siyahısıdan (drinks) silinir
 * 8.İçki siyahısında olan son içki drinks_order listinə əlavə olunanadək bu dövr təkrar işləyir
 * 9.Əgər seçilmiş içkilərin sayı, seçilmiş müvafiq kletkaya yerləşməzsə onda yeni bir kletka (drinks_order listi) da yaradılır və o da həmin prinsiplə doldurulur
 * 
 * 
 */
@Named(value = "indexBean")
@RequestScoped
public class IndexBean implements Serializable {
    
    Drinks d = new Drinks();
    ExtraMethods mthd = new ExtraMethods();
    private Map<String,String> all_boxes_size = mthd.getBoxSize();
    
    private String box_size;
    private int row, column, max_list_size;
    private int last_drinks_size;
    
    private List drinks = new ArrayList();
    private ArrayList columns_list = new ArrayList();
    private Map<Integer, ArrayList> drinks_order_map = new TreeMap<Integer, ArrayList>(); //sorted olsun deye HashMap`i TreeMap`le evez eledim
    private int drinks_order_map_key = 1;
    private boolean while_running = true;
    
    
//------------------------------------------------------------------------------    
    public void showFilledBox(){
        fillDrinksList();       //sechilmish ichkilerin siyahisi liste doldurulur
        fillDrinksOrderList();  //ichkilerin siyasi teleb olunan ardicilliqda yeni liste doldurulur
    }
    
//------------------------------------------------------------------------------    
    public void determineBoxSize(){
        if(d.isUse_box_size()){ //qutunun combobox`dan sechilmish standart olchusu
           row = Integer.parseInt(box_size.split("x")[0]);
           column = Integer.parseInt(box_size.split("x")[1]);
           max_list_size = row*column; //kletkada yerleshe bilecek maksimum ichkilerin sayi
           columns_list.add(column);
        } else { //qutunun olchusu manual sechilmezse random olaraq teyin olunur
           row = 3 + (int)(Math.random() * 5);     // 3-7 arasi qiymetler alir ---> 3 + (int)(Math.random() * ((7 - 3) + 1)) 
           column = 3 + (int)(Math.random() * 6);  // 3-8 arasi qiymetler alir
           max_list_size = row*column; 
           columns_list.add(column);
        }
    }

    
//------------------------------------------------------------------------------    
    private  void fillDrinksList(){
        
        drinks.clear();
        
        if(d.isChecked_fanta()){
            for(int i=0; i<d.getCount_fanta(); i++){
               drinks.add("1");
            }
        }
        if(d.isChecked_cola()){
            for(int i=0; i<d.getCount_cola(); i++){
               drinks.add("2");
            }
        }
        if(d.isChecked_pepsi()){
            for(int i=0; i<d.getCount_pepsi(); i++){
               drinks.add("3");
            }
        }
        if(d.isChecked_sprite()){
            for(int i=0; i<d.getCount_sprite(); i++){
               drinks.add("4");
            }
        }
        if((!d.isChecked_fanta() && !d.isChecked_cola() && !d.isChecked_pepsi() && !d.isChecked_sprite()) || (d.getCount_fanta()+d.getCount_cola()+d.getCount_pepsi()+d.getCount_sprite() == 0)){
            notCheckDrink();
        }

    }
    
    
//------------------------------------------------------------------------------     
    private void fillDrinksOrderList(){
      
      determineBoxSize(); //listin max olchusu teyin edilir ve her cedvel uchun sutun sayi teyin edilib liste yigilir
      ArrayList drinks_order = new ArrayList(); //her yeni qutu olchusune uygun list yaradilir

      while(drinks.size() >= 0 && while_running){ //ichkilerin siyahisi yeni listlere elave olunub qutaranadek while dovru ishleyir
          
        if(drinks.size() == 0){
            if (drinks_order.size() < max_list_size) { //kletkanin bosh qalan yerleri kletkanin olchusune uygun olaraq bosh xanalarla doldurulur
              for (int i = drinks_order.size(); i < max_list_size; i++) {
                 drinks_order.add("empty");
              }
            }
            drinks_order_map.put(drinks_order_map_key, drinks_order); 
            while_running=false;
        } else {
        
            if (drinks_order.size() != max_list_size) { //eger kletka hele dolmayibsa ichkiler hemin kletkaya doldurulmaga davam edir

                for (int i = 0; i < drinks.size(); i++) {  //sechilmish ichkilerin siyahisi bir-bir oxunur

                    if (drinks_order != null && drinks_order.size() < max_list_size) {  //kletkanin tutumu dolanadek bu shert ishleyir
                        if (drinks_order.isEmpty()) {  //ilk ichki elave olunanda hech bir ardicilliq sherti yoxlanilmir
                            drinks_order.add(drinks.get(i));  //ilk ichki elave olunur
                            drinks.remove(i);                 //ve elave olunan ichki siyahidan chixarilir
                        } else {
                            if (drinks_order != null && !drinks_order.isEmpty() && (drinks.get(i) != drinks_order.get(drinks_order.size()-1) || drinks_order.size()%column == 0)) { //ichki siyahisindan gelen ichki ile elave olunacaq listdeki son ichki bir birile eyni deyilse ve ya elave olunacaq listdeki ichki kletkanin sonuncu sutunundaki ichkidirse
                                if (drinks_order.size() >= column) {
                                    if (drinks.get(i) != drinks_order.get(drinks_order.size() - column)) { //kletkanin ilk setiri dolandan sonra artiq elave olunacaq ichkinin onun yuxarisindaki ichki ile eyni olub olmadigi da yoxlanilir
                                        drinks_order.add(drinks.get(i));
                                        drinks.remove(i);
                                    } 
                                } else {
                                    drinks_order.add(drinks.get(i));
                                    drinks.remove(i);
                                }
                            }
                        }
                    }
                }

                if(last_drinks_size == drinks.size()){ //ichki siyahisi dovrle oxunub qutardiqdan sonra eger sayi deyishilmirse, demeli siyahida qalan ichkileri ardicilliga uygun elave etmek mumkun deyil, arada bosh xana buraxilir
                    drinks_order.add("empty");
                }
                
        /*  Optimalligi temin etmek uchun (drinks_order.size()%column==0) sherti yuxari if`den chixarilib burda ayrica verilmelidi, max sayda ichkini minimum sayda qutuya yerleshdirmek uchun
            Sadece kod uzun olduguna gore hele ki commente atdim..
                
                if(last_drinks_size == drinks.size()){ //ichki siyahisi dovrle oxunub qutardiqdan sonra eger sayi deyishilmirse, demeli siyahida qalan ichkileri ardicilliga uygun elave etmek mumkun deyil, arada bosh xana buraxilir
                    if(drinks_order.size()%column==0){ //eger kletkanin sonuncu sutunudursa, ondan sonra gelecek ichki ile eyni de ola biler, chunki yanashi dushmurler, amma..
                        for(int i=0; i<drinks.size(); i++){
                            if(drinks.get(i) != drinks_order.get(drinks_order.size() - column)){ //..elave olunacaq ichki onun ustundeki ichki ile eyni olmamalidi
                                if(drinks_order.add(drinks.get(i))){ //qalan ichki siyahisinda bu sherti odeyen ichki varsa, elave edilir..
                                    drinks.remove(i); //..ichki siyahidan chixarilir..
                                    break;  //..ve dovr sonlanir
                                } 
                            }
                        }
                        if(last_drinks_size == drinks.size()) { //yox eger yuxaridaki sherti odeyen ichki yoxdursa, bosh xana elave edilir
                                drinks_order.add("empty");
                            }
                    } else {
                        drinks_order.add("empty");
                    }   
                }
            */    
                
                last_drinks_size=drinks.size();


            } else {
                drinks_order_map.put(drinks_order_map_key++, drinks_order); //dolmush list map`a elave olunur
                fillDrinksOrderList(); //ve qalan ichkilerin doldurulmasi uchun metod yeniden chagilir
            }
        }
        
      } 
        
    } //end method


     
//------------------------------------------------------------------------------ 
    private void notCheckDrink(){ //ichki sechilmeyibse ekrana bu barede bildirish chixarir    
         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "İçki seçilməmişdir!", "Nəticəni görmək üçün içkini seçin");
         FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
//------------------------------------------------------------------------------    
    public Map<String, String> getAll_boxes_size() { return all_boxes_size; }

    public Map<Integer, ArrayList> getDrinks_order_map() { return drinks_order_map; }
    
    public ArrayList getColumns_list() { return columns_list; }

    public String getBox_size() { return box_size; }
    public void setBox_size(String box_size) { this.box_size = box_size; }

    public Drinks getD() { return d; }
    public void setD(Drinks d) { this.d = d; }

    
    
} //End IndexBean
