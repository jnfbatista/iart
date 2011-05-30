package iart;

import java.util.ArrayList;

public class Mapa {
//yeah
	private int altura;
	private int largura;
	private Node fim;
	private Robo robo;
	private ArrayList<Obstaculo> obstaculos;
	public Mapa(int altura, int largura, Node fim,Robo robo,ArrayList<Obstaculo> obstaculos ){
		this.setAltura(altura);
		this.setLargura(largura);
		this.setFim(fim);
		this.setRobo(robo);
		this.setObstaculos(obstaculos);
	
	}


	Robo getRobo() {
		return robo;
	}


	ArrayList<Obstaculo> getObstaculos() {
		return obstaculos;
	}


	void setAltura(int altura) {
		this.altura = altura;
	}

	int getAltura() {
		return altura;
	}



	void setLargura(int largura) {
		this.largura = largura;
	}



	int getLargura() {
		return largura;
	}



	void setFim(Node fim) {
		this.fim = fim;
	}



	Node getFim() {
		return fim;
	}


	private void setRobo(Robo robo) {
		this.robo = robo;
	}


	private void setObstaculos(ArrayList<Obstaculo> obstaculos) {
		this.obstaculos = obstaculos;
	}


}
