package moves;

import server.model.Coordinates;
import server.model.Game;
import server.model.Tile;

import java.util.ArrayList;
import java.util.List;

public class MoveSelectTiles implements Move {
    private final String nickName;
    private final int lobbyId;
    private String selectedTiles;
    public static final String className = "MoveSelectTiles";

    public MoveSelectTiles(String nickName, int lobbyId) {
            this.nickName = nickName;
            this.lobbyId = lobbyId;
    }
    @Override
    public String getNickName() {
        return this.nickName;
    }

    @Override
    public int getLobbyId() {
        return this.lobbyId;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean canPerform(Game game) {
        int min = Math.max(game.getBoard().getSlots()[0].length,game.getBoard().getSlots().length) , max = 0;
        char inLine;
        ArrayList<Coordinates> coordArray = new ArrayList<>();
        for (int i = 0; i < this.getSelectedTiles().split(" ").length; i++) coordArray.add(new Coordinates(this.getSelectedTiles().split(" ")[i].charAt(0) - 'A', this.getSelectedTiles().split(" ")[i].charAt(1) - '1'));
        if (coordArray.stream().map(Coordinates::y).distinct().count() != coordArray.size() &&  coordArray.stream().map(Coordinates::x).distinct().count() != coordArray.size()) return false;
        for (Coordinates c : coordArray) if (!game.getBoard().getTile(c.x(), c.y()).isPickable()) return false;
        List<Tile> selectedTilesList = new ArrayList<Tile>();
        for (String s : selectedTiles.split(" ")) {
            selectedTilesList.add(game.getBoard().getTile(s.charAt(0) - 'A', s.charAt(1) - '1'));
        }
        game.setSelectedTiles(selectedTilesList);
        switch (coordArray.size()) {
            case 1 -> {
                return true;
            }
            case 3 -> {
                if (!(((coordArray.get(0).y() == coordArray.get(1).y()) && (coordArray.get(1).y() == coordArray.get(2).y())) || ((coordArray.get(0).x() == coordArray.get(1).x()) && (coordArray.get(1).x() == coordArray.get(2).x()))))
                    return false;
                if ((coordArray.get(0).y() == coordArray.get(1).y()) && (coordArray.get(1).y() == coordArray.get(2).y())) {
                    inLine = 'y';
                } else {
                    inLine = 'x';
                }
                switch (inLine) {
                    case 'y' -> {
                        for (Coordinates c : coordArray) {
                            min = Math.min(min, c.x());
                            max = Math.max(max, c.x());
                            if (max - min > 2) {
                                return false;
                            }
                        }
                    }
                    case 'x' -> {
                        for (Coordinates c : coordArray) {
                            min = Math.min(min, c.y());
                            max = Math.max(max, c.y());
                            if (max - min > 2) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
            case 2 -> {
                if ((coordArray.get(0).y() != coordArray.get(1).y() && coordArray.get(0).x() != coordArray.get(1).x()))
                    return false;
                if ((coordArray.get(0).y() == coordArray.get(1).y())) {
                    inLine = 'y';
                } else {
                    inLine = 'x';
                }
                switch (inLine) {
                    case 'y' -> {
                        for (Coordinates c : coordArray) {
                            min = Math.min(min, c.x());
                            max = Math.max(max, c.x());
                            if (max - min > 1) {
                                return false;
                            }
                        }
                    }
                    case 'x' -> {
                        for (Coordinates c : coordArray) {
                            min = Math.min(min, c.y());
                            max = Math.max(max, c.y());
                            if (max - min > 1) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public void setSelectedTiles(String selectedTiles) {
        this.selectedTiles = selectedTiles;
    }
    public String getSelectedTiles(){
        return this.selectedTiles;
    }
}
