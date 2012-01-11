package uk.co.timcowlishaw.BayTree;
import org.specs2.mutable._;
import org.specs2.specification._;

class RandomVariableSpec extends Specification {

  "Creating a uniformly distributed random variable" should {
    "return a random variable with the same probability for each element in the set" in {
      val values = Set(1, 2, 3); 
      val rv = RandomVariable.uniform(values);
      values.map { rv.p(_) }.size mustEqual 1
    }
  }

  "Getting the probability for an element" should {
    
    "return the value given for the item divided by the total value of every item in the set, if the item is in the random variable's set" in new randomVariable {
      rv.p(1) mustEqual 1.0/3
    }

    "return 0 if the element is not in the random variable's set" in new randomVariable {
      rv.p(4) mustEqual 0
    }

    "return values that add up to 1 for all elements in the set" in new randomVariable {
      (0.0 /: rv.elements) { (total, element) => total + rv.p(element) } mustEqual 1.0
    }

  }

  "Getting the probability for all elements matching a predicate" should {
    "return the sum of the probabilities of each item" in new randomVariable {
      rv.p { s => Seq(1, 2) contains s } mustEqual 2.0/3
    }
  }

  "Getting the cumulative probability for an element" should {
    "return the sum of all the probabilities of each item less than or equal to this one" in new randomVariable {
      rv.c(2) mustEqual 2.0/3
    }
  }

  "Getting the elements" should {
    "return a set of all elements in the random variable" in new randomVariable {
      rv.elements mustEqual Set(1, 2, 3)
    }
  }

  "The joint distribution of two random variables" should {
    "have an elements set containing the product of both random variables' elements" in new jointRandomVariable{
      jointRv.elements mustEqual elements
    }

    "return the product of the probabilities of each element picked from either set" in new jointRandomVariable {
      elements foreach { case pair@(i, j) => 
        jointRv.p(pair) mustEqual (rv1.p(i) * rv2.p(j)) 
      } 
    }

    "have values that add up to 1 for all elements in the set" in new jointRandomVariable {
      (0.0 /: jointRv.elements) { (total, element) => total + jointRv.p(element) } mustEqual 1.0

    }
  }

  trait randomVariable extends Scope {
    val rv : RandomVariable[Int] = new RandomVariable(Set((1, 1), (2, 1), (3, 1)))
  }

  trait jointRandomVariable extends Scope {
    val rv1 : RandomVariable[Int] = RandomVariable.uniform(Set(1, 2, 3));
    val rv2 : RandomVariable[String] = RandomVariable.uniform(Set("Ay", "Bee", "Cee", "Dee"));

    val jointRv = rv1 and rv2;
    
    val elements : Set[Tuple2[Int, String]] = for {
      i <- rv1.elements
      j <- rv2.elements
    } yield (i,j);


  }

}

