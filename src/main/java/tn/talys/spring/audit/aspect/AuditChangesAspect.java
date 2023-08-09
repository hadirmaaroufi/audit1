//package tn.talys.spring.audit.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import tn.talys.spring.audit.models.DiffRequest;
//import tn.talys.spring.audit.models.User;
//import tn.talys.spring.audit.serviceInterface.ChangeLogService;
//
//@Aspect
//@Component
//public class AuditChangesAspect {
//
//    @Autowired
//    private ChangeLogService changeLogService;
//
//    @AfterReturning(value = "@annotation(tn.talys.spring.audit.aspect.AuditChanges)", returning = "result")
//    public void afterReturningAuditChanges(JoinPoint joinPoint, Object result) throws Throwable {
//        if (args.length > 0 && args[0] instanceof DiffRequest) {
//            DiffRequest diffRequest = (DiffRequest) args[0];
//
//            // You may need to get the user information from the security context or somewhere else
//            User user = new User();
//            user.setId(1L);
//            user.setUsername("username");
//
//            // Set the user information in the diffRequest
//            diffRequest.setUser(user);
//
//            // Call the auditChanges method in the ChangeLogService
//            changeLogService.auditChanges(diffRequest);
//        }
//    }
//}
//
