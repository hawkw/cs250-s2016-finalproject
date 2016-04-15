package edu.allegheny.graph

/**
  * Created by hawk on 4/15/16.
  */
trait Undirected[V]
  extends Graph[V] {
  override type Node <: UndirectedNode

  trait UndirectedNode
  extends NodeLike { self: Node =>

    override final def connectTo(edge: Edge): Unit
      = if (!this.edges.contains(edge)) {
        this.addEdge(edge)
        this <~ edge
      }


    /** Operator for creating a bi-directional edge between this node
      * and another.
      *
      * Note that this node is only necessary on directed graphs.
      *
      * @param  that   the node to form a bi-directional edge with
      */
    @inline final def <~> (that: Edge): Unit = {
      this ~> that
      this <~ that
    }


  }

}
