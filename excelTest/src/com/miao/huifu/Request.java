/**
 * 
 */
package com.miao.huifu;

/**
* @Description: 
* @author Miao
* @date 2016年1月18日 下午5:22:44
*
*/
public class Request {
	private String CmdId;// 命令
	private String CardNo;// 银行卡号
	private String CertId;// 身份证号
	// private String OpenAcctId;

	public String getCmdId() {
		return CmdId;
	}

	public void setCmdId(String cmdId) {
		CmdId = cmdId;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public String getCertId() {
		return CertId;
	}

	public void setCertId(String certId) {
		CertId = certId;
	}

}
