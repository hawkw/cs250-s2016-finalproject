package edu.allegheny.test

import edu.allegheny.graph.{Graph, edgeWeighted}
import edu.allegheny.onion.simulation.{GraphGenerator, Peer, parseNetwork}
import org.scalatest.{Matchers, OptionValues, WordSpec}

import scala.io.Source

/**
  * Created by hawk on 4/12/16.
  */
class GraphGeneratorSpec
extends WordSpec
  with Matchers
  // with NodeMatchers
  with OptionValues {

  "The GraphGenerator" when {
    def tinyEWD: Source = Source.fromURL(getClass.getResource("/tinyEWD.txt"))
    "parsing tinyEWD.txt as an undirected graph " should {


      "produce a network with the correct number of nodes" in {
        parseNetwork(tinyEWD) should have ('graphOrder (8))
      }
      "produce a network with the correct number of edges" in {
        val network: Graph[Peer] = parseNetwork(tinyEWD)
        // Note that the expected number of edges is two fewer than in the file
        // since the file expects the graph to be directed
        network should have ('graphSize (13))
      }
      "connect the correct nodes to each other" in {
        val network: Graph[Peer] = parseNetwork(tinyEWD)

//        4 5 0.35
        network.nodes(4).hasEdgeTo(network.nodes(5)) shouldBe true
//        5 4 0.35
        network.nodes(5).hasEdgeTo(network.nodes(4)) shouldBe true
//        4 7 0.37
        network.nodes(4).hasEdgeTo(network.nodes(7)) shouldBe true
//        5 7 0.28
        network.nodes(5).hasEdgeTo(network.nodes(7)) shouldBe true
//        7 5 0.28
        network.nodes(7).hasEdgeTo(network.nodes(5)) shouldBe true
//        5 1 0.32
        network.nodes(5).hasEdgeTo(network.nodes(1)) shouldBe true
//        0 4 0.38
        network.nodes(0).hasEdgeTo(network.nodes(4)) shouldBe true
//        0 2 0.26
        network.nodes(0).hasEdgeTo(network.nodes(2)) shouldBe true
//        7 3 0.39
        network.nodes(7).hasEdgeTo(network.nodes(3)) shouldBe true
//        1 3 0.29
        network.nodes(1).hasEdgeTo(network.nodes(3)) shouldBe true
//        2 7 0.34
        network.nodes(2).hasEdgeTo(network.nodes(7)) shouldBe true
//        6 2 0.40
        network.nodes(6).hasEdgeTo(network.nodes(2)) shouldBe true
//        3 6 0.52
        network.nodes(3).hasEdgeTo(network.nodes(6)) shouldBe true
//        6 0 0.58
        network.nodes(6).hasEdgeTo(network.nodes(0)) shouldBe true
//        6 4 0.93
        network.nodes(6).hasEdgeTo(network.nodes(4)) shouldBe true

      }
      "connect the nodes with the correct weights" in {
        val network: edgeWeighted.Undigraph[Peer, Double]
          = parseNetwork(tinyEWD)

//        4 5 0.35
        network.nodes(4).weightTo(network.nodes(5)).value shouldBe 0.35
//        5 4 0.35
        network.nodes(5).weightTo(network.nodes(4)).value shouldBe 0.35
//        4 7 0.37
        network.nodes(4).weightTo(network.nodes(7)).value shouldBe 0.37
//        5 7 0.28
        network.nodes(5).weightTo(network.nodes(7)).value shouldBe 0.28
//        7 5 0.28
        network.nodes(7).weightTo(network.nodes(5)).value shouldBe 0.28
//        5 1 0.32
        network.nodes(5).weightTo(network.nodes(1)).value shouldBe 0.32
//        0 4 0.38
        network.nodes(0).weightTo(network.nodes(4)).value shouldBe 0.38
//        0 2 0.26
        network.nodes(0).weightTo(network.nodes(2)).value shouldBe 0.26
//        7 3 0.39
        network.nodes(7).weightTo(network.nodes(3)).value shouldBe 0.39
//        1 3 0.29
        network.nodes(1).weightTo(network.nodes(3)).value shouldBe 0.29
//        2 7 0.34
        network.nodes(2).weightTo(network.nodes(7)).value shouldBe 0.34
//        6 2 0.40
        network.nodes(6).weightTo(network.nodes(2)).value shouldBe 0.40
//        3 6 0.52
        network.nodes(3).weightTo(network.nodes(6)).value shouldBe 0.52
//        6 0 0.58
        network.nodes(6).weightTo(network.nodes(0)).value shouldBe 0.58
//        6 4 0.93
        network.nodes(6).weightTo(network.nodes(4)).value shouldBe 0.93
      }
    }
    "parsing tinyEWD.txt as a directed graph" should {

      "produce a network with the correct number of nodes" in {
        parseNetwork(tinyEWD) should have ('graphOrder (8))
      }
      "produce a network with the correct number of edges" in {
        val network: Graph[Peer] = parseNetwork(tinyEWD)
        network should have ('graphSize (15))
      }
      "connect the correct nodes to each other" in {
        val network: Graph[Peer] = parseNetwork(tinyEWD)

//        4 5 0.35
        network.nodes(4).hasEdgeTo(network.nodes(5)) shouldBe true
//        5 4 0.35
        network.nodes(5).hasEdgeTo(network.nodes(4)) shouldBe true
//        4 7 0.37
        network.nodes(4).hasEdgeTo(network.nodes(7)) shouldBe true
//        5 7 0.28
        network.nodes(5).hasEdgeTo(network.nodes(7)) shouldBe true
//        7 5 0.28
        network.nodes(7).hasEdgeTo(network.nodes(5)) shouldBe true
//        5 1 0.32
        network.nodes(5).hasEdgeTo(network.nodes(1)) shouldBe true
//        0 4 0.38
        network.nodes(0).hasEdgeTo(network.nodes(4)) shouldBe true
//        0 2 0.26
        network.nodes(0).hasEdgeTo(network.nodes(2)) shouldBe true
//        7 3 0.39
        network.nodes(7).hasEdgeTo(network.nodes(3)) shouldBe true
//        1 3 0.29
        network.nodes(1).hasEdgeTo(network.nodes(3)) shouldBe true
//        2 7 0.34
        network.nodes(2).hasEdgeTo(network.nodes(7)) shouldBe true
//        6 2 0.40
        network.nodes(6).hasEdgeTo(network.nodes(2)) shouldBe true
//        3 6 0.52
        network.nodes(3).hasEdgeTo(network.nodes(6)) shouldBe true
//        6 0 0.58
        network.nodes(6).hasEdgeTo(network.nodes(0)) shouldBe true
//        6 4 0.93
        network.nodes(6).hasEdgeTo(network.nodes(4)) shouldBe true

      }
      "connect the nodes with the correct weights" in {
        val network: edgeWeighted.Undigraph[Peer, Double]
        = parseNetwork(tinyEWD)

//        4 5 0.35
        network.nodes(4).weightTo(network.nodes(5)).value shouldBe 0.35
//        5 4 0.35
        network.nodes(5).weightTo(network.nodes(4)).value shouldBe 0.35
//        4 7 0.37
        network.nodes(4).weightTo(network.nodes(7)).value shouldBe 0.37
//        5 7 0.28
        network.nodes(5).weightTo(network.nodes(7)).value shouldBe 0.28
//        7 5 0.28
        network.nodes(7).weightTo(network.nodes(5)).value shouldBe 0.28
//        5 1 0.32
        network.nodes(5).weightTo(network.nodes(1)).value shouldBe 0.32
//        0 4 0.38
        network.nodes(0).weightTo(network.nodes(4)).value shouldBe 0.38
//        0 2 0.26
        network.nodes(0).weightTo(network.nodes(2)).value shouldBe 0.26
//        7 3 0.39
        network.nodes(7).weightTo(network.nodes(3)).value shouldBe 0.39
//        1 3 0.29
        network.nodes(1).weightTo(network.nodes(3)).value shouldBe 0.29
//        2 7 0.34
        network.nodes(2).weightTo(network.nodes(7)).value shouldBe 0.34
//        6 2 0.40
        network.nodes(6).weightTo(network.nodes(2)).value shouldBe 0.40
//        3 6 0.52
        network.nodes(3).weightTo(network.nodes(6)).value shouldBe 0.52
//        6 0 0.58
        network.nodes(6).weightTo(network.nodes(0)).value shouldBe 0.58
//        6 4 0.93
        network.nodes(6).weightTo(network.nodes(4)).value shouldBe 0.93
      }
    }
  }

}
