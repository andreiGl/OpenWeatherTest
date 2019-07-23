package ca.glotov.openweathertest.model;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class CityListTest {

    @Test
    public void testCityList() throws IllegalAccessException, NoSuchFieldException {
        final CityList cityList = new CityList();
        cityList.setCityListNames("London,GB;Hong Kong,CN;Vancouver,CA");

        final Field field = cityList.getClass().getDeclaredField("cityList");
        field.setAccessible(true);

        List<String> actual = (List<String>)field.get(cityList);
        List<String> expected = Arrays.asList("London,GB", "Hong Kong,CN", "Vancouver,CA");

        assertNotNull(actual);
        assertThat(actual, not(IsEmptyCollection.empty()));
        assertThat(actual, is(expected));
        assertThat(actual, hasItems("Hong Kong,CN"));
        assertThat(actual, hasSize(3));
        assertThat(actual.size(), is(3));
        assertThat(actual, contains("London,GB", "Hong Kong,CN", "Vancouver,CA"));
        assertThat(actual, containsInAnyOrder("Hong Kong,CN", "London,GB", "Vancouver,CA"));
    }
}