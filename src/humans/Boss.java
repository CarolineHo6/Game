package humans;

public class Boss extends MiniBoss {

    private int stages;
    private String type;

    public Boss(int health, int damage, int dodgeRange, int stages, String name, String currentRoom, String description, String talk,
            String id, String type) {
        super(health, damage, dodgeRange, name, currentRoom, description, talk, id, type);
        this.stages = stages;
        this.type = type;
    }

    public void nextStage(int addDamage, int addHealth) {
        super.setHealth(addHealth);
        super.setDamage(addDamage);
        stages--;
    }

    public int getStages() {
        return stages;
    }

    public String getType() {
        return type;
    }

}
