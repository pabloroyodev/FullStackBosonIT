package com.example.ex9;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties
@PropertySource("miconfiguracion.properties")
public class Configuracion {
    @Value("${valor1:}")
    String valor1;
    @Value("${valor2:}")
    String valor2;

//    Otra manera
//    String valor1,valor2;
}
