package items;

public class Item {
    private String id;
    private String name;
    private String description;
    private String type;

    public Item(String id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public int getAddHeart() {
        return 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isWeapon() {
        return false;
    }

    public int getAttack() {
        // fix later

        return 0;
    }
}
