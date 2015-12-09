package com.zcl.factory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class FactoryTbMgr {

	/**
	 * @param args
	 */
	//private Tb tb;////Tb的管理类 只负责管理本类的成员 当管理其他Tb时候需要SetTb建立关联
	private String TbMgrname;
	private String tbdaoPath = "com/zcl/gen/dao";
	public StringBuffer printinsert1(FactoryTb tb){
		String  fields[]=tb.getFields();
		String getMethods[] = tb.getGetterMethods();
		StringBuffer content = new StringBuffer();
		content.append("public boolean insert"+tb.getTbname()+"("+tb.getTbname()+" "+tb.gettbname()+"){\n");
		content.append("");
		content.append("\t\tString sql = \"insert into "+tb.getTbname()+" values(null\"+\n");
		for(int i = 0; i<getMethods.length;i++){
			content.append("\t\t\",'\" + " + getMethods[i]+ " + \"\'\"+ \n");
		}
		content.append("\t\t\")\";\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tint flag = dm.getUpdateResult();\n");
		content.append("\t\tif (flag!=0)\n\t\t\treturn true;\n\t\telse\n\t\t\treturn false;\n\t}");
		System.out.println(content);
		return content;
	}
	public StringBuffer printinsert(FactoryTb tb){
		String  fields[]=tb.getFields();
		String TYPES[]=tb.getTYPES();
		String getMethods[] = tb.getGetterMethods();
		StringBuffer content = new StringBuffer();
		content.append("public boolean insert"+tb.getTbname()+"("+tb.getTbname()+" "+tb.gettbname()+"){\n");
		content.append("\t\tString sql = \"insert into " + tb.gettbname() + " values(");
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
	public StringBuffer printdelete(FactoryTb tb){
		String  fields[]=tb.getFields();
		String TYPES[]=tb.getTYPES();
		String getMethods[] = tb.getGetterMethods();
		StringBuffer content = new StringBuffer();
		content.append("public boolean delete"+tb.getTbname()+"("+tb.getTbname()+" "+tb.gettbname()+"){\n");
		content.append("\t\tString sql = \"delete from " + tb.gettbname() + " where " + fields[0]+"=\'\" + " + getMethods[0] + " + \"'\";\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tint flag = dm.getUpdateResult();\n");
		content.append("\t\tif (flag!=0)\n\t\t\treturn true;\n\t\telse\n\t\t\treturn false;\n\t}");
		System.out.println(content);
		return content;
	}
	public StringBuffer printupdate1(FactoryTb tb){
		String  fields[]=tb.getFields();
		String getMethods[] = tb.getGetterMethods();
		StringBuffer content = new StringBuffer();
		content.append("public boolean update"+tb.getTbname()+"set "+tb.getTbname()+" "+tb.gettbname()+"){\n");
		content.append("");
		content.append("\t\tString sql = \"insert into "+tb.getTbname()+" values(null\"+\n");
		for(int i = 1; i<getMethods.length;i++){
			content.append("\t\t\"+fields[i]+'\" + " + getMethods[i]+ " + \"\'\"+ \n");
		}
		content.append("\nwhere "+fields[0]+"=" +"\"+getMethods[0]+");
		content.append("\t\t\"\";\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tint flag = dm.getUpdateResult();\n");
		content.append("\t\tif (flag!=0)\n\t\t\treturn true;\n\t\telse\n\t\t\treturn false;\n\t}");
		//System.out.println(content);
		return content;
	}
	public StringBuffer printupdate(FactoryTb tb){
		String  fields[]=tb.getFields();
		String TYPES[]=tb.getTYPES();
		String getMethods[] = tb.getGetterMethods();
		StringBuffer content = new StringBuffer();
		content.append("public boolean update"+tb.getTbname()+"("+tb.getTbname()+" "+tb.gettbname()+"){\n");
		content.append("\t\tString sql = \"update " + tb.gettbname() + " set ");
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
	public StringBuffer printqueryByid(FactoryTb tb){
		String  fields[]=tb.getFields();
		String types[]=tb.getTypes();
		String setMethodsDetail[] = tb.getSetterMethodsDetail();
		StringBuffer content = new StringBuffer();
		content.append("public "+tb.getTbname()+" queryByid"+tb.getTbname()+"("+types[0]+" "+fields[0]+"){\n");
		content.append("\t\tString sql = \"select* from " + tb.gettbname() + " where "+fields[0]+"=" + "\'\" + "+fields[0]+" + \"\'\";\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tResultSet rs = dm.getQueryResult();\n");
		content.append("\t\t"+tb.getTbname()+" " + tb.gettbname() + "= new "+tb.getTbname()+"();\n");
		content.append("\t\ttry{\n");
		content.append("\t\t\tif(rs.next()){\n");
		for(int i = 0;i < fields.length;i++){
		content.append("\t\t\t"+setMethodsDetail[i]+";\n");
		}
		content.append("\t\t}\n");
		content.append("\t\t\tdm.deleteDbManager();\n");
		content.append("\t\t\treturn "+tb.gettbname()+";\n");
		content.append("\t\t}catch(Exception e){\n");
		content.append("\t\t\te.printStackTrace();\n");
		content.append("\t\t\treturn null;\n");
		content.append("\t\t}\n");
		content.append("\t}");
		System.out.println(content);
		return content;
	}
	public StringBuffer printqueryBykey(FactoryTb tb){
		String  fields[]=tb.getFields();
		String types[]=tb.getTypes();
		String setMethodsDetail[] = tb.getSetterMethodsDetail();
		StringBuffer content = new StringBuffer();
		for(int j=1; j<fields.length;j++){
			content.append("public Vector queryBy"+fields[j]+tb.getTbname()+"("+types[j]+" "+fields[j]+"){\n");
			content.append("\t\tString sql = \"select* from " + tb.gettbname() + " where "+fields[j]+"=" + "\'\" + "+fields[j]+" + \"\'\";\n");
			content.append("\t\tDbManager dm = new DbManager();\n");
			content.append("\t\tdm.setSql(sql);\n");
			content.append("\t\tResultSet rs = dm.getQueryResult();\n");
			content.append("\t\t"+tb.getTbname()+" " + tb.gettbname() + "= new "+tb.getTbname()+"();\n");
			content.append("\t\tVector v = new Vector();\n");
			content.append("\t\ttry{\n");
			content.append("\t\t\tif(rs.next()){\n");
			content.append("\t\t\t"+ tb.gettbname() + "= new "+tb.getTbname()+"();\n");
			for(int i = 0;i < fields.length;i++){
			content.append("\t\t\t"+setMethodsDetail[i]+";\n");
			}
			content.append("\t\t\tv.add(" + tb.gettbname() + ");\n");
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
	public StringBuffer printqueryAll(FactoryTb tb){
		String  fields[]=tb.getFields();
		String types[]=tb.getTypes();
		String setMethodsDetail[] = tb.getSetterMethodsDetail();
		StringBuffer content = new StringBuffer();
		content.append("public Vector queryAll"+tb.getTbname()+"(){\n");
		content.append("\t\tString sql = \"select* from " + tb.gettbname() + "\";\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tResultSet rs = dm.getQueryResult();\n");
		content.append("\t\t"+tb.getTbname()+" " + tb.gettbname() + "= null ;\n");
		content.append("\t\tVector v = new Vector();\n");
		content.append("\t\ttry{\n");
		content.append("\t\t\twhile(rs.next()){\n");
		content.append("\t\t\t"+ tb.gettbname() + "= new "+tb.getTbname()+"();\n");
		for(int i = 0;i < fields.length;i++){
		content.append("\t\t\t"+setMethodsDetail[i]+";\n");
		}
		content.append("\t\t\tv.add(" + tb.gettbname() + ");\n");
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
	public StringBuffer printqueryAllDetail(FactoryTb tb){
		String  fields[]=tb.getFields();
		String types[]=tb.getTypes();
		String setMethodsDetail[] = tb.getSetterMethodsDetail();
		StringBuffer content = new StringBuffer();
		content.append("public Vector queryAllDetail"+tb.getTbname()+"(String sql){\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tResultSet rs = dm.getQueryResult();\n");
		content.append("\t\t"+tb.getTbname()+" " + tb.gettbname() + "= new "+tb.getTbname()+"();\n");
		content.append("\t\tVector v = new Vector();\n");
		content.append("\t\ttry{\n");
		content.append("\t\t\tif(rs.next()){\n");
		for(int i = 0;i < fields.length;i++){
		content.append("\t\t\t"+setMethodsDetail[i]+";\n");
		}
		content.append("\t\t\tv.add(" + tb.gettbname() + ");\n");
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
	public StringBuffer printqueryBysql(FactoryTb tb){
		String  fields[]=tb.getFields();
		String types[]=tb.getTypes();
		String setMethodsDetail[] = tb.getSetterMethodsDetail();
		String setMethods[] = tb.getSetterMethods();
		StringBuffer content = new StringBuffer();
		content.append("public Vector queryBysql"+tb.getTbname()+"(String sql){\n");
		content.append("\t\tDbManager dm = new DbManager();\n");
		content.append("\t\tdm.setSql(sql);\n");
		content.append("\t\tResultSet rs = dm.getQueryResult();\n");
		content.append("\t\t"+tb.getTbname()+" " + tb.gettbname() + "= null;\n");
		content.append("\t\tVector v = new Vector();\n");
		content.append("\t\ttry{\n");
		content.append("\t\t\twhile(rs.next()){\n");
		content.append("\t\t\t"+tb.gettbname() + "= new "+tb.getTbname()+"();\n");
		for(int i = 0;i < fields.length;i++){
		content.append("\t\t\t"+setMethodsDetail[i]+";\n");
		}
		content.append("\t\t\tv.add(" + tb.gettbname() + ");\n");
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
	public void printTbMgr(FactoryTb tb){
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
			File file = new File("src/com/bean/"+tb.gettbname()+"/" + tb.getTbname() +"Mgr.java");
			FileOutputStream fos = new FileOutputStream(file);
			System.out.println("TableName="+tb.getTbname());
			StringBuffer content = new StringBuffer() ;
			content.append("/*\n\t@about:\n\t@author:\n\t@version:\n*/\n");
			content.append("package "+FactoryTool.dirTopackage(tbdaoPath)+";\n");
			content.append("import java.sql.*;\n");
			content.append("import java.util.*;\n");
			content.append("import com.database.*;\n");
			content.append("public class ");
			content.append(tb.getTbname()+"Mgr");
			content.append("{\n\n\t");
			content.append(this.printinsert(tb));
			content.append("\n\n\t");
			content.append(this.printdelete(tb));
			content.append("\n\n\t");
			content.append(this.printupdate(tb));
			content.append("\n\n\t");
			content.append(this.printqueryByid(tb));
			content.append("\n\n\t");
			content.append(this.printqueryBykey(tb));
			content.append("\n\n\t");
			content.append(this.printqueryAll(tb));
			content.append("\n\n\t");
			content.append(this.printqueryBysql(tb));;
			content.append("\n");
			content.append("\tpublic static void main(String args[]){\n");
			content.append("\t\t"+tb.getTbname()+" "+tb.gettbname()+" = new "+tb.getTbname()+"();\n");
			content.append("\t\t"+tb.getTbname()+"Mgr "+tb.gettbname()+"Mgr = new "+tb.getTbname()+"Mgr();\n");
			content.append("\t\tVector "+tb.gettbname()+"s= new Vector();\n");
			content.append("\t\t//--------------1、insert test data "+tb.getTbname()+"1-5--------------------//\n");
			content.append("\t\tSystem.out.println(\"//--------------1、insert--------------------//\");\n");
			content.append("\t\tfor(int i =1;i<=5;i++){//insert test data 1-5");
			for(int i =0;i<fields.length;i++)
				content.append("\t\t\t"+tb.gettbname()+".set"+FactoryTool.UpperCaseFirstLetter(fields[i])+"(\""+fields[i]+"\"+i);\n");
			content.append("\t\t\ttry{\n");
			content.append("\t\t\t\t"+tb.gettbname()+"Mgr.insert"+tb.getTbname()+"("+tb.gettbname()+");\n");
			content.append("\t\t\t\tSystem.out.println(\"insert 第\"+i+\"个"+tb.gettbname()+"\"+i+\" success\");\n");
			content.append("\t\t\t}catch(Exception e){\n");
			content.append("\t\t\t\t\te.printStackTrace();\n");
			content.append("\t\t\t}");
			content.append("\t\t}\n");
			content.append("\t\tSystem.out.println(\"Insert success\");\n");
			content.append("\t\t//--------2、delete----------//delete data 1\n");
			content.append("\t\t\tSystem.out.println(\"//--------2、delete----------//delete data 2\");\n");
			content.append("\t\t\t"+tb.gettbname()+".set"+FactoryTool.UpperCaseFirstLetter(fields[0])+"(2);\n");
			content.append("\t\ttry{\n");
			content.append("\t\t\t"+tb.gettbname()+"Mgr.delete"+tb.getTbname()+"("+tb.gettbname()+");\n");
			content.append("\t\t\tSystem.out.println(\"delete第\"+"+getMethods[0]+"+\"个"+tb.gettbname()+" success\");\n");
			content.append("\t\t}catch(Exception e){\n");
			content.append("\t\t\te.printStackTrace();\n");
			content.append("\t\t}\n");
			content.append("\t\t//----------3、Update---------------//update data 3\n");
			content.append("\t\tSystem.out.println(\"//----------3、Update---------------//update data 2\");\n");
			content.append("\t\t//先查询后修改这样的话别的没改的就不为空了\n");
			content.append("\t\t"+tb.gettbname()+".set"+FactoryTool.UpperCaseFirstLetter(fields[0])+"(3);\n");
			content.append("\t\t"+tb.gettbname()+" = "+tb.gettbname()+"Mgr.queryByid"+tb.getTbname()+"("+getMethods[0]+");\n");
			for(int i1 =1;i1<fields.length;i1++)
				content.append("\t\t"+tb.gettbname()+".set"+FactoryTool.UpperCaseFirstLetter(fields[i1])+"(\"update\");\n");
			content.append("\t\ttry{\n");
			content.append("\t\t\t"+tb.gettbname()+"Mgr.update"+tb.getTbname()+"("+tb.gettbname()+");\n");
			content.append("\t\t\tSystem.out.println(\"update第 \"+"+getMethods[0]+"+\" 个"+tb.getTbname()+" success\");\n");
			content.append("\t\t}catch(Exception e){\n");
			content.append("\t\t\te.printStackTrace();\n");
			content.append("\t\t}\n");
			content.append("\t\t//----------4、queryByid-------------//query data 4\n");
			content.append("\t\tSystem.out.println(\"//----------4、queryByid---data-3 as example----------//query data 3\");\n");
			content.append("\t\ttry{\n");
			content.append("\t\t\t"+tb.gettbname()+" = "+tb.gettbname()+"Mgr.queryByid"+tb.getTbname()+"(4);\n");
			content.append("\t\t\tSystem.out.println(\"第3号 "+tb.gettbname()+"\" ");
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
				content.append("\t\t\t"+tb.gettbname()+"s = "+tb.gettbname()+"Mgr.queryBy"+fields[k]+tb.getTbname()+"(\""+fields[k]+"3\");\n");
				content.append("\t\t\tIterator it = "+tb.gettbname()+"s.iterator();\n");
				content.append("\t\t\twhile(it.hasNext()){\n");
				content.append("\t\t\t"+tb.gettbname()+" = ("+tb.getTbname()+")it.next();\n");
				content.append("\t\t\tSystem.out.println(\"第\"+"+getMethods[0]+"+\"号 "+tb.gettbname()+"的值为\"\n ");
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
			content.append("\t\t\t"+tb.gettbname()+"s = "+tb.gettbname()+"Mgr.queryAll"+tb.getTbname()+"();\n");
			content.append("\t\t\tIterator it = "+tb.gettbname()+"s.iterator();\n");
			content.append("\t\t\twhile(it.hasNext()){\n");
			content.append("\t\t\t"+tb.gettbname()+" = ("+tb.getTbname()+")it.next();\n");
			content.append("\t\t\tSystem.out.println(\"第\"+"+getMethods[0]+"+\"号 "+tb.gettbname()+"的值为\"\n ");
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
			content.append("\t\t\t"+tb.gettbname()+"s = "+tb.gettbname()+"Mgr.queryBysql"+tb.getTbname()+"(sql);\n");
			content.append("\t\t\tIterator it = "+tb.gettbname()+"s.iterator();\n");
			content.append("\t\t\twhile(it.hasNext()){\n");
			content.append("\t\t\t"+tb.gettbname()+" = ("+tb.getTbname()+")it.next();\n");
			content.append("\t\t\tSystem.out.println(\"第\"+"+getMethods[0]+"+\"号 "+tb.gettbname()+"\" ");
			for(int i2=1;i2<fields.length;i2++)
				content.append("+\""+fields[i2]+" =\"+ "+getMethods[i2]+" ");
			//content.append("\t\t\tSystem.out.println(\"第\"+ai.getAid()+\"个Admin_info的姓名为\"+ai.getAname()+\",密码为\"+ai.getApassword());\n");
			content.append(");\n\t\t\t}\n");
			content.append("\t\t\tSystem.out.println(\"queryBysql "+tb.gettbname()+" success\");\n");
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
		// TODO Auto-generated method stub
		FactoryTbMgr tm = new FactoryTbMgr();
		String[]tbs = new FactoryDb("spider").getTbs();
		//for(int i =0;i<tbs.length;i++){
			FactoryTb tb = new FactoryTb("spider","site");
			tb.setTbname(tbs[0]);
			tm.printTbMgr(tb);
		//}
		//System.out.println(" ");
	//	tm.printinsert(tb);
	//	tm.printinsert2(tb);
		//tm.printupdate2(tb);
	//	tm.printdelete(tb);
	//	tm.printquery(tb);
	//	tm.printqueryAll(tb);
		//tm.printTbMgr(tb);
	}

}
