package shinimex.controller;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2016年5月26日
 *
 */

public class MoldData {
	private String mjbh;// 模具編號
	private String lbdh;// 模具類型 = lbzls.zwsm = MJZLS.size
	private String kfjc;// 客戶簡稱
	private String kfjc1;// 鞋廠簡稱
	private Integer mjsl;// 模具數量
	private String gbbh;// 鞋碼國別
	private String bz1;// 備註一
	private String bz2;// 備註二

	public MoldData() {
	}

	public MoldData(String mjbh, String lbdh, String kfjc, String kfjc1,
			Integer mjsl, String gbbh, String bz1, String bz2) {
		super();
		this.mjbh = mjbh;
		this.lbdh = lbdh;
		this.kfjc = kfjc;
		this.kfjc1 = kfjc1;
		this.mjsl = mjsl;
		this.gbbh = gbbh;
		this.bz1 = bz1;
		this.bz2 = bz2;
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

	public String getBz1() {
		return bz1;
	}

	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}

	public String getBz2() {
		return bz2;
	}

	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}

}
