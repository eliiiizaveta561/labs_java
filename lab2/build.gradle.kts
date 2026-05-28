import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.provider.Property
import java.util.Properties
import java.time.LocalDateTime


plugins {
    id("java")
    application
    id("com.gradleup.shadow") version "8.3.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application{
    mainClass = "org.example.Main"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")


    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("org.slf4j:slf4j-api:2.0.9")
}


tasks.shadowJar{
    manifest{
        attributes(Pair("Main-Class", "org.example.Main"))
    }
}


abstract class PrintInfoTask : DefaultTask() {
    @TaskAction
    fun print() {
        println("======================================")
        println("This is my first user task!")
        println("Project: ${project.name}")
        println("Version Gradle: ${project.gradle.gradleVersion}")
        println("======================================")
        }
    }

tasks.register<PrintInfoTask>("printInfo") {
    group = "Custom"
    description = "Displays informstion about the project"
    }


// задача для генерации паспорта сборки
abstract class GenerateBuildPassportTask : DefaultTask() {

    @get:Input
    abstract val welcomeMessage: Property<String>

    @TaskAction
    fun generate() {
        // собираем информацию
        val properties = Properties()

        // имя пользователя
        val userName = System.getenv("USERNAME") ?: System.getenv("USER") ?: "unknown"
        properties.setProperty("user.name", userName)

        // ОС
        properties.setProperty("os.name", System.getProperty("os.name"))

        // версия Java
        properties.setProperty("java.version", System.getProperty("java.version"))

        // дата и время
        properties.setProperty("build.time", LocalDateTime.now().toString())

        // приветствие
        properties.setProperty("welcome.message", welcomeMessage.get())

        // создаём папку resources, если её нет
        val resourcesDir = project.projectDir.resolve("src/main/resources")
        resourcesDir.mkdirs()

        // сохраняем файл
        val file = resourcesDir.resolve("build-passport.properties")

        //открывает файл для записи и сохраняет свойства в файл
        file.outputStream().use {
            properties.store(it, "Build Passport")
        }

        println("File build-passport.properties created: ${file.absolutePath}")
        println("Contains:")
        properties.forEach { key, value ->
            println("   ${key}=${value}")
        }
    }
}

// Регистрируем задачу
tasks.register<GenerateBuildPassportTask>("generateBuildPassport") {
    group = "Custom"
    description = "Generates a file build-passport.properties with assembly information"
    welcomeMessage.set("Hello from Gradle!")
}

// Интегрируем в процесс сборки
tasks.named("processResources") {
    dependsOn(tasks.named("generateBuildPassport"))
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}