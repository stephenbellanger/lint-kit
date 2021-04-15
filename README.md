
# lint-kit  
  
## Rules  
* NotAuthorizedSbcNaming  
  
## How to integrate  
  
Step 1. Add the maven central repository to your build file<br/>  
Add it in your root build.gradle at the end of repositories:  
```groovy  
allprojects {  
   repositories {  
      mavenCentral()  
   }  
}  
```  
Step 2. Add the dependency  
```groovy  
dependencies {  
    implementation 'io.github.stephenbellanger.kit.lint:lint-kit:1.0'  
}  
```  
  
## Lint configuration  
  
```groovy
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
  
### Maven configuration  
  
Add `maven-publish` and `signing` plugins in `build.gradle` file :

```groovy
plugins {  
  id 'maven-publish'  
  id 'signing'  
}
```

See in `lint-kit` module `maven-push.gradle` file and add this file in `build.gradle file` :

```groovy
apply from: 'maven-push.gradle'
```

In `maven-push.gradle` :

```groovy
afterEvaluate {  
	publishing {  
		publications {  
			release(MavenPublication) {  
				from components.release    
				groupId = GROUP 
                artifactId = ARTEFACT_ID  
                version = VERSION_CODE  
  
                pom {  
	                name = POM_NAME  
                    description = POM_DESCRIPTION  
                    url = POM_URL  
  
                    scm {  
	                    connection = POM_SCM_CONNECTION  
                        developerConnection = POM_SCM_DEV_CONNECTION  
                        url = POM_SCM_URL  
                    }  

					licenses {  
						license {  
							name = POM_LICENCE_NAME  
                            url = POM_LICENCE_URL  
                            distribution = POM_LICENCE_DIST  
                        }  
	                }  

					developers {  
						developer {  
							id = POM_DEVELOPER_ID  
                            name = POM_DEVELOPER_NAME  
                            email = POM_DEVELOPER_EMAIL  
                        }  
					 } 
				 }  
				 repositories {  
					 maven {  
						 credentials {  
							 username = getRepositoryUsername()
							 password = getRepositoryPassword()  
                        }  
                        url = version.endsWith('SNAPSHOT') ? getSnapshotRepositoryUrl() : getReleaseRepositoryUrl()  
                     }  
                 }
           } 
    }  
    signing {  
	    sign publishing.publications.release  
	}  
  
	task androidSourcesJar(type: Jar) {  
		classifier = 'sources'  
	    from android.sourceSets.main.java.sourceFiles  
    }  
  
	artifacts {  
		archives androidSourcesJar  
	}  
 }
}
```  

All variables are in `gradle.properties` file.

```gradle
VERSION_NAME=0.0.1  
VERSION_CODE=1.0  
GROUP=[com.example.library]
ARTEFACT_ID=[library-name]
POM_DESCRIPTION=[A great library]  
POM_URL=[https://github.com/user/library-name]
POM_SCM_URL=[https://github.com/user/library-name]  
POM_SCM_CONNECTION=[scm:git@github.user/library-name.git]  
POM_SCM_DEV_CONNECTION=[scm:git@github.user/library-name.git]
POM_LICENCE_NAME=The Apache Software License, Version 2.0  
POM_LICENCE_URL=http://www.apache.org/licenses/LICENSE-2.0.txt  
POM_LICENCE_DIST=repo  
POM_DEVELOPER_ID=[github_username]  
POM_DEVELOPER_NAME=[Dupond developer]
POM_DEVELOPER_EMAIL=[duponddeveloper@gmail.com]  

# Sonatype nexus
NEXUS_USERNAME=[sonatype_username]  
NEXUS_PASSWORD=[sonatype_password]

# Signing with gpg
signing.keyId=[gpg_key_id] #Last 8 symbols of your gpg key
signing.password=[gpg_password]  
signing.secretKeyRingFile=[/Users/username/.gnupg/secring.gpg]
```

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

### GPG signing
* https://docs.gradle.org/current/userguide/signing_plugin.html