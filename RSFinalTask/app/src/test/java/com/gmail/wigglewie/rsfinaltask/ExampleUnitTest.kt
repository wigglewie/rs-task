package com.gmail.wigglewie.rsfinaltask.test

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
//import io.reactivex.rxjava3.core.Single
import org.mockito.ArgumentMatchers.anyString

//import org.junit.Test

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

import org.junit.Assert.*

class HelloSpec: Spek({
    describe("A Hello") {
        it("should say hello") {
            assertEquals(expected = "Hello", actual = "Hello")
        }

        it("should not say hello") {
            assertEquals(expected = "not Hello", actual = "not Hello")
        }
    }
})
