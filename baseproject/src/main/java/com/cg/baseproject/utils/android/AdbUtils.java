package com.cg.baseproject.utils.android;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2018/4/4
 */
    public class AdbUtils {

    public static void doCmds(String cmd) throws Exception {
        List<String> listCmd = new ArrayList<String>();
        listCmd.add(cmd);
        doCmds(listCmd);
        
    }
    public static void doCmds(List<String> cmds) throws Exception {
        Process process = Runtime.getRuntime().exec("su");
        DataOutputStream os = new DataOutputStream(process.getOutputStream());
        for (String tmpCmd : cmds) {
            os.writeBytes(tmpCmd+"\n");
        }
        os.writeBytes("exit\n");
        os.flush();
        os.close();
        process.waitFor();
    }
}
