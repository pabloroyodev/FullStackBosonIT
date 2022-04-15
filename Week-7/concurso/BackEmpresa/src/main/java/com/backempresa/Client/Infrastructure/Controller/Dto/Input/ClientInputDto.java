package com.backempresa.Client.Infrastructure.Controller.Dto.Input;

import lombok.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class ClientInputDto implements Serializable {
    @NonNull
    private String name;

    private String surname;

    @NonNull
    private String email;

    @NonNull
    private String password;

}
