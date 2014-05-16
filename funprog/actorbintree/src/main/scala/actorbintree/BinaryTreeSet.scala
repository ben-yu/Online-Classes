/**
 * Copyright (C) 2009-2013 Typesafe Inc. <http://www.typesafe.com>
 */
package actorbintree

import akka.actor._
import scala.collection.immutable.Queue

object BinaryTreeSet {

  trait Operation {
    def requester: ActorRef
    def id: Int
    def elem: Int
  }

  trait OperationReply {
    def id: Int
  }

  /** Request with identifier `id` to insert an element `elem` into the tree.
    * The actor at reference `requester` should be notified when this operation
    * is completed.
    */
  case class Insert(requester: ActorRef, id: Int, elem: Int) extends Operation

  /** Request with identifier `id` to check whether an element `elem` is present
    * in the tree. The actor at reference `requester` should be notified when
    * this operation is completed.
    */
  case class Contains(requester: ActorRef, id: Int, elem: Int) extends Operation

  /** Request with identifier `id` to remove the element `elem` from the tree.
    * The actor at reference `requester` should be notified when this operation
    * is completed.
    */
  case class Remove(requester: ActorRef, id: Int, elem: Int) extends Operation

  /** Request to perform garbage collection*/
  case object GC

  /** Holds the answer to the Contains request with identifier `id`.
    * `result` is true if and only if the element is present in the tree.
    */
  case class ContainsResult(id: Int, result: Boolean) extends OperationReply
  
  /** Message to signal successful completion of an insert or remove operation. */
  case class OperationFinished(id: Int) extends OperationReply

}


class BinaryTreeSet extends Actor {
  import BinaryTreeSet._
  import BinaryTreeNode._

  def createRoot: ActorRef = context.actorOf(BinaryTreeNode.props(0, initiallyRemoved = true))

  var root = createRoot

  // optional
  var pendingQueue = Queue.empty[Operation]

  // optional
  def receive = normal

  // optional
  /** Accepts `Operation` and `GC` messages. */
  val normal: Receive = {
    case msg: Operation => root ! msg
  }

  // optional
  /** Handles messages while garbage collection is performed.
    * `newRoot` is the root of the new binary tree where we want to copy
    * all non-removed elements into.
    */
  def garbageCollecting(newRoot: ActorRef): Receive = ???

}

object BinaryTreeNode {
  trait Position

  case object Left extends Position
  case object Right extends Position

  case class CopyTo(treeNode: ActorRef)
  case object CopyFinished

  def props(elem: Int, initiallyRemoved: Boolean) = Props(classOf[BinaryTreeNode],  elem, initiallyRemoved)
}

class BinaryTreeNode(val elem: Int, initiallyRemoved: Boolean) extends Actor {
  import BinaryTreeNode._
  import BinaryTreeSet._

  var subtrees = Map[Position, ActorRef]()
  var removed = initiallyRemoved

  // optional
  def receive = normal

  // optional
  /** Handles `Operation` messages and `CopyTo` requests. */
  val normal: Receive = {
    case msg @ Contains(requester,id,v) => {
      println(s"Contains: (id: $id, elem: $v, this.elem: $elem)")
      if (elem == v) {
        if (removed == false) requester ! ContainsResult(id, true)
        else requester ! ContainsResult(id, false)
      } else if (elem > v) {
        subtrees.get(Left) match {
          case Some(left) => left ! msg
          case None => requester ! ContainsResult(id, false)
        }
      } else if (elem < v) {
        subtrees.get(Right) match {
          case Some(right) => right ! msg
          case None => requester ! ContainsResult(id, false)
        }
      }
    }
    
    case msg @ Insert(requester, id, v) => {
      println(s"Insert: (id: $id, elem: $v, this.elem: $elem)")
      if (elem == v) {
        removed = false
        requester ! OperationFinished(id)
      } else if (elem > v) {
        subtrees.get(Left) match {
          case Some(left) => left ! msg
          case None => {
            // Make a new BinaryTreeNode on the Left that is a child of current node
            subtrees = subtrees.updated(Left, context.actorOf(props(v, false)))
            requester ! OperationFinished(id)
          }
        }
      } else if (elem < v) {
        subtrees.get(Right) match {
          case Some(right) => right ! msg
          case None => {
            // Make a new BinaryTreeNode on the Right that is a child of current node
            subtrees = subtrees.updated(Right, context.actorOf(props(v, false)))
            requester ! OperationFinished(id)
          }
        }
      }
    }
    
    case msg @ Remove(requester, id, v) => {
      println(s"Remove: (id: $id, elem: $v, this.elem: $elem)")
      if (elem == v) {
        if (removed == false) removed = true; 
        requester ! OperationFinished(id)
      } else if (elem > v) {
        subtrees.get(Left) match {
          case Some(left) => left ! msg
          case None => requester ! OperationFinished(id)
        }
      } else if (elem < v) {
        subtrees.get(Right) match {
          case Some(right) => right ! msg
          case None => requester ! OperationFinished(id)
        }
      }      
    }
  }

  // optional
  /** `expected` is the set of ActorRefs whose replies we are waiting for,
    * `insertConfirmed` tracks whether the copy of this node to the new tree has been confirmed.
    */
  def copying(expected: Set[ActorRef], insertConfirmed: Boolean): Receive = ???

}
