package org.gamein.model;


//State for serialization
public class Turn {
    public Turn() {
        Coordinates pip;
        pip = new Coordinates(10,20);
        PersonalGoalCard mim = new PersonalGoalCard(pip, pip, pip, pip, pip, pip);
    }
}
