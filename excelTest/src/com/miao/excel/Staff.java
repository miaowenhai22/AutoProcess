/**
 * 
 */
package com.miao.excel;

/**
* @Description: 
* @author Miao
* @date 2016年1月12日 上午10:33:06
*
*/
public class Staff {
	private String name;
	private String id;
	private String company;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Staff(String name, String id, String company) {
		super();
		this.name = name;
		this.id = id;
		this.company = company;
	}

	
	
}
