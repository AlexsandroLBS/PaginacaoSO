import PaginationAlgos.Clock;
import PaginationAlgos.FIFO;
import PaginationAlgos.LRU;
import PaginationAlgos.NFU;

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
            var fifoMetrics = fifo.generateMetrics();

            LRU lru = new LRU(tamanhoMemoria);
            lru.paginar(casosDeTeste.get(i));
            lru.printLista();
            var lruMetrics = lru.generateMetrics();

            NFU nfu = new NFU(tamanhoMemoria);
            nfu.paginar(casosDeTeste.get(i));
            nfu.printLista();
            var nfuMetrics = nfu.generateMetrics();
            
            Clock clock = new Clock(tamanhoMemoria);
            clock.paginar(casosDeTeste.get(i));
            clock.printLista();
            var clockMetrics = clock.generateMetrics();

            // Comparando o número de faltas de página
            int fifoFaults = fifoMetrics.getPageMissing();
            int lruFaults = lruMetrics.getPageMissing();
            int nfuFaults = nfuMetrics.getPageMissing();
            int clockFaults = clockMetrics.getPageMissing();

            System.out.println("Faltas de página:");
            System.out.println("FIFO: " + fifoFaults);
            System.out.println("LRU: " + lruFaults);
            System.out.println("NFU: " + nfuFaults);
            System.out.println("Clock: " + clockFaults);

            if (fifoFaults == lruFaults && lruFaults == nfuFaults && nfuFaults == clockFaults) {
                System.out.println("\033[31mTodos os algoritmos tiveram o mesmo número de faltas de página.\033[0m");
            } else {
                if (fifoFaults <= lruFaults && fifoFaults <= nfuFaults && fifoFaults <= clockFaults) {
                    System.out.println("\033[32mFIFO teve o menor número de faltas de página.\033[0m");
                } else if (lruFaults <= fifoFaults && lruFaults <= nfuFaults && lruFaults <= clockFaults) {
                    System.out.println("\033[32mLRU teve o menor número de faltas de página.\033[0m");
                } else  if (nfuFaults <= fifoFaults && nfuFaults <= lruFaults && nfuFaults <= clockFaults) {
                    System.out.println("\033[32mNFU teve o menor número de faltas de página.\033[0m");
                } else {
                    System.out.println("\033[32mCLOCK teve o menor número de faltas de página.\033[0m");
                }

            }

            System.out.println("----------------------------");
        }
    }
}
