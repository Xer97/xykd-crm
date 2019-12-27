package bootcrm.handler;

import java.util.List;
import java.util.Objects;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import bootcrm.common.ServerResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ServerResponse<String> handleBindException(Exception exception) {
		if (exception instanceof BindException) {
			List<FieldError> fieldErrors = ((BindException) exception).getFieldErrors();
			if (!CollectionUtils.isEmpty(fieldErrors)) {
				for (FieldError fieldError : fieldErrors) {
					// 只返回第一个参数绑定错误信息
					if (!Objects.isNull(fieldError) && !Objects.isNull(fieldError.getDefaultMessage())) {
						return ServerResponse.createByErrorMessage(fieldError.getDefaultMessage());
					}
				}
			}
		}
		return ServerResponse.createByErrorMessage(exception.getMessage());
	}
}
