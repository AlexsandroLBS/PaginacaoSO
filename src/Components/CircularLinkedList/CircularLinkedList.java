package Components.CircularLinkedList;

import Components.LinkedList.LinkedList;
import Components.LinkedList.LinkedListNode;

public class CircularLinkedList extends LinkedList {

    public CircularLinkedList(int capacidade) {
        super(capacidade);
    }

    @Override
    public void addNode(LinkedListNode node) {
        if (getTamanho() >= getCapacidade()) {
            System.out.println("Lista cheia.");
            return;
        }

        if (getPrimeiro() == null) {
            setPrimeiro(node);
            setUltimo(node);
            node.setNext(node); // Aponta para si mesmo
        } else {
            node.setNext(getPrimeiro());
            getUltimo().setNext(node);
            setUltimo(node);
        }

        setTamanho(getTamanho() + 1);
    }

    @Override
    public void printLista() {
        LinkedListNode atual = getPrimeiro();

        if (atual == null) {
            System.out.println("Memoria vazia.");
            return;
        }

        System.out.print("Memória atual: ");
        do {
            System.out.print(atual.getValue() + " ");
            atual = atual.getNext();
        } while (atual != getPrimeiro());
        System.out.println();
    }

    @Override
    public void removeComeco() {
        if (getPrimeiro() == null) return;

        if (getTamanho() == 1) {
            setPrimeiro(null);
            setUltimo(null);
        } else {
            getUltimo().setNext(getPrimeiro().getNext());
            setPrimeiro(getPrimeiro().getNext());
        }

        setTamanho(getTamanho() - 1);
    }

    @Override
    public void removeFinal() {
        if (getPrimeiro() == null) return;

        if (getTamanho() == 1) {
            setPrimeiro(null);
            setUltimo(null);
        } else {
            LinkedListNode atual = getPrimeiro();
            while (atual.getNext() != getUltimo()) {
                atual = atual.getNext();
            }
            atual.setNext(getPrimeiro());
            setUltimo(atual);
        }

        setTamanho(getTamanho() - 1);
    }
}

