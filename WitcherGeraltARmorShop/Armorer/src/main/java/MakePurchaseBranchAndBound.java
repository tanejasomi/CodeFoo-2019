import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**************************************************
 * /
 //go through inventory and segregate
 //Solution
 //Part a: Select combination with one of each kind with maximum value and minimum cost
 //Part b: Select combination with remaining available crowns

 /*public static void main(String[] arg){
 Inventory i = new Inventory();
 i.createInventory1();

 }*/

public class MakePurchaseBranchAndBound {
    protected List<Armor> inventory;
    protected int crowns;

    private class Item implements Comparable<Item>{
        public int level;
        List<Armor> taken;
        public double bound;
        public double value;
        public double cost;

        public Item(){
            taken = new ArrayList<Armor>();
        }

        public Item(Item parent){
            level = parent.level +1;
            taken = new ArrayList<Armor>();
            bound = parent.bound;
            value = parent.value;
            cost = parent.cost;
        }

        public int compareTo(Item item){
            return (int)(item.bound - bound);
        }

        public void computeBound(){
            int i = level;
            double c = cost;
            bound = value;
            Armor armor;
            do{
                armor = inventory.get(i);
                if(c + armor.cost >crowns)
                    break;
                c+=armor.cost;
                bound +=armor.value;
                i++;
            }while(i<inventory.size());

            bound +=(crowns - c)*(armor.value/armor.cost);

        }

    }

    MakePurchaseBranchAndBound(List<Armor> inventory, int crowns){
        this.inventory = inventory;
        this.crowns = crowns;
    }

    public Purchase purchaseArmor(){
        Collections.sort(inventory,Armor.byRatio());
        Item best = new Item();
        Item root = new Item();
        root.computeBound();

        PriorityQueue<Item> q = new PriorityQueue<Item>();
        q.offer(root);

        while(!q.isEmpty()){
            Item item = q.poll();
            if(item.bound > best.value && item.level<inventory.size()-1){
                Item withItem = new Item(item);
                Armor armor = inventory.get(item.level);
                withItem.cost +=armor.cost;

                if(withItem.cost<=crowns){
                    withItem.taken.add(inventory.get(item.level));
                    withItem.value +=armor.value;
                    withItem.computeBound();
                    if(withItem.value >best.value){
                        best=withItem;
                    }
                    if(withItem.bound > best.value){
                        q.offer(withItem);
                    }
                }

                Item withoutItem = new Item(item);
                withoutItem.computeBound();

                if(withoutItem.bound>best.value){
                    q.offer(withoutItem);
                }

            }

        }

        Purchase purchase = new Purchase();
        purchase.purchasedArmor = best.taken;
        purchase.value = best.value;
        purchase.cost = best.cost;

        return purchase;
    }


    /***********************************
     *
     * @param inventory
     * @return
     */
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





}
