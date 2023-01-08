import org.junit.Test;
import ru.job4j.concurrent.CASCount;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {

    @Test
    public void whenIncrementThenOne() {
        CASCount casCount =  new CASCount();
        casCount.increment();
        assertThat(casCount.get(), is(1));
    }

    @Test
    public void whenGetThenZero() {
        CASCount casCount =  new CASCount();
        assertThat(casCount.get(), is(0));
    }


}