/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iart;

import javax.swing.UIManager;

/**
 *
 * @author batista
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // tentar pôr a aplicação com o aspecto nativo do SO
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        GUI gui = new GUI();
        
    }
}
