package com.softwaremill.bootzooka.utils

import scala.concurrent.{ExecutionContext, Future}

object FutureHelpers {
  implicit class PimpedFuture[T](future: Future[T])(implicit val ec: ExecutionContext) {
    def mapToUnit = future.map(_ => ())
  }
}
