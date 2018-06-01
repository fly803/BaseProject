package com.cg.baseproject.manager;

import android.util.Log;

/**
 * @ClassName: LogManager
 * @Description: 日志管理类，系统中所有的日志使用该类统一管理
 * @author sam
 * @date 2016-3-27 下午5:16:32
 */
public class LogManager {

	// 锁，是否关闭Log日志输出
	public static boolean LogOFF = true;

	// 是否关闭VERBOSE输出
	public static boolean LogOFF_VERBOSE = true;
	// 是否关闭debug输出
	public static boolean LogOFF_DEBUG = true;
	// 是否关闭info输出
	public static boolean LogOFF_INFO = true;
	// 是否关闭SystemOUT输出
	public static boolean LogOFF_SYSTEMOUT = true;

	/**** 5中Log日志类型 *******/
	/** 调试日志类型 */
	public static final int DEBUG = 101;
	/** 错误日志类型 */
	public static final int ERROR = 102;
	/** 信息日志类型 */
	public static final int INFO = 103;
	/** 详细信息日志类型 */
	public static final int VERBOSE = 104;
	/** 警告调试日志类型 */
	public static final int WARN = 105;
	/** SystemOUT类型 */
	public static final int SYSTEMOUT = 106;

	/** 显示，打印日志 */
	public static void logShow(String Tag, String Message, int Style) {
		if (!LogOFF) {
			switch (Style) {
			case DEBUG:
				if (!LogOFF_DEBUG) {
					Log.d(Tag, Message);
				}
				break;
			case ERROR:
				Log.e(Tag, Message);
				break;
			case INFO:
				if (!LogOFF_INFO) {
					Log.i(Tag, Message);
				}
				break;
			case VERBOSE:
				if (!LogOFF_VERBOSE) {
					Log.v(Tag, Message);
				}
				break;
			case WARN:
				Log.w(Tag, Message);
				break;
			case SYSTEMOUT:
				if (!LogOFF_SYSTEMOUT) {
					System.out.println(Tag + ":" + Message);
				}
				break;
			}
		}
	}
}
