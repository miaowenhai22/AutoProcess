/**
 * 
 */
package com.miao.developer.queue;

/**
* @Description: 
* @author Miao
* @date 2015��12��24�� ����4:51:32
*
*/
public class Task {
	enum State {
        NEW, RUNNING, FINISHED
    }

	private State state;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}
