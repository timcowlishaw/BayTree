package uk.co.timcowlishaw.BayTree;
import scala.collection.immutable.TreeMap
import scala.collection.immutable.SortedSet
import scala.collection.immutable.Set


object RandomVariable {

  def uniform[T](set : Set[T])(implicit ev : Ordering[T]) : RandomVariable[T] = {
    new RandomVariable(set map { (_, 1.0) });
  }  
    // Some more discrete distributions!
    //def binomial;
    //def zeta
    //def bernoulli(1, 2);
}

class RandomVariable[T] (private val members: Set[(T, Double)]) (implicit ev : Ordering[T]) {

  private val total = (0.0 /: members) { (total, pair) => 
    val (_, prob) = pair
    total + prob
  }

  private val dist = (new TreeMap[T, Double] /: members) { (map, pair) =>
    val (item, prob) = pair
    map + ((item, prob / total))
  }

  def p(v:T) : Double = dist.getOrElse(v, 0)

  def c(v:T) : Double = (0.0 /: dist.filterKeys { ev.lteq(_, v) }) { (sum, pair) =>
    val (_, prob) = pair
    sum + prob
  }

  def p(f : T => Boolean) : Double =  {
    val chosen = elements filter f;
    (0.0 /: chosen) { (total, element) => total + p(element) }
  }

  def elements : SortedSet[T] = dist.keySet;
  
  def and[U](other : RandomVariable[U])(implicit ev : Ordering[(T,U)]) : RandomVariable[(T,U)] = {
    val pairs = for {
      i <- elements
      j <- other.elements
    } yield ((i,j), p(i) * other.p(j));
    return new RandomVariable(pairs);
  }
}
