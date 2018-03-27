package tools;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TopLayout extends JPanel{

	private String strSelect = "选择效果图尺寸:";
	
	
	private ButtonGroup group;
	private JRadioButton jrb1280x720;
	private JRadioButton jrb1920x1080;
	
	// 是否支持3.2
//	private JCheckBox jcbV32;
	/**
	 * 构造容器
	 */
	TopLayout(){
		
		
		JLabel jlShow = new JLabel(strSelect);
		group = new ButtonGroup();
		
		this.add( jlShow);
//		jrb1280x720 = new JRadioButton("1280x720");
//		jrb1280x720.setSelected(true);
//		group.add(jrb1280x720);
//		this.add( jrb1280x720);
		jrb1920x1080 = new JRadioButton("1920x1080");
		group.add(jrb1920x1080);
		jrb1920x1080.setSelected(true);
		this.add( jrb1920x1080);
		
		
		
		// 设置是否支持3.2系统，因为3.2系统主要针对平板。 屏幕适配规则不太
//		jcbV32 = new JCheckBox("支持3.2 平板系统");
//		this.add(jcbV32);
		
		
	}
	
	
//	public boolean isSPSelected(){
//		
//		
//		return jcbV32.isSelected();
//	}
	
	
	public String getSrcSize(){
		
		
//		if(jrb1280x720.isSelected()){
//			return "720";
//		}else if(jrb1920x1080.isSelected()){
//			return "1080";
//		}
		
//		String result = group.getSelection().getSelectedObjects(1) + "";
//		System.out.println("result  ====" + result);
//		return result;
		
		return "1080";
	}
	
	
	
}
