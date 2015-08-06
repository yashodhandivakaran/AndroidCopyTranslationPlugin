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
    ]


    String string = "values"
    String string_de = "values-de"
    String string_es = "values-es"
    String string_fr = "values-fr"
    String string_nl = "values-nl"
    String string_pt = "values-pt"
    String string_ru = "values-ru"
    String string_th = "values-th"
    String string_zh_rCN = "values-zh-rCN"
    String string_zh_rTW = "values-zh-rTW"


    @TaskAction
    void performTask(){
        println(sourcePath+" : "+destPath)

        values.each {
            value ->
                def source = sourcePath + "/" + value + "/" + sourceFileName
                def destination = destPath + "/" + value + "/" + destFileName

                copy(source, destination)
                println("Copied ${value}/${sourceFileName}")
        }
        println("Copiead all translated strings.")
    }

    /**
     * Copy then content of source file to destination file
     * @param sorucePath
     * @param destinationPath
     */
    void copy(String sorucePath, String destinationPath) {

        Path xmlSrcFilePath = Paths
                .get(sorucePath)
                .toAbsolutePath()

        Path xmlDestFilePath = Paths
                .get(destinationPath)
                .toAbsolutePath()

        def sourceRes = new XmlParser().parse(xmlSrcFilePath.toFile())
        def destinationRes = new XmlParser().parse(xmlDestFilePath.toFile())

        sourceRes.children().each {
            string -> deleteDublicateString(destinationRes,string)
                copyStringToDestination(destinationRes,string)
        }

        new XmlNodePrinter(new PrintWriter(new FileWriter(destinationPath))).print(destinationRes)
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
