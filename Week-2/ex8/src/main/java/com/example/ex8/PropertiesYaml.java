package com.example.ex8;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ConfigurationProperties(prefix="datos")
public class PropertiesYaml {
    private String VAR1;
    private String My_VAR2;
    private String VAR3;

    public String getVAR1() {
        return VAR1;
    }

    public void setVAR1(String VAR1) {
        this.VAR1 = VAR1;
    }

    public String getMy_VAR2() {
        return My_VAR2;
    }

    public void setMy_VAR2(String my_VAR2) {
        My_VAR2 = my_VAR2;
    }

    public String getVAR3() {
        return VAR3 == null ? "var3 no tiene valor" : VAR3;
    }

    public void setVAR3(String VAR3) {
        this.VAR3 = VAR3;
    }
}
