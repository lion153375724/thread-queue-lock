package com.learn.reflection;

import java.lang.reflect.Array;

public class RelectionArrayDemo {
	public static void main(String[] args) throws Exception {
		//创建一个一维数组
		/*Class<?> classType = Class.forName("java.lang.String");
		Object array = Array.newInstance(classType, 5);
		Array.set(array, 0, "abc");
		System.out.println(Array.get(array, 0));*/
		
		//创建二维数据
		int [] dimens = {3,3};
		Object array = Array.newInstance(int.class, dimens);
		Object arrayObj = Array.get(array, 2);//获取第3行
		Array.set(arrayObj, 2, 10); //给指定位置赋值
		System.out.println(Array.get(arrayObj, 2));
	}
}
