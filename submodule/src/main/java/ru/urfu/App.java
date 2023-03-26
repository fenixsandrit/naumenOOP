package ru.urfu;

import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ImmutablePair<String, String> pair = new ImmutablePair<>("Hello from ", "apache commons lang3");
        System.out.println(pair.left + pair.right);
    }
}
