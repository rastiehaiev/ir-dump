package com.rastiehaiev

import java.io.File
import java.nio.file.Files

fun log(message: String) {
    val tmp = File("~/dev/project/personal/ir-dump/sample/logs.tmp").toPath()
    Files.writeString(tmp, message)
}
