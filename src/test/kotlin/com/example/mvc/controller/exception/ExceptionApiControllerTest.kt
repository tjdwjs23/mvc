package com.example.mvc.controller.exception

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest() {
        mockMvc.perform(
            get("/api/exception/hello")
        ).andExpect ( // 기대하는 값
            status().isOk
        ).andExpect ( // 기대하는 값
            content().string("hello")
        ).andDo(print())
    }

    @Test
    fun getTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name","Shun")
        queryParams.add("age","28")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect ( // 기대하는 값
            status().isOk
        ).andExpect ( // 기대하는 값
            content().string("Shun 28")
        ).andDo(print())
    }

    @Test
    fun getFailTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name","Shun")
        queryParams.add("age","9")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect ( // 기대하는 값
            status().isBadRequest
        ).andExpect ( // 기대하는 값
            MockMvcResultMatchers.content().contentType("application/json")
        ).andExpect(
            jsonPath("\$.result_code").value("FAIL")
        ).andExpect(
            jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            jsonPath("\$.errors[0].value").value("9")
        ).andDo(print())
    }

    @Test
    fun postTest(){
        val userRequest = UserRequest().apply {
            this.name = "seong"
            this.age = 28
            this.phoneNumber = "010-1111-2222"
            this.address = "seoul"
            this.email = "seoul@naver.com"
            this.createdAt = "2022-01-23 17:08:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect ( // 기대하는 값
            status().isOk
        ).andExpect(
            jsonPath("\$.name").value("seong")
        ).andExpect(
            jsonPath("\$.age").value("28")
        ).andExpect(
            jsonPath("\$.phoneNumber").value("010-1111-2222")
        ).andExpect(
            jsonPath("\$.address").value("seoul")
        ).andExpect(
            jsonPath("\$.email").value("seoul@naver.com")
        ).andExpect(
            jsonPath("\$.createdAt").value("2022-01-23 17:08:00")
        ).andDo(print())
    }

    @Test
    fun postFailTest(){
        val userRequest = UserRequest().apply {
            this.name = "seong"
            this.age = -1
            this.phoneNumber = "010-1111-2222"
            this.address = "seoul"
            this.email = "seoul@naver.com"
            this.createdAt = "2022-01-23 17:08:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect ( // 기대하는 값
            status().isBadRequest
        ).andDo(print())
    }
}