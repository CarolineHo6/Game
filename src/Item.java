import java.util.*;

public class Item {
    private String id;
    private String name;
    private String description;
<<<<<<< HEAD
    private boolean isWeapon;

    public Item(String id, String name, String description, boolean isWeapon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isWeapon = isWeapon;
=======
    private int heal;

    public Item(String id, String name, String description, int heal) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.heal = heal;
>>>>>>> 68be78f5bde3932ce20e82027b7eb9615369e5aa
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
