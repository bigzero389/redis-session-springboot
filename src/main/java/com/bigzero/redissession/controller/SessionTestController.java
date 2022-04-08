package com.bigzero.redissession.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class SessionTestController {

  @ResponseBody
  @GetMapping("/hello")
  public Hello hello(HttpServletRequest req, @RequestParam String helloCaller, @RequestParam String greetingText) throws JsonProcessingException {

    Hello hello = new Hello();
    hello.setHelloCaller(helloCaller);
    hello.setGreetingText(greetingText);

    ObjectMapper objectMapper = new ObjectMapper();
    HttpSession httpSession = req.getSession();

    httpSession.setAttribute("sessionString", helloCaller + ":" + greetingText);
    httpSession.setAttribute("sessionObject", objectMapper.writeValueAsString(hello));

    return hello;
  }

  @ResponseBody
  @GetMapping("/getString")
  public String getString(HttpServletRequest req) throws JsonProcessingException {
    HttpSession httpSession = req.getSession();
    String sessionString = String.valueOf(httpSession.getAttribute("sessionString"));

    return sessionString;
  }

  @ResponseBody
  @GetMapping("/getObject")
  public Hello getObject(HttpServletRequest req) throws JsonProcessingException {
    HttpSession httpSession = req.getSession();
    String sessionObjectJson = String.valueOf(httpSession.getAttribute("sessionObject"));

    ObjectMapper objectMapper = new ObjectMapper();
    return sessionObjectJson == null ? new Hello() : objectMapper.readValue(sessionObjectJson, Hello.class);
  }
}


@Setter
@Getter
class Hello {
  private String helloCaller;
  private String greetingText;
}
