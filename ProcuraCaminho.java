package iart;

import java.util.LinkedList;
import java.util.List;
import static java.lang.Math.*;

public class ProcuraCaminho extends Astar<Node> {

    private Mapa mapa;

    public ProcuraCaminho(Mapa mapa) {
        this.mapa = mapa;
    }

    @Override
    protected Double g(Node de, Node para) {

        int cusRot = 0;
        //Distancia entre os pontos
        double dist = Math.sqrt(Math.pow((de.getX() - para.getX()), 2) + Math.pow((de.getY() - para.getY()), 2));

        //Custo rotacao
        if (para.getFlag() > 0) {
            cusRot = mapa.getRobo().getCustoRotacao() * para.getFlag();
        }

        //Custo obstaculos
        int custoObst = 0;
        /*
        for (int i=0; i < mapa.getObstaculos().size(); i++){
        if (intersect(de, para, mapa.getObstaculos().get(i)) == true){
        custoObst += mapa.getObstaculos().get(i).getCusto();
        }
        }
         */
        Double g = dist + cusRot + custoObst;
        return g;
    }

    @Override
    protected List<Node> geraSucessores(Node nodo) {
    	System.out.println(" Nodo a ser computado:" + nodo);
    	List<Node> ret = new LinkedList<Node>();
    	int ang = mapa.getRobo().getAngRotacao();
    	int numAng = 360 / ang;
		System.out.println("### Lista gerada: ###");	

    	for (int i = 0; i < numAng; i++) {
    		Node nnode = new Node (nodo.getX() + mapa.getRobo().getDistMov() * (cos(ang*i)), nodo.getX() + mapa.getRobo().getDistMov() * sin(ang*i), i);
    		/*
    		if(nnode.getX()<0){
    			nnode.setX(0.0);
    		}
    		if (nnode.getY()<0){
    			nnode.setY(0.0);
    		}
    		if (nnode.getX()>mapa.getLargura()){
    			nnode.setX(mapa.getLargura());
    		}
    		if (nnode.getX()>mapa.getAltura()){
    			nnode.setX(mapa.getAltura());
    		}
    		*/
    		System.out.println(nnode);
    		ret.add(nnode);

    	}
		System.out.println("#####");	

    return ret;
    
}

    @Override
    protected Double h(Node de, Node para) {
        double dist = Math.sqrt(Math.pow((para.getX() - mapa.getFim().getX()), 2) + Math.pow((para.getY() - mapa.getFim().getY()), 2));
        //System.out.println("h:" + dist);

        return dist;
    }

    @Override
    protected boolean solucao(Node nodo) {
        if (nodo.getX() + mapa.getRobo().getDistMov() >= mapa.getFim().getX() && nodo.getY() + mapa.getRobo().getDistMov() >= mapa.getFim().getY()) {
            return true;
        }
        return false;
    }

    protected boolean intersect(Node de, Node para, Obstaculo obstaculo) {
        double m1, m2, b1, b2, ix, iy;

        double p1ax = de.getX();
        double p1ay = de.getY();
        double p1bx = para.getX();
        double p1by = para.getY();
        double p2ax = obstaculo.getX();
        double p2ay = obstaculo.getY();
        double p2bx = p2ax + obstaculo.getComprimento();
        double p2by = p2ay + obstaculo.getLargura();


        // comparison line segment
        m1 = (p1ay - p1by) / (p1ax - p1bx);
        b1 = p1ay - m1 * p1ax;


        // second line segment
        m2 = (p2ay - p2by) / (p2ax - p2bx);
        b2 = p2ay - m2 * p2ax;

        // if they're not parallel
        if (m1 != m2) {

            // find the intersection
            ix = (b2 - b1) / (m1 - m2);
            iy = m1 * ix + b1;
            // TODO ainda n detecta
            // is the intersection on the line segments?
            if (ix > min(p1ax, p1bx) && ix < max(p1ax, p1bx)
                    && iy > min(p1ay, p1by) && iy < max(p1ay, p1by)
                    && ix > min(p2ax, p2bx) && ix < max(p2ax, p2bx)
                    && iy > min(p2ay, p2by) && iy < max(p2ay, p2by)) {
                System.out.println("tocam-se as porcas!!!");
                return true;
                // send the points to the shape object
            }
        }
        System.out.println("n se tocam, e n tem prazer");
        return false;
    }
}
