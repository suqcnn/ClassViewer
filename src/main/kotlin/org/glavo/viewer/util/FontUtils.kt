@file:JvmName("FontUtils")

package org.glavo.viewer.util

import kotlinfx.*

val fontFamily: Regex = """-fx-font-family:\s*"([^"]+)"\s*;""".toRegex()

private val fonts: List<String> = Font.getFamilies()

val defaultUiFontName: String =
        fonts.run {
            find { it == "PingFang SC" }
                    ?: find { it == "Microsoft YaHei UI" }
                    ?: find { it == "Ubuntu" }
                    ?: find { it == "Segoe UI"}
                    ?: "Dialog"
        }
