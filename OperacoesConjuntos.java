import java.util.Scanner;

public class OperacoesConjuntos {
    private static final int CAPACIDADE_MAX = 30;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int[] conjuntoA = new int[CAPACIDADE_MAX];
        int[] conjuntoB = new int[CAPACIDADE_MAX];
        int tamanhoA = 0;
        int tamanhoB = 0;
        int opcao;
        
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    System.out.print("Digite um elemento para o Conjunto A: ");
                    int elementoA = scanner.nextInt();
                    tamanhoA = inserirElemento(conjuntoA, tamanhoA, elementoA);
                    break;
                    
                case 2:
                    System.out.print("Digite um elemento para o Conjunto B: ");
                    int elementoB = scanner.nextInt();
                    tamanhoB = inserirElemento(conjuntoB, tamanhoB, elementoB);
                    break;
                    
                case 3:
                    System.out.print("Conjunto A: ");
                    imprimir(conjuntoA, tamanhoA);
                    System.out.print("Conjunto B: ");
                    imprimir(conjuntoB, tamanhoB);
                    break;
                    
                case 4:
                    System.out.print("União de A e B: ");
                    int[] uniao = new int[CAPACIDADE_MAX * 2];
                    int tamanhoUniao = gerarUniao(conjuntoA, tamanhoA, conjuntoB, tamanhoB, uniao);
                    imprimir(uniao, tamanhoUniao);
                    break;
                    
                case 5:
                    System.out.print("Interseção de A e B: ");
                    int[] intersecao = new int[CAPACIDADE_MAX];
                    int tamanhoIntersecao = gerarIntersecao(conjuntoA, tamanhoA, conjuntoB, tamanhoB, intersecao);
                    imprimir(intersecao, tamanhoIntersecao);
                    break;
                    
                case 6:
                    System.out.print("Diferença entre A e B (A - B): ");
                    int[] diferencaAB = new int[CAPACIDADE_MAX];
                    int tamanhoDiferencaAB = gerarDiferenca(conjuntoA, tamanhoA, conjuntoB, tamanhoB, diferencaAB);
                    imprimir(diferencaAB, tamanhoDiferencaAB);
                    break;
                    
