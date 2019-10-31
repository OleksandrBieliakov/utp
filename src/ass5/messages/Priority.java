package ass5.messages;

import java.util.Random;

public enum Priority {
    HIGH,
    NORMAL,
    LOW;

    public static Priority generatePriority() {
        Priority[] priorities = Priority.values();
        return priorities[new Random().nextInt(priorities.length)];
    }

}
