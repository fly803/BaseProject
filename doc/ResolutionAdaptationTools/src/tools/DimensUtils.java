package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 
 * @ClassName: DimensUtils
 *
 * @Description: Dimens 元素换算的工具类
 *
 * @author Tyrion
 *
 * @date 2015年6月17日 下午12:18:47
 *
 */
public class DimensUtils {

//	private static int value_stand = 720;
	public static void outContent(File desFile, DimenInfo dimenInfo) throws IOException {
		BufferedWriter bw = null;
		FileOutputStream fos;
		fos = new FileOutputStream(desFile);
		bw = new BufferedWriter(new OutputStreamWriter(fos));
		String hear = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		bw.write(hear + "\n");
		String tag = "<resources>";
		bw.write(tag + "\n");
//		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
		float scale = StringUtils.getScale(dimenInfo.getDpi());
		float yRadio = StringUtils.getRadio(dimenInfo);
		System.out.println("scale:"+scale+" yRadio:"+yRadio);
		for (int i = 1; i <= 1080; i++) {
			float xValue = StringUtils.pixelTodip(scale, i);
			String contentLine = "<dimen name=\"x" + i + "\">" + xValue + "dp</dimen>" + "\n";
			bw.write(contentLine);
		}
		for (int j = 1; j<= 1920; j++) {
			float yValue = StringUtils.pixelTodip(scale, j)*yRadio;
			String contentLine = "<dimen name=\"y" + j + "\">" + yValue + "dp</dimen>" + "\n";
			bw.write(contentLine);
		}
		for (int k = 1; k <= 300; k++) {
			float spValue = StringUtils.pixelTodip(scale, k)*yRadio;
			String contentLine = "<dimen name=\"sp" + k + "\">" + spValue + "dp</dimen>" + "\n";
			bw.write(contentLine);
		}
		String end = "</resources>" + "\n";
		bw.write(end);
		bw.close();
		fos.close();
	}
}
