package model;

import java.time.LocalDateTime;

public class CouponDTO {

	// 쿠폰번호
	private String CPID;

	// 회원 ID
	private String MID;

	// 적용제품의 번호
	private int PID;

	// 쿠폰이름
	private String couponName;

	// 쿠폰 유효기간
	// LocalDate 일까지 입력가능
	// LocalDateTime 초까지 입력가능
	private LocalDateTime period;

	// 쿠폰의 할인율
	private int discount;

	// 쿠폰의 사용여부
	private boolean use;

	// 쿠폰의 카테고리
	private String category;

	public String getCPID() {
		return CPID;
	}

	public void setCPID(String cPID) {
		CPID = cPID;
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

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public LocalDateTime getPeriod() {
		return period;
	}

	public void setPeriod(LocalDateTime period) {
		this.period = period;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public boolean isUse() {
		return use;
	}

	public void setUse(boolean use) {
		this.use = use;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "CouponDTO [CPID=" + CPID + ", MID=" + MID + ", PID=" + PID + ", couponName=" + couponName + ", period="
				+ period + ", discount=" + discount + ", use=" + use + ", category=" + category + "]";
	}

}
