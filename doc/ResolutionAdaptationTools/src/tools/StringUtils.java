package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static void doString(String input){
		
		System.out.println("input -------- " + input);
		Pattern p = Pattern.compile("\\d");
		Matcher matcher = p.matcher(input);
		
		if(matcher.matches()){
			String str1 = matcher.group(0);
			System.out.println("result  ====  " + str1);
		}
		
	}
	
	public static String getHdpi(String dimenName) {
		String xxxHdpi = "xxxhdpi";
		Pattern pXXX = Pattern.compile("values-([^-]*)");// 匹配values-开头，hdpi结尾的文档
		Matcher m = pXXX.matcher(dimenName);// 开始编译  
		if (m.find()) {
			xxxHdpi = m.group(1);
        }
		return xxxHdpi;
	}
	
	/**
	 * 得到dpi的缩放值
	 * @param xxxHdpi
	 * @return
	 */
	public static float getScale(String xxxHdpi) {
		if ("ldpi".equals(xxxHdpi)) {
			return 0.75f;
		} else if ("mdpi".equals(xxxHdpi)) {
			return 1.0f;
		} else if ("hdpi".equals(xxxHdpi)) {
			return 1.5f;
		} else if ("xhdpi".equals(xxxHdpi)) {
			return 2.0f;
		} else if ("xxhdpi".equals(xxxHdpi)) {
			return 3.0f;
		} else if ("xxxhdpi".equals(xxxHdpi)) {
			return 4.0f;
		} else {
			return 3.0f;
		}
	}
	
	/**
	 * 得到有无虚拟键屏幕之间的比例
	 * @param dimenInfo
	 * @return
	 */
	public static float getRadio(DimenInfo dimenInfo) {
		if(dimenInfo.getHeight() == 1280) {
			return 1.0f;
		}else if(dimenInfo.getHeight() == 1184) {
			return 1184/1280f;
		}else if(dimenInfo.getHeight() == 1920) {
			return 1.0f;
		}else if(dimenInfo.getHeight() == 1776) {
			return 1776/1920f;
		}else if(dimenInfo.getHeight() == 1812) {
			return 1812/1920f;
		}else if(dimenInfo.getHeight() == 2560) {
			return 1.0f;
		}else if(dimenInfo.getHeight() == 2392) {
			return 2392/2560f;
		}else if(dimenInfo.getHeight() == 2960) {
			return 2960/2560f;
		}else {
			return 1.0f;
		}
	}
	
    public static float pixelTodip(float scale, float pxValue){
		return (float)(Math.round((pxValue / scale)*100))/100;
	}
	
}
