import PaginationAlgos.FIFO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int tamanhoMemoria = 5;
        List<Integer> paginas = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 1, 2, 6));

        FIFO fifo = new FIFO(tamanhoMemoria);

        fifo.Paginar(paginas);

        fifo.printLista();
    }
}