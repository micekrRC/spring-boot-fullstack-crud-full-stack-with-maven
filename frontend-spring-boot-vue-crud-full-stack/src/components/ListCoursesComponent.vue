
<template>
  <div class="container">
    <h3>All Courses</h3>
    <div v-if="message" class="alert alert-success">{{message}}</div>
    <div class="container">
      <table class="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Description</th>

            <th>Update</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          <!--

  v-for="course in courses" - 
Allow you to loop around a list of items and define how each item should be displayed.
  v-bind:key="course.id" - 
A key is used to uniquely identify a row.
  <td>{{course.id}}</td> - 
Decide how column is displayed
  <td>{{course.description}}</td> - 
Decide how column is displayed

          -->
          <tr v-for="course in courses" v-bind:key="course.id">
            <td>{{course.id}}</td>
            <td>{{course.description}}</td>
            <!--
            <td>
              <button
                class="btn btn-warning"
                v-on:click="deleteCourseClicked(course.id)"
              >
                Delete
              </button>
            </td>
            -->
            <td>
              <button class="btn btn-success" 
                v-on:click="updateCourseClicked(course.id)">Update</button>
            </td>
            <td>
              <button class="btn btn-warning" 
              v-on:click="deleteCourseClicked(course.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="row">
        <button class="btn btn-success" v-on:click="addCourseClicked()">Add</button>
    </div>


  </div>


</template>

<script>
import CourseDataService from "../service/CourseDataService";
export default {
  name: "CoursesList",
  data() {
    return {
      /*
courses: [], message: null, - 
To display courses, we need to make them available to the component.
 We add courses to the state of the component and 
 initialize it in the constructor.


        */
      courses: [],
      message: null,
      INSTRUCTOR: "in28minutes"
    };
  },

  /*

    test

methods: { refreshCourses() {}} - Any method in a vue component 
should be under methods. 
CourseDataService.retrieveAllCourses(INSTRUCTOR).then - 
This would make the call to the REST API. 

You can define how to process the response in the then method.

    */

  methods: {

    refreshCourses() {
      CourseDataService.retrieveAllCourses(this.INSTRUCTOR) //HARDCODED
        .then(response => {
          /*            
response => {this.courses = response.data;} - 
When the response comes back with data, we update the state.

*/

          this.courses = response.data;
        });
    },

    updateCourseClicked(id) {
      this.$router.push(`/courses/${id}`);
    },

    addCourseClicked() {    

/*
 
https://www.springboottutorial.com/spring-boot-vue-full-stack-crud-maven-application

Let’s add the appropriate method to handle click of Add button.

methods: {
  ...
  addCourseClicked() {
    this.$router.push(`/course/-1`);
  },
}

this.$router.push(`/course/-1`);
 was typo in tutorial, s/b /courses


*/
      this.$router.push(`/courses/-1`);      
    },



    /*

syntax error in tutorial
https://www.springboottutorial.com/spring-boot-vue-full-stack-crud-maven-application
...
deleteCourseClicked(id) {


 ERROR  Failed to compile with 1 errors                                             9:28:47 PM

 error  in ./src/components/ListCoursesComponent.vue

Module Error (from ./node_modules/eslint-loader/index.js):
error: 'response' is defined but never used (no-unused-vars) at src\components\ListCoursesComponent.vue:66:66:
  64 |
  65 |       deleteCourseClicked(id) {
> 66 |         CourseDataService.deleteCourse(this.INSTRUCTOR, id).then(response => {
     |                                                                  ^
  67 |           this.message = `Delete of course ${id} Successful`;
  68 |           this.refreshCourses();
  69 |         });      


      deleteCourseClicked(id) {
        CourseDataService.deleteCourse(this.INSTRUCTOR, id).then(response => {
          this.message = `Delete of course ${id} Successful`;
          this.refreshCourses();
        });
      }


On click of the button we are calling the deleteCourseClicked method 
passing the course id. The implementation for deleteCourseClicked is shown below:

When we get a successful response for delete API call, 
we set a message into state and refresh the courses list


      */
    deleteCourseClicked(id) {
      //CourseDataService.deleteCourse(this.INSTRUCTOR, id).then(response => {
      CourseDataService.deleteCourse(this.INSTRUCTOR, id).then(response => {
        this.message = `Delete of course ${id} Successful`;
        this.refreshCourses();

        // way to get around - compile response not used error
        response.status;
        /*
          rjm-debug, added this 
          this.courses = response.data; 
          to resolve the compile error
          */
        //this.courses = response.data;
        //var a = response.data;
        /*

https://stackoverflow.com/questions/49525310/error-console-is-not-defined-no-undef-brackets
ERROR: 'console is not defined. [no-undef] - Brackets

*/

        //          console.log(response.data);
      });
    }







  },

  /*
created() - Vue defines a component lifecycle. 
created will be called as soon as the component is mounted. 
We are calling refreshCourses as soon as a component is mounted.

  */
  created() {
    this.refreshCourses();
  }
};
</script>

<style></style>