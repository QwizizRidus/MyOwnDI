import org.example.entity.Employee;
import org.example.entity.Manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class ReflectionApiDemoTest {

    @Test
    public void createAppropriateCopy() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Employee employee = new Employee();
        Employee manager = new Manager();

        Class<? extends Employee> employeeClass = employee.getClass();
        Class<? extends Employee> managerClass = Manager.class;// class literal

        Employee employeeCopy = employeeClass.getDeclaredConstructor().newInstance();
        Employee managerCopy = managerClass.getDeclaredConstructor().newInstance();

        Assertions.assertEquals(employeeCopy.getClassName(),"Employee");
        Assertions.assertEquals(managerCopy.getClassName(),"Manager");
    }
}
