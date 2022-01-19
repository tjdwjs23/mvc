package com.example.mvc.controller.exception

import org.omg.SendingContext.RunTime
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/exception")
class ExceptionApiController {
    
    @GetMapping("/hello")
    fun hello() {
        val list = mutableListOf<String>()
        val temp = list[0]
    }
    
    @ExceptionHandler(value = [IndexOutOfBoundsException::class])   // 해당 controller만 처리
    fun indexOutOfBoundsException(e : IndexOutOfBoundsException): ResponseEntity<String> {  // 200 OK
        println("controller exception handler")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}