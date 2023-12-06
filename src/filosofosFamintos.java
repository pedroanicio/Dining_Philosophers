import java.util.Random;

public class filosofosFamintos {
    public static void main(String[] args) throws Exception {
        int qtde = 5; //número de filósofos
        int fome = 0; //número de vezes que cada filósofo come
        Filosofo[] filosofos = new Filosofo[qtde];

        Object[] garfos = new Object[filosofos.length]; // número de garfos é o mesmo que o número de filósofos

        //cria os garfos
        for (int i = 0; i < garfos.length; i++) {
            garfos[i] = new Object();
        }

        //cria os filósofos
        for (int i = 0; i < filosofos.length; i++) {
            Object garfoEsquerdo = garfos[i];
            Object garfoDireito = garfos[(i + 1) % garfos.length];

            //filosofos[i] = new Filosofo(garfoEsquerdo, garfoDireito, fome); //deadlock
            if (i == filosofos.length - 1) {
                filosofos[i] = new Filosofo(garfoDireito, garfoEsquerdo, fome); // último filósofo pega o garfo da direita primeiro para evitar deadlock
            } else {
                filosofos[i] = new Filosofo(garfoEsquerdo, garfoDireito, fome);
            }
        }

        embaralharArray(filosofos); // embaralha o array de filósofos
        for (Filosofo filosofo : filosofos) {
            Thread t = new Thread(filosofo, "Filosofo " + (getIndex(filosofos, filosofo) + 1));
            t.start(); // Cria threads para os filósofos e inicia sua execução
        }
    }

    // Função para embaralhar um array de filósofos
    private static void embaralharArray(Filosofo[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Troca os elementos i e j
            Filosofo temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // Função para obter o índice de um filósofo em um array
    private static int getIndex(Filosofo[] array, Filosofo filosofo) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == filosofo) {
                return i;
            }
        }
        return -1; // Não encontrado
    }
}