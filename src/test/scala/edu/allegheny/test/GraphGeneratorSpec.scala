package edu.allegheny.test

import edu.allegheny.graph.Graph
import edu.allegheny.graph.edgeWeighted.Undigraph
import edu.allegheny.onion.simulation.{GraphGenerator, Peer}
import org.scalatest.{Matchers, WordSpec}

import scala.io.Source

/**
  * Created by hawk on 4/12/16.
  */
class GraphGeneratorSpec
extends WordSpec
  with Matchers {

  "The GraphGenerator" when {
    "parsing tinyEWD.txt" should {
      val tinyEWD = Source.fromURL(getClass.getResource("/tinyEWD.txt"))
      "produce a network with the correct number of nodes" in {
        val network: Graph[Peer] = GraphGenerator parse tinyEWD

        network should have ('graphOrder (8))
      }
      "produce a network with the correct number of edges" in {
        val network: Graph[Peer] = GraphGenerator parse tinyEWD

        network should have ('graphSize (15))
      }
    }
  }

}
