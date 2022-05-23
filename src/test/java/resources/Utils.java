package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification requestSpecification;

    public RequestSpecification requestSpecificationForRequest() throws IOException
    {
        if (requestSpecification==null)
        {
            // Add place to google API
            // Using Request and Response spec Builder
            // Using Print stream for logging
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpecification =new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("BaseUri"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpecification;
        }
        return requestSpecification;

    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis =new FileInputStream((System.getProperty("user.dir") + "\\src\\test\\java\\resources\\global.properties"));
        prop.load(fis);
        return  prop.getProperty(key);

    }

}
