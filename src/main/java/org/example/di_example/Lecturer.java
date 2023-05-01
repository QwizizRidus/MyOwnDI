package org.example.di_example;

import org.example.annotation.Inject;
import org.example.annotation.InjectRandomInt;
import org.example.annotation.MyBenchmark;
import org.example.annotation.MyPostConstruct;

import java.util.stream.Stream;

public class Lecturer {

    @Inject
    private Speaker speaker;
    @Inject
    private Lecture lecture;
    @Inject
    private SlideShow slideShow;
    @InjectRandomInt(min = 1, max = 3)
    private int numberOfGreetings;

    @MyPostConstruct
    private void init(){
        for(int i = 0; i<numberOfGreetings;i++)
            System.out.println("Hello!");
    }

    public void startLecture(){
        speaker.speak();
        slideShow.showSlides();
        lecture.bringLecture();
    }

}
