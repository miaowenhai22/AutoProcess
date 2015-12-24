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
* @date 2015年12月24日 下午4:29:48
*
*/
public class TaskQueue {
	private List<Task> queue = new LinkedList<>();

	/**
	 * 添加到消息队列
	 * @param task
	 */
	public synchronized void addTask(Task task) {
		if (task != null) {
			queue.add(task);
		}
	}

	/**
	 * 完成任务后将它从任务队列中删除
	 * @param task
	 */
	public synchronized void finishTask(Task task) {
		if (task != null) {
			task.setState(Task.State.FINISHED);
			queue.remove(task);
		}
	}

	/**
	 * 取得一项待执行任务
	 * @return
	 */
	public synchronized Task getTask() {
		Iterator<Task> it = queue.iterator();
		Task task;
		while (it.hasNext()) {
			task = it.next(); // 寻找一个新建的任务
			if (Task.State.NEW.equals(task.getState())) { // 把任务状态置为运行中
				task.setState(Task.State.RUNNING);
				return task;
			}
		}
		return null;
	}
}
