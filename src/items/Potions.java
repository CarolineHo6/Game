package items;

public class Potions extends Item {
    private int addHeart;
    private String type;

    public Potions(String id, String name, String description, String type, int addHeart) {
        super(id, name, description, type);
        this.addHeart = addHeart;
        this.type = type;
    }

    public int getAddHeart() {
        return addHeart;
    }

    public void setAddHeart(int h) {
        addHeart = h;
    }

    public String getType() {
        return type;
    }
}
