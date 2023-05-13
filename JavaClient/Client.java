import java.io.*;
import java.net.*;
import java.net.http.*;
import javax.management.ValueExp;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.text.AbstractDocument.ElementEdit;
import javax.xml.*;
import org.w3c.dom.*;
import org.w3c.dom.Node;

/**
 * This approach uses the java.net.http.HttpClient classes, which
 * were introduced in Java11.
 */
public class Client {
    static String host = "";
    static String port = "0";
    public static void main(String... args) throws Exception {
        host = args[0];
        port = args[1];
        System.out.println(add() == 0);
        System.out.println(add(1, 2, 3, 4, 5) == 15);
        System.out.println(add(2, 4) == 6);
        System.out.println(subtract(12, 6) == 6);
        System.out.println(multiply(3, 4) == 12);
        System.out.println(multiply(1, 2, 3, 4, 5) == 120);
        System.out.println(divide(10, 5) == 2);
        System.out.println(modulo(10, 5) == 0);
    }

    public static int add(int lhs, int rhs) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String body = "<methodCall><params><param><value><i4>" + lhs + "</i4></value></param>" + "<param><value><i4>" + rhs + "</i4></value></param></params></methodCall>";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://" + host + ":" + port + "/RPC/ADD")).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Integer.parseInt(response.body());    
    }

    public static int add(Integer... params) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String body = "<methodCall><params>";
        for (Integer value :  params) {
            body = body + "<param><value><i4>" + value + "</i4></value></param>";
        }
        body += "</params></methodCall>";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://" + host + ":" + port + "/RPC/ADD")).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Integer.parseInt(response.body());
    }

    public static int subtract(int lhs, int rhs) throws Exception { 
        HttpClient client = HttpClient.newHttpClient();
        String body = "<methodCall><params><param><value><i4>" + lhs + "</i4></value></param>" + "<param><value><i4>" + rhs + "</i4></value></param></params></methodCall>";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://" + host + ":" + port + "/RPC/SUB")).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Integer.parseInt(response.body());        
    }

    public static int multiply(int lhs, int rhs) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String body = "<methodCall><params><param><value><i4>" + lhs + "</i4></value></param>" + "<param><value><i4>" + rhs + "</i4></value></param></params></methodCall>";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://" + host + ":" + port + "/RPC/MULT")).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Integer.parseInt(response.body());        
    }

    public static int multiply(Integer... params) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String body = "<methodCall><params>";
        for (Integer value :  params) {
            body = body + "<param><value><i4>" + value + "</i4></value></param>";
        }
        body += "</params></methodCall>";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://" + host + ":" + port + "/RPC/MULT")).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Integer.parseInt(response.body());
    }

    public static int divide(int lhs, int rhs) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String body = "<methodCall><params><param><value><i4>" + lhs + "</i4></value></param>" + "<param><value><i4>" + rhs + "</i4></value></param></params></methodCall>";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://" + host + ":" + port + "/RPC/DIV")).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Integer.parseInt(response.body());        
    }

    public static int modulo(int lhs, int rhs) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String body = "<methodCall><params><param><value><i4>" + lhs + "</i4></value></param>" + "<param><value><i4>" + rhs + "</i4></value></param></params></methodCall>";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://" + host + ":" + port + "/RPC/MOD")).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Integer.parseInt(response.body());        
    }
}