package recfun

object Main {
  def main(args: Array[String]) {
    /*println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }*/


  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      //base case, if the parent doesn't exist
      if (c < 0 || c > r + 1) {
        return 0;
      }
      //base case, if the parent is an edge
      if (c == 0 || c == r) {
        return 1;
      }
      //return the sum of the parents
      return pascal(c - 1, r - 1) + pascal(c, r - 1);
    }
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      //function that counts pairs of parentheses
      def countParens(chars: List[Char], count: Int): Int = {
        //list is empty
        if (chars.length == 0) return count;
        //count is negative, mismatching parens
        if (count < 0) return count;
        if (chars.head == '(') {
          //increment count
          return countParens(chars.drop(1), count + 1);
        } else if (chars.head == ')') {
          //decrement count
          return countParens(chars.drop(1), count - 1);
        }
        return countParens(chars.drop(1), count);
      }
      //if pairs is 0 then there are all matching pairs
      var pairs = countParens(chars, 0);
      if (pairs == 0) {
        return true;
      }
      return false;
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = ???
  }
