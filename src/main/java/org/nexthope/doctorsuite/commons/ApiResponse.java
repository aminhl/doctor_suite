package org.nexthope.doctorsuite.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse <T> {

    private boolean success;

    private String message;

    private LocalDateTime timestamp;

    private T data;

}
