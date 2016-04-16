package edu.allegheny.test

import edu.allegheny.graph.{Graph, edgeWeighted, unweighted}
import edu.allegheny.onion.simulation.{GraphGenerator, Peer}
import org.scalatest.{Matchers, OptionValues, WordSpec}

import scala.io.Source

/**
  * Created by hawk on 4/12/16.
  */
class GraphSpec
extends WordSpec
  with Matchers
  with OptionValues {

  "An unweighted, directed graph" when {
    "using operators to create edges" should {
      "create an edge from A to B using the ~> operator" in {
        val g = new unweighted.Digraph[Int]
        val a = g node 1
        val b = g node 2
        a ~> b
        a.hasEdgeTo(b) shouldBe true
        b.hasEdgeTo(a) shouldBe false
      }
      "create a bidirectional edge between A and B using the <~> operator" in {
        val g = new unweighted.Digraph[Int]
        val a = g node 1
        val b = g node 2
        a <~> b
        a.hasEdgeTo(b) shouldBe true
        b.hasEdgeTo(a) shouldBe true
      }
    }
    "using the ~>? operator to test for edges" should {
      "return true when A has an edge to B" in {
        val g = new unweighted.Digraph[Int]
        val a = g node 1
        val b = g node 2
        a.connectTo(b)
        a ~>? b shouldBe true
      }
      "return false when A does not have an edge to B" in {
        val g = new unweighted.Digraph[Int]
        val a = g node 1
        val b = g node 2
        a ~>? b shouldBe false
      }
      "return false when B has an edge to A but A doesn't have one to B" in {
        val g = new unweighted.Digraph[Int]
        val a = g node 1
        val b = g node 2
        b.connectTo(a)
        a ~>? b shouldBe false
      }
    }
    "using the <~>? operator to test for edges" should {
        "return false when the edge is from A to B only" in {
          val g = new unweighted.Digraph[Int]
          val a = g node 1
          val b = g node 2
          a.connectTo(b)
          a <~>? b shouldBe false
        }
        "return false when the edge is from B to A only" in {
          val g = new unweighted.Digraph[Int]
          val a = g node 1
          val b = g node 2
          b.connectTo(a)
          a <~>? b shouldBe false
        }
        "return false when there are no edges" in {
          val g = new unweighted.Digraph[Int]
          val a = g node 1
          val b = g node 2
          a <~>? b shouldBe false
        }
        "return true when the edges are bi-directional" in {
          val g = new unweighted.Digraph[Int]
          val a = g node 1
          val b = g node 2
          a.connectTo(b)
          b.connectTo(a)
          a <~>? b shouldBe false
        }
      }
    "using the <~? operator to test for edges" should {
      "return true when B has an edge to A" in {
        val g = new unweighted.Digraph[Int]
        val a = g node 1
        val b = g node 2
        b.connectTo(a)
        a <~? b shouldBe true
      }
      "return false when A does not have an edge to B" in {
        val g = new unweighted.Digraph[Int]
        val a = g node 1
        val b = g node 2
        a <~? b shouldBe false
      }
      "return false when B has an edge to A but A doesn't have one to B" in {
        val g = new unweighted.Digraph[Int]
        val a = g node 1
        val b = g node 2
        a.connectTo(b)
        a <~? b shouldBe false
      }
    }
  }

  "An unweighted, undirected graph" when {
    "using operators to create edges" should {
      "create an edge from A to B using the ~> operator" in {
        val g = new unweighted.Undigraph[Int]
        val a = g node 1
        val b = g node 2
        a ~> b
        a.hasEdgeTo(b) shouldBe true
        b.hasEdgeTo(a) shouldBe true
      }
      "create a bidirectional edge between A and B using the <~> operator" in {
        val g = new unweighted.Undigraph[Int]
        val a = g node 1
        val b = g node 2
        a <~> b
        a.hasEdgeTo(b) shouldBe true
        b.hasEdgeTo(a) shouldBe true
      }
    }
    "using the ~>? operator to test for edges" should {
      "return true when A has an edge to B" in {
        val g = new unweighted.Undigraph[Int]
        val a = g node 1
        val b = g node 2
        a.connectTo(b)
        a ~>? b shouldBe true
      }
      "return false when A does not have an edge to B" in {
        val g = new unweighted.Undigraph[Int]
        val a = g node 1
        val b = g node 2
        a ~>? b shouldBe false
      }
      "return true when the connection was created from B" in {
        val g = new unweighted.Undigraph[Int]
        val a = g node 1
        val b = g node 2
        b.connectTo(a)
        a ~>? b shouldBe true
      }
    }
    "using the <~? operator to test for edges" should {
      "return true when B has an edge to A" in {
        val g = new unweighted.Undigraph[Int]
        val a = g node 1
        val b = g node 2
        b.connectTo(a)
        a <~? b shouldBe true
      }
      "return false when A does not have an edge to B" in {
        val g = new unweighted.Undigraph[Int]
        val a = g node 1
        val b = g node 2
        a <~? b shouldBe false
      }
      "return true when the connection was created from B" in {
        val g = new unweighted.Undigraph[Int]
        val a = g node 1
        val b = g node 2
        a.connectTo(b)
        a <~? b shouldBe true
      }
    }
  }

  "An edge weighted, directed graph" when {
    "using operators to create edges" should {
      "create an edge from A to B using the ~> operator" in {
        val g = new edgeWeighted.Digraph[Int, Double]
        val a = g node 1
        val b = g node 2
        a ~> (b, 0.5)
        a.hasEdgeTo(b) shouldBe true
        b.hasEdgeTo(a) shouldBe false
      }
      "create a bidirectional edge between A and B using the <~> operator" in {
        val g = new edgeWeighted.Digraph[Int, Double]
        val a = g node 1
        val b = g node 2
        a <~> (b, 0.5)
        a.hasEdgeTo(b) shouldBe true
        b.hasEdgeTo(a) shouldBe true
      }
    }
    "using the ~>? operator to test for edges" should {
      "return true when A has an edge to B" in {
        val g = new edgeWeighted.Digraph[Int, Double]
        val a = g node 1
        val b = g node 2
        a.connectTo(b, 0.5)
        a ~>? b shouldBe true
      }
      "return false when A does not have an edge to B" in {
        val g = new edgeWeighted.Digraph[Int, Double]
        val a = g node 1
        val b = g node 2
        a ~>? b shouldBe false
      }
      "return false when B has an edge to A but A doesn't have one to B" in {
        val g = new edgeWeighted.Digraph[Int, Double]
        val a = g node 1
        val b = g node 2
        b.connectTo(a, 0.5)
        a ~>? b shouldBe false
      }
    }
    "using the <~? operator to test for edges" should {
      "return true when B has an edge to A" in {
        val g = new edgeWeighted.Digraph[Int, Double]
        val a = g node 1
        val b = g node 2
        b.connectTo(a, 0.5)
        a <~? b shouldBe true
      }
      "return false when A does not have an edge to B" in {
        val g = new edgeWeighted.Digraph[Int, Double]
        val a = g node 1
        val b = g node 2
        a <~? b shouldBe false
      }
      "return false when B has an edge to A but A doesn't have one to B" in {
        val g = new edgeWeighted.Digraph[Int, Double]
        val a = g node 1
        val b = g node 2
        a.connectTo(b, 0.5)
        a <~? b shouldBe false
      }
    }
  }

  "An edge weighted, undirected graph" when {
    "using operators to create edges" should {
      "create an edge from A to B using the ~> operator" in {
        val g = new edgeWeighted.Undigraph[Int, Double]
        val a = g node 1
        val b = g node 2
        a ~> (b, 0.5)
        a.hasEdgeTo(b) shouldBe true
        b.hasEdgeTo(a) shouldBe true
      }
      "create a bidirectional edge between A and B using the <~> operator" in {
        val g = new edgeWeighted.Undigraph[Int, Double]
        val a = g node 1
        val b = g node 2
        a <~> (b, 0.5)
        a.hasEdgeTo(b) shouldBe true
        b.hasEdgeTo(a) shouldBe true
      }
    }
    "using the ~>? operator to test for edges" should {
      "return true when A has an edge to B" in {
        val g = new edgeWeighted.Undigraph[Int, Double]
        val a = g node 1
        val b = g node 2
        a.connectTo(b, 0.5)
        a ~>? b shouldBe true
      }
      "return false when A does not have an edge to B" in {
        val g = new edgeWeighted.Undigraph[Int, Double]
        val a = g node 1
        val b = g node 2
        a ~>? b shouldBe false
      }
      "return true when the connection was created from B" in {
        val g = new edgeWeighted.Undigraph[Int, Double]
        val a = g node 1
        val b = g node 2
        b.connectTo(a, 0.5)
        a ~>? b shouldBe true
      }
    }
    "using the <~? operator to test for edges" should {
      "return true when B has an edge to A" in {
        val g = new edgeWeighted.Undigraph[Int, Double]
        val a = g node 1
        val b = g node 2
        b.connectTo(a, 0.5)
        a <~? b shouldBe true
      }
      "return false when A does not have an edge to B" in {
        val g = new edgeWeighted.Undigraph[Int, Double]
        val a = g node 1
        val b = g node 2
        a <~? b shouldBe false
      }
      "return true when the connection was created from B" in {
        val g = new edgeWeighted.Undigraph[Int, Double]
        val a = g node 1
        val b = g node 2
        a.connectTo(b, 0.5)
        a <~? b shouldBe true
      }
    }
    "finding a shortest path" should {
      "return the path whose weights are minimal" in {
        val tinyEWD = Source.fromURL(getClass.getResource("/tinyEWD.txt"))
        val g = GraphGenerator parse tinyEWD
        val a = g nodes 0
        val b = g nodes 3
        val path = a shortestPathTo b
        val shortest = Seq(a, g nodes 2, g nodes 7, b)
        path shouldBe shortest
      }
    }
  }


}
