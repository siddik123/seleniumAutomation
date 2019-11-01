package TestFramework;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import resources.*;
public class basics4 {

	
	@Test
	public void postDataXML() throws IOException
	{
		String postdata=GenerateStringFromResource("F:\\Udemy API Automation\\postdata.xml");
		RestAssured.baseURI="http://216.10.245.166";
		Response resp=given().
		
		queryParam("key","qaclick123").
		body(postdata).
		when().
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).
		extract().response();
		
		XmlPath x=ReusableMethods.rawToXML(resp);
		System.out.println(x.get("PlaceAddResponse.place_id"));
		
		
		
	// Create a place =response (place id)
		
		// delete Place = (Request - Place id)	
		//Task 3 place this place id in the Delete request
				given().
				queryParam("key","qaclick123").
				
				body("{"+
		  "\"place_id\": \""+x.get("PlaceAddResponse.place_id")+"\""+
		"}").
				when().
				post("/maps/api/place/delete/json").
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("status",equalTo("OK"));
		

	}
	
	public static String GenerateStringFromResource(String path) throws IOException {

	    return new String(Files.readAllBytes(Paths.get(path)));

	}
}
