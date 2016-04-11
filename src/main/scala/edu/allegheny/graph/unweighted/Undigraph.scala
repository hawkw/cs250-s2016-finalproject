package edu.allegheny.graph.unweighted

/** An unweighted directed graph.
  *
  * @tparam V the type of the value to associate with each node in the graph.
  * @author Hawk Weisman
  *
  * Created by hawk on 4/11/2016
  */
class Undigraph[V]
  extends Unweighted[V] {

  override type Node = UndirectedUWNode
  override type Edge = Node

  override def node(item: V): Node = {
    val n = new Node(item)
    _nodes = _nodes :+ n
    n
  }

  class UndirectedUWNode(value: V)
    extends UWNode(value) { self: Node =>

    def connectTo(that: Node): Unit
      = if (!this.hasEdgeTo(that)) {
          this addEdge that
          that addEdge this
        }

    /** @inheritdoc
      *
      * In an unweighted graph, the shortest path is the path that requires
      * the fewest edges to be traversed.
      */
    def shortestPathTo(to: Node): List[Node]
      = ???

  }

}