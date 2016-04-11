package edu.allegheny.graph

/** An unweighted directed graph.
  *
  * @tparam V      the type of the value to associate with each node in the
  *                graph.
  *
  * @author Hawk Weisman
  *
  * Created by hawk on 4/11/2016
  */
class UnweightedDigraph[V]
extends Unweighted[V] {

  override type Node = DirectedUWNode
  override type Edge = Node

  class DirectedUWNode(value: V)
    extends UWNode(value) { self: Node =>

    def connectTo(that: Node): Unit
      = if (!this.hasEdgeTo(that)) this addEdge that

  }

}