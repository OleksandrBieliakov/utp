package ass12;

import ass12.gui.TasksFrame;

public class Main {

    // Integrate refreshing thread with table model
    // Add Cell Editor for Statuses column
    // Think about Task being Callable(is needed? can be Runnable? how to use result/done()?)

    private static final int REFRESH_RATE = 200;

    public static void main(String[] args) {
        TasksFrame frame = new TasksFrame();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(REFRESH_RATE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frame.refresh();
            }
        }).start();
    }

}
