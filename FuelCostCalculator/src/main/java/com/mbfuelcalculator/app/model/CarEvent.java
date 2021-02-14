package com.mbfuelcalculator.app.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import io.swagger.annotations.ApiModelProperty;

@RedisHash("CarEvent")
public class CarEvent implements Serializable{

	@Id
	@ApiModelProperty(access="hidden", hidden=true)
	private String username;
	private boolean fueling;
	private String city;
	private Date date;

	public CarEvent() {
		super();
	}
	public CarEvent(boolean fueling, String city, Date date, String username) {
		super();
		this.fueling = fueling;
		this.city = city;
		this.date = date;
		this.username = username;
	}
	public boolean isFueling() {
		return fueling;
	}
	public void setFueling(boolean fueling) {
		this.fueling = fueling;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "CarEvent [username=" + username + ", fueling=" + fueling + ", city=" + city + ", date=" + date + "]";
	}

}
