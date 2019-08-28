package com.sinothk.redheart.view.controller;

import com.sinothk.redheart.filer.Tet;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    public String s(){
        Tet.test();


        return "";
    }
}
