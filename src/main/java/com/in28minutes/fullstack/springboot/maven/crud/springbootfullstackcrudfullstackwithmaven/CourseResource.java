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
    
    
*/

/*
 * 
 * @RestController : Combination of @Controller and @ResponseBody
 * 
 * Beans returned are converted to/from JSON/XML.
 * 
 * 
 */
@CrossOrigin(origins = { 
		"http://localhost:3000", 
		"http://localhost:4200", 
		"http://localhost:8089" })
@RestController
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

	@DeleteMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable String username, @PathVariable long id) {

		Course course = courseManagementService.deleteById(id);

		if (course != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable String username, @PathVariable long id,
			@RequestBody Course course) {

		Course courseUpdated = courseManagementService.save(course);

		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@PostMapping("/instructors/{username}/courses")
	public ResponseEntity<Void> createCourse(@PathVariable String username, @RequestBody Course course) {

		Course createdCourse = courseManagementService.save(course);

		// Location
		// Get current resource url
		/// {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCourse.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}