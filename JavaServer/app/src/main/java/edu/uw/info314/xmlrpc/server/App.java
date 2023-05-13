package edu.uw.info314.xmlrpc.server;

import java.util.*;
import java.util.logging.*;
import java.io.StringReader;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;

import static spark.Spark.*;

class Call {
    public String name;
    public List<Object> args = new ArrayList<Object>();
}

public class App {
    public static final Logger LOG = Logger.getLogger(App.class.getCanonicalName());

    public static void main(String[] args) {
        port(8080);
        ipAddress("localhost");

        before((req, res) -> {
            if (!req.uri().startsWith("/RPC")) {
                LOG.info("this is the starting of the life");
                res.status(404);
                halt();
            }
            if (!req.requestMethod().equals("POST")) {
                LOG.info("Hi my name is weifan wu!");
                res.status(405);
                halt();
            }
        });
        Calc calculator = new Calc();
        LOG.info("Starting up on port 8080");
        // This is the mapping for POST requests to "/RPC";
        // this is where you will want to handle incoming XML-RPC requests
        post("/RPC", (request, response) -> { response.status(500); return "TBD"; });
        post("/RPC/ADD", (request, response) -> { 
            String body = request.body();
            response.status(400); 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(body));
            Document document = builder.parse(inputSource);
            NodeList numbers = document.getElementsByTagName("i4");
            int[] params = new int[numbers.getLength()];
            for (int i = 0; i < numbers.getLength(); i++) {
                int value = Integer.parseInt(numbers.item(i).getTextContent());
                params[i] = value;
            }
            return calculator.add(params); 
        }); 
        post("/RPC/SUB", (request, response) -> { 
            String body = request.body();
            response.status(400); 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(body));
            Document document = builder.parse(inputSource);
            NodeList numbers = document.getElementsByTagName("i4");
            int[] params = new int[numbers.getLength()];
            for (int i = 0; i < numbers.getLength(); i++) {
                int value = Integer.parseInt(numbers.item(i).getTextContent());
                params[i] = value;
            }
            return calculator.subtract(params[0], params[1]); 
        });

        post("/RPC/MULT", (request, response) -> { 
            String body = request.body();
            response.status(400); 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(body));
            Document document = builder.parse(inputSource);
            NodeList numbers = document.getElementsByTagName("i4");
            int[] params = new int[numbers.getLength()];
            for (int i = 0; i < numbers.getLength(); i++) {
                int value = Integer.parseInt(numbers.item(i).getTextContent());
                params[i] = value;
            }
            return calculator.multiply(params); 
        });

        post("/RPC/DIV", (request, response) -> { 
            String body = request.body();
            response.status(400); 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(body));
            Document document = builder.parse(inputSource);
            NodeList numbers = document.getElementsByTagName("i4");
            int[] params = new int[numbers.getLength()];
            for (int i = 0; i < numbers.getLength(); i++) {
                int value = Integer.parseInt(numbers.item(i).getTextContent());
                params[i] = value;
            }
            if (params[1] == 0) {
                String error = "<soap:Body><soap:Fault><faultcode>1</faultcode><faultstring>Division by zero</faultstring></soap:Fault></soap:Body>";
                return error;
            } else {
                return calculator.divide(params[0], params[1]); 
            }
        });

        post("/RPC/MOD", (request, response) -> { 
            String body = request.body();
            response.status(400); 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(body));
            Document document = builder.parse(inputSource);
            NodeList numbers = document.getElementsByTagName("i4");
            int[] params = new int[numbers.getLength()];
            for (int i = 0; i < numbers.getLength(); i++) {
                int value = Integer.parseInt(numbers.item(i).getTextContent());
                params[i] = value;
            }
            return calculator.modulo(params[0], params[1]); 
        }); 
        // Each of the verbs has a similar format: get() for GET,
        // put() for PUT, delete() for DELETE. There's also an exception()
        // for dealing with exceptions thrown from handlers.
        // All of this is documented on the SparkJava website (https://sparkjava.com/).
    }
}