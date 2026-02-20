package ru.itis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "ru.itis.spring")
public class Application {
    static void main() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        // Create
        UserService service = context.getBean("userService", UserService.class);
        long id = service.register("jane");
        System.out.println(id);

        // Read
        UserEntity entity = service.get(1);
        System.out.println(entity);

        // Update
        service.updateUsername(entity.getUserId(), entity.getName() + entity.getUserId());
        System.out.println(service.get(1));

        // Delete
        service.remove(id);
    }
}
