package com.endofmaster.rest.exceptionhandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YQ.Huang
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class DefaultErrorController implements ErrorController {

    @Value("${server.error.path:${error.path:/error}}")
    String errorPath;

    @RequestMapping
    @ResponseBody
    public ResponseEntity<ErrorMessage> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        ErrorMessage message;
        if (status == HttpStatus.NOT_FOUND) {
            String desc = "Request uri '" + request.getAttribute("javax.servlet.error.request_uri").toString() + "' not found";
            desc = getErrorMessage(request, desc);
            message = new ErrorMessage(ErrorCode.NotFound, desc);
        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
            String desc = request.getAttribute("javax.servlet.error.message").toString();
            desc = getErrorMessage(request, desc);
            message = new ErrorMessage(ErrorCode.InternalServerError, desc);
        } else if (status == HttpStatus.UNAUTHORIZED) {
            String desc = "this api need login";
            desc = getErrorMessage(request, desc);
            message = new ErrorMessage(ErrorCode.Unauthorized, desc);
        } else {
            String desc = "Unknown error";
            desc = getErrorMessage(request, desc);
            message = new ErrorMessage(ErrorCode.InternalServerError, desc);
        }
        return new ResponseEntity<>(message, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    private String getErrorMessage(HttpServletRequest request, String orElse) {
        String errorMsg = (String) request.getAttribute("javax.servlet.error.message");
        return StringUtils.isEmpty(errorMsg) ? orElse : errorMsg;
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }
}
