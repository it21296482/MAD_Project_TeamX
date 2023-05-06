package com.example.myapplication

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.xml.validation.Validator

@RunWith(JUnit4::class)
class ValidatorTest{


    @Test
    fun whenInputIsValid(){

        val unit = 10
        val result = Validator.ValidateInput(unit)
        assertThat(result).isEqualTo(true)

    }
}