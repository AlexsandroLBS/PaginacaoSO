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

//        boolean shouldBePaged = executionMetrics.verifyVisited(node.getValue());
//
//        var alreadyPaged = false;

        var aux = getPrimeiro();
        var notFrequenlyUsed = getPrimeiro();
        var idx = 0;
        var notFrequenlyUsedIndex = 0;
        while (aux != null){
            if(node.getValue() == aux.getValue()){
                aux.getPage().addUse();
                return;
            }
            if(aux.getPage().getUses() < notFrequenlyUsed.getPage().getUses()){
                notFrequenlyUsed = aux;
                notFrequenlyUsedIndex = idx;
            }
            idx++;
            aux = aux.getNext();
        }

        executionMetrics.incrementPageMissing();

        if (getCapacidade() == getTamanho()) {
            removerPosicao(notFrequenlyUsedIndex);
            executionMetrics.incrementTradesNumber();
        }

        if (getPrimeiro() == null) {
            setPrimeiro(node);
        } else {
            getUltimo().setNext(node);
        }


//        if(shouldBePaged) {
//            executionMetrics.incrementPageMissing();
//        }
        setUltimo(node);

        setTamanho(getTamanho() + 1);

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
        System.out.println("Para o NFU");
        executionMetrics.printMetrics();
        return executionMetrics;
    }


    public void removerPosicao(int posicao) {
        if (posicao < 0 || posicao >= getTamanho()) return; // Posição inválida

        // Caso especial: remover o primeiro nó
        if (posicao == 0) {
            setPrimeiro(getPrimeiro().getNext());
            if (getPrimeiro() == null) {
                setUltimo(null); // Lista ficou vazia
            }
        } else {
            LinkedListNode anterior = getPrimeiro();
            for (int i = 0; i < posicao - 1; i++) {
                anterior = anterior.getNext();
            }

            LinkedListNode aRemover = anterior.getNext();
            anterior.setNext(aRemover.getNext());

            // Se o último foi removido, atualize o ponteiro 'ultimo'
            if (aRemover == getUltimo()) {
                setUltimo(anterior);
            }
        }

        setTamanho(getTamanho() - 1); // Atualiza o tamanho da lista
    }

}
