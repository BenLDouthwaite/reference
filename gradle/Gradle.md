Gradle.md

## Gradlew

# Show available tasks
./gradlew tasks 

# Run a spring boot project
./gradle bootRun

# Run code coverage with jacoco

apply plugin: 'jacoco'

jacocoTestReport {
    group = "Reporting"
    reports {
        xml.enabled = true
        csv.enabled = false
        html.destination = "${buildDir}/reports/coverage"
    }
    afterEvaluate {
        classDirectories = files(classDirectories.files.collect {
            fileTree(dir: it,
                    exclude: ['com/foo/bar/**'])
        })
    }
}