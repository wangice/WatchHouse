package com.ice.brother.house.app.web.controller;

import java.io.IOException;
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
  public String hello(@PathVariable String name) throws IOException {
    return "hello" + name;
  }

}
