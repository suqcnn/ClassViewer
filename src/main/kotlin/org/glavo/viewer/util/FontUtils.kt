@file:JvmName("FontUtils")

package org.glavo.viewer.util

import kotlinfx.*

val fontFamily: Regex = """-fx-font-family:\s*"([^"]+)"\s*;""".toRegex()

private val fonts: List<String> = Font.getFamilies()

val defaultUiFontFamily: String =
        fonts.run {
            find { it == "PingFang SC" }
                    ?: find { it == "Microsoft YaHei UI" }
                    ?: find { it == "Ubuntu" }
                    ?: find { it == "Segoe UI" }
                    ?: "Dialog"
        }

val defaultTextFontFamily: String =
        fonts.run {
            find { it == "Consolas" }
                    ?: find { it == "Source Code Pro" }
                    ?: find { it == "Fira Code" }
                    ?: find {it == "DejaVu Sans Mono"}
                    ?: "Monospaced"
        }
