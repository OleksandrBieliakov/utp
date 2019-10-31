package ass5.messages;

import java.util.Random;

public enum Priority {
    LOW,
    NORMAL,
    HIGH;

    public static Priority generatePriority() {
        Priority[] priorities = Priority.values();
        return priorities[new Random().nextInt(priorities.length)];
    }

}
