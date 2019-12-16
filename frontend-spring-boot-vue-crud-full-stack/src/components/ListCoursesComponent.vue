<template>
  <div class="container">
    <h3>All Courses</h3>
    <div v-if="message" class="alert alert-success">
      {{message}}
    </div>
    <div class="container">
      <table class="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Description</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="course in courses" v-bind:key="course.id">
            <td>{{course.id}}</td>
            <td>{{course.description}}</td>
            <td>
              <button
                class="btn btn-warning"
                v-on:click="deleteCourseClicked(course.id)"
              >
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  import CourseDataService from "../service/CourseDataService";
  export default {
    name: "CoursesList",
    data() {
      return {
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
            this.courses = response.data;
          });
      },
      /*

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


      */
      deleteCourseClicked(id) {
        CourseDataService.deleteCourse(this.INSTRUCTOR, id).then(response => {
          this.message = `Delete of course ${id} Successful`;
          this.refreshCourses();
          /*
          rjm-debug, added this 
          this.courses = response.data; 
          to resolve the compile error
          */
          this.courses = response.data; 
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