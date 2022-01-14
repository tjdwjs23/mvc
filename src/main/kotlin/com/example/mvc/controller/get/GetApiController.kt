package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController                             // REST API Controller 동작
@RequestMapping("/api")                     // http://localhost:8080/api
class GetApiController {

    @GetMapping(path = ["/hello", "/abcd"])                   // GET http://localhost:8080/api/hello, GET http://localhost:8080/api/abcd
    fun hello(): String {
        return "hello kotlin"
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])          //예전방식
    fun reqeustMapping(): String {
        return "reqeust-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}") // GET http://localhost:8080/api//get-mapping/path-variable/Seonghun
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("${name}, ${age}")
        return name+" "+age
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}") // GET http://localhost:8080/api//get-mapping/path-variable/Seonghun/28
    fun pathVariable2(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") age: Int): String {
        val name = "kotlin"
        println("${_name}, ${age}")
        return _name+" "+age
    }

    // http://localhost:8080/api/page?key=value&key=value&key=value
    @GetMapping("/get-mapping/query-param") // ?name=Seonghun&age=28
    fun queryParam(
            @RequestParam(name = "name") name : String,
            @RequestParam(value = "age") age : Int
    ): String {
        println("${name}, ${age}")
        return name+" "+age
    }

    // name, age, address, email
    // -
    // phoneNumber -> phonenumber, phone-number 코틀린은 -변수선언불가 name, value 활용
    @GetMapping("/get-mapping/query-param/object") // ?name=Seonghun&age=28
    fun queryParamObject(userRequest: UserRequest) : UserRequest {
        println(userRequest)
        return userRequest
    }
    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        val phoneNumber = map.get("phone-number")
        return map
    }

}