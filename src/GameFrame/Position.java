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

//坐标系左下为原点，屏幕空间实际为1260*670，与逻辑坐标映射关系为1：1
public class Position {
	public float x;
	public float y;
	public Position(float x, float y){
		this.x = x;
		this.y = y;
	}
	public Position(){
		this.x = 0.0f;
		this.y = 0.0f;
	}
	static public Position Add(Position p1, Position p2){
		return new Position(p1.x + p2.x, p1.y + p2.y);
	}
	//p1 - p2
	static public Position Minus(Position p1, Position p2){
		return new Position(p1.x - p2.x, p1.y - p2.y);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x:" + this.x + " y:" + this.y;
	}
}
