package have.fun.scala3.basic.implicits.function

import scala.concurrent.ExecutionContext

type FutureContext[R] = ExecutionContext ?=> R
