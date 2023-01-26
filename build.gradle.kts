plugins {
    kotlin("multiplatform") version "1.7.10"
    application
}

group = "me.dantink"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    mavenLocal()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.41.1")
                implementation("org.postgresql:postgresql:42.1.4")
                implementation("org.slf4j:slf4j-api:2.0.6")
                implementation("ch.qos.logback:logback-classic:1.4.5")
                implementation("com.zaxxer:HikariCP:5.0.1")
            }
        }
        val jvmTest by getting
    }
}

application {
    mainClass.set("test.CitiesKt")
}