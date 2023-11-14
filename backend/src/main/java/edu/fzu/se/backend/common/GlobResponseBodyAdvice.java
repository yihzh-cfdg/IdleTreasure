package edu.fzu.se.backend.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fzu.se.backend.common.response.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "edu.fzu.se.backend.controller")
public class GlobResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        return !returnType.getGenericParameterType().equals(ResultMessage.class);
    }
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof String)
        {
            try {
                return objectMapper.writeValueAsString(ResultMessage.success(body));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(body instanceof ResultMessage){
            return body;
        }
        return ResultMessage.success(body);
    }
}
