import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Jackson Jiang
 * @author Jimmy Yuan
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    @Test
    public final void testHasKeyEmptyFalse() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();

        /*
         * Call method under test
         */
        boolean result = m.hasKey("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(result, false);
    }

    @Test
    public final void testHasKeyOneFalse() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2", "2");
        Map<String, String> mExpected = this.createFromArgsRef("2", "2");

        /*
         * Call method under test
         */
        boolean result = m.hasKey("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(result, false);
    }

    @Test
    public final void testHasKeyOneTrue() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("1", "2");
        Map<String, String> mExpected = this.createFromArgsRef("1", "2");

        /*
         * Call method under test
         */
        boolean result = m.hasKey("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(result, true);
    }

    @Test
    public final void testHasKeyMoreThanOneFalse() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2", "2", "3.3", "3.3");
        Map<String, String> mExpected = this.createFromArgsRef("2", "2", "3.3",
                "3.3");

        /*
         * Call method under test
         */
        boolean result = m.hasKey("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(result, false);
    }

    @Test
    public final void testHasKeyMoreThanOneTrue() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("1", "2", "a", "b");
        Map<String, String> mExpected = this.createFromArgsRef("1", "2", "a",
                "b");

        /*
         * Call method under test
         */
        boolean result = m.hasKey("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(result, true);
    }

    @Test
    public final void testRemoveToEmptyTest() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("mee", "moo");
        Map<String, String> mExpected = this.createFromArgsRef("mee", "moo");
        Pair<String, String> removedExpected = mExpected.remove("mee");
        /*
         * Call method under test
         */
        Map.Pair<String, String> removed = m.remove("mee");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(removed, removedExpected);
    }

    @Test
    public final void testRemoveToOneTest() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("mee", "moo", "shee",
                "shoo");
        Map<String, String> mExpected = this.createFromArgsRef("mee", "moo",
                "shee", "shoo");
        Pair<String, String> removedExpected = mExpected.remove("shee");
        /*
         * Call method under test
         */
        Map.Pair<String, String> removed = m.remove("shee");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(removed, removedExpected);
    }

    @Test
    public final void testRemoveToMoreThanOneTest() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("mee", "moo", "shee",
                "shoo", "1", "2");
        Map<String, String> mExpected = this.createFromArgsRef("mee", "moo",
                "shee", "shoo", "1", "2");
        Pair<String, String> removedExpected = mExpected.remove("shee");
        /*
         * Call method under test
         */
        Map.Pair<String, String> removed = m.remove("shee");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(removed, removedExpected);
    }

    @Test
    public final void testSizeOne() {
        /*
         * Set up variables
         */
        Map<String, String> testPair = this.createFromArgsTest("mee", "moop");
        Map<String, String> testPairExpected = this.createFromArgsRef("mee",
                "moop");
        int expectedSize = 1;
        /*
         * Call method under test
         */
        int size = testPair.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, expectedSize);
        assertEquals(testPair, testPairExpected);
    }

    @Test
    public final void testSizeMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> testPair = this.createFromArgsTest("mee", "moop",
                "hello", "hi");
        Map<String, String> testPairExpected = this.createFromArgsRef("mee",
                "moop", "hello", "hi");
        int expectedSize = 2;
        /*
         * Call method under test
         */
        int size = testPair.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, expectedSize);
        assertEquals(testPair, testPairExpected);
    }

    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> testPair = this.createFromArgsTest();
        Map<String, String> testPairExpected = this.createFromArgsRef();
        int expectedSize = 0;
        /*
         * Call method under test
         */
        int size = testPair.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, expectedSize);
        assertEquals(testPair, testPairExpected);
    }

    @Test
    public final void testValueStringOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("meep", "moo");
        Map<String, String> mExpected = this.createFromArgsRef("meep", "moo");
        String expectedValue = "moo";
        /*
         * Call method under test
         */
        String testValue = m.value("meep");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(testValue, expectedValue);
        assertEquals(m, mExpected);
    }

    @Test
    public final void testValueIntMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("meep", "moo", "1",
                "2");
        Map<String, String> mExpected = this.createFromArgsTest("meep", "moo",
                "1", "2");
        String expectedValue = "2";
        /*
         * Call method under test
         */
        String testValue = m.value("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(testValue, expectedValue);
        assertEquals(m, mExpected);
    }

    @Test
    public final void testValueDoubleMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("meep", "moo", "1.2",
                "2.2");
        Map<String, String> mExpected = this.createFromArgsTest("meep", "moo",
                "1.2", "2.2");
        String expectedValue = "2.2";
        /*
         * Call method under test
         */
        String testValue = m.value("1.2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(testValue, expectedValue);
        assertEquals(m, mExpected);
    }

    @Test
    public final void testRemoveAnyStringEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("a", "b");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.removeAny();
        boolean result = mExpected.hasKey(x.key())
                && mExpected.hasValue(x.value());
        //Expecting true.
        assertEquals(result, true);
        Pair<String, String> xExpected = mExpected.remove(x.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(x, xExpected);
    }

    @Test
    public final void testRemoveAnyIntegerEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2", "3");
        Map<String, String> mExpected = this.createFromArgsRef("2", "3");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.removeAny();
        boolean result = mExpected.hasKey(x.key())
                && mExpected.hasValue(x.value());
        //Expecting true.
        assertEquals(result, true);
        Pair<String, String> xExpected = mExpected.remove(x.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(x, xExpected);
    }

    @Test
    public final void testRemoveAnyDoubleEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2.2", "3.3");
        Map<String, String> mExpected = this.createFromArgsRef("2.2", "3.3");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.removeAny();
        boolean result = mExpected.hasKey(x.key())
                && mExpected.hasValue(x.value());
        //Expecting true.
        assertEquals(result, true);
        Pair<String, String> xExpected = mExpected.remove(x.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(x, xExpected);
    }

    @Test
    public final void testRemoveAnyMixedEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2", "3.3");
        Map<String, String> mExpected = this.createFromArgsRef("2", "3.3");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.removeAny();
        boolean result = mExpected.hasKey(x.key())
                && mExpected.hasValue(x.value());
        //Expecting true.
        assertEquals(result, true);
        Pair<String, String> xExpected = mExpected.remove(x.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(x, xExpected);
    }

    @Test
    public final void testRemoveAnyStringLeavingOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.removeAny();
        boolean result = mExpected.hasKey(x.key())
                && mExpected.hasValue(x.value());
        //Expecting true.
        assertEquals(result, true);
        Pair<String, String> xExpected = mExpected.remove(x.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(x, xExpected);
    }

    @Test
    public final void testRemoveAnyIntegerLeavingOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2", "3", "4", "5");
        Map<String, String> mExpected = this.createFromArgsRef("2", "3", "4",
                "5");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.removeAny();
        boolean result = mExpected.hasKey(x.key())
                && mExpected.hasValue(x.value());
        //Expecting true.
        assertEquals(result, true);
        Pair<String, String> xExpected = mExpected.remove(x.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(x, xExpected);
    }

    @Test
    public final void testRemoveAnyDoubleLeavingOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2.2", "3.3", "4.4",
                "5.5");
        Map<String, String> mExpected = this.createFromArgsRef("2.2", "3.3",
                "4.4", "5.5");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.removeAny();
        boolean result = mExpected.hasKey(x.key())
                && mExpected.hasValue(x.value());
        //Expecting true.
        assertEquals(result, true);
        Pair<String, String> xExpected = mExpected.remove(x.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(x, xExpected);
    }

    @Test
    public final void testRemoveAnyMixedLeavingOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2", "3.3", "a", "b");
        Map<String, String> mExpected = this.createFromArgsRef("2", "3.3", "a",
                "b");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.removeAny();
        boolean result = mExpected.hasKey(x.key())
                && mExpected.hasValue(x.value());
        //Expecting true.
        assertEquals(result, true);
        Pair<String, String> xExpected = mExpected.remove(x.key());

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(x, xExpected);
    }

    @Test
    public final void testRemoveAnyStringLeavingMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d", "e", "f");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.removeAny();
        boolean result = mExpected.hasKey(x.key())
                && mExpected.hasValue(x.value());
        //Expecting true.
        assertEquals(result, true);
        Pair<String, String> xExpected = mExpected.remove(x.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(x, xExpected);
    }

    @Test
    public final void testRemoveAnyIntegerLeavingMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2", "3", "4", "5", "6",
                "7");
        Map<String, String> mExpected = this.createFromArgsRef("2", "3", "4",
                "5", "6", "7");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.removeAny();
        boolean result = mExpected.hasKey(x.key())
                && mExpected.hasValue(x.value());
        //Expecting true.
        assertEquals(result, true);
        Pair<String, String> xExpected = mExpected.remove(x.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(x, xExpected);
    }

    @Test
    public final void testRemoveAnyDoubleLeavingMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2.2", "3.3", "4.4",
                "5.5", "6.6", "7.7");
        Map<String, String> mExpected = this.createFromArgsRef("2.2", "3.3",
                "4.4", "5.5", "6.6", "7.7");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.removeAny();
        boolean result = mExpected.hasKey(x.key())
                && mExpected.hasValue(x.value());
        //Expecting true.
        assertEquals(result, true);
        Pair<String, String> xExpected = mExpected.remove(x.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(x, xExpected);
    }

    @Test
    public final void testRemoveAnyMixedLeavingMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2", "3.3", "a", "b",
                "0", "1");
        Map<String, String> mExpected = this.createFromArgsRef("2", "3.3", "a",
                "b", "0", "1");
        /*
         * Call method under test
         */
        Pair<String, String> x = m.removeAny();
        boolean result = mExpected.hasKey(x.key())
                && mExpected.hasValue(x.value());
        //Expecting true.
        assertEquals(result, true);
        Pair<String, String> xExpected = mExpected.remove(x.key());

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals(x, xExpected);
    }

    @Test
    public final void testAddEmptyString() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("a", "b");
        /*
         * Call method under test
         */
        m.add("a", "b");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddEmptyInt() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("2", "3");
        /*
         * Call method under test
         */
        m.add("2", "3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddEmptyDouble() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("2.2", "3.3");
        /*
         * Call method under test
         */
        m.add("2.2", "3.3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddEmptyMixed() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("2.2", "3");
        /*
         * Call method under test
         */
        m.add("2.2", "3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddOneString() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("a", "b");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d");
        /*
         * Call method under test
         */
        m.add("c", "d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddOneInt() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2", "3");
        Map<String, String> mExpected = this.createFromArgsRef("2", "3", "4",
                "5");
        /*
         * Call method under test
         */
        m.add("4", "5");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddOneDouble() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2.2", "3.3");
        Map<String, String> mExpected = this.createFromArgsRef("2.2", "3.3",
                "4.4", "5.5");
        /*
         * Call method under test
         */
        m.add("4.4", "5.5");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddOneMixed() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2.2", "3.3");
        Map<String, String> mExpected = this.createFromArgsRef("2.2", "3.3",
                "a", "b");
        /*
         * Call method under test
         */
        m.add("a", "b");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddMoreThanOneString() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d", "e", "f");
        /*
         * Call method under test
         */
        m.add("e", "f");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddMoreThanOneInt() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2", "3", "4", "5");
        Map<String, String> mExpected = this.createFromArgsRef("2", "3", "4",
                "5", "6", "7");
        /*
         * Call method under test
         */
        m.add("6", "7");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddMoreThanOneDouble() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2.2", "3.3", "4.4",
                "5.5");
        Map<String, String> mExpected = this.createFromArgsRef("2.2", "3.3",
                "4.4", "5.5", "6.6", "7.7");
        /*
         * Call method under test
         */
        m.add("6.6", "7.7");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddMoreThanOneMixed() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2.2", "3.3", "a", "b");
        Map<String, String> mExpected = this.createFromArgsRef("2.2", "3.3",
                "a", "b", "0", "1");
        /*
         * Call method under test
         */
        m.add("0", "1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testConstructorZeroInt() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0", "0");
        Map<String, String> mExpected = this.createFromArgsRef("0", "0");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testConstructorZeroString() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("", "");
        Map<String, String> mExpected = this.createFromArgsRef("", "");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testConstructorZeroDouble() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0.0", "0.0");
        Map<String, String> mExpected = this.createFromArgsRef("0.0", "0.0");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testConstructorZeroMixed() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("0.0", "");
        Map<String, String> mExpected = this.createFromArgsRef("0.0", "");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testConstructorOneInt() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2", "2");
        Map<String, String> mExpected = this.createFromArgsRef("2", "2");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testConstructorOneString() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("a", "b");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testConstructorOneDouble() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("3.3", "4.4");
        Map<String, String> mExpected = this.createFromArgsRef("3.3", "4.4");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testConstructorOneMixed() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("3.3", "s");
        Map<String, String> mExpected = this.createFromArgsRef("3.3", "s");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testConstructorMoreThanOneInt() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("2", "2", "3", "3");
        Map<String, String> mExpected = this.createFromArgsRef("2", "2", "3",
                "3");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testConstructorMoreThanOneString() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testConstructorMoreThanOneDouble() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("3.3", "4.4", "5.5",
                "6.6");
        Map<String, String> mExpected = this.createFromArgsRef("3.3", "4.4",
                "5.5", "6.6");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

    @Test
    public final void testConstructorMoreThanOneMixed() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("3.3", "4.4", "5.5",
                "s");
        Map<String, String> mExpected = this.createFromArgsRef("3.3", "4.4",
                "5.5", "s");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
    }

}
