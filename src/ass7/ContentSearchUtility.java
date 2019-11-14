package ass7;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ContentSearchUtility {

    private ContentSearchUtility() {
    }

    public static boolean containsText(InputStream input, String content) throws IOException {
        return new Scanner(input).hasNext(content);
    }

}
