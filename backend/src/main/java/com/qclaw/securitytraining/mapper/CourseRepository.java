package com.qclaw.securitytraining.mapper;
import com.qclaw.securitytraining.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseRepository extends JpaRepository<Course, Long> {}