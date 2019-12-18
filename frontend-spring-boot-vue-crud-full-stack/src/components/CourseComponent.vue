<!--

Following are some of the important details:

Form creation in Vue is similar as creating form in HTML
v-model is binding input to the data, same is with description, it will automaticall display initial values.
<input type="text" class="form-control" v-model="id" disabled> - Creating a disabled text element for id. v-model value should match data.
<input type="text" class="form-control" v-model="description"> - Creating a text element for description.
<button class="btn btn-success" type="submit">Save</button> - Adding a submit button.

-->
<template>
  <div>
    <h3>Course</h3>
    <div class="container">
    <!--
        tie up the form with the submit method. 
        The key snippet is @submit="validateAndSubmit".

    --> 
      <form @submit="validateAndSubmit">
        <div v-if="errors.length">
            <div class="alert alert-warning" v-bind:key="index" v-for="(error, index) in errors">{{ error }}</div>
        </div>
        <fieldset class="form-group">
          <label>Id</label>
          <input type="text" class="form-control" v-model="id" disabled>
        </fieldset>
        <fieldset class="form-group">
          <label>Description</label>
          <input type="text" class="form-control" v-model="description">
        </fieldset>
        <button class="btn btn-success" type="submit">Save</button>
      </form>
    </div>

    <br>

<!--        
        <div>
        <button class="btn btn-success" 
            v-on:click="gotoCourses()">Home
        </button>

                <a class="btn btn-success"  v-on:click="gotoCourses()">Goto Home</a>
    </div>

-->


    <a class="btn btn-success"  v-on:click="gotoCourses()">Courses</a>
  </div>
</template>


<script>
import CourseDataService from '../service/CourseDataService';
export default {
  name: "courseDetails",
  data() {
    return {
      description: "",
      INSTRUCTOR: "in28minutes",
      errors: []
    };
  },
  computed: {
/*
Note the data can be computed at runtime using computed function. Here id is taken from url params
      */
    id() {
      return this.$route.params.id;
    }
  },
  methods: {

      /*
      add method signature
      and button/link to call this method
      to route to the list

      this.$router.push('/courses');

      */
    gotoCourses() {
        this.$router.push('/courses');
    },

    validateAndSubmit(e) {

        console.log({
        id: this.id,
        description: this.description
        }) 

        e.preventDefault();
        this.errors = [];
        if(!this.description) {
            console.log("Enter a description"); // rjm-debug
            this.errors.push("Enter a valid description.");  

        } else if(this.description.length < 5) {
            
            //this.errors.push("Enter at least 5 characters in Description");
            var m = "You ONLY supplied " + this.description.length + " characters of the expected 5.";   
            this.errors.push(m);
            console.log(m); // rjm-debug

        } else {
            console.log("form PASSED validation!"); // rjm-debug 
        }

        if(this.errors.length === 0) {
           /*
            update the CourseComponent to invoke the right service 
            on the click of the submit button.
            */

            console.log("form PASSED validation .. ready to update datastore"); // rjm-debug 
/*

We are creating a course object with the updated details 
and calling the appropriate method on the CourseDataService. 
Once the request is successful, we are redirecting the user to 
the course listing page using this.$router.push('/courses').

*/
            if (this.id === -1) {
                CourseDataService.createCourse(this.INSTRUCTOR, {
                    description: this.description
                })
                .then(() => {
                    this.$router.push('/courses');
                });
            } else {
                CourseDataService.updateCourse(this.INSTRUCTOR, this.id, {
                    id: this.id,
                    description: this.description
                })
                .then(() => {
                    this.$router.push('/courses');
                });
            }
        } else {
            // dump errors to console    
            console.log("this.errors:" + this.errors);
        }
    },

refreshCourseDetails() {	
	CourseDataService.retrieveCourse(this.INSTRUCTOR, this.id).then(res => {		
		this.description = res.data.description;
    });
    
    },
  },    

  created() {       // called during component load
    this.refreshCourseDetails();
  }
};
</script>

<style>
</style>