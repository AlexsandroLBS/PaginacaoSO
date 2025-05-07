import PaginationAlgos.Clock;
import PaginationAlgos.FIFO;
import PaginationAlgos.LRU;
import PaginationAlgos.NFU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int tamanhoMemoria = 5;

        // Caso 1: Sequência com repetições simples
        List<Integer> paginas1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5));
        // FIFO: 5
        // LRU: 5
        // NFU: 5

        // Caso 2: Sequência com substituições frequentes
//        List<Integer> paginas2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        // FIFO: 10
        // LRU: 10
        // NFU: 10

        // Caso 3: Sequência com padrões repetidos
//        List<Integer> paginas3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 1, 2, 6, 7, 1, 2, 3, 4, 5));
        // FIFO: 10
        // LRU: 8
        // NFU: 8

        // Caso 4: Sequência com alta reutilização
//        List<Integer> paginas4 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5));
        // FIFO: 5
        // LRU: 5
        // NFU: 5

        // Caso 5: Sequência com substituições alternadas
//        List<Integer> paginas5 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 1, 2, 7, 8, 9, 10));
        // FIFO: 10
        // LRU: 9
        // NFU: 9

        // Testando os casos
//        List<List<Integer>> casosDeTeste = Arrays.asList(paginas1, paginas2, paginas3, paginas4, paginas5);
        List<List<Integer>> casosDeTeste = List.of(paginas1);

        for (int i = 0; i < casosDeTeste.size(); i++) {
            System.out.println("Caso de Teste " + (i + 1));

            FIFO fifo = new FIFO(tamanhoMemoria);
            fifo.paginar(casosDeTeste.get(i));
            fifo.printLista();
            fifo.generateMetrics();

            LRU lru = new LRU(tamanhoMemoria);
            lru.paginar(casosDeTeste.get(i));
            lru.printLista();
            lru.generateMetrics();

            NFU nfu = new NFU(tamanhoMemoria);
            nfu.paginar(casosDeTeste.get(i));
            nfu.printLista();
            nfu.generateMetrics();

            System.out.println("----------------------------");
        }
    }
}