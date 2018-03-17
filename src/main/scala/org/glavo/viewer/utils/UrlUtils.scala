package org.glavo.viewer.utils

import java.net.{URL, URLDecoder}
import java.nio.file.Path

object UrlUtils {
  //noinspection ScalaDeprecation
  def path2url(path: Path): URL = new URL(URLDecoder.decode(path.toUri.toString))
}