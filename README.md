# Android Copy String Translation Plugin
Copy translated string from the source files into your project's strings.xml. If any string is already present then the old one deleted and new string is copied.

##Usage

Apply the plugin in your build.gradle:

``` 
buildscript {
  maven {
            url "https://plugins.gradle.org/m2/"
        }
  dependencies {
    classpath "gradle.plugin.com.yashodhan:AndroidCopyTranslationPlugin:1.9"
  }
}

apply plugin: 'com.yashodhan.copytranslation'

```

You need to specify the basePath. It is the path to directory which contains resource value directories which contains relative language strings.xml
Directory structure should look like:
```
Translation
 |-- values-es
 |    |-- strings.xml
 |-- values-de
 |    |-- strings.xml
 |-- values-fr
 |    |-- strings.xml
```
Specify basePath in build.gradle
```
translation.basePath = '<PathToResource>'
```

### Currently following languages are supported:
  - values-af
  - values-am
  - values-ar
  - values-be
  - values-bg
  - values-ca
  - values-cs
  - values-da
  - values-de
  - values-el
  - values-en-rGB
  - values-en-rIN
  - values-es
  - values-es-rUS
  - values-et
  - values-et-rEE
  - values-fa
  - values-fi
  - values-fr
  - values-fr-rCA
  - values-hi
  - values-hr
  - values-hu
  - values-hy-rAM
  - values-in
  - values-it
  - values-iw
  - values-ja
  - values-ka-rGE
  - values-km-rKH
  - values-ko
  - values-lo-rLA
  - values-lt
  - values-lv
  - values-mn-rMN
  - values-ms
  - values-ms-rMY
  - values-nb
  - values-nl
  - values-pl
  - values-pt
  - values-pt-rBR
  - values-pt-rPT
  - values-ro
  - values-ru
  - values-sk
  - values-sl
  - values-sr
  - values-sv
  - values-sw
  - values-th
  - values-tl
  - values-tr
  - values-uk
  - values-vi
  - values-zh-rCN
  - values-zh-rHK
  - values-zh-rTW
  - values-zu
  
## To run the task execute the following command:
```
gradle copyTranslation
```
