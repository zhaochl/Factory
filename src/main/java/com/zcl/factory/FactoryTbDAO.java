package com.zcl.factory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.chainsaw.Main;
/**
 * java tbDAO class generator.
 * @author zcl
 * @ 2016.1.17
 * update 
 */
public class FactoryTbDAO {

	private FactoryTb tb;
	public static String TbNameDAO;
	public static String tbNameDAO;
	public static String baseDirName = "src/";
//	private String tbdaoPath = "com/zcl/gen/dao";
	private StringBuffer content;
	public FactoryTb getTb() {
		return tb;
	}
	public void setTb(FactoryTb tb) {
		this.tb = tb;
	}
	public FactoryTbDAO(FactoryTb tb){
		this.tb = tb;
		TbNameDAO = TbNameDAO;
		tbNameDAO = tbNameDAO;
	}
	public FactoryTbDAO(FactoryTb tb,String prefix){
		this.tb = tb;
		String TbNamePHPTmp = tb.getTbname().substring(tb.getTbname().indexOf(prefix)+prefix.length());
		
		String t = tb.gettbname().substring(tb.gettbname().indexOf(prefix)+prefix.length());
		TbNameDAO = FactoryTool.UpperCaseFirstLetter(t);
		tbNameDAO = tb.gettbname().substring(tb.gettbname().indexOf(prefix)+prefix.length());
		System.out.println("TbNamePHP:"+TbNameDAO);
		System.out.println("tbNamePHP:"+tbNameDAO);
		
	}
	private StringBuffer printinsert(){
		String  fields[]=tb.getFields();
		String TYPES[]=tb.getTYPES();
		String getMethods[] = tb.getGetterMethods();
		StringBuffer content = new StringBuffer();
		content.append("public boolean insert"+TbNameDAO+"("+TbNameDAO+" "+tbNameDAO+"){\n");
		content.append("\t\tString sql = \"insert into " + tbNameDAO + " values(");
		for(int i = 0; i < fields.length - 1; i++){
					content.append("?,");
		}
		content.append("?)\";\n");
		content.append("\t\tString params[] = new String[" + fields.length + "];\n");
		for(int i = 0; i < fields.length; i++){
		content.append("\t\tparams[" + i + "] = ((" +TYPES[i]+")"+ getMethods[i] +")"+ ".toString();\n");
		}
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tdm.setParams(params);\n");
		content.append("\t\tint flag = dm.getUpdateResult();\n");
		content.append("\t\tif (flag!=0)\n\t\t\treturn true;\n\t\telse\n\t\t\treturn false;\n\t}");
		System.out.println(content);
		return content;
	}
	private StringBuffer printdelete(){
		String  fields[]=tb.getFields();
		String TYPES[]=tb.getTYPES();
		String getMethods[] = tb.getGetterMethods();
		StringBuffer content = new StringBuffer();
		content.append("public boolean delete"+TbNameDAO+"("+TbNameDAO+" "+tbNameDAO+"){\n");
		content.append("\t\tString sql = \"delete from " + tbNameDAO + " where " + fields[0]+"=\'\" + " + getMethods[0] + " + \"'\";\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tint flag = dm.getUpdateResult();\n");
		content.append("\t\tif (flag!=0)\n\t\t\treturn true;\n\t\telse\n\t\t\treturn false;\n\t}");
		System.out.println(content);
		return content;
	}
	private StringBuffer printupdate(){
		String  fields[]=tb.getFields();
		String TYPES[]=tb.getTYPES();
		String getMethods[] = tb.getGetterMethods();
		StringBuffer content = new StringBuffer();
		content.append("public boolean update"+TbNameDAO+"("+TbNameDAO+" "+tbNameDAO+"){\n");
		content.append("\t\tString sql = \"update " + tbNameDAO + " set ");
		for(int i = 1; i < fields.length; i++){
			content.append(fields[i] + "=?");
			if(i < fields.length - 1) content.append(",");
		}
		content.append(" where " + fields[0] + "=?\";\n");
		
		//String sql="update users set username=?,password=? where userid=?";
		//content.append("?)\";\n");
		content.append("\t\tString params[] = new String[" + fields.length + "];\n");
		for(int i = 0; i < fields.length-1; i++){
		content.append("\t\tparams[" + i + "] = " +"(("+ TYPES[i+1]+")"+ getMethods[i+1] + ").toString();\n");
		}
		content.append("\t\tparams[" + (fields.length-1) + "] = " +"(("+TYPES[0]+")" + getMethods[0] + ").toString();\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tdm.setParams(params);\n");
		content.append("\t\tint flag = dm.getUpdateResult();\n");
		content.append("\t\tif (flag!=0)\n\t\t\treturn true;\n\t\telse\n\t\t\treturn false;\n\t}");
		System.out.println(content);
		return content;
	}
	private StringBuffer printqueryByid(){
		String  fields[]=tb.getFields();
		String types[]=tb.getTypes();
		String setMethodsDetail[] = tb.getSetterMethodsDetail();
		StringBuffer content = new StringBuffer();
		content.append("public "+TbNameDAO+" queryByid"+TbNameDAO+"("+types[0]+" "+fields[0]+"){\n");
		content.append("\t\tString sql = \"select* from " + tbNameDAO + " where "+fields[0]+"=" + "\'\" + "+fields[0]+" + \"\'\";\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tResultSet rs = dm.getQueryResult();\n");
		content.append("\t\t"+TbNameDAO+" " + tbNameDAO + "= new "+TbNameDAO+"();\n");
		content.append("\t\ttry{\n");
		content.append("\t\t\tif(rs.next()){\n");
		for(int i = 0;i < fields.length;i++){
		content.append("\t\t\t"+setMethodsDetail[i]+";\n");
		}
		content.append("\t\t}\n");
		content.append("\t\t\tdm.deleteDbManager();\n");
		content.append("\t\t\treturn "+tbNameDAO+";\n");
		content.append("\t\t}catch(Exception e){\n");
		content.append("\t\t\te.printStackTrace();\n");
		content.append("\t\t\treturn null;\n");
		content.append("\t\t}\n");
		content.append("\t}");
		System.out.println(content);
		return content;
	}
	private StringBuffer printqueryBykey(){
		String  fields[]=tb.getFields();
		String types[]=tb.getTypes();
		String setMethodsDetail[] = tb.getSetterMethodsDetail();
		StringBuffer content = new StringBuffer();
		for(int j=1; j<fields.length;j++){
			content.append("public Vector queryBy"+fields[j]+TbNameDAO+"("+types[j]+" "+fields[j]+"){\n");
			content.append("\t\tString sql = \"select* from " + tbNameDAO + " where "+fields[j]+"=" + "\'\" + "+fields[j]+" + \"\'\";\n");
			content.append("\t\tDbManager dm = new DbManager();\n");
			content.append("\t\tdm.setSql(sql);\n");
			content.append("\t\tResultSet rs = dm.getQueryResult();\n");
			content.append("\t\t"+TbNameDAO+" " + tbNameDAO + "= new "+TbNameDAO+"();\n");
			content.append("\t\tVector v = new Vector();\n");
			content.append("\t\ttry{\n");
			content.append("\t\t\tif(rs.next()){\n");
			content.append("\t\t\t"+ tbNameDAO + "= new "+TbNameDAO+"();\n");
			for(int i = 0;i < fields.length;i++){
			content.append("\t\t\t"+setMethodsDetail[i]+";\n");
			}
			content.append("\t\t\tv.add(" + tbNameDAO + ");\n");
			content.append("\t\t}\n");
			content.append("\t\t\tdm.deleteDbManager();\n");
			content.append("\t\t\treturn v;\n");
			content.append("\t\t}catch(Exception e){\n");
			content.append("\t\t\te.printStackTrace();\n");
			content.append("\t\t\treturn null;\n");
			content.append("\t\t}\n");
			content.append("\t}\n");
		}
		System.out.println(content);
		return content;
	}
	private StringBuffer printqueryAll(){
		String  fields[]=tb.getFields();
		String types[]=tb.getTypes();
		String setMethodsDetail[] = tb.getSetterMethodsDetail();
		StringBuffer content = new StringBuffer();
		content.append("public Vector queryAll"+TbNameDAO+"(){\n");
		content.append("\t\tString sql = \"select* from " + tbNameDAO + "\";\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tResultSet rs = dm.getQueryResult();\n");
		content.append("\t\t"+TbNameDAO+" " + tbNameDAO + "= null ;\n");
		content.append("\t\tVector v = new Vector();\n");
		content.append("\t\ttry{\n");
		content.append("\t\t\twhile(rs.next()){\n");
		content.append("\t\t\t"+ tbNameDAO + "= new "+TbNameDAO+"();\n");
		for(int i = 0;i < fields.length;i++){
		content.append("\t\t\t"+setMethodsDetail[i]+";\n");
		}
		content.append("\t\t\tv.add(" + tbNameDAO + ");\n");
		content.append("\t\t}\n");
		content.append("\t\tdm.deleteDbManager();\n");
		content.append("\t\t\treturn v;\n");
		content.append("\t\t}catch(Exception e){\n");
		content.append("\t\t\te.printStackTrace();\n");
		content.append("\t\t\treturn null;\n");
		content.append("\t\t}\n");
		content.append("\t}");
		System.out.println(content);
		return content;
	}
	private StringBuffer printqueryAllDetail(){
		String  fields[]=tb.getFields();
		String types[]=tb.getTypes();
		String setMethodsDetail[] = tb.getSetterMethodsDetail();
		StringBuffer content = new StringBuffer();
		content.append("public Vector queryAllDetail"+TbNameDAO+"(String sql){\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tResultSet rs = dm.getQueryResult();\n");
		content.append("\t\t"+TbNameDAO+" " + tbNameDAO + "= new "+TbNameDAO+"();\n");
		content.append("\t\tVector v = new Vector();\n");
		content.append("\t\ttry{\n");
		content.append("\t\t\tif(rs.next()){\n");
		for(int i = 0;i < fields.length;i++){
		content.append("\t\t\t"+setMethodsDetail[i]+";\n");
		}
		content.append("\t\t\tv.add(" + tbNameDAO + ");\n");
		content.append("\t\t}\n");
		content.append("\t\tdm.deleteDbManager();\n");
		content.append("\t\t\treturn v;\n");
		content.append("\t\t}catch(Exception e){\n");
		content.append("\t\t\te.printStackTrace();\n");
		content.append("\t\treturn null;\n");
		content.append("\t\t}\n");
		content.append("\t}");
		System.out.println(content);
		return content;
	}
	private StringBuffer printqueryBysql(){
		String  fields[]=tb.getFields();
		String types[]=tb.getTypes();
		String setMethodsDetail[] = tb.getSetterMethodsDetail();
		String setMethods[] = tb.getSetterMethods();
		StringBuffer content = new StringBuffer();
		content.append("public Vector queryBysql"+TbNameDAO+"(String sql){\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tResultSet rs = dm.getQueryResult();\n");
		content.append("\t\t"+TbNameDAO+" " + tbNameDAO + "= null;\n");
		content.append("\t\tVector v = new Vector();\n");
		content.append("\t\ttry{\n");
		content.append("\t\t\twhile(rs.next()){\n");
		content.append("\t\t\t"+tbNameDAO + "= new "+TbNameDAO+"();\n");
		for(int i = 0;i < fields.length;i++){
		content.append("\t\t\t"+setMethodsDetail[i]+";\n");
		}
		content.append("\t\t\tv.add(" + tbNameDAO + ");\n");
		content.append("\t\t}\n");
		content.append("\t\tdm.deleteDbManager();\n");
		content.append("\t\t\treturn v;\n");
		content.append("\t\t}catch(Exception e){\n");
		content.append("\t\t\te.printStackTrace();\n");
		content.append("\t\treturn null;\n");
		content.append("\t\t}\n");
		content.append("\t}");
		System.out.println(content);
		return content;
	}
	public void printTbMgr(){
		//File file = new File("src/com/bean/Account");
		try{
			String TYPES[]=tb.getTYPES();
			//String comments[]=tb.getComments();
			String getMethods[] = tb.getGetterMethods();
			String  fields[]=tb.getFields();
			String types[]=tb.getTypes();
			String setMethodsDetail[] = tb.getSetterMethodsDetail();
			String setMethods[] = tb.getSetterMethods();
			//com.bean.Tbname.account.java src +this.path+tbname.java
			File file = new File(baseDirName+TbNameDAO+"/" + TbNameDAO +"DAO.java");
			FileOutputStream fos = new FileOutputStream(file);
			System.out.println("TableName="+TbNameDAO);
			StringBuffer content = new StringBuffer() ;
			content.append("/*\n\t@about:\n\t@author:\n\t@version:\n*/\n");
			content.append("package "+FactoryTool.dirTopackage(baseDirName)+";\n");
			content.append("import java.sql.*;\n");
			content.append("import java.util.*;\n");
			content.append("import com.database.*;\n");
			content.append("public class ");
			content.append(TbNameDAO+"Mgr");
			content.append("{\n\n\t");
			content.append(this.printinsert());
			content.append("\n\n\t");
			content.append(this.printdelete());
			content.append("\n\n\t");
			content.append(this.printupdate());
			content.append("\n\n\t");
			content.append(this.printqueryByid());
			content.append("\n\n\t");
			content.append(this.printqueryBykey());
			content.append("\n\n\t");
			content.append(this.printqueryAll());
			content.append("\n\n\t");
			content.append(this.printqueryBysql());;
			content.append("\n");
			content.append("\tpublic static void main(String args[]){\n");
			content.append("\t\t"+TbNameDAO+" "+tbNameDAO+" = new "+TbNameDAO+"();\n");
			content.append("\t\t"+TbNameDAO+"Mgr "+tbNameDAO+"Mgr = new "+TbNameDAO+"Mgr();\n");
			content.append("\t\tVector "+tbNameDAO+"s= new Vector();\n");
			content.append("\t\t//--------------1、insert test data "+TbNameDAO+"1-5--------------------//\n");
			content.append("\t\tSystem.out.println(\"//--------------1、insert--------------------//\");\n");
			content.append("\t\tfor(int i =1;i<=5;i++){//insert test data 1-5");
			for(int i =0;i<fields.length;i++)
				content.append("\t\t\t"+tbNameDAO+".set"+FactoryTool.UpperCaseFirstLetter(fields[i])+"(\""+fields[i]+"\"+i);\n");
			content.append("\t\t\ttry{\n");
			content.append("\t\t\t\t"+tbNameDAO+"Mgr.insert"+TbNameDAO+"("+tbNameDAO+");\n");
			content.append("\t\t\t\tSystem.out.println(\"insert 第\"+i+\"个"+tbNameDAO+"\"+i+\" success\");\n");
			content.append("\t\t\t}catch(Exception e){\n");
			content.append("\t\t\t\t\te.printStackTrace();\n");
			content.append("\t\t\t}");
			content.append("\t\t}\n");
			content.append("\t\tSystem.out.println(\"Insert success\");\n");
			content.append("\t\t//--------2、delete----------//delete data 1\n");
			content.append("\t\t\tSystem.out.println(\"//--------2、delete----------//delete data 2\");\n");
			content.append("\t\t\t"+tbNameDAO+".set"+FactoryTool.UpperCaseFirstLetter(fields[0])+"(2);\n");
			content.append("\t\ttry{\n");
			content.append("\t\t\t"+tbNameDAO+"Mgr.delete"+TbNameDAO+"("+tbNameDAO+");\n");
			content.append("\t\t\tSystem.out.println(\"delete第\"+"+getMethods[0]+"+\"个"+tbNameDAO+" success\");\n");
			content.append("\t\t}catch(Exception e){\n");
			content.append("\t\t\te.printStackTrace();\n");
			content.append("\t\t}\n");
			content.append("\t\t//----------3、Update---------------//update data 3\n");
			content.append("\t\tSystem.out.println(\"//----------3、Update---------------//update data 2\");\n");
			content.append("\t\t//先查询后修改这样的话别的没改的就不为空了\n");
			content.append("\t\t"+tbNameDAO+".set"+FactoryTool.UpperCaseFirstLetter(fields[0])+"(3);\n");
			content.append("\t\t"+tbNameDAO+" = "+tbNameDAO+"Mgr.queryByid"+TbNameDAO+"("+getMethods[0]+");\n");
			for(int i1 =1;i1<fields.length;i1++)
				content.append("\t\t"+tbNameDAO+".set"+FactoryTool.UpperCaseFirstLetter(fields[i1])+"(\"update\");\n");
			content.append("\t\ttry{\n");
			content.append("\t\t\t"+tbNameDAO+"Mgr.update"+TbNameDAO+"("+tbNameDAO+");\n");
			content.append("\t\t\tSystem.out.println(\"update第 \"+"+getMethods[0]+"+\" 个"+TbNameDAO+" success\");\n");
			content.append("\t\t}catch(Exception e){\n");
			content.append("\t\t\te.printStackTrace();\n");
			content.append("\t\t}\n");
			content.append("\t\t//----------4、queryByid-------------//query data 4\n");
			content.append("\t\tSystem.out.println(\"//----------4、queryByid---data-3 as example----------//query data 3\");\n");
			content.append("\t\ttry{\n");
			content.append("\t\t\t"+tbNameDAO+" = "+tbNameDAO+"Mgr.queryByid"+TbNameDAO+"(4);\n");
			content.append("\t\t\tSystem.out.println(\"第3号 "+tbNameDAO+"\" ");
			for(int i2=1;i2<fields.length;i2++)
				content.append("+\""+fields[i2]+" =\"+ "+getMethods[i2]);
			content.append(");\n");
			content.append("\t\t\tSystem.out.println(\"queryByid \"+"+getMethods[0]+"+\" success\");\n");
			content.append("\t\t}catch(Exception e){\n");
			content.append("\t\t\te.printStackTrace();\n");
			content.append("\t\t}\n");
			for(int k=1;k<fields.length;k++){
				content.append("\t\t//-----------4、queryBy"+fields[k]+"-data-3 as example-----------//queryBykey"+fields[0]+" data 4\n");
				content.append("\t\tSystem.out.println(\"//-----------5、query"+fields[k]+"------------//query"+fields[k]+" data\");\n");
				content.append("\t\ttry{\n");
				content.append("\t\t\t"+tbNameDAO+"s = "+tbNameDAO+"Mgr.queryBy"+fields[k]+TbNameDAO+"(\""+fields[k]+"3\");\n");
				content.append("\t\t\tIterator it = "+tbNameDAO+"s.iterator();\n");
				content.append("\t\t\twhile(it.hasNext()){\n");
				content.append("\t\t\t"+tbNameDAO+" = ("+TbNameDAO+")it.next();\n");
				content.append("\t\t\tSystem.out.println(\"第\"+"+getMethods[0]+"+\"号 "+tbNameDAO+"的值为\"\n ");
				for(int i2=1;i2<fields.length;i2++)
					content.append(" +\""+fields[i2]+" =\"+ "+getMethods[i2]);		
				//content.append("\t\t\tSystem.out.println(\"第\"+ai.getAid()+\"个Admin_info的姓名为\"+ai.getAname()+\",密码为\"+ai.getApassword());\n");
				content.append(");\n\t\t\t}\n");			
				content.append("\t\t\tSystem.out.println(\"queryBy"+fields[k]+" admin success\");\n");
				content.append("\t\t}catch(Exception e){\n");
				content.append("\t\t\te.printStackTrace();\n");
				content.append("\t\t}\n");
			}
			content.append("\t\t//-----------5、queryAll------------//queryAll data\n");
			content.append("\t\tSystem.out.println(\"//-----------5、queryAll------------//queryAll data\");\n");
			content.append("\t\ttry{\n");
			content.append("\t\t\t"+tbNameDAO+"s = "+tbNameDAO+"Mgr.queryAll"+TbNameDAO+"();\n");
			content.append("\t\t\tIterator it = "+tbNameDAO+"s.iterator();\n");
			content.append("\t\t\twhile(it.hasNext()){\n");
			content.append("\t\t\t"+tbNameDAO+" = ("+TbNameDAO+")it.next();\n");
			content.append("\t\t\tSystem.out.println(\"第\"+"+getMethods[0]+"+\"号 "+tbNameDAO+"的值为\"\n ");
			for(int i2=1;i2<fields.length;i2++)
				content.append(" +\""+fields[i2]+" =\"+ "+getMethods[i2]);		
			//content.append("\t\t\tSystem.out.println(\"第\"+ai.getAid()+\"个Admin_info的姓名为\"+ai.getAname()+\",密码为\"+ai.getApassword());\n");
			content.append(");\n\t\t\t}\n");			
			content.append("\t\t\tSystem.out.println(\"queryAll admin success\");\n");
			content.append("\t\t}catch(Exception e){\n");
			content.append("\t\t\te.printStackTrace();\n");
			content.append("\t\t}\n");
			content.append("\t\t//---------------6、queryBysql---------------//queryBySQL\n");
			content.append("\t\tSystem.out.println(\"//---------------6、queryBykey---------------//queryBySQL\");\n");
			content.append("\t\tString sql =\"select*from admin_info\";\n");
			content.append("\t\ttry{\n");
			content.append("\t\t\t"+tbNameDAO+"s = "+tbNameDAO+"Mgr.queryBysql"+TbNameDAO+"(sql);\n");
			content.append("\t\t\tIterator it = "+tbNameDAO+"s.iterator();\n");
			content.append("\t\t\twhile(it.hasNext()){\n");
			content.append("\t\t\t"+tbNameDAO+" = ("+TbNameDAO+")it.next();\n");
			content.append("\t\t\tSystem.out.println(\"第\"+"+getMethods[0]+"+\"号 "+tbNameDAO+"\" ");
			for(int i2=1;i2<fields.length;i2++)
				content.append("+\""+fields[i2]+" =\"+ "+getMethods[i2]+" ");
			//content.append("\t\t\tSystem.out.println(\"第\"+ai.getAid()+\"个Admin_info的姓名为\"+ai.getAname()+\",密码为\"+ai.getApassword());\n");
			content.append(");\n\t\t\t}\n");
			content.append("\t\t\tSystem.out.println(\"queryBysql "+tbNameDAO+" success\");\n");
			content.append("\t\t}catch(Exception e){\n");
			content.append("\t\t\te.printStackTrace();\n");
			content.append("\t\t}\n");
			content.append("\t}\n");
			content.append("}"); 
			System.out.println(content);
			fos.write(content.toString().getBytes());
			fos.close();
			System.out.println("print tableMgr success");
		}catch(IOException e){
			System.out.println("print tableMgr fail");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String tbName = "sp_todolist";
		String prefix = "sp_";
		String t = tbName.substring(tbName.indexOf(prefix)+prefix.length());
		//System.out.println("tname - "+t);
		FactoryTool.tryToMakdir(tbName,"sp_");
		
		FactoryDb db = new FactoryDb("timeplan");
		String[] tbs = db.getTbs();
		FactoryTb tb = new FactoryTb(db,"sp_todolist");
		FactoryTbDAO tbDAO = new FactoryTbDAO(tb,"sp_");
		tbDAO.printTbMgr();
	}
}
