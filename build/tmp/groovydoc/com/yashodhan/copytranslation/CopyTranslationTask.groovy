package com.yashodhan.copytranslation

import org.apache.xerces.parsers.XMLParser
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.nio.file.Path
import java.nio.file.Paths

/**
 * Created by Yashodhan Divakaran on 31/07/15.
 */
class CopyTranslationTask extends DefaultTask {

    public static final String TASK_NAME = "copyTranslation"

    String sourcePath = null
    String sourceFileName = null

    String destPath = ""
    String destFileName = "strings.xml"

    String[] values = [
            "values"
            , "values-de"
            , "values-es"
            , "values-fr"
            , "values-nl"
            , "values-pt"
            , "values-ru"
            , "values-th"
            , "values-zh-rCN"
            , "values-zh-rTW"
            , "values-af"
            , "values-am"
            , "values-ar"
            , "values-be"
            , "values-bg"
            , "values-ca"
            , "values-cs"
            , "values-da"
            , "values-el"
            , "values-en-rGB"
            , "values-en-rIN"
            , "values-es-rUS"
            , "values-et-rEE"
            , "values-et"
            , "values-fa"
            , "values-fi"
            , "values-fr-rCA"
            , "values-hi"
            , "values-hr"
            , "values-hu"
            , "values-hy-rAM"
            , "values-in"
            , "values-it"
            , "values-iw"
            , "values-ja"
            , "values-ka-rGE"
            , "values-km-rKH"
            , "values-ko"
            , "values-lo-rLA"
            , "values-lt"
            , "values-lv"
            , "values-mn-rMN"
            , "values-ms-rMY"
            , "values-ms"
            , "values-nb"
            , "values-pl"
            , "values-pt-rBR"
            , "values-pt-rPT"
            , "values-ro"
            , "values-sk"
            , "values-sl"
            , "values-sr"
            , "values-sv"
            , "values-sw"
            , "values-tl"
            , "values-tr"
            , "values-uk"
            , "values-vi"
            , "values-zh-rHK"
            , "values-zu"
    ]


    @TaskAction
    void performTask(){

        if(sourcePath == null || sourcePath.isEmpty()){
            throw new IllegalStateException("baseBath is empty. Please specify basePath in your build script eg. translation.basePath = 'pathToValues' ")
        }

        values.each {
            value ->
                def source = sourcePath + "/" + value + "/" + sourceFileName
                def destination = destPath + "/" + value + "/" + destFileName

                if (copy(source, destination)) {
                    println("Copied ${value}/${sourceFileName}")
                }
        }
        println("CopyTanslation task completed.")
    }

    /**
     * Copy then content of source file to destination file
     * @param sorucePath
     * @param destinationPath
     */
    boolean copy(String sorucePath, String destinationPath) {

        Path xmlSrcFilePath = Paths
                .get(sorucePath)
                .toAbsolutePath()
        if(!xmlSrcFilePath.toFile().exists()){
            println("Resource not present: "+xmlSrcFilePath.toAbsolutePath())
            return false
        }


        Path xmlDestFilePath = Paths
                .get(destinationPath)
                .toAbsolutePath()

        if(!xmlDestFilePath.toFile().exists()){
            println("Project does not contain: "+xmlDestFilePath.toAbsolutePath())
            return false
        }

        def sourceRes = new XmlParser().parse(xmlSrcFilePath.toFile())
        def destinationRes = new XmlParser().parse(xmlDestFilePath.toFile())

        sourceRes.children().each {
            string -> deleteDublicateString(destinationRes,string)
                copyStringToDestination(destinationRes,string)
        }

        new XmlNodePrinter(new PrintWriter(new FileWriter(destinationPath))).print(destinationRes)
        return true
    }

    /**
     * If any duplicate string is present in the xml then the old one is deleted and the new resource is copied
     * @param destNode
     * @param string
     */
    void deleteDublicateString(Node destNode,Node string) {

        def duplicateStringNode = destNode.depthFirst().find {
            dupString -> dupString.@name == string.@name
        }

        if(duplicateStringNode != null){
            destNode.remove(duplicateStringNode)
        }

    }

    /**
     * Copy the given string into given node
     * @param destNode
     * @param string
     */
    void copyStringToDestination(Node destNode,Node string){
        destNode.append(string)
    }
}
