package routes.files;

import HTTPServer.Response;
import HTTPServer.Route;
import HTTPServer.Headers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HealthCheckHTMLRoute implements Route {
    private static String body = null;
    private ArrayList<String> headers = new ArrayList<>();
    private static final List<String> allow = Arrays.asList("GET", "HEAD");
    private boolean routeIsFound;

    @Override
    public String getBody() {
        return body;
    }

    public void setBody(String newBody) {
        body = newBody;
    }

    public byte[] getFile() {
        String filePath = new File("").getAbsolutePath();
        filePath += "/src/main/resources/health-check.html";

        byte[] fileBytes = new byte[0];
        try {
            fileBytes = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileBytes;
    }

    @Override
    public ArrayList<String> getHeaders() {
        headers.add(Headers.CONTENT_TYPE_HTML.getHeader());
        headers.add(formatAllow());
        return headers;
    }

    @Override
    public String formatAllow() {
        String allowHeader = Headers.ALLOW.getHeader();
        allowHeader += String.join(", ", allow);
        return allowHeader;
    }

    @Override
    public List<String> getAllow() {
        return allow;
    }

    @Override
    public void performRequest(String method, Response response, String body, String route) {
        response.setFile(this.getFile());
        routeIsFound = true;
    }

    public boolean getRouteIsFound() {
        return this.routeIsFound;
    }
}
