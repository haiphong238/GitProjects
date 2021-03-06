package com.plug2field.oauth2.resourceserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    @PreAuthorize("#oauth2.hasScope('plug2Field')")
    @RequestMapping(method = RequestMethod.GET, value = "/foo")
    @ResponseBody
    public String findById() {
        return "Hello";
    }
}
