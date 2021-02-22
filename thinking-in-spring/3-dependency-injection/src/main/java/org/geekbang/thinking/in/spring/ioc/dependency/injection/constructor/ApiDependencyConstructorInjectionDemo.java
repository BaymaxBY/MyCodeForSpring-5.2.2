package org.geekbang.thinking.in.spring.ioc.dependency.injection.constructor;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.UserHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApiDependencyConstructorInjectionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ApiDependencyConstructorInjectionDemo.class);

        BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();

        applicationContext.registerBeanDefinition("userHolder", userHolderBeanDefinition);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";

        reader.loadBeanDefinitions(location);

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);

        applicationContext.close();

//
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        // 注册 Configuration Class（配置类）
////        applicationContext.register(ApiDependencySetterIntejectDemo.class);
//
//        BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();
//
//        applicationContext.registerBeanDefinition("userHolder", userHolderBeanDefinition);
//
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
//
//        String location = "classpath:/META-INF/dependency-lookup-context.xml";
//
//        reader.loadBeanDefinitions(location);
//
//        // 启动 Spring 应用上下文
//        applicationContext.refresh();
//
//        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
//
//        System.out.println(userHolder);
//
//        // 显示地关闭 Spring 应用上下文
//        applicationContext.close();
    }

    public static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        builder.addConstructorArgReference("superUser");
        return builder.getBeanDefinition();
    }

}
