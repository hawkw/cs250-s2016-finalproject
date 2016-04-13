package edu.allegheny.onion.simulation
import scala.io.Source

/** Generates graphs from the Algs4 data file format
  *
  * @author Aubrey Collins
  */
object GraphGenerator {

	def parse(source: io.Source): Network = {
		val graph = new Network()

		// val f = Source.fromFile(new java.io.File(path))

		// read file as a sequence of strings
		val lines: Seq[String] = source.getLines.toIndexedSeq
		// the first line in the input file is the number of nodes
		val numNodes = lines.head.toInt
		// create an array to store the new nodes and fill it with empty nodes
		val nodes: Seq[graph.Node]
			= for (i <- 0 until numNodes) yield graph.node(new Peer(i))
		//parse rest of file and make connections
		for (ln <- lines.drop(2) ) {
			val Array(to, from, weight) = ln.split(' ')
			nodes(to.toInt).connectTo(nodes(from.toInt), weight.toDouble)
		}

		graph
	}

}
