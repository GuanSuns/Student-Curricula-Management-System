package guan.suns.repository;

import guan.suns.model.CoursePDM;
import guan.suns.model.CourseSelectionCompositeId;
import guan.suns.model.CourseSelectionPDM;
import guan.suns.model.StudentPDM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/5/31.
 */
public interface CourseSelectionRepository extends JpaRepository<CourseSelectionPDM, CourseSelectionCompositeId> {

    public CourseSelectionPDM findByCourseSelectionCompositeIdStudentIDAndCourseSelectionCompositeIdCourseID(StudentPDM studentID, CoursePDM courseID);
    public ArrayList<CourseSelectionPDM> findByCourseSelectionCompositeIdCourseID(CoursePDM courseID);

}
