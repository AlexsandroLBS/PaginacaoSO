package PaginationAlgos;

import Components.LinkedList.LinkedList;
import Components.LinkedList.LinkedListNode;

import java.util.List;

public class FIFO extends LinkedList implements IPaginator {

    public FIFO(int tamanhoMemoria) {
        super(tamanhoMemoria);
    }

    @Override
    public void addNode(LinkedListNode node) {
        var aux = getPrimeiro();
        LinkedListNode last = null;

        while (aux != null) {
            if (aux.getValue() == node.getValue()) {
                System.out.println("O valor " + node.getValue() + " já está paginado.");
                return;
            }
            last = aux;
            aux = aux.getNext();
        }

        if (getCapacidade() == getTamanho()) {
            removeComeco();
        }

        setTamanho(getTamanho() + 1);

        if (getPrimeiro() == null) {
            setPrimeiro(node);
        } else {
            assert last != null;
            last.setNext(node);
        }
    }


    @Override
    public void Paginar(List<Integer> paginas) {
        for(Integer pagina : paginas){
                this.addNode(new LinkedListNode(pagina){
            });
        }
    }
}
