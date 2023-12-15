// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false
    id("app.cash.sqldelight") version "2.0.1" apply false
}

//repositories {
//    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
//}
//repositories {
//    google()
//    mavenCentral()
//}

//sqldelight {
//    databases {
//        create("Database") {
//            packageName.set("com.ucfjoe")
//        }
//    }
//}