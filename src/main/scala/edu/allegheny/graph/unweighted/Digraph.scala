package edu.allegheny.graph.unweighted

import edu.allegheny.graph.Directed


/** An unweighted directed graph.
  *
  * @tparam V the type of the value to associate with each node in the graph.
  * @author Hawk Weisman
  *
  * Created by hawk on 4/11/2016
  */
class Digraph[V]
extends Unweighted[V]
  with Directed[V]
  with Traversable[Unweighted[V]#Node] {

  override type Node = DirectedUWNode
  override type Edge = Node

  override def node(item: V): Node = {
    val n = new Node(item)
    _nodes = _nodes :+ n
    n
  }

  class DirectedUWNode(value: V)
  extends UWNode(value)
    with DirectedNode { self: Node =>

    /** @inheritdoc
      *
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

}
