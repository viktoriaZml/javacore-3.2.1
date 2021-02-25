import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class Main {
  public static final String url = "https://cat-fact.herokuapp.com/facts";
  public static final ObjectMapper mapper = new ObjectMapper();

  public static void main(String[] args) throws IOException {
    CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setDefaultRequestConfig(RequestConfig.custom()
                    .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                    .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                    .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                    .build())
            .build();
    // создание объекта запроса
    HttpGet request = new HttpGet(url);

    // отправка запроса
    CloseableHttpResponse response = httpClient.execute(request);

    // чтение
    List<Fact> posts = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<Fact>>() {
    });

    // фильтрация и вывод
    posts.stream().filter(value -> value.getText().length() < 100).forEach(System.out::println);
  }
}
