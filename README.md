# lint-kit

## Rules
* NotAuthorizedSbcNaming

## How to integrate

Step 1. Add the maven central repository to your build file<br/>
Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		mavenCentral()
	}
}
```
Step 2. Add the dependency
```
dependencies {
    implementation 'io.github.stephenbellanger.kit.lint:lint-kit:1.0'
}
```

## Lint configuration

```
android {
    lintOptions {
        checkReleaseBuilds false
        // Or, if you prefer, you can continue to check for errors in release builds,
        // but continue the build even when errors are found:
        abortOnError false
        htmlOutput file("${project.buildDir}/reports/lint/lint-report.html")
        xmlOutput file("${project.buildDir}/reports/lint/lint-report.xml")

        enable 'WrongThreadInterprocedural',
                'UnusedIds',
                'UnsupportedChromeOsHardware',
                'UnpackedNativeCode',
                'UnknownNullness',
                'TypographyQuotes',
                'SyntheticAccessor',
                'StopShip',
                'SelectableText',
                'RequiredSize',
                'Registered',
                'PermissionImpliesUnsupportedChromeOsHardware',
                'NoHardKeywords',
                'NewerVersionAvailable',
                'NegativeMargin',
                'MissingRegistered',
                'MinSdkTooLow',
                'MangledCRLF',
                'LogConditional',
                'LambdaLast',
                'KotlinPropertyAccess',
                'IconExpectedSize',
                'FieldGetter',
                'EasterEgg',
                'ConvertToWebp',
                'BackButton',
                'AppLinksAutoVerifyWarning',
                'AppLinksAutoVerifyError',
                'CanvasSize',
                'IntentReset',
                'InvalidNavigation',
                'InvalidWakeLockTag',
                'MissingDefaultResource',
                'RequiresFeature',
                'Slices',
                'TranslucentOrientation',
                'ValidActionsXml',
                'Untranslatable',
                'DeletedProvider',
                'DeprecatedProvider',
                'ProxyPassword',
                'RiskyLibrary',
                'ExpiredTargetSdkVersion',
                'ExpiringTargetSdkVersion',
                'OutdatedLibrary',
                'SyntheticAccessor',
                'Autofill',
                'ConstantLocale',
                'KotlinPropertyAccess',
                'LambdaLast',
                'NoHardKeywords',
                'UnknownNullness'
        disable 'TrustAllX509TrustManager',
                'ObsoleteLintCustomCheck',
                'SyntheticAccessor', // many use case in Kotlin
                'Overdraw',
                'MissingRegistered', // Lib are not part of sources sets for Lint
                'PluralsCandidate', // Generation is done from POEditor, case to case exception is hard
                'UnusedIds'
        warningsAsErrors true
        lintConfig file("${rootProject.projectDir}/config/lint/lint-config.xml")
    }
}
```

## Tutorial push maven repository

WIP...

## Documentations

### Lint Rules
* https://medium.com/supercharges-mobile-product-guide/formatting-code-analysis-rule-with-android-lint-part-1-2-4b906f717382
* https://www.youtube.com/watch?v=jCmJWOkjbM0
* https://github.com/vanniktech/lint-rules

### Maven deployment
* https://medium.com/@scottyab/how-to-publish-your-open-source-library-to-maven-central-5178d9579c5
* https://zubairehman.medium.com/a-complete-guide-to-create-and-publish-an-android-library-to-maven-central-6eef186a42f5
* https://docs.gradle.org/current/userguide/publishing_maven.html#publishing_maven:tasks
* https://central.sonatype.org/publish/publish-guide/#deployment
* https://s01.oss.sonatype.org/#welcome