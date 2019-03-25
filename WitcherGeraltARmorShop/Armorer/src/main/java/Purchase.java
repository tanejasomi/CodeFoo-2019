import java.util.List;

public class Purchase {
    public List<Armor> purchasedArmor;
    public double cost;
    public double value;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Purchased Armor: ");
        sb.append(" cost=" + cost);
        sb.append(" value=" + value+"\n");


        //Collections.sort(purchasedArmor,Armor.byType());
       /*for(Armor armor: purchasedArmor ){
            sb.append(armor.armorType+":"+armor.name);
            sb.append(", ");
        }*/
        return sb.toString();
    }

    public void printList(List<Armor> armors){
        System.out.println("");
        for(Armor armor: armors){
            System.out.print("");
        }
    }
}
