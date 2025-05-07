package PaginationAlgos;

import Components.ExecutionMetrics;
import Components.LinkedList.LinkedList;
import Components.LinkedList.LinkedListNode;

import java.util.List;

public class FIFO extends LinkedList implements IPaginator {

    public FIFO(int tamanhoMemoria) {
        super(tamanhoMemoria);
    }

    @Override
    public void addNode(LinkedListNode node) {
        //Start no tempo de execucao da chamada
        long startTime = System.nanoTime();

//        boolean shouldBePaged = executionMetrics.verifyVisited(node.getValue());

        var aux = getPrimeiro();
        LinkedListNode last = null;

        while (aux != null) {
            if (aux.getValue() == node.getValue()) {
                return;
            }
            last = aux;
            aux = aux.getNext();
        }

        if (getCapacidade() == getTamanho()) {
            removeComeco();

            // Setando metrica de numero de trocas
            executionMetrics.incrementTradesNumber();
        }

        setTamanho(getTamanho() + 1);

        if (getPrimeiro() == null) {
            setPrimeiro(node);
        } else {
            assert last != null;
            last.setNext(node);
        }

        executionMetrics.incrementPageMissing();

//        if(shouldBePaged) {
//            executionMetrics.incrementPageMissing();
//        }

        setUltimo(node);

        // Setando metrica de tempo
        long endTime = System.nanoTime();
        executionMetrics.setExecutionTime(endTime - startTime);
    }


    @Override
    public void paginar(List<Integer> paginas) {
        executionMetrics.clearMetrics();
        for(Integer pagina : paginas){
                this.addNode(new LinkedListNode(pagina){
            });
        }
    }

    @Override
    public ExecutionMetrics generateMetrics() {
        System.out.println("Para o FIFO");
        executionMetrics.printMetrics();
        return executionMetrics;
    }
}
