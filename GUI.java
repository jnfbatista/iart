/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iart;

import iart.gui.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author batista
 */
public class GUI extends JFrame {

    private Sidebar sidebar = null;
    private MyPanel drawingPanel = null;
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
    // A* Elements
    private Robo robo;
    private Node fim;
    private ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
    // Draggable Items
    private DraggableImageComponent robot;
    private ArrayList<Line2D> lines = new ArrayList<Line2D>();

    public GUI() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("IART 11");

        // Define o tamanho e não permite que se reinicie
        this.setSize(800, 600);
        this.setResizable(false);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.darkGray);

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
        drawingPanel = new MyPanel();
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
                    JTextField x = new IntegerTextField(), y = new IntegerTextField();

                    Object[] msg = {"X:", x, "Y:", y};

                    JOptionPane op = new JOptionPane(msg, JOptionPane.QUESTION_MESSAGE,
                            JOptionPane.OK_CANCEL_OPTION, null, null);

                    JDialog dialog = op.createDialog(self, "Características:");
                    dialog.setVisible(true);

                    int result = JOptionPane.OK_OPTION;

                    try {
                        result = ((Integer) op.getValue()).intValue();
                    } catch (Exception uninitializedValue) {
                        uninitializedValue.printStackTrace();
                    }

                    if (result == JOptionPane.OK_OPTION) {
                        Image img = getToolkit().createImage("robot.jpg");

                        robot = new DraggableImageComponent();
                        /** @todo adicionar um menu de botão direito para remoção */
                        drawingPanel.add(robot);

                        robot.setImage(img);//Sets image
                        robot.setAutoSize(true);//The component get ratio w/h of source image
                        robot.setOverbearing(true);//On click ,this panel gains lowest z-buffer
                        robot.setSize(75, 75);
                        robot.setLocation(
                                new Point(Integer.valueOf(x.getText()), Integer.valueOf(y.getText())));
                        drawingPanel.repaint();

                        hasRobot = true;
                        adicionarRobo.setEnabled(false);

                        robo = new Robo(new Node(Integer.valueOf(x.getText()), Integer.valueOf(y.getText()), 0), 0,
                                Integer.decode(self.sidebar.custoRotacaoInput.getText()),
                                Integer.decode(self.sidebar.dTetaInput.getText()),
                                Integer.decode(self.sidebar.dDistInput.getText()));

                    } else {
                        //return false;
                    }


                }

            }
        });

        adicionarMeta.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!hasObjective) {

                    JTextField x = new IntegerTextField(), y = new IntegerTextField();

                    Object[] msg = {"X:", x, "Y:", y};

                    JOptionPane op = new JOptionPane(msg, JOptionPane.QUESTION_MESSAGE,
                            JOptionPane.OK_CANCEL_OPTION, null, null);

                    JDialog dialog = op.createDialog(self, "Características:");
                    dialog.setVisible(true);

                    int result = JOptionPane.OK_OPTION;

                    try {
                        result = ((Integer) op.getValue()).intValue();
                    } catch (Exception uninitializedValue) {
                        uninitializedValue.printStackTrace();
                    }

                    if (result == JOptionPane.OK_OPTION) {
                        Image img = getToolkit().createImage("meta.png");

                        DraggableImageComponent meta = new DraggableImageComponent();
                        drawingPanel.add(meta);

                        meta.setImage(img);//Sets image
                        meta.setAutoSize(true);//The component get ratio w/h of source image
                        meta.setOverbearing(true);//On click ,this panel gains lowest z-buffer
                        meta.setLocation(
                                new Point(Integer.valueOf(x.getText()), Integer.valueOf(y.getText())));
                        meta.setSize(75, 75);
                        drawingPanel.repaint();

                        hasObjective = true;
                        adicionarMeta.setEnabled(false);
                        fim = new Node(Integer.valueOf(x.getText()), Integer.valueOf(y.getText()), 0);
                    }

                }

            }
        });

        adicionarObstaculo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                JTextField obsLargura = new IntegerTextField(),
                        obsAltura = new IntegerTextField(), objPesoUltrapassar = new IntegerTextField(),
                        x = new IntegerTextField(), y = new IntegerTextField();




                Object[] msg = {"X:", x, "Y:", y, "Largura:", obsLargura, "Altura:", obsAltura, "Peso:", objPesoUltrapassar};

                JOptionPane op = new JOptionPane(msg, JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.OK_CANCEL_OPTION, null, null);

                JDialog dialog = op.createDialog(self, "Características:");
                dialog.setVisible(true);

                int result = JOptionPane.OK_OPTION;

                try {
                    result = ((Integer) op.getValue()).intValue();
                } catch (Exception uninitializedValue) {
                    uninitializedValue.printStackTrace();
                }

                if (result == JOptionPane.OK_OPTION) {
                    Image img = getToolkit().createImage("wall.png");

                    DraggableImageComponent robot = new DraggableImageComponent();
                    /** @todo adicionar um menu de botão direito para remoção */
                    drawingPanel.add(robot);

                    robot.setImage(img);//Sets image
                    //robot.setAutoSize(true);//The component get ratio w/h of source image
                    robot.setOverbearing(true);//On click ,this panel gains lowest z-buffer
                    robot.setSize(Integer.decode(obsLargura.getText()),
                            Integer.decode(obsAltura.getText()));
                    robot.setLocation(
                            new Point(Integer.valueOf(x.getText()), Integer.valueOf(y.getText())));
                    drawingPanel.repaint();

                    obstaculos.add(new Obstaculo(robot.getX(), robot.getY(),
                            Integer.decode(obsAltura.getText()),
                            Integer.decode(obsLargura.getText()),
                            Integer.decode(objPesoUltrapassar.getText())));

                } else {
                    //return false;
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

        drawingPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.darkGray));


        this.setLayout(layout);

        layElement(layoutConstraints, GridBagConstraints.BOTH, 10, 1, 0, 0);
        this.getContentPane().add(drawingPanel, layoutConstraints);

        sidebar = new Sidebar();
        sidebar.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, Color.darkGray));

        sidebar.startAStar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                runAStar();
            }
        });

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

    /**
     * Esta função corre o A* quando se carrega no botão
     */
    private void runAStar() {
        Mapa mapa = new Mapa(drawingPanel.getHeight(), drawingPanel.getWidth(), fim, robo, obstaculos);

        ProcuraCaminho pc = new ProcuraCaminho(mapa);

        //pc.intersect(de, para, obs);

        System.out.println("Procura Caminho.");

        long begin = System.currentTimeMillis();

        LinkedList<Node> nodes = (LinkedList<Node>) pc.compute(new Node(mapa.getRobo().getPosRobo().getX(), mapa.getRobo().getPosRobo().getY(), 0));
        nodes.add(fim);

        long end = System.currentTimeMillis();

        for (int i = 0; i < nodes.size() - 1; i++) {
            Node n1 = nodes.get(i), n2 = nodes.get(i + 1);

            Line2D tempLine = new Line2D.Double(Math.floor(n1.getX()),
                    Math.floor(n1.getY()), Math.floor(n2.getX()),
                    Math.floor(n2.getY()));

            lines.add(tempLine);

            Graphics2D g = (Graphics2D) drawingPanel.getGraphics();

            g.setColor(Color.red);
            g.draw(tempLine);
            drawingPanel.repaint();
            repaint();
            //drawingPanel.

        }


        System.out.println("Time = " + (end - begin) + " ms");
        //System.out.println("Expanded = " + pc.getExpandedCounter());
        System.out.println("Cost = " + pc.getCost());

        if (nodes == null) {
            System.out.println("Nao Existe Caminho");
        } else {
            System.out.print("Caminho = ");
            for (Node n : nodes) {
                System.out.print(n);
            }
            System.out.println();
        }

    }

    private class MyPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponents(g);

            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setPaint(Color.red);

            /*g2.draw(new Line2D.Float(50, 50, 200, 20));

            g2.draw(new Line2D.Float(200, 20, 50, 200));*/

            for (Line2D line : lines) {

                g2.draw(line);

            }

        }
    }
}
