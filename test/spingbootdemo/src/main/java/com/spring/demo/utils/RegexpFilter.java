package com.spring.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpFilter {
	
    public static String stringFilter(String str) {
        
    	/**
	 	* 特殊字符处理 
	 	* 1. 
	 	* 2.\n换行 
	 	* 3.\r回车 
	 	* 4.\t tab
	 	* 5.\f 换页
    	*/
        String regEx = "[ \\n\\r\\t\\f]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        str = m.replaceAll("").trim();
        String[] str_arrays= {"i.e.","e.g.","u.s.","p.m."};
        for (String string : str_arrays) {
        	str=str.replace(string, "");
		}
        return removeHtmlTag(str);

    }
    /**
    * 删除Html标签
    * @param inputString
    * @return
    */
    public static String removeHtmlTag(String inputString) {
    if (inputString == null)
    return null;
    String htmlStr = inputString; // 含html标签的字符串
    String textStr = "";
    java.util.regex.Pattern p_script;
    java.util.regex.Matcher m_script;
    java.util.regex.Pattern p_style;
    java.util.regex.Matcher m_style;
    java.util.regex.Pattern p_html;
    java.util.regex.Matcher m_html;
    java.util.regex.Pattern p_special;
    java.util.regex.Matcher m_special;
    java.util.regex.Pattern u_special;
    java.util.regex.Matcher un_special;
    try {
    //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
    String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
    //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
    String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
    // 定义HTML标签的正则表达式
    String regEx_html = "<[^>]+>";
    // 定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
//    String regEx_special = "\\&[a-zA-Z]{1,10};";
//    String regEx_spec = "[\\\\u][0-9a-zA-Z]{1,5}";//

    p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
    m_script = p_script.matcher(htmlStr);
    htmlStr = m_script.replaceAll(""); // 过滤script标签
    p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
    m_style = p_style.matcher(htmlStr);
    htmlStr = m_style.replaceAll(""); // 过滤style标签
    p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
    m_html = p_html.matcher(htmlStr);
    htmlStr = m_html.replaceAll(""); // 过滤html标签
//    p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
//    m_special = p_special.matcher(htmlStr);
//    htmlStr = m_special.replaceAll(""); // 过滤特殊标签
//    u_special = Pattern.compile(regEx_spec, Pattern.CASE_INSENSITIVE);
//    un_special = u_special.matcher(htmlStr);
//    htmlStr = un_special.replaceAll(""); // 过滤特殊标签
    textStr = htmlStr;
    } catch (Exception e) {
    e.printStackTrace();
    }
    return textStr;// 返回文本字符串
    }
    public static void main(String[] args) {
    	String a="   USE - The pre-harvest treatment liquid is useful for improving post-harvest storage of longan fruits, preferably for preserving longan fruits (claimed).\r\n" + 
    			"   ADVANTAGE - The pre-harvest treatment liquid inhibits the browning of the longan peel and the autolysis of the pulp, reduces the rate of decay and mildew of the longan fruits, the risk of post-harvest storage and transportation, and the post-harvest losses, does not affect the yield and quality of longan, and improves the storage stability of the longan fruits.\r\n" + 
    			"   DETAILED DESCRIPTION - An INDEPENDENT CLAIM is included for a pre-harvest treatment method for improving post-harvest storage of longan fruits, which involves spraying the pre-harvest treatment liquid every half a month from 2 months before the harvest of the longan fruits for 3 times, then pre-cooling and packaging as soon as possible after harvesting.\r\n" + 
    			"TF TECHNOLOGY FOCUS - INORGANIC CHEMISTRY - Preferred Composition: The pre-harvest treatment liquid further comprises 0.01-0.1 %mass surfactant, preferably laundry detergent. The pre-harvest treatment liquid comprises 0.2 wt.% zinc sulfate or zinc chloride, or 0.15 wt.% potassium dihydrogen phosphate, and the remainder being water.\r\n" + 
    			"DC ";
    	if (a.startsWith("  ")) {
    		System.out.println(a);
		}
    	
//		String a=" Die Erfindung betrifft 2-Benzoyl-cyclohexan-1,3-dione der Formel I $F1 mit$A R&uarr;1&uarr;, R&uarr;2&uarr; Wasserstoff, Nitro, Halogen, Cyano, Rhodano, Alkyl, Halogenalkyl, Alkoxyalkyl, Alkenyl, Alkinyl, -OR&uarr;5&uarr;, -OCOR&uarr;6&uarr;, -OSO&darr;2&darr;R&uarr;6&uarr;, -SH, -S(O)&darr;n&darr;R&uarr;7&uarr;, -SO&darr;2&darr;OR&uarr;5&uarr;, -SO&darr;2&darr;NR&uarr;5&uarr;R&uarr;8&uarr;, -NR&uarr;8&uarr;SO&darr;2&darr;R&uarr;6&uarr; oder -NR&uarr;8&uarr;COR&uarr;6&uarr;;$A R&uarr;3&uarr; Wasserstoff, Alkyl, Halogenalkyl, Alkenyl oder Alkinyl;$A R&uarr;4&uarr; Wasserstoff, gegebenenfalls substituiertes Alkyl, Cycloalkyl, Alkenyl, Cycloalkenyl, Alkinyl, -COR&uarr;9&uarr;, -CO&darr;2&darr;R&uarr;9&uarr;, -COSR&uarr;9&uarr; oder -CONR&uarr;8&uarr;R&uarr;9&uarr;;$A X Sauerstoff oder Schwefel;$A Z Sauerstoff oder NR&uarr;8&uarr;;$A m 0 oder 1;$A n 0, 1 oder 2;$A R&uarr;5&uarr; Wasserstoff, Alkyl, Halogenalkyl, Alkoxyalkyl, Alkenyl oder Alkinyl;$A R&uarr;6&uarr; Alkyl oder Halogenalkyl;$A R&uarr;7&uarr; Alkyl, Halogenalkyl, Alkoxyalkyl, Alkenyl oder Alkinyl;$A R&uarr;8&uarr; Wasserstoff oder Alkyl;$A R&uarr;9&uarr; Alkyl, Alkenyl, Alkinyl, Phenyl oder Benzyl;$A R&uarr;10&uarr; Alkyl, Halogenalkyl, Alkenyl oder Alkinyl;$A Q ein gegebenenfalls substituierter in 2-Stellung verknüpfter Cyclohexan-1,3-dionring;$A wobei m 1 bedeutet, wenn R&uarr;3&uarr; für Wasserstoff steht;$A sowie deren landwirtschaftlich brauchbaren Salze; Verfahren und Zwischenprodukte zur Herstellung der Verbindungen der Formel I; Mittel, welche diese enthalten; sowie die Verwendung dieser Derivate oder diese enthaltenden Mittel zur Bekämpfung unerwünschter Pflanzen.The invention concerns 2-benzoyl-cyclohexan-1,3-diones of formula (I), in which R<1>, R<2> stand for hydrogen, nitro, halogen, cyano, rhodano, alkyl, halogen alkyl, alkoxyalkyl, alkenyl, alkinyl, -OR<5>, -OCOR<6>, -OSO2R<6>, -SH, -S(O)nR<7>, -SO2OR<5>, -SO2NR<5>R<8>, -NR<8>SO2R<6> or -NR<8>COR<6>; R<3> stands for hydrogen, alkyl, halogen a";
//		String u=" \\u043a\\u0443\\u043b\\u044c\\u0442\\u0443\\u0440\\u0430\\u043b\\u044c\\";
//		System.out.println(removeHtmlTag(u));
	}
}
