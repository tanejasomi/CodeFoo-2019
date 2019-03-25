import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/********************************************************************
 *@Author Somya Taneja
 * @Description: Inventory reads input from file
 * Updates Inventory by sorting items ontypes
 */

public class Inventory {
    ArrayList<ArrayList<Armor>> inventory;
    List<Armor> items;

    /********************************************************************
     *
     */

    public void sortInventory(ArrayList<Armor> items) {
        if (items == null) {
            return;
        }
        ArrayList<ArrayList<Armor>> sItems = new ArrayList<ArrayList<Armor>>();
        for (Armor armor : items) {
            boolean newArmorType = true;
            for (int i = 0; i < sItems.size(); i++) {
                String armorType = sItems.get(i).get(0).armorType;
                if (armorType.equals(armor.armorType)) {
                    newArmorType = false;
                    sItems.get(i).add(armor);
                }
            }

            if (newArmorType) {
                ArrayList<Armor> newArmorList = new ArrayList<Armor>();
                newArmorList.add(armor);
                sItems.add(newArmorList);
            }
        }
        for(ArrayList<Armor> armorLst: sItems){
            Collections.sort(armorLst,Armor.byRatio());
        }
        setInventory(sItems);
    }

    public ArrayList<ArrayList<Armor>> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<ArrayList<Armor>> inventory) {
        this.inventory = inventory;
    }

    public void printInvt(List<Armor> inv) {
        for (Armor armor : inv) {
            System.out.println(armor.toString());
        }

    }


    //Used For Testing only
    public ArrayList<Armor> createInventory1() {
        ArrayList<Armor> invt = new ArrayList<Armor>();
        Armor a1 = new Armor("helmet", "h1", 90, 23);
        Armor a2 = new Armor("helmet", "h2", 77, 24);
        Armor a3 = new Armor("helmet", "h3", 68, 19);
        Armor a32 = new Armor("helmet", "h32", 68, 19);
        Armor a33 = new Armor("helmet", "h3", 68, 19);
        Armor a34 = new Armor("helmet", "h3", 68, 19);
        invt.add(a1);
        invt.add(a2);
        invt.add(a3);
        Armor a4 = new Armor("legging", "l1", 87, 22);
        Armor a5 = new Armor("legging", "l2", 78, 18);
        Armor a6 = new Armor("legging", "l3", 75, 15);
        Armor a7 = new Armor("legging", "l4", 69, 17);
        invt.add(a4);
        invt.add(a5);
        invt.add(a6);
        invt.add(a7);
        Armor a8 = new Armor("chest", "c1", 67, 22);
        Armor a9 = new Armor("chest", "c2", 64, 23);
        invt.add(a8);
        invt.add(a9);
        Armor a10 = new Armor("boots", "b1", 64, 18);
        Armor a11 = new Armor("boots", "b2", 52, 14);
        Armor a12 = new Armor("boots", "b3", 52, 20);
        Armor a13 = new Armor("boots", "b4", 35, 7);
        Armor a14 = new Armor("boots", "b5", 33, 5);
        invt.add(a10);
        invt.add(a11);
        invt.add(a12);
        invt.add(a13);
        invt.add(a14);

        this.items = invt;
        sortInventory(invt);


        return invt;
    }
    public List<Armor> getItems() {
        return items;
    }
}

