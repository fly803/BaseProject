package com.cg.baseproject.utils.android;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.cg.baseproject.BaseApplication;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by sam on 2017/4/19.
 *
 * 判断网络连接工具类
 */
public class NetworkUtils {

	private NetworkUtils() {
		throw new UnsupportedOperationException("不允许创建NetworkUtils构造器");
	}

	public static final int NETWORK_WIFI = 1; // wifi network
	public static final int NETWORK_4G = 4; // "4G" networks
	public static final int NETWORK_3G = 3; // "3G" networks
	public static final int NETWORK_2G = 2; // "2G" networks
	public static final int NETWORK_UNKNOWN = 5; // unknown network
	public static final int NETWORK_NO = -1; // no network

	private static final int NETWORK_TYPE_GSM = 16;
	private static final int NETWORK_TYPE_TD_SCDMA = 17;
	private static final int NETWORK_TYPE_IWLAN = 18;

    //除了判断是否有网络，还写工具方法判断网络为什么类型 2G、3G、4G、wifi此处忽略
    public static boolean checkNetworkState(Context context) {
        //得到网络信息
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            //当前的网络是连接的
            return true;
        } else {
            return false;
        }
    }
    
	/**
	 * 打开网络设置界面
	 * <p>
	 * 3.0以下打开设置界面
	 * </p>
	 * 
	 * @param context
	 *            上下文
	 */
	public static void openWirelessSettings(Context context) {
		if (android.os.Build.VERSION.SDK_INT > 10) {
			context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
		} else {
			context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
		}
	}

	/**
	 * 获取活动网络信息
	 * <p>
	 * 需添加权限
	 * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}
	 * </p>
	 * 
	 * @param context
	 *            上下文
	 * @return NetworkInfo
	 */
	private static NetworkInfo getActiveNetworkInfo(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo();
	}

	/**
	 * 判断网络是否连接
	 * <p>
	 * 需添加权限
	 * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}
	 * </p>
	 * 
	 * @return {@code true}: 是<br>
	 *         {@code false}: 否
	 */
	public static boolean isConnected() {
		NetworkInfo info = getActiveNetworkInfo(BaseApplication.getContext());
		return info != null && info.isConnected();
	}

	/**
	 * 打开或关闭移动数据
	 * <p>
	 * 需系统应用 需添加权限
	 * {@code <uses-permission android:name="android.permission.MODIFY_PHONE_STATE"/>}
	 * </p>
	 * 
	 * @param context
	 *            上下文
	 * @param enabled
	 *            {@code true}: 打开<br>
	 *            {@code false}: 关闭
	 */
	public static void setDataEnabled(Context context, boolean enabled) {
		try {
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			Method setMobileDataEnabledMethod = tm.getClass().getDeclaredMethod("setDataEnabled", boolean.class);
			if (null != setMobileDataEnabledMethod) {
				setMobileDataEnabledMethod.invoke(tm, enabled);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断网络是否是4G
	 * <p>
	 * 需添加权限
	 * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}
	 * </p>
	 * 
	 * @param context
	 *            上下文
	 * @return {@code true}: 是<br>
	 *         {@code false}: 否
	 */
	public static boolean is4G(Context context) {
		NetworkInfo info = getActiveNetworkInfo(context);
		return info != null && info.isAvailable() && info.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
	}

	/**
	 * 判断wifi是否打开
	 * <p>
	 * 需添加权限
	 * {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}
	 * </p>
	 * 
	 * @param context
	 *            上下文
	 * @return {@code true}: 是<br>
	 *         {@code false}: 否
	 */
	public static boolean getWifiEnabled(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		return wifiManager.isWifiEnabled();
	}

	/**
	 * 打开或关闭wifi
	 * <p>
	 * 需添加权限
	 * {@code <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>}
	 * </p>
	 * 
	 * @param context
	 *            上下文
	 * @param enabled
	 *            {@code true}: 打开<br>
	 *            {@code false}: 关闭
	 */
	public static void setWifiEnabled(Context context, boolean enabled) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		if (enabled) {
			if (!wifiManager.isWifiEnabled()) {
//				wifiManager.setWifiEnabled(true);
			}
		} else {
			if (wifiManager.isWifiEnabled()) {
//				wifiManager.setWifiEnabled(false);
			}
		}
	}

	/**
	 * 判断wifi是否连接状态
	 * <p>
	 * 需添加权限
	 * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}
	 * </p>
	 * 
	 * @param context
	 *            上下文
	 * @return {@code true}: 连接<br>
	 *         {@code false}: 未连接
	 */
	public static boolean isWifiConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm != null && cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
	}

	/**
	 * 获取网络运营商名称
	 * <p>
	 * 中国移动、如中国联通、中国电信
	 * </p>
	 * 
	 * @param context
	 *            上下文
	 * @return 运营商名称
	 */
	public static String getNetworkOperatorName(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm != null ? tm.getNetworkOperatorName() : null;
	}

	/**
	 * 获取当前的网络类型(WIFI,2G,3G,4G)
	 * <p>
	 * 需添加权限
	 * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}
	 * </p>
	 * 
	 * @param context
	 *            上下文
	 * @return 网络类型
	 *         <ul>
	 *         <li>{@link #NETWORK_WIFI   } = 1;</li>
	 *         <li>{@link #NETWORK_4G     } = 4;</li>
	 *         <li>{@link #NETWORK_3G     } = 3;</li>
	 *         <li>{@link #NETWORK_2G     } = 2;</li>
	 *         <li>{@link #NETWORK_UNKNOWN} = 5;</li>
	 *         <li>{@link #NETWORK_NO     } = -1;</li>
	 *         </ul>
	 */
	public static int getNetworkType(Context context) {
		int netType = NETWORK_NO;
		NetworkInfo info = getActiveNetworkInfo(context);
		if (info != null && info.isAvailable()) {

			if (info.getType() == ConnectivityManager.TYPE_WIFI) {
				netType = NETWORK_WIFI;
			} else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
				switch (info.getSubtype()) {

				case NETWORK_TYPE_GSM:
				case TelephonyManager.NETWORK_TYPE_GPRS:
				case TelephonyManager.NETWORK_TYPE_CDMA:
				case TelephonyManager.NETWORK_TYPE_EDGE:
				case TelephonyManager.NETWORK_TYPE_1xRTT:
				case TelephonyManager.NETWORK_TYPE_IDEN:
					netType = NETWORK_2G;
					break;

				case NETWORK_TYPE_TD_SCDMA:
				case TelephonyManager.NETWORK_TYPE_EVDO_A:
				case TelephonyManager.NETWORK_TYPE_UMTS:
				case TelephonyManager.NETWORK_TYPE_EVDO_0:
				case TelephonyManager.NETWORK_TYPE_HSDPA:
				case TelephonyManager.NETWORK_TYPE_HSUPA:
				case TelephonyManager.NETWORK_TYPE_HSPA:
				case TelephonyManager.NETWORK_TYPE_EVDO_B:
				case TelephonyManager.NETWORK_TYPE_EHRPD:
				case TelephonyManager.NETWORK_TYPE_HSPAP:
					netType = NETWORK_3G;
					break;

				case NETWORK_TYPE_IWLAN:
				case TelephonyManager.NETWORK_TYPE_LTE:
					netType = NETWORK_4G;
					break;
				default:

					String subtypeName = info.getSubtypeName();
					if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA")
							|| subtypeName.equalsIgnoreCase("CDMA2000")) {
						netType = NETWORK_3G;
					} else {
						netType = NETWORK_UNKNOWN;
					}
					break;
				}
			} else {
				netType = NETWORK_UNKNOWN;
			}
		}
		return netType;
	}

	/**
	 * 获取当前的网络类型(WIFI,2G,3G,4G)
	 * <p>
	 * 依赖上面的方法
	 * </p>
	 * 
	 * @param context
	 *            上下文
	 * @return 网络类型名称
	 *         <ul>
	 *         <li>NETWORK_WIFI</li>
	 *         <li>NETWORK_4G</li>
	 *         <li>NETWORK_3G</li>
	 *         <li>NETWORK_2G</li>
	 *         <li>NETWORK_UNKNOWN</li>
	 *         <li>NETWORK_NO</li>
	 *         </ul>
	 */
	public static String getNetworkTypeName(Context context) {
		switch (getNetworkType(context)) {
		case NETWORK_WIFI:
			return "NETWORK_WIFI";
		case NETWORK_4G:
			return "NETWORK_4G";
		case NETWORK_3G:
			return "NETWORK_3G";
		case NETWORK_2G:
			return "NETWORK_2G";
		case NETWORK_NO:
			return "NETWORK_NO";
		default:
			return "NETWORK_UNKNOWN";
		}
	}

	/**
	 * 获取IP地址
	 * <p>
	 * 需添加权限
	 * {@code <uses-permission android:name="android.permission.INTERNET"/>}
	 * </p>
	 * 
	 * @param useIPv4
	 *            是否用IPv4
	 * @return IP地址
	 */
	public static String getIPAddress(boolean useIPv4) {
		try {
			for (Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces(); nis.hasMoreElements();) {
				NetworkInterface ni = nis.nextElement();
				// 防止小米手机返回10.0.2.15
				if (!ni.isUp())
					continue;
				for (Enumeration<InetAddress> addresses = ni.getInetAddresses(); addresses.hasMoreElements();) {
					InetAddress inetAddress = addresses.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						String hostAddress = inetAddress.getHostAddress();
						boolean isIPv4 = hostAddress.indexOf(':') < 0;
						if (useIPv4) {
							if (isIPv4)
								return hostAddress;
						} else {
							if (!isIPv4) {
								int index = hostAddress.indexOf('%');
								return index < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, index)
										.toUpperCase();
							}
						}
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 检查当前网络是否可用
     *
     * @param context
     * @return
     */

    public static boolean isNetworkAvailable(Context context) {

        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    /**
     * 使用SSL不信任的证书
     */
    public static void useUntrustedCertificate() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断网络是否连接
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) return false;
        return connectivity.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
        intent.setComponent(componentName);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }
}