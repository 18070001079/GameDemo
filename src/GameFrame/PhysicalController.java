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
public class PhysicalController {
	protected Velocity velocity;
	protected GameObject go;
	protected CollisionBox cb;
	protected boolean isGravity = false;//是否受重力影响
	protected float gravity = -100f;	//负数向下加速
	protected float bounceFactor = 0.0f;//弹力系数，不能超过1，0表示无弹力
	protected boolean isStatic = true;	//是否是静态物体
	
	public PhysicalController(CollisionBox cb, GameObject go){
		this.cb = cb;
		velocity = new Velocity(go);
		this.go = go;
	}

	public Velocity getVelocity() {
		return velocity;
	}
	public void setVelocity(float vx, float vy){
		velocity.vx = vx;
		velocity.vy = vy;
	}
	public void addVelocity(float vx, float vy){
		velocity.vx = velocity.vx+ vx;
		velocity.vy = velocity.vy+ vy;
	}
	public boolean getIsGravity() {
		return isGravity;
	}
	public void setIsGravity(boolean isGravity) {
		this.isGravity = isGravity;
	}
	public float getGravity() {
		return gravity;
	}
	public void setGravity(float gravity) {
		this.gravity = gravity;
	}
	public CollisionBox getCb() {
		return cb;
	}
	public void setCb(CollisionBox cb) {
		this.cb = cb;
	}
	public float getBounceFactor() {
		return bounceFactor;
	}
	public void setBounceFactor(float bounceFactor) {
		this.bounceFactor = bounceFactor;
	}
	public boolean getIsStatic() {
		return isStatic;
	}
	public void setIsStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
}
