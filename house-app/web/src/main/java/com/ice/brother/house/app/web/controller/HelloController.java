package com.ice.brother.house.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:ice
 * @Date: 2018/6/16 11:25
 */
@RestController
public class HelloController {

  @GetMapping("hello/{name}")
  public String hello(@PathVariable String name) {
    return "hello" + name;
  }

}
