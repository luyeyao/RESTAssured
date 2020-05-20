package secret.testcase;

import org.junit.jupiter.api.*;
import secret.api.Depart;

import static org.hamcrest.CoreMatchers.equalTo;

public class DepartTest {
    @BeforeAll
    public static void beforeall(){}

    static Depart depart = new Depart();
    @Test
    public void addDepart(){
        depart.addDep("研发部",depart.parentid)
                .then()
                .body("errmsg",equalTo("created"));

        depart.list(depart.parentid).body("",equalTo(""));
    }
    @Test
    public void delDepart(){}
    @Test
    public void list(){
        depart.list(depart.parentid)
                .then()
                .log().all()
                .body("errmsg",equalTo("ok"));
    }

}
