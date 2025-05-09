package PaginationAlgos;

import Components.CircularLinkedList.CircularLinkedList;
import Components.ExecutionMetrics;
import Components.LinkedList.LinkedListNode;

import java.util.List;

public class Clock extends CircularLinkedList implements IPaginator {

    private LinkedListNode ponteiro; // Ponteiro do relógio

    public Clock(int tamanhoMemoria) {
        super(tamanhoMemoria);
        ponteiro = null;
    }

    @Override
    public void addNode(LinkedListNode novoNode) {
        long startTime = System.nanoTime();

        // Verifica se a lista está vazia
        if (getPrimeiro() == null) {
            super.addNode(novoNode);
            ponteiro = getPrimeiro(); // Inicializa o ponteiro
            executionMetrics.incrementPageMissing(); // primeira inserção é page fault
            long endTime = System.nanoTime();
            executionMetrics.setExecutionTime(endTime - startTime);
            return;
        }

        // Verifica se a página já existe (HIT)
        LinkedListNode atual = getPrimeiro();
        boolean encontrado = false;

        do {
            if (atual.getValue() == novoNode.getValue()) {
                atual.getPage().setUseBit(true); // Segunda chance
                encontrado = true;
                break;
            }
            atual = atual.getNext();
        } while (atual != getPrimeiro());

        if (encontrado) {
            long endTime = System.nanoTime();
            executionMetrics.setExecutionTime(endTime - startTime);
            return;
        }

        // PAGE FAULT
        executionMetrics.incrementPageMissing();

        if (getTamanho() == getCapacidade()) {
            // Substituição usando algoritmo Clock
            while (true) {
                if (ponteiro.getPage().isUseBit()) {
                    ponteiro.getPage().setUseBit(false);
                    ponteiro = ponteiro.getNext();
                } else {
                    // Substitui o valor da página
                    ponteiro.getPage().setValue(novoNode.getValue());
                    ponteiro.getPage().setUseBit(true);
                    ponteiro = ponteiro.getNext();
                    executionMetrics.incrementTradesNumber();
                    break;
                }
            }
        } else {
            // Ainda há espaço: adiciona novo nó
            super.addNode(novoNode);
            executionMetrics.incrementTradesNumber(); // mesmo sem remover, é uma "troca"
        }

        long endTime = System.nanoTime();
        executionMetrics.setExecutionTime(endTime - startTime);
    }

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