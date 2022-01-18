package com.example.mvc.model.http

import com.example.mvc.annotation.StringFormatDateTime
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

data class UserRequest (

    @field:NotEmpty
    @field:Size(min = 2, max = 8)
    var name:String?=null,

    @field:PositiveOrZero // 0 < 숫자를 검증 0도 포함 (양수)
    var age:Int?=null,
    
    @field:Email    // email 양식
    var email:String?=null,
    
    @field:NotBlank
    var address:String?=null,
    
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$") // 정규식 검증
    var phoneNumber:String?=null,    // phone_number

    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
    var createdAt:String?=null      //yyyy-MM-dd HH:mm:ss ex) 2022-01-18 21:32:00
)