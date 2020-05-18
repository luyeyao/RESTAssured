package secret.api;

import io.restassured.response.Response;
import secret.WeWork;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Depart {

    public int parentid = 1;

    public Response list(int id){
        return given()
                .param("access_token", WeWork.getInstance().getToken())
                .param("id",parentid)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }//如果此处没有返回值，则调用list方法时报错
}
