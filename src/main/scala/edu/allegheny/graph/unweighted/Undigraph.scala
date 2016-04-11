package edu.allegheny.graph.unweighted

/** An unweighted directed graph.
  *
  * @tparam V      the type of the value to associate with each node in the
  *                graph.
  * @author Hawk Weisman
  *
  * Created by hawk on 4/11/2016
  */
class Undigraph[V]
  extends Unweighted[V] {

  override type Node = UndirectedUWNode
  override type Edge = Node

  class UndirectedUWNode(value: V)
    extends UWNode(value) { self: Node =>

    def connectTo(that: Node): Unit
      = if (!this.hasEdgeTo(that)) {
          this addEdge that
          that addEdge this
        }

  }

}