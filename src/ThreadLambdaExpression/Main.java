/*
 * Criação de Threads utilizando Lambda Expression
 * Vítor Yudi Shinohara
 * UTFPR
 */
package ThreadLambdaExpression;

/**
 *
 * @author yudi
 */
public class Main {

    public static void main(String argv[]) {

        Runnable task = () -> {
            System.out.println("Teste");
        };

        new Thread(task).start();
    }
}
