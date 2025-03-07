import java.util.Scanner;

public class PilhaHanoi {
    public static int[] pilhaA = {5, 4, 3, 2, 1};
    public static int[] pilhaB = {0, 0, 0, 0, 0};
    public static int[] pilhaC = {0, 0, 0, 0, 0};
    
    public static int topOrigem;
    public static int topDestino;
    
    public static char origem;
    public static char destino;
    
    public static boolean cheio = false;
    public static boolean vazio = false;
    public static boolean jogoAtivo = true;
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        while (jogoAtivo) {            
            exibePilhas();
            
            System.out.println("\n1) Escolha a pilha de origem:");
            origem = teclado.nextLine().charAt(0);
            origem = Character.toUpperCase(origem);
            
            estaVazia(origem);
            
            if (!vazio && origem == 'A' || !vazio && origem == 'B' || !vazio && origem == 'C') {
                System.out.println("2) Escolha a pilha de destino:");
                destino = teclado.nextLine().charAt(0);
                destino = Character.toUpperCase(destino);
            }else{
                System.out.println("Alerta! A pilha origem está vazia ou não existe. Vamos voltar e tentar novamente.\n");
                continue;
            }
            
            estaCheia(destino);
            
            if (cheio == false && destino != origem && destino == 'A' || destino == 'B' || destino == 'C') {
                System.out.println("Tudo certo até aqui. Vamos ver se é possível fazer a transfêrencia de disco.");
                System.out.println("_______________________________________\n");
            }else{
                System.out.println("Alerta! Ou pilha de destino está cheia, ou foi selecionada a mesma pilha para"
                        + " origem e destino, ou foi um inserido um valor inválido. Voltando para o inicio.\n");
                continue;
            }
            
            consultaTopos(origem, destino);
            movendoDisco(origem, destino);
            checarVitoria();
            
        }
        
    }
    
    //funcao que exibe as pilhas para ajudar o usuario durante as jogadas
    //pode ser considerado um consulta topo, pois exibe todas as pilhas e consequentemente seus topos
    public static void exibePilhas(){
        System.out.println("Exibindo estado atual das pilhas:");
        System.out.println("_______________________________________");
        System.out.println("Pilha A:        Pilha B:        Pilha C:");
        for (int i = 4; i >= 0; i--) {
            System.out.println("  |" + pilhaA[i] + "|             |"+ pilhaB[i]+"|             |"+pilhaC[i] + "|");
        }
        System.out.println("_______________________________________");
    }
    
    //funcao esta cheia
    public static void estaCheia(char pilhaEscolha1){
        cheio = false;
        switch (pilhaEscolha1) {
            case 'A':
                if (pilhaA[4] == 0) {
                    System.out.println("A pilha A não está cheia. O processo pode continuar...\n");
                }else{
                    System.out.println("A pilha A está cheia.");
                    cheio = true;
                }
                break;
            case 'B':
                if (pilhaB[4] == 0) {
                    System.out.println("A pilha B não está cheia. O processo pode continuar...\n");
                }else{
                    System.out.println("A pilha B está cheia.");
                    cheio = true;
                }
                break;
            case 'C':
                if (pilhaC[4] == 0) {
                    System.out.println("A pilha C não está cheia. O processo pode continuar...\n");
                }else{
                    System.out.println("A pilha C está cheia.");
                    cheio = true;
                }
                break;
            default:
                System.out.println("Pilha não encontrada. Digite apenas A, B ou C da próxima vez.");
                cheio = false;
        }
    }
    
    //funcao esta vazia
    public static void estaVazia(char pilhaEscolha2){
        vazio = false;
        switch (pilhaEscolha2) {
            case 'A':
                if (pilhaA[0] == 0) {
                    System.out.println("A pilha A está vazia.");
                    vazio = true;
                }
                break;
            case 'B':
                if (pilhaB[0] == 0) {
                    System.out.println("A pilha B está vazia.");
                    vazio = true;
                }
                break;
            case 'C':
                if (pilhaC[0] == 0) {
                    System.out.println("A pilha C está vazia.");
                    vazio = true;
                }
                break;
            default:
                System.out.println("Pilha não encontrada. Digite apenas A, B ou C da próxima vez.");
        }
    }
    
    //funcao que verifica a posicao dos topos conforme selecionado pelo usuario
    //pensei em criar uma variavel para cada topo,mas optei por calcular o topo de origem e destino conforme
    //escolhido pelo usuario a cada movimentacao, embora provavelmente exija mais processamento
    public static void consultaTopos(char topO, char topD){
        topOrigem = -1;
        topDestino = -1;
        
        switch (topO) {
            case 'A':
                for (int i = 0; i < 5; i++) {
                    if (pilhaA[i] != 0) {
                        topOrigem++;
                    }
                }
                
                break;
            case 'B':
                for (int i = 0; i < 5; i++) {
                    if (pilhaB[i] != 0) {
                        topOrigem++;
                    }
                }
                break;
            case 'C':
                for (int i = 0; i < 5; i++) {
                    if (pilhaC[i] != 0) {
                        topOrigem++;
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
        
        switch (topD) {
            case 'A':
                for (int i = 0; i < 5; i++) {
                    if (pilhaA[i] != 0) {
                        topDestino++;
                    }
                }
                break;
            case 'B':
                for (int i = 0; i < 5; i++) {
                    if (pilhaB[i] != 0) {
                        topDestino++;
                    }
                }
                break;
            case 'C':
                for (int i = 0; i < 5; i++) {
                    if (pilhaC[i] != 0) {
                        topDestino++;
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }
    
    //funcao que desempilha de uma pilha e empilha em outra
    public static void movendoDisco(char orig, char dest){
        
        if (orig == 'A' && dest == 'B') {
            if (topDestino == -1) {
                pilhaB[topDestino+1] = pilhaA[topOrigem];
                pilhaA[topOrigem] = 0;
            }
            else if (pilhaA[topOrigem] < pilhaB[topDestino]) {
                pilhaB[topDestino+1] = pilhaA[topOrigem];
                pilhaA[topOrigem] = 0;
            }else{
                System.out.println("\nO disco de origem é maior do que o de destino. Por favor, tente novamente"
                    + " com outra combinação.\n");
            }
        }
        
        if (orig == 'A' && dest == 'C') {
            if (topDestino == -1) {
                pilhaC[topDestino+1] = pilhaA[topOrigem];
                pilhaA[topOrigem] = 0;
            }
            else if (pilhaA[topOrigem] < pilhaC[topDestino]) {
                pilhaC[topDestino+1] = pilhaA[topOrigem];
                pilhaA[topOrigem] = 0;
            }else{
                System.out.println("\nO disco de origem é maior do que o de destino. Por favor, tente novamente"
                    + " com outra combinação.\n");
            }
        }
        
        if (orig == 'B' && dest == 'A') {
            if (topDestino == -1) {
                pilhaA[topDestino+1] = pilhaB[topOrigem];
                pilhaB[topOrigem] = 0;
            }
            else if (pilhaB[topOrigem] < pilhaA[topDestino]) {
                pilhaA[topDestino+1] = pilhaB[topOrigem];
                pilhaB[topOrigem] = 0;
            }else{
                System.out.println("\nO disco de origem é maior do que o de destino. Por favor, tente novamente"
                    + " com outra combinação.\n");
            }
        }
        
        if (orig == 'B' && dest == 'C') {
            if (topDestino == -1) {
                pilhaC[topDestino+1] = pilhaB[topOrigem];
                pilhaB[topOrigem] = 0;
            }
            else if (pilhaB[topOrigem] < pilhaC[topDestino]) {
                pilhaC[topDestino+1] = pilhaB[topOrigem];
                pilhaB[topOrigem] = 0;
            }else{
                System.out.println("\nO disco de origem é maior do que o de destino. Por favor, tente novamente"
                    + " com outra combinação.\n");
            }
        }
        
        if (orig == 'C' && dest == 'A') {
            if (topDestino == -1) {
                pilhaA[topDestino+1] = pilhaC[topOrigem];
                pilhaC[topOrigem] = 0;
            }
            else if (pilhaC[topOrigem] < pilhaA[topDestino]) {
                pilhaA[topDestino+1] = pilhaC[topOrigem];
                pilhaC[topOrigem] = 0;
            }else{
                System.out.println("\nO disco de origem é maior do que o de destino. Por favor, tente novamente"
                    + " com outra combinação.\n");
            }
        }
        
        if (orig == 'C' && dest == 'B') {
            if (topDestino == -1) {
                pilhaB[topDestino+1] = pilhaC[topOrigem];
                pilhaC[topOrigem] = 0;
            }
            else if (pilhaC[topOrigem] < pilhaB[topDestino]) {
                pilhaB[topDestino+1] = pilhaC[topOrigem];
                pilhaC[topOrigem] = 0;
            }else{
                System.out.println("\nO disco de origem é maior do que o de destino. Por favor, tente novamente"
                    + " com outra combinação.\n");
            }
        }

    }
    
    //funcao que checa se o jogo acabou
    public static void checarVitoria(){
        if (pilhaC[0] == 5 && pilhaC[4] == 1) {
            System.out.println("Você conseguiu completar a torre. Parabéns.");
            jogoAtivo = false;
        }
    }
    
}