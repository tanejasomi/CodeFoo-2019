import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] arg){
        Scanner scanner= new Scanner(System.in);
        int count = scanner.nextInt();

        /*List<Armor> items = new ArrayList<Armor>();
        for(int i=0;i<count;i++){
            String type = scanner.next();
            String name = scanner.next();
            int weight = scanner.nextInt();
            int value = scanner.nextInt();
            Armor armor = new Armor(type,name,weight,value);
            items.add(armor);
        }*/

        //int crowns = scanner.nextInt();
        int crowns = 300;
        Inventory i = new Inventory();

        List<Armor> inventory = i.createInventory1();
        System.out.println("INVENTORY");
        i.printInvt(inventory);

        /*MakePurchaseBranchAndBound mp = new MakePurchaseBranchAndBound(inventory, crowns);
        Purchase p = mp.purchaseArmor();
        System.out.println(p.toString());*/
        MakePurchase mp = new MakePurchase(crowns,i.getInventory());
        Purchase p = mp.getArmor(i.items);
        System.out.println(p.toString());











    }
}
