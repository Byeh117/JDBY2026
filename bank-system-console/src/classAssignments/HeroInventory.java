package classAssignments;

public class HeroInventory {
    private static final int MAX_QUANTITY = 10; // sets the max number of inventory slots available

    private String weapon;
    private String armor;
    private String[] item;
    private int itemQuantity;

    public HeroInventory(String weapon, String armor) {
        if (weapon == null || weapon.isEmpty()) {
            throw new RuntimeException("Weapon slot is empty");
        }
        if (armor == null || armor.isEmpty()) {
            throw new RuntimeException("Armor slot is empty");
        }
        this.weapon = weapon;
        this.armor = armor;
        this.item = new String[MAX_QUANTITY];
        this.itemQuantity = 0;
    }

    public void addItem(String itemName) {
        if (itemQuantity >= MAX_QUANTITY) {
            throw new RuntimeException("Inventory is full, cannot add anymore items");
        }
        if (itemName == null || itemName.isEmpty()) {
            throw new RuntimeException("Item is not in inventory");
        }
        item[itemQuantity] = itemName;
        itemQuantity++;
        System.out.println("\"" + itemName + "\" has been added to your inventory");
    }

    public void equipWeapon(String newWeapon) {
        if (newWeapon == null || newWeapon.isEmpty()) {
            throw new RuntimeException("No new weapons here");
        }
        System.out.println("Weapon swapped, \"" + newWeapon + "\" now equipped");
        this.weapon = newWeapon;
    }

    public void equipArmor(String newArmor) {
        if (newArmor == null || newArmor.isEmpty()) {
            throw new RuntimeException("No new armor here");
        }
        System.out.println("Armor swapped, \"" + newArmor + "\" now equipped");
        this.armor = newArmor;
    }

    public String getWeapon() { return weapon; }
    public String getArmor() { return armor; }
    public String[] getItem() { return item; }
    public int getItemQuantity() { return itemQuantity; }
}
