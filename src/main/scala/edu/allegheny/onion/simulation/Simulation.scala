package edu.allegheny.onion.simulation
import edu.allegheny.graph.edgeWeighted.Undigraph
import edu.allegheny.graph.edgeWeighted.Limited.Implicits.DoubleLimits

/**
  * Created by hawk on 4/12/16.
  */
object Simulation {

  val network = new Undigraph[Peer, Double]()

  val p1 = network.node(new Peer())
  val p2 = network.node(new Peer())
  val p3 = network.node(new Peer())

  p1.connectTo(p2, 0.5)
  p2.connectTo(p3, 0.75)
  p3.connectTo(p1, 0.25)

}
