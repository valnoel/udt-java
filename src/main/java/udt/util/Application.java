package udt.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Application implements Runnable {

	protected static boolean verbose;

	protected static String localIP=null;

	protected static int localPort=-1;

	public void configure(){
		if(verbose){
			Logger.getLogger("udt").setLevel(Level.INFO);
		}
		else{
			Logger.getLogger("udt").setLevel(Level.OFF);
		}
	}


	protected static String[] parseOptions(String[] args){
		List<String>newArgs=new ArrayList<String>();
		for(String arg: args){
			if(arg.startsWith("-")){
				parseArg(arg);
			}
			else
			{
				newArgs.add(arg);
			}
		}
		return newArgs.toArray(new String[newArgs.size()]);
	}


	protected static void parseArg(String arg){
		if("-v".equals(arg) || "--verbose".equals(arg)){
			verbose=true;
			return;
		}
		if(arg.startsWith("--localIP")){
			localIP=arg.split("=")[1];
		}
		if(arg.startsWith("--localPort")){
			localPort=Integer.parseInt(arg.split("=")[1]);
		}
	}

	static int decode(final byte[]data) {
		return decode(data, 0);
	}

	static int decode(final byte[]data, final int start) {
		if(data.length != Integer.BYTES)
			throw new IllegalArgumentException("Data to decode should be 4-bytes long.");
		int result = 0;
		for (int i = 0; i < Integer.BYTES; i++) {
			result <<= Byte.SIZE;
			result |= (data[i] & 0xFF);
		}
		return result;
	}

	static long decode64(final byte[] data) {
		return decode64(data, 0);
	}

	static long decode64(final byte[] data, final int start) {
		if(data.length != Long.BYTES)
			throw new IllegalArgumentException("Data to decode should be 8-bytes long.");
		long result = 0;
		for (int i = 0; i < Long.BYTES; i++) {
			result <<= Byte.SIZE;
			result |= (data[i] & 0xFF);
		}
		return result;
	}

	static byte[] encode(int value) {
		final byte[] result = new byte[Integer.BYTES];
		for(int i = (Integer.BYTES - 1); i >= 0; i--) {
			result[i] = (byte)(value & 0xFF);
			value >>= Byte.SIZE;
		}
		return result;
	}

	static byte[] encode64(long value) {
		final byte[] result = new byte[Long.BYTES];
		for(int i = (Long.BYTES - 1); i >= 0; i--) {
			result[i] = (byte)(value & 0xFF);
			value >>= Byte.SIZE;
		}
		return result;
	}
}
