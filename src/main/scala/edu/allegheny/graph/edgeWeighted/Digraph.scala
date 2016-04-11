package edu.allegheny.graph.edgeWeighted

import org.scalactic.Requirements

import scala.{Numeric => Num, Ordering => Ord, specialized => sp}

/** An edge-weighted directed graph.
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
class Digraph[V, @sp(Int, Long, Float, Double) Weight : Num : Ord]
extends EdgeWeighted[V, Weight]
  with Requirements {

  override type Node = DirectedEWNode
  override type Edge = (Node, Weight)

  override def node(item: V): Node = {
    val n = new Node(item)
    _nodes = _nodes :+ n
    n
  }

  class DirectedEWNode(value: V)
  extends EWNode(value) { self: Node =>

    import Ord.Implicits._

    def connectTo(that: Node, weight: Weight): Unit = {
      require(weight > 0.asInstanceOf[Weight])
      if (!this.hasEdgeTo(that)) this addEdge (that, weight)
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
