import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import humans.*;
import items.*;

public class Room {
    private String id;
    private String name;
    private String description;
    private Map<String, String> exits; // direction → roomId
    private List<Item> items;
    private ArrayList<NPC> npc; // one npc per room
    private static ArrayList<String> riddles = new ArrayList<String>(); // make riddles!!!!!!!!
    private static ArrayList<String> answer = new ArrayList<String>(); // put the answers at bottom make sure they
                                                                       // correspond w the index of the
                                                                       // question
    private String floor;
    private boolean isLocked;
    private String keyID;

    public Room(String id, String name, String description, Map<String, String> exits, List<Item> items,
            ArrayList<NPC> npc, String floor, boolean isLocked, String keyID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exits = exits;
        this.items = items;
        this.npc = npc;
        this.floor = floor;
        this.isLocked = isLocked;
        this.keyID = keyID;
    }

    public boolean isRiddle() { // checks if a room is suppose to have a riddle or not
        if (isLocked && keyID == null) {
            return true;
        }

        return false;
    }

    public void removeRiddle(String x) {
        int index = riddles.indexOf(x);
        riddles.remove(index);
        answer.remove(index);

    }

    public String generateRandomRiddle() {
        int x = (int) (Math.random() * riddles.size());

        return riddles.get(x) + " " + answer.get(x);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return description;
    }

    public Map<String, String> getExits() {
        return exits;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<NPC> getNPCs() {
        return npc;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public String getKeyID() {
        return keyID;
    }

    // remove?
    // public NPC randomGenerateMonster() {
    // int r = (int) (Math.random() * 2);
    // if (npc.size() != 0 && r == 1) {
    // int x = (int) (Math.random() * npc.size());
    // NPC m = npc.get(x);
    // npc.remove(x);
    // return m;
    // } else {
    // return null;
    // }
    // }

    public String getLongDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        sb.append(description).append("\n");

        if (!items.isEmpty()) {
            sb.append("Items:\n");
            for (Item item : items) {
                sb.append("- ").append(item.getName()).append(": ").append(item.getDescription()).append("\n");
                System.out.println("item name and description");
            }
        }
        if (!npc.isEmpty()) {
            sb.append("You see:\n");
            for (NPC n : npc) {
                sb.append("- ").append(n.getName()).append(": ").append(n.getDescription()).append("\n");
                System.out.println("npc name and description");
            }
        }
        if (!exits.isEmpty()) {
            sb.append("Exits: ");
            for (String direction : exits.keySet()) {
                sb.append(direction).append(", ");
            }
            // Remove trailing comma and space
            sb.setLength(sb.length() - 2);
            sb.append(".\n");
        }

        return sb.toString();
    }

    static {
        riddles.add(
                "I have keys but open no doors. I have space but no room. You can enter, but not go outside. What am I?");
        riddles.add("The more of me you take, the more you leave behind. What am I?");
        riddles.add(
                "No heartbeat, no breath, but I can still follow you. I copy your every move, but only in the light. What am I?");
        riddles.add(
                "You pass me every day, but never speak to me. I reflect what you are, and in this school, sometimes what you fear. What am I?");
        riddles.add(
                "You can’t see me, but I decide when you panic. I make your heart race, your hands shake, and your breath quicken. I’m the only curve your calculator can’t flatten. What am I?");
        riddles.add(
                "I was your last hope for passing. Now I hold the last piece of the key. But I won’t open unless you remember everything I taught. What am I?");
        riddles.add("I’m always running, but I never move. You can never catch me, but you always lose me. What am I?");
        riddles.add("I have cities but no houses, forests but no trees, and rivers but no water. What am I?");
        riddles.add("I turn polar bears white and I will make you cry.\n" + //
                "I make guys have to pee and girls comb their hair.\n" + //
                "I make celebrities look stupid and normal people look like celebrities.\n" + //
                "I turn pancakes brown and make your champagne bubble.\n" + //
                "If you squeeze me, I'll pop. If you look at me, you'll pop. ");
        riddles.add(
                "You are a prisoner in a room with 2 doors and 2 guards. One of the doors will guide you to freedom and behind the other is a hangman–you don't know which is which, but the guards do know.\n"
                        + //
                        "\n" + //
                        "One of the guards always tells the truth and the other always lies. You don't know which one is the truth-teller or the liar either. However both guards know each other.\n"
                        + //
                        "\n" + //
                        "You have to choose and open one of these doors, but you can only ask a single question to one of the guards.\n"
                        + //
                        "\n" + //
                        "Which door will you choose?");

        answer.add("Keyboard");
        answer.add("Footsteps");
        answer.add("Shadow");
        answer.add("Mirror");
        answer.add("Anxiety");
        answer.add("Exam");
        answer.add("Time");
        answer.add("Map");
        answer.add("Time");
        answer.add("the other door");
    }

}
