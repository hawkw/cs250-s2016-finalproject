package edu.allegheny.graph.unweighted


/** An unweighted directed graph.
  *
  * @tparam V the type of the value to associate with each node in the graph.
  * @author Hawk Weisman
  *
  * Created by hawk on 4/11/2016
  */
class Digraph[V]
extends Unweighted[V]
  with Traversable[Unweighted[V]#Node] {

  override type Node = DirectedUWNode
  override type Edge = Node

  override def node(item: V): Node = {
    val n = new Node(item)
    _nodes = _nodes :+ n
    n
  }

  class DirectedUWNode(value: V)
  extends UWNode(value) { self: Node =>

    def connectTo(that: Node): Unit
      = if (!this.hasEdgeTo(that)) this addEdge that

    /** @inheritdoc
      *
      * In an unweighted graph, the shortest path is the path that requires
      * the fewest edges to be traversed.
      */
    def shortestPathTo(to: Node): Seq[Node]
      = ???

      /** Operator for creating an edge from another node to this node.
        *
        * Note that this node is only necessary on directed graphs.
        *
        * @param  that   the node to form an edge to this node.
        */
    @inline final def <~ (that: Node): Unit = that ~> this

    /** Operator for creating a bi-directional edge between this node
      * and another.
      *
      * Note that this node is only necessary on directed graphs.
      *
      * @param  that   the node to form a bi-directional edge with
      */
    @inline final def <~> (that: Node): Unit = {
        this ~> that
        this <~ that
    }
  }

  /** Apply a function to each [[Node]] in this graph.
    *
    * @param f
    * @tparam U
    */
  @inline final def foreach[U](f: (Unweighted[V]#Node) => U): Unit
    = _nodes foreach f

}
