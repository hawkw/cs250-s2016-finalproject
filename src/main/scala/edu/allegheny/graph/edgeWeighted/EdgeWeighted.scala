package edu.allegheny.graph
package edgeWeighted

import edu.allegheny.graph.edgeWeighted.Limited.Limited

import scala.annotation.tailrec
import scala.collection.mutable
import scala.language.{implicitConversions, postfixOps}
import Numeric.Implicits._
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
  * @author Hawk Weisman
  *
  * Created by hawk on 4/11/16.
  */
abstract class EdgeWeighted[V, Weight: Numeric: Ordering: Limited]
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

    /**
      * The shortest-path tree computed using Djikstra's algorithm.
      *
      * These are `lazy val`s so that they do not have to be re-calculated
      * ever time we want to find the shortest path to a given node.
      */
    lazy private[this] val (dist, prev) = {
      // Cache the max (infinity) value of Weight so we don't have to fetch
      // it multiple times
      val maxDist = implicitly[Limited[Weight]].max

      // create a minimum priority queue of (Node, Weight) pairs
      var q = mutable.PriorityQueue.empty[(Node, Weight)](
        Ordering.by((_: (Node, Weight))._1).reverse
      )

      // Previous node for each node
      var prev = Map[Node, Option[Node]]()

      // Distances to each node (start with a distance of zero to this node)
      var dist = Map[Node, Weight](this -> implicitly[Numeric[Weight]].zero)

      for { node <- nodes if node != this } {
        prev += node -> None
        dist += node -> maxDist
        q += node -> maxDist
      }

      while (q nonEmpty) {
        val (u, dist_u) = q.dequeue()
        for { (v, dist_uv) <- u.edges
              dist_alt = dist_uv + dist_u if dist_alt < dist(v) } {
          dist += v -> dist_alt
          prev += v -> u
          ??? // this is where Scala's priority queue doesn't do the thing
              // that we want
        }
      }

      (dist, prev)
    }

    /** Find the shortest path from this node to the specified node.
      *
      * @param to the [[Node]] to find the shortest path to.
      * @return   a list of nodes representing the path (in order)
      */
    def shortestPathTo(to: Node): Seq[Node]
      = {
          @tailrec
          def _spt(t: Node, path: mutable.Buffer[Node]): mutable.Buffer[Node]
          = prev.get(t) match {
              case Some(u: Node) => _spt(u, u +: path)
              case None => path
            }
          to +: _spt(to, mutable.Buffer[Node]())
        }
  }

}
