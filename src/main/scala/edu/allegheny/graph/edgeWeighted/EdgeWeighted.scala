package edu.allegheny.graph
package edgeWeighted

import scala.{Numeric => Num, Ordering => Ord, specialized => sp}

/** A graph that is edge-weighted.
  *
  * In an edge-weighted graph, each edge is associated with a weight. These
  * weights can be any type which is an instance of `Numeric`. This class is
  * specialised over `Int`, `Long`, `Float`, and `Double`, but you can use
  * anything that has an associated implementation of `Numeric`.
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
abstract class EdgeWeighted[V, @sp(Int, Long, Float, Double) Weight: Num: Ord]
extends Graph[V] {

  override type Node <: EWNode
  override type Edge = (Node, Weight)

  abstract class EWNode(value: V)
  extends NodeLike(value) { self: Node =>

    def connectTo(node: Node, weight: Weight)

    override def hasEdgeTo(node: Node): Boolean
      = _edges contains { case (n: Node, _) => n == node }
  }


}