package FashionFlow.product_service;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestAotProcessor;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import java.util.regex.Matcher;
import io.restassured.RestAssured;
import org.testcontainers.utility.DockerImageName;


@Import(TestcontainersConfiguration.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:7.0.5").asCompatibleSubstituteFor("mongo"));

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp(){
		RestAssured.baseURI="http://localhost";

		RestAssured.port=port;
	}
	static {
		mongoDBContainer.start();
	}
	@Test
	void contextLoads() {
		String requestBody = """
                {
                    "name":"Baby Frock Pink",
                    "description":"baby Frock from china",
                    "price":2500
                }
                """;
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("Baby Frock Pink"))
				.body("description", Matchers.equalTo("baby Frock from china"))
				.body("price", Matchers.equalTo(2500));
	}

}
