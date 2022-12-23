import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseClass {
    protected static final String BASE_ENDPOINT = "https://restful-booker.herokuapp.com";

    protected <T> T unmarshallGeneric(CloseableHttpResponse response, Class<T> clazz) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, clazz);
    }

    protected String readFileAsString(String fileName)throws Exception{
        String file = "src/main/resources/schema/" + fileName;
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    protected String readFileAsStringFromBookingsFolder(String fileName)throws Exception{
        String file = "src/main/resources/Bookings/" + fileName;
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
