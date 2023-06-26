package moves;

import server.model.Coordinates;
import server.model.Game;

import java.util.ArrayList;

public class MoveSelectTiles extends Move {
    private String selectedTiles;
    private final String classname = "MoveSelectTiles";

    public MoveSelectTiles(String nickName) {
        super(nickName);
    }

    @Override
    public String getName() {
        return classname;
    }

    @Override
    public boolean canPerform(Game game) {
        int min = Math.max(game.getBoard().getSlots()[0].length,game.getBoard().getSlots().length) , max = 0;
        char inLine;
        ArrayList<Coordinates> coordArray = new ArrayList<>();
        for (int i = 0; i < this.getSelectedTiles().split(" ").length; i++) coordArray.add(new Coordinates(this.getSelectedTiles().split(" ")[i].charAt(0) - 'A', this.getSelectedTiles().split(" ")[i].charAt(1) - '1'));
        if (coordArray.stream().map(Coordinates::y).distinct().count() != coordArray.size() &&  coordArray.stream().map(Coordinates::x).distinct().count() != coordArray.size()) return false;
        for (Coordinates c : coordArray) if (!game.getBoard().getTile(c.x(), c.y()).isPickable()) return false;
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
