package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class UserResponse (
    var result:Result?=null,
    var descrpitons:String?=null,

    @JsonProperty("user")
    var userRequest: MutableList<UserRequest>?=null //MutableList는 코틀린의 변경가능한 List
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Result(
    var resultCode:String?=null,
    var resultMessage:String?=null
)


/*
{
    "result" : {
        "result_code" : "OK",
        "result_message" : "성공",
    },

    "description": "~~~~",

    "user" : [
        {   "name": "Seonghun1",
            "age" : "10",
            "email" : "",
            "phoneNumber" : ""
        },
        {   "name": "Seonghun2",
            "age" : "20",
            "email" : "",
            "phoneNumber" : ""
        },
        {   "name": "Seonghun3",
            "age" : "30",
            "email" : "",
            "phoneNumber" : ""
        }
    ]
}
 */
