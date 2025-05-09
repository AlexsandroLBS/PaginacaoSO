# Projeto de Paginação de Memória

[Clique aqui para acessar o repositório](https://github.com/AlexsandroLBS/PaginacaoSO)

Este projeto foi desenvolvido como parte da disciplina **Projeto de Sistemas Operacionais**. Ele implementa e compara diferentes algoritmos de substituição de páginas em memória, utilizando uma estrutura de lista encadeada para gerenciar as páginas. O objetivo é simular o comportamento de algoritmos de paginação e analisar métricas como faltas de página, trocas de páginas e tempo de execução.

---

## **Autores**
- **Alexsandro Lopes** (Matrícula: 2323215)
- **Ricardo Temportal** (Matrícula: 2310292)
- **Camila Martins** (Matrícula: 2310286)

---

## **Descrição Geral**
O projeto utiliza uma estrutura de dados chamada `LinkedList`, que representa uma **lista encadeada**. Essa estrutura é usada para gerenciar as páginas em memória de forma dinâmica, permitindo a implementação de diferentes algoritmos de substituição de páginas.

### **Estrutura Geral**
A classe `LinkedList` é a base para os algoritmos de paginação. Ela fornece funcionalidades como:
- Adicionar e remover nós (páginas).
- Gerenciar o tamanho da memória.
- Representar a memória como uma lista encadeada.

Os algoritmos implementados no projeto são:
1. **FIFO (First-In, First-Out)**: Substitui a página mais antiga na memória.
2. **LRU (Least Recently Used)**: Substitui a página menos recentemente usada.
3. **NFU (Not Frequently Used)**: Substitui a página menos frequentemente usada.
4. **Clock**: Utiliza um ponteiro circular para gerenciar as páginas, substituindo páginas com o bit de uso igual a 0.

---

## **Funcionamento do `Main.java`**
O arquivo `Main.java` é o ponto de entrada do programa. Ele realiza os seguintes passos:

1. **Definição do Tamanho da Memória**:
   - O tamanho da memória é definido como `3` páginas.

2. **Casos de Teste**:
   - São definidos cinco casos de teste, cada um com uma sequência de páginas a serem acessadas. Cada caso simula diferentes padrões de acesso à memória:
     - **Caso 1 – Alternância alta**: Testa a alternância entre páginas.
       - **Esperado**: FIFO=8, LRU=7, NFU=6, Clock=7
     - **Caso 2 – Acesso contínuo com reaproveitamento**: Testa o reaproveitamento de páginas já carregadas.
       - **Esperado**: FIFO=3, LRU=3, NFU=3, Clock=3
     - **Caso 3 – Substituição completa**: Testa a substituição total das páginas na memória.
       - **Esperado**: FIFO=7, LRU=7, NFU=7, Clock=7
     - **Caso 4 – Reutilização com atraso**: Testa a reutilização de páginas após um intervalo.
       - **Esperado**: FIFO=8, LRU=6, NFU=6, Clock=6
     - **Caso 5 – Frequência ajuda o NFU**: Testa o impacto da frequência de acesso no algoritmo NFU.
       - **Esperado**: FIFO=6, LRU=5, NFU=4, Clock=5

    

3. **Execução dos Algoritmos**:
   - Para cada caso de teste, os algoritmos FIFO, LRU, NFU e Clock são executados.
   - Cada algoritmo realiza as seguintes operações:
     - **Paginação**: Processa a sequência de páginas.
     - **Impressão da Memória**: Exibe o estado final da memória.
     - **Geração de Métricas**: Exibe as métricas de execução, incluindo:
       - Número de faltas de página.
       - Número de trocas de páginas.
       - Tempo de execução.

4. **Comparação de Resultados**:
   - O programa compara o número de faltas de página entre os algoritmos e destaca qual teve o menor número de faltas.

---

## **Como Executar**
1. Certifique-se de ter o Java instalado em sua máquina.
2. Compile o projeto:
   ```bash
   javac -d out src/**/*.java
   ```

3. Execute o programa
    ```bash
    java -cp out Main
    ```


## **Exemplo de Saída**
A saída do programa exibe os resultados de cada caso de teste, incluindo:
- O estado final da memória.
- As métricas de execução para cada algoritmo.
- A comparação do número de faltas de página.

Exemplo de saída para um caso de teste:
```bash

Caso de Teste 1
Memória atual: 1 2 3
Para o FIFO
===== Métricas de Execução =====
Tempo de execução: 583 ns
Número de falta de páginas (page fault): 8
================================
Para o LRU
===== Métricas de Execução =====
Tempo de execução: 417 ns
Número de falta de páginas (page fault): 7
================================
Para o NFU
===== Métricas de Execução =====
Tempo de execução: 917 ns
Número de falta de páginas (page fault): 6
================================
Para o Clock
===== Métricas de Execução =====
Tempo de execução: 500 ns
Número de falta de páginas (page fault): 7
================================
```


## Contribuições
Este projeto foi desenvolvido em equipe como parte de um trabalho acadêmico. Sugestões e melhorias são bem-vindas!