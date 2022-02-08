
package crashthejett.game;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Egg extends Sprite{
private final Image image;
    public Egg(int x, int y, int speed,int which_way) {
        super(x, y, speed*which_way);
        this.image = new ImageIcon(getClass().getResource("/crashthejett/game/asset/images/egg1.png")).getImage();
    }

  @Override
protected void draw(Graphics2D g2D) {
        g2D.drawImage(this.image,getX() ,getY() , null);
    }
public void update() {
        setX(getX()- getSpeed());
    }
    
public Rectangle getBound(){
        return new Rectangle(getX(),getY(),this.image.getWidth(null),this.image.getHeight(null));
    } 
    
}
  


