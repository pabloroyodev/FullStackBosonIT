package com.example.ex8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    @Autowired
    ApplicationProperties variables;

    @Autowired
    PropertiesYaml var;


    //POR ApplicationProperties
    @GetMapping("/valores")
    public String valores() {
        return "valor de VAR1 es: " + variables.getVariable1() + ". valor de MY.VAR2 es: " + variables.getVariable2()+ ".";
    }
    @GetMapping("/var3")
    public String valorVAR3() {
        return "valor de la var3 es: " + variables.getVariable3();
    }


    //POR YAML
    @GetMapping("/yvalores")
    public String valoresY() {
        return "valor de VAR1 es: " + var.getVAR1() + ". valor de MY.VAR2 es: " + var.getMy_VAR2() + ".";

    }
    @GetMapping("/yvar3")
    public String valorYVAR3() {
        return "valor de la var3 es: " + var.getVAR3();
    }
}
