package uk.co.timcowlishaw.BayTree;
import scala.collection.mutable.HashSet;

object Node {
  implicit def node2option(node : Node) : Option[Node] = Some(node);
}

class Node(val parent : Option[Node]) {
  parent map { _.children += this }
  var children : HashSet[Node] = new HashSet[Node]();
}
