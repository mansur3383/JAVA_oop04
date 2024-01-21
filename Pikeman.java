package OOP.sem1.Heroes;

import OOP.sem1.TypeOfHeroes.MeleeHero;
import OOP.sem1.Vector2;

public class Pikeman extends MeleeHero {

    public Pikeman(String nameHero, int posX, int posY) {
        super(100, 100, 5, new int[]{20, 30}, nameHero, posX, posY, 1);
        Vector2 position;
    }

    @Override
    public String toString() {
        return ("Копейщик: " + super.toString());
    }
}
