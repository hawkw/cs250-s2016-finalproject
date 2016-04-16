package edu.allegheny.onion
import edu.allegheny.graph.edgeWeighted
import edu.allegheny.graph.edgeWeighted.Undigraph

import scala.io.Source

/**
  * Created by hawk on 4/12/16.
  */
package object simulation {
  type Link = (Peer, Double)
  type Network = edgeWeighted.Undigraph[Peer, Double]

  val parseNetwork: (Source) => Network
    = Undigraph.parse[Peer, Double](() => new Peer) _


}