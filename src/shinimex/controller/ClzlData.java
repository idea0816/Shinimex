package shinimex.controller;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年9月7日
 *
 */
public class ClzlData {

	private String cldh;
	private String zwpm;
	private String ywpm;
	private Double cldj;
	
	public ClzlData(){}

	public ClzlData(String cldh, String zwpm, String ywpm, Double cldj) {
		super();
		this.cldh = cldh;
		this.zwpm = zwpm;
		this.ywpm = ywpm;
		this.cldj = cldj;
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

	public String getYwpm() {
		return ywpm;
	}

	public void setYwpm(String ywpm) {
		this.ywpm = ywpm;
	}

	public Double getCldj() {
		return cldj;
	}

	public void setCldj(Double cldj) {
		this.cldj = cldj;
	}

}
