package com.learn.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReflectionAnnotationDemo {
	public static void main(String[] args) throws Exception {
		Class<?> classType = Class.forName("com.learn.annotation.AnnotationTest");
		if(classType.isAnnotationPresent(Description.class)){
			Description description = classType.getAnnotation(Description.class);
			System.out.println("description.value():" + description.value());
		}
		Method[] methods = classType.getDeclaredMethods();
		for(Method method : methods){
			if(method.isAnnotationPresent(Author.class)){
				Author author = method.getAnnotation(Author.class);
				System.out.println("author.name():" + author.name());
				System.out.println("author.group():" + author.group());
			}
		}
		
		Collection c;
		List s = new ArrayList();
	}
}

@Description(value="这是一个用来测试annotation的类")
class AnnotationTest{
	
	@Author(name="jason",group="gd")
	public void test(){
		System.out.println("test over...");
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
@Documented
@interface Author{
	String name();
	String group();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@interface Description{
	String value();
}
