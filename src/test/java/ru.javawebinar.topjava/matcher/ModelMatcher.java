package ru.javawebinar.topjava.matcher;

import org.junit.Assert;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @param <T> : entity
 * @param <R> : testEntity, converter result for compare
 */
public class ModelMatcher<T, R> {

    protected Function<T, R> entityConverter;

    public ModelMatcher(Function<T, R> entityConverter) {
        this.entityConverter = entityConverter;
    }

    public void assertEquals(T excepted, T actual) {
        Assert.assertEquals(entityConverter.apply(excepted), entityConverter.apply(actual));
    }

    public void assertCollectionEquals(Collection<T> excepted, Collection<T> actual) {
        Assert.assertEquals(map(excepted, entityConverter), map(actual, entityConverter));
    }

    public static <S, T> Collection<T> map(Collection<S> collection, Function<S, T> converter) {
        return collection.stream().map(converter).collect(Collectors.toList());
    }
}
