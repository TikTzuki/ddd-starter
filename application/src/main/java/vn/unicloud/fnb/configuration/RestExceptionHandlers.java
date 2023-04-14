package vn.unicloud.fnb.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandlers {
//    @ExceptionHandler({AggregateNotFoundException.class})
//    public ResponseStatusException handle(AggregateNotFoundException e) {
//        log.error(e.getMessage());
//        return new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
//    }

//    @ExceptionHandler({CommandExecutionException.class})
//    public ResponseStatusException handle(CommandExecutionException e) {
//        log.error(e.getMessage(), e);
//        RemoteNonTransientHandlingException t = (RemoteNonTransientHandlingException) e.getCause();
//        log.error(String.valueOf(t.getCause()));
//        log.error(String.valueOf(t.getExceptionDescriptions()));
//        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
//    }

}
