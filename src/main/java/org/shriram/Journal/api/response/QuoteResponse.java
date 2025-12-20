package org.shriram.Journal.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteResponse {
    private String quote;
    private String author;
    private String category;

    @Override
    public String toString() {
        return "\"" + quote + "\" — " + author;
    }
}

