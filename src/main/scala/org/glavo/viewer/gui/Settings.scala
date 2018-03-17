package org.glavo.viewer.gui

import org.glavo.viewer.utils.{FontUtils, Logger}

import scalafx.application.Application
import scalafx.beans.binding.{Bindings, ObjectBinding}
import scalafx.beans.property._
import scalafx.scene.text.Font

class Settings extends Cloneable {
  val debugProperty: BooleanProperty = BooleanProperty(false)
  val coloredProperty: BooleanProperty = BooleanProperty(true)
  val uiFontNameProperty: StringProperty = StringProperty(FontUtils.defaultUiFontName)
  val textFontNameProperty: StringProperty = StringProperty(FontUtils.defaultTextFontName)
  val uiFontSizeProperty: DoubleProperty = DoubleProperty(15)
  val textFontSizeProperty: DoubleProperty = DoubleProperty(15)

  val uiFontProperty: ObjectBinding[Font] =
    Bindings.createObjectBinding(() => new Font(uiFontName, uiFontSize), uiFontNameProperty, uiFontSizeProperty)
  val textFontProperty: ObjectBinding[Font] =
    Bindings.createObjectBinding(() => new Font(textFontName, textFontSize), textFontNameProperty, textFontSizeProperty)

  var logger: Logger = Logger.globalLogger

  coloredProperty.onChange {
    logger = Logger(logger.level, logger.writer, logger.errorWriter, logger.colored)
  }

  def debug: Boolean = debugProperty.value

  def debug_=(value: Boolean): Unit = debugProperty.value = value

  def colored: Boolean = coloredProperty.value

  def colored_=(value: Boolean): Unit = coloredProperty.value = value

  def uiFontName: String = uiFontNameProperty.value

  def uiFontName_=(value: String): Unit = uiFontNameProperty.value = value

  def textFontName: String = textFontNameProperty.value

  def textFontName_=(value: String): Unit = textFontNameProperty.value = value

  def uiFontSize: Double = uiFontSizeProperty.value

  def uiFontSize_=(value: Double): Unit = uiFontSizeProperty.value = value

  def textFontSize: Double = textFontSizeProperty.value

  def textFontSize_=(value: Double): Unit = textFontSizeProperty.value = value

  def uiFont: Font = uiFontProperty.value

  def textFont: Font = textFontProperty.value

  override def clone(): Settings = {
    val opt = new Settings
    opt.debug = debug
    opt.colored = colored
    opt.uiFontName = uiFontName
    opt.uiFontSize = uiFontSize
    opt.textFontName = textFontName
    opt.textFontSize = textFontSize
    opt.logger = logger
    opt
  }

  override def toString: String = s"Options(debug=$debug,colored=$colored,uiFont=$uiFont,textFont=$textFont)"
}

object Settings {
  val globalSettings: Settings = new Settings

  def initGlobalSettings(p: Application.Parameters): Unit = {

  }
}