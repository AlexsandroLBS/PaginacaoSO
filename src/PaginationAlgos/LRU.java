package PaginationAlgos;

import Components.ExecutionMetrics;
import Components.LinkedList.LinkedList;
import Components.LinkedList.LinkedListNode;

import java.util.List;

public class LRU extends LinkedList implements IPaginator {

    public LRU(int tamanhoMemoria) {
        super(tamanhoMemoria);
    }

    @Override
    public void addNode(LinkedListNode node) {
        long startTime = System.nanoTime();
        boolean alreadyPaged = false;

        LinkedListNode atual = getPrimeiro();
        LinkedListNode anterior = null;

        // Verifica se o valor já está na lista
        while (atual != null) {
            if (atual.getValue() == node.getValue()) {
                // Remove o nó atual
                if (anterior == null) {
                    setPrimeiro(atual.getNext());
                } else {
                    anterior.setNext(atual.getNext());
                }

                // Ajusta o ponteiro do último, se necessário
                if (atual == getUltimo()) {
                    setUltimo(anterior);
                }

                setTamanho(getTamanho() - 1);
                alreadyPaged = true;
                break;
            }
            anterior = atual;
            atual = atual.getNext();
        }

        // Se for uma nova página
        if (!alreadyPaged) {
            if (getTamanho() == getCapacidade()) {
                removeComeco();
                executionMetrics.incrementTradesNumber();
            }
            executionMetrics.incrementPageMissing();
        }

        // Garante que o novo nó não aponta para ninguém
        node.setNext(null);

        // Adiciona no final da lista
        if (getPrimeiro() == null) {
            setPrimeiro(node);
            setUltimo(node);
        } else {
            getUltimo().setNext(node);
            setUltimo(node);
        }

        setTamanho(getTamanho() + 1);

        // Registra o tempo de execução
        long endTime = System.nanoTime();
        executionMetrics.setExecutionTime(endTime - startTime);
    }

    @Override
    public void paginar(List<Integer> paginas) {
        executionMetrics.clearMetrics();
        for (Integer pagina : paginas) {
            this.addNode(new LinkedListNode(pagina));
        }
    }

    @Override
    public ExecutionMetrics generateMetrics() {
        System.out.println("Para o LRU");
        executionMetrics.printMetrics();
        return executionMetrics;
    }
}
