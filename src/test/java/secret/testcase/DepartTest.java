package secret.testcase;

import org.junit.jupiter.api.*;
import secret.api.Depart;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class DepartTest {
    static Depart depart = new Depart();

    @BeforeAll
    public static void beforeall(){
        //数据清理
        ArrayList<Integer> ids =
                depart.list(depart.parentid).then().extract().body().path("department.findAll{ depart->depart.parentid == "+depart.parentid+" }.id");
        System.out.println(ids);
        ids.forEach(id->depart.delDep(id));
    }

    @Test
    public void list(){
        depart.list(depart.parentid)
                .then()
                .log().all()
                .body("errmsg",equalTo("ok"));
    }

    @Test
    public void addDepart(){
        String name = "部门3";
        depart.addDep(name,depart.parentid).then().body("errmsg",equalTo("created"));
        //depart.list(depart.parentid).then().body("depart.findAll {d->d.name == '"+ name +"' }.id",hasItem("部门2"));


        //depart.list(depart.parentid).body("",equalTo(""));
    }
    @Test
    public void delDepart(){

        //先创建再删除，case隔离
        int id = depart.addDep("部门2").then().body("errmsg",equalTo("created"))
        .extract().body().path("id");
        System.out.println(id);
        depart.delDep(id).then().body("errmsg",equalTo("deleted"));
    }


}
