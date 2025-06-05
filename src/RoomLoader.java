import java.io.FileReader;
import java.util.*;
import com.google.gson.*;
import items.*;
import humans.*;

public class RoomLoader {
    public Map<String, Room> loadRooms(String filePath) {
        Map<String, Room> rooms = new HashMap<>();
        try {
            Gson gson = new Gson();
            JsonObject data = gson.fromJson(new FileReader(filePath), JsonObject.class);

            for (String key : data.keySet()) {
                JsonObject obj = data.getAsJsonObject(key);
                System.out.println(obj.get("name"));
                System.out.println(obj.get("description"));
                String name = obj.get("name").getAsString();
                String description = obj.get("description").getAsString();
                String floor = obj.get("floor").getAsString();
                Map<String, String> exits = new HashMap<>();
                JsonObject exitsJson = obj.getAsJsonObject("exits");
                for (String dir : exitsJson.keySet()) {
                    exits.put(dir, exitsJson.get(dir).getAsString());
                }
                boolean isLocked = obj.get("isLocked").getAsBoolean();
                JsonElement keyElement = obj.get("keyID");
                String keyID = (keyElement != null && !keyElement.isJsonNull()) ? keyElement.getAsString() : null;

                // Items array creation
                List<Item> items = new ArrayList<>();
                JsonArray itemArray = obj.getAsJsonArray("items");

                if (itemArray != null) {
                    for (JsonElement e : itemArray) {
                        JsonObject i = e.getAsJsonObject();
                        String type = i.get("type").getAsString().trim().toLowerCase();
                        System.out.println("Raw item JSON: " + i.toString());
                        // using a switch statement to define diff types of items
                        switch (type) {
                            case "item":
                                items.add(new Item(i.get("id").getAsString(), i.get("name").getAsString(),
                                        i.get("description").getAsString(), type));
                                break;
                            case "potion":
                                items.add(new Potions(i.get("id").getAsString(), i.get("name").getAsString(),
                                        i.get("description").getAsString(), type,
                                        i.get("addHeart").getAsInt()));
                                break;
                            case "weapon":
                                items.add(new Weapon(i.get("id").getAsString(), i.get("name").getAsString(),
                                        i.get("description").getAsString(), type,
                                        i.get("attack").getAsInt(), i.get("crit").getAsInt()));
                                break;
                            case "key":
                                items.add(new Key(i.get("id").getAsString(), i.get("name").getAsString(),
                                        i.get("description").getAsString(), type,
                                        i.get("room").getAsString(), i.get("used").getAsBoolean()));
                                break;
                            default:
                                System.out.println("Unknown item type: " + type);
                                break;
                        }
                    }
                }
                System.out.println("Items loaded for room " + key + ":");
                for (Item it : items) {
                    System.out.println(it.getName() + " - " + it.getDescription());
                }

                // NPC array creation
                ArrayList<NPC> npc = new ArrayList<NPC>();
                if (obj.has("npc") && obj.get("npc").isJsonArray()) {
                    JsonArray npcArray = obj.getAsJsonArray("npc");
                    for (JsonElement n : npcArray) {
                        JsonObject i = n.getAsJsonObject();
                        String type = i.get("type").getAsString().trim().toLowerCase();
                        System.out.println("Raw item JSON: " + i.toString());
                        // Making diff objects based on what "type" they are using a switch statement
                        switch (type) {
                            case "npc":
                                npc.add(new NPC(i.get("name").getAsString(), i.get("currentRoom").getAsString(),
                                        i.get("description").getAsString(),
                                        i.get("talk").getAsString(), i.get("id").getAsString(),
                                        type));
                                break;
                            case "miniboss":
                                npc.add(new MiniBoss(i.get("health").getAsInt(), i.get("damage").getAsInt(),
                                        i.get("dodgeRange").getAsInt(), i.get("name").getAsString(),
                                        i.get("currentRoom").getAsString(), i.get("description").getAsString(),
                                        i.get("talk").getAsString(), i.get("id").getAsString(),
                                        type));
                                break;
                            case "boss":
                                npc.add(new MiniBoss(i.get("health").getAsInt(), i.get("damage").getAsInt(),
                                        i.get("dodgeRange").getAsInt(), i.get("name").getAsString(),
                                        i.get("currentRoom").getAsString(), i.get("description").getAsString(),
                                        i.get("talk").getAsString(), i.get("id").getAsString(),
                                        type));
                                break;
                            default:
                                System.out.println("Unknown item type: " + type);
                                break;
                        }
                    }
                }
                System.out.println("Items loaded for room " + key + ":");
                for (Item it : items) {
                    System.out.println(it.getName() + " - " + it.getDescription());
                }

                // Making the acc room
                rooms.put(key, new Room(key, name, description, exits, items, npc, floor, isLocked, keyID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
