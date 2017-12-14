package com.learn.reflection.cls;

/**
 * 获取class的4种方式
 * @author jason
 * @createTime 2017年12月13日上午9:54:49
 */
public class ClassDemo {
	public static void main(String[] args) {
		//获取class
		//方法1：
		/*Employee employee = new Employee("zhangesan",32);
		Class<?> classType = employee.getClass();
		System.out.println("getName:" + classType.getName());
		System.out.println("getSuperclass:" + classType.getSuperclass());*/
		
		//方法2
		/*Class<?> classType = Employee.class;
		System.out.println("getName:" + classType.getName());
		System.out.println("getSuperclass:" + classType.getSuperclass());*/
		
		//方法3
		/*try {
			Class<?> classType = Class.forName("com.learn.relection.Employee");
			System.out.println("getName:" + classType.getName());
			System.out.println("getSuperclass:" + classType.getSuperclass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//方法4：获取基本数据类型
		/*Class<?> classType = int.class;
		System.out.println("getName:" + classType.getName());
		System.out.println("getSuperclass:" + classType.getSuperclass());//为空基本类型没有父类
*/		
		/*Class<?> classType = Integer.TYPE;//获取基本类型
		System.out.println("getName:" + classType.getName());
		System.out.println("getSuperclass:" + classType.getSuperclass());//为空基本类型没有父类
*/		
	}
}

class Employee {
	private String name;
	private int age;

	
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

}
