package Components.LinkedList;

public abstract class LinkedList {

    private int capacidade;

    private int tamanho;

    private LinkedListNode primeiro = null;

    private LinkedListNode ultimo = null;

    public LinkedList(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public LinkedListNode getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(LinkedListNode primeiro) {
        this.primeiro = primeiro;
    }

    public LinkedListNode getUltimo() {
        return ultimo;
    }

    public void setUltimo(LinkedListNode ultimo) {
        this.ultimo = ultimo;
    }

    public abstract void addNode(LinkedListNode node);

    public void removeComeco(){
        if(primeiro != null){
            tamanho--;
            primeiro = primeiro.getNext();
        }
    }

    public void removeFinal(){
        if(primeiro == null){
            return;
        }
        tamanho--;
        var aux = primeiro;
        while (aux.getNext().getNext() != null){
            aux = aux.getNext();
        }
        aux.setNext(null);
        ultimo = aux;
    }


    public void printLista() {
        LinkedListNode atual = primeiro;

        if (atual == null) {
            System.out.println("Memoria vazia.");
            return;
        }

        System.out.print("Memória atual: ");
        while (atual != null) {
            System.out.print(atual.getValue() + " ");
            atual = atual.getNext();
        }
        System.out.println();
    }

}


