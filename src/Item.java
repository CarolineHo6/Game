import java.util.*;

public class Item {
    private String id;
    private String name;
    private String description;
    private boolean isWeapon;

    public Item(String id, String name, String description, boolean isWeapon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isWeapon = isWeapon;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isWeapon() {
        return false;
    }

    public String getDescription() {
        return description;
    }

    public int getAttack() {
        // fix later

        return 0;
    }
}
