package com.example.mvc.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated              // bean이 아니기때문에 선언 -  하위 validation작동 (not null, min 등등..)
class DeleteApiController {

    // 1. path variable
    // 2. request param

    @DeleteMapping(path = ["/delete-mapping"])
    fun deleteMapping(
        @RequestParam(value = "name") _name : String,

        @NotNull(message = "age 값이 누락되었습다.")
        @Min(value = 20, message = "age는 20보다 커야 합니다.") //consol창 message
        @RequestParam(name = "age") _age : Int
    ) : String {
        println(_name)
        println(_age)

        return _name+" "+_age
    }

    @DeleteMapping(path = ["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(@PathVariable(value = "name")
                          @Size(min = 2, max = 5, message = "name의 길이는 2~5")
                          @NotNull
                          _name: String,    //aa ~ aaaaa

                          @NotNull(message = "age 값이 누락되었습다.")
                          @Min(value = 20, message = "age는 20보다 커야 합니다.") //consol창 message
                          @PathVariable(name = "age") _age: Int): String {
        println(_name)
        println(_age)

        return _name+" "+_age
    }
}