package guan.suns.response.responseConstant;

/**
 * Created by lenovo on 2016/5/23.
 */
public class ResponseString {

    public static final String CommonResponseFailDescription = "Fail";
    public static final String CommonResponseSuccessDescription = "Success";
    public static final String CommonResponseUserNotFoundDescription = "Exception: User Not Found";


    public static final String HttpServletRequestIOException = "HttpServletRequest: IO Error";
    public static final String JsonProcessingErrorException = "JsonProcessor: Json Format Error";

    public static final String LoginPasswordErrorException = "Exception: Password Error";
    public static final String LoginUserNotFindException = "Exception: Login User Not Found";

    public static final String CreateUserExistedException = "Exception: User Existed While Creating";
    public static final String UserInfoErrorException = "Exception: User Info Error";
    public static final String NotFoundUpdateUserDescription = "Exception: Fail To Update User, User Not Found";

    public static final String DeleteUserNotFoundException = "Exception: User Not Found";
    public static final String FailToDeleteStudentDescription = "Exception: Fail To Delete Student";
    public static final String FailToDeleteTeacherDescription = "Exception: Fail To Delete Teacher";

    public static final String CourseExistedExceptionDescription = "Exception: Course Existed";
    public static final String CourseNotFoundExceptionDescription = "Exception: Course Not Found";
    public static final String CourseTeacherNotFoundExceptionDescription = "Exception: Teacher Not Found";
    public static final String CourseInfoErrorExceptionDescription = "Exception: Course Info Error";
    public static final String TeacherCannotModifyScoreDescription = "Exception: Teacher Can Not Modify Existed Score";

    public static final String CourseSelectionExisted = "Exception: Course Has Been Selected";
    public static final String CourseNotSelectedDescription = "Exception: Course Not Selected";
    public static final String CourseSelectionInfoError = "Exception: Course Selection Info Error";
    public static final String StudentCanNotSelectCourseDescription = "Exception: Student Can Not Select This Course";

    public static final String GetStudentsDetailsByNameOrDepartmentOrClassNameQueryInfoError = "Exception: Query Info Error";



}
