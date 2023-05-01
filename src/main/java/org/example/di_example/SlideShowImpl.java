package org.example.di_example;

import org.example.annotation.MyBenchmark;

public class SlideShowImpl implements SlideShow{
    @Override
    @MyBenchmark
    public void showSlides() {
        System.out.println("Slide 1 -> Slide 2 -> ...");
    }
}
