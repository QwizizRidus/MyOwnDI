import org.example.CommandDispatcher;
import org.example.entity.MyController;
import org.example.entity.Service;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DispatcherTest {
    Service service = Mockito.mock(Service.class);
    CommandDispatcher dispatcher = new CommandDispatcher(new MyController(service));

    @Test
    void dispatch_to_foo_test(){
        dispatcher.invokeMethodByCommand("foo");
        Mockito.verify(service,Mockito.times(1)).foo();
    }

    @Test
    void dispatch_to_bar_test(){
        dispatcher.invokeMethodByCommand("bar");
        Mockito.verify(service,Mockito.times(1)).bar();
    }

    @Test
    void dispatch_to_help_test(){
        dispatcher.invokeMethodByCommand("help");
        Mockito.verify(service,Mockito.times(1)).help();
    }
}
