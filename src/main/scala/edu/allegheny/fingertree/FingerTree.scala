package edu.allegheny.fingertree

/** Trait defining [[FingerTree]] operations
  *
  * @tparam V the type of the measure
  * @tparam A the type of the elements stored in the tree
  *
  * @author Hawk Weisman
  * @author Aubrey Collins
  *
  * Created by hawk on 3/13/16.
  */
sealed trait FingerTree[V, +A] {

  protected[this] type Self = FingerTree[V, A]

  /** Prepend an element.
    *
    * Prepends an element to this finger tree, returning a copy with that
    * element prepended.
    *
    * @param  x  the prepended element
    * @tparam A1 the element type of the prepended element
    * @return    a new collection of type `FingerTree[V, A1]` consisting of `x`
    *            followed by all elements of this `FingerTree`.
    *
    */
  def prepend[A1 >: A](x: A1)(implicit m: Measure[V, A1]): FingerTree[V, A1]

  /** Append an element.
    *
    * Appends an element to this finger tree, returning a copy with that
    * element appended.
    *
    *  @param  x   the appended element
    *  @tparam A1  the element type of the appended element
    *  @return     a new collection of type `FingerTree[V, A1]` consisting of
    *              all the elements of this `FingerTree` followed by `x`.
    *
    */
  def append[A1 >: A](x: A1)(implicit m: Measure[V, A1]): FingerTree[V, A1]

  /** A copy of this `FingerTree` with an element prepended.
    *
    *  @param  x  the prepended element
    *  @tparam A1 the element type of the prepended element
    *  @return    a new collection of type `FingerTree[V, A1]` consisting of `x`
    *             followed by all elements of this `FingerTree`.
    *
    *  Note that `:`-ending operators are right associative.
    *  A mnemonic for `+:` vs. `:+` is: the COLon goes on the COLlection side.
    *
    *  Also, the original `FingerTree` is not modified, so you will want to
    *  capture the result.
    */
  def +:[A1 >: A](x: A1)(implicit m: Measure[V, A1]): FingerTree[V, A1]
    = prepend(x)

  /** A copy of this `FingerTree` with an element appended.
    *
    *  @param  x   the appended element
    *  @tparam A1  the element type of the appended element
    *  @return     a new collection of type `FingerTree[V, A1]` consisting of
    *              all the elements of this `FingerTree` followed by `x`.
    *
    *  Note that `:`-ending operators are right associative.
    *  A mnemonic for `+:` vs. `:+` is: the COLon goes on the COLlection side.
    *
    *  Also, the original `FingerTree` is not modified, so you will want to
    *  capture the result.
    */
  def :+[A1 >: A](x: A1)(implicit m: Measure[V, A1]): FingerTree[V, A1]
    = append(x)

  def init(implicit m: Measure[V, A]): Self
  def head(implicit m: Measure[V, A]): Self

}