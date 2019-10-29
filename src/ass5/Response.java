package ass5;

import ass5.requesters.Requester;

import java.util.List;

public class Response implements Comparable<Response> {

    private Request request;
    private List<String> result;

    public Response(Request request, List<String> result) {
        this.request = request;
        this.result = result;
    }

    Response(Request request) {
        this.request = request;
    }

    public Request request() {
        return request;
    }

    public Requester requester() {
        return request.requester();
    }

    private RequestType type() {
        return request.type();
    }

    Priority priority() {
        return request.priority();
    }

    public List<String> result() {
        return result;
    }

    @Override
    public int compareTo(Response other) {
        return priority().weight() - other.priority().weight();
    }

    @Override
    public String toString() {
        return request.toString() + ": " + result;
    }

}
