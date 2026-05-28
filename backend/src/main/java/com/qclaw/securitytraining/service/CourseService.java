package com.qclaw.securitytraining.service;
import com.qclaw.securitytraining.entity.Course;
import com.qclaw.securitytraining.mapper.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepo;
    public List<Course> getAllCourses() { return courseRepo.findAll(); }
    public Course getCourseById(Long id) { return courseRepo.findById(id).orElse(null); }
    public Course saveCourse(Course c) { return courseRepo.save(c); }
    public void deleteCourse(Long id) { courseRepo.deleteById(id); }
}