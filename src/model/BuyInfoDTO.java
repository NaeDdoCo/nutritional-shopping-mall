package model;

import java.sql.Date;

public class BuyInfoDTO {
	// PK
	private int BID;
	
	// 구매한 멤버
	private String MID;
	
	// 구매한 제품
	private int PID;
	
	// 적용한 쿠폰 (적용되지 않으면 null)
	private String CPID;
	
	// 주문번호
	private int orderNum;
	
	// 배송 상태 (주문완료 -> 배송중 -> 배송완료)
	private String deliStat;
	
	// 구매 수량
	private int cnt;
	
	// 구매 가격
	private int paymentPrice;
	
	// 구매 일자
	private Date buyDate;
	
	public int getBID() {
		return BID;
	}
	public void setBID(int bID) {
		BID = bID;
	}
	public String getMID() {
		return MID;
	}
	public void setMID(String mID) {
		MID = mID;
	}
	public int getPID() {
		return PID;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public String getCPID() {
		return CPID;
	}
	public void setCPID(String cPID) {
		CPID = cPID;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getDeliStat() {
		return deliStat;
	}
	public void setDeliStat(String deliStat) {
		this.deliStat = deliStat;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	
	@Override
	public String toString() {
		return "BuyInfoDTO [BID=" + BID + ", MID=" + MID + ", PID=" + PID + ", CPID=" + CPID + ", orderNum=" + orderNum
				+ ", deliStat=" + deliStat + ", cnt=" + cnt + ", paymentPrice=" + paymentPrice + ", buyDate=" + buyDate
				+ "]";
	}
	
}
