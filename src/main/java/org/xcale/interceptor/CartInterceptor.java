package org.xcale.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.xcale.service.TaskSchedulingService;
import org.xcale.task.DeleteCartTaskDefinition;

import java.util.Map;
import java.util.UUID;

@Component
public class CartInterceptor implements HandlerInterceptor {
    private final static String ID_NAME_REQUEST = "id";
    private final DeleteCartTaskDefinition deleteCartTaskDefinition;
    private final TaskSchedulingService taskSchedulingService;

    public CartInterceptor(DeleteCartTaskDefinition deleteCartTaskDefinition, TaskSchedulingService taskSchedulingService) {
        this.deleteCartTaskDefinition = deleteCartTaskDefinition;
        this.taskSchedulingService = taskSchedulingService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("Executing interceptor for cart path");
        System.out.println("URI: " +request.getRequestURI());

        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        if(!pathVariables.isEmpty()) {
            Long idCart = Long.parseLong(pathVariables.get(ID_NAME_REQUEST).toString());
            deleteCartTaskDefinition.setIdCart(idCart);
            taskSchedulingService.scheduleATask(UUID.randomUUID().toString().replace("-", ""), deleteCartTaskDefinition);
        }

        return true;
    }
}
