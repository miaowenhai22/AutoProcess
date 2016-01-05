/**
 * 
 */
package com.miao.test;

import com.miao.developer.queue.Task;
import com.miao.developer.queue.TaskQueue;

/**
 * @Description:
 * @author Miao
 * @date 2015��12��15�� ����4:34:26
 *
 */
public class QueueTest {

	public static void main(String[] args) {

		TaskQueue taskQueue = new TaskQueue();
		// ��������

		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("******************����"+System.currentTimeMillis()+"start*******************");
					for (int i = 0; i <= 20; i++) {
						taskQueue.addTask(new Task(Thread.currentThread().getName() + i, System.currentTimeMillis()));
						Thread.sleep(10);
					}
					System.out.println("******************����"+System.currentTimeMillis()+"end*******************");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "����").start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("******************�ɼ�"+System.currentTimeMillis()+"start*******************");
				for (int i = 0; i <= 20; i++) {
					taskQueue.addTask(new Task(Thread.currentThread().getName() + i, System.currentTimeMillis()));
				}
				System.out.println("******************�ɼ�"+System.currentTimeMillis()+"end*******************");
			}
		}, "�ɼ�").start();

		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(30000);
					System.out.println("******************����"+System.currentTimeMillis()+"start*******************");
					for (int i = 0; i <= 20; i++) {
						taskQueue.addTask(new Task(Thread.currentThread().getName() + i, System.currentTimeMillis()));
					}
					System.out.println("******************����"+System.currentTimeMillis()+"end*******************");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "����").start();
		
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(31000);
					System.out.println("******************����"+System.currentTimeMillis()+"start*******************");
					System.out.println("���˵���");
					for (int i = 0; i <= 20; i++) {
						taskQueue.addTask(new Task(Thread.currentThread().getName() + i, System.currentTimeMillis()));
					}
					System.out.println("******************����"+System.currentTimeMillis()+"end*******************");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "����").start();

		new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						Task task = taskQueue.getTask();
						if (task != null) {
							System.out.println("��������:" + task.getId() + " time:" + task.getTime());
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
