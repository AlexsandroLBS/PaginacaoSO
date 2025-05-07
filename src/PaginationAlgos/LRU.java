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
        //Start no tempo de execucao da chamada
        long startTime = System.nanoTime();


//        boolean shouldBePaged = executionMetrics.verifyVisited(node.getValue());

        boolean alreadyPaged = false;

        LinkedListNode atual = getPrimeiro();
        LinkedListNode anterior = null;

        //Logica para validar se o valor ja estava na lista ou nao
        while (atual != null) {
            if (atual.getValue() == node.getValue()) {
                if (anterior == null) {
                    setPrimeiro(atual.getNext());
                } else {
                    anterior.setNext(atual.getNext());
                }

                setTamanho(getTamanho() - 1);
                alreadyPaged = true;
                break;
            }
            anterior = atual;
            atual = atual.getNext();
        }

        // LRU: Se estiver cheia, remove o primeiro (menos recentemente usado)
//        if (getTamanho() == getCapacidade()) {
//            removeComeco();
//            executionMetrics.incrementTradesNumber();
//        }
        if (!alreadyPaged) {
            if (getTamanho() == getCapacidade()) {
                removeComeco();
                executionMetrics.incrementTradesNumber();
            }
            executionMetrics.incrementPageMissing();
        }


        if (getPrimeiro() == null) {
            setPrimeiro(node);
        } else {
            getUltimo().setNext(node);
            setUltimo(node);
        }

        setTamanho(getTamanho() + 1);

        // Setando metrica de tempo
        long endTime = System.nanoTime();
        executionMetrics.setExecutionTime(endTime - startTime);
    }

    @Override
    public void paginar(List<Integer> paginas) {
        executionMetrics.clearMetrics();
        for(Integer pagina : paginas){
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
