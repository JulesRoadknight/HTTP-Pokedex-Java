import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseBuilderTest {
    @Test
    public void returnsOKForExistingGETPath() {
        String method = "GET";
        String path = "/simple_get";
        String expectedResponse = "HTTP/1.1 200 OK\r\n";
        Response response = ResponseBuilder.responseHandler(method, path);
        assertEquals(expectedResponse, response.print());
    }

    @Test
    public void returnsOKWithBody() {
        String method = "GET";
        String path = "/simple_get_with_body";
        String expectedResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nHello world";
        Response response = ResponseBuilder.responseHandler(method, path);
        assertEquals(expectedResponse, response.print());
    }

    @Test
    public void returns404ForNoSuchGETPath() {
        String method = "GET";
        String path = "/over_the_rainbow";
        String expectedResponse = "HTTP/1.1 404 Not Found\r\n";
        Response response = ResponseBuilder.responseHandler(method, path);
        assertEquals(expectedResponse, response.print());
    }

    @Test
    public void returns301ForRedirect() {
        String method = "GET";
        String path = "/redirect";
        String expectedResponse = "HTTP/1.1 301 Redirect\r\nLocation: http://127.0.0.1:5000/simple_get\r\n";
        Response response = ResponseBuilder.responseHandler(method, path);
        assertEquals(expectedResponse, response.print());
    }

    @Test
    public void returnsMethodOptions() {
        String method = "OPTIONS";
        String path = "/method_options";
        String expectedResponse = "HTTP/1.1 200 OK\r\nAllow: GET, HEAD, OPTIONS\r\n";
        Response response = ResponseBuilder.responseHandler(method, path);
        assertEquals(expectedResponse, response.print());
    }
}