package org.geekbang.thinking.in.spring.ioc.dependency.injection.qualifier;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 *
 * @author Baymax
 * @date 2021/3/7 0007 22:24
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User namedUser;

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers;

    @Bean
    @Qualifier
    public User user1() {
        return createUser(7L);
    }

    @Bean
    @Qualifier
    public User user2() {
        return createUser(8L);
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(10L);
    }

    private User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";

        reader.loadBeanDefinitions(location);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user = " + demo.user);

        System.out.println("demo.namedUser = " + demo.namedUser);

        System.out.println("demo.allUsers = " + demo.allUsers);

        System.out.println("demo.qualifiedUsers = " + demo.qualifiedUsers);

        System.out.println("demo.groupedUsers = " + demo.groupedUsers);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();

    }


}
