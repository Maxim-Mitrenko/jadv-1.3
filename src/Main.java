import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static Random random = new Random();

    public static void main(String[] args) {
        long first;
        long second;
        int[] intArray = random(100);
        System.out.println(Arrays.toString(intArray));
        first = System.currentTimeMillis();
        System.out.println(oneTread(intArray));
        second = System.currentTimeMillis();
        final long oneTreadTime = second - first;
        System.out.println(oneTreadTime + " миллисекунд выполнено одним потоком");
        first = System.currentTimeMillis();
        System.out.println(multithreaded(intArray));
        second = System.currentTimeMillis();
        final long multiTreadTime = second - first;
        System.out.println(multiTreadTime + " миллисекунд выполнено многопоточным программированием");
    }

    public static int[] random(int length) {
        int[] randomArray = new int[length];
        for (iment i = 0; i < length; i++) {
            randomArray[i] = random.nextInt(100);
        }
        return randomArray;
    }

    public static int oneTread(int[] numbers) {
        int i = 0;
        for (int number : numbers) {
            i += number;
        }
        return i;
    }


    public static int multithreaded(int[] numbers) {
        Task task = new Task(numbers);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(task);
    }
}