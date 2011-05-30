/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iart;

import iart.gui.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.BorderFactory;

/**
 *
 * @author batista
 */
public class GUI extends JFrame {

    private Sidebar sidebar = null;
    private JPanel drawingPanel = null;
    private JMenuBar menuBar = null;
    private JPopupMenu popupMenu = null;
    private JMenu adicionarSub = null;
    private GUI self = this;
    // IMPORTANT State variables
    private boolean hasRobot = false;
    private boolean hasObjective = false;
    // Menu Items
    private JMenuItem adicionarRobo = new JMenuItem("Robô");
    private JMenuItem adicionarMeta = new JMenuItem("Meta");
    private JMenuItem adicionarObstaculo = new JMenuItem("Obstáculo");
    private JMenuItem limparMapa = new JMenuItem("Limpar Mapa");
    private JMenuItem salvarMapa = new JMenuItem("Salvar Mapa");
    private JMenuItem abrirMapa = new JMenuItem("Abrir Mapa");
    // Alternative layout
    private GridBagLayout layout = null;
    private GridBagConstraints layoutConstraints = null;
    private PopupListener popupListener = null;

    public GUI() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("IART 11");

        // Define o tamanho e não permite que se reinicie
        this.setSize(800, 600);
        this.setResizable(false);
        this.setBackground(Color.DARK_GRAY);

        popupListener = new PopupListener();


        setupCanvas();
        setupPopupMenu();


        layout = new GridBagLayout();
        layoutConstraints = new GridBagConstraints();

        this.layoutElements();
        this.setVisible(true);
    }

    /**
     * Setups the canvas element
     */
    private void setupCanvas() {
        drawingPanel = new JPanel();
        drawingPanel.setSize(590, 590);
        drawingPanel.setBackground(Color.WHITE);
        drawingPanel.setLayout(null);

    }

    /**
     * Sets up the popup menu
     */
    private void setupPopupMenu() {

        popupMenu = new JPopupMenu();

        adicionarSub = new JMenu("Adicionar...");



        /** @todo: adicionar acções aos items do menu */
        adicionarRobo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!hasRobot) {
                    //drawingPanel.addC
                    Image img = getToolkit().createImage("robot.jpg");

                    DraggableImageComponent robot = new DraggableImageComponent();
                    /** @todo adicionar um menu de botão direito para remoção */
                    drawingPanel.add(robot);

                    robot.setImage(img);//Sets image
                    robot.setAutoSize(true);//The component get ratio w/h of source image
                    robot.setOverbearing(true);//On click ,this panel gains lowest z-buffer
                    robot.setSize(75, 75);
                    robot.setLocation(popupMenu.getLocation());
                    drawingPanel.repaint();

                    hasRobot = true;
                    adicionarRobo.setEnabled(false);

                }

            }
        });

        adicionarMeta.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!hasObjective) {
                    //drawingPanel.addC
                    Image img = getToolkit().createImage("meta.png");

                    DraggableImageComponent robot = new DraggableImageComponent();
                    /** @todo adicionar um menu de botão direito para remoção */
                    drawingPanel.add(robot);

                    robot.setImage(img);//Sets image
                    robot.setAutoSize(true);//The component get ratio w/h of source image
                    robot.setOverbearing(true);//On click ,this panel gains lowest z-buffer
                    robot.setSize(75, 75);
                    robot.setLocation(popupMenu.getLocation());
                    drawingPanel.repaint();

                    hasObjective = true;
                    adicionarMeta.setEnabled(false);

                }

            }
        });

        adicionarSub.add(adicionarRobo);
        adicionarSub.add(adicionarMeta);
        adicionarSub.add(adicionarObstaculo);

        popupMenu.add(adicionarSub);
        popupMenu.addSeparator();
        popupMenu.add(limparMapa);
        popupMenu.add(salvarMapa);
        popupMenu.add(abrirMapa);


        super.add(popupMenu);

        //super.addMouseListener(popupListener);
        drawingPanel.addMouseListener(popupListener);
    }

    /**
     * Sets the master layout in this application
     */
    public void layoutElements() {

        // Sets a border to each element

        drawingPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.darkGray));


        this.setLayout(layout);

        layElement(layoutConstraints, GridBagConstraints.BOTH, 6, 1, 0, 0);
        this.getContentPane().add(drawingPanel, layoutConstraints);

        sidebar = new Sidebar();
        sidebar.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.darkGray));

        layElement(layoutConstraints, GridBagConstraints.NORTHWEST, 1, 1, 1, 0);
        this.getContentPane().add(sidebar, layoutConstraints);

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

    private class PopupListener implements MouseListener {

        public void mousePressed(MouseEvent evt) {
            if (evt.isPopupTrigger()) {
                popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }

        public void mouseClicked(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
