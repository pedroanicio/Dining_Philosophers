import java.util.concurrent.Semaphore;


public class Filosofo implements Runnable {  //fornece uma implementação do metodo run(), que tem o objetivo de executar uma thread
    //garfos de cada lado do filósofo
    private Object garfoEsquerdo;
    private Object garfoDireito;
    private int fome;
    private static Semaphore semaforo = new Semaphore(1); // Semáforo global para controlar o acesso à região crítica


    public int getFome() {
        return fome;
    }

    public void setFome(int fome) {
        this.fome = fome;
    }

    public Filosofo(Object garfoEsquerdo, Object garfoDireito, int fome) {
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
        this.fome = fome;
    }

    @Override
    public void run() { //metodo run() é chamado quando a thread é iniciada
        try {
            while (Filosofo.this.fome < 3) {
                //filósofo está pensando
                acao(": Pensando");

                // Introduzir atraso deliberado para alguns filósofos (simulando inanição)
                if (Thread.currentThread().getName().equals("Filosofo "+ 1)) {
                    Thread.sleep(35000); // Atraso de 20 segundos para Filosofo aleatoriamente escolhido
                    Thread.currentThread().interrupt();
                    System.out.println("Filosofo "+1+ " morreu de starvation!");
                    System.exit(0);
                }

                //aqui começa a região crítica
                synchronized (garfoEsquerdo) { // apenas um filósofo pode pegar o garfo à esquerda. Caso outro tente pegá-lo, ele aguardará até que o seja liberado
                    acao(": Pegou o garfo esquerdo");
                    synchronized (garfoDireito) {//garante que apenas um filósofo por vez possa pegar o garfo à direita.
                        // Isso evita deadlock, onde todos os filósofos pegariam um garfo à esquerda e esperariam indefinidamente por um garfo à direita.
                        //filósofo está comendo
                        semaforo.acquire();
                        Filosofo.this.setFome(Filosofo.this.getFome() + 1);
                        acao(": Pegou o garfo direito - comendo - Matou " + Filosofo.this.getFome() + "/3 da fome");
                        //filósofo terminou de comer
                        acao(": Soltou o garfo direito");
                    }
                    //aqui termina a região crítica
                    acao(": Soltou o garfo esquerdo");
                    semaforo.release();

                    //filósofo terminou de comer
                    acao(": Voltando a pensar!");
                    Thread.sleep(3000);
                }


            }
        } catch (InterruptedException e) {
            // caso a thread seja interrompida por alguma exceção, ela é finalizada
            Thread.currentThread().interrupt();
        }
    }

    //metodo para filósfo realizar uma ação(comer, pensar, pegar os garfos)
    private void acao(String acao) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + acao);
        Thread.sleep(500);
    }

}
