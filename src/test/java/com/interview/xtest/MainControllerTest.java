package com.interview.xtest;

import com.interview.xtest.entities.Item;
import com.interview.xtest.entities.ItemList;
import com.interview.xtest.entities.RequestObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testSave() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/save";
        URI uri = new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        System.out.println(result);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("Saved"));
    }

    @Test
    public void testGetItem() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/getitem";
        URI uri = new URI(baseUrl);
        ResponseEntity<Item[]> result = restTemplate.getForEntity(uri, Item[].class);
        System.out.println(result.getBody().length);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(0, result.getBody().length);
    }

    @Test
    public void testGetItemCount() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/getItemCount";
        URI uri = new URI(baseUrl);
        ResponseEntity<Long> result = restTemplate.getForEntity(uri, Long.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(0, result.getBody().longValue());
    }

    @Test
    public void testCalculate() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/calculate";
        URI uri = new URI(baseUrl);
        List<RequestObject> requestObjects1 = new ArrayList<>();
        requestObjects1.add(new RequestObject(1, "1", 20, 400, 2 , 3 ));
        Double result1 = restTemplate.postForObject(uri, requestObjects1,  Double.class);
        List<RequestObject> requestObjects2 = new ArrayList<>();
        requestObjects2.add(new RequestObject(2,"2",20,400,0,63));
        Double result2 = restTemplate.postForObject(uri, requestObjects2,  Double.class);
        List<RequestObject> requestObjects3 = new ArrayList<>();
        requestObjects3.add(new RequestObject(2,"2",20,400,3,3));
        Double result3 = restTemplate.postForObject(uri, requestObjects2,  Double.class);
        Assert.assertEquals(0, Double.compare(result1, 878.0));
        Assert.assertEquals(0, Double.compare(result2, 1158.0));
        Assert.assertEquals(0, Double.compare(result3, 1158.0));
    }


}
