import java.util.HashMap;

public class CastleMap {
    static HashMap<String, CastleRoom> map;

    public CastleMap() { //creates hashmap for the castle
        map = new HashMap<>();
    }

    public static void addRoom(CastleRoom room) {
        map.put(room.returnRoomName().toLowerCase(), room); //adds room to the castle map
    }

    public CastleRoom getName(String room) { //get name of room from castle map
        return map.get(room);
    }
}