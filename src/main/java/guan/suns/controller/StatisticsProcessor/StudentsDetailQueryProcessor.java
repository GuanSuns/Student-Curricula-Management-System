package guan.suns.controller.StatisticsProcessor;

import guan.suns.exception.QueryInfoError;
import guan.suns.exception.UserInfoErrorException;
import guan.suns.exception.UserNotFoundException;
import guan.suns.model.StudentPDM;
import guan.suns.request.StudentRequest.GetStudentDetailsStatisticsRequest;
import guan.suns.response.responseConstant.ResponseIntStatus;
import guan.suns.response.responseConstant.ResponseString;
import guan.suns.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/6/5.
 */
public class StudentsDetailQueryProcessor {

    @Autowired
    private StudentService studentService;

    public ArrayList<StudentPDM> getStudentsMultiConditionSearch(GetStudentDetailsStatisticsRequest getStudentsDetailRequest) throws QueryInfoError {

        if(getStudentsDetailRequest==null) return null;
		
		ArrayList<StudentPDM> students;

        if(getStudentsDetailRequest.getStudentID()!=null){
			StudentPDM student;
			try{
				student = studentService.getStudentDetail(new StudentPDM(getStudentsDetailRequest.getStudentID(),"",getStudentsDetailRequest.getName(),null,getStudentsDetailRequest.getClassName(),getStudentsDetailRequest.getDepartment(),null,null));
                students = new ArrayList<>();
                students.add(student);
			}
			catch(UserInfoErrorException userInfoErrorException){
                userInfoErrorException.printStackTrace();
                throw new QueryInfoError();
			}
            catch (UserNotFoundException userNotFoundException){
                userNotFoundException.printStackTrace();
                throw new QueryInfoError();
            }

		}else{
			try{
                int queryMethod = 0;
                if(getStudentsDetailRequest.getName() != null){
                    queryMethod = queryMethod + 1 ;
                }
                if(getStudentsDetailRequest.getClassName()!= null){
                    queryMethod = queryMethod + 10;
                }
                if(getStudentsDetailRequest.getDepartment() != null){
                    queryMethod = queryMethod + 100;
                }

                switch (queryMethod){
                    case 0 :
                        students = studentService.getAllStudentsDetail();
                        break;
                    case 1 :
                        students = studentService.getStudentDetailByName(new StudentPDM("","",getStudentsDetailRequest.getName(),null,getStudentsDetailRequest.getClassName(),getStudentsDetailRequest.getDepartment(),null,null));
                        break;
                    case 10 :
                        students = studentService.getStudentDetailByClassName(new StudentPDM("","",getStudentsDetailRequest.getName(),null,getStudentsDetailRequest.getClassName(),getStudentsDetailRequest.getDepartment(),null,null));
                        break;
                    case 100 :
                        students = studentService.getStudentDetailByDepartment(new StudentPDM("","",getStudentsDetailRequest.getName(),null,getStudentsDetailRequest.getClassName(),getStudentsDetailRequest.getDepartment(),null,null));
                        break;
                    case 11 :
                        students = studentService.getStudentDetailByNameAndClassName(new StudentPDM("","",getStudentsDetailRequest.getName(),null,getStudentsDetailRequest.getClassName(),getStudentsDetailRequest.getDepartment(),null,null));
                        break;
                    case 101 :
                        students = studentService.getStudentDetailByNameAndDepartment(new StudentPDM("","",getStudentsDetailRequest.getName(),null,getStudentsDetailRequest.getClassName(),getStudentsDetailRequest.getDepartment(),null,null));
                        break;
                    case 110 :
                        students = studentService.getStudentDetailByClassNameAndDepartment(new StudentPDM("","",getStudentsDetailRequest.getName(),null,getStudentsDetailRequest.getClassName(),getStudentsDetailRequest.getDepartment(),null,null));
                        break;
                    case 111 :
                        students = studentService.getStudentDetailByNameAndClassNameAndDepartment(new StudentPDM("","",getStudentsDetailRequest.getName(),null,getStudentsDetailRequest.getClassName(),getStudentsDetailRequest.getDepartment(),null,null));
                        break;
                    default:
                        return null;
                }
            }
            catch(QueryInfoError queryInfoError){
                queryInfoError.printStackTrace();
                throw new QueryInfoError();
            }
		}

		return students;
    }
}
