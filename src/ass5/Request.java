package ass5;

import ass5.requesters.Requester;

import java.util.List;

public class Request implements Comparable<Request> {

    private Requester requester;
    private RequestType type;
    private List<String> arguments;
    private Priority priority;

    public Request(Requester requester, RequestType type, List<String> arguments, Priority priority) {
        this.requester = requester;
        this.type = type;
        this.arguments = arguments;
        this.priority = priority;
    }

    public Request(Requester requester, RequestType type, Priority priority) {
        this.requester = requester;
        this.type = type;
        this.priority = priority;
    }

    Request(Priority priority) {
        this.priority = priority;
    }

    Requester requester() {
        return requester;
    }

    RequestType type() {
        return type;
    }

    public List<String> arguments() {
        return arguments;
    }

    Priority priority() {
        return priority;
    }

    @Override
    public int compareTo(Request other) {
        return priority.weight() - other.priority.weight();
    }

    @Override
    public String toString() {
        return "from " + requester.name() + " " + type.toString() + arguments + " (" + priority + ")";
    }

}
