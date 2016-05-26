package shinimex.controller;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2016年5月26日
 *
 */

public class MoldData {
	private String mjbh;// 模具編號
	private String lbdh;// 模具類型
	private String kfjc;// 客戶簡稱
	private String kfjc1;// 鞋廠簡稱
	private Integer mjsl;// 模具數量
	private String gbbh;// 鞋碼國別

	public MoldData() {
	}

	public MoldData(String mjbh, String lbdh, String kfjc, String kfjc1,
			Integer mjsl, String gbbh) {
		super();
		this.mjbh = mjbh;
		this.lbdh = lbdh;
		this.kfjc = kfjc;
		this.kfjc1 = kfjc1;
		this.mjsl = mjsl;
		this.gbbh = gbbh;
	}

	public String getMjbh() {
		return mjbh;
	}

	public void setMjbh(String mjbh) {
		this.mjbh = mjbh;
	}

	public String getLbdh() {
		return lbdh;
	}

	public void setLbdh(String lbdh) {
		this.lbdh = lbdh;
	}

	public String getKfjc() {
		return kfjc;
	}

	public void setKfjc(String kfjc) {
		this.kfjc = kfjc;
	}

	public String getKfjc1() {
		return kfjc1;
	}

	public void setKfjc1(String kfjc1) {
		this.kfjc1 = kfjc1;
	}

	public Integer getMjsl() {
		return mjsl;
	}

	public void setMjsl(Integer mjsl) {
		this.mjsl = mjsl;
	}

	public String getGbbh() {
		return gbbh;
	}

	public void setGbbh(String gbbh) {
		this.gbbh = gbbh;
	}

}
