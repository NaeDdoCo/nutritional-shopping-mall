package model;

public class ProductDTO {
	// PK
	private int PID;
	
	// 이름
	private String pName;
	
	// 원가
	private int costPrice;
	
	// 정가
	private int regularPrice;
	
	// 판매가
	private int sellingPrice;
	
	// 재고
	private int cnt;
	
	// 영양 성분
	private String ingredient;
	
	// 용법
	private String usage;
	
	// 유통기한
	private String exp;
	
	// 카테고리
	private String category;
	
	public int getPID() {
		return PID;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}
	public int getRegularPrice() {
		return regularPrice;
	}
	public void setRegularPrice(int regularPrice) {
		this.regularPrice = regularPrice;
	}
	public int getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "ProductDTO [PID=" + PID + ", pName=" + pName + ", costPrice=" + costPrice + ", regularPrice="
				+ regularPrice + ", sellingPrice=" + sellingPrice + ", cnt=" + cnt + ", ingredient=" + ingredient
				+ ", usage=" + usage + ", exp=" + exp + ", category=" + category + "]";
	}
	
}
