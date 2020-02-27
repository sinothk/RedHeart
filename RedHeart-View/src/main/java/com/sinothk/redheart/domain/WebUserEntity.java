package com.sinothk.redheart.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WebUserEntity {

    private String name;
    private int age;

    private String email;
    private String password;

}
