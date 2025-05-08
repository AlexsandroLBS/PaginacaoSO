package PaginationAlgos;

import Components.ExecutionMetrics;
import Components.LinkedList.LinkedList;
import Components.LinkedList.LinkedListNode;

import java.util.List;

public class NFU extends LinkedList implements IPaginator {

    public NFU(int tamanhoMemoria) {
        super(tamanhoMemoria);
    }

    @Override
    public void addNode(LinkedListNode node) {
        long startTime = System.nanoTime();

        // Caso especial: lista vazia
        if (getPrimeiro() == null) {
            setPrimeiro(node);
            setUltimo(node);
            setTamanho(1);
            executionMetrics.incrementPageMissing();
            long endTime = System.nanoTime();
            executionMetrics.setExecutionTime(endTime - startTime);
            return;
        }

        // Verifica se a página já está na memória e atualiza contador de uso
        var aux = getPrimeiro();
        var notFrequentlyUsed = getPrimeiro();
        var idx = 0;
        var notFrequentlyUsedIndex = 0;

        while (aux != null) {
            if (node.getValue() == aux.getValue()) {
                aux.getPage().addUse();
                long endTime = System.nanoTime();
                executionMetrics.setExecutionTime(endTime - startTime);
                return;
            }

            if (aux.getPage().getUses() < notFrequentlyUsed.getPage().getUses()) {
                notFrequentlyUsed = aux;
                notFrequentlyUsedIndex = idx;
            }

            idx++;
            aux = aux.getNext();
        }

        // Página não encontrada: falta de página
        executionMetrics.incrementPageMissing();

        // Se a memória estiver cheia, remove a menos usada
        if (getCapacidade() == getTamanho()) {
            removerPosicao(notFrequentlyUsedIndex);
            executionMetrics.incrementTradesNumber();
        }

        // Insere a nova página no final
        getUltimo().setNext(node);
        setUltimo(node);
        setTamanho(getTamanho() + 1);

        // Métrica de tempo
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
        System.out.println("Para o NFU");
        executionMetrics.printMetrics();
        return executionMetrics;
    }

    public void removerPosicao(int posicao) {
        if (posicao < 0 || posicao >= getTamanho()) return;

        if (posicao == 0) {
            setPrimeiro(getPrimeiro().getNext());
            if (getPrimeiro() == null) {
                setUltimo(null);
            }
        } else {
            LinkedListNode anterior = getPrimeiro();
            for (int i = 0; i < posicao - 1; i++) {
                anterior = anterior.getNext();
            }

            LinkedListNode aRemover = anterior.getNext();
            anterior.setNext(aRemover.getNext());

            if (aRemover == getUltimo()) {
                setUltimo(anterior);
            }
        }

        setTamanho(getTamanho() - 1);
    }
}
