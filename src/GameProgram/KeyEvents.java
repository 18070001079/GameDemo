/**
 * 
 */
package GameProgram;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GameFrame.GameObject;
import GameFrame.Position;

/** 
 * 类描述：
 * 作者：linsiyuan 
 * 创建日期：2021年11月23日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class KeyEvents implements KeyListener{
	
	//主程序实例，只能被赋值一次
	private GamePanel gp = null;
	
	public KeyEvents(GamePanel gp){
		this.gp = gp;
	}

	/* 
	 * 按键按下后调用事件
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("KeyPressed:" + KeyEvent.getKeyText(e.getKeyCode()));
		//System.out.println(e.getKeyChar());
    	if (e.getKeyCode() == KeyEvent.VK_W) {
    		gp.moveCamera(new Position(0, 20));
        }
    	if (e.getKeyCode() == KeyEvent.VK_S) {
    		gp.moveCamera(new Position(0, -20));
        }
    	if (e.getKeyCode() == KeyEvent.VK_A) {
    		gp.moveCamera(new Position(-20, 0));
        }
    	if (e.getKeyCode() == KeyEvent.VK_D) {
    		gp.moveCamera(new Position(20, 0));
        }
	}

	/* 
	 * 按键按下释放后后调用事件
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("KeyReleased:" + KeyEvent.getKeyText(e.getKeyCode()));
	}

	/* 
	 * 
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
