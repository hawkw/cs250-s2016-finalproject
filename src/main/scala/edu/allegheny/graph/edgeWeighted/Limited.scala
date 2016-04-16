package edu.allegheny.graph.edgeWeighted

/**
  * Created by hawk on 4/16/16.
  */
object Limited {
  abstract class Limited[T] {
    val min: T
    val max: T
  }

  object Implicits {
    implicit object IntLimits extends Limited[Int] {
      val min = Int.MinValue
      val max = Int.MaxValue
    }

    implicit object DoubleLimits extends Limited[Double] {
      val min = Double.MinValue
      val max = Double.MaxValue
    }

    implicit object FloatLimits extends Limited[Float] {
      val min = Float.MinValue
      val max = Float.MaxValue
    }
  }


}
