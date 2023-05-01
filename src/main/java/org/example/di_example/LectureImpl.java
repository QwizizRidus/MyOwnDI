package org.example.di_example;

import org.example.annotation.MyBenchmark;

public class LectureImpl implements Lecture{
    @Override
    @MyBenchmark
    public void bringLecture() {
        System.out.println("KNOWLEDGE IS POWER!");
    }
}
