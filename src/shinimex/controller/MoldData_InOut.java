package shinimex.controller;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2016年5月31日
 *
 */

public class MoldData_InOut {
	private String DGLB; // 異動類別
	private String KSDH; // 異動單號
	private String KSRQ; // 異動日期
	private Integer SL; // 入庫數量
	private Integer SL1; // 入庫數量

	public MoldData_InOut() {
	}

	public MoldData_InOut(String dGLB, String kSDH, String kSRQ, Integer sL,
			Integer sL1) {
		super();
		DGLB = dGLB;
		KSDH = kSDH;
		KSRQ = kSRQ;
		SL = sL;
		SL1 = sL1;
	}

	public String getDGLB() {
		return DGLB;
	}

	public void setDGLB(String dGLB) {
		DGLB = dGLB;
	}

	public String getKSDH() {
		return KSDH;
	}

	public void setKSDH(String kSDH) {
		KSDH = kSDH;
	}

	public String getKSRQ() {
		return KSRQ;
	}

	public void setKSRQ(String kSRQ) {
		KSRQ = kSRQ;
	}

	public Integer getSL() {
		return SL;
	}

	public void setSL(Integer sL) {
		SL = sL;
	}

	public Integer getSL1() {
		return SL1;
	}

	public void setSL1(Integer sL1) {
		SL1 = sL1;
	}

}
