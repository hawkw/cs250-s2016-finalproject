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

    // Our implementation of Djikstra's algorithm computes the shortest-path
    // tree from this node: when we run Djikstra's algorithm a single time,
    // we have already done the heavy-lifting of finding the shortest path to
    // each other node. Thus, it makes sense to want to cache this
    // information, rather than having to re-calcualte it every time we want to
    // find the shortest path to a node.
    protected[this] type SPState = (Map[Node, Node], Map[Node, Weight])
    private[this] object SPCache {
      // The obvious solution would be to just use a `lazy val` to cache our
      // shortest-path tree, so that it is calculated the first time we need to
      // use it. However, this has a major issue: if we calculate the
      // shortest-path tree and then the topography of the graph changes, the
      // tree could now be incorrect. Therefore, we make it an Option instead.
      private[this] var cache: Option[SPState] = None
      // When the node's edges have changed, we have to invalidate the cache.
      // We do this simply by setting the cahce equal to `None`.
      @inline final def invalidate() { cache = None }
      // Rather than re-calculating the shortest-path tree every time the node's
      // edges change, however, we only update the cache when we need to access
      // it. This is because it is quite likely that we will often make multiple
      // new connections to a node before needing to find the shortest path to
      // some other node, such as when a graph is initially being created. It
      // would be a waste of time to re-compute the shortest path tree every time
      // the node's edges change, as we may not need to find a shortest path
      // before the edges change again.
      @inline final def getOrUpdate: SPState
      = cache match {
        case Some(c) => c
        case None =>
          val c = djikstra()
          cache = Some(c)
          c
      }
    }


    // We can also cache the max (infinity) value of Weight so we don't have to
    // fetch it multiple times
    private lazy val maxDist = implicitly[Limited[Weight]].max

    @inline final private[this] def djikstra(): SPState = {
      // create a minimum priority queue of (Node, Weight) pairs
      var q = mutable.PriorityQueue.empty[(Node, Weight)](
        Ordering.by((_: (Node, Weight))._2).reverse
      )

      // Previous node for each node
      var prev = Map[Node, Node]()

      // Distances to each node (start with a distance of zero to this node)
      var dist = Map[Node, Weight](this -> implicitly[Numeric[Weight]].zero)

      for { node <- nodes if node != this } {
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

      (prev, dist)
    }

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
          SPCache.invalidate()
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


    /** @inheritdoc
      *
      * In an edge-weighted graph, the shortest path is the path for which
      * the sum of the weights of the edges traversed is the lowest.
      */
    override def shortestPathTo(to: Node): Seq[Node]
      = { val (prev, _) = SPCache.getOrUpdate

          @tailrec
          def _spt(t: Node, path: mutable.Buffer[Node]): mutable.Buffer[Node]
            = prev.get(t) match {
                case Some(u) => _spt(u, u +: path)
                case None => path
              }

          to +: _spt(to, mutable.Buffer[Node]())
        }

    /**
      * Returns the shortest distance to the given node, calculated using
      * Djikstra's algorithm.
      *
      * @param to the node to get the best distance to
      * @return   the shortest distance to that node.
      */
   @inline def shortestDistanceTo(to: Node): Weight
      = { val (_, dist) = SPCache.getOrUpdate; dist(to) }
  }

}
