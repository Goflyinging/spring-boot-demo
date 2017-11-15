import com.lxing.client.config.HttpClientRestConfig;
import com.lxing.client.domain.Book;
import com.lxing.helloWorld.common.exception.BaseException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/***
 * Created on 2017/11/13 <br>
 * Description: <br>
 * @author lxing
 */
public class RestTemplateTest {

    private static final String preURL = "http://127.0.0.1:8080";

    RestTemplate restTemplate;

    @Before
    public void before() {
        HttpClientRestConfig httpClientRestConfig = new HttpClientRestConfig();
        restTemplate = httpClientRestConfig.restTemplate();
    }

    @Test
    public void whenPostFail() {
        Book book = new Book();
        book.setAuthor("lxing");
        book.setBookName("lxing");
        book.setPrice(50);
        try {
            Book resposebook = restTemplate.postForObject(preURL + "/books", book, Book.class);
        } catch (BaseException exception) {
            System.out.println(exception.getMessage());
        }

    }

}
