import java.util.*;

public class Item {
    private String id;
    private String name;
    private String description;
    private int addHeart;

    public Item(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Item(String id, String name, String description, int addHeart) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.addHeart = addHeart;
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

    public int getAddHeart() {
        return addHeart;
    }
}
