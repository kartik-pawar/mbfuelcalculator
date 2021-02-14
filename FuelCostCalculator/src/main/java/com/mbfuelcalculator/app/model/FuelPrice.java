package com.mbfuelcalculator.app.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/*FuelPrice expires automatically after 24 hrs*/
@RedisHash(value="FuelPrice", timeToLive = 24*60*60)
public class FuelPrice implements Serializable {
	@Id
	private String city;
	private double price;
	private Date date;
	
	public FuelPrice(String city, double price) {
		super();
		this.city = city;
		this.price = price;
		this.date = new Date();
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "FuelPrice [city=" + city + ", price=" + price + ", date=" + date + "]";
	}
}
