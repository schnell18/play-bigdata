import java.lang.Double.isNaN

case class MatchData(
    id1: Int,
    id2: Int,
    scores: Array[Double],
    matched: Boolean
)

def isHeader(line: String) = line.contains("id_1")

def toDouble(s: String) = {
    if ("?".equals(s)) Double.NaN else s.toDouble
}

def parse2(line: String) = {
    val pieces = line.split(',')
    val id1 = pieces(0).toInt
    val id2 = pieces(1).toInt
    val scores = pieces.slice(2, 11).map(toDouble)
    val matched = pieces(11).toBoolean
    MatchData(id1, id2, scores, matched)
}

:load StatsWithMissing.scala
val rawblocks = sc.textFile("linkage")
val noheader = rawblocks.filter(!isHeader(_))
val parsed = noheader.map(x => parse2(x))

val reduced = statsWithMissing(parsed.map(m => m.scores))
reduced.foreach(println)
