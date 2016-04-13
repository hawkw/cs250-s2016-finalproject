package edu.allegheny.onion
import edu.allegheny.graph.edgeWeighted

/**
  * Created by hawk on 4/12/16.
  */
package object simulation {
  type Link = (Peer, Double)
  type Network = edgeWeighted.Undigraph[Peer, Double]

}
