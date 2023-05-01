package di_test;

import org.example.ObjectFactory;
import org.example.di_example.Lecturer;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class DiTests {

    Lecturer lecturer = new ObjectFactory<Lecturer>().getInstance(Lecturer.class);

    public DiTests() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
    }

    @Test
    void lecture_test(){
        lecturer.startLecture();
    }
}
