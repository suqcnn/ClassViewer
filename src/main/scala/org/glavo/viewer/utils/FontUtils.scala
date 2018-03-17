package org.glavo.viewer.utils

import scalafx.scene.text.Font

object FontUtils {
  val defaultUiFontName: String = {
    val fonts = Font.families
    None
      .orElse(fonts.find(_ == "PingFang SC"))
      .orElse(fonts.find(_ == "Microsoft YaHei UI"))
      .orElse(fonts.find(_ == "Ubuntu"))
      .orElse(fonts.find(_ == "Segoe UI"))
      .getOrElse("Dialog")
  }
  val defaultTextFontName: String = {
    val fonts = Font.families
    None
      .orElse(fonts.find(_ == "Consolas"))
      .orElse(fonts.find(_ == "Source Code Pro"))
      .orElse(fonts.find(_ == "Fira Code"))
      .orElse(fonts.find(_ == "DejaVu Sans Mono"))
      .getOrElse("Monospaced")
  }

  val defaultUiFontSize: Double = 15

  val defaultTextFontSize: Double = 15
}
