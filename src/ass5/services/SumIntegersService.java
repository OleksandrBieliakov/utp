package ass5.services;

import ass5.Request;
import ass5.Response;

import java.util.*;

public class SumIntegersService extends Service {

    public SumIntegersService(PriorityQueue<Request> requests, PriorityQueue<Response> responses, String name) {
        super(requests, responses, name);
    }

    @Override
    public Response fulfilRequest(Request request) {
        List<String> arguments = request.arguments();
        if (arguments == null) return new Response(request, WRONG_ARGS);
        double result = 0;
        try {
            for (String arg : arguments)
                result += Double.parseDouble(arg);
        } catch (NumberFormatException e) {
            return new Response(request, WRONG_ARGS);
        }
        return new Response(request, new ArrayList<>(Collections.singletonList(result + "")));
    }

}
