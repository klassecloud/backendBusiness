package cloud.klasse.backendbusiness.exception;

import cloud.klasse.backendbusiness.classroom.ClassroomNotFoundException;
import cloud.klasse.backendbusiness.result.ResultNotFoundException;
import cloud.klasse.backendbusiness.task.TaskNotFoundException;
import cloud.klasse.backendbusiness.teacher.TeacherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String catchAll (final Throwable throwable) {
        log.error("An Exception is thrown!", throwable);
        return throwable.getMessage();
    }


    @ExceptionHandler(TeacherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String teacherNotFoundHandler(TeacherNotFoundException ex) {
        log.error("An exception is thrown!", ex);
        return ex.getMessage();
    }

    @ExceptionHandler(ClassroomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String classroomNotFoundHandler(ClassroomNotFoundException ex) {
        log.error("An exception is thrown!", ex);
        return ex.getMessage();
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String taskNotFoundHandler(TaskNotFoundException ex) {
        log.error("An exception is thrown!", ex);
        return ex.getMessage();
    }

    @ExceptionHandler(ResultNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String resultNotFoundHandler(ResultNotFoundException ex) {
        log.error("An exception is thrown!", ex);
        return ex.getMessage();
    }
}
