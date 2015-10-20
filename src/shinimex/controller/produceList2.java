package shinimex.controller;

/*
 * @author Eric Chen.,CPY
 * @version Create Time:2015年10月12日
 *
 *生產 入庫 資料表
 */
public class produceList2 {
	private String Xiexing;
	private String SheHao;
	private String Size;
	private Double Qty;

	public produceList2() {
	}

	public produceList2(String xiexing, String sheHao, String size, Double qty) {
		super();
		Xiexing = xiexing;
		SheHao = sheHao;
		Size = size;
		Qty = qty;
	}

	public String getXiexing() {
		return Xiexing;
	}

	public void setXiexing(String xiexing) {
		Xiexing = xiexing;
	}

	public String getSheHao() {
		return SheHao;
	}

	public void setSheHao(String sheHao) {
		SheHao = sheHao;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public Double getQty() {
		return Qty;
	}

	public void setQty(Double qty) {
		Qty = qty;
	}

}
