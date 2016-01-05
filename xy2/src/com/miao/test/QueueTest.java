/**
 * 
 */
package com.miao.test;

import com.miao.developer.queue.Task;
import com.miao.developer.queue.TaskQueue;

/**
 * @Description:
 * @author Miao
 * @date 2015年12月15日 下午4:34:26
 *
 */
public class QueueTest {

	public static void main(String[] args) {

		TaskQueue taskQueue = new TaskQueue();
		// 增加任务

		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("******************监听"+System.currentTimeMillis()+"start*******************");
					for (int i = 0; i <= 20; i++) {
						taskQueue.addTask(new Task(Thread.currentThread().getName() + i, System.currentTimeMillis()));
						Thread.sleep(10);
					}
					System.out.println("******************监听"+System.currentTimeMillis()+"end*******************");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "监听").start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("******************采集"+System.currentTimeMillis()+"start*******************");
				for (int i = 0; i <= 20; i++) {
					taskQueue.addTask(new Task(Thread.currentThread().getName() + i, System.currentTimeMillis()));
				}
				System.out.println("******************采集"+System.currentTimeMillis()+"end*******************");
			}
		}, "采集").start();

		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(30000);
					System.out.println("******************传输"+System.currentTimeMillis()+"start*******************");
					for (int i = 0; i <= 20; i++) {
						taskQueue.addTask(new Task(Thread.currentThread().getName() + i, System.currentTimeMillis()));
					}
					System.out.println("******************传输"+System.currentTimeMillis()+"end*******************");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "传输").start();
		
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(31000);
					System.out.println("******************空运"+System.currentTimeMillis()+"start*******************");
					System.out.println("空运到达");
					for (int i = 0; i <= 20; i++) {
						taskQueue.addTask(new Task(Thread.currentThread().getName() + i, System.currentTimeMillis()));
					}
					System.out.println("******************空运"+System.currentTimeMillis()+"end*******************");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "空运").start();

		new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						Task task = taskQueue.getTask();
						if (task != null) {
							System.out.println("处理任务:" + task.getId() + " time:" + task.getTime());
							taskQueue.finishTask(task);
							Thread.sleep(500);
							System.out.println(taskQueue.queue.size());
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

}
