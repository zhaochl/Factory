package com.zcl.factory;

import java.io.*;

/**
 * 本版本主要修正了ThinkAction中add方法和update方法 create方式改为 $data方法,add($data),save($data)
 * 
 * @author zcl
 * @date 2014.5.12
 */
public class PHPFactory2016 {

	private FactoryTb tb;
	public static String TbNamePHP;
	public static String tbNamePHP;
	public static String baseDirName = "src/";

	public FactoryTb getTb() {
		return tb;
	}

	public void setTb(FactoryTb tb) {
		this.tb = tb;
	}

	public PHPFactory2016(FactoryTb tb) {
		this.tb = tb;
		TbNamePHP = tb.getTbname();
		tbNamePHP = tb.gettbname();
	}

	public PHPFactory2016(FactoryTb tb, String prefix) {
		this.tb = tb;
		String TbNamePHPTmp = tb.getTbname().substring(
				tb.getTbname().indexOf(prefix) + prefix.length());
		// TbNamePHP =
		// String.valueOf(TbNamePHPTmp.charAt(0)).toUpperCase()+TbNamePHPTmp.substring(1);

		String t = tb.gettbname().substring(
				tb.gettbname().indexOf(prefix) + prefix.length());
		// System.out.println("fuck--t:"+t+",TbName:"+tb.getTbname()+",prefix:"+prefix);
		TbNamePHP = FactoryTool.UpperCaseFirstLetter(t);
		tbNamePHP = tb.gettbname().substring(
				tb.gettbname().indexOf(prefix) + prefix.length());
		System.out.println("TbNamePHP:" + TbNamePHP);
		System.out.println("tbNamePHP:" + tbNamePHP);

	}

