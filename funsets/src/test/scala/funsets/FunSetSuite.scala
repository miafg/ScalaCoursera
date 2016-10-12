package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(1)
  }
  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s1, 3), "Singleton s1 does not contain 3")
      assert(contains(s2, 2), "Singleton s2")
      assert(contains(s3, 3), "Singleton s3")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersection empty of two sets") {
    new TestSets {
      val intersection = intersect(s1, s2)
      assert(!contains(intersection,4), "set should not contain 4")
      assert(!contains(intersection, 1), "set should not contain 1")
      assert(!contains(intersection, 2), "set should not contain 2")
    }
  }

  test("intersect contains intersection of sets") {
    new TestSets {
      val s1_s4 = intersect(s1, s4)
      assert(contains(s1_s4, 1), "set should contain 1")
      assert(!contains(s1_s4, 2), "set should not contain 2")
    }
  }

  test("difference between sets") {
    new TestSets {
      val dif = diff(s1, s2)
      assert(contains(dif, 1), "set should contain 1")
      assert(contains(dif, 2), "set should contain 2")
      assert(!contains(dif, 3), "set should not contain 3")
    }
  }

  test("filter set") {
    new TestSets {
      val uni = union(s1, union(s2, s3))
      val filtered = filter(uni, x => x > 2)
      assert(!contains(filtered, 1), "set should not contain 1")
      assert(!contains(filtered, 2), "set should not contain 2")
      assert(contains(filtered, 3), "set should not contain 3")
    }
  }

  test("for all") {
    new TestSets {
      val uni = union(s1, union(s2, s3))
      assert(forall(uni, x => x > 0), "all values in set are positive")
      assert(!forall(uni, x => x % 2 == 0), "not all values in set are even")
    }
  }

  test("exists") {
    new TestSets {
      val uni = union(s1, union(s2, s3))
      assert(exists(uni, x => x % 3 == 0), "there exists a value that satisfies % 3 == 0")
      assert(!exists(uni, x => x > 4), "there does not exist a value that is greater than 4")
    }
  }

  test("map") {
    new TestSets {
      val uni = union(s1, union(s2, s3))
      val mapped = map(uni, x => x + 1)
      assert(contains(mapped, 2), "map should contain 2")
      assert(contains(mapped, 3), "map should contain 3")
      assert(contains(mapped, 4), "map should contain 4")
      assert(!contains(mapped, 1), "map should not contain 1")
      assert(!contains(mapped, 5), "map should not contain 5")
    }
  }

}
