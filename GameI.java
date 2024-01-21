package OOP.sem1.Interfaces;

import OOP.sem1.Hero;

import java.util.ArrayList;

public interface GameI {
    void gameStep(ArrayList<Hero> teamEnemy, ArrayList<Hero> teamAllias);
    //void gameStep(ArrayList<Hero> teamEnemy);

}
