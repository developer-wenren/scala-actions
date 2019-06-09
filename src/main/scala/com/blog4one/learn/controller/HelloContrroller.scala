package com.blog4one.learn.controller

import org.springframework.web.bind.annotation.{GetMapping, RestController}

/**
  * Hello 控制器
  */
@RestController
class HelloContrroller {
  @GetMapping(value = Array("/hello"))
  def hello(): String = {
    "hello,scala"
  }
}