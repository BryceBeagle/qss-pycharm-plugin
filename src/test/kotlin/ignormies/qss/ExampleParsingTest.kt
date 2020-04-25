package ignormies.qss

import com.intellij.testFramework.ParsingTestCase
import org.junit.jupiter.api.Test


class ExampleParsingTest : ParsingTestCase("", "qss", QSSParserDefinition()) {

    init {
        setUp()
    }

    @Test
    fun testParsingTestData() {
        name = "ParsingTestData"
        doTest(true)
    }

    override fun getTestDataPath(): String {
        return "src/test/testData"
    }

    override fun skipSpaces(): Boolean {
        return false;
    }

    override fun includeRanges(): Boolean {
        return true;
    }
}
