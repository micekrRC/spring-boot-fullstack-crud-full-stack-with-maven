<template>
  <div>
      <h3>Course</h3>
      <div>{{id}}</div>
      <div>{{description}}</div>
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
    id() {
      return this.$route.params.id;
    }
  },
  methods: {
    refreshCourseDetails() {
        CourseDataService.retrieveCourse(this.INSTRUCTOR, this.id).then(res => {
          this.description = res.data.description;
        });
    },
  },
  created() {
    this.refreshCourseDetails();
  }
};
</script>

<style>
</style>