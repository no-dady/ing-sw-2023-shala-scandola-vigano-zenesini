package org.gamein.model;

public class PersonalGoalCard {
    Coordinates green, cyan, white, orange, blue, pink;

    public PersonalGoalCard(Coordinates green, Coordinates cyan, Coordinates white, Coordinates orange, Coordinates blue, Coordinates pink) {
        this.green = green;
        this.cyan = cyan;
        this.white = white;
        this.orange = orange;
        this.blue = blue;
        this.pink = pink;
    }

    public boolean conditionCheck(Tile[][] slots)
    {
        if (slots[this.green.y()][this.green.x()].getTileType() == TileType.CAT)
        {
            if (slots[this.cyan.y()][this.cyan.x()].getTileType() == TileType.TROPHY)
            {
                if (slots[this.white.y()][this.white.x()].getTileType() == TileType.BOOK)
                {
                    if (slots[this.orange.y()][this.orange.x()].getTileType() == TileType.TOYS)
                    {
                        if (slots[this.blue.y()][this.blue.x()].getTileType() == TileType.FRAMES)
                        {
                            return slots[this.pink.y()][this.pink.x()].getTileType() == TileType.FLOWERS;
                        }
                    }
                }
            }
        }

        return false;
    }
}
