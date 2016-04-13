package edu.allegheny.graph

import scala.language.postfixOps

/** Base trait defining a Graph.
  *
  * //@tparam V the value to store at each node in this graph
  *
  * Created by hawk on 4/11/16.
  */
trait Graph[V] {
  /** The type of nodes in this graph */
  type Node <: NodeLike

  /** The type of edges in this graph.
    *
    * In an edge-weighted graph, this will generally be a tuple of the form
    * `(Node, Weight)`, while in a non-edge-weighted graph, this is just a
    * `Node`.
    */
  type Edge

  /** Class representing a node in a graph.
    *
    * Each Node stores a set of edges. It may also optionally be associated
    * with a value.
    *
    * @param value the value to store at this node.
    */
  abstract class NodeLike(val value: V) { self: Node =>
    protected[this] var _edges: Set[Edge] = Set()

    /** @return the set of edges from this node. */
    @inline final def edges: Set[Edge] = _edges

    /** Add an edge from this node */
    @inline final def addEdge(e: Edge): Unit =  _edges = _edges + e

    def hasEdgeTo(node: Node): Boolean

    /** The _degree_ (or _valency_) of a node is the number of edges connecting
      * to that node.
      * @return the degree of this node.
      */
    @inline final def degree: Int
      = _nodes count { _ hasEdgeTo this }

    /** Find the shortest path from this node to the specified node.
      *
      * @param to the node to find the shortest path to.
      * @return   a list of nodes representing the path (in order)
      */
    def shortestPathTo(to: Node): Seq[Node]
  }

  protected[this] var _nodes: Seq[Node] = Nil

  /** Construct and return a new node.
    *
    * The new node will be in the graph but will not be connected to any other
    * nodes.
    *
    * @return a new node.
    */
  def node(item: V): Node

  /** The _order_ of a graph is the number of nodes in the graph
    * @return the number of nodes in this graph
    */
  @inline final def order: Int = _nodes length

  /** The _size_ of a graph is the number of edges in the graph
    * @return the number of edges in this graph
    */
  @inline final def size: Int = _nodes map { _.edges.size } sum

  /** Find the shortest path from one node to another.
    *
    * @param to   the starting node
    * @param from the ending node
    * @return     a list of nodes representing the path (in order)
    */
  @inline final def shortestPath(to: Node, from: Node): Seq[Node]
    = from shortestPathTo to
}
