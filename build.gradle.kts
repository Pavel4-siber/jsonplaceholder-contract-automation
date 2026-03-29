plugins {
    java
    scala
    id("io.qameta.allure") version "2.11.2"
    id("org.jsonschema2pojo") version "1.2.1"
    id("io.gatling.gradle") version "3.10.5"
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
val gatlingVersion = "3.10.5"

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("io.rest-assured:json-schema-validator:5.4.0")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    testImplementation("io.qameta.allure:allure-junit5:2.25.0")
    testImplementation("io.qameta.allure:allure-rest-assured:2.25.0")
    testImplementation("org.assertj:assertj-core:3.25.3")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.17.0")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    gatlingImplementation("io.gatling:gatling-core:$gatlingVersion")
    gatlingImplementation("io.gatling:gatling-http:$gatlingVersion")
}

jsonSchema2Pojo {
    setSource(files("src/test/resources/schemas"))
    targetPackage = "model.generated"
    isGenerateBuilders = true
    isIncludeConstructors = true
    isUsePrimitives = false
    includeAdditionalProperties = false //строгий контракт DTO
}

tasks.test {
    useJUnitPlatform()
    systemProperty("allure.results.directory", "build/allure-results")
}
tasks.named("compileTestJava") {
    dependsOn("generateJsonSchema2Pojo")
}
sourceSets {
    test {
        java {
            srcDir("build/generated-sources/js2p")
        }
    }
}
tasks.register("runTestsWithAllure") {
    dependsOn("clean", "test", "allureServe")
}