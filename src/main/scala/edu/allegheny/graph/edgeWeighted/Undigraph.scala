package edu.allegheny.graph.edgeWeighted

import edu.allegheny.graph.Undirected

import scala.{specialized => sp}

/** An edge-weighted undirected graph.
  *
  * @tparam V      the type of the value to associate with each node in the
  *                graph.
  * @tparam Weight the type of the weight value associated with each edge in
  *                the graph.
  * @author Hawk Weisman
  *
  * Created by hawk on 4/11/16.
  */
class Undigraph[V, @sp(Int, Long, Float, Double) Weight : Numeric : Ordering]
extends EdgeWeighted[V, Weight]
  with Undirected[V]
  with Traversable[EdgeWeighted[V, Weight]#Node]  {

  override type Node = UndirectedEWNode
  override type Edge = (Node, Weight)

  override def node(item: V): Node = {
    val n = new Node(item)
    _nodes = _nodes :+ n
    n
  }

  class UndirectedEWNode(value: V)
  extends EWNode(value)
    with UndirectedNode { self: Node =>

    /** @inheritdoc
      *
      * In an edge-weighted graph, the shortest path is the path for which
      * the sum of the weights of the edges traversed is the lowest.
      */
    def shortestPathTo(to: Node): Seq[Node]
      = ???

  }

  /** Apply a function to each [[Node]] in this graph.
    *
    * @param f
    * @tparam U
    */
  @inline final def foreach[U](f: (EdgeWeighted[V, Weight]#Node) => U): Unit
    = _nodes foreach f

  /** @inheritdoc
    *
    * This is special-cased for undirected graphs, since they cannot calculate
    * the number of edges in the same way as directed graphs.
    */
  @inline override def graphSize: Int = super.graphSize / 2

}
