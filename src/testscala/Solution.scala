package testscala

object Solution {

  def main(args: Array[String]) = {
    val m = 3
    val grid = new Array[String](m)
//    grid.update(0, "---")
//    grid.update(1, "-m-")
//    grid.update(2, "p--")
    grid.update(0, "--p")
    grid.update(1, "-m-")
    grid.update(2, "---")
    princess(m, grid)
  }


  def princess(m: Int, grid: Array[String]) = {
    var mRow = -1
    var mCol = -1
    for (row <- grid.indices) {
      if (grid(row).indexOf("m") >= 0) {
        mRow = row
        mCol = grid(row).indexOf("m")
      }
    }

    var pRow = -1
    var pCol = -1
    for (row <- grid.indices) {
      if (grid(row).indexOf("p") >= 0) {
        pRow = row
        pCol = grid(row).indexOf("p")
      }
    }

    val r = pRow - mRow
    if (r > 0) {
      (0 until r).foreach(x => println("DOWN"))
    } else if (r < 0) {
      (0 until Math.abs(r)).foreach(x => println("UP"))
    }

    val c = pCol - mCol
    if (c > 0) {
      (0 until c).foreach(x => println("RIGHT"))
    } else if (c < 0) {
      (0 until Math.abs(c)).foreach(x => println("LEFT"))
    }

  }

}
