package tools;

import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CenterLayout extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3503191094211761668L;
	JCheckBox item,item2,item3,item4,item5,item6,item7,item8;
	JLabel labelCustomResolution;//自定义分辨率提示
	JTextField jtextCustomResolution;//自定义分辨率输入框
	String customResolution = "values-xxhdpi-1920x1080";
	CenterLayout(){
		this.add(new JLabel("选择想要生成的目标尺寸:"));
		item = new JCheckBox("values-xhdpi-1280x720");
		this.add(item);
		item2 = new JCheckBox("values-xhdpi-1184x720");
		this.add(item2);
		item3 = new JCheckBox("values-xxhdpi-1920x1080");
		this.add(item3);
		item4 = new JCheckBox("values-xxhdpi-1776x1080");
		this.add(item4);
		item5 = new JCheckBox("values-xxhdpi-1812x1080");
		this.add(item5);
		item6 = new JCheckBox("values-xxxhdpi-2560x1440");
		this.add(item6);
		item7 = new JCheckBox("values-xxxhdpi-2392x1440");
		this.add(item7);
		item8 = new JCheckBox("values-xxxhdpi-2960x1440");
		this.add(item8);
		
		labelCustomResolution = new JLabel("自定义分辨率：");
		this.add(labelCustomResolution);
		jtextCustomResolution = new JTextField(100);
		jtextCustomResolution.setBounds(80, 10, 400, 25);
		this.add(jtextCustomResolution);
		
	}
	
	
	public LinkedList<String> getTargetValues(){
		LinkedList<String> lists = new LinkedList<String>();
		if(item.isSelected()){
			String actionCommand = item.getActionCommand();
			lists.add(actionCommand);
		}
		if(item2.isSelected()){
			String actionCommand = item2.getActionCommand();
			lists.add(actionCommand);
		}if(item3.isSelected()){
			String actionCommand = item3.getActionCommand();
			lists.add(actionCommand);
		}if(item4.isSelected()){
			String actionCommand = item4.getActionCommand();
			lists.add(actionCommand);
		}if(item5.isSelected()){
			String actionCommand = item5.getActionCommand();
			lists.add(actionCommand);
		}if(item6.isSelected()){
			String actionCommand = item6.getActionCommand();
			lists.add(actionCommand);
		}if(item7.isSelected()){
			String actionCommand = item7.getActionCommand();
			lists.add(actionCommand);
		}if(item8.isSelected()){
			String actionCommand = item8.getActionCommand();
			lists.add(actionCommand);
		}
		lists.add(jtextCustomResolution.getText());
		System.out.println(jtextCustomResolution.getText());
		return lists;
	}
}
