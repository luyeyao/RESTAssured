package secret.testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import secret.api.Depart;

import static org.hamcrest.CoreMatchers.equalTo;

public class DepartTest {
    @BeforeAll
    public static void beforeall(){}

    static Depart depart = new Depart();
    @Test
    public void addDepart(){}
    @Test
    public void delDepart(){}
    @Test
    public void list(){
        depart.list(depart.parentid)
                .then()
                .body("errmsg",equalTo("ok"));
    }
}
