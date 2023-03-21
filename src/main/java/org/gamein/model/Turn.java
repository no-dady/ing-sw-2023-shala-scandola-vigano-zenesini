package org.gamein.model;


//State for serialization
public class Turn {
    public Turn() {
        Coordinate pip;
        pip = new Coordinate(10,20);
        PersonalGoalCard mim = new PersonalGoalCard(pip, pip, pip, pip, pip, pip);
    }
}
