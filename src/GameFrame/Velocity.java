/**
 * 
 */
package GameFrame;

/** 
 * 类描述：
 * 作者：linsiyuan 
 * 创建日期：2021年11月24日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class Velocity {
	private GameObject go;
	protected float vx = 0.0f;	//x轴速度，单位为像素/秒
	protected float vy = 0.0f;	//y轴速度，单位为像素/秒
	
	public Velocity(GameObject go){
		this.go = go;
	}
	
	//根据速度更新对象坐标
	public void updatePos(){
		Position offset = new Position(vx/50.0f, vy/50.0f);
		go.setPosition(Position.Add(offset, go.getPosition()));
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}
	

}
