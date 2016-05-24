package guan.suns.response.responseConstant;

/**
 * Created by lenovo on 2016/5/23.
 */
public class ResponseString {

    public static final String CommonResponseFailDescription = "Fail";
    public static final String CommonResponseSuccessDescription = "Success";

    public static final String HttpServletRequestIOException = "HttpServletRequest: IO Error";
    public static final String JsonProcessingErrorException = "JsonProcessor: Json Format Error";

    public static final String LoginPasswordErrorException = "Exception: Password Error";
    public static final String LoginUserNotFindException = "Exception: User Not Found";

    public static final String CreateUserExistedException = "Exception: User Existed While Creating";
    public static final String UserInfoErrorException = "Exception: User Info Error";

    public static final String DeleteUserNotFoundException = "Exception: User Not Found";
    public static final String FailToDeleteStudentDescription = "Fial To Delete Student";
}
