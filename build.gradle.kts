import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.41"
}

group = "at.bayava"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

java {
    // (4)
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.apache.logging.log4j", "log4j-slf4j-impl", "2.12.1")
    implementation("org.apache.logging.log4j", "log4j-core", "2.12.1")
    testImplementation("io.kotlintest", "kotlintest-runner-junit5", "3.3.2")
}
