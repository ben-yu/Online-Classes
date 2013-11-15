package weekOne

object sqrt {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  1+2                                             //> res0: Int(3) = 3
 	def abs(x: Double) = if (x<0) -x else x   //> abs: (x: Double)Double
  
  def sqrt(x: Double) = {
	  
	  def sqrtIter(guess: Double): Double =
	  	if (isGoodEnough(guess)) guess
	  	else sqrtIter(improve(guess))
	  	
	  def isGoodEnough(guess: Double) =
	  	abs(guess * guess - x) / x < 0.01
	  	
	  def improve(guess: Double) =
	  	(guess + x / guess) /2
	  	
	  sqrtIter(1.0)
  }                                               //> sqrt: (x: Double)Double
  
  sqrt(2)                                         //> res1: Double = 1.4166666666666665
  sqrt(4)                                         //> res2: Double = 2.000609756097561
  sqrt(1e60)                                      //> res3: Double = 1.0000788456669446E30
  
  
  
 	println("show pascals triangle")          //> show pascals triangle
 
  
  def pascal(c: Int, r: Int): Int = {
  	if (c > r || c < 0 || r < 0) 0
  	else if (c == 0) 1
  	else pascal(c,r-1) + pascal(c-1,r-1)
  }                                               //> pascal: (c: Int, r: Int)Int
  
  pascal(1,0)                                     //> res4: Int = 0
  pascal(0,0)                                     //> res5: Int = 1
  pascal(0,2)                                     //> res6: Int = 1
  pascal(1,1)                                     //> res7: Int = 1
  pascal(0,2)                                     //> res8: Int = 1
  pascal(1,2)                                     //> res9: Int = 2
  pascal(2,2)                                     //> res10: Int = 1
  pascal(0,3)                                     //> res11: Int = 1
  pascal(1,3)                                     //> res12: Int = 3
  pascal(2,3)                                     //> res13: Int = 3
  pascal(3,3)                                     //> res14: Int = 1
  
  
  def balance(chars: List[Char]): Boolean = {
  	def balanceCheck(chars: List[Char], count : Int): Boolean = {
  	    if(chars.isEmpty) count == 0
    		else if (chars.head == '(') balanceCheck(chars.tail,count+1)
    		else if (chars.head == ')') balanceCheck(chars.tail,count-1)
    		else balanceCheck(chars.tail,count)
  	}

		balanceCheck(chars,0)

  }                                               //> balance: (chars: List[Char])Boolean
  
  balance("()()()(faewfaewf)".toList)             //> res15: Boolean = true
  
  def countChange(money: Int, coins: List[Int]): Int = {
  	if(money == 0) 1
  	else if (money < 0 || coins.isEmpty) 0
  	else countChange(money,coins.tail) + countChange(money - coins.head,coins)
  }                                               //> countChange: (money: Int, coins: List[Int])Int
  
  countChange(100,List(1,5,10,25,50))             //> res16: Int = 292
}