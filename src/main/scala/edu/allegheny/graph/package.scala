package edu.allegheny

/** == Graphs ==
  *
  * This package contains two subpackages, one for edge-weighted graphs, and
  * one for graphs that are unweighted. Each package contains its own concrete
  * implementations for directed ([[graph.unweighted.Digraph]] and
  * [[graph.edgeWeighted.Digraph]] and undirected (
  * [[graph.unweighted.Undigraph]] and [[graph.edgeWeighted.Undigraph]]) graphs.
  *
  * All graph implementations extend the core [[graph.Graph]] trait.
  *
  * Nodes in the graphs in this package may be associated with a value. If
  * you do not wish to store a value in the nodes in your graph, simply
  * create graphs with the value type set to `Unit`.
  *
  * @author Hawk Weisman
  *
  * Created by hawk on 4/11/16.
  */
package object graph {

}