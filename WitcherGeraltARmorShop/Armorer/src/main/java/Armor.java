import java.util.Comparator;
/***********************************************************
 * @Author Somya Taneja
 * @Description: Model class to store armor details and related functions
 * @Params:
 *
 *
 *
 */
public class Armor {
    String armorType;
    String name;
    int cost;
    int value;

    public Armor(String armorType, String name, int cost, int value) {
        this.armorType = armorType;
        this.name = name;
        this.cost = cost;
        this.value = value;
    }

    public Armor(){

    }

    public Armor(String[] val) {

        this.armorType =val[0] ;
        this.name = val[1];
        this.cost = integerValueOf(val[2]);
        this.value = integerValueOf(val[3]);
    }

    public static Comparator<Armor> byType(){
        return new Comparator<Armor>() {
            public int compare(Armor o1, Armor o2) {
                if(o1.armorType.equals(o2.armorType))
                    return 0;
                else
                    return 1;
            }
        };

    }

    public static Comparator<Armor>byRatio(){
        return new Comparator<Armor>(){
            public int compare(Armor a1, Armor a2){
                return Double.compare(a2.getRatio(),a1.getRatio());
            }
        };
    }

    public double getRatio(){
        return value/cost;
    }


    public int integerValueOf(String s){
        //s = s.replaceAll("\\s+","");
        s = s.trim();
        System.out.println(s.trim());
        return Integer.parseInt(s);

    }

    @Override
    public String toString() {
        return "Armor{" +
                "armorType='" + armorType + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", value=" + value +
                '}';
    }

    public String getArmorType() {
        return armorType;
    }

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
