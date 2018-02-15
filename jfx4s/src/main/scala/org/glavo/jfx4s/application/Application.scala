package org.glavo.jfx4s.application

import scala.reflect.ClassTag

abstract class Application extends javafx.application.Application {

}

object Application {
  def launch[A <: Application : ClassTag](args: String*): Unit =
    javafx.application.Application.launch(implicitly[ClassTag[A]].runtimeClass.asInstanceOf[Class[_ <: Application]], args: _*)

  def launch[A <: Application : ClassTag](args: Array[String]): Unit =
    javafx.application.Application.launch(implicitly[ClassTag[A]].runtimeClass.asInstanceOf[Class[_ <: Application]], args: _*)
}