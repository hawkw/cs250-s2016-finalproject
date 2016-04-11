package edu.allegheny.graph

import scala.{ Ordering => Ord
             , Numeric => Num
             , specialized => sp
             }
import org.scalactic.Requirements


/**
  * Created by hawk on 4/11/16.
  */
class EdgeWeightedGraph[@sp(Int, Long, Float, Double) Weight : Num : Ord]
extends EdgeWeighted[Weight]
  with Requirements {

  override type Node = UndirectedEWNode
  override type Edge = (Node, Weight)

  class UndirectedEWNode
  extends EWNode { self: Node =>

    import ev$2.mkOrderingOps

    def connectTo(that: Node, weight: Weight): Unit = {
      require(weight > 0.asInstanceOf[Weight])
      if (!this.hasEdgeTo(that)) {
        this addEdge (that, weight)
        that addEdge (this, weight)
      }
    }

  }

}