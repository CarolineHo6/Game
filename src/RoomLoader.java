import java.io.FileReader;
import java.util.*;
import com.google.gson.*;
import humans.NPC;
import humans.Enemies;
import humans.Boss;

public class RoomLoader {
    public Map<String, Room> loadRooms(String filePath) {
        Map<String, Room> rooms = new HashMap<>();
        try {
            Gson gson = new Gson();
            JsonObject data = gson.fromJson(new FileReader(filePath), JsonObject.class);

            for (String key : data.keySet()) {
                JsonObject obj = data.getAsJsonObject(key);
                String name = obj.get("name").getAsString();
                String desc = obj.get("description").getAsString();
                String floor = obj.get("floor").getAsString();
                Map<String, String> exits = new HashMap<>();
                JsonObject exitsJson = obj.getAsJsonObject("exits");
                for (String dir : exitsJson.keySet()) {
                    exits.put(dir, exitsJson.get(dir).getAsString());
                }
                List<Item> items = new ArrayList<>();
                JsonArray itemArray = obj.getAsJsonArray("items");
                for (JsonElement e : itemArray) {
                    JsonObject i = e.getAsJsonObject();
                    items.add(new Item(i.get("id").getAsString(), i.get("name").getAsString(),
                            i.get("description").getAsString()));
                }
                ArrayList<NPC> npc = new ArrayList<NPC>();
                JsonArray npcArray = obj.getAsJsonArray("npc");
                for (JsonElement n : npcArray) {
                    JsonObject i = n.getAsJsonObject();
                    // TODO add if statement for type
                    if (i.get("type").getAsString().equals("NPC")) {
                        npc.add(new NPC(i.get("name").getAsString(), i.get("currentRoom").getAsString(),
                                i.get("isHostile").getAsBoolean(), i.get("description").getAsString(),
                                i.get("talk").getAsString(), i.get("id").getAsString(), i.get("type").getAsString()));
                    } else if (i.get("type").getAsString().equals("Enemy")) {
                        npc.add(new Enemies(i.get("health").getAsInt(), i.get("damage").getAsInt(), 
                        i.get("dodgeRange").getAsInt(), i.get("name").getAsString(), 
                        i.get("currentRoom").getAsString(), i.get("description").getAsString(), 
                        i.get("talk").getAsString(), i.get("id").getAsString(), i.get("type").getAsString()));
                    } else if (i.get("type").getAsString().equals("Boss")) {
                        npc.add(new Boss(i.get("h").getAsInt(), i.get("d").getAsInt(), 
                        i.get("dr").getAsInt(), i.get("stages").getAsInt(), i.get("name").getAsString(), 
                        i.get("currentRoom").getAsString(), i.get("description").getAsString(), 
                        i.get("talk").getAsString(), i.get("id").getAsString(), i.get("type").getAsString()));
                    }
                }

                rooms.put(key, new Room(key, name, desc, exits, items, npc, floor));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
