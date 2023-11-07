public class filosofosFamintos {

    public static class Filosofo implements Runnable{  //fornece uma implementação do metodo run(), que tem o objetivo de executar uma thread
        //garfos de cada lado do filósofo
        private Object garfoEsquerdo;
        private Object garfoDireito;

        public Filosofo(Object garfoEsquerdo, Object garfoDireito){
            this.garfoEsquerdo = garfoEsquerdo;
            this.garfoDireito = garfoDireito;
        }

        @Override
        public void run(){ //metodo run() é chamado quando a thread é iniciada
            try{
                while(true){
                    //filósofo está pensando
                    acao(": Pensando");
                    synchronized (garfoEsquerdo){ // apenas um filósofo pode pegar o garfo à esquerda. Caso outro tente pegá-lo, ele aguardará até que o seja liberado
                        acao(": Pegou o garfo esquerdo");
                        synchronized (garfoDireito){//garante que apenas um filósofo por vez possa pegar o garfo à direita.
                            // Isso evita deadlock, onde todos os filósofos pegariam um garfo à esquerda e esperariam indefinidamente por um garfo à direita.

                            //filósofo está comendo
                            acao(": Pegou o garfo direito - comendo");
                            //filósofo terminou de comer
                            acao(": Soltou o garfo direito");
                        }
                        //filósofo terminou de comer
                        acao(": Voltando a pensar!");
                    }
                }
            } catch (InterruptedException e) {
                // caso a thread seja interrompida por alguma exceção, ela é finalizada
                Thread.currentThread().interrupt();
            }
        }
        //metodo para filósfo realizar uma ação(comer, pensar, pegar os garfos)
        private void acao(String acao) throws InterruptedException{
            System.out.println(Thread.currentThread().getName() + "" + acao);
            Thread.sleep(((int) (Math.random() * 100)));
        }

    }

    public static void main(String[] args) throws Exception{
        int qtde = 5; //número de filósofos
        Filosofo[] filosofos = new Filosofo[qtde];

        Object[] garfos = new Object[filosofos.length]; // número de garfos é o mesmo que o número de filósofos

        //cria os garfos
        for(int i = 0; i < garfos.length; i++){
            garfos[i] = new Object();
        }

        for(int i = 0; i < filosofos.length; i++){
            Object garfoEsquerdo = garfos[i];
            Object garfoDireito = garfos[(i + 1) % garfos.length];

            filosofos[i] = new Filosofo(garfoEsquerdo, garfoDireito);
            Thread t = new Thread(filosofos[i], "Filosofo " + (i + 1));
            t.start(); // Cria threads para os filósofos e inicia sua execução
        }



    }

}
