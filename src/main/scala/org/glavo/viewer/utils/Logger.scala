package org.glavo.viewer.utils

import java.io.{OutputStreamWriter, PrintWriter}

import org.fusesource.jansi.{Ansi, AnsiConsole}

abstract class Logger(var level: Logger.Level = Logger.Level.DEFAULT,
                      var writer: PrintWriter = new PrintWriter(new OutputStreamWriter(System.out)),
                      var errorWriter: PrintWriter = new PrintWriter(new OutputStreamWriter(System.err))) extends Cloneable {
  def colored: Boolean

  def log(message: String = "", ex: Throwable = null)(level: Logger.Level): Unit

  def trace(message: String): Unit = log(message)(Logger.Level.TRACE)

  def trace(message: String, ex: Throwable): Unit = log(message, ex)(Logger.Level.TRACE)

  def debug(message: String): Unit = log(message)(Logger.Level.DEBUG)

  def debug(message: String, ex: Throwable): Unit = log(message, ex)(Logger.Level.DEBUG)

  def config(name: String, value: Any): Unit = log(s"$name=$value")(Logger.Level.CONFIG)

  def info(message: String): Unit = log(message)(Logger.Level.INFO)

  def info(message: String, ex: Throwable): Unit = log(message, ex)(Logger.Level.INFO)

  def warning(message: String): Unit = log(message)(Logger.Level.WARNING)

  def warning(message: String, ex: Throwable): Unit = log(message, ex)(Logger.Level.WARNING)

  def error(message: String): Unit = log(message)(Logger.Level.ERROR)

  def error(message: String, ex: Throwable): Unit = log(message, ex)(Logger.Level.ERROR)

  override def clone(): Logger = super.clone().asInstanceOf[Logger]
}

object Logger {
  val globalLogger: Logger = apply()

  final case class Level private(name: String, level: Int,
                                 color: Ansi.Color = Ansi.Color.DEFAULT) extends Ordered[Level] {
    override def compare(that: Level): Int = Integer.compare(level, that.level)

    override def toString: String = name
  }

  object Level {
    val OFF = Level("OFF", Int.MaxValue)
    val ERROR = Level("ERROR", 1000, Ansi.Color.RED)
    val WARNING = Level("WARNING", 900, Ansi.Color.MAGENTA)
    val INFO = Level("INFO", 800, Ansi.Color.GREEN)
    val CONFIG = Level("CONFIG", 700, Ansi.Color.YELLOW)
    val DEBUG = Level("CONFIG", 600, Ansi.Color.BLUE)
    val TRACE = Level("TRACE", 500, Ansi.Color.CYAN)
    val ALL = Level("ALL", Int.MinValue)

    val DEFAULT: Level = CONFIG

    def apply(name: String): Level = if (name == null) DEFAULT else name.trim.toUpperCase match {
      case "OFF" => OFF
      case "ERROR" => ERROR
      case "WARNING" => WARNING
      case "INFO" => INFO
      case "CONFIG" => CONFIG
      case "DEBUG" => DEBUG
      case "TRACE" => TRACE
      case "ALL" => ALL
      case _ => DEFAULT
    }
  }

  final class Colored(level: Level = Level.DEFAULT,
                      writer: PrintWriter = new PrintWriter(new OutputStreamWriter(System.out)),

                     )
    extends Logger(level, writer) {
    AnsiConsole.systemInstall()

    override def log(message: String = "", ex: Throwable = null)(level: Level): Unit = {
      if (level >= this.level) {
        val writer = if (level >= Level.ERROR) this.errorWriter else this.writer

        writer.println((Ansi.ansi() fg level.color a "[" a level.name a "] " reset) a message)
        if (ex != null) {
          ex.printStackTrace(writer)
        }
      }
      writer.flush()
    }

    override def finalize(): Unit = AnsiConsole.systemUninstall()

    override def colored: Boolean = true
  }

  final class NotColored(level: Level = Level.DEFAULT,
                         writer: PrintWriter = new PrintWriter(new OutputStreamWriter(System.out)))
    extends Logger(level, writer) {
    override def log(message: String = "", ex: Throwable = null)(level: Level): Unit = {
      if (level >= this.level) {
        val writer = if (level >= Level.ERROR) this.errorWriter else this.writer

        writer.println(s"[${level.name}] $message")
        if (ex != null) {
          ex.printStackTrace(writer)
        }
      }
      writer.flush()
    }

    override def colored: Boolean = false
  }

  def apply(level: Level = Level.DEFAULT,
            writer: PrintWriter = new PrintWriter(new OutputStreamWriter(System.out)),
            errorWriter: PrintWriter = new PrintWriter(new OutputStreamWriter(System.err)),
            colored: Boolean = true): Logger = {
    if (colored) {
      new Colored(level, writer)
    } else {
      new NotColored(level, writer)
    }
  }
}
