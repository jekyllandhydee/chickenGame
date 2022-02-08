package crashthejett.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Target extends Sprite{
private final Image image;
private int size;

    public Target(int x, int y,int speed, int size) {
        super(x, y, speed);
        this.size=size;
        if(size==1) {
            this.image = new ImageIcon(getClass().getResource("/crashthejett/game/asset/images/cepboy.png")).getImage();
        } else {
            this.image = new ImageIcon(getClass().getResource("/crashthejett/game/asset/images/Target.png")).getImage();
        }
    }

    @Override
    protected void draw(Graphics2D g2D) {
        g2D.drawImage(this.image,getX() ,getY() , null);
    }
    public void update() {
        setY(getY()- getSpeed());
    }
    
    public Rectangle getBound(){
        return new Rectangle(getX(),getY(),this.image.getWidth(null),this.image.getHeight(null));
    } 

    int getSize() {
        return size;
    }
    
}