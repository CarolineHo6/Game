package humans;
public class NPC {
    private String name;
    private String currentRoom;
    private boolean isHostile;
    private String description;
    private String talk;

    public NPC(String name, String currentRoom, boolean isHostile, String description, String talk) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.isHostile = isHostile;
        this.description = description;
        this.talk = talk;
    }

    public String getDescription() {
        return description;
    }

    public void setCurrentRoomId(String currentRoomId) {
        currentRoom = currentRoomId;
    }

    public String getRoom() {
        return currentRoom;
    }

    public boolean getIsHostility() {
        return isHostile;
    }

    public String getTalk() {
        return talk;
    }

    public String getName() {
        return name;
    }


}
