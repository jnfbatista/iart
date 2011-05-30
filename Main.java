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
        /*int altura = 100;
        int comprimento = 100;
        Node posicaoInicial = new Node(0, 0, 0);
        Node fim = new Node(100, 100, 0);
        int altMaxObs = 1;
        int custoRotacao = 1;
        int angRotacao = 45;
        int distMov = 3;

        // TODO criar objectos e mapa
        Robo robo = new Robo(posicaoInicial, altMaxObs, custoRotacao, angRotacao, distMov);
        ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();


        Mapa mapa = new Mapa(altura, comprimento, fim, robo, obstaculos);

        ProcuraCaminho pc = new ProcuraCaminho(mapa);


		//TODO retirar dados do robo e obstaculos a partir mapa e defini��es da simulacao
		int altura = 530;
		int comprimento = 530;
		Node posicaoInicial = new Node(0,0,0);
		Node fim = new Node(420,482,0);
		int altMaxObs=1;
		int custoRotacao=1;
		int angRotacao=90; 
		int distMov=10; 

        pc.intersect(de, para, obs);
         */
        /*System.out.println("Find a path from the top left corner to the right bottom one.");


        long begin = System.currentTimeMillis();

        LinkedList<Node> nodes = (LinkedList<Node>) pc.compute(new Node(0, 0, 0));

        long end = System.currentTimeMillis();


        System.out.println("Time = " + (end - begin) + " ms");
        //System.out.println("Expanded = " + pc.getExpandedCounter());
        System.out.println("Cost = " + pc.getCost());

        if (nodes == null) {
            System.out.println("No path");
        } else {
            System.out.print("Path = ");
            for (Node n : nodes) {
                System.out.print(n);
            }
            System.out.println();
        }*/
    }
}
