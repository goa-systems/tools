plugins {
	java
	id("org.springframework.boot") version "3.5.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "goa.systems"
version = "0.0.1"

java {
    targetCompatibility = JavaVersion.VERSION_21
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

val mockitoAgent = configurations.create("mockitoAgent")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(libs.mockito)
    mockitoAgent(libs.mockito) { isTransitive = false }
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs.add("-Xshare:off")
    jvmArgs.add("-javaagent:${mockitoAgent.asPath}")
}

tasks.named<Jar>("bootJar") {
    archiveClassifier.set("full")
    exclude("application.properties")
    exclude("logback.xml")
    exclude(".gitignore")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.named<Jar>("jar") {
    archiveClassifier.set("")
    exclude("application.properties")
    exclude("logback.xml")
    exclude(".gitignore")
}

tasks.register<Copy>("distribute") {
    group = "build"
    description = "Creates distribution."
    dependsOn(tasks.build)
    from(configurations.runtimeClasspath)
    from(tasks.jar)
    into(layout.buildDirectory.dir("dist/libs"))
}