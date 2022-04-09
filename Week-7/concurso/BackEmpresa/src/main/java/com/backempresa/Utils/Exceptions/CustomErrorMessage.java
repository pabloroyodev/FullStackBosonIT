package com.backempresa.Utils.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CustomErrorMessage {
    private Date timestamp;
    int HttpCode;
    String message;
}
