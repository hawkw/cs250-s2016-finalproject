package edu.allegheny.graph
package unweighted


/** An unweighted graph.
  *
  * In an unweighted graph, edges are not associated with weights. Finding
  * the shortest path in such a graph cares only about the number of edges,
  * not their lengths.
  *
  * @tparam V the type of the value to associate with each node in the graph.
  * @author Hawk Weisman
  *
  * Created by hawk on 4/11/16.
  */
trait Unweighted[V]
extends Graph[V] {

  override type Node <: UWNode
  override type Edge = Node

  abstract class UWNode(value: V)
  extends NodeLike(value) { self: Node =>

    def connectTo(node: Node)

    override def hasEdgeTo(node: Node): Boolean
    = _edges contains node
  }


}