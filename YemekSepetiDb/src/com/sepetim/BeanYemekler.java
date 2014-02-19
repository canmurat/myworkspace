package com.sepetim;

import java.io.Serializable;

public class BeanYemekler implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int yemekId;
	private String yemekAd;
	private int yemekFiyat;
	private String yemekId1;
	private String yemegi_sil;
	
	/**
	 * @return the yemekId1
	 */
	public String getYemekId1() {
		return yemekId1;
	}

	/**
	 * @param yemekId1 the yemekId1 to set
	 */
	public void setYemekId1(String yemekId1) {
		
		if (yemekId1 != null)
			yemekId = Integer.parseInt(yemekId1);
		this.yemekId1 = yemekId1;
	}

	/**
	 * @return the yemegi_sil
	 */
	public String getYemegi_sil() {
		return yemegi_sil;
	}

	/**
	 * @param yemegi_sil the yemegi_sil to set
	 */
	public void setYemegi_sil(String yemegi_sil) {
		
		
		this.yemegi_sil = yemegi_sil;
	}

	public int getYemekId() {
		return yemekId;
	}
	
	public void setYemekId(int yemekId) {
		this.yemekId = yemekId;
	}
	
	public String getYemekAd() {
		return yemekAd;
	}
	
	public void setYemekAd(String yemekAd) {
		this.yemekAd = yemekAd;
	}
	
	public int getYemekFiyat() {
		return yemekFiyat;
	}
	
	public void setYemekFiyat(int yemekFiyat) {
		this.yemekFiyat = yemekFiyat;
	}
	public BeanYemekler()
	{
		
	}

}
