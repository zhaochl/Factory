package com.zcl.factory;

import java.sql.*;

public class FactoryDb {

	/**
	 * @param args
	 */
	private String dbname;
	//dbname
	private  String dbdriver;
	//dburlURL
	private  String dburl;
	
	private String host;
	//数据库连接
	private  String dbuser;
	//数据库用户名
	private  String dbpassword;
	//数据库密码
	private String[] tbs ;
	private Connection dbconn;
	private int tbcount;
	/*public FactoryDb(){
		try{
			this.dbname = "db_user";
			this.host = "localhost";
			this.dbdriver = "com.mysql.jdbc.Driver";
			this.dburl = "jdbc:mysql://"+host+":3306/"+this.getDbname()+"?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
			this.dbuser = "root";
			this.dbpassword = "root";
			Class.forName(dbdriver);
			this.dbconn = DriverManager.getConnection(this.dburl,this.dbuser,this.dbpassword);
			//System.out.println("create DateBase success");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("create DateBase faile");
		}
	}*/
	public FactoryDb(String db){
		try{
			this.dbname = db;
			this.host = "localhost";
			this.dbdriver = "com.mysql.jdbc.Driver";
			this.dburl = "jdbc:mysql://"+host+":3306/"+this.getDbname()+"?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
			this.dbuser = "root";
			this.dbpassword = "root";
			Class.forName(dbdriver);
			this.dbconn = DriverManager.getConnection(this.dburl,this.dbuser,this.dbpassword);
			//System.out.println("create DateBase success");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("create DateBase faile");
		}
	}
	/**
	 * mysql -h localhost -uroot -p123456 db1
	 * @param host
	 * @param user
	 * @param password
	 * @param db
	 */
	public FactoryDb(String host,String user,String password,String db){
		try{
			this.dbname = db;
			this.host = host;
			this.dbdriver = "com.mysql.jdbc.Driver";
			this.dburl = "jdbc:mysql://"+host+":3306/"+this.getDbname()+"?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
			this.dbuser = user;
			this.dbpassword = password;
			Class.forName(dbdriver);
			this.dbconn = DriverManager.getConnection(this.dburl,this.dbuser,this.dbpassword);
			//System.out.println("create DateBase success");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("create DateBase faile");
		}
	}
	public FactoryDb(String dbname,String dbdriver ,String dburl,String dbuser,String dbpassword){
		try{	
			this.dbname = dbname;
			this.dbdriver = dbdriver;
			this.dburl = dburl;
			this.dbuser = dbuser;
			this.dbname = dbpassword;
			Class.forName(dbdriver);
			this.dbconn = DriverManager.getConnection(dburl,dbuser,dbpassword);
			
			//System.out.println("Db/create DateBase success");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Db/create DateBase faile");
		}
	}
	public void setDbname(String dbname){
		this.dbname = dbname;
	}
	public String getDbname(){
		return dbname;
	}
	public void setDbdriver(String dbdriver){
		this.dbdriver = dbdriver;
	}
	public String getDbdriver(){
		return this.dbdriver;
	}
	public void setDburl(String dburl){
		this.dburl = dburl;
	}
	public String getDburl(){
		return this.dburl;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setDbuser(String dbuser){
		this.dbuser = dbuser;
	}
	public String getDbuser(){
		return dbuser;
	}
	public void setDbpassword(String bdpassword){
		this.dbpassword = dbpassword;
	}
	public String getDbpassword(){
		return this.dbpassword;
	}	
	public void setTbs(String []tbs){
		this.tbs = tbs;
	}
	public String[] getTbs(){//获得所有的表
		try{
			//Class.forName(this.getDbdriver());
			//Connection conn=DriverManager.getConnection(this.getDburl(),this.getDbuser(),this.getDbpassword());
			Connection conn = this.getDbconn();
			String sql = "show tables";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();
			int i =0;
			while(rs.next()) i++;
			String []tables = new String[i];//??????е???
			i = 0;
			rs.first();
			do{
				tables[i] = rs.getString(1);
				i ++;
			}while(rs.next());
			//System.out.println("DB/get tables success");
			return tables;
		}catch(Exception e){
			System.out.println("Db/get tables fail");
			e.printStackTrace();
			return null;
		}
	}
	public int getTbscount(){
		try{
			Class.forName(this.getDbdriver());
			Connection conn=DriverManager.getConnection(this.getDburl(),this.getDbuser(),this.getDbpassword());
			String sql = "show tables;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			//ResultSetMetaData rsm = rs.getMetaData();
			
			//ResultSetMetaData   rsmd   =   rs.getMetaData();   
			//  int   column   =   rsmd.getColumnCount();
			int i =0;
			while(rs.next()) i++;
			System.out.print("Db/get tablesCount success");
			return i;
		}catch(Exception e){
			System.out.print("Db/get tablesCount fail");
			e.printStackTrace();
			return 0;
		}
	}
	public Connection getDbconn() {
		if(dbconn==null){
			try {
				return this.dbconn;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return dbconn;
	}
	public void setDbconn(Connection dbconn) {
		this.dbconn = dbconn;
	}
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FactoryDb db = new FactoryDb();
		String [] tables =db.getTbs();
		for(String table:tables){
			System.out.println(table);
			System.out.println("--");
		}
	}*/

}
