package skypro.course_2.exception;

public class ExceptionAlreadyQuestionIsBusy extends RuntimeException{
    public ExceptionAlreadyQuestionIsBusy(String message) {
        super(message);
    }
}
