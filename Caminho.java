package iart;

import java.util.ArrayList;

/**
 * representacao do deslocamento de um ponto a outro do mapa e suas operacoes
 */

public class Caminho {
	/** Lista de passos que compoem um caminho */
	private ArrayList<Passo> passos = new ArrayList<Passo>();

	/**
	 * Cria um caminho vazio
	 */
	public Caminho() {

	}

	/**
	 * 
	 * @return Numero de passos de um caminho
	 */
	public int getNumeroPassos() {
		return passos.size();
	}

	/**
	 * Acessa um passo num indice do caminho
	 * 
	 * @return a informacao do passo, posicao no mapa.
	 */
	public Passo getPasso(int index) {
		return (Passo) passos.get(index);
	}

	/**
	 * @return Coordenada X de um passo
	 */
	public int getX(int index) {
		return getPasso(index).x;
	}

	/**
	 * @return Coordenada y de um passo
	 */
	public int getY(int index) {
		return getPasso(index).y;
	}

	/**
	 * Adiciona um passo ao fim de um caminho
	 */
	public void adicionaPassoFim(int x, int y) {
		passos.add(new Passo(x,y));
	}

	/**
	 * Adiciona um passo ao inicio de um caminho 
	 */
	public void adicionaPassoInicio(int x, int y) {
		passos.add(0, new Passo(x, y));
	}

	/**
	 * verifica se um caminho contem um passo
	 * 
	 * @return True se contem o caminho
	 */
	public boolean contains(int x, int y) {
		return passos.contains(new Passo(x,y));
	}

	/**
	 * Um passo de um caminho
	 * 
	 */
	public class Passo {
		/** Coordenada X de um passo */
		private int x;
		/** Coordenada Y de um passo */
		private int y;

		/**
		 * Novo Passo
		 */
		public Passo(int x, int y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * @return Coordenada X de um passo
		 */
		public int getX() {
			return x;
		}

		/**
		 * @return Coordenada Y de um passo
		 */
		public int getY() {
			return y;
		}

		/**
		 * @see Object#hashCode()
		 */
		public int hashCode() {
			return x*y;
		}

		/**
		 * @see Object#equals(Object)
		 */
		public boolean equals(Object other) {
			if (other instanceof Passo) {
				Passo o = (Passo) other;
				return (o.x == x) && (o.y == y);
			}

			return false;
		}
	}
}

