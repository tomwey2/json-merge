import org.junit.jupiter.api.Test

class JsonMergeTest {

    @Test
    fun `merge with empty JSON`() {
        kotlin.test.assertEquals(
            """{"foo":"bar"}""",
            jsonMerge(
                """{"foo":"bar"}""",
                """{}"""
            )
        )
    }

    @Test
    fun `merge JSON objects`() {
        kotlin.test.assertEquals(
            """{"foo":{"bar":"baz","baz":"bar"}}""",
            jsonMerge(
                """{"foo":{"bar":"baz"}}""",
                """{"foo":{"baz":"bar"}}"""
            )
        )
    }

    @Test
    fun `merge different types`() {
        kotlin.test.assertEquals(
            """{"foo":"bar"}""",
            jsonMerge(
                """{"foo":{"bar":"baz"}}""",
                """{"foo":"bar"}"""
            )
        )
    }
}
