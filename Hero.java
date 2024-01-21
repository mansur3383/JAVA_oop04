package OOP.sem1;

/**
 * Описание структуры класса
 * <p>
 * Hero - Абстрактный базовый класс, описывающий самые общие черты героев
 * Каждый элемент базового класса имеет следующие поля:
 * - имя (String nameHero),
 * - здоровье (int health),
 * - максимальное здоровье (int healthMax),
 * - защита (int armor),
 * - урон ([]int damage)
 * - местоположение на игровой плоскости (Vector2 position)
 * <p>
 * Наследники Hero:
 * - HealerHero
 * - MeleeHero
 * - RangeHero
 * - Peasant
 * <p>
 * Методы:
 * printEnemysDistance - в качестве аргумента получает список вражеских героев (ArrayList<Hero>) и выводит в консоль расстояние до вражеских героев (void)
 * findMinDistance - в качестве аргумента получает список вражеских героев (ArrayList<Hero>) и возвращает минимальное расстояние до вражеского героя (float)
 * findNearestEnemy - в качестве аргумента получает список вражеских героев (ArrayList<Hero>) и возвращает ближайшего вражеского героя (Hero)
 */

import OOP.sem1.Interfaces.GameI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public abstract class Hero implements GameI {

    public int health, healthMax, armor, initiative;
    public Random random = new Random();
    public int[] damage;
    public String nameHero;
    public Vector2 position;

    /**
     * Конструктор класса Hero
     *
     * @param health    - текущее здоровье (int)
     * @param healthMax - максимальный уровень здоровья (int)
     * @param armor     - защита (int)
     * @param damage    - урон ([]int)
     * @param nameHero  - имя героя (String)
     *                  относятся к полю Position:
     * @param posX      - координата X (0,9) (int)
     * @param posY      - координата Y (0,9) (int)
     */
    public Hero(int health, int healthMax, int armor, int[] damage, String nameHero, int posX, int posY, int initiative) {
        this.health = health;
        this.healthMax = healthMax;
        this.armor = armor;
        this.damage = damage;
        this.nameHero = nameHero;
        this.position = new Vector2(posX, posY);
        this.initiative = initiative;
    }
    public void getDamage(int damage){
        health -= damage;
        if (health < 0) health = 0;
    }

    /**
     * @param enemys - список вражеских героев (ArrayList<Hero>)
     */
    public void printEnemysDistance(ArrayList<Hero> enemys) {
        enemys.forEach(n -> System.out.print(position.rangeEnemy(n.position) + ", "));
        System.out.println();
    }

    /**
     * @param enemys - список вражеских героев (ArrayList<Hero>)
     * @return - расстояние до ближайшего противника (float)
     */
    public float findMinDistance(ArrayList<Hero> enemys) {
        ArrayList<Float> distances = new ArrayList<>();
        enemys.forEach(n -> distances.add(position.rangeEnemy(n.position)));
        float minDistance = Collections.min(distances);
        return minDistance;
    }

    /**
     * @param enemys - список вражеских героев (ArrayList<Hero>)
     * @return - ближайший вражеский герой (Hero)
     */
    public Hero findNearestEnemy(ArrayList<Hero> enemys) {
        Hero heroTMP = enemys.get(0);
        for (int i = 0; i < enemys.size(); i++) {
            if (this.position.rangeEnemy(enemys.get(i).position) < this.position.rangeEnemy(heroTMP.position)) {
                heroTMP = enemys.get(i);
            }
        }
        return heroTMP;
    }
    public int getInitiative(){
        return initiative;
    }
}
