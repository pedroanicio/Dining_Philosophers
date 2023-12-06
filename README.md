# Dining Philosophers
pt-br: Imagine que existem cinco filósofos sentados ao redor de uma mesa redonda. Entre cada filósofo, há um garfo. Cada filósofo passa a maior parte do seu tempo pensando, mas ocasionalmente fica com fome e precisa comer. Para comer, um filósofo precisa pegar os garfos que estão ao seu lado esquerdo e direito. No entanto, os garfos são compartilhados, e apenas um filósofo pode usá-los de cada vez. Portanto, um filósofo precisa pegar ambos os garfos para comer e, em seguida, colocá-los de volta na mesa quando terminar.
O problema é que, se todos os filósofos tentarem pegar o garfo à sua esquerda simultaneamente, eles podem entrar em um estado de deadlock, onde ninguém pode comer. Portanto, a tarefa é criar um algoritmo que permita que os filósofos compartilhem os garfos de maneira que evite deadlock e permita que todos eles comam.  A técnica usada para garantir a exclusão mútua é o uso de blocos synchronized e de semáforos em torno das operações críticas que envolvem a manipulação dos garfos. Isso garante que apenas um filósofo por vez pode acessar e manipular os garfos, evitando condições de corrida e garantindo a consistência do estado do sistema.


O algorimo foi desenvolvido utilizando Java, com conceitos de threads, POO e algoritmos e estrutura de dados.

En: Imagine that there are five philosophers sitting around a round table. Between each philosopher, there is a fork. Each philosopher spends most of their time thinking, but occasionally they get hungry and need to eat. To eat, a philosopher needs to pick up the forks on their left and right. However, the forks are shared, and only one philosopher can use them at a time. Therefore, a philosopher needs to pick up both forks to eat and then put them back on the table when they are done.

The problem is that if all philosophers try to pick up the fork on their left simultaneously, they can enter a state of deadlock where no one can eat. Therefore, the task is to create an algorithm that allows the philosophers to share the forks in a way that avoids deadlock and allows all of them to eat.

The algorithm was developed using Java, with the concepts of threads, OOP, and algorithms and data structures.


