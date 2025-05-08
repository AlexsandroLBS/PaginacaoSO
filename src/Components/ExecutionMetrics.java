package Components;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ExecutionMetrics{
    private long executionTime;

    private int tradesNumber;

    private int pageMissing;

    private List<Integer> visitedPages = new ArrayList<>();


    public int getTradesNumber() {
        return tradesNumber;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void clearMetrics(){
        executionTime = 0;
        tradesNumber = 0;
        visitedPages = new ArrayList<>();
        pageMissing = 0;
    }

    public void incrementTradesNumber(){
        this.tradesNumber++;
    }

    public boolean verifyVisited(int page){
        return visitedPages.contains(page);
    }

    public void incrementPageMissing(){
        this.pageMissing++;
    }

    public int getPageMissing(){
        return this.pageMissing;
    }


    public void setExecutionTime(long executionTime){
        this.executionTime = executionTime;
    }


    public void printMetrics() {
        System.out.println("===== Métricas de Execução =====");
//        System.out.println("Tempo de execução: " + executionTime + " ns (" + (executionTime / 1_000_000.0) + " ms)");
//        System.out.println("Número de operações (trades): " + tradesNumber);
        System.out.println("Número de falta de páginas (page fault): " + pageMissing);
        System.out.println("================================");
    }
}
