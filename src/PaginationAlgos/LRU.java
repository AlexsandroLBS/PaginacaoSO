package PaginationAlgos;

import Components.LinkedList.LinkedList;
import Components.LinkedList.LinkedListNode;

import java.util.List;

public class LRU extends LinkedList implements IPaginator {
    public LRU(int capacidade) {
        super(capacidade);
    }

    @Override
    public void addNode(LinkedListNode node) {
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
                break;
            }
            anterior = atual;
            atual = atual.getNext();
        }

        // LRU: Se estiver cheia, remove o primeiro (menos recentemente usado)
        if (getTamanho() == getCapacidade()) {
            removeComeco();
        }

        if (getPrimeiro() == null) {
            setPrimeiro(node);
        } else {
            getUltimo().setNext(node);
            setUltimo(node);
        }

        setTamanho(getTamanho() + 1);
    }

    @Override
    public void Paginar(List<Integer> paginas) {
        for(Integer pagina : paginas){
            this.addNode(new LinkedListNode(pagina){
            });
        }
    }
}
