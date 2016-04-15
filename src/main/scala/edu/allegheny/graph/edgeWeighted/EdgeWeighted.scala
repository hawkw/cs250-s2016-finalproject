package edu.allegheny.graph
package edgeWeighted

import scala.language.implicitConversions
import Ordering.Implicits._

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
abstract class EdgeWeighted[V, Weight: Numeric: Ordering]
extends Graph[V] {

  override type Node <: EWNode
  override type Edge = (Node, Weight)

//  @inline protected[this] implicit def edge2node(e: Edge): Node
//    = e._1

  abstract class EWNode(value: V)
  extends NodeLike(value) { self: Node =>

    @inline override final def <~ (edge: Edge): Unit = {
      val (that, weight) = edge
      that ~> (this, weight)
    }

   /** Connect this node to another node with an edge with the given weight.
     *
     * The weight must be greater than zero.
     *
     * @param edge the edge to add
     */
    @throws[IllegalArgumentException]("if the weight is <= 0")
    override protected[this] def addEdge (edge: Edge): Unit
      = { val (_, weight: Weight) = edge
          require(weight > implicitly[Numeric[Weight]].zero)
          _edges += edge
        }

    @inline override def hasEdgeTo(node: Node): Boolean
     = _edges exists { case (n, _) => n == node }

    /** Returns the [[Weight]] of the edge to the given [[Node]], if one exists.
      *
      * @param node the [[Node]] to find the weight of the edge to
      * @return     `Some(Weight)` if this node has an edge to the given node,
      *             `None` otherwise.
      */
    final def weightTo(node: Node): Option[Weight]
      = _edges find { case (n, _) => n == node } map { case (_, w) => w }
  }

}
