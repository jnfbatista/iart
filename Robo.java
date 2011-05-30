package iart;

import java.awt.Point;

public class Robo {
	
	private Node posRobo;
	private int altMaxObs=0;
	private int custoRotacao=0;
	private int angRotacao=0;
	private int distMov=0;
	
	
	//constructor do robo
	public Robo() {
	}
	public Robo(Node posRobo, int altMaxObs, int custoRotacao, int angRotacao, int distMov )  {	
		this.setPosRobo(posRobo);
		this.setaltMaxObs(altMaxObs);
		this.setCustoRotacao(custoRotacao);
		this.setAngRotacao(angRotacao);
		this.setDistMov(distMov);
	}

	
	// gets e sets do robo
	public void setPosRobo(Node posRobo) {
		this.posRobo = posRobo;
	}

	public  Node getPosRobo() {
		return posRobo;
	}
	
	public void setaltMaxObs(int altMaxObs) {
		this.altMaxObs = altMaxObs;
	}

	public  int getAltMaxObs() {
		return altMaxObs;
	}


	public void setCustoRotacao(int custoRotacao) {
		this.custoRotacao = custoRotacao;
	}


	public int getCustoRotacao() {
		return custoRotacao;
	}


	public void setAngRotacao(int angRotacao) {
		this.angRotacao = angRotacao;
	}


	public int getAngRotacao() {
		return angRotacao;
	}


	public void setDistMov(int distMov) {
		this.distMov = distMov;
	}


	public int getDistMov() {
		return distMov;
	}
	
}