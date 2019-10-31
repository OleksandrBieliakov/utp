package ass5.participants;

import ass5.MessagesQueue;

abstract class Participant implements Runnable {

    private static final int SLEEP_TIME = 1000;

    MessagesQueue queue;

    Participant(MessagesQueue queue) {
        this.queue = queue;
    }

    void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
