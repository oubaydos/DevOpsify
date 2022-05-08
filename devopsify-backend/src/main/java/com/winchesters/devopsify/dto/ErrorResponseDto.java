package com.winchesters.devopsify.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.winchesters.devopsify.utils.ExceptionJsonFormatter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@JsonFormat
public record ErrorResponseDto(
        HttpStatus status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "GMT+1")
        Instant timestamp,
        Boolean messageIsHandled,
        ExceptionJsonFormatter exception
) {
        public ErrorResponseDto(HttpStatus status, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "GMT+1")
                Instant timestamp, Boolean messageIsHandled, ExceptionJsonFormatter exception) {
                this.status = status;
                this.timestamp = timestamp;
                this.messageIsHandled = messageIsHandled;
                this.exception = exception;
        }
        public ErrorResponseDto(HttpStatus status, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "GMT+1")
                Instant timestamp, Boolean messageIsHandled, Exception exception) {
                this(status, timestamp, messageIsHandled, ExceptionJsonFormatter.convertExceptionToJson(exception));
        }

        public ErrorResponseDto(HttpStatus status, Instant timestamp, ExceptionJsonFormatter exception) {
                this(status, timestamp, false, exception);
        }
        public ErrorResponseDto(HttpStatus status, Instant timestamp, Exception exception) {
                this(status, timestamp, false, ExceptionJsonFormatter.convertExceptionToJson(exception));
        }

}
