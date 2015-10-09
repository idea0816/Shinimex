package shinimex.controller;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年10月8日
 * 
 * Get cldh,zwpm,cldj,YYSL For 原物料
 *
 */
public class ClzlDatalist_cldhz {
	private String cldh;
	private String zwpm;
	private Double cldj;
	private Double YYSL;

	public ClzlDatalist_cldhz() {
	}

	public ClzlDatalist_cldhz(String cldh, String zwpm, Double cldj, Double yYSL) {
		super();
		this.cldh = cldh;
		this.zwpm = zwpm;
		this.cldj = cldj;
		this.YYSL = yYSL;
	}

	public String getCldh() {
		return cldh;
	}

	public void setCldh(String cldh) {
		this.cldh = cldh;
	}

	public String getZwpm() {
		return zwpm;
	}

	public void setZwpm(String zwpm) {
		this.zwpm = zwpm;
	}

	public Double getCldj() {
		return cldj;
	}

	public void setCldj(Double cldj) {
		this.cldj = cldj;
	}

	public Double getYYSL() {
		return YYSL;
	}

	public void setYYSL(Double yYSL) {
		this.YYSL = yYSL;
	}

}
