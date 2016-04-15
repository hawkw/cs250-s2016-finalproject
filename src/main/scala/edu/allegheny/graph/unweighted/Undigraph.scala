package edu.allegheny.graph.unweighted

import edu.allegheny.graph.Undirected

/** An unweighted directed graph.
  *
  * @tparam V the type of the value to associate with each node in the graph.
  * @author Hawk Weisman
  *
  * Created by hawk on 4/11/2016
  */
class Undigraph[V]
extends Unweighted[V]
  with Undirected[V]
  with Traversable[Unweighted[V]#Node] {

  override type Node = UndirectedUWNode
  override type Edge = Node

  override def node(item: V): Node = {
    val n = new Node(item)
    _nodes = _nodes :+ n
    n
  }

  class UndirectedUWNode(value: V)
  extends UWNode(value)
    with UndirectedNode { self: Node =>

    /** @inheritdoc
      *i
      * In an unweighted graph, the shortest path is the path that requires
      * the fewest edges to be traversed.
      */
    def shortestPathTo(to: Node): Seq[Node]
      = ???

  }

  /** Apply a function to each [[Node]] in this graph.
    *
    * @param f
    * @tparam U
    */
  @inline final def foreach[U](f: (Unweighted[V]#Node) => U): Unit
    = _nodes foreach f

  /** @inheritdoc
    *
    * This is special-cased for undirected graphs, since they cannot calculate
    * the number of edges in the same way as directed graphs.
    */
  @inline override def graphSize: Int = super.graphSize / 2


}
