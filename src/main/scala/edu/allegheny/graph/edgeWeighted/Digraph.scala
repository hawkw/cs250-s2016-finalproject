package edu.allegheny.graph.edgeWeighted

import edu.allegheny.graph.Directed
import org.scalactic.Requirements

import scala.{specialized => sp}
import scala.languageFeature.postfixOps



/** An edge-weighted directed graph.
  *
  * @tparam V      the type of the value to associate with each node in the
  *                graph.
  * @tparam Weight the type of the weight value associated with each edge in
  *                the graph.
  *
  * @author Hawk Weisman
  *
  * Created by hawk on 4/11/16.
  */
class Digraph[V, @sp(Int, Long, Float, Double) Weight : Numeric : Ordering]
extends EdgeWeighted[V, Weight]
  with Directed[V]
  with Traversable[EdgeWeighted[V, Weight]#Node]
  with Requirements {

  override type Node = DirectedEWNode
  override type Edge = (Node, Weight)

  override def node(item: V): Node = {
    val n = new Node(item)
    _nodes = _nodes :+ n
    n
  }

  class DirectedEWNode(value: V)
  extends EWNode(value)
    with DirectedNode { self: Node =>

    /** @inheritdoc
      *
      * In an edge-weighted graph, the shortest path is the path for which
      * the sum of the weights of the edges traversed is the lowest.
      */
    def shortestPathTo(to: Node): Seq[Node]
      = ???

  }

  /** Apply a function to each [[Node]] in this graph.
    * @param f
    * @tparam U
    */
  @inline final def foreach[U](f: (EdgeWeighted[V, Weight]#Node) => U): Unit
    = _nodes foreach f

}

object Digraph {

  /**
    * Parse an edge-weighted directed graph.
    *
    * Parse an edge-weighted directed graph from the edge-weighted graph
    * description format from _Algorithms_, 4th Edition.
    *
    * @param mkValue a function that produces the values to place in each node.
    * @param source  a `Source` containing the text to parse
    * @tparam V      the type of the values in the graph
    * @tparam W      the type of the weights in the graph (note that this
    *                should probably be either `Double` or `Float`, since
    *                most of the weights in the _Algorithms_ 4th Ed. sample
    *                files are decimal and truncating them to integers would
    *                give you a lot of zeros)
    * @return        a graph based on the one in the data file.
    * @author        Aubrey Collins
    */
  def parse[V, W : Numeric](mkValue: () => V)(source: io.Source): Digraph[V, W]
    = {
      val graph = new Digraph[V, W]()

      // val f = Source.fromFile(new java.io.File(path))

      // read file as a sequence of strings
      val lines: Seq[String] = source.getLines.toIndexedSeq
      // the first line in the input file is the number of nodes
      val numNodes = lines.headOption map (_ toInt) getOrElse 0
      // create an array to store the new nodes and fill it with empty nodes
      val nodes
      = for { i <- 0 until numNodes } yield graph.node(mkValue())

      //parse rest of file and make connections
      for { ln <- lines.drop(2)
        Array(to, from, weight) = ln.split(' ')
      } {
        nodes(to.toInt) ~> (nodes(from.toInt), weight.toDouble.asInstanceOf[W])
      }

      graph
    }
}