
import java.awt.Color;
import java.awt.Graphics;

public class carro {
    private int x;
    private int y;
    private int diameter;
    private Color color;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public carro(int x, int y, int diameter, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
    }

    public void moveRight(int distance) {
        x += distance;
    }

    public void moveLeft(int distance) {
        x -= distance;
    }
}
