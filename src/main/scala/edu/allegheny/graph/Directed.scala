package edu.allegheny.graph

/**
  * Created by hawk on 4/15/16.
  */
trait Directed[V]
extends Graph[V] {
  override type Node <: DirectedNode

  trait DirectedNode
  extends NodeLike { self: Node =>

    override def connectTo(edge: Edge): Unit
      = if (!this.edges.contains(edge)) {
          _edges += edge
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
