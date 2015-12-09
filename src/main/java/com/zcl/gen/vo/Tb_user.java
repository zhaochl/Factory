package com.zcl.gen.vo;
/*
	@about:
	@author zcl:
	@version:
*/

public class Tb_user{
	private int id;
	private String username;
	private String password;
	private int sex;
	private int gid;
	
	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setSex(int sex){
		this.sex = sex;
	}

	public int getSex(){
		return sex;
	}

	public void setGid(int gid){
		this.gid = gid;
	}

	public int getGid(){
		return gid;
	}

}