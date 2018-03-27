package tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BottomLayout extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 2566994302921751173L;

    private DuFrame frame;
    private JButton btnSure;

    BottomLayout(DuFrame frame) {

        this.frame = frame;
        btnSure = new JButton("生成");
        this.add(btnSure);
        // 设置事件监听
        btnSure.addActionListener(this);
    }


    private boolean isSPSelect = false;
    DimenInfo mDimenInfo = new DimenInfo();

    /**
     * 动作事件监听
     */
    @Override
    public void actionPerformed(ActionEvent e) {
//        isSPSelect = frame.isSPSelect();
        if (e.getSource() == btnSure) {
            String result = frame.getSrcSize();
            // 屏蔽掉程序执行入口
            btnSure.setText("正在生成中");
            btnSure.setEnabled(false);
            // 判断文件是否存在
            File resFile = new File("./res");
            if (resFile.exists()) {
//                System.out.println("当前目录存在同名文件夹，请处理");
//                JOptionPane.showMessageDialog(null, "当前目录存在同名文件夹，请处理");
            } else {
                resFile.mkdir();
            }
            // 获取相关参数
            LinkedList<String> lists = frame.getTargetValues();
            // 设置以480*800为计算标准
            // 根文件创建成功，则创建其他文件夹
            File file;
            Iterator<String> iterator = lists.iterator();
            while (iterator.hasNext()) {
                String dirName = iterator.next();
                file = new File("./res/" + dirName);
                if (!file.exists()) {
                    file.mkdir();
                    System.out.println(dirName+"创建成功");
                    // 计算缩放比例
                    String desValue = dirName;
                    Pattern p = Pattern.compile("\\d+");
                    Matcher m = p.matcher(desValue);
                    String numValueOne = "";
                    String numValueTwo = "";
                    if (m.find()) {
                        numValueOne = m.group(0);
                        if (m.find()) {
                            numValueTwo = m.group(0);
                        }
                    }
                    // 获取最小值
                    int num;
                    int height = Integer.parseInt(numValueOne);
                    int width = Integer.parseInt(numValueTwo);
                    String xxxHdpi = StringUtils.getHdpi(dirName);
//                    if (height > width) {
//                        num = width;
//                    } else {
//                        num = height;
//                    }
                    // 获取原始效果尺寸
//                    int intSrcSize = Integer.parseInt(frame.getSrcSize());
//                    float scale = (float) ((num * 1.0) / intSrcSize);
                    File dimensFile = new File(file.getAbsoluteFile()
                            + "/dimens.xml");
                    try {
                    	mDimenInfo.setDpi(xxxHdpi);
                    	mDimenInfo.setHeight(height);
                    	mDimenInfo.setWidth(width);
                        DimensUtils.outContent(
                                dimensFile, mDimenInfo);
//                        System.out.println("dpi:"+xxxHdpi+" height:"+height+" width:"+width);
                        System.out.println();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        System.out.println("生成文件错误，请稍后重试");
                        JOptionPane.showMessageDialog(null, "生成文件错误，请稍后重试");
                        return;
                    }
                    // 生成sp相关数据
                }
            }
            JOptionPane.showMessageDialog(null, "配置文件生成成功！");
            // 生成完毕，恢复按钮状态
            btnSure.setText("生成");
            btnSure.setEnabled(true);
        }

    }

}
