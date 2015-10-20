package shinimex.controller;
/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年10月9日
 * 
 * 配方資料 新增 刪除 修改 使用
 *
 */
public class ClzlDatalist_NDU {
	
	private String lb;
	private String cldhz;
//	private String zwpm;
	private Double phr;
	private Double clyl;
	private Double cldj;
	private Double price;
	
	public ClzlDatalist_NDU(){}
	
	public ClzlDatalist_NDU(String lb, String cldhz, Double phr, Double clyl,
			Double cldj, Double price) {
		super();
		this.lb = lb;
		this.cldhz = cldhz;
		this.phr = phr;
		this.clyl = clyl;
		this.cldj = cldj;
		this.price = price;
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

	public Double getCldj() {
		return cldj;
	}

	public void setCldj(Double cldj) {
		this.cldj = cldj;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
