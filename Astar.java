package iart;

import java.util.*;

/**
 * Algoritmo A*
 * 
 */
public abstract class Astar<T>
{
	private PriorityQueue<Pesquisa> pesquisas;
	private HashMap<T, Double> distmin;
	private Double ultimoCusto;
	private int contador;
	
	/**
	 * verifica se o nodo atual e solucao
	 *
	 * @return true se for solucao, senao falso
	 */
	protected abstract boolean solucao(T nodo);

	/**
	 * @return o custo "de" "para" 
	 */
	protected abstract Double g(T de, T para);

	/**
	 * @return estimativa do custo  para a solucao
	 */
	protected abstract Double h(T de, T para);


	/**
	 * gera successores de um nodo
	 *
	 */
	protected abstract List<T> geraSucessores(T nodo);
	
	
	/**
	 * verifica quantas vezes um nodo foi expandido
	 */
	public int getContador(){
		return contador;
	}

	/**
	 * Constructor
	 */
	public Astar(){
		pesquisas = new PriorityQueue<Pesquisa>();
		distmin = new HashMap<T, Double>();
		contador = 0;
		ultimoCusto = 0.0;
	}


	/**
	 * Custo de um nodo para outro
	 */
	protected Double f(Pesquisa p, T de, T para){
		Double g =  g(de, para) + ((p.pai != null) ? p.pai.g : 0.0);
		Double h = h(de, para);
		
		
		p.g = g;
		p.f = g + h;
		
		System.out.println("f= " + p.f + "=> g(" +g + ") h("+h +")");
		return p.f;
	}

	/**
	 * Expande uma pesquisa.
	 *
	 */
	private void expande(Pesquisa pesquisa){
		T p = pesquisa.getPonto();
		Double min = distmin.get(pesquisa.getPonto());

		/*
		 * Não expande se ja existir um caminho melhor que passe pelo ponto
		 */
		if(min == null || min.doubleValue() > pesquisa.f.doubleValue())
			distmin.put(pesquisa.getPonto(), pesquisa.f);
		else
			return;

		List<T> sucessores = geraSucessores(p);

		for(T t : sucessores){
			Pesquisa novaPesquisa = new Pesquisa(pesquisa);
			novaPesquisa.setPonto(t);
			f(novaPesquisa, pesquisa.getPonto(), t);
			pesquisas.offer(novaPesquisa);
		}

		contador++;
	}

	/**
	 * 
	 * @return O custo para a solucao
	 */
	public Double getCost(){
		return ultimoCusto;
	}


	/**
	 * Encontra Pesquisa mais curta do nodo inicial a solucao
	 *
	 * @return A lista de nodos
	 */
	public List<T> compute(T incio){
		try{
			Pesquisa raiz = new Pesquisa();
			raiz.setPonto(incio);

			
			f(raiz, incio, incio); // no caso nodo incial ter um custo associado

			expande(raiz);

			for(;;){
				Pesquisa p = pesquisas.poll();

				if(p == null){
					ultimoCusto = Double.MAX_VALUE;
					return null;
				}

				T ultimo = p.getPonto();

				System.out.println(p.g);
				ultimoCusto = p.g;

				if(solucao(ultimo)){
					LinkedList<T> retPath = new LinkedList<T>();

					for(Pesquisa i = p; i != null; i = i.pai){
						retPath.addFirst(i.getPonto());
					}

					return retPath;
				}
				expande(p);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;

	}
	
	private class Pesquisa implements Comparable{
		public T ponto;
		public Double f;
		public Double g;
		public Pesquisa pai;

		/**
		 * Construtor por defeito
		 */
		 public Pesquisa(){
			pai = null;
			ponto = null;
			g = f = 0.0;
		}

		/**
		 *Clonagem de uma pesquisa
		 */
		 public Pesquisa(Pesquisa p){
			 this();
			 pai = p;
			 g = p.g;
			 f = p.f;
		 }
		 

		 /**
		  * Compara o custo de f de dois objetos
		  *
		  */ 
		 public int compareTo(Object o){
			 Pesquisa p = (Pesquisa)o;
			 return (int)(f - p.f);
		 }

		 /**
		  * ultimo ponto de uma pesquisa
		  *
		  * @return The last point visited by the path.
		  */
		 public T getPonto(){
			 return ponto;
		 }

		 /**
		  * Define o ponto
		  */
		 public void setPonto(T p){
			 ponto = p;
		 }
	}
}


