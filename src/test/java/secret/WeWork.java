package secret;

import static io.restassured.RestAssured.given;

public class WeWork {
    String token;
    private static WeWork weWork;

    public static WeWork getInstance(){
        if (weWork == null){
            weWork = new WeWork();
        }
        return weWork;
    }

    public String getToken(){
        if (token == null){
            token = given()
                    .param("corpid","ww2e9b02771240c625")
                    .param("corpsecret","zyXR7JHt49xwLQmLx2ZeUmWhDKvnE6RGWWeg7EFNTCQ")
                    .when()
                    .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                    .then()
                    .extract()
                    .body().path("access_token");
            System.out.println(token);
        }
        return token;
    }

}