	/**
	 * @param args
	 */
	public void printAddNewTabPHP() {
		String fields[] = tb.getFields();
		int tbs = 0;
		StringBuffer content = new StringBuffer();
		content.append("<include file=\"Public:header\" />\n");
		content.append("<script type=\"text/javascript\" src=\"__PUBLIC__/js/kindeditor-all-min.js\"></script>\n");
		content.append("<script type=\"text/javascript\" src=\"__PUBLIC__/js/zh_CN.js\"></script>\n");
		content.append("<link rel=\"stylesheet\" href=\"__PUBLIC__/css/kindeditor.css\" type=\"text/css\" />\n");
		content.append("<script type=\"text/javascript\">\n");
		content.append("\t<if condition=\"C('ismobile') neq 1\">\n");
		content.append("\t\tvar editor;\n");
		content.append("\t\tKindEditor.ready(function(K) {\n");
		content.append("\t\t\teditor = K.create('textarea[name=\"description\"]', {\n");
		content.append("\t\t\t\tuploadJson:'{:U(\"file/editor\")}',\n");
		content.append("\t\t\t\tallowFileManager : true,\n");
		content.append("\t\t\t\tloadStyleMode : false\n");
		content.append("\t\t\t});\n");
		content.append("\t\t});\n");
		content.append("\t</if>\n");
		content.append("</script>\n");
		content.append("<div class=\"container\">\n");
		content.append("\t<div class=\"page-header\">\n");
		content.append("\t\t<h4>Add " + TbNamePHP + " Panel</h4>\n");
		content.append("\t</div>\n");
		content.append("\t<div class=\"row\">\n");
		content.append("\t\t<div class=\"span12\">\n");
		content.append("\t\t\t<include file=\"Public:alert\" />\n");
		content.append("\t\t\t<form action=\"{:U('" + TbNamePHP
				+ "/addNewTab')}\" method=\"post\">\n");
		content.append("\t\t\t\t<input  type=\"hidden\" name=\"role_id\" value=\"{$Think.session.role_id}\"/>\n");
		content.append("\t\t\t\t<table class=\"table\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\">\n");
		content.append("\t\t\t\t\t<thead>\n");
		content.append("\t\t\t\t\t\t<tr>\n");
		content.append("\t\t\t\t\t\t\t<td colspan=\"2\"><input name=\"submit\" class=\"btn btn-primary\" type=\"submit\" value=\"保存\"/>&nbsp; <input class=\"btn btn-primary\" name=\"submit\" type=\"submit\" value=\"保存并新建\"/> &nbsp;<input class=\"btn\" type=\"button\" onclick=\"javascript:history.go(-1)\" value=\"返回\"/></td>\n");
		content.append("\t\t\t\t\t\t</tr>\n");
		content.append("\t\t\t\t\t</thead>\n");
		content.append("\t\t\t\t\t<tfoot>\n");
		content.append("\t\t\t\t\t\t<tr>\n");
		content.append("\t\t\t\t\t\t\t<td colspan=\"2\"><input class=\"btn btn-primary\"  name=\"submit\" type=\"submit\" value=\"保存\"/> &nbsp;<input class=\"btn btn-primary\" type=\"submit\" value=\"保存并新建\"/>&nbsp; <input class=\"btn\" type=\"button\" onclick=\"javascript:history.go(-1)\" value=\"返回\"/></td>\n");
		content.append("\t\t\t\t\t\t</tr>\n");
		content.append("\t\t\t\t\t</tfoot>\n");
		content.append("\t\t\t\t\t<tbody>\n");
		content.append("\t\t\t\t\t\t<th colspan=\"2\">基本信息</th>\n");
		// for(int i=0;i<fields.length;i++) {
		for (int i = 1; i < fields.length; i++) {// 略去id update@14.3.17
			if (fields[i].equals("create_time")
					|| fields[i].equals("update_time")
					|| fields[i].equals("is_deleted")
					|| fields[i].equals("delete_time")) {
				continue;
			}
			if (fields[i].equals("status")) {
				content.append("\t\t\t\t\t\t<tr>\n");
				content.append("\t\t\t\t\t\t\t<td class=\"tdleft\">状态</td>\n");
				content.append("\t\t\t\t\t\t\t<td>\n");
				content.append("\t\t\t\t\t\t\t\t<select name=\"status\">\n");
				content.append("\t\t\t\t\t\t\t\t\t<option value=\"1\">有效</option>\n");
				content.append("\t\t\t\t\t\t\t\t\t<option value=\"0\">无效</option>\n");
				content.append("\t\t\t\t\t\t\t\t</select>\n");
				content.append("\t\t\t\t\t\t\t</td>\n");
				content.append("\t\t\t\t\t\t</tr>\n");
			} else if (fields[i].equals("description")) {
				content.append("\t\t\t\t\t\t<tr>\n");
				content.append("\t\t\t\t\t\t\t<td class=\"tdleft\">"
						+ fields[i] + "</td>\n");
				content.append("\t\t\t\t\t\t\t<td>\n");
				content.append("\t\t\t\t\t\t\t\t<textarea name=\"description\" id=\"description\" style=\"width: 800px; height: 350px;\"></textarea>\n");
				content.append("\t\t\t\t\t\t\t</td>\n");
				content.append("\t\t\t\t\t\t</tr>\n");
			} else {
				content.append("\t\t\t\t\t\t<tr>\n");
				content.append("\t\t\t\t\t\t\t<td class=\"tdleft\">"
						+ fields[i] + "</td>\n");
				content.append("\t\t\t\t\t\t\t<td><input class=\"span3\" type=\"text\" id=\""
						+ fields[i]
						+ "\" name=\""
						+ fields[i]
						+ "\" placeholder=\"在此输入"
						+ fields[i]
						+ "，50个字符以内\"></td>\n");
				content.append("\t\t\t\t\t\t</tr>\n");
			}
		}

		content.append("\t\t\t\t\t</tbody>\n");
		content.append("\t\t\t\t</table>\n");
		content.append("\t\t\t</form>\n");
		content.append("\t\t</div>\n");
		content.append("\t</div>\n");
		content.append("</div>\n");
		content.append("<script type=\"text/javascript\">\n");
		content.append("\t<if condition=\"C('ismobile') eq 1\">\n");
		content.append("\t\twidth=$('.container').width() * 0.9;\n");
		content.append("\t\t$(\"#description\").css({\n");
		content.append("\t\t\twidth : width\n");
		content.append("\t\t});\n");
		content.append("\t</if>\n");
		content.append("</script>\n");
		content.append("<include file=\"Public:footer\" />\n");

		try { // 输出到文件
			String filename = baseDirName + TbNamePHP + "/addNewTab.html";
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");// 防止中文乱码
																			// @
																			// 2014.3.16
			osw.write(content.toString());
			osw.close();
			System.out.println("generate " + filename + " complete!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printAddPHP() {
		String field[] = tb.getFields();
		int tbs = 0;
		StringBuffer content = new StringBuffer();
		content.append("<form class=\"form-horizontal\" action=\"{:U('"
				+ TbNamePHP + "/add')}\" method=\"post\" >\n");

		// for(int i= 0;i<field.length;i++){
		for (int i = 1; i < field.length; i++) {// 略去id update@14.3.17
			if (field[i].equals("create_time")
					|| field[i].equals("update_time")
					|| field[i].equals("is_deleted")
					|| field[i].equals("delete_time")
					|| field[i].contains("time")) {
				continue;
			}
			if (field[i].equals("status")) {
				content.append("\t<div class=\"control-group\">\n");
				content.append("\t\t<label class=\"control-label\" for=\"name\">是否有效</label>\n");
				content.append("\t\t<div class=\"controls\"><input name=\"status\" type=\"radio\" checked=\"true\" value=\"1\"/>有效<input name=\"status\" type=\"radio\" value=\"0\"/>无效</div>\n");
				content.append("\t</div>\n");
			} else {// description 略去，因为未验证模式对话框是否可以执行特性tarea -keditor的执行情况
				content.append("\t<div class=\"control-group\">\n");
				content.append("\t\t<label class=\"control-label\" for=\"name\">"
						+ field[i] + "</label>\n");
				content.append("\t\t<div class=\"controls\"><input type=\"text\" id=\""
						+ field[i] + "\" name=\"" + field[i] + "\"/></div>\n");
				content.append("\t</div>\n");
			}
		}

		content.append("\t<div class=\"control-group\">\n");
		content.append("\t\t<label class=\"control-label\" for=\"name\"></label>\n");
		content.append("\t\t<div class=\"controls\">\n");
		content.append("\t\t\t<input class=\"btn btn-primary\" type=\"submit\" name=\"submit\" value=\"保存\"/> &nbsp; \n");
		content.append("\t\t\t<input class=\"btn btn-primary\" onclick=\"$('#add_dialog').dialog('close');\" type=\"reset\" value=\"取消\"/>\n");
		content.append("\t\t</div>\n");
		content.append("\t</div>\n");
		content.append("</form>\n");

		try { // 输出到文件
			String filename = baseDirName + TbNamePHP + "/add.html";
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");// 防止中文乱码
																			// @
																			// 2014.3.16
			osw.write(content.toString());
			osw.close();
			System.out.println("generate " + filename + " complete!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printEditPHP() {
		String field[] = tb.getFields();
		String getMethods[] = tb.getGetterMethods();
		String types[] = tb.getTypes();

		int tbs = 0;
		StringBuffer content = new StringBuffer();
		content.append("<form class=\"form-horizontal\" action=\"{:U('"
				+ TbNamePHP + "/edit')}\" method=\"post\" >\n");
		for (int i = 0; i < field.length; i++) {
			if (i == 0) {
				content.append("\t<div class=\"control-group\">\n");
				content.append("\t\t<label class=\"control-label\" for=\"name\">"
						+ field[i] + "</label>\n");
				content.append("\t\t<div class=\"controls\"><input type=\"hidden\" id=\""
						+ field[i]
						+ "\" name=\""
						+ field[i]
						+ "\" value=\"{$vo."
						+ field[i]
						+ "}\" /><input type=\"text\"  value=\"{$vo."
						+ field[i] + "}\" disabled=\"diabaled\" /></div>\n");
				content.append("\t</div>\n");
				continue;
			}
			if (field[i].equals("create_time")
					|| field[i].equals("update_time")
					|| field[i].equals("is_deleted")
					|| field[i].equals("delete_time")) {
				continue;
			}
			if (field[i].equals("status")) {
				content.append("\t<div class=\"control-group\">\n");
				content.append("\t\t<label class=\"control-label\" for=\"name\">是否有效</label>\n");
				content.append("\t\t<div class=\"controls\"><input name=\"status\" type=\"radio\" <if condition=\"($vo.status eq 1)\">checked=\"true\"</if> value=\"1\"/>有效<input name=\"status\" type=\"radio\" <if condition=\"($vo.status eq 0)\">checked=\"true\"</if> value=\"0\"/>无效</div>\n");
				content.append("\t</div>\n");
			} else {
				content.append("\t<div class=\"control-group\">\n");
				content.append("\t\t<label class=\"control-label\" for=\"name\">"
						+ field[i] + "</label>\n");
				content.append("\t\t<div class=\"controls\"><input type=\"text\" id=\""
						+ field[i]
						+ "\" name=\""
						+ field[i]
						+ "\" value=\"{$vo." + field[i] + "}\" /></div>\n");
				content.append("\t</div>\n");
			}
		}

		content.append("\t<div class=\"control-group\">\n");
		content.append("\t\t<label class=\"control-label\" for=\"name\"></label>\n");
		content.append("\t\t<div class=\"controls\">\n");
		content.append("\t\t\t<input class=\"btn btn-primary\" type=\"submit\" name=\"submit\" value=\"保存\"/> &nbsp; \n");
		content.append("\t\t\t<input class=\"btn btn-primary\" onclick=\"$('#edit_dialog').dialog('close');\" type=\"reset\" value=\"取消\"/>\n");
		content.append("\t\t</div>\n");
		content.append("\t</div>\n");
		content.append("</form>\n");
		try {
			String filename = baseDirName + TbNamePHP + "/edit.html";
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");// 防止中文乱码
																			// @
																			// 2014.3.16
			osw.write(content.toString());
			osw.close();
			System.out.println("generate " + filename + " complete!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printEditNewTabPHP() {
		String fields[] = tb.getFields();
		String getMethods[] = tb.getGetterMethods();
		String types[] = tb.getTypes();
		StringBuffer content = new StringBuffer();
		content.append("<include file=\"Public:header\" />\n");
		content.append("<script type=\"text/javascript\" src=\"__PUBLIC__/js/kindeditor-all-min.js\"></script>\n");
		content.append("<script type=\"text/javascript\" src=\"__PUBLIC__/js/zh_CN.js\"></script>\n");
		content.append("<link rel=\"stylesheet\" href=\"__PUBLIC__/css/kindeditor.css\" type=\"text/css\" />\n");
		content.append("<script type=\"text/javascript\">\n");
		content.append("\t<if condition=\"C('ismobile') neq 1\">\n");
		content.append("\t\tvar editor;\n");
		content.append("\t\tKindEditor.ready(function(K) {\n");
		content.append("\t\t\teditor = K.create('textarea[name=\"description\"]', {\n");
		content.append("\t\t\t\tuploadJson:'{:U(\"file/editor\")}',\n");
		content.append("\t\t\t\tallowFileManager : true,\n");
		content.append("\t\t\t\tloadStyleMode : false\n");
		content.append("\t\t\t});\n");
		content.append("\t\t});\n");
		content.append("\t</if>\n");
		content.append("</script>\n");
		content.append("<div class=\"container\">\n");
		content.append("\t<div class=\"page-header\">\n");
		content.append("\t\t<h4>Edit " + TbNamePHP + " Panel</h4>\n");
		content.append("\t</div>\n");
		content.append("\t<div class=\"row\">\n");
		content.append("\t\t<div class=\"span12\">\n");
		content.append("\t\t\t<include file=\"Public:alert\" />\n");
		content.append("\t\t\t<form action=\"{:U('" + TbNamePHP
				+ "/editNewTab')}\" method=\"post\">\n");
		content.append("\t\t\t\t<input  type=\"hidden\" name=\"role_id\" value=\"{$Think.session.role_id}\"/>\n");
		content.append("\t\t\t\t<table class=\"table\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\">\n");
		content.append("\t\t\t\t\t<thead>\n");
		content.append("\t\t\t\t\t\t<tr>\n");
		content.append("\t\t\t\t\t\t\t<td colspan=\"2\"><input name=\"submit\" class=\"btn btn-primary\" type=\"submit\" value=\"保存\"/>&nbsp; <input class=\"btn btn-primary\" name=\"submit\" type=\"submit\" value=\"保存并新建\"/> &nbsp;<input class=\"btn\" type=\"button\" onclick=\"javascript:history.go(-1)\" value=\"返回\"/></td>\n");
		content.append("\t\t\t\t\t\t</tr>\n");
		content.append("\t\t\t\t\t</thead>\n");
		content.append("\t\t\t\t\t<tfoot>\n");
		content.append("\t\t\t\t\t\t<tr>\n");
		content.append("\t\t\t\t\t\t\t<td colspan=\"2\"><input class=\"btn btn-primary\"  name=\"submit\" type=\"submit\" value=\"保存\"/> &nbsp;<input class=\"btn btn-primary\" type=\"submit\" value=\"保存并新建\"/>&nbsp; <input class=\"btn\" type=\"button\" onclick=\"javascript:history.go(-1)\" value=\"返回\"/></td>\n");
		content.append("\t\t\t\t\t\t</tr>\n");
		content.append("\t\t\t\t\t</tfoot>\n");
		content.append("\t\t\t\t\t<tbody>\n");
		content.append("\t\t\t\t\t\t<th colspan=\"2\">基本信息</th>\n");
		for (int i = 0; i < fields.length; i++) {
			if (i == 0) {
				content.append("\t\t\t\t\t\t<tr>\n");
				content.append("\t\t\t\t\t\t\t<td class=\"tdleft\">"
						+ fields[i] + "</td>\n");
				content.append("\t\t\t\t\t\t\t<td><input class=\"span3\" type=\"hidden\" id=\""
						+ fields[i]
						+ "\" name=\""
						+ fields[i]
						+ "\" value=\"{$vo."
						+ fields[i]
						+ "}\"><input type=\"text\" disabled=\"disabled\" value=\"{$vo."
						+ fields[i] + "}\"/></td>\n");
				content.append("\t\t\t\t\t\t</tr>\n");
				continue;
			}
			if (fields[i].equals("create_time")
					|| fields[i].equals("update_time")
					|| fields[i].equals("is_deleted")
					|| fields[i].equals("delete_time")) {
				continue;
			}
			if (fields[i].equals("status")) {
				content.append("\t\t\t\t\t\t<tr>\n");
				content.append("\t\t\t\t\t\t\t<td class=\"tdleft\">状态</td>\n");
				content.append("\t\t\t\t\t\t\t<td>\n");
				content.append("\t\t\t\t\t\t\t\t<select name=\"status\">\n");
				content.append("\t\t\t\t\t\t\t\t\t<option value=\"1\" <if condition=\"($vo.status eq 1)\">selected=\"selected\"</if>>有效</option>\n");
				content.append("\t\t\t\t\t\t\t\t\t<option value=\"0\" <if condition=\"($vo.status eq 0)\">selected=\"selected\"</if>>无效</option>\n");
				content.append("\t\t\t\t\t\t\t\t</select>\n");
				content.append("\t\t\t\t\t\t\t</td>\n");
				content.append("\t\t\t\t\t\t</tr>\n");
			} else if (fields[i].equals("description")) {
				content.append("\t\t\t\t\t\t<tr>\n");
				content.append("\t\t\t\t\t\t\t<td class=\"tdleft\">描述</td>\n");
				content.append("\t\t\t\t\t\t\t<td>\n");
				content.append("\t\t\t\t\t\t\t\t<textarea name=\"description\" id=\"description\" style=\"width: 800px; height: 350px;\">{$vo."
						+ fields[i] + "}</textarea>\n");
				content.append("\t\t\t\t\t\t\t</td>\n");
				content.append("\t\t\t\t\t\t</tr>\n");
			} else {
				content.append("\t\t\t\t\t\t<tr>\n");
				content.append("\t\t\t\t\t\t\t<td class=\"tdleft\">"
						+ fields[i] + "</td>\n");
				content.append("\t\t\t\t\t\t\t<td><input class=\"span3\" type=\"text\" id=\""
						+ fields[i]
						+ "\" name=\""
						+ fields[i]
						+ "\" value=\"{$vo." + fields[i] + "}\"></td>\n");
				content.append("\t\t\t\t\t\t</tr>\n");
			}
		}

		content.append("\t\t\t\t\t</tbody>\n");
		content.append("\t\t\t\t</table>\n");
		content.append("\t\t\t</form>\n");
		content.append("\t\t</div>\n");
		content.append("\t</div>\n");
		content.append("</div>\n");
		content.append("<script type=\"text/javascript\">\n");
		content.append("\t<if condition=\"C('ismobile') eq 1\">\n");
		content.append("\t\twidth=$('.container').width() * 0.9;\n");
		content.append("\t\t$(\"#description\").css({\n");
		content.append("\t\t\twidth : width\n");
		content.append("\t\t});\n");
		content.append("\t</if>\n");
		content.append("</script>\n");
		content.append("<include file=\"Public:footer\" />\n");

		try {
			String filename = baseDirName + TbNamePHP + "/editNewTab.html";
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");// 防止中文乱码
																			// @
																			// 2014.3.16
			osw.write(content.toString());
			osw.close();
			System.out.println("generate " + filename + " complete!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printViewNewTabPHP() {
		String fields[] = tb.getFields();
		int tbs = 0;
		StringBuffer content = new StringBuffer();
		content.append("<include file=\"Public:header\" />\n");
		content.append("<script type=\"text/javascript\" src=\"__PUBLIC__/js/kindeditor-all-min.js\"></script>\n");
		content.append("<script type=\"text/javascript\" src=\"__PUBLIC__/js/zh_CN.js\"></script>\n");
		content.append("<link rel=\"stylesheet\" href=\"__PUBLIC__/css/kindeditor.css\" type=\"text/css\" />\n");
		/*
		 * content.append("<script type=\"text/javascript\">\n");
		 * content.append("\t<if condition=\"C('ismobile') neq 1\">\n");
		 * content.append("\t\tvar editor;\n");
		 * content.append("\t\tKindEditor.ready(function(K) {\n");
		 * content.append
		 * ("\t\t\teditor = K.create('textarea[name=\"content\"]', {\n");
		 * content.append("\t\t\t\tuploadJson:'{:U(\"file/editor\")}',\n");
		 * content.append("\t\t\t\tallowFileManager : true,\n");
		 * content.append("\t\t\t\tloadStyleMode : false\n");
		 * content.append("\t\t\t});\n"); content.append("\t\t});\n");
		 * content.append("\t</if>\n"); content.append("</script>\n");
		 */
		content.append("<div class=\"container\">\n");
		content.append("\t<div class=\"page-header\">\n");
		content.append("\t\t<h4>View " + TbNamePHP + " Panel</h4>\n");
		content.append("\t</div>\n");
		content.append("\t<div class=\"row\">\n");
		content.append("\t\t<div class=\"span12\">\n");
		content.append("\t\t\t<include file=\"Public:alert\" />\n");
		content.append("\t\t\t<form action=\"{:U('" + TbNamePHP
				+ "/addNewTab')}\" method=\"post\">\n");
		content.append("\t\t\t\t<input  type=\"hidden\" name=\"role_id\" value=\"{$Think.session.role_id}\"/>\n");
		content.append("\t\t\t\t<table class=\"table\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\">\n");
		content.append("\t\t\t\t\t<thead>\n");
		content.append("\t\t\t\t\t\t<tr>\n");
		content.append("\t\t\t\t\t\t\t<td colspan=\"2\">&nbsp;<input class=\"btn\" type=\"button\" onclick=\"javascript:history.go(-1)\" value=\"返回\"/></td>\n");
		content.append("\t\t\t\t\t\t</tr>\n");
		content.append("\t\t\t\t\t</thead>\n");
		content.append("\t\t\t\t\t<tfoot>\n");
		content.append("\t\t\t\t\t\t<tr>\n");
		content.append("\t\t\t\t\t\t\t<td colspan=\"2\"> &nbsp;&nbsp; <input class=\"btn\" type=\"button\" onclick=\"javascript:history.go(-1)\" value=\"返回\"/></td>\n");
		content.append("\t\t\t\t\t\t</tr>\n");
		content.append("\t\t\t\t\t</tfoot>\n");
		content.append("\t\t\t\t\t<tbody>\n");
		content.append("\t\t\t\t\t\t<th colspan=\"2\">Information</th>\n");
		for (int i = 0; i < fields.length; i++) {
			// if(fields[i].equals("create_time")||fields[i].equals("update_time")||fields[i].equals("is_deleted")||fields[i].equals("delete_time")){
			// continue;
			// }
			if (fields[i].equals("status")) {
				content.append("\t\t\t\t\t\t<tr>\n");
				content.append("\t\t\t\t\t\t\t<td class=\"tdleft\">状态</td>\n");
				content.append("\t\t\t\t\t\t\t<td>\n");
				content.append("\t\t\t\t\t\t\t\t\t<if condition=\"($vo.status eq 1)\">有效</if>\n");
				content.append("\t\t\t\t\t\t\t\t\t<if condition=\"($vo.status eq 0)\">无效</if>\n");
				content.append("\t\t\t\t\t\t\t</td>\n");
				content.append("\t\t\t\t\t\t</tr>\n");
			} else if (fields[i].equals("is_deleted")) {
				content.append("\t\t\t\t\t\t<tr>\n");
				content.append("\t\t\t\t\t\t\t<td class=\"tdleft\">is_deleted</td>\n");
				content.append("\t\t\t\t\t\t\t<td>\n");
				content.append("\t\t\t\t\t\t\t\t\t<if condition=\"($vo.is_deleted eq 1)\">Yes</if>\n");
				content.append("\t\t\t\t\t\t\t\t\t<if condition=\"($vo.is_deleted eq 0)\">No</if>\n");
				content.append("\t\t\t\t\t\t\t</td>\n");
				content.append("\t\t\t\t\t\t</tr>\n");
			} else if (fields[i].contains("time")) {

				content.append("\t\t\t\t\t\t<tr>\n");
				content.append("\t\t\t\t\t\t\t<td class=\"tdleft\">"
						+ fields[i] + "</td>\n");
				content.append("\t\t\t\t\t\t\t<td>\n");
				content.append("\t\t\t\t\t\t\t\t{$vo." + fields[i]
						+ "|date='Y-m-d',###}\n");
				content.append("\t\t\t\t\t\t\t</td>\n");
				content.append("\t\t\t\t\t\t</tr>\n");

			} else if (fields[i].equals("description")) {

				content.append("\t\t\t\t\t\t<tr>\n");
				content.append("\t\t\t\t\t\t\t<td class=\"tdleft\">描述</td>\n");
				content.append("\t\t\t\t\t\t\t<td>\n");
				content.append("\t\t\t\t\t\t\t\t<div name=\"description\" id=\"description\" style=\"width: 800px; height: 350px;border=1px solid #aaa\">{$vo."
						+ fields[i] + "}</div>\n");
				content.append("\t\t\t\t\t\t\t</td>\n");
				content.append("\t\t\t\t\t\t</tr>\n");

			} else {
				content.append("\t\t\t\t\t\t<tr>\n");
				content.append("\t\t\t\t\t\t\t<td class=\"tdleft\">"
						+ fields[i] + "</td>\n");
				content.append("\t\t\t\t\t\t\t<td>{$vo." + fields[i]
						+ "}</td>\n");
				content.append("\t\t\t\t\t\t</tr>\n");
			}
		}

		content.append("\t\t\t\t\t</tbody>\n");
		content.append("\t\t\t\t</table>\n");
		content.append("\t\t\t</form>\n");
		content.append("\t\t</div>\n");
		content.append("\t</div>\n");
		content.append("</div>\n");
		content.append("<script type=\"text/javascript\">\n");
		content.append("\t<if condition=\"C('ismobile') eq 1\">\n");
		content.append("\t\twidth=$('.container').width() * 0.9;\n");
		content.append("\t\t$(\"#content\").css({\n");
		content.append("\t\t\twidth : width\n");
		content.append("\t\t});\n");
		content.append("\t</if>\n");
		content.append("</script>\n");
		content.append("<include file=\"Public:footer\" />\n");

		try { // 输出到文件
			String filename = baseDirName + TbNamePHP + "/viewNewTab.html";
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");// 防止中文乱码
																			// @
																			// 2014.3.16
			osw.write(content.toString());
			osw.close();
			System.out.println("generate " + filename + " complete!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printViewPHP() {
		String field[] = tb.getFields();
		int tbs = 0;
		StringBuffer content = new StringBuffer();
		content.append("<form class=\"form-horizontal\" action=\"{:U('"
				+ TbNamePHP + "/add')}\" method=\"post\" >\n");

		for (int i = 0; i < field.length; i++) {

			if (field[i].contains("time")) {
				// content.append("\t\t\t\t\t\t\t\t<td><a class=\"role_info\" href=\"javascript:void(0)\">{$vo."+fields[i]+"|date='Y-m-d',###}</a></td>\n");
				content.append("\t<div class=\"control-group\">\n");
				content.append("\t\t<label class=\"control-label\" for=\"name\">"
						+ field[i] + "</label>\n");
				content.append("\t\t<div class=\"controls\">{$vo." + field[i]
						+ "|date='Y-m-d',###}</div>\n");
				content.append("\t</div>\n");
			} else if (field[i].equals("status")) {
				content.append("\t<div class=\"control-group\">\n");
				content.append("\t\t<label class=\"control-label\" for=\"name\">isValid</label>\n");
				content.append("\t\t<div class=\"controls\"><if condition=\"($vo.status eq 1)\">valid</if><if condition=\"($vo.status eq 0)\">invalid</if></div>\n");
				content.append("\t</div>\n");
			} else if (field[i].equals("is_deleted")) {
				content.append("\t<div class=\"control-group\">\n");
				content.append("\t\t<label class=\"control-label\" for=\"name\">isValid</label>\n");
				content.append("\t\t<div class=\"controls\"><if condition=\"($vo.is_deleted eq 1)\">yes</if><if condition=\"($vo.status eq 0)\">No</if></div>\n");
				content.append("\t</div>\n");
			} else if (field[i].equals("description")) {
				content.append("\t<div class=\"control-group\">\n");
				content.append("\t\t<label class=\"control-label\" for=\"name\">"
						+ field[i] + "</label>\n");
				content.append("\t\t<div class=\"controls\"  style=\"width: 400px; height: 100px; border=\"1px solid #aaa\"\">{$vo."
						+ field[i] + "}</div>\n");
				content.append("\t</div>\n");
			} else {
				content.append("\t<div class=\"control-group\">\n");
				content.append("\t\t<label class=\"control-label\" for=\"name\">"
						+ field[i] + "</label>\n");
				content.append("\t\t<div class=\"controls\">{$vo." + field[i]
						+ "}</div>\n");
				content.append("\t</div>\n");
			}
		}

		content.append("\t<div class=\"control-group\">\n");
		content.append("\t\t<label class=\"control-label\" for=\"name\"></label>\n");
		content.append("\t\t<div class=\"controls\">\n");
		// content.append("\t\t\t<input class=\"btn btn-primary\" type=\"submit\" name=\"submit\" value=\"保存\"/> &nbsp; \n");
		content.append("\t\t\t<input class=\"btn btn-primary\" onclick=\"$('#view_dialog').dialog('close');\" type=\"button\" value=\"确定\"/>\n");
		content.append("\t\t</div>\n");
		content.append("\t</div>\n");
		content.append("</form>\n");

		try { // 输出到文件
			String filename = baseDirName + TbNamePHP + "/view.html";
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");// 防止中文乱码
																			// @
																			// 2014.3.16
			osw.write(content.toString());
			osw.close();
			System.out.println("generate " + filename + " complete!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printIndexPHP() {// 输出表的管理主页
		String fields[] = tb.getFields();
		String methods[] = tb.getGetterMethods();
		StringBuffer content = new StringBuffer();
		content.append("<include file=\"Public:header\" />\n");
		content.append("<div class=\"container\">\n");
		content.append("\t<div class=\"page-header\" style=\"border:none; font-size:14px; \">\n");
		content.append("\t\t<ul class=\"nav nav-tabs\">\n");
		content.append("\t\t\t\t<li <if condition=\"$_GET['content'] == '"
				+ tbNamePHP
				+ "' || $_GET['content'] == ''\">class=\"active\"</if>><a  href=\"{:U('"
				+ TbNamePHP
				+ "/index')}\"><img src=\"__PUBLIC__/img/customer_icon.png\"/>&nbsp;"
				+ TbNamePHP + "</a></li>\n");
		content.append("\t\t\t\t<li><a href=\"javascript:void(0)\" target=\"_blank\" style=\"font-size: 12px;color: rgb(255, 102, 0);\"><img width=\"20px;\" src=\"__PUBLIC__/img/help.png\"/> 帮助</a></li>\n");
		content.append("\t\t</ul>\n");
		content.append("\t</div>\n");
		content.append("\t<include file=\"Public:alert\" />\n");
		content.append("\t<if condition=\"$_GET['content'] != 'resource'\">\n");
		content.append("\t<p class=\"view\">\n");
		content.append("\t\t<b><img src=\"__PUBLIC__/img/view.png\"/>"
				+ TbNamePHP + "View：</b>\n");
		content.append("\t\t<img src=\" __PUBLIC__/img/by_owner.png\"/> \n");
		content.append("\t\t<a href=\"{:U('"
				+ TbNamePHP
				+ "/index','by=all')}\" <if condition=\"$Think.get.by eq null\">class=\"active\"</if>>全部</a> |\n");
		content.append("\t\t<a href=\"{:U('"
				+ TbNamePHP
				+ "/index','by=me')}\" <if condition=\"$Think.get.by eq 'me'\">class=\"active\"</if>>My "
				+ TbNamePHP + "</a> |\n");
		content.append("\t\t<img src=\"__PUBLIC__/img/by_time.png\"/> <a href=\"{:U('"
				+ TbNamePHP
				+ "/index','by=today')}\" <if condition=\"$Think.get.by eq 'today'\">class=\"active\"</if>>今日新建</a> |\n");
		content.append("\t\t<a href=\"{:U('"
				+ TbNamePHP
				+ "/index','by=week')}\" <if condition=\"$Think.get.by eq 'week'\">class=\"active\"</if>>本周新建</a> |\n");
		content.append("\t\t<a href=\"{:U('"
				+ TbNamePHP
				+ "/index','by=month')}\" <if condition=\"$Think.get.by eq 'month'\">class=\"active\"</if>>本月新建</a>  &nbsp;  \n");
		content.append("\t\t<a href=\"{:U('"
				+ TbNamePHP
				+ "/index','by=add')}\" <if condition=\"$Think.get.by eq 'add'\">class=\"active\"</if>>最近创建</a> | 	\n");
		content.append("\t\t<a href=\"{:U('"
				+ TbNamePHP
				+ "/index','by=update')}\" <if condition=\"$Think.get.by eq 'update'\">class=\"active\"</if>>最近更新</a> &nbsp;  &nbsp; \n");
		content.append("\t\t<a href=\"{:U('"
				+ TbNamePHP
				+ "/index','by=deleted')}\" <if condition=\"$Think.get.by eq 'deleted'\">class=\"active\"</if>><img src=\"__PUBLIC__/img/task_garbage.png\"/> 回收站</a>\n");
		content.append("\t</p>\n");
		content.append("\t</if>\n");
		content.append("\t<div class=\"row\">	\n");
		content.append("\t\t<div class=\"span12\">\n");
		content.append("\t\t\t<ul class=\"nav pull-left\">\n");
		content.append("\t\t\t\t<if condition=\"$Think.session.admin eq 1 or $Think.get.by neq 'deleted'\">\n");
		content.append("\t\t\t\t\t<div class=\"btn-group pull-left\">\n");
		content.append("\t\t\t\t\t\t<a class=\"btn dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">\n");
		content.append("\t\t\t\t\t\t\t&nbsp;批量操作\n");
		content.append("\t\t\t\t\t\t\t<span class=\"caret\"></span>\n");
		content.append("\t\t\t\t\t\t</a>\n");
		content.append("\t\t\t\t\t\t<ul class=\"dropdown-menu\">\n");
		content.append("\t\t\t\t\t\t\t<li><a id=\"delete\"  style=\"margin-right: 5px;\" href=\"javascript:void(0)\">批量删除</a></li>\n");
		content.append("\t\t\t\t\t\t\t<li><a id=\"recover\"  style=\"margin-right: 5px;\" href=\"javascript:void(0)\">批量恢复</a></li>\n");
		content.append("\t\t\t\t\t\t</ul>\n");
		content.append("\t\t\t\t\t</div>\n");
		content.append("\t\t\t\t</if>\n");
		content.append("\t\t\t\t<li class=\"pull-left\">\n");
		content.append("\t\t\t\t\t<form class=\"form-inline\" id=\"searchForm\" action=\"index.php\" method=\"get\">\n");
		content.append("\t\t\t\t\t\t<ul class=\"nav pull-left\">\n");
		content.append("\t\t\t\t\t\t<li class=\"pull-left\" >\n");
		content.append("\t\t\t\t\t\t\t<select style=\"width:auto\" id=\"field\" onchange=\"changeCondition()\" name=\"field\">\n");
		content.append("\t\t\t\t\t\t\t\t<option class=\"\" value=\"\">--请选择筛选条件--</option>\n");
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].equals("create_time")
					|| fields[i].equals("update_time")
					|| fields[i].equals("is_deleted")
					|| fields[i].equals("delete_time")
					|| fields[i].equals("description")) {
				continue;
			}
			content.append("\t\t\t\t\t\t\t\t<option class=\"input-medium search-query\" value=\""
					+ fields[i] + "\">" + fields[i] + "</option>\n");
		}
		// content.append("\t\t\t\t\t\t\t\t<option class=\"input-medium search-query\" value=\""+fields[0]+"\">姓名</option>\n");
		content.append("\t\t\t\t\t\t\t</select>&nbsp;&nbsp;\n");
		content.append("\t\t\t\t\t\t</li>\n");
		content.append("\t\t\t\t\t\t<li id=\"conditionContent\" class=\"pull-left\">\n");
		content.append("\t\t\t\t\t\t\t<select id=\"condition\" style=\"width:auto\" name=\"condition\" onchange=\"changeSearch()\">\n");
		content.append("\t\t\t\t\t\t\t\t<option value=\"contains\">包含</option>\n");
		content.append("\t\t\t\t\t\t\t\t<option value=\"not_contain\">不包含</option>\n");
		content.append("\t\t\t\t\t\t\t\t<option value=\"is\">是</option>\n");
		content.append("\t\t\t\t\t\t\t\t<option value=\"isnot\">不是</option>\n");
		content.append("\t\t\t\t\t\t\t\t<option value=\"start_with\">开始字符</option>\n");
		content.append("\t\t\t\t\t\t\t\t<option value=\"end_with\">结束字符</option>\n");
		content.append("\t\t\t\t\t\t\t\t<option value=\"is_empty\">为空</option>\n");
		content.append("\t\t\t\t\t\t\t\t<option value=\"is_not_empty\">不为空</option>\n");
		content.append("\t\t\t\t\t\t\t</select>&nbsp;&nbsp;\n");
		content.append("\t\t\t\t\t\t</li>\n");
		content.append("\t\t\t\t\t\t<li id=\"searchContent\" class=\"pull-left\">\n");
		content.append("\t\t\t\t\t\t\t<input id=\"search\" type=\"text\" class=\"input-medium search-query\" name=\"search\"/>&nbsp;&nbsp;\n");
		content.append("\t\t\t\t\t\t</li>\n");
		content.append("\t\t\t\t\t\t<li class=\"pull-left\"> \n");
		content.append("\t\t\t\t\t\t\t<input type=\"hidden\" name=\"m\" value=\""
				+ TbNamePHP + "\"/>\n");
		content.append("\t\t\t\t\t\t\t<if condition=\"$Think.get.by neq null\"><input type=\"hidden\" name=\"by\" value=\"{$Think.get.by}\"/></if>\n");
		content.append("\t\t\t\t\t\t\t<button type=\"button\" id=\"dosearch\" class=\"btn\"><img src=\"__PUBLIC__/img/search.png\"/>搜索</button>\n");
		content.append("\t\t\t\t\t\t\t&nbsp;\n");
		content.append("\t\t\t\t\t\t</li>\n");
		content.append("\t\t\t\t\t\t</ul>\n");
		content.append("\t\t\t\t\t\t<input type=\"hidden\" name=\"act\" id=\"act\" value=\"search\"/>\n");
		content.append("\t\t\t\t\t\t<input type=\"hidden\" name=\"content\" <if condition=\"$_GET['content']\">value=\"resource\"</if>/>\n");
		content.append("\t\t\t\t\t</form>\n");
		content.append("\t\t\t\t</li>\n");
		content.append("\t\t\t</ul>\n");
		content.append("\t\t\t<div class=\"pull-right\">\n");
		content.append("\t\t\t\t<a href=\"{:U('"
				+ TbNamePHP
				+ "/addNewTab')}\" class=\"btn btn-primary\"><i class=\"icon-plus\"></i>&nbsp; AddNewTab "
				+ TbNamePHP + "</a>&nbsp;\n");
		content.append("\t\t\t\t<a href=\"javascript:void(0)\" id=\"add_btn\" class=\"btn btn-primary\"><i class=\"icon-plus\"></i>&nbsp; Add "
				+ TbNamePHP + "</a>&nbsp;\n");
		content.append("\t\t\t\t<div class=\"btn-group\">\n");
		content.append("\t\t\t\t\t<button class=\"btn btn-primary dropdown-toggle\" data-toggle=\"dropdown\"> <i class=\"icon-wrench\"></i>&nbsp;Tool Panel<span class=\"caret\"></span></button>\n");
		content.append("\t\t\t\t\t<ul class=\"dropdown-menu\">\n");
		content.append("\t\t\t\t\t\t<!-- <li><a href=\"{:U('"
				+ TbNamePHP
				+ "/analytics')}\" id=\"analytics\"  class=\"link\"> <i class=\"icon-align-left\"></i>"
				+ TbNamePHP + "统计</a></li> -->\n");
		content.append("\t\t\t\t\t\t<li><a id=\"import_excel\" class=\"link\" href=\"javascript:void(0);\"><i class=\"icon-upload\"></i>Import "
				+ TbNamePHP + "</a></li>\n");
		content.append("\t\t\t\t\t\t<li><a href=\"{:U('"
				+ TbNamePHP
				+ "/excelExport')}\" id=\"excelExport\"  class=\"link\"> <i class=\"icon-download\"> </i>Export "
				+ TbNamePHP + "</a></li>\n");
		content.append("\t\t\t\t\t</ul>\n");
		content.append("\t\t\t\t</div>\n");
		content.append("\t\t\t</div>\n");
		content.append("\t\t</div>\n");
		content.append("\t\t\n");
		content.append("\t\t<div class=\"span12\">\n");
		content.append("\t\t\t<form id=\"form1\" action=\"\" method=\"post\">\n");
		content.append("\t\t\t\t<input type=\"hidden\" name=\"owner_role\" id=\"hidden_owner_id\" value=\"0\"/>\n");
		content.append("\t\t\t\t<input type=\"hidden\" name=\"message_alert\" id=\"hidden_message\" value=\"0\"/>\n");
		content.append("\t\t\t\t<input type=\"hidden\" name=\"sms_alert\" id=\"hidden_sms\" value=\"0\"/>\n");
		content.append("\t\t\t\t<input type=\"hidden\" name=\"email_alert\" id=\"hidden_email\" value=\"0\"/>\n");
		content.append("\t\t\t\t<input type=\"hidden\" name=\"operating_type\" id=\"operating_type\" value=\"\"/>\n");
		content.append("\t\t\t\t<table class=\"table table-hover table-striped\">\n");
		content.append("\t\t\t\t\t<if condition=\"$" + tbNamePHP
				+ "_list eq null\">\n");
		content.append("\t\t\t\t\t\t<tr><td>----暂无数据！----</td></tr>\n");
		content.append("\t\t\t\t\t<else/>\n");
		content.append("\t\t\t\t\t<thead>\n");
		content.append("\t\t\t\t\t\t<tr id=\"childNodes_num\">\n");
		content.append("\t\t\t\t\t\t\t<th width=\"20\"><input type=\"checkbox\" id=\"check_all\"/></th>\n");
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].equals("description"))
				continue;// description 限制输出

			if (i >= 8)
				break;
			else
				content.append("\t\t\t\t\t\t\t<th>" + fields[i] + "</th>\n");

		}
		/*
		 * content.append("\t\t\t\t\t\t\t<th>ID</th>\n");
		 * content.append("\t\t\t\t\t\t\t<th>姓名</th>\n");
		 * content.append("\t\t\t\t\t\t\t<th>角色ID</th>\n");
		 * content.append("\t\t\t\t\t\t\t<th>密钥</th>\n");
		 * content.append("\t\t\t\t\t\t\t<th>创建时间</th>\n");
		 * content.append("\t\t\t\t\t\t\t<th>修改时间</th>\n");
		 * content.append("\t\t\t\t\t\t\t<th>状态</th>\n");
		 */
		content.append("\t\t\t\t\t\t\t<th colspan=\"2\">操作</th>\n");
		content.append("\t\t\t\t\t\t</tr>\n");
		content.append("\t\t\t\t\t</thead>\n");
		content.append("\t\t\t\t\t<tfoot>\n");
		content.append("\t\t\t\t\t\t<tr>\n");
		content.append("\t\t\t\t\t\t\t<td id=\"td_colspan\">{$page}</td>\n");
		content.append("\t\t\t\t\t\t</tr>\n");
		content.append("\t\t\t\t\t</tfoot>\n");
		content.append("\t\t\t\t\t<tbody>\n");
		content.append("\t\t\t\t\t\t<volist name=\"" + tbNamePHP
				+ "_list\" id=\"vo\">\n");
		content.append("\t\t\t\t\t\t\t<tr>\n");
		content.append("\t\t\t\t\t\t\t\t<td>\n");
		content.append("\t\t\t\t\t\t\t\t\t<input name=\""
				+ tbNamePHP
				+ "_ids[]\" class=\"check_list\" type=\"checkbox\" value=\"{$vo."
				+ fields[0] + "}\"/>\n");
		content.append("\t\t\t\t\t\t\t\t</td>\n");
		content.append("\t\t\t\t\t\t\t\t\n");

		// content.append("\t\t\t\t\t\t\t\t<td><a class=\"role_info\" rel=\"{$vo."+fields[0]+"}\" href=\"javascript:void(0)\">{$vo."+fields[0]+"}</a></td>\n");
		// content.append("\n");
		for (int i = 0; i < fields.length; i++) {
			if (i >= 8)
				break;
			if (fields[i].equals("description"))
				continue;

			if (fields[i].equals("delete_time")) {

				content.append("\t\t\t\t\t\t\t\t<td><a class=\"role_info\" href=\"javascript:void(0)\"><if condition=\"($vo.is_deleted eq 1)\">{$vo."
						+ fields[i]
						+ "|date='Y-m-d',###}<else/>--</if></a></td>\n");
			} else if (fields[i].equals("create_time")
					|| fields[i].equals("update_time")) {
				content.append("\t\t\t\t\t\t\t\t<td><a class=\"role_info\" href=\"javascript:void(0)\">{$vo."
						+ fields[i] + "|date='Y-m-d',###}</a></td>\n");
			} else if (fields[i].equals("status")) {
				content.append("\t\t\t\t\t\t\t\t<td><a class=\"role_info\" href=\"javascript:void(0)\"><if condition=\"($vo.status eq 1)\">valid<else/>invalid</if></a></td>\n");
			} else if (fields[i].equals("is_deleted")) {
				content.append("\t\t\t\t\t\t\t\t<td><a class=\"role_info\" href=\"javascript:void(0)\"><if condition=\"($vo.is_deleted eq 1)\">yes<else/>no</if></a></td>\n");
			} else {
				content.append("\t\t\t\t\t\t\t\t<td><a class=\"role_info\" href=\"javascript:void(0)\">{$vo."
						+ fields[i] + "}</a></td>\n");
			}
		}
		/*
		 * content.append(
		 * "\t\t\t\t\t\t\t\t<td><a class=\"role_info\" href=\"javascript:void(0)\">{$vo.username}</a></td>\n"
		 * ); content.append(
		 * "\t\t\t\t\t\t\t\t<td><a class=\"role_info\" href=\"javascript:void(0)\">{$vo.role_id}</a></td>\n"
		 * ); content.append(
		 * "\t\t\t\t\t\t\t\t<td><a class=\"role_info\" href=\"javascript:void(0)\">{$vo.salt}</a></td>\n"
		 * ); content.append(
		 * "\t\t\t\t\t\t\t\t<td><a class=\"role_info\" href=\"javascript:void(0)\">{$vo.create_time|date='Y-m-d',###}</a></td>\n"
		 * ); content.append(
		 * "\t\t\t\t\t\t\t\t<td><a class=\"role_info\" href=\"javascript:void(0)\">{$vo.update_time|date='Y-m-d',###}</a></td>\n"
		 * ); content.append(
		 * "\t\t\t\t\t\t\t\t<td><a class=\"role_info\" href=\"javascript:void(0)\">{$vo.status}</a></td>\n"
		 * );
		 */
		content.append("\t\t\t\t\t\t\t\t\t\t\n");
		content.append("\t\t\t\t\t\t\t\t<td><a class=\"view_btn\" rel=\"{$vo."
				+ fields[0]
				+ "}\" href=\"javascript:void(0)\">View</a>&nbsp;&nbsp;\n");
		content.append("\t\t\t\t\t\t\t\t\t<a class=\"viewNewTab_btn\" rel=\"{$vo."
				+ fields[0]
				+ "}\" href=\"{:U('"
				+ TbNamePHP
				+ "/viewNewTab','"
				+ fields[0]
				+ "='.$vo['"
				+ fields[0]
				+ "'])}\">ViewNewTab</a>&nbsp;&nbsp;\n");
		content.append("\t\t\t\t\t\t\t\t\t<a class=\"edit_btn\" rel=\"{$vo."
				+ fields[0]
				+ "}\" href=\"javascript:void(0)\">Edit</a>&nbsp;&nbsp;\n");
		content.append("\t\t\t\t\t\t\t\t\t<a class=\"editNewTab_btn\" rel=\"{$vo."
				+ fields[0]
				+ "}\" href=\"{:U('"
				+ TbNamePHP
				+ "/editNewTab','"
				+ fields[0]
				+ "='.$vo['"
				+ fields[0]
				+ "'])}\">EditNewTab</a>&nbsp;&nbsp;\n");
		// content.append("\t\t\t\t\t\t\t\t\t<a class=\"delete_btn\" rel=\"{$vo."+fields[0]+"}\" href=\"javascript:void(0)\">Del</a>\n");
		// add recover
		content.append("\t\t\t\t\t\t\t\t\t<if condition=\"($by_deleted eq 1)\"><a class=\"recover_btn\" rel=\"{$vo."
				+ fields[0]
				+ "}\" href=\"javascript:void(0)\">Recover</a><else/><a class=\"delete_btn\" rel=\"{$vo."
				+ fields[0] + "}\" href=\"javascript:void(0)\">Del</a></if>\n");
		content.append("\t\t\t\t\t\t\t\t</td>\n");
		content.append("\t\t\t\t\t\t\t</tr>\n");
		content.append("\t\t\t\t\t\t</volist>\n");
		content.append("\t\t\t\t\t</tbody>\n");
		content.append("\t\t\t\t\t</if>\n");
		content.append("\t\t\t\t</table>\n");
		content.append("\t\t\t</form>\n");
		content.append("\t\t</div>\n");
		content.append("\t</div>\n");
		content.append("</div>\n");
		content.append("<div id=\"add_dialog\" class=\"hide\" title=\"添加信息\">loading...</div>\n");
		content.append("<div id=\"edit_dialog\" class=\"hide\" title=\"编辑信息\">loading...</div>\n");
		content.append("<div id=\"view_dialog\" class=\"hide\" title=\"查看信息\">loading...</div>\n");
		content.append("<div class=\"hide\" id=\"dialog-import\" title=\"导入数据\">loading...</div>\n");
		content.append("<script type=\"text/javascript\">\n");
		content.append("<if condition=\"C('ismobile') eq 1\">width=$('.container').width() * 0.9;<else/>width=600;</if>\n");
		content.append("$(\"#dialog-import\").dialog({\n");
		content.append("\tautoOpen: false,\n");
		content.append("\tmodal: true,\n");
		content.append("\twidth: width,\n");
		content.append("\tmaxHeight: 400,\n");
		content.append("\tposition: [\"center\",100]\n");
		content.append("});\n");
		content.append("\n");
		content.append("\n");
		content.append("function changeContent(){\n");
		content.append("\ta = $(\"#select1  option:selected\").val();\n");
		content.append("\twindow.location.href=\"{:U('" + TbNamePHP
				+ "/index', 'by=')}\"+a;\n");
		content.append("}\n");
		content.append("\n");
		content.append("\n");
		content.append("\n");
		content.append("$(function(){\n");
		content.append("\n");
		content.append("\t$(\"#check_all\").click(function(){\n");
		content.append("\t\t$(\"input[class='check_list']\").prop('checked', $(this).prop(\"checked\"));\n");
		content.append("\t});\n");
		content.append("\t$('#delete').click(function(){\n");
		content.append("\t\tif(confirm('确认删除吗？')){\n");
		content.append("\t\t\t$(\"#form1\").attr('action', '{:U(\"" + TbNamePHP
				+ "/delete\")}');\n");
		content.append("\t\t\t$(\"#form1\").submit();\n");
		content.append("\t\t}\n");
		content.append("\t});\n");
		content.append("\t$('#recover').click(function(){\n");
		content.append("\t\tif(confirm('确认恢复吗？')){\n");
		content.append("\t\t\t$(\"#form1\").attr('action', '{:U(\"" + TbNamePHP
				+ "/recover\")}');\n");
		content.append("\t\t\t$(\"#form1\").submit();\n");
		content.append("\t\t}\n");
		content.append("\t});\n");
		content.append("\n");
		content.append("\t$(\"#dosearch\").click(function(){\n");
		content.append("\t\tresult = checkSearchForm();\n");
		content.append("\t\tif(result) $(\"#searchForm\").submit();\n");
		content.append("\t});\n");
		content.append("\t//\n");
		content.append("\t\n");
		content.append("});\n");
		content.append("$('#add_dialog').dialog({\n");
		content.append("\tautoOpen: false,\n");
		content.append("\tmodal: true,\n");
		content.append("\twidth: 600,\n");
		content.append("\tmaxHeight: 400,\n");
		content.append("\tposition :[\"center\",100]\n");
		content.append("});\n");
		content.append("$('#view_dialog').dialog({\n");
		content.append("\tautoOpen: false,\n");
		content.append("\tmodal: true,\n");
		content.append("\twidth: 700,\n");
		content.append("\tmaxHeight: 900,\n");
		content.append("\tposition :[\"center\",100]\n");
		content.append("});\n");
		content.append("$('#edit_dialog').dialog({\n");
		content.append("\tautoOpen: false,\n");
		content.append("\tmodal: true,\n");
		content.append("\twidth: 700,\n");
		content.append("\tmaxHeight: 900,\n");
		content.append("\tposition :[\"center\",100]\n");
		content.append("});\n");
		content.append("$(\"#dialog-import\").dialog({\n");
		content.append("\tautoOpen: false,\n");
		content.append("\tmodal: true,\n");
		content.append("\twidth: 600,\n");
		content.append("\tmaxHeight: 400,\n");
		content.append("\tposition: [\"center\",100]\n");
		content.append("});\n");
		content.append("$(function(){\n");
		content.append("\t$(\"#add_btn\").click(function(){\n");
		content.append("\t\t$('#add_dialog').dialog('open');\n");
		content.append("\t\t$('#add_dialog').load('{:U('" + TbNamePHP
				+ "/add')}');\n");
		content.append("\t});\n");
		content.append("\t$(\".edit_btn\").click(function(){\n");
		content.append("\t\t$('#edit_dialog').dialog('open');\n");
		content.append("\t\t$id = $(this).attr('rel');\n");
		content.append("\t\t$('#edit_dialog').load(\"{:U('" + TbNamePHP
				+ "/edit','" + fields[0] + "=')}\"+$id);\n");
		content.append("\t});\n");
		content.append("\t$(\".view_btn\").click(function(){\n");
		content.append("\t\t$('#view_dialog').dialog('open');\n");
		content.append("\t\t$id = $(this).attr('rel');\n");
		content.append("\t\t$('#view_dialog').load(\"{:U('" + TbNamePHP
				+ "/view','" + fields[0] + "=')}\"+$id);\n");
		content.append("\t});\n");
		content.append("\t$(\".delete_btn\").click(function(){\n");
		content.append("\t\t$id = $(this).attr('rel');\n");
		content.append("\t\twindow.location.href=\"{:U('" + TbNamePHP
				+ "/delete','" + fields[0] + "=')}\"+$id;\n");
		content.append("\t});\n");
		content.append("\t$(\".recover_btn\").click(function(){\n");
		content.append("\t\t$id = $(this).attr('rel');\n");
		content.append("\t\twindow.location.href=\"{:U('" + TbNamePHP
				+ "/recover','" + fields[0] + "=')}\"+$id;\n");
		content.append("\t});\n");
		content.append("\t$(\"#import_excel\").click(function(){\n");
		content.append("\t\t$('#dialog-import').dialog('open');\n");
		content.append("\t\t$('#dialog-import').load('{:U(\"" + TbNamePHP
				+ "/excelimport\")}');\n");
		content.append("\t});\n");
		content.append("});\n");
		content.append("<if condition=\"$" + tbNamePHP + "_list neq null\">\n");
		content.append("\t$nodes_num = document.getElementById(\"childNodes_num\").children.length;\n");
		content.append("\t$(\"#td_colspan\").attr('colspan',$nodes_num);\n");
		content.append("</if>\n");
		content.append("</script>\n");
		content.append("<include file=\"Public:footer\" />\n");

		try { // 输出到Query.jsp
			String filename = baseDirName + TbNamePHP + "/index.html";
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");// 防止中文乱码
																			// @
																			// 2014.3.16
			osw.write(content.toString());
			osw.close();
			System.out.println("generate " + filename + " complete!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//
	public void printTbActionPHP() {// 输出表的管理主页
		String fields[] = tb.getFields();
		String methods[] = tb.getGetterMethods();
		StringBuffer content = new StringBuffer();

		content.append("<?php\n");
		content.append("class " + TbNamePHP + "Action extends Action\n");
		content.append("{\n");
		// add-start
		content.append("\tpublic function _initialize(){\n");
		content.append("\n\n\n");
		content.append("\t}\n");
		// 查询数据
		content.append("\tpublic function index(){\n");
		content.append("\t\t\n");
		content.append("\t\t$this->by_deleted=0;\n");
		content.append("\t\t$where = array();\n");
		content.append("\t\t$where['is_deleted'] = array('eq', 0);\n");
		content.append("\t\t$params = array();\n");
		content.append("\t\t$by = isset($_GET['by']) ? trim($_GET['by']) : '';\n");
		content.append("\t\tswitch ($by) {\n");
		content.append("\t\t\tcase 'all' : $where['is_deleted'] = array('neq', -1); break;\n");
		content.append("\t\t\tcase 'today' : $where['create_time'] =  array('gt',strtotime(date('Y-m-d', time()))); break;\n");
		content.append("\t\t\tcase 'week' : $where['create_time'] =  array('gt',(strtotime(date('Y-m-d')) - (date('N', time()) - 1) * 86400)); break;\n");
		content.append("\t\t\tcase 'month' : $where['create_time'] = array('gt',strtotime(date('Y-m-01', time()))); break;\n");
		content.append("\t\t\tcase 'add' : $order = 'create_time desc'; break;\n");
		content.append("\t\t\tcase 'update' : $order = 'update_time desc'; break;\n");
		content.append("\t\t\tcase 'sub' : $where['owner_role_id'] = array('in',implode(',', $below_ids)); break;\n");
		content.append("\t\t\tcase 'deleted' : $where['is_deleted'] = 1;$this->by_deleted=1;break;\n");
		content.append("\t\t\tcase 'me' : $where['owner_role_id'] = session('role_id'); break;\n");
		content.append("\t\t\tdefault :\n");
		content.append("\t\t\tbreak;\n");
		content.append("\t\t}\n");
		// ////////////
		content.append("\t\t$m_" + tbNamePHP + " = D('" + TbNamePHP
				+ "'); // \n");
		content.append("\t\timport(\"@.ORG.Page\");//定制ORG-Page-bootstrap-pagination\n");

		content.append("\t\tif ($_REQUEST[\"field\"]) {\n");
		content.append("\t\t\t$field = trim($_REQUEST['field']) == 'all' ? 'title|content' : $_REQUEST['field'];\n");
		content.append("\t\t\t$search = empty($_REQUEST['search']) ? '' : trim($_REQUEST['search']);\n");
		content.append("\t\t\t$condition = empty($_REQUEST['condition']) ? 'is' : trim($_REQUEST['condition']);\n");
		content.append("\t\t\tif('create_time' == $field ||'update_time' == $field) $search = is_numeric($search)?$search:strtotime($search);\n");
		content.append("\t\t\tswitch ($condition) {\n");
		content.append("\t\t\t\tcase \"is\" : $where[$field] = array('eq',$search);break;\n");
		content.append("\t\t\t\tcase \"isnot\" :  $where[$field] = array('neq',$search);break;\n");
		content.append("\t\t\t\tcase \"contains\" :  $where[$field] = array('like','%'.$search.'%');break;\n");
		content.append("\t\t\t\tcase \"not_contain\" :  $where[$field] = array('notlike','%'.$search.'%');break;\n");
		content.append("\t\t\t\tcase \"start_with\" :  $where[$field] = array('like',$search.'%');break;\n");
		content.append("\t\t\t\tcase \"end_with\" :  $where[$field] = array('like','%'.$search);break;\n");
		content.append("\t\t\t\tcase \"is_empty\" :  $where[$field] = array('eq','');break;\n");
		content.append("\t\t\t\tcase \"is_not_empty\" :  $where[$field] = array('neq','');break;\n");
		content.append("\t\t\t\tcase \"gt\" :  $where[$field] = array('gt',$search);break;\n");
		content.append("\t\t\t\tcase \"egt\" :  $where[$field] = array('egt',$search);break;\n");
		content.append("\t\t\t\tcase \"lt\" :  $where[$field] = array('lt',$search);break;\n");
		content.append("\t\t\t\tcase \"elt\" :  $where[$field] = array('elt',$search);break;\n");
		content.append("\t\t\t\tcase \"eq\" : $where[$field] = array('eq',$search);break;\n");
		content.append("\t\t\t\tcase \"neq\" : $where[$field] = array('neq',$search);break;\n");
		content.append("\t\t\t\tcase \"between\" : $where[$field] = array('between',array($search-1,$search+86400));break;\n");
		content.append("\t\t\t\tcase \"nbetween\" : $where[$field] = array('not between',array($search,$search+86399));break;\n");
		content.append("\t\t\t\tcase \"tgt\" :  $where[$field] = array('gt',$search+86400);break;\n");
		content.append("\t\t\t\tdefault : $where[$field] = array('eq',$search);\n");
		content.append("\t\t\t}\n");
		content.append("\t\t\t\t$params = array('field='.$field, 'condition='.$condition, 'search='.trim($_REQUEST[\"search\"]),'p='.trim($_REQUEST[\"p\"]));\n");
		content.append("\t\t}//if request\n");
		content.append("\t\t$p = isset($_GET['p'])?$_GET['p']:1;\n");
		content.append("\t\tif(count($where)>0) {\n");
		content.append("\t\t\t\t$count = $m_" + tbNamePHP
				+ "->where($where)->count();\n");
		content.append("\t\t\t\t$list = $m_"
				+ tbNamePHP
				+ "->order('create_time desc')->where($where)->Page($p.',50')->select();\n");
		content.append("\t\t} else {\n");
		content.append("\t\t\t\t$count = $m_" + tbNamePHP
				+ "->count();// 查询满足要求的总记录数\n");
		content.append("\t\t\t\t$list = $m_" + tbNamePHP
				+ "->where($where)->order('" + fields[0]
				+ " desc')->Page($p.',50')->select();\n");
		content.append("\t\t}\n");

		content.append("\t\t$Page = new Page($count,50);// 实例化分页类 传入总记录数和每页显示的记录数\n");
		content.append("\t\t$Page->parameter = implode('&', $params);\n");

		content.append("\t\t$this->assign('" + tbNamePHP
				+ "_list',$list);// 赋值数据集\n");
		content.append("\t\t$this->assign('page',$Page->show());// 赋值分页输出\n");
		content.append("\t\t$this->alert=parseAlert();\n");
		content.append("\t\t$this->display(); // 输出模板\n");
		content.append("\t\t\n");
		content.append("\t}//index\n");
		content.append("\tpublic function add(){\n");
		content.append("\t\tif($_POST['submit']){\n");

		content.append("\t\t\t/*$" + fields[1] + " = trim($_POST['" + fields[1]
				+ "']);\n");
		content.append("\t\t\tif ($" + fields[1] + " == '' || $" + fields[1]
				+ " == null) {\n");
		content.append("\t\t\t\talert('error','" + fields[1]
				+ "不能为空!',$_SERVER['HTTP_REFERER']);\n");// 略去id update@14.3.17
		content.append("\t\t\t}*/\n");
		content.append("\t\t\t$m_" + tbNamePHP + " = D('" + TbNamePHP + "');\n");
		/*
		 * content.append("\t\t\tif($m_"+tbNamePHP+"->create()){\n");
		 * content.append("\t\t\t\t$m_"+tbNamePHP+"->create_time = time();\n");
		 * content.append("\t\t\t\t$m_"+tbNamePHP+"->update_time = time();\n");
		 * content.append("\t\t\t\t$m_"+tbNamePHP+"->is_deleted = 0;\n");
		 * content.append("\t\t\t\t$m_"+tbNamePHP+"->delete_time = 0;\n");
		 * content.append("\t\t\t\t$m_"+tbNamePHP+"->add();\n");
		 * content.append("\t\t\t\tif($_POST['submit'] == '保存') {\n");
		 * content.append
		 * ("\t\t\t\t\talert('success', 'congratulations, add success!', U('"
		 * +TbNamePHP+"/index'));\n");
		 * content.append("\t\t\t\t} else {//保存并新建\n"); content.append(
		 * "\t\t\t\t\talert('success', 'congratulations,add success!', $_SERVER['HTTP_REFERER']);\n"
		 * ); content.append("\t\t\t\t}\n"); content.append("\t\t\t}else{\n");
		 * content.append("\t\t\t\texit($m_"+tbNamePHP+"->getError());\n");
		 * content.append("\t\t\t}\n");
		 */
		content.append("\t\t\t$data = array();\n");
		for (int i = 1; i < fields.length; i++) {
			if (fields[i].equals("create_time")) {
				content.append("\t\t\t$data['" + fields[i] + "']=time();\n");
			} else if (fields[i].equals("update_time")) {
				content.append("\t\t\t$data['" + fields[i] + "']=time();\n");
			} else if (fields[i].endsWith("is_deleted")) {
				content.append("\t\t\t$data['" + fields[i] + "']=0;\n");
			} else if (fields[i].equals("delete_time")) {
				content.append("\t\t\t$data['" + fields[i] + "']=0;\n");
			} else {
				content.append("\t\t\t$data['" + fields[i] + "']=$_POST['"
						+ fields[i] + "'];\n");
			}
		}
		content.append("\t\t\t$result = $m_" + tbNamePHP + "->add($data);\n");
		content.append("\t\t\t\tif($result != '' && $result!=null) {\n");
		content.append("\t\t\t\t\talert('success', 'congratulations, add success!', U('"
				+ TbNamePHP + "/index'));\n");
		content.append("\t\t\t\t} else {\n");
		content.append("\t\t\t\t\talert('error', 'sorry,add fail!', $_SERVER['HTTP_REFERER']);\n");
		content.append("\t\t\t\t}\n");

		content.append("\t\t\t\n");
		content.append("\t\t\t\n");
		content.append("\t\t}else{//if post\n");
		content.append("\t\t\t\t$this->alert = parseAlert();\n");
		content.append("\t\t\t\t$this->display();\n");
		content.append("\t\t}\n");
		content.append("\t}//add\n");
		content.append("\tpublic function addNewTab(){\n");
		content.append("\t\tif($_POST['submit']){\n");
		content.append("\t\t\t\n");
		content.append("\t\t\t/*$" + fields[1] + " = trim($_POST['" + fields[1]
				+ "']);\n");
		content.append("\t\t\tif ($" + fields[1] + " == '' || $" + fields[1]
				+ " == null) {\n");
		content.append("\t\t\t\talert('error','" + fields[1]
				+ "不能为空!',$_SERVER['HTTP_REFERER']);\n");// 略去id update@14.3.17
		content.append("\t\t\t}*/\n");
		content.append("\t\t\t$m_" + tbNamePHP + " = D('" + TbNamePHP + "');\n");
		/*
		 * content.append("\t\t\tif($m_"+tbNamePHP+"->create()){\n");
		 * content.append("\t\t\t\t$m_"+tbNamePHP+"->create_time = time();\n");
		 * content.append("\t\t\t\t$m_"+tbNamePHP+"->update_time = time();\n");
		 * content.append("\t\t\t\t$m_"+tbNamePHP+"->is_deleted = 0;\n");
		 * content.append("\t\t\t\t$m_"+tbNamePHP+"->delete_time = 0;\n");
		 * content.append("\t\t\t\t$m_"+tbNamePHP+"->add();\n");
		 * content.append("\t\t\t\tif($_POST['submit'] == '保存') {\n");
		 * content.append
		 * ("\t\t\t\t\talert('success', 'congratulations, add success!', U('"
		 * +TbNamePHP+"/index'));\n");
		 * content.append("\t\t\t\t} else {//保存并新建\n"); content.append(
		 * "\t\t\t\t\talert('success', 'congratulations,add success!', $_SERVER['HTTP_REFERER']);\n"
		 * ); content.append("\t\t\t\t}\n"); content.append("\t\t\t}else{\n");
		 * content.append("\t\t\t\texit($m_"+tbNamePHP+"->getError());\n");
		 * content.append("\t\t\t}\n");
		 */
		content.append("\t\t\t$data = array();\n");
		for (int i = 1; i < fields.length; i++) {
			if (fields[i].equals("create_time")) {
				content.append("\t\t\t$data['" + fields[i] + "']=time();\n");
			} else if (fields[i].equals("update_time")) {
				content.append("\t\t\t$data['" + fields[i] + "']=time();\n");
			} else if (fields[i].endsWith("is_deleted")) {
				content.append("\t\t\t$data['" + fields[i] + "']=0;\n");
			} else if (fields[i].equals("delete_time")) {
				content.append("\t\t\t$data['" + fields[i] + "']=0;\n");
			} else {
				content.append("\t\t\t$data['" + fields[i] + "']=$_POST['"
						+ fields[i] + "'];\n");
			}
		}
		content.append("\t\t\t$result = $m_" + tbNamePHP + "->add($data);\n");
		content.append("\t\t\t\tif($result != '' && $result!=null) {\n");
		content.append("\t\t\t\t\talert('success', 'congratulations, add success!', U('"
				+ TbNamePHP + "/index'));\n");
		content.append("\t\t\t\t} else {\n");
		content.append("\t\t\t\t\talert('error', 'sorry,add fail!', $_SERVER['HTTP_REFERER']);\n");
		content.append("\t\t\t\t}\n");

		content.append("\t\t\t\n");

		content.append("\t\t}else{//if post\n");
		content.append("\t\t\t\t$this->alert = parseAlert();\n");
		content.append("\t\t\t\t$this->display();\n");
		content.append("\t\t}\n");

		content.append("\t}//addNewTab\n");
		content.append("\tpublic function view(){\n");
		content.append("\t\tif($_GET['" + fields[0] + "']){\n");
		content.append("\t\t\t\t$m_" + tbNamePHP + " = M('" + TbNamePHP
				+ "');\n");
		content.append("\t\t\t\t$m_" + tbNamePHP + " = $m_" + tbNamePHP
				+ "->where('" + fields[0] + " = %d ', $_GET['" + fields[0]
				+ "'])->find();\n");
		content.append("\t\t\t\t$this->vo = $m_" + tbNamePHP + ";\n");
		content.append("\t\t\t\t$this->alert = parseAlert();\n");
		content.append("\t\t\t\t$this->display();\n");
		content.append("\t\t}else{\n");
		content.append("\t\t\t\t$this->error(\"parameter error！\");\n");
		content.append("\t\t}\n");
		content.append("\t}//view\n");
		content.append("\tpublic function viewNewTab(){\n");
		content.append("\t\tif($_GET['" + fields[0] + "']){\n");
		content.append("\t\t\t\t$m_" + tbNamePHP + " = M('" + TbNamePHP
				+ "');\n");
		content.append("\t\t\t\t$m_" + tbNamePHP + " = $m_" + tbNamePHP
				+ "->where('" + fields[0] + " = %d ', $_GET['" + fields[0]
				+ "'])->find();\n");
		content.append("\t\t\t\t$this->vo = $m_" + tbNamePHP + ";\n");
		content.append("\t\t\t\t$this->alert = parseAlert();\n");
		content.append("\t\t\t\t$this->display();\n");
		content.append("\t\t}else{\n");
		content.append("\t\t\t\t$this->error(\"parameter error！\");\n");
		content.append("\t\t}\n");
		content.append("\t}//view\n");
		// ////////////////delete
		content.append("\tpublic function delete() {//\n");
		content.append("\t\t$m_" + tbNamePHP + " = M('" + TbNamePHP + "');\n");
		content.append("\t\tif ($this->isPost()) {\n");
		content.append("\t\t\t$" + fields[0] + "s =$_POST['" + fields[0]
				+ "s'];\n");
		content.append("\t\t\tif ('' == $" + fields[0] + "s) {\n");
		content.append("\t\t\t\talert('error', '您没有选择任何内容！', U('" + TbNamePHP
				+ "/index'));\n");
		content.append("\t\t\t} else {\n");
		content.append("\t\t\t\t$data = array('is_deleted'=>1, 'update_time'=>time());\n");
		content.append("\t\t\t\tif($_POST['" + fields[0] + "s']!=''){//\n");
		content.append("\t\t\t\t\tfor ($i = 0; $i < count($" + fields[0]
				+ "s); $i++) {\n");
		content.append("\t\t\t\t\t\t$m_" + tbNamePHP + "->where('" + fields[0]
				+ " in (%s)', $" + fields[0] + "s[$i])->setField($data);\n");
		content.append("\t\t\t\t\t}\n");
		content.append("\t\t\t\t\talert('success', 'delete success!',U('"
				+ TbNamePHP + "/index'));\n");
		content.append("\t\t\t\t} else {\n");
		content.append("\t\t\t\t\talert('error', '删除失败1，联系管理员！', U('"
				+ TbNamePHP + "/index'));\n");
		content.append("\t\t\t\t}\n");
		content.append("\t\t\t}\n");
		content.append("\t\t} elseif($_GET['" + fields[0] + "']) {\n");
		content.append("\t\t\t$customer = $m_" + tbNamePHP + "->where('"
				+ fields[0] + " = %d', $_GET['" + fields[0] + "'])->find();\n");
		content.append("\t\t\tif (is_array($customer)) {\n");
		content.append("\t\t\t\t\t$data = array('is_deleted'=>1, 'update_time'=>time());\n");
		content.append("\t\t\t\t\tif($m_" + tbNamePHP + "->where('" + fields[0]
				+ " = %d', $_GET['" + fields[0] + "'])->setField($data)){\n");

		content.append("\t\t\t\t\t\talert('success', '删除成功!', $_SERVER['HTTP_REFERER']);\n");
		content.append("\t\t\t\t\t}else{\n");
		content.append("\t\t\t\t\t\talert('error', '删除失败2，请联系管理员！', $_SERVER['HTTP_REFERER']);\n");
		content.append("\t\t\t\t\t}\n");
		content.append("\t\t\t} else {\n");
		content.append("\t\t\t\talert('error', '记录不存在！', U('" + TbNamePHP
				+ "/index'));\n");
		content.append("\t\t\t}\n");
		content.append("\t\t} else {\n");
		content.append("\t\t\talert('error', '请选择要删除的线索!',$_SERVER['HTTP_REFERER']);\n");
		content.append("\t\t}\n");
		content.append("\t}//delete\n");
		// //////////////////end delete
		// ///////////////recover
		content.append("\tpublic function recover() {//\n");
		content.append("\t\t$m_" + tbNamePHP + " = M('" + TbNamePHP + "');\n");
		content.append("\t\tif ($this->isPost()) {\n");
		content.append("\t\t\t$" + fields[0] + "s =$_POST['" + fields[0]
				+ "s'];\n");
		content.append("\t\t\tif ('' == $" + fields[0] + "s) {\n");
		content.append("\t\t\t\talert('error', '您没有选择任何内容！', U('" + TbNamePHP
				+ "/index'));\n");
		content.append("\t\t\t} else {\n");
		content.append("\t\t\t\t$data = array('is_deleted'=>0, 'update_time'=>time());\n");
		content.append("\t\t\t\tif($_POST['" + fields[0] + "s']!=''){//\n");
		content.append("\t\t\t\t\tfor ($i = 0; $i < count($" + fields[0]
				+ "s); $i++) {\n");
		content.append("\t\t\t\t\t\t$m_" + tbNamePHP + "->where('" + fields[0]
				+ " in (%s)', $" + fields[0] + "s[$i])->setField($data);\n");
		content.append("\t\t\t\t\t}\n");
		content.append("\t\t\t\t\talert('success', '恢复成功!',U('" + TbNamePHP
				+ "/index'));\n");
		content.append("\t\t\t\t} else {\n");
		content.append("\t\t\t\t\talert('error', '恢复失败1，联系管理员！', U('"
				+ TbNamePHP + "/index'));\n");
		content.append("\t\t\t\t}\n");
		content.append("\t\t\t}\n");
		content.append("\t\t} elseif($_GET['" + fields[0] + "']) {\n");
		content.append("\t\t\t$customer = $m_" + tbNamePHP + "->where('"
				+ fields[0] + " = %d', $_GET['" + fields[0] + "'])->find();\n");
		content.append("\t\t\tif (is_array($customer)) {\n");
		content.append("\t\t\t\t\t$data = array('is_deleted'=>0, 'update_time'=>time());\n");
		content.append("\t\t\t\t\tif($m_" + tbNamePHP + "->where('" + fields[0]
				+ " = %d', $_GET['" + fields[0] + "'])->setField($data)){\n");

		content.append("\t\t\t\t\t\talert('success', '恢复成功!', $_SERVER['HTTP_REFERER']);\n");
		content.append("\t\t\t\t\t}else{\n");
		content.append("\t\t\t\t\t\talert('error', '恢复失败2，请联系管理员！', $_SERVER['HTTP_REFERER']);\n");
		content.append("\t\t\t\t\t}\n");
		content.append("\t\t\t} else {\n");
		content.append("\t\t\t\talert('error', '记录不存在！', U('" + TbNamePHP
				+ "/index'));\n");
		content.append("\t\t\t}\n");
		content.append("\t\t} else {\n");
		content.append("\t\t\talert('error', '请选择要恢复的线索!',$_SERVER['HTTP_REFERER']);\n");
		content.append("\t\t}\n");
		content.append("\t}//delete\n");
		// ///////////edit
		content.append("\tpublic function edit(){\n");
		content.append("\t\tif($_GET['" + fields[0] + "']){\n");
		content.append("\t\t\t$m_" + tbNamePHP + " = M('" + TbNamePHP + "');\n");
		content.append("\t\t\t$this->vo = $m_" + tbNamePHP + "->where('"
				+ fields[0] + " = ' . $_GET['" + fields[0] + "'])->find();\n");
		content.append("\t\t\t$this->display();\n");
		content.append("\t\t}elseif($_POST['submit']){\n");
		//
		content.append("\t\t\t/*$" + fields[1] + " = trim($_POST['" + fields[1]
				+ "']);\n");
		content.append("\t\t\tif ($" + fields[1] + " == '' || $" + fields[1]
				+ " == null) {\n");
		content.append("\t\t\t\talert('error','" + fields[1]
				+ "不能为空!',$_SERVER['HTTP_REFERER']);\n");// 略去id update@14.3.17
		content.append("\t\t\t}*/\n");
		content.append("\t\t\t$m_" + tbNamePHP + " = D('" + TbNamePHP + "');\n");
		content.append("\t\t\t$data = array();\n");
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].equals("update_time")) {
				content.append("\t\t\t$data['update_time'] = time();\n");
			} else if (fields[i].equals("create_time")
					|| fields[i].equals("is_deleted")
					|| fields[i].equals("delete_time")) {
				continue;
			} else {
				content.append("\t\t\t$data['" + fields[i] + "']=$_POST['"
						+ fields[i] + "'];\n");
			}
		}
		content.append("\t\t\t$result = $m_" + tbNamePHP + "->save($data);\n");
		content.append("\t\t\t\tif($result != '' && $result!=null) {\n");
		content.append("\t\t\t\t\talert('success', 'congratulations, edit success!', U('"
				+ TbNamePHP + "/index'));\n");
		content.append("\t\t\t\t} else {\n");
		content.append("\t\t\t\t\talert('error', 'sorry,edit fail!', $_SERVER['HTTP_REFERER']);\n");
		content.append("\t\t\t\t}\n");

		content.append("\t\t\t\n");
		/*
		 * content.append("\t\t\t$m_"+tbNamePHP+" = M('"+TbNamePHP+"');\n");
		 * content.append("\t\t\t$m_"+tbNamePHP+" -> create();\n");
		 * content.append("\t\t\t$m_"+tbNamePHP+"->update_time = time();\n");
		 * 
		 * content.append("\t\t\tif($m_"+tbNamePHP+" -> save()){\n");
		 * content.append
		 * ("\t\t\t\talert('success','congratulations,edit success！',U('"
		 * +TbNamePHP+"/index'));\n"); content.append("\t\t\t}else{\n");
		 * content.append(
		 * "\t\t\t\talert('error','sorry,edit fail',$_SERVER['HTTP_REFERER']);\n"
		 * ); content.append("\t\t\t}\n");
		 */
		// over
		content.append("\t\t}else{\n");
		content.append("\t\t\t\t$this->error('parameter error!');\n");
		content.append("\t\t}\n");

		content.append("\t}//edit\n");
		content.append("\tpublic function editNewTab(){\n");
		content.append("\t\tif($_GET['" + fields[0] + "']){\n");
		content.append("\t\t\t$m_" + tbNamePHP + " = M('" + TbNamePHP + "');\n");
		content.append("\t\t\t$this->vo = $m_" + tbNamePHP + "->where('"
				+ fields[0] + " = ' . $_GET['" + fields[0] + "'])->find();\n");
		content.append("\t\t\t$this->display();\n");
		content.append("\t\t}elseif($_POST['submit']){\n");

		content.append("\t\t\t/*$" + fields[1] + " = trim($_POST['" + fields[1]
				+ "']);\n");
		content.append("\t\t\tif ($" + fields[1] + " == '' || $" + fields[1]
				+ " == null) {\n");
		content.append("\t\t\t\talert('error','" + fields[1]
				+ "不能为空!',$_SERVER['HTTP_REFERER']);\n");// 略去id update@14.3.17
		content.append("\t\t\t}*/\n");
		content.append("\t\t\t$m_" + tbNamePHP + " = D('" + TbNamePHP + "');\n");
		content.append("\t\t\t$data = array();\n");
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].equals("update_time")) {
				content.append("\t\t\t$data['update_time'] = time();\n");
			} else if (fields[i].equals("create_time")
					|| fields[i].equals("is_deleted")
					|| fields[i].equals("delete_time")) {
				continue;
			} else {
				content.append("\t\t\t$data['" + fields[i] + "']=$_POST['"
						+ fields[i] + "'];\n");
			}
		}
		content.append("\t\t\t$result = $m_" + tbNamePHP + "->save($data);\n");
		content.append("\t\t\t\tif($result != '' && $result!=null) {\n");
		content.append("\t\t\t\t\talert('success', 'congratulations, edit success!', U('"
				+ TbNamePHP + "/index'));\n");
		content.append("\t\t\t\t} else {\n");
		content.append("\t\t\t\t\talert('error', 'sorry,edit fail!', $_SERVER['HTTP_REFERER']);\n");
		content.append("\t\t\t\t}\n");

		content.append("\t\t\t\n");

		content.append("\t\t}else{\n");
		content.append("\t\t\t\t$this->error('parameter error!');\n");
		content.append("\t\t}\n");

		content.append("\t}//\n");
		// add-end
		content.append("}\n");// class
		content.append("?>\n");// php

		try { // 输出到Query.jsp
			String filename = baseDirName + TbNamePHP + "Action.class.php";
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content.toString().getBytes());
			fos.close();
			System.out.println("generate " + filename + " complete!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// Action

	public void printTbAPIActionPHP() {// 输出表的管理主页
		String fields[] = tb.getFields();
		String methods[] = tb.getGetterMethods();
		StringBuffer content = new StringBuffer();

		content.append("<?php\n");
		content.append("class " + TbNamePHP + "APIAction extends Action\n");
		content.append("{\n");
		// add-start
		content.append("\tpublic function _initialize(){\n");
		content.append("\n\n\n");
		content.append("\t}\n");
		// -----------list--
		// list ->query_list
		content.append("\tpublic function query_list(){\n");
		content.append("\t\t\n");
		content.append("\t\t$return_data  = array();\n");
		content.append("\t\t$return_data['message'] = '';\n");
		content.append("\t\t$return_data['status'] = 0;\n");
		content.append("\t\t$return_data['result'] = null;\n");
		
		content.append("\t\t$this->by_deleted=0;\n");
		content.append("\t\t$where = array();\n");
		content.append("\t\t$where['is_deleted'] = array('eq', 0);\n");
		content.append("\t\tif ( isset($_GET['uid'])) {\n");
		content.append("\t\t\t$where['uid'] = array('eq',$_GET['uid'] );\n");
		content.append("\t\t}\n");
		content.append("\t\t$params = array();\n");
		content.append("\t\t$by = isset($_GET['by']) ? trim($_GET['by']) : '';\n");
		content.append("\t\tswitch ($by) {\n");
		content.append("\t\t\tcase 'all' : $where['is_deleted'] = array('neq', -1); break;\n");
		content.append("\t\t\tcase 'today' : $where['create_time'] =  array('gt',strtotime(date('Y-m-d', time()))); break;\n");
		content.append("\t\t\tcase 'week' : $where['create_time'] =  array('gt',(strtotime(date('Y-m-d')) - (date('N', time()) - 1) * 86400)); break;\n");
		content.append("\t\t\tcase 'month' : $where['create_time'] = array('gt',strtotime(date('Y-m-01', time()))); break;\n");
		content.append("\t\t\tcase 'add' : $order = 'create_time desc'; break;\n");
		content.append("\t\t\tcase 'update' : $order = 'update_time desc'; break;\n");
		content.append("\t\t\tcase 'sub' : $where['owner_role_id'] = array('in',implode(',', $below_ids)); break;\n");
		content.append("\t\t\tcase 'deleted' : $where['is_deleted'] = 1;$this->by_deleted=1;break;\n");
		content.append("\t\t\tcase 'me' : $where['owner_role_id'] = session('role_id'); break;\n");
		content.append("\t\t\tdefault :\n");
		content.append("\t\t\tbreak;\n");
		content.append("\t\t}\n");
		// ////////////
		content.append("\t\t$m_" + tbNamePHP + " = D('" + TbNamePHP
				+ "'); // \n");
		content.append("\t\timport(\"@.ORG.Page\");//定制ORG-Page-bootstrap-pagination\n");

		content.append("\t\tif ($_REQUEST[\"field\"]) {\n");
		content.append("\t\t\t$field = trim($_REQUEST['field']) == 'all' ? 'title|content' : $_REQUEST['field'];\n");
		content.append("\t\t\t$search = empty($_REQUEST['search']) ? '' : trim($_REQUEST['search']);\n");
		content.append("\t\t\t$condition = empty($_REQUEST['condition']) ? 'is' : trim($_REQUEST['condition']);\n");
		content.append("\t\t\tif('create_time' == $field ||'update_time' == $field) $search = is_numeric($search)?$search:strtotime($search);\n");
		content.append("\t\t\tswitch ($condition) {\n");
		content.append("\t\t\t\tcase \"is\" : $where[$field] = array('eq',$search);break;\n");
		content.append("\t\t\t\tcase \"isnot\" :  $where[$field] = array('neq',$search);break;\n");
		content.append("\t\t\t\tcase \"contains\" :  $where[$field] = array('like','%'.$search.'%');break;\n");
		content.append("\t\t\t\tcase \"not_contain\" :  $where[$field] = array('notlike','%'.$search.'%');break;\n");
		content.append("\t\t\t\tcase \"start_with\" :  $where[$field] = array('like',$search.'%');break;\n");
		content.append("\t\t\t\tcase \"end_with\" :  $where[$field] = array('like','%'.$search);break;\n");
		content.append("\t\t\t\tcase \"is_empty\" :  $where[$field] = array('eq','');break;\n");
		content.append("\t\t\t\tcase \"is_not_empty\" :  $where[$field] = array('neq','');break;\n");
		content.append("\t\t\t\tcase \"gt\" :  $where[$field] = array('gt',$search);break;\n");
		content.append("\t\t\t\tcase \"egt\" :  $where[$field] = array('egt',$search);break;\n");
		content.append("\t\t\t\tcase \"lt\" :  $where[$field] = array('lt',$search);break;\n");
		content.append("\t\t\t\tcase \"elt\" :  $where[$field] = array('elt',$search);break;\n");
		content.append("\t\t\t\tcase \"eq\" : $where[$field] = array('eq',$search);break;\n");
		content.append("\t\t\t\tcase \"neq\" : $where[$field] = array('neq',$search);break;\n");
		content.append("\t\t\t\tcase \"between\" : $where[$field] = array('between',array($search-1,$search+86400));break;\n");
		content.append("\t\t\t\tcase \"nbetween\" : $where[$field] = array('not between',array($search,$search+86399));break;\n");
		content.append("\t\t\t\tcase \"tgt\" :  $where[$field] = array('gt',$search+86400);break;\n");
		content.append("\t\t\t\tdefault : $where[$field] = array('eq',$search);\n");
		content.append("\t\t\t}\n");
		content.append("\t\t\t\t$params = array('field='.$field, 'condition='.$condition, 'search='.trim($_REQUEST[\"search\"]),'p='.trim($_REQUEST[\"p\"]));\n");
		content.append("\t\t}//if request\n");
		content.append("\t\t$p = isset($_GET['p'])?$_GET['p']:1;\n");
		content.append("\t\tif(count($where)>0) {\n");
		content.append("\t\t\t\t$count = $m_" + tbNamePHP
				+ "->where($where)->count();\n");
		content.append("\t\t\t\t$list = $m_"
				+ tbNamePHP
				+ "->order('create_time desc')->where($where)->Page($p.',50')->select();\n");
		content.append("\t\t} else {\n");
		content.append("\t\t\t\t$count = $m_" + tbNamePHP
				+ "->count();// 查询满足要求的总记录数\n");
		content.append("\t\t\t\t$list = $m_" + tbNamePHP
				+ "->where($where)->order('" + fields[0]
				+ " desc')->Page($p.',50')->select();\n");
		content.append("\t\t}\n");
		content.append("\t\t$Page = new Page($count,50);// 实例化分页类 传入总记录数和每页显示的记录数\n");
		content.append("\t\t$Page->parameter = implode('&', $params);\n");
		content.append("\t\t$this->assign('" + tbNamePHP
				+ "_list',$list);// 赋值数据集\n");
		content.append("\t\t$this->assign('page',$Page->show());// 赋值分页输出\n");
		//content.append("\t\t$this->alert=parseAlert();\n");
		//content.append("\t\t$this->display(); // 输出模板\n");
		content.append("\t\t\n");
		content.append("\t\tif(count($list)>0){\n");
		content.append("\t\t\t$return_data['message'] ='success';\n");
		content.append("\t\t\t$return_data['result'] = $list;\n");
		content.append("\t\t\t$return_data['status'] = 1;\n");
		content.append("\t\t}\n");
		content.append("\t\t$this->ajaxReturn($return_data,'jsonp');\n");
		content.append("\t}//list\n");
		// -----------end list---

		content.append("\tpublic function add(){\n");
		content.append("\t\t$return_data  = array();\n");
		content.append("\t\t$return_data['message'] = '';\n");
		content.append("\t\t$return_data['status'] = 0;\n");
		content.append("\t\t$return_data['result'] = null;\n");
		content.append("\t\tif($_POST['submit']){\n");
		content.append("\t\t\t$m_" + tbNamePHP + " = D('" + TbNamePHP + "');\n");
		content.append("\t\t\t$data = array();\n");
		for (int i = 1; i < fields.length; i++) {
			if (fields[i].equals("create_time")) {
				content.append("\t\t\t$data['" + fields[i] + "']=time();\n");
			} else if (fields[i].equals("update_time")) {
				content.append("\t\t\t$data['" + fields[i] + "']=time();\n");
			} else if (fields[i].endsWith("is_deleted")) {
				content.append("\t\t\t$data['" + fields[i] + "']=0;\n");
			} else if (fields[i].equals("delete_time")) {
				content.append("\t\t\t$data['" + fields[i] + "']=0;\n");
			} else {
				content.append("\t\t\t$data['" + fields[i] + "']=$_POST['"
						+ fields[i] + "'];\n");
			}
		}
		content.append("\t\t\t$result = $m_" + tbNamePHP + "->add($data);\n");
		content.append("\t\t\tif($result != '' && $result!=null) {\n");
		content.append("\t\t\t\t$return_data['message'] = 'add success';\n");
		content.append("\t\t\t\t$return_data['status'] = 1;\n");
		content.append("\t\t\t\t$return_data['result'] = $result;\n");
		content.append("\t\t\t} else {\n");
		content.append("\t\t\t\t$return_data['message'] = 'add fail,server error.';\n");
		content.append("\t\t\t\t}\n");
		content.append("\t\t\t\n");
		content.append("\t\t\t\n");
		content.append("\t\t}//if post\n");
		content.append("\t\t$this->ajaxReturn($return_data,'jsonp');\n");

		content.append("\t}//add\n");

		// ////////////////delete
		content.append("\tpublic function delete() {//\n");
		content.append("\t\t$return_data  = array();\n");
		content.append("\t\t$return_data['message'] = '';\n");
		content.append("\t\t$return_data['status'] = 0;\n");
		content.append("\t\t$return_data['result'] = null;\n");
		content.append("\t\t$m_" + tbNamePHP + " = M('" + TbNamePHP + "');\n");

		content.append("\t\tif($_GET['" + fields[0] + "']) {\n");
		content.append("\t\t\t$customer = $m_" + tbNamePHP + "->where('"
				+ fields[0] + " = %d', $_GET['" + fields[0] + "'])->find();\n");
		content.append("\t\t\tif (is_array($customer)) {\n");
		content.append("\t\t\t\t$data = array('is_deleted'=>1, 'update_time'=>time());\n");
		content.append("\t\t\t\tif($m_" + tbNamePHP + "->where('" + fields[0]
				+ " = %d', $_GET['" + fields[0] + "'])->setField($data)){\n");
		content.append("\t\t\t\t\t$return_data['message'] = 'delete success';\n");
		content.append("\t\t\t\t\t$return_data['status'] = 1;\n");
		content.append("\t\t\t\t\t$return_data['result'] = $_GET['todoid'];\n");
		content.append("\t\t\t\t\t}else{\n");
		content.append("\t\t\t\t\t\t$return_data['message'] = 'delete error';\n");
		content.append("\t\t\t\t\t}\n");
		content.append("\t\t\t} else {\n");
		content.append("\t\t\t\t$return_data['message'] = 'delete error,no id';\n");
		content.append("\t\t\t}\n");
		content.append("\t\t}\n");
		content.append("\t\t$this->ajaxReturn($return_data,'jsonp');\n");
		content.append("\t}//delete\n");
		// //////////////////end delete
		// ///////////////recover
		content.append("\tpublic function recover() {//\n");
		content.append("\t\t$return_data  = array();\n");
		content.append("\t\t$return_data['message'] = '';\n");
		content.append("\t\t$return_data['status'] = 0;\n");
		content.append("\t\t$return_data['result'] = null;\n");
		content.append("\t\t$m_" + tbNamePHP + " = M('" + TbNamePHP + "');\n");

		content.append("\t\tif($_GET['" + fields[0] + "']) {\n");
		content.append("\t\t\t$customer = $m_" + tbNamePHP + "->where('"
				+ fields[0] + " = %d', $_GET['" + fields[0] + "'])->find();\n");
		content.append("\t\t\tif (is_array($customer)) {\n");
		content.append("\t\t\t\t$data = array('is_deleted'=>0, 'update_time'=>time());\n");
		content.append("\t\t\t\tif($m_" + tbNamePHP + "->where('" + fields[0]
				+ " = %d', $_GET['" + fields[0] + "'])->setField($data)){\n");
		content.append("\t\t\t\t\t$return_data['message'] = 'delete success';\n");
		content.append("\t\t\t\t\t$return_data['status'] = 1;\n");
		content.append("\t\t\t\t\t$return_data['result'] = $_GET['todoid'];\n");
		content.append("\t\t\t\t\t}else{\n");
		content.append("\t\t\t\t\t\t$return_data['message'] = 'delete error';\n");
		content.append("\t\t\t\t\t}\n");
		content.append("\t\t\t} else {\n");
		content.append("\t\t\t\t$return_data['message'] = 'delete error,no id';\n");
		content.append("\t\t\t}\n");
		content.append("\t\t}\n");
		content.append("\t\t$this->ajaxReturn($return_data,'jsonp');\n");
		content.append("\t}//recover\n");
		// ///////////edit
		content.append("\tpublic function edit(){\n");
		content.append("\t\t$return_data  = array();\n");
		content.append("\t\t$return_data['message'] = '';\n");
		content.append("\t\t$return_data['status'] = 0;\n");
		content.append("\t\t$return_data['result'] = null;\n");
		content.append("\t\tif($_POST['submit']){\n");
		content.append("\t\t\t$m_" + tbNamePHP + " = D('" + TbNamePHP + "');\n");
		content.append("\t\t\t$data = array();\n");
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].equals("update_time")) {
				content.append("\t\t\t$data['update_time'] = time();\n");
			} else if (fields[i].equals("create_time")
					|| fields[i].equals("is_deleted")
					|| fields[i].equals("delete_time")) {
				continue;
			} else {
				content.append("\t\t\t$data['" + fields[i] + "']=$_POST['"
						+ fields[i] + "'];\n");
			}
		}
		content.append("\t\t\t$result = $m_" + tbNamePHP + "->save($data);\n");
		content.append("\t\t\t\tif($result != '' && $result!=null) {\n");
		content.append("\t\t\t\t\t$return_data['message'] = 'edit success';\n");
		content.append("\t\t\t\t\t$return_data['status'] = 1;\n");
		content.append("\t\t\t\t} else {\n");
		content.append("\t\t\t\t\t$return_data['message'] = 'edit fail';\n");
		content.append("\t\t\t\t}\n");
		content.append("\t\t$this->ajaxReturn($return_data,'jsonp');\n");
		content.append("\t\t\t\n");
		content.append("\t\t}\n");
		content.append("\t}//edit\n");
		// add-end
		content.append("}\n");// class
		content.append("?>\n");// php

		try { // 输出到Query.jsp
			String filename = baseDirName + TbNamePHP + "APIAction.class.php";
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content.toString().getBytes());
			fos.close();
			System.out.println("generate " + filename + " complete!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// APIAction

	public void printTbActionNoPagePHP() {// 输出表的管理主页
		String fields[] = tb.getFields();
		String methods[] = tb.getGetterMethods();
		StringBuffer content = new StringBuffer();

		content.append("<?php\n");
		content.append("class " + TbNamePHP + "Action extends Action\n");
		content.append("{\n");
		content.append("// 查询数据\n");
		content.append("\tpublic function index(){\n");
		content.append("\t\t//普通方式实现分页\n");
		content.append("\t\t$Form=M(\'" + TbNamePHP + "\');\n");
		content.append("\t\t$list=$Form->order(\'id desc\')->findAll();\n");
		content.append("\t\t$this->assign ( \"list\", $list );\n");
		content.append("\t\t$this->display();\n");
		content.append("\t}\n");
		content.append("\t// 写入数据\n");
		content.append("\tpublic function insert() {\n");
		content.append("\t\t$Form	=	D(\"" + TbNamePHP + "\");\n");
		content.append("\t\tif($vo = $Form->create()) {\n");
		content.append("\t\t\t$list=$Form->add();\n");
		content.append("\t\t\tif($list!==false){\n");
		content.append("\t\t\t\t//$this->success(\'data save success！\');\n");
		content.append("\t\t\t\t$this->ajaxReturn($_POST['id'],'add success!',1);\n");
		content.append("\t\t\t}else{\n");
		content.append("\t\t\t\t$this->error(\'sorry！save failed,please contact the web master!\');\n");
		content.append("\t\t\t}\n");
		content.append("\t\t}else{\n");
		content.append("\t\t\t$this->error($Form->getError());\n");
		content.append("\t\t}\n");
		content.append("\t}\n");

		content.append("\t// 更新数据\n");
		content.append("\tpublic function update() {\n");
		content.append("\t\t$Form	=	D(\"" + TbNamePHP + "\");\n");
		content.append("\t\tif($vo = $Form->create()) {\n");
		content.append("\t\t\t$list=$Form->save();\n");
		content.append("\t\t\tif($list!==false){\n");
		content.append("\t\t\t\t//$this->success(\'data update success！\');\n");
		content.append("\t\t\t\t\t$this->ajaxReturn($_POST[\'id\'],\'update success!\',1);\n");
		content.append("\t\t\t}else{\n");
		content.append("\t\t\t\t$this->error(\"sorry！update failed,please contact the web master!!\");\n");
		content.append("\t\t\t}\n");
		content.append("\t\t}else{\n");
		content.append("\t\t\t$this->error($Form->getError());\n");
		content.append("\t\t}\n");
		content.append("\t}\n");
		content.append("\t// 删除数据\n");
		content.append("\tpublic function delete() {\n");
		content.append("\t\tif(!empty($_POST[\'id\'])) {\n");
		content.append("\t\t\t$Form	=	M(\"" + TbNamePHP + "\");\n");
		content.append("\t\t\t$result	=	$Form->delete($_POST[\'id\']);\n");
		content.append("\t\t\tif(false !== $result) {\n");
		content.append("\t\t\t\t$this->ajaxReturn($_POST[\'id\'],'delete success！',1);\n");
		content.append("\t\t\t}else{\n");
		content.append("\t\t\t\t$this->error('sorry！delete failed,please contact the web master!！');\n");
		content.append("\t\t\t}\n");
		content.append("\t\t}else{\n");
		content.append("\t\t\t$this->error('no id to delete,sorry！delete failed,please contact the web master!！');\n");
		content.append("\t\t}\n");
		content.append("\t}\n");

		content.append("\t// 编辑数据\n");
		content.append("\tpublic function edit() {\n");
		content.append("\t\tif(!empty($_GET['id'])) {\n");
		content.append("\t\t\t$Form	=	M(\"" + TbNamePHP + "\");\n");
		content.append("\t\t\t$vo	=	$Form->getById($_GET[\'id\']);\n");
		content.append("\t\t\tif($vo) {\n");
		content.append("\t\t\t\t$this->assign(\'vo\',$vo);\n");
		content.append("\t\t\t\t$this->display();\n");
		content.append("\t\t\t}else{\n");
		content.append("\t\t\t\texit(\'sorry！edit failed,please contact the web master!！\');\n");
		content.append("\t\t\t}\n");
		content.append("\t\t}else{\n");
		content.append("\t\t\texit(\'no id,sorry！save failed,please contact the web master!！\');\n");
		content.append("\t\t}\n");
		content.append("\t}\n");
		content.append("}\n");
		content.append("?>\n");

		try { // 输出到Query.jsp
			String filename = baseDirName + TbNamePHP + "Action.class.php";
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content.toString().getBytes());
			fos.close();
			System.out.println("generate " + filename + " complete!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printTpl() {
		String field[] = tb.getFields();
		int tbs = 0;
		StringBuffer content = new StringBuffer();
		content.append("<form class=\"form-horizontal\" action=\"{:U('"
				+ TbNamePHP + "/add')}\" method=\"post\" >\n");
		// for(int i= 0;i<field.length;i++){
		for (int i = 1; i < field.length; i++) {// 略去id update@14.3.17
			if (field[i].equals("create_time")
					|| field[i].equals("update_time")
					|| field[i].equals("is_deleted")
					|| field[i].equals("delete_time")
					|| field[i].contains("time")) {
				continue;
			}
			if (field[i].equals("status")) {
				content.append("\t<div class=\"control-group\">\n");
				content.append("\t\t<label class=\"control-label\" for=\"name\">是否有效</label>\n");
				content.append("\t\t<div class=\"controls\"><input name=\"status\" type=\"radio\" checked=\"true\" value=\"1\"/>有效<input name=\"status\" type=\"radio\" value=\"0\"/>无效</div>\n");
				content.append("\t</div>\n");
			} else {// description 略去，因为未验证模式对话框是否可以执行特性tarea -keditor的执行情况
				content.append("\t<div class=\"control-group\">\n");
				content.append("\t\t<label class=\"control-label\" for=\"name\">"
						+ field[i] + "</label>\n");
				content.append("\t\t<div class=\"controls\"><input type=\"text\" id=\""
						+ field[i] + "\" name=\"" + field[i] + "\"/></div>\n");
				content.append("\t</div>\n");
			}
		}

		try { // 输出到文件
			String filename = baseDirName + TbNamePHP + "/Tpl.html";
			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");// 防止中文乱码
																			// @
																			// 2014.3.16
			osw.write(content.toString());
			osw.close();
			System.out.println("generate " + filename + " complete!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printExcelExport(FactoryTb tb) {
		String field[] = tb.getFields();
		int tbs = 0;
		StringBuffer content = new StringBuffer();
		// for(int i= 0;i<field.length;i++){

		// $objActSheet->setCellValue('A1', '标题');
		for (int i = 0; i < field.length; i++) {// 略去id update@14.3.17

			content.append("\t$objActSheet->setCellValue('" + (char) (i + 65)
					+ "1', '" + field[i] + "');\n");

		}

		// $objActSheet->setCellValue('A'.$i , $v['title']);
		for (int i = 0; i < field.length; i++) {// 略去id update@14.3.17

			content.append("\t$objActSheet->setCellValue('" + (char) (i + 65)
					+ "'.$i , $v['" + field[i] + "']);\n");

		}
		System.out.println(content);
		/*
		 * try { // 输出到文件 String filename = baseDirName + TbNamePHP+
		 * "/Tpl.html"; File file = new File(filename); FileOutputStream fos =
		 * new FileOutputStream(file); OutputStreamWriter osw = new
		 * OutputStreamWriter(fos, "utf-8");//防止中文乱码 @ 2014.3.16
		 * osw.write(content.toString()); osw.close();
		 * System.out.println("generate " + filename + " complete!"); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
	}

	public void printExcelImport() {
		String field[] = tb.getFields();
		int tbs = 0;
		StringBuffer content = new StringBuffer();

		// for(int i= 0;i<field.length;i++){
		for (int i = 0; i < field.length; i++) {
			// content.append("\t<div class=\"control-group\">\n");
			// content.append("\t\t<label class=\"control-label\" for=\"name\">"+field[i]+"</label>\n");
			// content.append("\t\t<div class=\"controls\"><input type=\"text\" id=\""+field[i]+"\" name=\""+field[i]+"\"/></div>\n");
			// content.append("\t</div>\n");

			content.append("\t$" + field[i]
					+ " = (string)$currentSheet->getCell('" + (char) (i + 65)
					+ "'.$currentRow)->getValue();\n");
			content.append("\tif($" + field[i] + " != '' && $" + field[i]
					+ " != null) {\n");
			content.append("\t\t$data['" + field[i] + "'] = $" + field[i]
					+ ";\n\t}\n");
		}

		System.out.println(content);
		try { // 输出到文件
			/*
			 * String filename = baseDirName + TbNamePHP+ "/excelImport.html";
			 * File file = new File(filename); FileOutputStream fos = new
			 * FileOutputStream(file); OutputStreamWriter osw = new
			 * OutputStreamWriter(fos, "utf-8");//防止中文乱码 @ 2014.3.16
			 * osw.write(content.toString()); osw.close();
			 * System.out.println("generate " + filename + " complete!");
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String tbName = "sp_todolist";
		String prefix = "sp_";
		String t = tbName.substring(tbName.indexOf(prefix) + prefix.length());
		System.out.println("tname - " + t);
		FactoryTool.tryToMakdir(tbName, "sp_");

		FactoryDb db = new FactoryDb("timeplan");
		// db.setDbname("timeplan");
		String[] tbs = db.getTbs();
		FactoryTb tb = new FactoryTb(db, "sp_todolist");
		// tb.setTbname("sp_note");
		PHPFactory2016 php = new PHPFactory2016(tb, "sp_");
		php.printAddPHP();// v
		php.printEditPHP();//
		php.printEditNewTabPHP();// v
		php.printAddPHP();// v
		php.printAddNewTabPHP();
		php.printViewPHP();
		php.printViewNewTabPHP();
		php.printIndexPHP();

		// php.printIndexPHP();
		// php.printTbActionNoPagePHP(tb);
		// php.printTbActionPHP(tb, "note", "Note");
		php.printTbActionPHP();
		php.printTbAPIActionPHP();
	}
}
