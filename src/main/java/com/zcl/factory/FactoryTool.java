package com.zcl.factory;

public class FactoryTool {
	public static String typeToType(String typeName){	
		//从数据表字段类型转换成Java数据类型
		if(typeName.contains("char") || typeName.contains("text")||typeName.contains("timestamp")){
			return "String";
		} 
		if(typeName.contains("int") || typeName.contains("number")){
			return "int";
		}
		if(typeName.contains("float") || typeName.contains("numeric") || typeName.contains("double")||typeName.contains("decimal")){
			return "double";
		}
		if(typeName.contains("date")){
			return "java.sql.Date";
		}
		return null;
	}
	public static String typeToTYPE(String typeName){	
		//从数据表字段类型转换成Java数据类型
		if(typeName.contains("char") || typeName.contains("text")||typeName.contains("timestamp")){
			return "String";
		} 
		if(typeName.contains("int") || typeName.contains("number")){
			return "Integer";
		}
		if(typeName.contains("float") || typeName.contains("numeric") || typeName.contains("double")||typeName.contains("decimal")){
			return "Double";
		}
		if(typeName.contains("date")){
			return "String";
		}
		return null;
	}

	public static String fromStringToType(String str){
		if(str.equals("int")){
			return "Integer.parseInt";
		}else if(str.equals("double")){
			return "Double.parseDouble";
		}else if(str.equals("float")){
			return "Float.parseFloat";
		}else if(str.equals("String")){
			return "";
		}
		return null;
	}
	
	public static String UpperCaseFirstLetter(String name){		//将字符串首字母大写返回
		if(name == null || name.equals("")){
			return "";
		}
		char []c = new char[1];
		c[0] = name.charAt(0);
		return new String(c).toUpperCase().concat(name.substring(1));
	}
	public static String LowerCaseFirstLetter(String name){		//将字符串首字母小写返回
		if(name == null || name.equals("")){
			return "";
		}
		char []c = new char[1];
		c[0] = name.charAt(0);
		return new String(c).toLowerCase().concat(name.substring(1));
	}
	public static String dirTopackage(String dir){
		String newDir = new String();
		if(dir.contains("/"))
			newDir = dir.replace("/", ".");
		return newDir;
	}
	public static String removePrefix(String tb_tableName,int prefixStartIndex,int prefixEndIndex){
		return tb_tableName.substring(prefixEndIndex,tb_tableName.length());
	}
	public static void main(String args[]){
		//Tool tool = new Tool();
		String dir = "com/bean/account";
		System.out.println(FactoryTool.dirTopackage(dir));
		System.out.println(FactoryTool.removePrefix("tb_user333rrrrr",0,3));//tb_123
	}
}
