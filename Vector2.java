package OOP.sem1;

public class Vector2 {

    public int posX, posY;

    public Vector2(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public float rangeEnemy(Vector2 posEnemy) {
        double distance = Math.sqrt(Math.pow(posEnemy.posY - posY, 2) + Math.pow(posEnemy.posX - posX, 2));
        return (float) distance;
    }
    public Vector2 getDelta(Vector2 posEnemy){
        return new Vector2(posX - posEnemy.posX, posY - posEnemy.posY);
    }

   public boolean equals(Vector2 posEnemy) {
        return posX == posEnemy.posX & posY == posEnemy.posY;
    }
}
