package weekTwo

object functionalSets {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  type Set = Int => Boolean
  
  def contains(s: Set, elem: Int) = s(elem)       //> contains: (s: Int => Boolean, elem: Int)Boolean
  def singletonSet(elem: Int) = Set(elem)         //> singletonSet: (elem: Int)scala.collection.immutable.Set[Int]
  def union(s: Set, t: Set): Set = elem => s(elem) || t(elem)
                                                  //> union: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
  def intersect(s: Set, t: Set): Set = elem => s(elem) && t(elem)
                                                  //> intersect: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
  def diff(s: Set, t: Set): Set = elem => s(elem) && !t(elem)
                                                  //> diff: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
 	def filter(s: Set, p: Int => Boolean): Set = elem => s(elem) && p(elem)
                                                  //> filter: (s: Int => Boolean, p: Int => Boolean)Int => Boolean
 	
  def forall(s: Set, p: Int => Boolean): Boolean = {
	 def iter(a: Int): Boolean = {
	   if (a > 1000) true
	   else if (s(a))  p(a) && iter(a+1)
	   else iter(a+1)
		}
 		iter(-1000)
 	}                                         //> forall: (s: Int => Boolean, p: Int => Boolean)Boolean
 	
 	forall(Set(1,2,3,4,5), x => x <= 5)       //> res0: Boolean = true
 	
 	def exists(s: Set, p: Int => Boolean): Boolean = {
	 def iter(a: Int): Boolean = {
	   if (a > 1000) false
	   else if (s(a))  p(a) || iter(a+1)
	   else iter(a+1)
		}
 		iter(-1000)
 	}                                         //> exists: (s: Int => Boolean, p: Int => Boolean)Boolean
 	
 	exists(Set(1,2,3,4,5), x => x == 6)       //> res1: Boolean = false
 	
 	
 	def map(s: Set, f: Int => Int): Set = {
 		elem => exists(s,elem2 => f(elem2) == elem)
 	}                                         //> map: (s: Int => Boolean, f: Int => Int)Int => Boolean
 	
 	contains(map(Set(1,2,3,4,5), x => x * 2),9)
                                                  //> res2: Boolean = false
 	contains(map(Set(1,2,3,4,5), x => x * 2),10)
                                                  //> res3: Boolean = true
}