package guan.suns.repository;

import guan.suns.model.CoursePDM;
import guan.suns.model.CourseSelectionCompositeId;
import guan.suns.model.CourseSelectionPDM;
import guan.suns.model.StudentPDM;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lenovo on 2016/5/31.
 */
public interface CourseSelectionRepository extends JpaRepository<CourseSelectionPDM, CourseSelectionCompositeId> {

    CourseSelectionPDM findByCourseSelectionCompositeIdStudentIDAndCourseSelectionCompositeIdCourseID(StudentPDM studentID, CoursePDM courseID);

}
