package crashthejett.game;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Background extends Sprite {

    private final Image image;
    
    public Background(int x, int y, int speed) {
        super(x, y, speed);
        this.image = new ImageIcon(getClass().getResource("/crashthejett/game/asset/images/bg.png")).getImage();
    }

    @Override
    protected void draw(Graphics2D g2D) {
        g2D.drawImage(this.image, getX(), getY(), null);
    }
}