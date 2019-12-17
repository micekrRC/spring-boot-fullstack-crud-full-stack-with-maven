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

    validateAndSubmit(e) {

        // this console call is working (aka: no compile error)
        console.log({
        id: this.id,
        description: this.description
        })  

        e.name;
    },


    refreshCourseDetails() {
        CourseDataService.retrieveCourse(this.INSTRUCTOR, this.id).then(res => {
          this.description = res.data.description;
        });
    },
  },
    // test comment

  created() {
    this.refreshCourseDetails();
  }
};
</script>

<style>
</style>