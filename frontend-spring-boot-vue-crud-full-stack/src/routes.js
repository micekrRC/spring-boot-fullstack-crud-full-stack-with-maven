

/*
Implementing Routing
When the user clicks the update course button on the 
course listing page, we would want to route to the course page. 
How do we do it? That’s where Routing comes into the picture.


We are defining a Router around all the components and configuring paths to each of them.

http://localhost:8081/ takes you to home page
http://localhost:8081/courses takes you to course listing page
http://localhost:8081/courses/2 takes you to course page

*/
import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
  mode: "history", // Use browser history
  
  routes: [
    {
      path: "/",
      name: "Home",
// import ListCoursesComponent from "./ListCoursesComponent";

      component: () => import("./components/ListCoursesComponent")
//      component: () => import("./components/Courses")      
    },
    {
      path: "/courses",
      name: "Courses",
      component: () => import("./components/ListCoursesComponent")
//      component: () => import("./components/Courses")      

    },
    {
      path: "/courses/:id",
      name: "Course Details",
//      CourseComponent

      component: () => import("./components/CourseComponent")
     // component: () => import("./components/Course")
    }
  ]
});

export default router;