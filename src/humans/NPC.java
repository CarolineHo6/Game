package humans;

public class NPC {
    private String name;
    private String currentRoom;
    private String description;
    private String talk;
    private String id;
    private String type;

    public NPC(String name, String currentRoom, String description, String talk, String id, String type) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.description = description;
        this.talk = talk;
        this.id = id;
        this.type = type;
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

    public String getTalk() {
        return talk;
    }

    public String getName() {
        return name;
    }

}
