package Components;

public class ExecutionMetrics{
    private long executionTime;

    private int tradesNumber;


    public int getTradesNumber() {
        return tradesNumber;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void clearMetrics(){
        executionTime = 0;
        tradesNumber = 0;
    }

    public void incrementTradesNumber(){
        this.tradesNumber++;
    }


    public void setExecutionTime(long executionTime){
        this.executionTime = executionTime;
    }


    public void printMetrics() {
        System.out.println("===== Métricas de Execução =====");
        System.out.println("Tempo de execução: " + executionTime + " ns (" + (executionTime / 1_000_000.0) + " ms)");
        System.out.println("Número de operações (trades): " + tradesNumber);
        System.out.println("================================");
    }
}
