package edu.allegheny.onion.simulation

/** A fake onion (it's made of plastic)
  * Created by hawk on 4/12/16.
  */
case class FakeOnion( source: Peer
                    , dest: Peer
                    , path: Seq[Link]) {

  lazy val pathLength = path.length
  lazy val pathLatency
    = path.foldLeft(0d) { case (totalLatency, (_, latency)) =>
        totalLatency + latency
    }

}