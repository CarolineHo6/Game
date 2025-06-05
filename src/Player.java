import java.util.ArrayList;
import items.Item;
import items.Weapon;

public class Player {
    private String currentRoomId;
    private ArrayList<Item> inventory;
    private int health;
    private int attack;

    public Player(String startingRoomId) {
        this.currentRoomId = startingRoomId;
        this.inventory = new ArrayList<>();
        this.health = 100; // hearts
        this.attack = 2; // 2 attack
        inventory.add(new Weapon(startingRoomId, "fist", "you own fists", "weapon", attack, 0));
    }

    public String stats() {
        return "Health: " + health + " Attack: " + attack;
    }

    public String getCurrentRoomId() {
        return currentRoomId;
    }

    public void setCurrentRoomId(String roomId) {
        this.currentRoomId = roomId;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public boolean hasItem(String itemName) {
        return inventory.stream().anyMatch(i -> i.getName().equalsIgnoreCase(itemName));
    }

    public int getAttack() {
        return attack;
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int h) {
        health = h;
    }
}
