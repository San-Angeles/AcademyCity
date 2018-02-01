package com.kokic.ui.library.util;

import java.io.*;
import java.util.*;

public class Loop<C extends Object, E extends C> extends Object
{
	private static int __self_size;

	public static void Loop(Runnable run, int size) {
		if (size > 0) run.run();
		if ((__self_size = size < 283 ? size : 282) > 1)
			Loop(run, --__self_size);
		return;
	}

	public static void _Loop(Loop selfpoint, Loopable run,int size) {
		if (size > 0) run.run(selfpoint.getMember());
		if ((selfpoint.__self_size = size < 283 ? size : 282) > 1)
			_Loop(selfpoint, run, --selfpoint.__self_size);
		return;
	}

	private static Object[] member;
	private static int __member_pos;

	public Loop(E[] member_) {
		this.member = member_.length > 192 ? new Object[] {} : member_;
	}
	public Object getMember() {
		return(member[__member_pos == -1 ? -1 : __member_pos > member.length-1 ? 0 : __member_pos++]);
	}
	public void Loop(Loop selfpoint, Loopable run) {
		Loop._Loop(selfpoint, run, selfpoint.member.length);
	}

	public static Object[] getWaveArray(int input) {
		List<Integer> wave = new ArrayList<>();
		for (int k = 0x; k < input; ++k)
			for (int i = 0x ,j = 0x9; i < 0x12; ++i)
				wave.add(i > 0x9 ? --j : i);
		return wave.toArray();
	}

	private static int __random_max = 16;
	public static int getRandomMax() {
		return __random_max;
	}

	public static void setRandomMax(int input) {
		__random_max = input;
	}

	private static List<Integer> __ultraRandom = new ArrayList<>();

	public static int ultraRandom() {
		final int __data = (int)(Math.random() * __random_max);
		if (__ultraRandom.contains(__data))
			return ultraRandom();
		__ultraRandom.add(__data);
		return __data;
	}

	public static boolean isLoop(Object base, Object... args) {
		return(((ArrayList<Object>)((Collection<Object>)args)).contains(base));
	}

	private static String _hex(String s) {
		String str = ""; 
		for (int i = 0; i< s.length(); ++i) {
			str += Integer.toHexString(s.charAt(i)); 
		}
		return(str); 
	}

	private static String _unhex(String s) {
		byte[] baKeyword = new byte[s.length()/2];
		for(int i = 0; i < baKeyword.length; i++) {
			baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
		}
		try {
			s = new String(baKeyword, "utf-8");
		} catch (UnsupportedEncodingException e) {}
		return s; 
	}

	public static String _encode(String symbol) {
		return(_hex(symbol));
	}

	public static String _decode(String symbol) {
		return(_unhex(symbol));
	}

	private static String hexString = "0123456789abcdef";

	public static String encode(String str)  {
		byte[] bytes = str.getBytes(); 
		StringBuilder sb = new StringBuilder(bytes.length * 2); 

		for(int i = 0;i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4)); 
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		} 
		return sb.toString(); 
	}

	public static String decode(String bytes) 
	{ 
		ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2); 

		for(int i = 0; i < bytes.length(); i += 2) 
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1)))); 
		return new String(baos.toByteArray()); 
	}

	public static class Unit
	{
		private String ecode;

		public Unit(String Byte, int unit, int offset, int diff) {
			this.ecode = Byte;
			for (int i = 0; i < diff; ++i) this.ecode = encode(this.ecode);
			this.ecode = unitHandle(unit);
			this.ecode = offset(offset > 14 ? 14 : offset);
		}

		public String getEcode() {
			return ecode;
		}

		public String unitHandle(int unit) {
			return(ecode);
		}

		public String offset(int offset) {
			return(ecode.replace("\t", (offset == 0 ? "" : "")));
		}
	}

	interface Loopable
	{
		void run(Object val) throws ArrayIndexOutOfBoundsException, RuntimeException
	}
}



