package RESTAssured1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
given()
content type,set cookies, add auth, add param, set headers info
when()
 get, post, put, delete

then()
validation status code,extarct responce..
*/

public class Httprequest

{
	int id;
	@Test(priority=1)
	void getUser()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200)
		.body("page" , equalTo(2))
		.log().all();
	}
	@Test (priority=2)
	void createUser()
	{
		HashMap hp = new HashMap();
		hp.put("name","Test");
		hp.put("job","Tester");
		
		id=given()
			.contentType("application/json")
			.body(hp)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
		//.then()
			//.statusCode(201)
			//.log().all();
	}
	@Test (priority=3,dependsOnMethods= {"createUser"})
	void UpdateUser() {
		
		HashMap hp = new HashMap();
		hp.put("name","vijay");
		hp.put("job","Engineer");
		
		given()
			.contentType("application/json")
			.body(hp)
		
		.when()
			.put("https://reqres.in/api/users/" + id)
			
		
		.then()
			.statusCode(200)
			.log().all();

	}
	@Test(priority=4)
	void DeleteUser()
	{
		given()
		
		
		.when()
		.delete("https://reqres.in/api/users/"+ id)
		
		.then()
		.statusCode(204)
		.log().all();
	}
}
	


















