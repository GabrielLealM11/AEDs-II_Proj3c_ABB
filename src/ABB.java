import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class ABB<K, V> implements IMapeamento<K, V>{

	// atributos
	private long comparacoes;
	private long inicio;
	private long termino;
	
	/**
	 * Construtor da classe.
	 * @param comparador o comparador a ser utilizado para organizar os elementos da árvore.  
	 */
	public ABB(Comparator<K> comparador) {

	}
	
	@Override
	public long getComparacoes() {
		return comparacoes;
	}

	@Override
	public double getTempo() {
		return (termino - inicio) / 1_000_000;
	}
	public Lista<V> recortar(K chaveInicio, K chaveFinal){
		Lista<V> resultado = new Lista<>();

		if (comparar(chaveInicio, chaveFinal) > 0){ // chave inicial > final
			return resultado;
		}
		recortar(raiz, chaveInicio, chaveFinal, resultado);

		return resultado;
	}
	private void recortar(No<K, V> no, K inicio, K fim, Lista<V> lista){ // recebe nó e limites de recorte
		if(no == null){
			return;
		} //compara a chave do no com os limites de intervalo
		int comparacaoInicio = comparar(no.getChave(), inicio);
		int comparacaoFim = comparar(no.getChave(), fim);

		if (comparacaoInicio > 0){  // procura na esquerda
			recortar(no.getEsquerda(), inicio, fim, lista);
		}
		if (comparacaoInicio >= 0 && comparacaoFim <= 0){ // verifica se a chave nao esta no intervalo
			lista.inserir(no.getItem());
		}
		if (comparacaoFim < 0){ // procura na direita
			recortar(no.getDireita(), inicio, fim, lista);
		}
	}
}
