import java.util.*;

public class Item {
    private String id;
    private String name;
    private String description;
    private int heal;

    public Item(String id, String name, String description, int heal) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.heal = heal;
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

    public int getHeal() {
        return heal;
    }
}
