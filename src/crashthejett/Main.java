
package crashthejett;

import crashthejett.game.GamePanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

 
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame gameFrame = new JFrame("Crash The Jet");
                gameFrame.add(new GamePanel());
                gameFrame.setResizable(false);
                gameFrame.pack();
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setVisible(true);
            }
        });
       
    
    }
}