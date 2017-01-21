package com.example;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.Arrays;

/**
 * Created by mac on 1/18/17.
 */
public class DatabaseSeedRule implements MethodRule {

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        System.out.println("seeding database==============================");
        DatabaseSeed annotation = method.getAnnotation(DatabaseSeed.class);
        String[] locations = annotation.locations();
        System.out.println(Arrays.asList(locations));
        return null;
    }
}
