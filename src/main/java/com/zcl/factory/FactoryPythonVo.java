package com.zcl.factory;


import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

/**
 * python model generator
 * just for peewee
 * @author zcl
 *
 */
public class FactoryPythonVo{

	/**
	 * @param args
	 */
	private String tbname; //user tb_user
	private String Tbname; //User Tb_user
	
	private int fieldsLength;
	private String[] fields;
	private String[] types;
	private String[] TYPES;
	private String[] comments;
	private String[] keys;
	private String[] mkeys;
	private String[] fkeys;

	private FactoryDbMgr dmInstance;
	private FactoryDb dbInstance;
	private String dbName;
	// private FactoryTool tool = new FactoryTool();
	private String path;// 为包所在地址com.bean.Tbname不包含src
	private StringBuffer content = new StringBuffer();
	
	private String tbvoPath = "com/zcl/gen/vo";
	
	public FactoryPythonVo(String tbname) {
		if (getDbName() != null) {
			this.dbInstance = new FactoryDb(getDbName());
			this.dmInstance = new FactoryDbMgr(dbInstance);
		} else {
			try {
				throw new Exception(
						"db name not initialize,please one to connect");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (this.dbInstance == null) {
			try {
				throw new Exception(
						"DbMgr not initial with a db,use setter Method to set one");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.tbname = tbname;
		this.Tbname = FactoryTool.UpperCaseFirstLetter(tbname);
		initTableInfo();
	}

	public FactoryPythonVo(String dbname, String tbname) {
		this.tbname = tbname;
		this.Tbname = FactoryTool.UpperCaseFirstLetter(tbname);
		this.dbInstance = new FactoryDb(dbname);
		this.dmInstance = new FactoryDbMgr(dbInstance);
		this.tbname = tbname;
		this.Tbname = FactoryTool.UpperCaseFirstLetter(tbname);
		initTableInfo();
	}
	public FactoryPythonVo(FactoryDb db, String tbname) {
		this.tbname = tbname;
		this.Tbname = FactoryTool.UpperCaseFirstLetter(tbname);
		this.dbInstance = db;
		this.dmInstance = new FactoryDbMgr(dbInstance);
		this.tbname = tbname;
		this.Tbname = FactoryTool.UpperCaseFirstLetter(tbname);
		initTableInfo();
	}
	public FactoryPythonVo(FactoryDbMgr dbmgr, String tbname) {
		this.tbname = tbname;
		this.Tbname = FactoryTool.UpperCaseFirstLetter(tbname);
		System.out.println("dbmgr.getDbInstance:"+dbmgr.getDbInstance()+",dbmgr:"+dbmgr);
		this.dbInstance = dbmgr.getDbInstance();
		this.dmInstance = dbmgr;
		this.tbname = tbname;
		this.Tbname = FactoryTool.UpperCaseFirstLetter(tbname);
		initTableInfo();
	}
/**
 1			2				3		4	5
 field		type			isNull  PK	
site_id		int(10)			NO		PRI FK
site_name	varchar(255)	YES	
site_url	varchar(255)	YES	
create_time	int(10)			YES	
update_time	int(10)			YES	
is_deleted	int(10)			YES	
delete_time	int(10)			YES	
status	varchar(255)		YES	
description	varchar(255)	YES	
 */
	public void initTableInfo() {
		try {
			System.out.println("init table "+tbname);
			String sql = "desc" + " " + tbname + ";";
			dmInstance.setSql(sql);
			ResultSet rs = dmInstance.getQueryResult();
			int i = 0;
			while (rs.next()) {
				i++;
			}// 调用两次rs已经在最后了
			fieldsLength = i;
			
			setFields(new String[fieldsLength]);
			types = new String[fieldsLength];
			TYPES = new String[fieldsLength];
			System.out.println("initial types,length:"+fieldsLength);
//			keys = new String[fieldLength];
//			mkeys = new String[fieldLength];
//			fkeys = new String[fieldLength];
			
			i = 0;
			rs.beforeFirst();
			while (rs.next()) {
				getFields()[i] = rs.getString(1);
				types[i] = FactoryTool.typeToType(rs.getString(2));
				TYPES[i] = FactoryTool.typeToTYPE(rs.getString(2));
//				keys[i] = rs.getString(4);
				i++;
			}
			// System.out.println("TB/get fields success");
		} catch (Exception e) {
			// System.out.println("Tb/get fields fail");
			e.printStackTrace();
		}
	}
	public void settbname(String tbname) {// 设置表名子要大写
		this.tbname = tbname;
	}
	public String gettbname() {// 设置表名子要大写
		return tbname;
	}
	public void setTbname(String tbname) {// 设置表名子要大写
		this.Tbname = tbname;
	}
	public String getTbname() {// 设置表名子要大写
		return Tbname;
	}
	public String[] getGetterMethods(){//b.getBname()
		String getMethods[] = new String[this.getFieldsLength()];
		this.fields = this.getFields();
		for(int i = 0; i<this.getFieldsLength(); i++){
			getMethods[i] =( tbname + ".get" +FactoryTool.UpperCaseFirstLetter(this.fields[i])+"()");  
		}
		return getMethods;
	}
	public String[] getSetterMethods(){//改b.setBname(String bname)X为->b.setBname(bname)
		//account.setAid(int aid)
		String setMethods[] = new String[this.getFieldsLength()];
		String fields[] = this.getFields();
		String types[]=this.getTypes();
		for(int i = 0; i<this.getFieldsLength(); i++){
			setMethods[i] =( this.tbname + ".set" +FactoryTool.UpperCaseFirstLetter(this.fields[i])+"("+fields[i]+")");  
		}
		return setMethods;
	}
	public String[] getSetterMethodsDetail(){//b.setBname(rs.getString(i))
		//account.setAid(int aid)
		String setMethods[] = new String[this.getFieldsLength()];
		String fields[] = this.getFields();
		String types[]=this.getTypes();
		for(int i = 0; i<this.getFieldsLength(); i++){
			setMethods[i] =( this.tbname + ".set" +FactoryTool.UpperCaseFirstLetter(this.fields[i])+"(rs.get"+FactoryTool.UpperCaseFirstLetter(types[i])+"("+(i+1)+")"+")");  
		}
		return setMethods;
	}

//	public String gettbname() {
//		return FactoryTool.LowerCaseFirstLetter(this.tbname);
//	}
//
//	public String getTbname() {
//		return FactoryTool.UpperCaseFirstLetter(this.tbname);
//	}





	public void setTypes(String[] types) {
		this.types = types;
	}

	public String[] getTypes() {
		return types;
	}
	public void setTYPES(String[] types) {
		this.TYPES = types;
	}
	public String[] getTYPES() {
		return TYPES;
	}


	public void setKeys(String[] keys) {
		this.keys = keys;
	}



	public void setMkeys(String[] mkeys) {
		this.mkeys = mkeys;
	}



	public void setFkeys(String[] fkeys) {
		this.fkeys = fkeys;
	}

public boolean printTb() {
		
		String dir = "package " + tbvoPath + ";\n";
		System.out.println("dir=" + dir);
		String newDir = new String();
		//newDir = FactoryTool.dirTopackage(dir);
		//content.append(newDir);
		System.out.println("tool.dirTopackage(dir)="+ FactoryTool.dirTopackage(dir));
		content.append("class "+Tbname+"(Model):\n");
		content.append("\tclass Meta:\n");
		content.append("\t\tdb_table = '"+tbname+"'\n");
		content.append("\t\tdatabase = db\n");
		for(String s:types){
			System.out.println("types:"+s);
		}
		System.out.println("--fuck types:"+types.length);
		for(int i=0;i<fields.length;i++){    
			if(i==0){
				content.append("\t"+fields[0]+" = PrimaryKeyField()\n");
			}else{
				if(types[i].equalsIgnoreCase("int")){
					content.append("\t"+fields[i]+" = IntegerField()\n");
				}else if(types[i].equalsIgnoreCase("string")){
					content.append("\t"+fields[i]+" = CharField()\n");
				}
			}
		}
		String addStr ="";//projectId,userId,fromStatus,toStatus,type,data,creationTime
		String setVal="";//project_id=project_id,title=title,tag=tag,weight=weight,create_time=create_time,update_time=create_time,status=0
		for(int i=1;i<fields.length;i++) {
			addStr +=fields[i];
			if(i<fields.length-1){
				addStr+=",";
				setVal+=fields[i]+"="+fields[i]+",";
			}
		}
		String pk = fields[0];
		content.append("def add"+Tbname+"("+addStr+"):\n");
		content.append("\tprint '--add"+Tbname+"--start--'\n");
		content.append("\tcreate_time = (int)(time.time())\n");
		content.append("\t"+Tbname+" = "+Tbname+"("+setVal+")\n");
		content.append("\t"+Tbname+".save()\n");
		content.append("\tprint '--add"+Tbname+"--end--'\n");
		
		content.append("def find"+Tbname+"("+pk+"):\n");
		content.append("\tresult= object\n");
		content.append("\ttry:\n");
		content.append("\t\tul = "+Tbname+".select().where("+Tbname+"."+pk+" == "+pk+").get()\n");
		content.append("\t\tresult=ul\n");
		content.append("\texcept:\n");
		content.append("\t\t\tresult=None\n");
		content.append("\treturn result\n");
		content.append("def update"+Tbname+"("+pk+",key,value):\n");
		content.append("\tresult= object\n");
		content.append("\tprint '---update start---'\n");
		content.append("\ttry:\n");
		content.append("\t\tul = "+Tbname+".select().where("+Tbname+"."+pk+" == "+pk+").get()\n");
		content.append("\t\tif ul:\n");
		content.append("\t\t\t#print 'find success'\n");
		
//		content.append("\t\t\tif key=='title':\n");
//		content.append("\t\t\t\tul.title = value\n");
//		content.append("\t\t\telif key=='tag':\n");
//		content.append("\t\t\t\tul.tag = value\n");
		
		content.append("\t\t\tif key=='"+fields[1]+"':\n");
		content.append("\t\t\t\tul."+fields[1]+" = value\n");
		for(int i=2;i<=fields.length-1;i++){
			content.append("\t\t\telif key=='"+fields[i]+"':\n");
			content.append("\t\t\t\tul."+fields[i]+" = value\n");
		}
		content.append("\t\telse:\n");
		content.append("\t\t\traise 'unkown field:'+key\n");
		content.append("\t\tul.save()\n");
		content.append("\texcept:\n");
		content.append("\t\tresult=None\n");
		content.append("\tprint '---update "+Tbname+"start---'\n");
		content.append("def findAll"+Tbname+"():\n");
		content.append("\t#     print '---find all start---'\n");
		content.append("\tresult= object\n");
		content.append("\ttry:\n");
		content.append("\t\tulist = "+Tbname+".select()\n");
		content.append("\t\tresult=ulist\n");
		content.append("\texcept:\n");
		content.append("\t\tresult=None\n");
		content.append("\t#     print '---find all end---'\n");
		content.append("\treturn result\n");
		content.append("def print_"+Tbname+"(less):\n");
		content.append("\tprint '---print Less start---'\n");
		
		String printStr="print '";
		for(int i=1;i<fields.length;i++) {
			printStr+=fields[i]+":'+";
			if(types[i].equalsIgnoreCase("int")){
				printStr+="str("+Tbname+"."+fields[i]+")+'";
			}else{
				printStr+=Tbname+"."+fields[i]+"+'";
			}
			if(i<fields.length-1){
				addStr+="+',";
			}
		}
		printStr+="'";
		//content.append("\tprint 'log_id:'+str("+Tbname+".log_id)+',project_id:'+str("+Tbname+".project_id)+',title:'+"+Tbname+".title+',tag:'+"+Tbname+".tag+',create_time:'+str("+Tbname+".create_time)\n");
		content.append("\t"+printStr+"\n");
		content.append("\tprint '---print "+Tbname+" start---'\n");
		try {
			// 写入.java文件com.bean.Tbname.account.java src +this.path+tbname.java
			File file = new File("src/main/java/"+tbvoPath+"/" + Tbname+ ".py");
			System.out.println("gen file:"+file+" success.");
			FileOutputStream fos = new FileOutputStream(file);
			 fos.write(content.toString().getBytes());
			fos.close();
			System.out.println("print table success");
			return true;
		} catch (IOException e) {
			System.out.println("print table fail");
			e.printStackTrace();
			return false;
		}
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public int getFieldsLength() {
		return fieldsLength;
	}

	public void setFieldsLength(int fieldLength) {
		this.fieldsLength = fieldLength;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FactoryPythonVo tb = new FactoryPythonVo("spider","page");
		tb.printTb();

	}
}
