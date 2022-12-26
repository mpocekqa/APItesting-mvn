import com.github.entities.AccessToken;
import entities.BookingDetails;
import entities.NewBookingResponse;
import org.apache.http.Header;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RestfullBooker extends BaseClass{

    CloseableHttpClient client;
    CloseableHttpResponse response;

    int bookingId;

    String token ="";

    @BeforeMethod
    public void setup(){
        client  = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }

    @Test (priority = 0)
    public void createBooking() throws Exception {
        String jasonAsString = readFileAsString("BookingDetails.json");
        HttpPost request = new HttpPost(BASE_ENDPOINT + "/booking");
        request.addHeader("Accept","*/*");
        request.setEntity(new StringEntity(jasonAsString, ContentType.APPLICATION_JSON));
        long start = System.currentTimeMillis();
        response = client.execute(request);
        long elapsed = System.currentTimeMillis() - start;


        int actualResponseCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualResponseCode, 200);

        NewBookingResponse responseObject = unmarshallGeneric(response, NewBookingResponse.class);
        bookingId = responseObject.getBookingid();
        System.out.println("response time je: " + elapsed);
    }

    @Test (priority = 5)
    public void verifyBookingIsCreated(int bookingID) throws Exception {

        HttpGet getRequest = new HttpGet(BASE_ENDPOINT + "/booking/" + bookingID);
        getRequest.addHeader("Accept","*/*");

        response = client.execute(getRequest);
        int actualResponseCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualResponseCode, 200);

        BookingDetails responseObject = unmarshallGeneric(response, BookingDetails.class);
        //Assert.assertEquals(responseObject.getFirstname(), "Dane");
        //System.out.println(responseObject.getFirstname());

    }

    @Test(priority = 10)
    public void getAccessToken() throws Exception {

        HttpPost postRequest = new HttpPost(BASE_ENDPOINT + "/auth");
        String jasonAsString = readFileAsString("UserCrediantals.json");
        postRequest.addHeader("Accept","*/*");
        postRequest.setEntity(new StringEntity(jasonAsString, ContentType.APPLICATION_JSON));
        response = client.execute(postRequest);

        int actualResponseCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualResponseCode, 200);

        AccessToken responseObject = unmarshallGeneric(response, AccessToken.class);
        System.out.println("Access token = " + responseObject.getToken());
        token = responseObject.getToken();

    }

    @Test(priority = 15)
    public void updateBooking() throws Exception {
        if(bookingId == 0){
            System.out.println("BookingId wasn't set");
            createBooking();
            System.out.println("Now it is set, booking id = " + bookingId);
        }
        HttpPut putRequest = new HttpPut(BASE_ENDPOINT + "/booking/" + bookingId);
        putRequest.addHeader("Accept","*/*");

        if(token.equals("")){
            System.out.println("Token wasn't set");
            getAccessToken();
            System.out.println("Now it is set, Token = " + token);
        }

        String cookieValue = "token=" + token;
        putRequest.addHeader("Cookie", cookieValue);
        String jasonAsString = readFileAsString("BookingForUpdate.json");
        putRequest.setEntity(new StringEntity(jasonAsString, ContentType.APPLICATION_JSON));
        response = client.execute(putRequest);

        BookingDetails responseObject = unmarshallGeneric(response, BookingDetails.class);
        Assert.assertEquals(responseObject.getFirstname(), "Milovan");
    }

    @Test (priority = 20)
    public void verifyBookingIsUpdated() throws Exception {

        HttpGet getRequest = new HttpGet(BASE_ENDPOINT + "/booking/" + bookingId);
        getRequest.addHeader("Accept","*/*");
        response = client.execute(getRequest);

        int actualResponseCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualResponseCode, 200);

        BookingDetails responseObject = unmarshallGeneric(response, BookingDetails.class);
        Assert.assertEquals(responseObject.getFirstname(), "Milovan");

    }

    @Test(priority = 25)
    public void deleteBooking() throws Exception{
        if(bookingId == 0){
            System.out.println("BookingId wasn't set");
            createBooking();
            System.out.println("Now it is set, booking id = " + bookingId);
        }
        HttpDelete deleteRequest = new HttpDelete(BASE_ENDPOINT + "/booking/" + bookingId);
        if(token.equals("")){
            System.out.println("Token wasn't set");
            getAccessToken();
            System.out.println("Now it is set, Token = " + token);
        }

        String cookieValue = "token=" + token;
        deleteRequest.addHeader("Cookie", cookieValue);
        response = client.execute(deleteRequest);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
    }

    @Test (priority = 30)
    public void verifyBookingIsDeleted() throws Exception {

        HttpGet getRequest = new HttpGet(BASE_ENDPOINT + "/booking/" + bookingId);
        getRequest.addHeader("Accept","*/*");
        response = client.execute(getRequest);

        int actualResponseCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualResponseCode, 404);

    }

    @Test (priority = 35)
    public void createMultipleBookings() throws Exception {
        for (int i = 1; i <= 10 ; i++) {
            String jasonAsString = readFileAsStringFromBookingsFolder("Booking" + i + ".json");
            HttpPost request = new HttpPost(BASE_ENDPOINT + "/booking");
            request.addHeader("Accept","*/*");
            request.setEntity(new StringEntity(jasonAsString, ContentType.APPLICATION_JSON));
            long start = System.currentTimeMillis();
            response = client.execute(request);
            long elapsed = System.currentTimeMillis() - start;
            int actualResponseCode = response.getStatusLine().getStatusCode();
            Assert.assertEquals(actualResponseCode, 200);


            NewBookingResponse responseObject = unmarshallGeneric(response, NewBookingResponse.class);
            bookingId = responseObject.getBookingid();

            verifyBookingIsCreated(bookingId);
            System.out.println("Booking " + i + ". created, booking id = " + bookingId + " response time: " + elapsed + " miliseconds bla bla bla");

        }
    }

}
