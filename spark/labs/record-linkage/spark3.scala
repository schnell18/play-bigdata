import java.lang.Double.isNaN
:load spark2.scala
val parsed = noheader.map(x => parse2(x))
parsed.map(md => md.scores(0)).filter(!isNaN(_)).stats()
val stats = (0 until 9).map(i => {
parsed.map(md => md.scores(i)).filter(!isNaN(_)).stats()
})
:load StatsWithMissing.scala
:load StatsWithMissing.scala
val nas1 = NAStatCounter(10.0)
nas1.add(2.1)
nas1.add(2.1)
nas1.add(2.1)
nas1.add(2.1)
nas1.add(2.1)
nas1.add(2.1)
val nas2 = NAStatCounter(Double.NaN)
nas1.merge(nas2)
nas1.merge(nas2)
nas1.merge(nas2)
nas1.merge(nas2)
val arr = Array(1.0, Double.NaN, 17.29)
val nas = arr.map(d => NAStatCounter(d))
parsed
val nasRDD = parsed.map(md => { md.scores.map(d => NAStatCounter(d)) })
val nas1 = Array(1.0, Double.NaN).map(d => NAStatCounter(d))
val nas2 = Array(Double.NaN, 2.0).map(d => NAStatCounter(d))
val merged = nas1.zip(nas2).map(p => p._1.merge(p._2))
val merged = nas1.zip(nas2).map {case (a,b) => a.merge(b)}
val nas = List(nas1, nas2)
val merged = nas.reduce((n1, n2) => {
n1.zip(n2).map { case (a, b) => a.merge(b) }
})
val reduced = nasRDD.reduce((n1, n2) => {
    n1.zip(n2).map { case (a, b) => a.merge(b) }
})
reduced.foreach(println)
nasRDD
