package uk.co.timcowlishaw.BayTree;
import org.specs2.mutable._;

class NodeSpec extends Specification {
  "Creating a node with a given parent" should {
    "add the node to the parents children" in {
      val n = new Node(None)
      val n2 = new Node(n)
      n.children must contain(n2)
    }
    "ensure existing children remain" in {
      val n = new Node(None)
      val n2 = new Node(n)
      val n3 = new Node(n)
      n.children must contain(n2, n3)
    }
  }
}
