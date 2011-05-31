package iart;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GUI gui = new GUI();

        //TODO retirar dados do robo e obstaculos a partir mapa e definições da simulacao
        int altura = 40;
        int comprimento = 40;
        Node posicaoInicial = new Node(0, 0, 0);
        Node fim = new Node(20, 20, 0);
        int altMaxObs = 1;
        int custoRotacao = 1;
        int angRotacao = 90;
        int distMov = 4;

        // TODO criar objectos e mapa
        Robo robo = new Robo(posicaoInicial, altMaxObs, custoRotacao, angRotacao, distMov);
        Obstaculo obs = new Obstaculo(4, 1, 2, 2, 3);
        ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
        obstaculos.add(obs);

/*
        Mapa mapa = new Mapa(altura, comprimento, fim, robo, obstaculos);

        ProcuraCaminho pc = new ProcuraCaminho(mapa);

        //pc.intersect(de, para, obs);

        System.out.println("Procura Caminho.");


        long begin = System.currentTimeMillis();

        LinkedList<Node> nodes = (LinkedList<Node>) pc.compute(new Node(mapa.getRobo().getPosRobo().getX(), mapa.getRobo().getPosRobo().getY(), 0));

        long end = System.currentTimeMillis();


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
 * 
 */
    }
}
