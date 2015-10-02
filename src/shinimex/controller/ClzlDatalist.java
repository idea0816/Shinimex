package shinimex.controller;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年10月1日
 * 
 * For Formula List 
 *
 */
public class ClzlDatalist {
	private String lb;
	private String cldhz;
	private String zwpm;
	private Double phr;
	private Double clyl;
	private Double listcldj;
	private Double amount;

	public ClzlDatalist() {
	}

	public ClzlDatalist(String lb, String cldhz, String zwpm, Double phr,
			Double clyl, Double listcldj, Double amount) {
		super();
		this.lb = lb;
		this.cldhz = cldhz;
		this.zwpm = zwpm;
		this.phr = phr;
		this.clyl = clyl;
		this.listcldj = listcldj;
		this.amount = amount;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getCldhz() {
		return cldhz;
	}

	public void setCldhz(String cldhz) {
		this.cldhz = cldhz;
	}

	public String getZwpm() {
		return zwpm;
	}

	public void setZwpm(String zwpm) {
		this.zwpm = zwpm;
	}

	public Double getPhr() {
		return phr;
	}

	public void setPhr(Double phr) {
		this.phr = phr;
	}

	public Double getClyl() {
		return clyl;
	}

	public void setClyl(Double clyl) {
		this.clyl = clyl;
	}

	public Double getListcldj() {
		return listcldj;
	}

	public void setListcldj(Double listcldj) {
		this.listcldj = listcldj;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
