package edu.allegheny.graph.edgeWeighted

import org.scalactic.Requirements

import scala.{Numeric => Num, Ordering => Ord, specialized => sp}


/** An edge-weighted undirected graph.
  *
  * @tparam V      the type of the value to associate with each node in the
  *                graph.
  * @tparam Weight the type of the weight value associated with each edge in
  *                the graph.
  *
  * @author Hawk Weisman
  *
  * Created by hawk on 4/11/16.
  */
class Undigraph[V, @sp(Int, Long, Float, Double) Weight : Num : Ord]
extends EdgeWeighted[V, Weight]
  with Requirements {

  override type Node = UndirectedEWNode
  override type Edge = (Node, Weight)

  class UndirectedEWNode(value: V)
  extends EWNode(value) { self: Node =>

    import ev$2.mkOrderingOps

    def connectTo(that: Node, weight: Weight): Unit = {
      require(weight > 0.asInstanceOf[Weight])
      if (!this.hasEdgeTo(that)) {
        this addEdge (that, weight)
        that addEdge (this, weight)
      }
    }

    /** @inheritdoc
      *
      * In an edge-weighted graph, the shortest path is the path for which
      * the sum of the weights of the edges traversed is the lowest.
      */
    def shortestPathTo(to: Node): List[Node]
      = ???

  }

}