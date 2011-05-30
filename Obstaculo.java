package iart;

public class Obstaculo {

	private int x;
	private int y;
	private int comprimento;
	private int largura;
	private int custo;
	Obstaculo(int x,int y,int comprimento,int largura, int custo){
		this.setX(x);
		this.setY(y);
		this.setComprimento(comprimento);
		this.setLargura(largura);
		this.setCusto(custo);
	}
	
	void setX(int x) {
		this.x = x;
	}
	
	int getX() {
		return x;
	}
	void setY(int y) {
		this.y = y;
	}
	int getY() {
		return y;
	}

	void setComprimento(int comprimento) {
		this.comprimento = comprimento;
	}

	int getComprimento() {
		return comprimento;
	}

	void setLargura(int largura) {
		this.largura = largura;
	}

	int getLargura() {
		return largura;
	}

	void setCusto(int custo) {
		this.custo = custo;
	}

	int getCusto() {
		return custo;
	}

}
