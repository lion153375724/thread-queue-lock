package com.learn.Thread.ThreedPool;

public class MyTask implements Runnable {
	private Integer taskId;
	private String taskName;

	public MyTask(Integer taskId,String taskName){
		this.taskId = taskId;
		this.taskName = taskName;
	}
	
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void run() {
		try {
			System.out.println("run taskId:"+taskId +",taskName:"+taskName);
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toString(){
		return Integer.toString(this.taskId);
	}
}
