package org.example.di_example;

import org.example.annotation.MyBenchmark;

public class SpeakerImpl implements Speaker{
    @Override
    @MyBenchmark
    public void speak() {
        System.out.println("Blah-blah-blah");
    }
}
