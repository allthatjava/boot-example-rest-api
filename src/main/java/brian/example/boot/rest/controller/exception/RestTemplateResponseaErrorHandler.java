package brian.example.boot.rest.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseaErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {

        if( clientHttpResponse.getStatusCode() != HttpStatus.OK){
            System.out.println("Error Happen");

            return true;
        }

        return false;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        throw new CustomerRestTemplateException("Error came back as "+clientHttpResponse.getStatusCode()+":"+clientHttpResponse.getStatusText());
    }
}
