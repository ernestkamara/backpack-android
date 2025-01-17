repositories {
  mavenCentral()
  google()
}

plugins {
  `kotlin-dsl`
}

dependencies {
  implementation(gradleApi())
  implementation("com.android.tools.build:gradle:7.2.1")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
  implementation("com.squareup:kotlinpoet:1.11.0")
  implementation("com.google.guava:guava:31.1-jre")
  implementation("com.google.code.gson:gson:2.9.0")
}
