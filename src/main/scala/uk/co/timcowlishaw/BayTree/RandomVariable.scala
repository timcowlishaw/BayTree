package uk.co.timcowlishaw.BayTree;
import scala.collection.immutable.HashMap

object RandomVariable {
  def uniform[T](set : Set[T]) : RandomVariable[T] = new RandomVariable(set map {(_,1.0)});
  // Some more discrete distributions!
  //def binomial;
  //def zeta
  //def bernoulli(1, 2);
}

class RandomVariable[T](private val members: Set[Tuple2[T, Double]]) {
  private val total = (0.0 /: members) { (total, pair) => 
    val (_, prob) = pair
    total + prob
  }
  private val dist = (new HashMap[T, Double] /: members) { (hash, pair)  => 
    val (item, prob) = pair
    hash + ((item, prob / total))
  }

  def p(v:T) : Double = dist.getOrElse(v, 0)

  //def c(v:T) : Double // REQUIRES ORDERING OVER MEMBER VALUES - Iterable rather than Set for EG.

  def p(f : T => Boolean) : Double =  {
    val chosen = elements filter f;
    (0.0 /: chosen) { (total, element) => total + p(element) }
  }

  def elements : Set[T] = dist.keySet;

  def and[U](other : RandomVariable[U]) : RandomVariable[(T,U)] = {
    val pairs = for {
      i <- elements
      j <- other.elements
    } yield ((i,j), p(i) * other.p(j));
    return new RandomVariable(pairs);
  }
}
