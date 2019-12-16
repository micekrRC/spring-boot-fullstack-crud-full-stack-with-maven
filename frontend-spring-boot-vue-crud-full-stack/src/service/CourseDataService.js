import axios from "axios";

const INSTRUCTOR = "in28minutes";
const COURSE_API_URL = "http://localhost:8080";
/*
forming the URL to call in a reusable way
*/
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/instructors/${INSTRUCTOR}`;

class CourseDataService {
  retrieveAllCourses() {

    // Call the REST API with the GET method
    return axios.get(`${INSTRUCTOR_API_URL}/courses`);
  }
}
// We are creating an instance of CourseDataService and making it available for other components.
export default new CourseDataService();