package OOP.sem1.Heroes;

import OOP.sem1.TypeOfHeroes.HealerHero;
import OOP.sem1.Vector2;

public class Wizard extends HealerHero {

    public Wizard(String nameHero, int posX, int posY) {
        super(50, 50, 1, new int[]{20, 35}, nameHero, posX, posY, 50, 50);
        Vector2 position;
    }

    @Override
    public String toString() {
        return ("Колдун: " + super.toString());
    }

}
