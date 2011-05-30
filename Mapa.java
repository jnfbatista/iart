package iart;

import java.util.ArrayList;

public class Mapa {

	private int altura;
	private int largura;
	private Node fim;
	private Robo robo;
	private ArrayList<Obstaculo> obstaculos;
	public Mapa(int altura, int comprimento, Node fim,Robo robo,ArrayList<Obstaculo> obstaculos ){

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


}
