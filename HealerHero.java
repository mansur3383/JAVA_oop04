package OOP.sem1.TypeOfHeroes;

import OOP.sem1.Hero;

import java.util.ArrayList;
import java.util.Random;

/**
 * Описание структуры класса
 * Абстрактный класс, описывающий тип героев, которые будут исцелять союзных героев
 * Каждый элемент данного класса имеет следующие дополнительные поля:
 * - мана (int mana),
 * - максимальное значение маны (int manaMax)
 * <p>
 * Наследники HealerHero:
 * - Wizard
 * - Monk
 * <p>
 * Методы:
 * getHealing - метод лечения союзного героя
 */
public abstract class HealerHero extends Hero {
    int mana, manaMax, healingPoint;

    /**
     * Конструктор
     */
    public HealerHero(int health, int healthMax, int armor, int[] damage, String nameHero, int posX, int posY, int mana, int manaMax) {
        super(health, healthMax, armor, damage, nameHero, posX, posY, 1);
        this.mana = mana;
        this.manaMax = manaMax;
    }

    /**
     * Метод лечения цели
     */
    public void getHealing(Hero target) {
        if (target.health > 0) {
            healingPoint = this.random.nextInt(damage[0], damage[1]);
            target.health = target.health + healingPoint;
        }
    }
    public Hero findMinHealthAllies(ArrayList<Hero> allies) {
        Hero heroTMP = allies.get(0);
                for (Hero ally: allies ) {
                    if (heroTMP.health > ally.health && ally.health > 0){
                        heroTMP = ally;
                    }
                }
        return heroTMP;
    }
    @Override
    public String toString() {
        return (nameHero + " здоровье: " + health + "/" + healthMax + " броня: " + armor);
    }

    @Override
    public void gameStep(ArrayList<Hero> teamAllies, ArrayList<Hero> teamEnemy ) {
        if (this.health > 0) {
            getHealing(findMinHealthAllies(teamAllies));
            System.out.println("Нанесен урон" + this.healingPoint);
        }
    }
}
