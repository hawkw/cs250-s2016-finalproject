package edu.allegheny.graph
package edgeWeighted

import scala.{specialized => sp}

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
trait EdgeWeighted[V, @sp(Int, Long, Float, Double) Weight]
extends Graph[V] {

  override type Node <: EWNode
  override type Edge = (Node, Weight)

  abstract class EWNode(value: V)
  extends NodeLike(value) { self: Node =>

   /** Connect this node to another node with an edge with the given weight.
     *
     * The weight must be greater than zero.
     *
     * @param  node   the node to connect this node to
     * @param  weight the weight of the created edge
     */
    @throws[IllegalArgumentException]("if the weight is <= 0")
    def connectTo(node: Node, weight: Weight): Unit

    override def hasEdgeTo(node: Node): Boolean
     = _edges exists { case (n, _) => n == node }

    /** Returns the [[Weight]] of the edge to the given [[Node]], if one exists.
      *
      * @param node the [[Node]] to find the weight of the edge to
      * @return     `Some(Weight)` if this node has an edge to the given node,
      *             `None` otherwise.
      */
    def weightTo(node: Node): Option[Weight]
     = _edges find { case (n, _) => n == node } map { case (_, w) => w }
  }

}
