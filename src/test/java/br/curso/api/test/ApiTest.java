package br.curso.api.test;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class ApiTest {

	//private String urlTodo = "http://localhost:8001/tasks-backend/todo";
	
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
		
	}
	
	
	
	@Test
	public void adicionarTarefaComSucessoPost() {
		
		RestAssured
		.given()
			.body("{\r\n" + 
				"\r\n" + 
				"\"task\":\"Teste API\",\r\n" + 
				"\"dueDate\": \"2020-10-25\"	\r\n" + 
				"	\r\n" + 
				"}").contentType(ContentType.JSON)
		
		.when()
			.post("/todo")
								
		.then().log().all()
		 	.statusCode(201);
	}
	
	@Test
	public void getTarefas() {
		
		RestAssured
		.given()
		
		.when()
			.get("/todo")
								
		.then()
		 	.statusCode(200);
		
		
	}
	
	
	
	
	
	
	
	@Test
	public void NaoDeveAdicionarTarefaPost() {
		
		RestAssured
		.given()
			.body("{\r\n" + 
				"\r\n" + 
				"\"task\":\"Teste API\",\r\n" + 
				"\"dueDate\": \"2010-10-25\"	\r\n" + 
				"	\r\n" + 
				"}").contentType(ContentType.JSON)
		
		.when()
			.post("/todo")
								
		.then().log().all()
		 	.statusCode(400)
		 	.body("message", CoreMatchers.is("Due date must not be in past"));
	}
	
	
}




