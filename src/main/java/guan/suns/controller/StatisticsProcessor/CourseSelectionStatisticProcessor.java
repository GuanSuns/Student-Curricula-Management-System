package guan.suns.controller.StatisticsProcessor;

import guan.suns.model.CoursePDM;
import guan.suns.model.CourseSelectionPDM;
import guan.suns.repository.CourseRepository;
import guan.suns.repository.CourseSelectionRepository;
import guan.suns.request.GetCourseDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/6/7.
 */

@Component
public class CourseSelectionStatisticProcessor {

    @Autowired
    private CourseSelectionRepository courseSelectionRepository;
    @Autowired
    private CourseRepository courseRepository;


    public ArrayList<CourseSelectionPDM> getCourseSelectionCondition(GetCourseDetailRequest getCourseDetailRequest){
        ArrayList<CourseSelectionPDM> attendStudents = new ArrayList<>();
        attendStudents.clear();

        if(getCourseDetailRequest==null
                || (getCourseDetailRequest.getName()==null && getCourseDetailRequest.getId()==null)
                ) {
            return attendStudents;
        }

        CoursePDM course;
        if(getCourseDetailRequest.getId()!=null && !getCourseDetailRequest.getId().equals("")){

            course = courseRepository.findOne(getCourseDetailRequest.getId());
        }
        else if(getCourseDetailRequest.getName()!=null && !getCourseDetailRequest.getName().equals("")){

            ArrayList<CoursePDM> coursePDMs = courseRepository.findByCourseName(getCourseDetailRequest.getName());
            if(coursePDMs==null || coursePDMs.isEmpty()){
                return attendStudents;
            }
            course = coursePDMs.get(0);
        }
        else{
            return attendStudents;
        }

        ArrayList<CourseSelectionPDM> tempStudents = courseSelectionRepository.findByCourseSelectionCompositeIdCourseID(course);
        if(tempStudents==null || tempStudents.isEmpty()) return attendStudents;
        attendStudents.addAll(tempStudents);

        return attendStudents;
    }

}
