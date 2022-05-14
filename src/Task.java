import java.util.concurrent.RecursiveTask;

public class Task extends RecursiveTask<Integer> {

    private final int start;
    private final int end;
    private final int[] array;

    private Task(int start, int end, int[] array) {
        this.start = start;
        this.end = end;
        this.array = array;
    }

    public Task(int[] array) {
        this(0, array.length, array);
    }

    @Override
    protected Integer compute() {
        switch (end -  start) {
            case 0 -> {
                return 0;
            }
            case 1 -> {
                return array[start];
            }
            case 2 -> {
                return array[start] + array[start + 1];
            } case 3 -> {
                return array[start] + array[start + 1] + array[start + 2];
            } case 4 -> {
                return array[start] + array[start + 1] + array[start + 2] + array[start + 3];
            } default -> {
                return forkTask();
            }
        }
    }

    private Integer forkTask() {
        final int middle = (end - start) / 2 + start;
        Task task1 = new Task(start, middle, array);
        Task task2 = new Task(middle, end, array);
        invokeAll(task1, task2);
        return task1.join() + task2.join();
    }
}