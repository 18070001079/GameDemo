/**
 * 
 */
package GameFrame;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

/** 
 * 类描述：
 * 作者：linsiyuan 
 * 创建日期：2021年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class GameObject {
	//基本属性
	protected String name;			//标识符
	protected Position pos;			//坐标
	protected Type type;			//物体类型
	protected boolean isCollision;	//是否参与碰撞检测
	protected int layerLevel;		//图层优先级，高的图层覆盖低的图层
	protected Image staticImg;		//静态贴图，无动画效果
	protected int imgWidth;			//静态贴图宽度
	protected int imgHeight;		//静态贴图高度
	
	//"无"表示无贴图,"静态贴图"表示播放静态贴图,其他则去ic中寻找相应的动画贴图
	protected String curAnim;		//当前播放动画	
	
	protected ImageContainer ic = null;		//绘图组件
	protected CollisionBox cb = null;		//碰撞组件
	protected PhysicalController pc = null;	//物理控件
	
	public GameObject(String name, Position pos, Type type){
		this.name = name;
		this.pos = pos;
		this.type = type;
		this.curAnim = "无";
		this.isCollision = false;
		this.layerLevel = 0;
		this.staticImg = null;
		this.imgHeight = 100;
		this.imgWidth = 100;
	}
	
	public void createPhysis(){
		//如果碰撞盒未初始化
		if(cb == null){
			cb = new CollisionBox(pos, new Position(pos.x + imgWidth, pos.y + imgHeight));
		}
		pc = new PhysicalController(cb, this);
	}
	
	//设置碰撞盒
	public void setCollisionBox(CollisionBox cb){
		this.cb = cb;
		//如果已有物理控件，则同步更新物理控件
		if(pc != null)
			pc.setCb(cb);
	}
	public CollisionBox getCollisionBox(){
		return cb;
	}
	
	//设置静态贴图
	public void setStaticImage(String url, int imgWidth, int imgHeight){
		//添加前检测文件是否存在
		File file = new File(url);
		if (!file.exists()) {
		    System.out.println("文件路径不存在:" + url);
		}else{
			staticImg = Toolkit.getDefaultToolkit().getImage(url);
			this.imgWidth = imgWidth;
			this.imgHeight = imgHeight;
			System.out.println("加载贴图资源成功:" + url);
		}
		curAnim = "静态贴图";
	}
	public Image getStaticImage(){
		return staticImg;
	}
	
	//设置当前播放动画
	public void playAnimation(String animName){
		if(ic.getAnim(animName)!=null){
			this.curAnim = animName;
			ic.getAnim(animName).curImgFrame = 0;
			ic.getAnim(animName).curFrame = 0;
		}
		else{
			System.out.println("<" + name + ">所需的动画资源不存在(" + animName + ")");
		}
	}
	
	//检测物体间是否发生碰撞,注意碰撞盒比较不能为null
	public boolean isOnCollision(GameObject obj){
		if(obj.cb != null && this.cb != null)
			return CollisionBox.isOnCollision(this.cb, obj.cb);
		return false;
	}
	
	//设置绘图组件
	public void setImageContainer(ImageContainer ic){
		this.ic = ic;
	}
	public ImageContainer getImageContainer(){
		return ic;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public String getCurAnimName(){
		return curAnim;
	}
	public void setCurAnim(String animName){
		this.curAnim = animName;
	}
	public void setPosition(Position pos){
		this.pos = pos;
		if(cb!=null){
			Position offset = Position.Minus(cb.upRight, cb.downLeft);
			cb.downLeft = pos;
			cb.upRight = Position.Add(pos, offset);
		}
	}
	public Position getPosition(){
		return pos;
	}
	public int getImgWidth(){
		return imgWidth;
	}
	public int getImgHeight(){
		return imgHeight;
	}
	public void setImgSize(int imgWidth, int imgHeight){
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}
	public void setLayerLevel(int level){
		this.layerLevel = level;
	}
	public int getLayerLevel(){
		return layerLevel;
	}
	public boolean getIsCollision(){
		return isCollision;
	}
	public void setIsCollision(boolean isCollision){
		this.isCollision = isCollision;
	}
	public PhysicalController getPhysicalController(){
		return pc;
	}
}
