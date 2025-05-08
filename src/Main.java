import PaginationAlgos.Clock;
import PaginationAlgos.FIFO;
import PaginationAlgos.LRU;
import PaginationAlgos.NFU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int tamanhoMemoria = 3;

        // Caso 1 – Alternância alta
        // Esperado: FIFO=8, LRU=7, NFU=6
        List<Integer> caso1 = Arrays.asList(1, 2, 3, 1, 4, 5, 1, 2, 3);

        // Caso 2 – Acesso contínuo com reaproveitamento
        // Esperado: FIFO=3, LRU=3, NFU=3
        List<Integer> caso2 = Arrays.asList(1, 2, 3, 1, 2, 3, 1, 2, 3);

        // Caso 3 – Substituição completa
        // Esperado: FIFO=7, LRU=7, NFU=7
        List<Integer> caso3 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        // Caso 4 – Reutilização com atraso
        // Esperado: FIFO=8, LRU=6, NFU=6
        List<Integer> caso4 = Arrays.asList(1, 2, 3, 4, 1, 2, 3, 4);

        // Caso 5 – Frequência ajuda o NFU
        // Esperado: FIFO=6, LRU=5, NFU=4
        List<Integer> caso5 = Arrays.asList(1, 2, 1, 2, 3, 4, 1, 2);

        List<List<Integer>> casosDeTeste = List.of(caso1, caso2, caso3, caso4, caso5);

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
