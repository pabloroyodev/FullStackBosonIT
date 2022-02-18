package com.example.ex9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    @Value("${url}")
    private String url;

    @Value("${password}")
    private String password;

    @Autowired
    Perfiles activeProfile;

    @Autowired
    Configuracion configuracion;

    @PostConstruct
    void printVariables(){
        System.out.println("valor1: " + configuracion.valor1);
        System.out.println("valor2: " + configuracion.valor2);
    }

    @GetMapping("parametros")
    public Object getParameters(){
        Map<String,String> res = new HashMap<>();
        res.put("url",url);
        res.put("password",password);
        return res;
    }

    @GetMapping("miconfiguracion")
    public Object getConfig(){
        return configuracion;
    }

    @GetMapping("perfil")
    public Object getProfile(){
        Map<String,Object> res = new HashMap<>();
        res.put("perfil", activeProfile.miFuncion());
        return res;
    }

}
