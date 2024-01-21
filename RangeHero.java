package OOP.sem1.TypeOfHeroes;

import OOP.sem1.Hero;

import java.util.ArrayList;
import java.util.Random;

/**
 * Описание структуры класса
 * Абстрактный класс, описывающий тип героев, которые будут наносить урон в дальнем бою
 * Каждый элемент данного класса имеет следующие дополнительные поля:
 * - максимальный запас стрел (int quantityShotsMax),
 * - запас стрел (int quantityShots),
 * - дистанция нанесения максимального укрона (int rangeMaxDamage)
 * <p>
 * Наследники HealerHero:
 * - Crossbower
 * - Sniper
 * <p>
 * Методы:
 * getShoot - метод нанесения урона вражескому герою (с дистанции)
 */
public abstract class RangeHero extends Hero {
    int quantityShotsMax, quantityShots, rangeMaxDamage;
    private int damagePoint;

    public RangeHero(int health, int healthMax, int armor, int[] damage, String nameHero, int posX, int posY, int quantityShots, int quantityShotsMax, int rangeMaxDamage) {
        super(health, healthMax, armor, damage, nameHero, posX, posY, 3);
        this.quantityShots = quantityShots;
        this.quantityShotsMax = quantityShotsMax;
        this.rangeMaxDamage = rangeMaxDamage;
    }

//    public void getShoot(Hero target) {
//        int damagePoint = (this.position.rangeEnemy(target.position) < rangeMaxDamage)? this.random.nextInt(damage[0], damage[1]): damage[0];
//        target.getDamage((this.position.rangeEnemy(target.position) < rangeMaxDamage)? this.random.nextInt(damage[0], damage[1]): damage[0]);
//    }

    public Hero findBestEnemyRDD(ArrayList<Hero> enemys) { //дописать проверку на жизнь
        Hero heroTMP = enemys.get(0);
        for (int i = 0; i < enemys.size(); i++) {
            if (heroTMP.health < 1) {
                heroTMP = enemys.get(i);
            }
            if (this.position.rangeEnemy(enemys.get(i).position) < this.position.rangeEnemy(heroTMP.position)) {
                heroTMP = enemys.get(i);
            }
        }
        if (this.position.rangeEnemy(heroTMP.position) > this.rangeMaxDamage) {
            for (Hero enemy : enemys) {
                if (heroTMP.health > enemy.health) {
                    heroTMP = enemy;
                }
            }
        }
        return heroTMP;
    }

    @Override
    public String toString() {
        return (nameHero + " здоровье: " + health + "/" + healthMax + " броня: " + armor + " Выстрелы " + this.quantityShots + "/" + this.quantityShotsMax);
    }

    @Override
    public void gameStep(ArrayList<Hero> teamEnemy, ArrayList<Hero> teamAllies) {
        if (this.health == 0 || this.quantityShots == 0) return;
        Hero target = findBestEnemyRDD(teamEnemy);
        target.getDamage((this.position.rangeEnemy(target.position) < rangeMaxDamage)? this.random.nextInt(damage[0], damage[1]): damage[0]);
        System.out.println("Нанесен урон" + this.damagePoint);
        quantityShots--;
    }
}
