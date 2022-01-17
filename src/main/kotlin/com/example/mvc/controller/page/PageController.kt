package com.example.mvc.controller.page

import com.example.mvc.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller      //http:localhost:8080/main
class PageController {

    @GetMapping("/main")
    fun main() : String {       //Controller - > text "main.html" X
        println("init main")
        return "main.html"
    }

    @ResponseBody               // text "main.html"
    @GetMapping("/test")
    fun response(): UserRequest {
        return UserRequest().apply{
            this.name = "SeongHun"
        }
        //return "main.html"
    }
}