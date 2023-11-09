public class filosofosFamintos {
    public static void main(String[] args) throws Exception{
        int qtde = 5; //número de filósofos
        int fome = 0; //número de vezes que cada filósofo come
        Filosofo[] filosofos = new Filosofo[qtde];

        Object[] garfos = new Object[filosofos.length]; // número de garfos é o mesmo que o número de filósofos

        //cria os garfos
        for(int i = 0; i < garfos.length; i++){
            garfos[i] = new Object();
        }

        for(int i = 0; i < filosofos.length; i++){
            Object garfoEsquerdo = garfos[i];
            Object garfoDireito = garfos[(i + 1) % garfos.length];

            //filosofos[i] = new Filosofo(garfoEsquerdo, garfoDireito, fome);
            if(i == filosofos.length - 1){
                filosofos[i] = new Filosofo(garfoDireito, garfoEsquerdo, fome); // último filósofo pega o garfo da direita primeiro para evitar deadlock
            }else {
                filosofos[i] = new Filosofo(garfoEsquerdo, garfoDireito, fome);
            }

            Thread t = new Thread(filosofos[i], "Filosofo " + (i + 1));
            t.start(); // Cria threads para os filósofos e inicia sua execução
        }



    }

}