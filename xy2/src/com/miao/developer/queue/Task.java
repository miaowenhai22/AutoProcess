/**
 * 
 */
package com.miao.developer.queue;

/**
* @Description: 
* @author Miao
* @date 2015年12月24日 下午4:51:32
*
*/
public class Task {
	public enum State {
		NEW, RUNNING, FINISHED
	}

	private String id;
	private State state;
	private long time;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Task(String id,long time) {
		this.id = id;
		this.state = State.NEW;
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	

}
