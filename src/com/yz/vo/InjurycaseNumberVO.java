package com.yz.vo;

public class InjurycaseNumberVO {

	private int itype;
	private int number1;
	private int number2;
	private int number3;
	private int totalNumber;

	public int getItype() {
		return itype;
	}

	public void setItype(int itype) {
		this.itype = itype;
	}

	public int getNumber1() {
		return number1;
	}

	public void setNumber1(int number1) {
		this.number1 = number1;
	}

	public int getNumber2() {
		return number2;
	}

	public void setNumber2(int number2) {
		this.number2 = number2;
	}

	public int getNumber3() {
		return number3;
	}

	public void setNumber3(int number3) {
		this.number3 = number3;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public InjurycaseNumberVO(int itype, int number1, int number2, int number3,
			int totalNumber) {
		this.itype = itype;
		this.number1 = number1;
		this.number2 = number2;
		this.number3 = number3;
		this.totalNumber = totalNumber;
	}

	public InjurycaseNumberVO() {
	}

}
