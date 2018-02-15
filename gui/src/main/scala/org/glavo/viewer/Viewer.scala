package org.glavo.viewer

import javafx.stage.Stage

import org.glavo.jfx4s.application.Application

class Viewer extends Application {
  override def start(primaryStage: Stage): Unit = ???
}

object Viewer {
  def main(args: Array[String]): Unit = {
    Application.launch[Viewer](args: _*)
  }
}