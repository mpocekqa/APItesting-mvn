import com.beust.jcommander.internal.Lists;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.*;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class Get200 extends BaseClass {
    CloseableHttpClient client;
    CloseableHttpResponse response;

    int bookingId;

    @BeforeMethod
    public void setup(){
        client  = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }

    @Test
    public void baseUrlReturns200() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);

        String jsonString = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonString);

        String value = (String) jsonObject.get("hub_url");

        System.out.println("Vrednost propertija hub_url je " + value);


    }

    @Test
    public void returnsCorrectLogin() throws IOException{
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/mpocekqa");
        response = client.execute(get);

        User user = unmarshall(response, User.class);
        System.out.println(user.getLogin());
        System.out.println(user.getId());
        System.out.println(user.getSubscriptionsUrl());

        Assert.assertEquals(user.getLogin(), "mpocekqa");
    }

    @Test
    public void notFoundTest() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/notExistingEndpoint");
        response = client.execute(get);

        NotFound notFoundObject = unmarshallGeneric(response, NotFound.class);
        Assert.assertEquals(notFoundObject.getMessage(), "Not Found");
    }

    @Test
    public void notFoundTest1() throws IOException {
        HttpGet get = new HttpGet("https://restful-booker.herokuapp.com/booking");
        response = client.execute(get);

        AllBookings notFoundObject = unmarshallGeneric(response, AllBookings.class);
        Assert.assertEquals(notFoundObject.getBookingid(), 10);
    }

    @Test
    public void correcRateLimits() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        response = client.execute(get);

        Rate rateLimitObject = unmarshallGeneric(response, Rate.class);
        Assert.assertEquals(rateLimitObject.getLimit(), 60);


    }

    @Test
    public void listOfBookings() throws IOException {
        HttpGet get = new HttpGet("https://restful-booker.herokuapp.com/booking");
        get.addHeader("Accept","*/*");
        response = client.execute(get);
        String jsonBody = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper();
        Booking[] bookings = mapper.readValue(jsonBody, Booking[].class);
        System.out.println(bookings[0].getBookingid() + " evo me u listi bookinga");
        bookingId = bookings[0].getBookingid();
        /*get = new HttpGet("https://restful-booker.herokuapp.com/booking/" + bookings[0].getBookingid());

        response = client.execute(get);
        System.out.println(get);


        BookingDetails bookingDetailsObject = unmarshallGeneric(response, BookingDetails.class);
        System.out.println(bookingDetailsObject.getFirstname());
        System.out.println(bookingDetailsObject.getTotalprice());
        System.out.println(bookingDetailsObject.getBookingdates().getCheckin());*/


    }

    @Test
    public void listOfBookingsDetails() throws IOException {
        if (bookingId == 0) {
            listOfBookings();
        }
        HttpGet get = new HttpGet("https://restful-booker.herokuapp.com/booking/" + bookingId);
        get.addHeader("Accept","*/*");
        response = client.execute(get);
        System.out.println(get.getURI());

        BookingDetails bookingDetailsObject = unmarshallGeneric(response, BookingDetails.class);
        System.out.println(bookingDetailsObject.getFirstname());
        System.out.println(bookingDetailsObject.getTotalprice());
        System.out.println(bookingDetailsObject.getBookingdates().getCheckin());
    }

    @Test
    public void header() throws IOException {
        HttpOptions options = new HttpOptions(BASE_ENDPOINT);
        response = client.execute(options);
        Header[] listOfHeaders =  response.getAllHeaders();
        for (int i = 0; i < listOfHeaders.length; i++) {
            System.out.println(listOfHeaders[i].getName());
            System.out.println(listOfHeaders[i].getValue());
            System.out.println("------------------------------------");
        }

    }

    @Test
    public void ispisJsonStringa() throws Exception {
        String filePath = "src/main/resources/schema/BookingDetails.json";
        String jasonAsString = readFileAsString(filePath);
        System.out.println(jasonAsString);
        HttpPost request = new HttpPost("https://restful-booker.herokuapp.com/booking");
        request.addHeader("Accept","*/*");
        request.setEntity(new StringEntity(jasonAsString, ContentType.APPLICATION_JSON));
        response = client.execute(request);

        int actualResult = response.getStatusLine().getStatusCode();
        System.out.println(actualResult);

        NewBookingResponse responseObject = unmarshallGeneric(response, NewBookingResponse.class);
        System.out.println(responseObject.getBookingid());
        System.out.println(responseObject.getBooking().getBookingdates().getCheckout());

    }

    private User unmarshall(CloseableHttpResponse response, Class<User> clazz) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, clazz);
    }



    //@Test
    public void rateUrlReturns200() throws IOException {
        int actualStatus = checkResponseStatus(BASE_ENDPOINT+ "/rate_limit");
        Assert.assertEquals(actualStatus, 200);
    }

    //@Test
    public void searchUrlturns200() throws IOException {
        int actualStatus = checkResponseStatus(BASE_ENDPOINT+ "/rate_limit");
        Assert.assertEquals(actualStatus, 200);
    }

    public int checkResponseStatus(String url) throws IOException {

        HttpGet get = new HttpGet(url);
        response = client.execute(get);
        int actualStatus = response.getStatusLine().getStatusCode();
        return actualStatus;
    }


}
