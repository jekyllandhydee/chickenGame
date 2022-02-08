package crashthejett.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GamePanel extends Canvas implements Runnable{
    
    private Thread gameThread;
    private final Sprite background = new Background(0, 0, 0);
    private final Sprite background1 = new Background(0, 0, 0);
    private final tank tank = new tank(GAME__WIDTH/2 - 60, GAME__HEIGHT -550, 3);
    private boolean isRunning;
    private final ArrayList<Egg> eggs = new ArrayList<>();
    private final ArrayList<Cat> cats = new ArrayList<>();
    private final ArrayList<Target> targets = new ArrayList<>();
    public static int which_way = 1;
    public int  LEVEL_COOFICIENT=1;
    private int life=3;
    private int score=0;
    
    public GamePanel(){
        setPreferredSize(new Dimension(GAME__WIDTH,GAME__HEIGHT));
    }
    @Override
    public void addNotify(){
        super.addNotify();
        if(gameThread == null){
            gameThread = new Thread(GamePanel.this);
        }
        gameThread.start();
    }
    @Override
    protected void onKeyUp(KeyEvent event) {
        tank.speedReset();
    }

    @Override
    protected void onKeyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_SPACE){
            
            
            eggs.add(new Egg(tank.getX(), tank.getY(),5,which_way));
            tank.shoot();
        }else if(event.getKeyCode() == KeyEvent.VK_LEFT){
            tank.moveleft();
            which_way = 1;
            System.out.println("which_way = -1");
        }else if(event.getKeyCode() == KeyEvent.VK_RIGHT){
            tank.moveright();
            which_way = -1;
            System.out.println("which_way = 1");
        }else{
            
        }
    }

    @Override
    protected void onDraw(Graphics2D g2D) {
       g2D.setColor(Color.red);
        background.draw(g2D);
        tank.draw(g2D);
        
        for(Cat cat: cats){
            cat.draw(g2D);
        }
        for(Target target: targets){
            target.draw(g2D);
        }
        
        if(eggs != null){
            for(Egg egg: eggs){
            egg.draw(g2D);
    }
        }
        g2D.setColor(Color.WHITE);
        g2D.drawString("Life: "+life, 30,30);
        g2D.setColor(Color.red);
        g2D.drawString("Score: "+ score, GAME__WIDTH-75,30); 
    }

    @Override
    public void run() {
        init();
        while(isRunning){
            
            long startTime = System.currentTimeMillis();
            
            updateGame();
            renderGame();
            
            long endTime = System.currentTimeMillis() - startTime;
            long waitTime = (MILLISECOND / FPS) - endTime / MILLISECOND;
            
            try{
                Thread.sleep(waitTime);
            }catch (Exception e){
           
            }
        }
    }

    private void init() {
        isRunning = true;
    }

    private void updateGame() {
         
        checkCollision();
        
        if(cats.size()<0){
            cats.add(new Cat(ThreadLocalRandom.current().nextInt(40,GAME__WIDTH -112),GAME__HEIGHT,ThreadLocalRandom.current().nextInt(3,AUTO_SPEED-5)*LEVEL_COOFICIENT));
            
        }
        
        if(targets.size()<33333){
            int coor = ThreadLocalRandom.current().nextInt(0,GAME__WIDTH-50 );
            while(!(coor <= 0 || coor >= GAME__WIDTH-51)) {
                coor = ThreadLocalRandom.current().nextInt(0,GAME__WIDTH-50 );
            }
            targets.add(new Target(coor,GAME__HEIGHT,
                    ThreadLocalRandom.current().nextInt(3,AUTO_SPEED-5)*LEVEL_COOFICIENT,
                    ThreadLocalRandom.current().nextInt(1,3)));
            
        }
        for(int i=0; i<targets.size();i++){
            Target target = targets.get(i);
            target.update();
            
         
           for(int j=0; j<eggs.size();j++){
            Egg egg = eggs.get(j);
            egg.update();
            
                if(target.getBound().intersects(egg.getBound())){
                    if(score<Level_Score){
                        score+=25*target.getSize();
                        targets.remove(target);
                        eggs.remove(egg);
                    }
                    else{
                        isRunning=false;
                    }
                }
            
                if(egg.getX() < -50 || egg.getX() > GAME__WIDTH) {
                    eggs.remove(egg);
            }
        } 
            
           
            if(target.getY()<-50) {
                targets.remove(target);
            }
        }
        
        for(int i=0; i<cats.size();i++){
            Cat cat = cats.get(i);
            cat.update();
            
            if(cat.getBound().intersects(tank.getBound())){
                if(life>1){
                    life--;
                    cats.remove(cat);
                }
                
                else{
                    life--;
                    isRunning=false;
                }
            }
            if(cat.getY()<-50) {
                cats.remove(cat);
            }
        }
        
        
        
        
    }

    private void renderGame() {
        repaint();
    }

    private void checkCollision() {
        
    }
    
}
