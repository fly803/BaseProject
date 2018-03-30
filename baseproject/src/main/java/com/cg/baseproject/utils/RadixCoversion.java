package com.cg.baseproject.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class RadixCoversion {

	public static void main(String[] args) throws IOException {
		String sBinary = "0000111100001111";
		String sHex = "0F0F";
		System.out.println(hexString2Bytes(sHex));
		// System.out.println("进制转换："+stringToHexString(sHex));
	}

    /**将二进制转换成16进制 
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制 
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

	public static String binaryToHexString(String sBinary) {
		StringBuffer sbuf = new StringBuffer();
		int blength = sBinary.length() % 4;
		if (blength != 0) {
			String first = sBinary.substring(0, blength);
			sbuf.append(Integer.toHexString(Integer.parseInt(first, 2)));
			sBinary = sBinary.substring(blength);
		}
		int cnum = sBinary.length() / 4;
		for (int i = 0; i < cnum; i++) {
			sbuf.append(Integer.toHexString(Integer.parseInt(
					sBinary.substring(i * 4, 4 * (i + 1)), 2)));
		}
		return "0x" + sbuf.toString().toUpperCase();
	}

	public static String hexToBinaryString(String sHex) {
		StringBuffer sbuf = new StringBuffer();
		for (int i = 0; i < sHex.length(); i++) {
			String bin = Integer.toBinaryString(Integer.parseInt(
					sHex.substring(i, i + 1), 16));
			if (i > 0) {
				bin = "0000" + bin;
				bin = bin.substring(bin.length() - 4);
			}
			sbuf.append(bin);
		}
		return sbuf.toString();
	}

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return "0x" + str;// 0x表示十六进制
	}

	public static byte[] stringToByte(String s) throws Exception {
		byte[] output = new byte[s.length()];
		for (int i = 0; i < s.length(); i++) {
			output[i] = Byte.valueOf(s.valueOf(i));
		}
		return output;
	}

	public static String getHexString(byte[] b) throws Exception {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static byte[] getByteArray(String hexString) {
		return new BigInteger(hexString, 16).toByteArray();
	}

	public static String binaryToHexString(byte[] bytes) {
		String result = "";
		String hex = "";
		String hexStr = "0123456789ABCDEF";
		for (int i = 0; i < bytes.length; i++) {
			// 字节高4位
			hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
			// 字节低4位
			hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
			result += hex;
		}
		return result;
	}

	public void testPositiveIntToHex() {
		// 如果正数小于15时，只输入一位，而不是按我们想像的两位标准十六进制输出显示的，后面解决这个问题
		System.out.println(Integer.toHexString(2));// 2
		System.out.println(Integer.toHexString(15));// f
		System.out.println(Integer.toHexString(16));// 10
		System.out.println(Integer.valueOf("F", 16));// 16
	}

	/**
	 * 强制转成16进制
	 * 
	 * @param str
	 */
	public void fcHex(String str) {

	}

	/*
	 * Integer.valueOf()实质上调用的是Integer.parseInt()来完成的，所以
	 * Integer.parseInt()与Integer.valueOf()功能是一样的，只是返回值不 一样而已
	 */
	public void testNegativeIntToHex() {
		// 负整数时，前面输入了多余的 FF ，没有去掉前面多余的 FF，按并双字节形式输出
		System.out.println(Integer.toHexString(-2).toUpperCase());// FFFFFFFE

		// 实质上0xFF会像转换成0x000000FF后再进行位运算
		System.out.println(Integer.toHexString(-2 & 0xFF).toUpperCase());// FE
		System.out.println(Integer.toHexString(-2 & 0x000000FF).toUpperCase());// FE

		// 注，FE输出时不会为-2，因为此时不会把FE看成负数，valueOf会把所有数字串看成正的
		System.out.println(Integer.valueOf("FE", 16));// 254
		// 如果要输出-2，只能按以下形式输出
		System.out.println(Integer.valueOf("-2", 16));// -2

		// 所以要把 FE 看成负的话，只能在前面加上负号，但是这里输出还不是-2，
		// 而是先计算Integer.valueOf("FE", 16)，再在结果前加上负
		System.out.println(Integer.valueOf("-FE", 16));// -254

		/*
		 * 所以如果要输入某个负数，我们只能先求出该数的绝对值的原码十六进制，再在前面加上负号， 例如求表示-128，则先对绝对值128求十六进制
		 * 80，再在前面加上负号 -80
		 */
		System.out.println(Integer.valueOf("-80", 16));// -128

		/*
		 * 为什么说valueOf把所有数字串看成正的呢？请看下面三行代码，因为最大正数为2147483647， 如果再
		 * 在7fffffff基础上加上一个一，运行肯定会出错误（这与直接输出0x80000000不一样）， 那么就可以证明
		 */
		System.out.println(Integer.valueOf("7fffffff", 16));// 2147483647
		// 此句运行时会报错，因为最大正数为7fffffff，但如 -80000000 却又可以运行，因为没超出整数范围
		// System.out.println(Integer.valueOf("80000000", 16));//不能运行，已注掉
		System.out.println(Integer.valueOf("-80000000", 16));// -2147483648

		/*
		 * 注，输出时不是负数，而是正，因为0xFE只有8位，而整数是32位，所以以int形式出现时前 面会自动补24个零，第一位是零，所以最后是正数
		 */
		System.out.println(0xFE);// 254
		System.out.println(-0xFE);// -254
		// 但0x80000000已满，无需补，第一位为一，所以最后为负数
		System.out.println(0x80000000);// -2147483648
	}

	public void testNegativeIntToBin() {
		System.out.println(Integer.toBinaryString(-2));// 11111111111111111111111111111110
		// 实质上0xFF会像转换成0x000000FF后再进行位运算
		System.out.println(Integer.toBinaryString(-2 & 0xFF));// 11111110
		System.out.println(Integer.toBinaryString(-2 & 0x000000FF));// 11111110

		// 与上面十六进制是一样的
		System.out.println(Integer
				.valueOf("1111111111111111111111111111111", 2));// 2147483647
		// 下面语句运行会出错，已注掉
		// System.out.println(Integer.valueOf("10000000000000000000000000000000",
		// 2));
		System.out.println(Integer.valueOf("-10000000000000000000000000000000",
				2));// -2147483648
		System.out.println(Integer.valueOf("11111110", 2));// 254
		System.out.println(Integer.valueOf("-11111110", 2));// -254

		/*
		 * 注，Java中没有直接使用二进制表示一个数（目前只支持八与十六进制直接表示法），下面其实是一个 八进制的数与十进制的数
		 */
		System.out.println(010);// 8
		System.out.println(10);// 10
	}

	public void testByteToHex() {

		byte negativeByte = -2;
		byte positiveByte = 2;

		/*
		 * toHexString方法类型为int型，所以转Hex前参数会提升成整型后再进行转换，过程如下：
		 * 10000010(原码)->11111110(补码)->11111111 11111111 11111111 11111110(提升)
		 * ->FFFFFFFE(转Hex进制输出)
		 */
		System.out.println(Integer.toHexString(negativeByte).toUpperCase());// FFFFFFFE

		/*
		 * 第一步把-2转成整型： 10000010(原码)->11111110(补码)->11111111 11111111 11111111
		 * 11111110(转整型) 第二步把 0xFF 前补24个零： 00000000 00000000 00000000 11111111
		 * 第三步：把第一二步结果进行与位运算： 00000000 00000000 00000000 11111110 最后一步：转十六进制结果为
		 * FE
		 */
		System.out.println(Integer.toHexString(negativeByte & 0xFF)
				.toUpperCase());// FE

		// 另一种转换，可以针对负数与正数的byte都可以以完整的单字节输出
		System.out.println(Integer
				.toHexString((negativeByte & 0x000000ff) | 0xffffff00)
				.substring(6).toUpperCase());// FE
		System.out.println(Integer
				.toHexString((positiveByte & 0x000000ff) | 0xffffff00)
				.substring(6).toUpperCase());// 02
	}

	/**
	 * 位运算与算术运行中的类型提升机制是一样的
	 */
	public void testBiteMathematical() {
		System.out.println(0x8000000000000000L);// -9223372036854775808
		System.out.println((int) 0x8000000000000000L);// 0
		System.out.println(0x8000000000000010L);// -9223372036854775792
		System.out.println(0x80000000);// -2147483648
		System.out.println(0x80000010);// -2147483632

		// 0x00000010提升成长整型，最后结果为长整型0x8000000000000010L
		System.out.println(0x00000010 | 0x8000000000000000L);// -9223372036854775792
		// 0x0010提升成整形，最后结果为整型0x80000010
		System.out.println(0x0010 | 0x80000000);// -2147483632
	}

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String stringToHexString(String strPart) {
		String hexString = "";
		for (int i = 0; i < strPart.length(); i++) {
			int ch = (int) strPart.charAt(i);
			String strHex = Integer.toHexString(ch);
			hexString = hexString + strHex;
		}
		return hexString;
	}

	private static String hexString = "0123456789ABCDEF";

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encodeAsciiString2Hex(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String decodeHex2AsciiString(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

	private static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 | _b1);
		return ret;
	}

	// private static byte[] strToToHexByte(string hexString) {
	// hexString = hexString.Replace(" ", "");
	// if ((hexString.Length % 2) != 0)
	// hexString += " ";
	// byte[] returnBytes = new byte[hexString.Length / 2];
	// for (int i = 0; i < returnBytes.Length; i++)
	// returnBytes[i] = Convert.ToByte(hexString.Substring(i * 2, 2)
	// .Trim(), 16);
	// return returnBytes;
	// }

	// //把16进制字符串转成字节流
	// public static byte[] hexString2Bytes(String src) {
	// byte[] ret = new byte[6];
	// byte[] tmp = src.getBytes();
	// for (int i = 0; i < 6; ++i) {
	// ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
	// }
	// return ret;
	// }

	/**
	 * bytes转换成十六进制字符串
	 * 
	 * @param b byte数组
	 * @return String 每个Byte值之间空格分隔
	 */
	public static String bytes2HexString(byte[] b) {
		String stmp = "";
		StringBuilder sb = new StringBuilder("");
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
			sb.append(" ");
		}
		return sb.toString().toUpperCase().trim();
	}

	/**
	 * bytes字符串转换为Byte值
	 * 
	 *            src Byte字符串，每个Byte之间没有分隔符
	 * @return byte[]
	 */
	public static byte[] hexString2Bytes(String src) {
		int m = 0, n = 0;
		int l = src.length() / 2;
		System.out.println(l);
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = Byte.decode("0x" + src.substring(i * 2, m)
					+ src.substring(m, n));
		}
		return ret;
	}

	public static byte[] hex2bytes(String hex) {
		String digital = "0123456789ABCDEF";
		char[] hex2char = hex.toCharArray();
		byte[] bytes = new byte[hex.length() / 2];
		int temp;
		for (int i = 0; i < bytes.length; i++) {
			temp = digital.indexOf(hex2char[2 * i]) * 16;
			temp += digital.indexOf(hex2char[2 * i + 1]);
			bytes[i] = (byte) (temp & 0xff);
		}
		return bytes;
	}

	/*
	 * Convert byte[] to hex
	 * string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
	 * 
	 * @param src byte[] data
	 * 
	 * @return hex string
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().toUpperCase().trim();
	}

	/**
	 * Convert hex string to byte[]
	 * 
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static String bytes2HexStringI(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}
}
