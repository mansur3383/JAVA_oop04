package OOP.sem1.TypeOfHeroes;

import OOP.sem1.Hero;
import OOP.sem1.Interfaces.GameI;
import OOP.sem1.Vector2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Описание структуры класса
 * Абстрактный класс, описывающий тип героев, которые будут наносить урон в ближнем бою
 * Каждый элемент данного класса имеет следующие дополнительные поля:
 * - шаг (int step)
 * <p>
 * Наследники HealerHero:
 * - Pikeman
 * - Rogue
 * <p>
 * Методы:
 * getDamage - метод нанесения урона вражескому герою (в ближнем бою)
 */
public abstract class MeleeHero extends Hero {
    int step, damagePoint;

    public MeleeHero(int health, int healthMax, int armor, int[] damage, String nameHero, int posX, int posY, int step) {
        super(health, healthMax, armor, damage, nameHero, posX, posY, 2);
        this.step = step;
    }

    public void getDamage(Hero target) {
        if (this.position.rangeEnemy(target.position) < 2) {
            damagePoint = this.random.nextInt(damage[0], damage[1]);
            target.health = target.health - damagePoint;
        } else {
            System.out.println("Где я?");
        }
    }

    public Hero findBestEnemyMDD(ArrayList<Hero> enemys) {
        Hero heroTMP = enemys.get(0);
        for (int i = 0; i < enemys.size(); i++) {
            if (this.position.rangeEnemy(enemys.get(i).position) < this.position.rangeEnemy(heroTMP.position) && enemys.get(i).health > 0) {
                heroTMP = enemys.get(i);
            }
        }
        return heroTMP;
    }

    public Vector2 getStepMDD(Hero enemy) {
        Vector2 delta = position.getDelta(enemy.position); //return new Vector2(posX - posEnemy.posX, posY - posEnemy.posY);
        Vector2 tmpVector2 = new Vector2(position.posX, position.posY);
        if (delta.posX < 0) {
            tmpVector2.posX++;
            return tmpVector2;
        }
        if (delta.posX > 0) {
            tmpVector2.posX--;
            return tmpVector2;
        }
        if (delta.posY < 0) {
            tmpVector2.posY++;
            return tmpVector2;
        }
        if (delta.posY > 0) {
            tmpVector2.posY--;
            return tmpVector2;
        }
        return tmpVector2;

    }

    @Override
    public String toString() {
        return (nameHero + " здоровье: " + health + "/" + healthMax + " броня: " + armor);
    }

    @Override
    public void gameStep(ArrayList<Hero> teamEnemy, ArrayList<Hero> teamAllies) {
        if (this.health == 0) return;
        Hero tmpHero = findBestEnemyMDD(teamEnemy);
        if (position.rangeEnemy(tmpHero.position) < 2){
            getDamage(tmpHero);
            System.out.println("Нанесен урон" + this.damagePoint);
        } else {
            Vector2 tmpVec = getStepMDD(tmpHero);
            boolean step = true;
            for (Hero hero: teamAllies) {
                if (tmpVec.equals(hero.position)) step = false;
            }
            if (step) position = tmpVec;
        }
    }
}
