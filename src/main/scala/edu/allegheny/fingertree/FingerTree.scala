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

  /** Tests whether this `FingerTree` is empty.
    *
    *  @return `true` if the `FingerTree` contains no elements,
    *          `false` otherwise.
    */
  def isEmpty: Boolean

  /** Tests whether this `FingerTree` is non-empty.
    *
    *  @return `true` if the `FingerTree` contains elements, `false` otherwise.
    */
  @inline final def nonEmpty: Boolean = !isEmpty

  /** Prepend an element.
    *
    * Prepends an element to this finger tree, returning a copy with that
    * element prepended.
    *
    *  @param  x  the prepended element
    *  @param  m  a [[Measure]] to use when updating the tree's structure
    *  @tparam A1 the element type of the prepended element
    *  @return    a new collection of type `FingerTree[V, A1]` consisting of `x`
    *             followed by all elements of this `FingerTree`.
    *
    */
  def prepend[A1 >: A](x: A1)(implicit m: Measure[V, A1]): FingerTree[V, A1]

  /** Append an element.
    *
    * Appends an element to this finger tree, returning a copy with that
    * element appended.
    *
    *  @param  x   the appended element
    *  @param  m   a [[Measure]] to use when updating the tree's structure
    *  @tparam A1  the element type of the appended element
    *  @return     a new collection of type `FingerTree[V, A1]` consisting of
    *              all the elements of this `FingerTree` followed by `x`.
    *
    */
  def append[A1 >: A](x: A1)(implicit m: Measure[V, A1]): FingerTree[V, A1]

  /** A copy of this `FingerTree` with an element prepended.
    *
    *  @param  x  the prepended element
    *  @param  m  a [[Measure]] to use when updating the tree's structure
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
  @inline def +:[A1 >: A](x: A1)(implicit m: Measure[V, A1]): FingerTree[V, A1]
    = prepend(x)

  /** A copy of this `FingerTree` with an element appended.
    *
    *  @param  x   the appended element
    *  @param  m   a [[Measure]] to use when updating the tree's structure
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
  @inline def :+[A1 >: A](x: A1)(implicit m: Measure[V, A1]): FingerTree[V, A1]
    = append(x)

  /** Selects all elements except the last.
    *  @param m a [[Measure]] to use when updating the tree's structure
    *  @return  a `FingerTree` consisting of all elements of this `FingerTree`
    *           except the last one.
    *  @throws UnsupportedOperationException if the `FingerTree` is empty.
    */
  def init(implicit m: Measure[V, A]): Self

  /** Selects all elements except the first.
    *  @return  a `FingerTree` consisting of all elements of this `FingerTree`
    *           except the first one.
    *  @throws NoSuchElementException if the `FingerTree` is empty.
    */
  def tail(implicit m: Measure[V, A]): Self

  /** Selects the first element of this `FingerTree`.
    *  @return  the first element of this `FingerTree`.
    *  @throws NoSuchElementException if the `FingerTree` is empty.
    */
  def head: A

  /** Optionally selects the first element.
    *
    *  @return  the first element of this `FingerTree` if it is non-empty,
    *           `None` if it is empty.
    */
  @inline def headOption: Option[A]
    = if (nonEmpty) Some(head) else None

  /** Selects the last element of this `FingerTree`.
    *  @return  the last element of this `FingerTree`.
    *  @throws NoSuchElementException if the `FingerTree` is empty.
    */
  def last: A

  /** Optionally selects the last element.
    *
    *  @return  the first element of this `FingerTree` if it is non-empty,
    *           `None` if it is empty.
    */
  @inline def lastOption: Option[A]
    = if (nonEmpty) Some(last) else None

  /** @return A new iterator over the elements of this `FingerTree`
    */
  def iterator: Iterator[A]

  /** @return A `List` containing the elements of this `FingerTree`
    */
  def toList: List[A]


}