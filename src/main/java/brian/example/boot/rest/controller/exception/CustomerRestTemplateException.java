package brian.example.boot.rest.controller.exception;

import org.springframework.web.client.RestClientException;

public class CustomerRestTemplateException extends RestClientException {
    public CustomerRestTemplateException(String message) {
        super(message);
    }

    public CustomerRestTemplateException(String message, Throwable cause) {
        super(message, cause);
    }
}
