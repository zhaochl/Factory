package com.zcl.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;





public class FactoryDbMgr {

	/**
	 * @param args
	 */
	private Connection conn;//设置SQL语句
	private String sql;//设置查询条件
	private String params[];//设置参数
	private ResultSet queryResult;//查询结果
	private int updateResult;
	private FactoryDb dbInstance;
	public FactoryDbMgr(){//构造函数中已经构造连接
		try{
			//Db db = new Db();
			//Class.forName("com.mysql.jdbc.Driver");
			//this.conn=DriverManager.getConnection(db.getDburl(),db.getDbuser(),db.getDbpassword());
			
			if(this.dbInstance==null) {
				throw new Exception("DbMgr not initial with a db,use setter Method to set one");
			}
			this.conn = this.dbInstance.getDbconn();
			conn.setAutoCommit(false);
			//System.out.println("create DbManager success");
		}catch(Exception e){
			System.out.println("create DbManager fail");
			e.printStackTrace();
		}
	}
	public FactoryDbMgr(FactoryDb db){
		try{
			this.dbInstance = db;
			if(this.dbInstance==null) {
				
				throw new Exception("DbMgr not initial with a db,use setter Method to set one");
			}
			this.conn = this.dbInstance.getDbconn();
			conn.setAutoCommit(false);
			//System.out.println("create DbManager success");
		}catch(Exception e){
			System.out.println("create DbManager fail");
			e.printStackTrace();
		}
	}
	public FactoryDbMgr(String host,String dbname,String username,String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dburl = "jdbc:mysql://"+host+":3306/"+dbname+"?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
			this.conn = DriverManager.getConnection(dburl,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setSql(String sql){
		this.sql=sql;
	}
	public String getSql(){
		return sql;
	}
	public FactoryDb getDbInstance() {
		return dbInstance;
	}
	public void setDbInstance(FactoryDb dbInstance) {
		this.dbInstance = dbInstance;
	}
	public void setParams(String[]params){
		this.params=params;
	}
	public String[]getParams(){
		return this.params;
	}

	public ResultSet getQueryResult(){	//获取查询结果
		try{
			PreparedStatement ps=conn.prepareStatement(sql, java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setString(i+1, params[i]);//setString从第一列开始
				}
			}
			queryResult=ps.executeQuery();//执行查询，并将结果返回
			return queryResult;
		}catch(Exception e){
			//System.out.println("Query fail;error:"+e.toString());
			return null;
			/*try {
				throw new Exception("Query fail");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			//e.printStackTrace();
		}
	}	
	public int getUpdateResult(){//插入数据
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setString(i+1, params[i]);
				}
			}
			updateResult=ps.executeUpdate();//执行新增数据库操作
			ps.close();
			conn.commit();//确认提交
			//conn.close();
		}catch(Exception e){
			System.out.println("变更数据失败");
			e.printStackTrace();
			try{
				conn.rollback();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return updateResult;
	}
	
	public void deleteDbManager(){
		try{
			if(this.queryResult!=null){
				if(!this.queryResult.isClosed()){
					this.queryResult.close();
				}
			}
			if(this.conn!=null){
				if(!this.conn.isClosed()){
					this.conn.close();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws SQLException{
		
		
		
	}
	
}