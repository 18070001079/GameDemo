/**
 * 
 */
package GameFrame;

/** 
 * 类描述：
 * 作者：linsiyuan 
 * 创建日期：2021年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class CollisionBox {
	
	public Position downLeft;
	public Position upRight;
	public CollisionBox(Position downLeft, Position upRight){
		this.downLeft = downLeft;
		this.upRight = upRight;
	}
	
	//检测是否发生碰撞
	static public boolean isOnCollision(CollisionBox cb1, CollisionBox cb2){
		if(cb1.upRight.x < cb2.downLeft.x || cb1.downLeft.x > cb2.upRight.x
				|| cb1.upRight.y < cb2.downLeft.y || cb1.downLeft.y > cb2.upRight.y){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "("+downLeft.x+","+downLeft.y+")("+upRight.x+","+upRight.y+")";
	}
}
