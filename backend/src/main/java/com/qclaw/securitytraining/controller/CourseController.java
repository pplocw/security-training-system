package com.qclaw.securitytraining.controller;
import com.qclaw.securitytraining.dto.ApiResponse;
import com.qclaw.securitytraining.entity.Course;
import com.qclaw.securitytraining.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @GetMapping("/list")
    public ApiResponse<List<Course>> list() { return ApiResponse.success(courseService.getAllCourses()); }
    @GetMapping("/{id}")
    public ApiResponse<Course> detail(@PathVariable Long id) { return ApiResponse.success(courseService.getCourseById(id)); }
    @PostMapping("/save")
    public ApiResponse<Course> save(@RequestBody Course course) { return ApiResponse.success(courseService.saveCourse(course)); }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) { courseService.deleteCourse(id); return ApiResponse.success(null); }
}