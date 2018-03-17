package scalafx.application

import javafx.{application => jfxa, stage => jfxs}

import scala.reflect.ClassTag
import scalafx.stage.Stage

abstract class Application extends jfxa.Application {
  self =>
  def start(primaryStage: Stage): Unit

  override def start(primaryStage: jfxs.Stage): Unit = start(new Stage(primaryStage))

  def hostService: HostServices = new HostServices(this.getHostServices)

  def parameters: Application.Parameters = ApplicationIncludes.jfxParameters2sfx(this.getParameters)
}

object Application {
  type Parameters = JFXApp.Parameters
  val Parameters: JFXApp.Parameters.type = JFXApp.Parameters

  def launch[T <: jfxa.Application : ClassTag](args: String*): Unit =
    jfxa.Application.launch(implicitly[ClassTag[T]].runtimeClass.asInstanceOf[Class[T]], args: _*)

  def launch[T <: jfxa.Application : ClassTag](args: Array[String]): Unit =
    jfxa.Application.launch(implicitly[ClassTag[T]].runtimeClass.asInstanceOf[Class[T]], args: _*)
}

