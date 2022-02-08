package crashthejett.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
public class tank extends Sprite{
private final Image image;
    
    public tank(int x, int y, int speed) {
        super(x, y, speed);
        this.image = new ImageIcon(getClass().getResource("/crashthejett/game/asset/images/chicken.jpg")).getImage();
    }

    @Override
    protected void draw(Graphics2D g2D) {
        g2D.drawImage(this.image,getX() ,getY() , null);
    }
    
    void shoot() {
        System.out.println("shooting");    
    }
    
    void moveleft() {
        incSpeed();
        if((getX() - getSpeed()) < 50)
            setX(50);
        else
        setX(getX() - getSpeed());
        System.out.println("moving left");
    }

    void moveright() {
        incSpeed();
        if((getX() + getSpeed()) > GAME__WIDTH - 78)
            setX(GAME__WIDTH - 78);
        else
            setX(getX() + getSpeed());
        System.out.println("moving right");
    }
    
    private void incSpeed(){
        if(getSpeed() < AUTO_SPEED){
            setSpeed(getSpeed() + 1);
        }
    }

    void speedReset() {
        setSpeed(0);
    }
    
    public Rectangle getBound(){
        return new Rectangle(getX(),getY(),this.image.getWidth(null),this.image.getHeight(null));
    } 
}