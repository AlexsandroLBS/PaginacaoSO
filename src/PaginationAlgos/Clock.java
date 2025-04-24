package PaginationAlgos;

import Components.CircularLinkedList.CircularLinkedList;
import Components.ExecutionMetrics;
import Components.LinkedList.LinkedListNode;

import java.util.List;

public class Clock extends CircularLinkedList implements IPaginator {

    private LinkedListNode ponteiro; // ponteiro do relógio

    public Clock(int tamanhoMemoria) {
        super(tamanhoMemoria);
        ponteiro = null;
    }

//    @Override
//    public void addNode(LinkedListNode novoNode) {
//        long startTime = System.nanoTime();
//
//        // Verifica se a lista está vazia (primeiro elemento)
//        if (getPrimeiro() == null) {
//            // A lista está vazia, então o primeiro nó é o novo nó
//            super.addNode(novoNode);
//            setTamanho(getTamanho() + 1); // Atualiza o tamanho
//            ponteiro = getPrimeiro(); // Inicializa o ponteiro para o primeiro nó
//            long endTime = System.nanoTime();
//            executionMetrics.setExecutionTime(endTime - startTime);
//            return;
//        }
//
//        LinkedListNode atual = getPrimeiro();
//        boolean found = false;
//
//        // Verifica se a página já existe (hit)
//        do {
//            if (atual.getValue() == novoNode.getValue()) {
//                atual.getPage().setUseBit(true); // Segunda chance
//                found = true;
//                break;
//            }
//            atual = atual.getNext();
//        } while (atual != getPrimeiro()); // Continua até voltar ao início (lista circular)
//
//        // Se a página foi encontrada, não há necessidade de adicionar
//        if (found) {
//            long endTime = System.nanoTime();
//            executionMetrics.setExecutionTime(endTime - startTime);
//            return;
//        }
//
//        // Page fault: não encontrou a página, portanto substitui
//        executionMetrics.incrementTradesNumber();
//
//        if (getTamanho() == getCapacidade()) {
//            // Aplica política de substituição do relógio
//            while (true) {
//                if (ponteiro.getPage().isUseBit()) {
//                    // A página tem segunda chance, desmarque o bit e avance o ponteiro
//                    ponteiro.getPage().setUseBit(false);
//                    ponteiro = ponteiro.getNext();
//                } else {
//                    // Substitui a página
//                    ponteiro.getPage().setValue(novoNode.getValue());
//                    ponteiro.getPage().setUseBit(true);
//                    break;
//                }
//            }
//        } else {
//            // Ainda há espaço na memória, adiciona o novo nó
//            super.addNode(novoNode);
//            setTamanho(getTamanho() + 1); // Atualiza o tamanho
//        }
//
//        // Garante que o ponteiro está apontando para o primeiro nó
//        if (ponteiro == null) {
//            ponteiro = getPrimeiro();
//        }
//
//        long endTime = System.nanoTime();
//        executionMetrics.setExecutionTime(endTime - startTime);
//    }


    @Override
    public void paginar(List<Integer> paginas) {
        executionMetrics.clearMetrics();
        for (Integer pagina : paginas) {
            addNode(new LinkedListNode(pagina));
        }
    }

    @Override
    public ExecutionMetrics generateMetrics() {
        System.out.println("Para o CLOCK");
        executionMetrics.printMetrics();
        return executionMetrics;
    }
}
