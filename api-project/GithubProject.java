package projects;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class GithubProject {

String sshKey;
int sshKeyId;
RequestSpecification requestSpec;
  @Test
  @BeforeClass
  public void setUp() {
	  requestSpec= new RequestSpecBuilder()
	  .setContentType(ContentType.JSON)
	  .addHeader("Authorization", "Token ")
	  .setBaseUri("https://api.github.com")
	  .build();
	 sshKey= "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDVjwhKr3E70nQKDWXkXVCCn0mi4b7NwSGg6U1Kz26XEiCBD4F8qGoNnm2eCd9F5WMpJBXIvs5T0QzCW0Ggo6NAeHJfROaYZYyqBxK/FofEXf+M1we4tPmw479w9r9sE2z2ix3AMs0wU7yU7fVhFW7KSw/8a7Zh8+TWY0HECDAptzTCS9EnNUutlZLiHfOPjNilldGbJzef/W8fpCQT9XIyS5cZ5lcJPI6U6XX1sRUjrIGYGqW1nfkkxCCo7B4qXFYaaYVIzk6+ZMR6QPPJw2MPFTRa0/MuyNw/WJ+bnBd93gQr0RI/XXEIVr/gNhEdNZQBBBE+jWOfJZt0EYarF6+1";  
  }
 @Test(priority=1)
  public void addKey() {
	  String reqBody = "{\"title\":\"TestKey\", \"key\": \"" +sshKey+ "\"}";
	  Response response = given().log().body()
			  				.spec(requestSpec)
			  				.body(reqBody)
			  				.when().post("/user/keys");
	  
	  String respBody = response.getBody().asPrettyString();
	  System.out.println(respBody);
	  sshKeyId= response.then().extract().path("id");
	  response.then().statusCode(201);
		  
	  }
  }
