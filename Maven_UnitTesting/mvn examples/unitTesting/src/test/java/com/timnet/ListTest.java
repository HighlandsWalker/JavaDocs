package com.timnet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThan;

public class ListTest {

    @Test
    public void testAssertListString() {

        List<String> actual = Arrays.asList("a", "b", "c");
        List<String> expected = Arrays.asList("a", "b", "c");

		//All passed / true

        //1. Test equal.
        assertThat(actual, is(expected));

        //2. If List has this value?
        assertThat(actual, hasItems("b"));

        //3. Check List Size
        assertThat(actual, hasSize(3));

        assertThat(actual.size(), is(3));

        //4.  List order

        // Ensure Correct order
        assertThat(actual, contains("a", "b", "c"));

        // Can be any order
        assertThat(actual, containsInAnyOrder("c", "b", "a"));

        //5. check empty list
        assertThat(actual, not(IsEmptyCollection.empty()));

        assertThat(new ArrayList<String>(), IsEmptyCollection.empty());
    }
    
    @Test
    public void testAssertListNumbers() {

        List<Integer> actual = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

        //All passed / true

        //1. Test equal.
        assertThat(actual, is(expected));

        //2. Check List has this value
        assertThat(actual, hasItems(2));

        //3. Check List Size
        assertThat(actual, hasSize(5));

        assertThat(actual.size(), is(5));

        //4.  List order

        // Ensure Correct order
        assertThat(actual, contains(1, 2, 3, 4, 5));

        // Can be any order
        assertThat(actual, containsInAnyOrder(5, 4, 3, 2, 1));

        //5. check empty list
        assertThat(actual, not(IsEmptyCollection.empty()));

        assertThat(new ArrayList<Number>(), IsEmptyCollection.empty());

		//6. Test numeric comparisons
        assertThat(actual, everyItem(greaterThanOrEqualTo(1)));

        assertThat(actual, everyItem(lessThan(10)));

    }



}