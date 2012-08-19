package kg.edu.iaau.imiaau;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author renat
 */
public class Main {

    public static void main(String args[]) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                
                try {

                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    //Login frame = new Login();
                    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //frame.setLocationRelativeTo(null);
                    //frame.pack();
                    //frame.setVisible(true);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }                    
            }
        });
    }

}