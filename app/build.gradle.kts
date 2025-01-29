plugins {
    id("java")
    application
    checkstyle
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports { xml.required.set(true) }
}