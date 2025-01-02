import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	kotlin("plugin.jpa") version "1.9.23"
//	id("com.bmuschko.docker-remote-api") version "9.4.0"
	id("com.google.cloud.tools.jib") version "2.3.0"
}

//apply(plugin = "com.bmuschko.docker-remote-api")


group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation ("jakarta.persistence:jakarta.persistence-api:3.1.0")
	testImplementation ("org.mockito.kotlin:mockito-kotlin:5.3.1")
	implementation("com.h2database:h2")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	testImplementation("org.mockito:mockito-core:3.6.0")
	testImplementation("org.mockito:mockito-junit-jupiter:3.6.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
//	jvmArgs("-XX:+EnableDynamicAgentLoading")
	useJUnitPlatform()
}
