package edu.allegheny.graph

import org.scalactic.Requirements

import scala.{ Ordering => Ord
             , Numeric => Num
             , specialized => sp
             }

/**
  * Created by hawk on 4/11/16.
  */
class EdgeWeightedDigraph[@sp(Int, Long, Float, Double) Weight : Num : Ord]
extends EdgeWeighted[Weight]
  with Requirements {

  override type Node = DirectedEWNode
  override type Edge = (Node, Weight)

  class DirectedEWNode
    extends EWNode { self: Node =>

    import ev$2.mkOrderingOps

    def connectTo(that: Node, weight: Weight): Unit = {
      require(weight > 0.asInstanceOf[Weight])
      if (!this.hasEdgeTo(that)) this addEdge (that, weight)
    }

  }

}
