package secret.testcase;
import org.junit.jupiter.api.Test;
import secret.api.Tag;
import static org.hamcrest.CoreMatchers.equalTo;


public class TagTest {
    Tag tag = new Tag();

    @Test
    public void addtag(){
        tag.addTag("UI",1)
                .then()
                .body("errmsg",equalTo("created"));
    }

    @Test
    public void update(){
        tag.updateTag("UI design",1)
                .then()
                .body("errmsg",equalTo("update"));
    }

    @Test
    public void del(){
        tag.deltag(1)
                .then()
                .body("errmsg",equalTo("deleted"));
    }
}
