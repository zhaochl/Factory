package com.zcl.gen.vo;
/*
	@about:
	@author zcl:
	@version:
*/

public class Page{
	private int page_id;
	private String page_name;
	private String page_url;
	private int site_id;
	private String short_description;
	private String description;
	private int create_time;
	private int update_time;
	private int is_deleted;
	private int delete_time;
	private String status;
	
	public void setPage_id(int page_id){
		this.page_id = page_id;
	}

	public int getPage_id(){
		return page_id;
	}

	public void setPage_name(String page_name){
		this.page_name = page_name;
	}

	public String getPage_name(){
		return page_name;
	}

	public void setPage_url(String page_url){
		this.page_url = page_url;
	}

	public String getPage_url(){
		return page_url;
	}

	public void setSite_id(int site_id){
		this.site_id = site_id;
	}

	public int getSite_id(){
		return site_id;
	}

	public void setShort_description(String short_description){
		this.short_description = short_description;
	}

	public String getShort_description(){
		return short_description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCreate_time(int create_time){
		this.create_time = create_time;
	}

	public int getCreate_time(){
		return create_time;
	}

	public void setUpdate_time(int update_time){
		this.update_time = update_time;
	}

	public int getUpdate_time(){
		return update_time;
	}

	public void setIs_deleted(int is_deleted){
		this.is_deleted = is_deleted;
	}

	public int getIs_deleted(){
		return is_deleted;
	}

	public void setDelete_time(int delete_time){
		this.delete_time = delete_time;
	}

	public int getDelete_time(){
		return delete_time;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

}