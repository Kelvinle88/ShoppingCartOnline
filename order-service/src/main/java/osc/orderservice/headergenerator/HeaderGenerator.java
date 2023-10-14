package osc.orderservice.headergenerator;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class HeaderGenerator {
    public HttpHeaders getHeadersForSuccessGetMethod() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        return httpHeaders;
    }

    public HttpHeaders getHeadersForError() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/problem+json; charset=UTF-8");
        return httpHeaders;
    }

//    public HttpHeaders getHeadersForSuccessPostMethod(HttpServletRequest request, Long newResourceId) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        try {
//            httpHeaders.setLocation(new URI(request.getRequestURI() + "/" + newResourceId));
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
//        return httpHeaders;
//    }
    public HttpHeaders getHeadersForSuccessPostMethod(Long newResourceId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newResourceId)
                .toUri());

        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        return httpHeaders;
    }

}