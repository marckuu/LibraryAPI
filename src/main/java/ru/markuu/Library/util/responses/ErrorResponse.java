package ru.markuu.Library.util.responses;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private long timestamp;

    public ErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
