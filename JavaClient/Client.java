import java.io.*;
import java.net.*;
import java.net.http.*;
import javax.management.ValueExp;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.w3c.dom.Node;

/**
 * This approach uses the java.net.http.HttpClient classes, which
 * were introduced in Java11.
 */
public class Client {
    private static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    public static void main(String... args) throws Exception {
        // System.out.println(add() == 0);
        System.out.println(add(1, 2, 3, 4, 5) == 15);
        // System.out.println(add(2, 4) == 6);
        // System.out.println(subtract(12, 6) == 6);
        // System.out.println(multiply(3, 4) == 12);
        // System.out.println(multiply(1, 2, 3, 4, 5) == 120);
        // System.out.println(divide(10, 5) == 2);
        // System.out.println(modulo(10, 5) == 0);
    }

    public static int add(int lhs, int rhs) throws Exception {
        return -1;
    }

    public static int add(Integer... params) throws Exception {
        DocumentBuilder factory = dbf.newDocumentBuilder();
        Document builder = factory.newDocument();
        builder.appendChild(builder.createElement("methodCall"));
        builder.getDocumentElement().appendChild(builder.createElement("params"));
        NodeList elements = element.getElementsByTagName("params");
        Element innerElement = (Element) elements.item(0);
        for (Integer value : params) {
            Element param = builder.createElement("param");
            Element paramValue = builder.createElement("value");
            paramValue.setTextContent(Integer.toString(value));
            param.appendChild(paramValue);
            innerElement.appendChild(param);
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:4567/get")).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("This is the body: " + response.body());
        return -1;
    }

    public static int subtract(int lhs, int rhs) throws Exception {
        return -1;
    }

    public static int multiply(int lhs, int rhs) throws Exception {
        return -1;
    }

    public static int multiply(Integer... params) throws Exception {
        return -1;
    }

    public static int divide(int lhs, int rhs) throws Exception {
        return -1;
    }

    public static int modulo(int lhs, int rhs) throws Exception {
        return -1;
    }
}