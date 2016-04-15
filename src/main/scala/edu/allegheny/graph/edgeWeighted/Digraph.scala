package edu.allegheny.graph.edgeWeighted

import edu.allegheny.graph.Directed
import org.scalactic.Requirements

import scala.{specialized => sp}



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
class Digraph[V, @sp(Int, Long, Float, Double) Weight : Numeric : Ordering]
extends EdgeWeighted[V, Weight]
  with Directed[V]
  with Traversable[EdgeWeighted[V, Weight]#Node]
  with Requirements {

  override type Node = DirectedEWNode
  override type Edge = (Node, Weight)

  override def node(item: V): Node = {
    val n = new Node(item)
    _nodes = _nodes :+ n
    n
  }

  class DirectedEWNode(value: V)
  extends EWNode(value)
    with DirectedNode { self: Node =>

    /** @inheritdoc
      *
      * In an edge-weighted graph, the shortest path is the path for which
      * the sum of the weights of the edges traversed is the lowest.
      */
    def shortestPathTo(to: Node): Seq[Node]
      = ???

  }

  /** Apply a function to each [[Node]] in this graph.
    * @param f
    * @tparam U
    */
  @inline final def foreach[U](f: (EdgeWeighted[V, Weight]#Node) => U): Unit
    = _nodes foreach f

}
