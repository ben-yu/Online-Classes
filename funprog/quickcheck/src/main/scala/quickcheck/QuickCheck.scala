package quickcheck

import common._
import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._
import math._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }
  
  property("min2") = forAll { (a: Int, b: Int) =>
    val h = insert(b, insert(a, empty))
    findMin(h) == min(a,b)
  }
    
  property("deleteMin") = forAll { a: Int =>
    val h = deleteMin(insert(a, empty))
    h == empty
  }
  
  property("minMeld") = forAll { (a: H, b: H) =>
    val h = findMin(meld(a, b))
    h == min(findMin(a),findMin(b))
  }
  
  def deleteHeap(h:H): H = {
    if(isEmpty(h)) h else deleteHeap(deleteMin(h))
  }
  
  property("deleteHeap") = forAll { a: H =>
    val h = deleteHeap(a)
    h == empty
  }
  
  def equalHeap(a:H,b:H): Boolean = {
    if(isEmpty(a) && isEmpty(b)) true
    else findMin(a) == findMin(b) && equalHeap(deleteMin(a),deleteMin(b))
  }
  
  property("meld") = forAll { (a: H, b: H) =>
    equalHeap(meld(a,b),meld(deleteMin(a),insert(findMin(a),b)))
  }

  lazy val genHeap: Gen[H] = for {
	v <- arbitrary[A]
	m <- oneOf(empty, genHeap)
  } yield insert(v,m)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)
}
