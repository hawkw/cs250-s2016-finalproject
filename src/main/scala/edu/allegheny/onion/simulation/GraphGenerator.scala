package edu.allegheny.onion.simulation
import edu.allegheny.graph.edgeWeighted.Undigraph
import scala.io.Source

object GraphGenerator {

	def parseFile(path: String): Undigraph[Peer, Double] {
		val graph = new Undigraph()

		val f = Source.fromFile(new java.io.File(path))

		// read file as a sequence of strings
		val lines: Seq[String] = for (ln <- f.getLines) yield ln
		// the first line in the input file is the number of nodes
		val numNodes = lines(0).toInt
		// create an array to store the new nodes and fill it with empty nodes
		val nodes: Array[graph.Node] 
			= for (_ <- 0...numNodes) yield graph.node(new Peer())
		//parse rest of file and make connections
		for (ln <- lines.drop(2)) {
			val Array(to, from, weight) = ln.split(' ')
			nodes(to.toInt).connectTo(nodes(from.toInt), weight.toDouble)
		}

		graph
	}

}