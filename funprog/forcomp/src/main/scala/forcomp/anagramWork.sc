package forcomp

import Anagrams._

object anagramWork {
    val sentence = List("ab", "ba")               //> sentence  : List[String] = List(ab, ba)
    val anas = List(
      List("Rex", "Lin", "Zulu"),
      List("nil", "Zulu", "Rex"),
      List("Rex", "nil", "Zulu"),
      List("Zulu", "Rex", "Lin"),
      List("null", "Uzi", "Rex"),
      List("Rex", "Zulu", "Lin"),
      List("Uzi", "null", "Rex"),
      List("Rex", "null", "Uzi"),
      List("null", "Rex", "Uzi"),
      List("Lin", "Rex", "Zulu"),
      List("nil", "Rex", "Zulu"),
      List("Rex", "Uzi", "null"),
      List("Rex", "Zulu", "nil"),
      List("Zulu", "Rex", "nil"),
      List("Zulu", "Lin", "Rex"),
      List("Lin", "Zulu", "Rex"),
      List("Uzi", "Rex", "null"),
      List("Zulu", "nil", "Rex"),
      List("rulez", "Linux"),
      List("Linux", "rulez")
    )                                             //> anas  : List[List[String]] = List(List(Rex, Lin, Zulu), List(nil, Zulu, Rex)
                                                  //| , List(Rex, nil, Zulu), List(Zulu, Rex, Lin), List(null, Uzi, Rex), List(Rex
                                                  //| , Zulu, Lin), List(Uzi, null, Rex), List(Rex, null, Uzi), List(null, Rex, Uz
                                                  //| i), List(Lin, Rex, Zulu), List(nil, Rex, Zulu), List(Rex, Uzi, null), List(R
                                                  //| ex, Zulu, nil), List(Zulu, Rex, nil), List(Zulu, Lin, Rex), List(Lin, Zulu, 
                                                  //| Rex), List(Uzi, Rex, null), List(Zulu, nil, Rex), List(rulez, Linux), List(L
                                                  //| inux, rulez))
    
	dictionaryByOccurrences.get(List(('i', 1), ('l', 1), ('n', 1)))
                                                  //> res0: Option[List[forcomp.Anagrams.Word]] = Some(List(Lin, nil))
	combinations(sentenceOccurrences(sentence))
                                                  //> res1: List[forcomp.Anagrams.Occurrences] = List(List(), List((a,1)), List((a
                                                  //| ,1), (b,1)), List((a,1), (b,2)), List((a,2)), List((a,2), (b,1)), List((a,2)
                                                  //| , (b,2)), List((b,1)), List((b,2)))
}