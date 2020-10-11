val rawblocks = sc.textFile("linkage")
val rawblocks = sc.textFile("linkage")
rawblocks.first
val head = rawblocks.take(10)
head.length
head.foreach(println)
def isHeader(line: String) = line.contains("id_1")
head.filterNot(isHeader)
head.filterNot(isHeader).foreach(println)
head.filter(x => !isHeader(x))
head.filter(x => !isHeader(x)).length
head.filter(!isHeader(_))
val noheader = rawblocks.filter(!isHeader(_))
def toDouble(s: String) = {
    if ("?".equals(s)) Double.NaN else s.toDouble
}
val head = noheader.take(10)
val line = head(5)
val pieces = line.split(',')
val id1 = pieces(0).toInt
val id2 = pieces(1).toInt
val matched = pieces(11).toBoolean
val rawscores = pieces.slice(2, 11)
rawscores.map(toDouble)
def parse(line: String) = {
val pieces = line.split(',')
val id1 = pieces(0).toInt
val id2 = pieces(1).toInt
val scores = pieces.slice(2, 11).map(toDouble)
val matched = pieces(11).toBoolean
(id1, id2, scores, matched)
}
val tup = parse(line)
tup._1

case class MatchData(
    id1: Int,
    id2: Int,
    scores: Array[Double],
    matched: Boolean
)

def parse2(line: String) = {
    val pieces = line.split(',')
    val id1 = pieces(0).toInt
    val id2 = pieces(1).toInt
    val scores = pieces.slice(2, 11).map(toDouble)
    val matched = pieces(11).toBoolean
    MatchData(id1, id2, scores, matched)
}

val md = parse2(line)
