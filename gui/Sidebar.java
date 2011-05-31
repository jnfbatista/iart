/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iart.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

/**
 *
 * @author batista
 */
public class Sidebar extends JPanel {

    private GridBagLayout layout = null;
    private GridBagConstraints layoutConstraints = null;
    // Sidebar components
    private JLabel aStarLabel = null, aStarRobo = null, dTeta = null,
            dDist = null, custoRotacao = null, aStarObstaculos = null,
            custoUltrapassar = null;
    public IntegerTextField dTetaInput = new IntegerTextField(),
            dDistInput = new IntegerTextField(), custoRotacaoInput = new IntegerTextField(),
            custoUltrapassarInput = new IntegerTextField();
    private JComboBox evaluationSelector = null;
    public JButton startAStar = new JButton("CAlcular Caminho");
    private Font mainLabelFont = new Font("Verdana", Font.BOLD, 14);
    private Font subLabelFont = new Font("Verdana", Font.PLAIN, 14);

    /**
     * Class Constructor
     */
    public Sidebar() {

        //inicializar o layout e o seu objecto de restrições
        layout = new GridBagLayout();
        layoutConstraints = new GridBagConstraints();
        this.setOpaque(false);

        this.setSize(200, 590);

        // Inicializa os elementos visuais
        IntegerTextField[] intTextFields = {dTetaInput,
            dDistInput, custoRotacaoInput,
            custoUltrapassarInput};

        for (IntegerTextField i : intTextFields) {
            i.setColumns(5);
        }


        try {
            layoutElements();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setVisible(true);
    }

    private void layoutElements() {

        this.setLayout(layout);

        aStarLabel = new JLabel("A*");
        aStarRobo = new JLabel(" Robô");
        aStarObstaculos = new JLabel(" Obstáculos");
        dTeta = new JLabel(" - Delta Téta:");
        dDist = new JLabel(" - Passo:");
        custoRotacao = new JLabel(" - Custo Rotação:");
        custoUltrapassar = new JLabel(" - Custo Ultrapassar:");

        JLabel[] labels = {aStarLabel, aStarRobo, dTeta,
            dDist, custoRotacao, aStarObstaculos,
            custoUltrapassar};

        dTetaInput.setText("45");
        dDistInput.setText("20");
        custoRotacaoInput.setText("30");
        custoUltrapassarInput.setText("1");



        int x = 0;
        for (JLabel l : labels) {
            l.setFont(subLabelFont);
            layElement(layoutConstraints, GridBagConstraints.HORIZONTAL, 2, 1, 0, x);
            this.add(l, layoutConstraints);

            // Lays out the input fields
            switch (x) {
                case 2:
                    layElement(layoutConstraints, GridBagConstraints.CENTER, 1, 1, 1, x);
                    this.add(dTetaInput, layoutConstraints);
                    break;
                case 3:
                    layElement(layoutConstraints, GridBagConstraints.CENTER, 1, 1, 1, x);
                    this.add(dDistInput, layoutConstraints);
                    break;
                case 4:
                    layElement(layoutConstraints, GridBagConstraints.CENTER, 1, 1, 1, x);
                    this.add(custoRotacaoInput, layoutConstraints);
                    break;
                case 6:
                    layElement(layoutConstraints, GridBagConstraints.CENTER, 1, 1, 1, x);
                    this.add(custoUltrapassarInput, layoutConstraints);
                    break;
            }



            x++;
        }

        layElement(layoutConstraints, GridBagConstraints.WEST, 2, 1, 0, x);
        this.add(startAStar, layoutConstraints);



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
    private void layElement(GridBagConstraints c, int fill, double weightx, double weighty, int gridx, int gridy) {
        c.fill = fill;
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx = gridx;
        c.gridy = gridy;
    }
    //private class NumberListener extends
}
