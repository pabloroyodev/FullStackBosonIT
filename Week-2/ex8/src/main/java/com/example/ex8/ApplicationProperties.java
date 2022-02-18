package com.example.ex8;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
class ApplicationProperties {
    @Value("${VAR1:}")
    private String variable1;

    @Value("${My.VAR2:}")
    private String variable2;

    @Value("${VAR3:}")
    private String variable3;

    public String getVariable3() {
        return variable3.isEmpty() ? "var3 no tiene valor" : variable3;
    }
}