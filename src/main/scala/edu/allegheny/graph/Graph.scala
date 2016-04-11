package edu.allegheny.graph

import scala.language.postfixOps

/**
  * Created by hawk on 4/11/16.
  */
trait Graph {
  type Node <: NodeLike
  type Edge

  trait NodeLike { self: Node =>
    protected[this] var _edges: Seq[Edge] = Nil

    @inline final def edges: Seq[Edge] = _edges
    @inline final def addEdge(e: Edge): Unit =  _edges = _edges :+ e

    def hasEdgeTo(node: Node): Boolean

    /** The _degree_ (or _valency_) of a node is the number of edges connecting
      * to that node.
      * @return the degree of this node.
      */
    @inline final def degree: Int
      = _nodes count { _ hasEdgeTo this }
  }

  protected[this] var _nodes: List[Node] = Nil

  /** Construct and return a new node.
    *
    * The new node will be in the graph but will not be connected to any other
    * nodes.
    *
    * @return a new node.
    */
  @inline final def node: Node = {
    val n = new Node
    _nodes = _nodes :+ n
    n
  }

  /** The _order_ of a graph is the number of nodes in the graph
    * @return the number of nodes in this graph
    */
  @inline final def order: Int = _nodes length

  /** The _size_ of a graph is the number of edges in the graph
    * @return the number of edges in this graph
    */
  @inline final def size: Int = _nodes map { _.edges.length } sum
}