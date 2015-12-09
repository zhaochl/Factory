package com.zcl.factory;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.Test;


public class FactoryTest {

	/**
	 * @throws Exception
	 */
	/*@Test
	public void testFactoryDb() throws Exception {
		FactoryDb db = new FactoryDb("u-mysql","root","root","db_user");
		String [] tables =db.getTbs();
		for(String table:tables){
			System.out.println(table);
			System.out.println("--");
		}
	}
	
	@Test
	public void FactoryDbMgr() throws Exception {
		FactoryDb db = new FactoryDb("u-mysql","root","root","db_user");
		FactoryDbMgr dm = new FactoryDbMgr(db);
		String sql = "desc tb_user";
		dm.setSql(sql);
		ResultSet rs = dm.getQueryResult();
		while (rs.next()){
			System.out.println(rs.getString(1));
			
		}
	}
	@Test
	public void testFactoryTb() throws Exception {
		FactoryDb db = new FactoryDb("u-mysql","root","root","db_user");
		FactoryDbMgr dm = new FactoryDbMgr(db);
		FactoryTb tb = new FactoryTb(dm,"tb_user");
		tb.printTb();
		
	}
	@Test
	public void testFactoryPythonTb() throws Exception {
		FactoryDb db = new FactoryDb("u-mysql","root","root","db_user");
		FactoryDbMgr dm = new FactoryDbMgr(db);
		FactoryPythonVo tb = new FactoryPythonVo(dm,"tb_user");
		tb.printTb();
		
	}*/
	public static void  print(Object a) {
		System.out.println(a);
	}
	@Test
	public void testFactoryPythonTb() throws Exception {
		FactoryDb db = new FactoryDb("u-mysql","root","root","wutong");
		FactoryDbMgr dm = new FactoryDbMgr(db);
		FactoryPythonVo tb = new FactoryPythonVo(dm,"project_operation");
		tb.printTb();
		
		String addStr ="";
		String fields[]=tb.getFields();
		for(int i=1;i<fields.length;i++) {
			addStr +=fields[i];
			if(i<fields.length-1){
				addStr+=",";
			}
		}
		print(addStr);
		
	}
}
