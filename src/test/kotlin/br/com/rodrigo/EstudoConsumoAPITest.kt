@file:Suppress("ClassName")

package com.rocksolidknowledge.stackunderflow

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

class QuestionTest {

    val user = User(1, "Alice")

    @Nested
    inner class `constructor should` {

        @Test
        fun `throw an exception if the title is empty`() {

            Assertions.assertThrows(QuestionException::class.java) {
                Question(1, user, "", "question")
            }
        }


        @Test
        fun `throw an exception if the body is empty`() {

            Assertions.assertThrows(QuestionException::class.java) {
                Question(1, user, "title", "")
            }
        }


        @Test
        fun `not throw an exception if the question is valid`() {

            Assertions.assertDoesNotThrow {
                Question(1, user, "title", "question")
            }
        }

        @ParameterizedTest
        @CsvSource("' ', question",
                "'', question",
                "title, ' '",
                "title, ''")
        fun `throw an exception if title or question is invalid`(title: String, body: String){
            Assertions.assertThrows(QuestionException::class.java) {
                Question(1, user, title, body)
            }
        }
    }

    companion object {

    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `constructor should with method source` {


        @Suppress("unused")
        fun titlesAndQuestions() = listOf(
                Arguments.of("", "question"),
                Arguments.of(" ", "question"),
                Arguments.of("title", ""),
                Arguments.of("title", " ")
        )

        @Suppress("unused")
        fun questions() = listOf(
            Arguments.of(Question(1,User(1,"qq qqqqqq"),"pq não vai","aaaa")),
            Arguments.of(Question(1,User(1,"qq qqqqqq"),"pq não vai","aaaa")),
            Arguments.of(Question(1,User(1,"qq qqqqqq"),"pq não vai","aaa")),
            Arguments.of(Question(1,User(1,"qq qqqqqq"),"pq não vai","aaaaa"))
        )


        @ParameterizedTest
        @MethodSource("titlesAndQuestions")
        fun `throw an exception if title or question is invalid`(title: String, question: String) {
            Assertions.assertThrows(QuestionException::class.java) {
                Question(1, user, title, question)
            }
        }

        @ParameterizedTest
        @MethodSource("questions")
        fun `Teste`(question: Question) {
            Assertions.assertEquals(question,"")
        }

    }

    @Nested
    @KotlinParameterizedTests
    inner class `constructor should with method source and annotation` {


        @Suppress("unused")
        fun titlesAndQuestions() = listOf(
                Arguments.of("", "question"),
                Arguments.of(" ", "question"),
                Arguments.of("title", ""),
                Arguments.of("title", " ")
        )

        @ParameterizedTest
        @MethodSource("titlesAndQuestions")
        fun `throw an exception if title or question is invalid`(title: String, question: String) {
            Assertions.assertThrows(QuestionException::class.java) {
                Question(1, user, title, question)
            }
        }

    }

}


















