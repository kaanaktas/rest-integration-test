package com.sps.utils;

public class Person {
    private String name;
    private String surname;
    private String city;
    private CityDetail cityDetail;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

	public void setCityDetail(CityDetail cityDetail) {
		this.cityDetail = cityDetail;
	}

	public CityDetail getCityDetail() {
		return cityDetail;
	}

	@Override
	public String toString() {
		return getName()+" "+getSurname()+" "+getCity()+" "+getCityDetail().getCounty()+" "+getCityDetail().getPostCode();
	}
}
