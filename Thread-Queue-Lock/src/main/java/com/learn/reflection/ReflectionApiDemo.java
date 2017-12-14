package com.learn.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectionApiDemo {

	public static void main(String[] args) throws Exception {
		//通过反射机制构建一个Employee对象
		Class<?> classType = Class.forName("com.learn.reflection.Employee");
		
		//获取构造方法，实例化对象
		
		//1调用无参数构造方法
		Employee employee = (Employee) classType.newInstance();
		
		//2调用指定的构造方法(无参数构造方法)
		/*Constructor<?> constructor = classType.getConstructor(new Class[]{});
		Employee employee2 = (Employee) constructor.newInstance(new Object[]{});
		System.out.println(employee2);*/
		
		//3调用指定的构造方法(带参数构造方法)
		Constructor<?> constructor = classType.getConstructor(new Class[]{String.class,int.class});
		Employee employee2 = (Employee) constructor.newInstance(new Object[]{"zhangsan",30});
		System.out.println(employee2.getName()+":" + employee2.getAge());
		
		//获取class对象所有方法，包括私有
		/*Method[] methods = classType.getDeclaredMethods();
		for(Method method:methods){
			System.out.println(method.getName() + ":"+method.getModifiers()+":"+method.getReturnType());
		}*/
		
		//获取class对象指定方法，包括私有
		
		/*Method method = classType.getDeclaredMethod("toString",new Class[]{});
		System.out.println(method);
		//方法调用
		String desc = (String)method.invoke(employee2, new Object[]{});
		System.out.println("desc:" + desc);*/
		
		//获取class对象指定方法，包括私有
		/*Method method = classType.getDeclaredMethod("work",new Class[]{});
		System.out.println(method);*/
		
		//方法调用,调用私有方法
		/*method.setAccessible(true); //设置私有方法，可以访问
		String desc = (String)method.invoke(employee2, new Object[]{});*/
		
		
		//获取属性
		Field field = classType.getDeclaredField("name");
		field.setAccessible(true);
		field.set(employee, "李四");
		System.out.println(field.get(employee));
		
		
	}
	
	
}

class Employee {
	private String name;
	private int age;

	public Employee(){
		System.out.println("无参数构造");
	}
	
	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	private void work(){
		System.out.println("working....");
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + "]";
	}

	
}
