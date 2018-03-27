package tools;

import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.JFrame;

public class DuFrame extends JFrame {

	
	private static DuFrame instance;
	
	public static  DuFrame getInstance(){
		if(instance == null){
			instance = new DuFrame();
		}
		return instance;
	}
	
	
	/**
	 * serial id
	 */
	private static final long serialVersionUID = -5609338395355447295L;
	
	private String title = "android适配方案工具";
	
	
	CenterLayout centerLayout;
	TopLayout topLayout;
	BottomLayout bottomLayout;
	/**
	 * 自定义构造容器
	 */
	private DuFrame(){
		super();
		
		this.setSize(500, 300);
		this.setLocation(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(title);
		this.setResizable(false);
		
		
		centerLayout = new CenterLayout();
		topLayout = new TopLayout();
		bottomLayout = new BottomLayout(this);
		// 添加组件
		this.add(topLayout,BorderLayout.NORTH );
		this.add(centerLayout,BorderLayout.CENTER );
		this.add(bottomLayout,BorderLayout.SOUTH );
		
	}
	
	
//	public boolean isSPSelect(){
//		return topLayout.isSPSelected();
//	}
	
	public LinkedList<String> getTargetValues(){
		
		return centerLayout.getTargetValues();
	}
	
	/**
	 * 设置展示
	 */
	public void showFrame(){
		this.setVisible(true);
	}
	
	
public String getSrcSize(){
		
		return topLayout.getSrcSize();
	}
	
	
	
}
