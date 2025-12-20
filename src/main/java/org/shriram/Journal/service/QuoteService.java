package org.shriram.Journal.service;

import org.shriram.Journal.api.response.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QuoteService {

    private static final String API_KEY = "ZDsjnPqBiK256+c4r/Cadg==oFXLOnsNrmtI1NxG"; // put your key here
    private static final String API_URL = "https://api.api-ninjas.com/v1/quotes";

    @Autowired
    private RestTemplate restTemplate;

    public QuoteResponse getQuote() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<QuoteResponse[]> response =
                restTemplate.exchange(API_URL, HttpMethod.GET, entity, QuoteResponse[].class);

        return response.getBody()[0]; // first element from array
    }

}