                case 7:
                    System.out.print("Diferença entre B e A (B - A): ");
                    int[] diferencaBA = new int[CAPACIDADE_MAX];
                    int tamanhoDiferencaBA = gerarDiferenca(conjuntoB, tamanhoB, conjuntoA, tamanhoA, diferencaBA);
                    imprimir(diferencaBA, tamanhoDiferencaBA);
                    break;
                    
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                    
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            
            if (opcao != 0) {
                System.out.println();
            }
            
        } while (opcao != 0);
        
        scanner.close();
    }
    
    /**
     * Exibe o menu de opções
     */
    public static void exibirMenu() {
        System.out.println("=== OPERAÇÕES COM CONJUNTOS ===");
        System.out.println("1) Inserir 1 elemento no Conjunto A");
        System.out.println("2) Inserir 1 elemento no Conjunto B");
        System.out.println("3) Imprimir os Conjuntos A e B");
        System.out.println("4) Gerar e Imprimir a união de A e B");
        System.out.println("5) Gerar e Imprimir a interseção entre A e B");
        System.out.println("6) Gerar e Imprimir a diferença entre A e B");
        System.out.println("7) Gerar e Imprimir a diferença entre B e A");
        System.out.println("0) Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    /**
     * Insere um elemento no vetor se não existir e houver espaço
     * @param v vetor onde inserir
     * @param tam tamanho atual do vetor
     * @param elemento elemento a ser inserido
     * @return novo tamanho do vetor (tam + 1 se inseriu, tam se não inseriu)
     */
    public static int inserirElemento(int[] v, int tam, int elemento) {
        // Verifica se o vetor está cheio
        if (tam >= CAPACIDADE_MAX) {
            System.out.println("Erro: Conjunto está cheio!");
            return tam;
        }
        
        // Verifica se o elemento já existe
        if (buscaSequencial(v, tam, elemento) != -1) {
            System.out.println("Erro: Elemento já existe no conjunto!");
            return tam;
        }
        
        // Insere o elemento
        v[tam] = elemento;
        System.out.println("Elemento inserido com sucesso!");
        return tam + 1;
    }
    
    /**
     * Busca sequencial por um elemento no vetor
     * @param v vetor onde buscar
     * @param tam tamanho do vetor
     * @param x elemento a ser buscado
     * @return índice do elemento se encontrado, -1 caso contrário
     */
    public static int buscaSequencial(int[] v, int tam, int x) {
        for (int i = 0; i < tam; i++) {
            if (v[i] == x) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Imprime todos os elementos do conjunto no formato { 1, 3, 6, 2 }
     * @param v vetor a ser impresso
     * @param tam tamanho do vetor
     */
    public static void imprimir(int[] v, int tam) {
        System.out.print("{ ");
        for (int i = 0; i < tam; i++) {
            System.out.print(v[i]);
            if (i < tam - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
    }
    
    /**
     * Gera a união entre dois conjuntos
     * @param conjuntoA primeiro conjunto
     * @param tamanhoA tamanho do primeiro conjunto
     * @param conjuntoB segundo conjunto
     * @param tamanhoB tamanho do segundo conjunto
     * @param resultado vetor para armazenar o resultado
     * @return tamanho do conjunto resultado
     */
    public static int gerarUniao(int[] conjuntoA, int tamanhoA, int[] conjuntoB, int tamanhoB, int[] resultado) {
        int tamanhoResultado = 0;
        
        // Adiciona todos os elementos do conjunto A
        for (int i = 0; i < tamanhoA; i++) {
            resultado[tamanhoResultado] = conjuntoA[i];
            tamanhoResultado++;
        }
        
        // Adiciona elementos do conjunto B que não estão em A
        for (int i = 0; i < tamanhoB; i++) {
            if (buscaSequencial(resultado, tamanhoResultado, conjuntoB[i]) == -1) {
                resultado[tamanhoResultado] = conjuntoB[i];
                tamanhoResultado++;
            }
        }
        
        return tamanhoResultado;
    }
    
    /**
     * Gera a interseção entre dois conjuntos
     * @param conjuntoA primeiro conjunto
     * @param tamanhoA tamanho do primeiro conjunto
     * @param conjuntoB segundo conjunto
     * @param tamanhoB tamanho do segundo conjunto
     * @param resultado vetor para armazenar o resultado
     * @return tamanho do conjunto resultado
     */
    public static int gerarIntersecao(int[] conjuntoA, int tamanhoA, int[] conjuntoB, int tamanhoB, int[] resultado) {
        int tamanhoResultado = 0;
        
        // Verifica cada elemento de A se está em B
        for (int i = 0; i < tamanhoA; i++) {
            if (buscaSequencial(conjuntoB, tamanhoB, conjuntoA[i]) != -1) {
                resultado[tamanhoResultado] = conjuntoA[i];
                tamanhoResultado++;
            }
        }
        
        return tamanhoResultado;
    }
    
    /**
     * Gera a diferença entre dois conjuntos (A - B)
     * @param conjuntoA primeiro conjunto
     * @param tamanhoA tamanho do primeiro conjunto
     * @param conjuntoB segundo conjunto
     * @param tamanhoB tamanho do segundo conjunto
     * @param resultado vetor para armazenar o resultado
     * @return tamanho do conjunto resultado
     */
    public static int gerarDiferenca(int[] conjuntoA, int tamanhoA, int[] conjuntoB, int tamanhoB, int[] resultado) {
        int tamanhoResultado = 0;
        
        // Adiciona elementos de A que não estão em B
        for (int i = 0; i < tamanhoA; i++) {
            if (buscaSequencial(conjuntoB, tamanhoB, conjuntoA[i]) == -1) {
                resultado[tamanhoResultado] = conjuntoA[i];
                tamanhoResultado++;
            }
        }
        
        return tamanhoResultado;
    }
}