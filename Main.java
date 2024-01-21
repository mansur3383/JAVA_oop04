package OOP.sem1;

import OOP.sem1.Heroes.*;
import OOP.sem1.TypeOfHeroes.HealerHero;
import OOP.sem1.TypeOfHeroes.MeleeHero;
import OOP.sem1.TypeOfHeroes.Peasant;
import OOP.sem1.TypeOfHeroes.RangeHero;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        heroesWhite = generateCommand(0,0);
        heroesBlack = generateCommand(3,9);
        ArrayList<Hero> twoCommands = new ArrayList<>();
        twoCommands.addAll(heroesBlack);
        twoCommands.addAll(heroesWhite);
        twoCommands.sort(((o1, o2) -> o2.getInitiative()- o1.getInitiative())); //сортировка лямбдой
        twoCommands.sort(new Comparator<Hero>() {
            @Override
            public int compare(Hero o1, Hero o2) {
                return o2.getInitiative() - o1.getInitiative();
            }
        });
        for (Hero h: twoCommands) {
            if (heroesBlack.contains(h)){
                h.gameStep(heroesWhite, heroesBlack);
            } else {
                h.gameStep(heroesBlack, heroesWhite);
            }
        }
    }

    static ArrayList<Hero> heroesWhite = new ArrayList<>();
    static ArrayList<Hero> heroesBlack = new ArrayList<>();

    static ArrayList<Hero> generateCommand(int n, int y) {
        ArrayList<Hero> commandHeroes = new ArrayList<>();
        Random random = new Random();
        int rand;


        for (int i = 0; i < 10; i++) {
            rand = random.nextInt(1, 5) + n;
            switch (rand) {
                case 1:
                    commandHeroes.add(new Crossbower(getName(), i, y));
                    break;
                case 2:
                    commandHeroes.add(new Monk(getName(), i, y));
                    break;
                case 4:
                    commandHeroes.add(new Peasant(getName(), i, y));
                    break;
                case 3:
                    commandHeroes.add(new Pikeman(getName(), i, y));
                    break;
                case 5:
                    commandHeroes.add(new Rogue(getName(), i, y));
                    break;
                case 6:
                    commandHeroes.add(new Sniper(getName(), i, y));
                    break;
                case 7:
                    commandHeroes.add(new Wizard(getName(), i, y));
                    break;

            }
        }
        return commandHeroes;
    }

    static String getName() {
        return NameOfHeroes.values()[new Random().nextInt(NameOfHeroes.values().length - 1)].name();
    }
}
