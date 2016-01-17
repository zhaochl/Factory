package com.zcl.factory;

import java.awt.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SQLFactory2016 {
	private String tbName;
	private ArrayList fieldString;
	private ArrayList fields;
	private ArrayList types;
	//private String primaryKey;
	private StringBuffer content;
	public static String baseDirName ="src/"; 
	public SQLFactory2016(String tbName) {
		this.tbName = tbName;
		fieldString = new ArrayList();
		fields = new ArrayList();
		types = new ArrayList();
		content = new StringBuffer();
		content.append("drop table if exists "+tbName+";\n");
		content.append("/*==============================================================*/\n");
		content.append("/* Table: "+tbName+"                                           */\n");
		content.append("/*==============================================================*/\n");
		content.append("create table "+tbName+"\n");
		content.append("(\n");
	}
	public void addField(String field,String type) {
		fields.add(field);
		if(fieldString.size()==0){
			fieldString.add(field+"               int(10) not null AUTO_INCREMENT,");
		}else{
			if(type.toLowerCase().contains("int")){
				//fields.add("create_time          int(10),");
				fieldString.add(field+"          int(10),");
			}else if(type.toLowerCase().contains("string") || type.toLowerCase().contains("char")){
				//fields.add("keyword              varchar(255),");
				fieldString.add(field+"              varchar(255),");
			}
		}
	}
	
	public void printSQL(){
		
		for(int i=0;i<fieldString.size();i++){
			content.append(fieldString.get(i)+"\n");
		}
		
		content.append("create_time          int(10),\n");
		content.append("update_time          int(10),\n");
		content.append("status               int(10),\n");
		content.append("type                 int(10),\n");
		content.append("description          varchar(255),\n");
		content.append("uid                  int(10),\n");
		content.append("is_deleted           int(10),\n");
		content.append("deleted_time         int(10),\n");
		content.append("primary key ("+fields.get(0)+")\n");
		content.append(") ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=39;\n");
		content.append("\n");
		System.out.println(content);
		try {
			String filename = baseDirName + tbName+ ".sql";
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			osw.write(content.toString());
			osw.close();
			System.out.println("generate " + filename + " complete!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SQLFactory2016 sql = new SQLFactory2016("sp_todolist");
		sql.addField("todoid", "int");
		sql.addField("content", "string");
		sql.printSQL();
	}
}
