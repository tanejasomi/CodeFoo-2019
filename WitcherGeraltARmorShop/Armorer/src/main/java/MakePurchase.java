import java.util.ArrayList;
import java.util.List;

public class MakePurchase {

    private int crowns;
    private ArrayList<ArrayList<Armor>> inventory;
    private int typeCount;

    public MakePurchase(int crowns, ArrayList<ArrayList<Armor>> inventory) {
        this.crowns = crowns;
        this.inventory = inventory;
        typeCount = inventory.size();
    }

    public Purchase getArmor( List<Armor> items){
        Purchase best = new Purchase();
        best.purchasedArmor = new ArrayList<Armor>();
        for(ArrayList<Armor> armorSet: findArmorSet(items)){
            double cost = getCost(armorSet);
            if(cost <= crowns){
                if(isValidSelection(armorSet)){
                    double value = getValue(armorSet);
                    if(value>best.value){
                        best.value = value;
                        best.cost = cost;
                        best.purchasedArmor = armorSet;
                    }
                }
            }
        }

        return best;
    }

    public List<ArrayList<Armor>> findArmorSet(List<Armor> inventory){
        List<ArrayList<Armor>> validSets = new ArrayList<ArrayList<Armor>>();
        if(inventory == null|| inventory.isEmpty()){
            //Return empty list
            validSets.add(new ArrayList<Armor>());
            return validSets;
        }

        Armor a = inventory.get(0);
        List<ArrayList<Armor>> subsets = findArmorSet(inventory.subList(1,inventory.size()));
        for(List<Armor> subset: subsets){
            //if(isValidSelection(subset)) {
                validSets.add(new ArrayList<Armor>(subset));
            //}
            List<Armor> augmented = new ArrayList<Armor>(subset);
            augmented.add(0,a);
            //if(isValidSelection(augmented)){
                validSets.add(new ArrayList<Armor>(augmented));
            //}

        }
        return validSets;

    }

    public boolean isValidSelection(List<Armor> items) {
        if (items == null) {
            return false;
        }
        ArrayList<ArrayList<Armor>> selItems = new ArrayList<ArrayList<Armor>>();
        for (Armor armor : items) {

            boolean newArmorType = true;
            for (int i = 0; i < selItems.size(); i++) {
                String armorType = selItems.get(i).get(0).armorType;
                if (armorType.equals(armor.armorType)) {
                    newArmorType = false;
                    selItems.get(i).add(armor);
                }
            }

            if (newArmorType) {
                ArrayList<Armor> newArmorList = new ArrayList<Armor>();
                newArmorList.add(armor);
                selItems.add(newArmorList);
            }
        }

        return selItems.size()>=typeCount? true:false;

    }

    public double getCost(List<Armor> inventory){
        double weight = 0;
        for(Armor a:inventory){
            weight+=a.cost;
        }
        return weight;
    }

    public double getValue(List<Armor> inventory){
        double value = 0;
        for(Armor a:inventory){
            value+=a.value;
        }
        return value;
    }

    public int getInventorySize(List<ArrayList<Armor>> inventory){
        int count =0;
        for(ArrayList<Armor> armorLst:inventory ){
            count+=armorLst.size();
        }
        return count;
    }

    public void getCombination(int iNum, ArrayList<Armor> current, ArrayList<Armor> best, int remCrowns ){
        if(remCrowns<=0)
            return;
        int type = iNum/inventory.size();
        int typeNum = iNum%inventory.size();
        Armor armor = inventory.get(type).get(typeNum);
        if(armor.cost <= remCrowns){

        }

    }

}
