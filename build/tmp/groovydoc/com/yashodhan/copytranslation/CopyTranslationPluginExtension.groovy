package com.yashodhan.copytranslation

import org.gradle.api.Project

/**
 * Created by Yashodhan Divakaran on 01/08/15.
 */
class CopyTranslationPluginExtension {
    public static final String EXTENSION_NAME= "translation"

    def String basePath
    def String sourceFileName = "strings.xml"

//    CopyTranslationPluginExtension(Project project) {
//       this.basePath = project.translation.basePath
//    }
}
