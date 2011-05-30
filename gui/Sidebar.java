/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iart.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author batista
 */
public class Sidebar extends JPanel {

    private GridBagLayout layout  = null;
    private GridBagConstraints layoutConstraints = null;

    // Sidebar components
    private JLabel mapaLabel = null, aStarLabel = null;
    private JButton mapaNovo = null, mapaAbrir = null, mapaSalvar = null;
    private JLabel mapaRobo = null, mapaAdicionarObjecto = null;

    private JComboBox evaluationSelector = null;

    private Font mainLabelFont = new Font("Verdana", Font.BOLD, 16);

    
    /**
     * Class Constructor
     */
    public Sidebar() {

        //inicializar o layout e o seu objecto de restrições
        layout = new GridBagLayout();
        layoutConstraints = new GridBagConstraints();

        this.setSize(200, 590);

        // Inicializa os elementos visuais
        mapaLabel = new JLabel("Mapa");
        mapaLabel.setFont(mainLabelFont);
        mapaAdicionarObjecto = new JLabel("Adicionar Objecto");

        mapaRobo = new JLabel(new ImageIcon("robot_icon.jpg"));
        //mapaRobo.is
        
        /** @todo Põr uma border de jeito */
        //mapaRobo.setBorder(new BevelBorder(BevelBorder.RAISED));

        aStarLabel = new JLabel("A*");
        aStarLabel.setFont(mainLabelFont);

        layoutElements();
        this.setVisible(true);
    }


    private void layoutElements() {
                
        this.setLayout(layout);

        layElement(layoutConstraints, GridBagConstraints.HORIZONTAL, 1, 1, 0, 0);
        this.add(mapaLabel, layoutConstraints);

        layElement(layoutConstraints, GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 1);
        this.add(mapaRobo, layoutConstraints);

        layElement(layoutConstraints, GridBagConstraints.HORIZONTAL, 1, 1, 0, 2);
        this.add(aStarLabel, layoutConstraints);

    }

    private void setJLabelDraggable (JLabel label ) {

        //label.

        label.addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(MouseEvent evt) {
                    JComponent comp = (JComponent)evt.getSource();
                    TransferHandler th = comp.getTransferHandler();

                    // Start the drag operation
                    th.exportAsDrag(comp, evt, TransferHandler.COPY);
    }
                }
        );
    }


    /**
     * Lays the element in a grid bag layout
     *
     * @param c
     * @param fill
     * @param weightx
     * @param weighty
     * @param gridx
     * @param gridy
     */
    private void layElement( GridBagConstraints c, int fill, double weightx, double weighty, int gridx, int gridy ){
        c.fill = fill;
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx = gridx;
        c.gridy = gridy;
    }

}
