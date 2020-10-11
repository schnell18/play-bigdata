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
noheader.take(10).foreach(println)
