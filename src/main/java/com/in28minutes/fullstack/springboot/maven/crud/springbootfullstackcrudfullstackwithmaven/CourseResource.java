package com.in28minutes.fullstack.springboot.maven.crud.springbootfullstackcrudfullstackwithmaven;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/*
base/rootFolder of GIT
C:\micekr\temp\spring-boot-fullstack-crud-full-stack-with-maven

root folder of vue code (NOT another GIT repo, just a sub-folder for client side code)
C:\micekr\temp\spring-boot-fullstack-crud-full-stack-with-maven\frontend-spring-boot-vue-crud-full-stack

added this line to test out GIT featureS in vs code
src\main\java\com\in28minutes\fullstack\springboot\maven\crud\springbootfullstackcrudfullstackwithmaven\CourseResource.java
CourseResource.java is and actual Java class that I accessed from vs code,
I am using vs code just to edit and debug client vue (include *.js) code
under
C:\micekr\temp\spring-boot-fullstack-crud-full-stack-with-maven\frontend-spring-boot-vue-crud-full-stack
but, vs code actually notifies me of any changes in the base git repositiory
of
C:\micekr\temp\spring-boot-fullstack-crud-full-stack-with-maven

where as the client code that I am editing/debugging in vs code is under the subfolder
of
C:\micekr\temp\spring-boot-fullstack-crud-full-stack-with-maven\frontend-spring-boot-vue-crud-full-stack

FYI

*/

/*
 
running java server web port 8080
loading client app from
http://localhost:8089/
client code is calling server code on port 8080 from port 8089

getting the following error:

Access to XMLHttpRequest at 'http://localhost:8080/instructors/in28minutes/courses' from origin 'http://localhost:8089' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.

Uncaught (in promise) Error: Network Error
    at createError (createError.js?2d83:16)
    at XMLHttpRequest.handleError (xhr.js?b50d:81)

    code BEFORE
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class CourseResource {    
    
    NEW code, to fix CORS error/exception
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class CourseResource {    
   
@CrossOrigin
Allow requests from specific origins    
    
    
We will use 3000 to run React, 			<< did NOT setup REACT code, proably won't (because of time)
and 8081 to run Vue JS apps,   			<< using 8089 
and we use 4200 to run Angular apps.  	<< did NOT setup Angular code, proably won't (because of time)
Hence we are allowing requests from all ports.    
    
    
*/

/*
 * 
 * @RestController : Combination of @Controller and @ResponseBody
 * 
 * Beans returned are converted to/from JSON/XML.
 * 
 * 
 */
//@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8089" })
/*
@CrossOrigin(origins = 
{ 	"http://localhost:3000", 
	"http://localhost:4200", 
	"http://localhost:8089",
	"http://localhost:8080",	
	"http://localhost:8090" 
	
})
*/
@RestController
@CrossOrigin(origins = 
{ 
//	"http://localhost:3000", 
//	"http://localhost:4200", 
//	"http://localhost:8089",
//	"http://localhost:8080",	
	"http://localhost:8090" 
	
})
public class CourseResource {
	/*
	 * 
	 * @Autowired private CoursesHardcodedService courseManagementService - Autowire
	 * the CoursesHardcodedService so that we can retrieve details from business
	 * service.
	 * 
	 * 
	 */
	@Autowired
	private CoursesHardcodedService courseManagementService;

	/*
	 * 
	 * If you launch up the Spring boot application and go to
	 * http://localhost:8080/instructors/in28minutes/courses in the browser, you
	 * would see the response from the API.
	 * 
	 * [ { "id": 1, "username": "in28minutes", "description":
	 * "Learn Full stack with Spring Boot and Angular" }, { "id": 2, "username":
	 * "in28minutes", "description": "Learn Full stack with Spring Boot and React"
	 * }, { "id": 3, "username": "in28minutes", "description":
	 * "Master Microservices with Spring Boot and Spring Cloud" }, { "id": 4,
	 * "username": "in28minutes", "description":
	 * "Deploy Spring Boot Microservices to Cloud with Docker and Kubernetes" } ]
	 * 
	 * 
	 * 
	 */
	@GetMapping("/instructors/{username}/courses")
	public List<Course> getAllCourses(@PathVariable String username) {
		return courseManagementService.findAll();
	}

	@GetMapping("/instructors/{username}/courses/{id}")
	public Course getCourse(@PathVariable String username, @PathVariable long id) {
		return courseManagementService.findById(id);
	}
	/*
	 * 
@DeleteMapping("/instructors/{username}/courses/{id}") - 
	We are mapping the Delete Request Method with two path variables
@PathVariable String username, @PathVariable long id - 
	Defining the variables for Path Variables	
	
	*/
	@DeleteMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable String username, @PathVariable long id) {

		Course course = courseManagementService.deleteById(id);

		if (course != null) {
			/*
			ResponseEntity.noContent().build() - If Request is successful, return no content back
			*/
			return ResponseEntity.noContent().build();
		}

		/*
		ResponseEntity.notFound().build() - If delete failed, return error - resource not found.
		*/
		return ResponseEntity.notFound().build();
	}
	/*
	using PUT method to update the course. 
	On course updating, we are returning 200 status with the 
	updated course details in the body.	
	
	*/
	@PutMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable String username, 
			@PathVariable long id,
			@RequestBody Course course) {

		Course courseUpdated = courseManagementService.save(course);

		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
	/*
Let’s add a method to the Resource class to create the course. 
We are using POST method to create the course. 
On course updating, we are returning a status of CREATED.	
	
	*/
	@PostMapping("/instructors/{username}/courses")
	public ResponseEntity<Void> createCourse(@PathVariable String username, 
			@RequestBody Course course) {

		Course createdCourse = courseManagementService.save(course);

		// Location
		// Get current resource url
		/// {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdCourse.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}