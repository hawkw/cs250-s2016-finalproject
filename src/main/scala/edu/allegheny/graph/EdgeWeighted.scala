package edu.allegheny.graph

import scala.{specialized => sp}
/**
  * Created by hawk on 4/11/16.
  */
abstract class EdgeWeighted[@sp(Int, Long, Float, Double) Weight: Numeric]
extends Graph {

  override type Node <: EWNode
  override type Edge = (Node, Weight)

  abstract class EWNode
  extends NodeLike { self: Node =>
    def connectTo(node: Node, weight: Weight)

    override def hasEdgeTo(node: Node): Boolean
      = _edges contains { case (n: Node, _) => n == node }
  }


}