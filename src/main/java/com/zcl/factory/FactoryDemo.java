package com.zcl.factory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FactoryDemo {
	private FactoryTb tb;
	public static String TbNamePHP;
	public static String tbNamePHP;
	public static String baseDirName = "src/";
	public void Demo() {
		String field[] = tb.getFields();
		int tbs = 0;
		StringBuffer content = new StringBuffer();
		
		for(int i= 0;i<field.length;i++){			
			content.append("\t$"+field[i]+" = (string)$currentSheet->getCell('"+(char)(i+65)+"'.$currentRow)->getValue();\n");
			content.append("\tif($"+field[i]+" != '' && $"+field[i]+" != null) {\n");
			content.append("\t\t$data['"+field[i]+"'] = $"+field[i]+";\n\t}\n");
		}
		
		System.out.println(content);
		try { // 输出到文件
			String filename = baseDirName + TbNamePHP+ "/excelImport.html";
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
}
