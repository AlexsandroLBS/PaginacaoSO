package PaginationAlgos;

import Components.ExecutionMetrics;

import java.util.List;

public interface IPaginator {
    void paginar(List<Integer> paginas);

    ExecutionMetrics generateMetrics();
}


