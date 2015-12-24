/**
 * 
 */
package com.miao.developer.queue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
* @Description: 
* @author Miao
* @date 2015��12��24�� ����4:29:48
*
*/
public class TaskQueue {
	private List<Task> queue = new LinkedList<>();

	/**
	 * ��ӵ���Ϣ����
	 * @param task
	 */
	public synchronized void addTask(Task task) {
		if (task != null) {
			queue.add(task);
		}
	}

	/**
	 * ���������������������ɾ��
	 * @param task
	 */
	public synchronized void finishTask(Task task) {
		if (task != null) {
			task.setState(Task.State.FINISHED);
			queue.remove(task);
		}
	}

	/**
	 * ȡ��һ���ִ������
	 * @return
	 */
	public synchronized Task getTask() {
		Iterator<Task> it = queue.iterator();
		Task task;
		while (it.hasNext()) {
			task = it.next(); // Ѱ��һ���½�������
			if (Task.State.NEW.equals(task.getState())) { // ������״̬��Ϊ������
				task.setState(Task.State.RUNNING);
				return task;
			}
		}
		return null;
	}
}
