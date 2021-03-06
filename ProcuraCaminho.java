package iart;

import java.util.LinkedList;
import java.util.List;
import static java.lang.Math.*;

public class ProcuraCaminho extends Astar<Node> {

	private Mapa mapa;
	private boolean frontOnly = true;

	private void setFrontOnly(boolean frontOnly) {
		this.frontOnly = frontOnly;
	}

	private boolean getFrontOnly() {
		return frontOnly;
	}

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

		for (int i=0; i < mapa.getObstaculos().size(); i++){
			if (intersect(de, para, mapa.getObstaculos().get(i)) == true){
				custoObst += mapa.getObstaculos().get(i).getCusto();
			}
		}
		
		Double g = dist + cusRot + custoObst;
		return g;
	}

	@Override
	protected List<Node> geraSucessores(Node nodo) {

		System.out.println(" Nodo a ser computado:" + nodo);
		List<Node> ret = new LinkedList<Node>();
		int ang = mapa.getRobo().getAngRotacao();
		double angrad = ang*2*Math.PI/360;
		int numAng =  (int) (2*Math.PI/angrad) ;
		System.out.println("### Lista gerada: ###");	

		for (int i = 1; i < numAng + 1; i++) {
			if (frontOnly && i>numAng*0.25 && i<numAng*0.75){
				continue;
			}
			Node nnode = new Node (nodo.getX() + mapa.getRobo().getDistMov() * (cos(angrad*i)), nodo.getY() + mapa.getRobo().getDistMov() * sin(angrad*i), i);
			System.out.println(nnode);
                        if(nnode.getX() >= 0 && nnode.getY() >= 0)
                            ret.add(nnode);

		}
		System.out.println("#####");	

		return ret;

	}

	@Override
	protected Double h(Node de, Node para) {
		double dist = Math.sqrt(Math.pow((para.getX() - mapa.getFim().getX()), 2) + Math.pow((para.getY() - mapa.getFim().getY()), 2));
		double custoObst=0;
		for (int i=0; i < mapa.getObstaculos().size(); i++){
			if (intersect(de, para, mapa.getObstaculos().get(i)) == true){
				custoObst += mapa.getObstaculos().get(i).getCusto();
				break;
			}
		}
		double h = dist + custoObst;
		return h;
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
		double p2bx = p2ax + obstaculo.getLargura();
		double p2by = p2ay + obstaculo.getComprimento();


		// comparison line segment
		m1 = (p1by - p1ay) / (p1bx - p1ax);
		b1 = p1ay - m1 * p1ax;

		// second line segment belonging to the obstacle
		m2 = 0;
		b2 = p2ax;

		// if they're not parallel
		if (m1 != m2 ) {

			// find the intersection
			ix = (b2 - b1) / (m1 - m2);
			iy = m1 * ix + b1;

                        if(Double.isNaN(ix) || Double.isNaN(iy) ) {
                            //System.out.printf("%g, %g, %g, %g, %g, %g, %g, %g\n", p1by, p1ay, p1bx, p1ax, b1, b2, m1, m2);
                        //    System.exit(0);
                        }
			// TODO ainda n detecta
			// is the intersection on the line segments?

			//System.out.printf("Intersecção em %g,%g!\n", ix, iy);



			if (ix >= p2ax && ix <= p2bx) { //verifica entre as verticais
				if (iy >= p2ay && iy <= p2by) { //verifica entre as horizontais
                                    //System.out.println("##############################################################\nteste interseccao");
					return true;
				}
			}
		}
		return false;
	}
}
