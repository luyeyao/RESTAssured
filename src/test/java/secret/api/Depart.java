package secret.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import secret.WeWork;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Depart {

    public int parentid = 1;

    public Response list(int id){
        return given()
                .param("access_token", WeWork.getInstance().getToken())
                //.param("id",parentid)
            .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
            .then()
                .log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }//如果此处没有返回值，则调用list方法时报错

    public Response addDep(String name,int parentid){
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name", name);
        data.put("parentid",parentid);

        return given()
                .log().all()
                .queryParam("access_token",WeWork.getInstance().getToken())
                .contentType(ContentType.JSON)
                .body(data)
                .when().log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then()
                .log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }
    public Response addDep(String name){
        return addDep(name,parentid);
    }

    public Response delDep(int id){
        return given()
                .param("access_token",WeWork.getInstance().getToken())
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .body("errmsg",equalTo("deleted"))
                .extract().response();

    }
}
