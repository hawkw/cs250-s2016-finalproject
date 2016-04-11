package edu.allegheny.graph

/**
  * Created by hawk on 4/11/16.
  */
trait Node {
  type Edge

  protected[this] var _edges: Seq[Edge]

  def edges: Seq[Edge] = _edges
  def addEdge(e: Edge): Unit =  _edges = _edges :+ e
//  def removeEdge(e: Edge): Unit =  _edges = _edges.
}