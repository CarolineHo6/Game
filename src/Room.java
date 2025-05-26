import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import humans.NPC;
import items.Item;

public class Room {
    private String id;
    private String name;
    private String description;
    private Map<String, String> exits; // direction → roomId
    private static List<Item> items;
    private static ArrayList<NPC> npc;
    private String floor;

    public Room(String id, String name, String description, Map<String, String> exits, List<Item> items,
            ArrayList<NPC> npc, String floor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exits = exits;
        this.items = items;
        this.npc = npc;
        this.floor = floor;
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

    // remove?
    public NPC randomGenerateMonster() {
        int r = (int) (Math.random() * 2);
        if (npc.size() != 0 && r == 1) {
            int x = (int) (Math.random() * npc.size());
            NPC m = npc.get(x);
            npc.remove(x);
            return m;
        } else {
            return null;
        }
    }

    public String getLongDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        sb.append(description).append("\n");

        if (!items.isEmpty()) {
            sb.append("You see: ");
            for (Item item : items) {
                sb.append(item.getName()).append(", ");
            }
            for (NPC npc : npc) {
                sb.append(npc.getName()).append(", ");
            }
            // Remove trailing comma and space
            sb.setLength(sb.length() - 2);
            sb.append(".\n");
        }

        if (!exits.isEmpty()) {
            sb.append("Exits: ");
            for (String direction : exits.keySet()) {
                sb.append(direction).append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append(".\n");
        }

        return sb.toString();
    }
}
