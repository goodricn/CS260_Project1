package edu.union.adt.graph.tests;
//package edu.union.adt.graph.tests.goodricn;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import edu.union.adt.graph.tests.goodricn.GoodrichGraphTests;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    SimpleGraphTests.class,
    GraphTestsUsingEquals.class,
    GoodrichGraphTests.class

})
public class GraphTestSuite
{ // no implementation needed; above annotations do the work.
}
