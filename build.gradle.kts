plugins {
    id("org.jetbrains.intellij") version "0.4.16"
    java
    kotlin("jvm") version "1.3.70"
}

group = "ignormies.qss"
version = "0.1"

repositories {
    mavenCentral()
}

// Not sure if this is the best way to do this. Lets the generated code get used
sourceSets["main"].java.srcDir("src/main/gen")

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
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
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("""Initial release""")
}