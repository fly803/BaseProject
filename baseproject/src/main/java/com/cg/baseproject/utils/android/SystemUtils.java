package com.cg.baseproject.utils.android;

import java.io.IOException;

import android.content.Context;
import android.content.Intent;

public class SystemUtils {
	/** recommend default thread pool size according to system available processors, {@link #getDefaultThreadPoolSize()} **/
    public static final int DEFAULT_THREAD_POOL_SIZE = getDefaultThreadPoolSize();

    private SystemUtils() {
        throw new AssertionError();
    }

    /**
     * get recommend default thread pool size
     * 
     * @return if 2 * availableProcessors + 1 less than 8, return it, else return 8;
     * @see {@link #getDefaultThreadPoolSize(int)} max is 8
     */
    public static int getDefaultThreadPoolSize() {
        return getDefaultThreadPoolSize(8);
    }

    /**
     * get recommend default thread pool size
     * 
     * @param max
     * @return if 2 * availableProcessors + 1 less than max, return it, else return max;
     */
    public static int getDefaultThreadPoolSize(int max) {
        int availableProcessors = 2 * Runtime.getRuntime().availableProcessors() + 1;
        return availableProcessors > max ? max : availableProcessors;
    }
    
	public static void reboot() {
		String cmd = "su -c reboot";
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// new
			// AlertDialog.Builder(this).setTitle("Error").setMessage(e.getMessage()).setPositiveButton("OK",
			// null).show();
		}
	}
	
	public static void shutdown(Context context){
		Intent shutdown = new Intent(Intent.ACTION_SHUTDOWN);
	    shutdown.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
	    context.startActivity(shutdown);
	}
}
