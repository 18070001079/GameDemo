/**
 * 
 */
package GameProgram;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/** 
 * 类描述：
 * 作者：linsiyuan 
 * 创建日期：2021年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class StartGame {
	
	static final int HEIGHT = 720;			//窗口高度
	static final int offsetHEIGHT = 673;	//修正坐标轴后的高度
	static final int WIDTH = 1280;			//窗口宽度

	public StartGame() {
        //绘图面板
        JFrame frame = new JFrame("Demo");
        frame.setBounds(300, 300, WIDTH, HEIGHT);
        frame.setResizable(false);//窗口大小不能改变，不然游戏变形
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setFocusable(false);
        frame.setVisible(true);
        
        frame.add(new GamePanel());
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StartGame();
	}

}
