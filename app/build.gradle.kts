plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {}

var IMPL_VERSION = "0.1.0";
var MAIN_CLASS = "org.farskymodding.fsmllauncher.Main";

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = MAIN_CLASS;
}



tasks.jar {
    manifest {
        attributes("Implementation-Version" to IMPL_VERSION)
        attributes("Main-Class" to MAIN_CLASS)
    }

    archiveBaseName.set("fsmlExec")
}