package com.yashodhan.copytranslation

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by Yashodhan Divakaran on 01/08/15.
 */
class CopyTranslationPlugin implements Plugin<Project> {

    /**
     *
     * @param project
     */
    @Override
    void apply(Project project) {

        //Add extension to project so that we can get some information from build
        project.extensions.create(CopyTranslationPluginExtension.EXTENSION_NAME,CopyTranslationPluginExtension)

        //This adds the task to project to which the plugin is applied
        project.task(CopyTranslationTask.TASK_NAME,type:CopyTranslationTask){
            project.afterEvaluate {
                def extension = project.extensions.findByName(CopyTranslationPluginExtension.EXTENSION_NAME) as CopyTranslationPluginExtension

                destPath = project.projectDir.absoluteFile.toString() + "/res"
                destFileName = "strings.xml"

                println("basepath " + extension.basePath)

                sourcePath = extension.basePath
                sourceFileName = extension.sourceFileName
            }
        }

    }
}
