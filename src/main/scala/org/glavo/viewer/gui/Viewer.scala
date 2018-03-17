package org.glavo.viewer.gui

import scalafx.application.Application
import scalafx.beans.property.ObjectProperty
import scalafx.stage.Stage
import scalafx.scene.text.Font

class Viewer(val settings: Settings) extends Application {

  def this() {
    this(Settings.globalSettings)
    Settings.initGlobalSettings(parameters)
  }

  override def init(): Unit = {
  }

  override def start(stage: Stage): Unit = {
    //primaryStage.show()
  }
}

object Viewer {
  def main(args: Array[String]): Unit =
    Application.launch[Viewer](args)
}