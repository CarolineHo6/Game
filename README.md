# Bayview Glen Escape

Bayview Glen Escape is a Java Swing text-adventure game set inside a locked-down school. Players explore rooms, collect items, solve riddles, unlock restricted areas, interact with characters, and fight monsters while trying to escape.

The game uses a JSON-backed room system, allowing the world map, items, NPCs, locks, and descriptions to be managed separately from the Java source code.

## Features

- Graphical desktop interface built with Java Swing
- Room-based exploration with directional movement
- JSON-loaded map, room descriptions, items, NPCs, exits, and locks
- Inventory management for collecting, using, and dropping items
- Key-based and riddle-based room unlocking
- NPC interaction through dialogue
- Combat system with weapons, health, damage, and dodge behavior
- External Gson dependency included in the `lib` folder

## Project Structure

```text
.
├── README.md
├── rooms.json
├── lib/
│   └── gson-2.13.1.jar
└── src/
    ├── Main.java
    ├── Game.java
    ├── AdventureGUI.java
    ├── CommandParser.java
    ├── Player.java
    ├── Room.java
    ├── RoomLoader.java
    ├── humans/
    │   ├── NPC.java
    │   ├── MiniBoss.java
    │   └── Boss.java
    └── items/
        ├── Item.java
        ├── Key.java
        ├── Potions.java
        └── Weapon.java
```

## Requirements

- Java Development Kit 8 or newer
- Gson 2.13.1, already included at `lib/gson-2.13.1.jar`

## Running the Game

From the project root, compile the source files:

```bash
javac -cp "lib/gson-2.13.1.jar" -d bin src/*.java src/items/*.java src/humans/*.java
```

Then launch the game:

```bash
java -cp "bin:lib/gson-2.13.1.jar" Main
```

On Windows, use a semicolon instead of a colon in the classpath:

```bash
java -cp "bin;lib/gson-2.13.1.jar" Main
```

## Gameplay Commands

Enter commands into the text box at the bottom of the game window.

| Command | Description |
| --- | --- |
| `go [direction]` | Move between rooms. Example: `go east` |
| `look` | Display the current room description again |
| `take [item]` | Pick up an item in the current room |
| `drop [item]` | Remove an item from your inventory |
| `inventory` | View all carried items |
| `use [item]` | Use a potion or weapon |
| `talk to [NPC]` | Speak with a character in the room |
| `kill [NPC]` | Attack a monster or boss |
| `read notebook` | Read the notebook introduction |
| `open [room] with [key]` | Unlock a room using the correct key |
| `solve [answer]` | Answer a riddle for a locked room |
| `help` | Show the command list in-game |
| `quit` | Start the quit confirmation flow |

## Game Data

The world is defined in `rooms.json`. Each room can include:

- A unique room ID
- Display name and description
- Directional exits
- Items such as keys, weapons, potions, or readable objects
- NPCs, minibosses, or bosses
- Floor information
- Lock status and required key ID

This structure makes it possible to expand the game by adding new rooms, exits, characters, and items without rewriting the core game loop.

## Main Classes

- `Main` starts the application.
- `Game` initializes the room loader, player, and command processor.
- `AdventureGUI` builds and updates the Swing interface.
- `CommandParser` handles player commands and game logic.
- `RoomLoader` reads `rooms.json` using Gson.
- `Room` stores room state, exits, riddles, items, and NPCs.
- `Player` tracks the current room, health, attack, and inventory.

## Notes

- The game starts in the `entrance` room.
- The player begins with 100 health and a basic weapon.
- Some rooms require keys, while others require solving riddles.
- Bosses and minibosses must be defeated before moving past them.
- Room images are loaded from an `images/` directory using the current room ID as the filename, such as `images/entrance.png`.

## License

This project is intended for educational use.
