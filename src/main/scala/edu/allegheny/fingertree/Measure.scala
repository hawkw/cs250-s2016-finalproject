package edu.allegheny.fingertree

import org.scalactic.Requirements._

/** Trait for a measure on a [[FingerTree]].
  *
  * A measure on a [[FingerTree]] is essentially a `Monoid`.
  *
  * A monoid is a semigroup with an identity. A monoid is a specialization of a
  * semigroup, so its operation must be associative. Additionally,
  * `combine(x, empty) == combine(empty, x) == x`.
  *
  * @todo Can this just be represented with a `Monoid` from `cats`?
  *
  * Right now we are essentially writing our own `Monoid` typeclass so as to
  * avoid using an external library. It might be better to just depend on
  * either `scalaz` or `algebra` (I think `algebra` looks nicer but it's also
  * experimental) to get `Monoid`s.
  *
  * Created by hawk on 3/14/16.
  */
trait Measure[V, @specialized(Int, Long, Float, Double) -A] {

  /** Return the identity measure.
    *
    * @todo should this be named `empty` by analogy with `Monoid`,
    *       `identity`, or `zero`?
    *
    * @return the identity measure
    */
  def empty: V

  /** Returns true if `a` is the identity
    *
    * @param a
    * @return
    */
  final def isEmpty(a: V): Boolean = a == empty

  /** Apply the measure to a `V`
    *
    * @param a
    * @return
    */
  def measure(a: A): V

  /** Combine two measures.
    *
    * This implements the monoid multiplication (⊗) morphism.
    *
    * @param a
    * @param b
    * @return
    */
  def combine(a: V, b: V): V

  /** The combine (⊗) operator.
    *
    * @param a
    * @param b
    * @return
    */
  @inline final def |+|(a: V, b: V): V = combine(a, b)

  /** The combine (⊗) operator for varargs.
    *
    * @param xs
    * @return
    */
  @throws[IllegalArgumentException]("if no arguments are passed")
  @inline final def |+|(xs: V*)
    = { require(xs.nonEmpty); xs reduce |+| }

  /** The combine (⊗) operator for people who like using crazy unicode
    * symbols in their code.
    *
    * @param a
    * @param b
    * @return
    */
  @inline final def ⊗(a: V, b: V): V  = combine(a, b)

  /** The combine (⊗) operator for varargs, for people who like using crazy
    * unicode symbols in their code.
    *
    * @param xs
    * @return
    */
  @throws[IllegalArgumentException]("if no arguments are passed")
  @inline final def ⊗(xs: V*)
    = { require(xs.nonEmpty); xs reduce ⊗ }

  @inline final def apply(a: A): V = measure(a)

}
