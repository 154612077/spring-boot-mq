package com.rabbit.demo.rabbit;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    Long id;
    String name;
    Date date;

}
