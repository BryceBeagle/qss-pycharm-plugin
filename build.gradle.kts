import org.jetbrains.grammarkit.tasks.GenerateLexer
import org.jetbrains.grammarkit.tasks.GenerateParser

plugins {
    id("org.jetbrains.intellij") version "0.4.18"
    id("org.jetbrains.grammarkit") version "2020.1"
    java
    kotlin("jvm") version "1.3.70"
}

group = "ignormies.qss"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    pluginName = "QSS"
    version = "LATEST-EAP-SNAPSHOT"
    type = "PY"
    setPlugins(
        "PsiViewer:201.6251.22-EAP-SNAPSHOT.3"
    )
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

sourceSets {
    main {
        java.srcDir("src/main/gen")
    }
}

tasks {

    val generateQSSParser by registering(GenerateParser::class) {
        source = "src/main/kotlin/ignormies/qss/QSS.bnf"
        targetRoot = "src/main/gen/"
        pathToParser = "ignormies/qss/parser/QSSParser.java"
        pathToPsiRoot = "ignormies/qss/psi/"
        purgeOldFiles = true
    }

    val generateQSSLexer by registering(GenerateLexer::class) {
        source = "src/main/kotlin/ignormies/qss/QSS.flex"
        targetDir = "src/main/gen/ignormies/qss/"
        targetClass = "QSSLexer"
        purgeOldFiles = true
    }

    compileJava {
        dependsOn(generateQSSParser)
        dependsOn(generateQSSLexer)
    }

    compileKotlin {
        dependsOn(generateQSSParser)
        dependsOn(generateQSSLexer)
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("""Initial release""")
}