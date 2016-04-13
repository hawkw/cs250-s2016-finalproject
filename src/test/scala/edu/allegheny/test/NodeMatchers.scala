// package edu.allegheny.test
//
//
// import org.scalatest.matchers.{HavePropertyMatchResult, HavePropertyMatcher}
// import edu.allegheny.graph.Graph
//
// /**
//   * Created by hawk on 4/13/16.
//   */
// trait NodeMatchers {
//
//   def edgeTo[G <: Graph[_]](to: G#Node) =
//     new HavePropertyMatcher[G#Node, Boolean] {
//       def apply(node: G#Node)
//         = HavePropertyMatchResult( node.hasEdgeTo(to)
//                                  , "edge to"
//                                  , true
//                                  , false )
//     }
//
// }
