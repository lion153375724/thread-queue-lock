package com.learn.annotation;

@MyAnnotation1
public class MyAnnotation {
	
	@MyAnnotation2("jason")
	public static void main(String[] args) {
		@MyAnnotation3(name = "jason")
		int number = 10;
	}
}


//标记注解
@interface MyAnnotation1{
	
}

//标记注解
@interface MyAnnotation2{
	String value() default "zhangsan";
}

//标记注解
@interface MyAnnotation3{
	String name() default "zhangsan";
}
