package edu.allegheny.graph.edgeWeighted

import org.scalactic.Requirements

import scala.{Numeric => Num, Ordering => Ord, specialized => sp}

import Ord.Implicits._

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
  extends EWNode(value) { self: Node =>

    @throws[IllegalArgumentException]("if the weight is <= 0")
    final def connectTo(that: Node, weight: Weight): Unit = {
      require(weight > implicitly[Numeric[Weight]].zero)
      if (!this.hasEdgeTo(that)) this addEdge (that, weight)
    }

    /** Operator for creating an edge from another node to this node.
      *
      * Note that this node is only necessary on directed graphs.
      *
      * @param  that   the node to form an edge to this node.
      * @param  weight the weight of the new edge
      */
    @throws[IllegalArgumentException]("if the weight is <= 0")
    @inline final def <~ (that: Node, weight: Weight): Unit
      = that ~> (this, weight)

  /** Operator for creating a bi-directional edge between this node and another.
    *
    * Note that this node is only necessary on directed graphs.
    *
    * @param  that   the node to form a bi-directional edge with
    * @param  weight the weight of the new edge
    */
    @throws[IllegalArgumentException]("if the weight is <= 0")
    @inline final def <~> (that: Node, weight: Weight): Unit = {
      this ~> (that, weight)
      this <~ (that, weight)
    }

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
