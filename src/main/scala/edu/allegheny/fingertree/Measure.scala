package edu.allegheny.fingertree

/** Trait for a measure on a [[FingerTree]]
  *
  * @todo Can this just be represented with a `Monoid` from `cats`?
  *
  * Created by hawk on 3/14/16.
  */
trait Measure[V, -A] {

  def measure(a: A): V

  @inline final def apply(a: A): V = measure(a)

}
