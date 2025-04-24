import PaginationAlgos.FIFO;
import PaginationAlgos.LRU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int tamanhoMemoria = 5;
        List<Integer> paginas = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 1, 7, 2, 8, 9));

        FIFO fifo = new FIFO(tamanhoMemoria);
        fifo.paginar(paginas);
        fifo.printLista();
        fifo.generateMetrics();

        LRU lru = new LRU(tamanhoMemoria);
        lru.paginar(paginas);
        lru.printLista();
        lru.generateMetrics();


    }
}