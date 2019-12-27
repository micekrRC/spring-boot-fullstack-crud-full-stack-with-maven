import axios from "axios";

const INSTRUCTOR = "in28minutes";
//const COURSE_API_URL = "http://localhost:8080";
const COURSE_API_URL = "http://localhost:8093";
/*
forming the URL to call in a reusable way
*/
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/instructors/${INSTRUCTOR}`;

class CourseDataService {

    retrieveCourse(name, id) {
        return axios.get(`${INSTRUCTOR_API_URL}/courses/${id}`);
    }

    retrieveAllCourses() {

        // Call the REST API with the GET method
        return axios.get(`${INSTRUCTOR_API_URL}/courses`);
    }
    /*
    Enhancing Vue app with Delete Course Feature
    Let’s add deleteCourse method to CourseDataService. 
    As you can see it execute the delete request to specific course api url.
    */
    deleteCourse(name, id) {
        return axios.delete(`${INSTRUCTOR_API_URL}/courses/${id}`);
    }

    updateCourse(name, id, course) {
        return axios.put(`${INSTRUCTOR_API_URL}/courses/${id}`, course);
    }
  
    createCourse(name, course) {
        return axios.post(`${INSTRUCTOR_API_URL}/courses/`, course);
    }



}
// We are creating an instance of CourseDataService and making it available for other components.
export default new CourseDataService();