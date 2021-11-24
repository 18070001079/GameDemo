/**
 * 
 */
package GameProgram;

import javax.swing.*;

import GameFrame.CollisionBox;
import GameFrame.GameObject;
import GameFrame.ImageContainer;
import GameFrame.ImageSeries;
import GameFrame.PhysicalController;
import GameFrame.Position;
import GameFrame.Type;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** 
 * 类描述：
 * 作者：linsiyuan 
 * 创建日期：2021年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class GamePanel extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//所有对象列表
	protected ArrayList<GameObject> allObjs = new ArrayList<GameObject>();
	
	//图层列表,优先级最高为4
	protected ArrayList<GameObject> layer_0 = new ArrayList<GameObject>();
	protected ArrayList<GameObject> layer_1 = new ArrayList<GameObject>();
	protected ArrayList<GameObject> layer_2 = new ArrayList<GameObject>();
	protected ArrayList<GameObject> layer_3 = new ArrayList<GameObject>();
	protected ArrayList<GameObject> layer_4 = new ArrayList<GameObject>();
	
	//定时器	一秒主循环执行50次
    Timer timer = new Timer(20, this);
    
    //摄像机位置
    protected Position camPos = new Position(0, 0);
    
    int offset = 2;
	
	public GamePanel() {
        init();//数据初始化
        //给Panel添加键盘监听
        this.setFocusable(true);
        //绑定键盘监听器
        this.addKeyListener(new KeyEvents(this));
        
        //给panel 添加定时器
        timer.start();//游戏一开始定时器就启动
    }
	
	//数据初始化
	public void init(){
		//每次新建对象都要添加进该队列
		ArrayList<GameObject> goList = new ArrayList<GameObject>();
		for(int i = 0; i < 3; i++){
			GameObject bg = new GameObject("背景"+Integer.toString(i), new Position(i*1280,0), Type.Backgroud);
			bg.setStaticImage("./res/img/bg.jpg", 1280, 720);
			bg.setIsCollision(false);
			goList.add(bg);
		}
		
		GameObject moveItem = new GameObject("物体1", new Position(300,500), Type.Item);
		moveItem.setLayerLevel(1);
		moveItem.setIsCollision(true);
		moveItem.setStaticImage("./res/img/2.jpg", 50, 50);
		moveItem.createPhysis();
		moveItem.getPhysicalController().setIsStatic(false);
		moveItem.getPhysicalController().setBounceFactor(1.0f);
		moveItem.getPhysicalController().setIsGravity(false);
		goList.add(moveItem);
		
		GameObject moveItem2 = new GameObject("物体2", new Position(500,500), Type.Item);
		moveItem2.setLayerLevel(1);
		moveItem2.setIsCollision(true);
		moveItem2.setStaticImage("./res/img/2.jpg", 50, 50);
		moveItem2.createPhysis();
		moveItem2.getPhysicalController().setIsStatic(false);
		moveItem2.getPhysicalController().setBounceFactor(0.0f);
		moveItem2.getPhysicalController().setIsGravity(false);
		moveItem2.getPhysicalController().setVelocity(200.0f, 0.0f);
		goList.add(moveItem2);
		
		GameObject moveItem3 = new GameObject("物体3", new Position(700,500), Type.Item);
		moveItem3.setLayerLevel(1);
		moveItem3.setIsCollision(true);
		moveItem3.setStaticImage("./res/img/2.jpg", 50, 50);
		moveItem3.createPhysis();
		moveItem3.getPhysicalController().setIsStatic(false);
		moveItem3.getPhysicalController().setBounceFactor(0.5f);
		moveItem3.getPhysicalController().setIsGravity(false);
		goList.add(moveItem3);
		
		GameObject grass = new GameObject("草地", new Position(150,0), Type.Item);
		grass.setStaticImage("./res/img/grass.jpg", 1000, 150);
		grass.setLayerLevel(1);
		grass.setIsCollision(true);
		grass.createPhysis();
		
		goList.add(grass);
		/*
		for(int i = 0; i < 1; i++){
			GameObject anim = new GameObject("动画测试" + Integer.toString(i), new Position(i*50,0), Type.Player);
			anim.setImgSize(200, 200);
			anim.setLayerLevel(2);
			ImageContainer ic = new ImageContainer();
			String[] urls = new String[]{"./res/img/player-idle-1.png", "./res/img/player-idle-2.png"
					,"./res/img/player-idle-3.png","./res/img/player-idle-4.png"};
			ic.addAnimImages(urls, "站立动画");
			anim.setImageContainer(ic);
			anim.playAnimation("站立动画");
			anim.getImageContainer().getAnim("站立动画").setPlayInterval(5);
			goList.add(anim);
		}
		*/
		//将生成对象添加至对象列表，否则不参与程序执行
		addObjToList(goList);
		destroy("背景1");
		debugInfo();
	}
	
	//绘制图像，不要在里面做数据更新
	@Override
    public void paint(Graphics g) {
		//long startTime =  System.currentTimeMillis();
		
		
		//清屏
		g.clearRect(0, 0, StartGame.WIDTH, StartGame.HEIGHT);
		
		//绘制所有图层
		drawLayers(g);
		
		
		
		/*Image img2 = Toolkit.getDefaultToolkit().getImage("./res/img/bg.jpg");
		g.drawImage(img2, 0, 0, 1280, 720, this);
		Image img = Toolkit.getDefaultToolkit().getImage("./res/img/2.jpg");
		g.drawImage(img, x, y, 300, 300, this);*/
		/*long endTime =  System.currentTimeMillis();
		long usedTime = (endTime-startTime);
		System.out.println("绘图时间:" + usedTime + "ms");*/
    }
	
	public void drawLayers(Graphics g){
		drawLayer(g, layer_0);
		drawLayer(g, layer_1);
		drawLayer(g, layer_2);
		drawLayer(g, layer_3);
		drawLayer(g, layer_4);
	}
	
	//绘制图层
	public void drawLayer(Graphics g, ArrayList<GameObject> layerList){
		/*
		for(int i = 0; i < layer_0.size(); i++){
			GameObject obj = layer_0.get(i);
			int x = (int)obj.getPosition().x;
			int y = (int)obj.getPosition().y;
			int width = obj.getImgWidth();
			int height = obj.getImgHeight();
			g.drawImage(obj.getStaticImage(), x, y, width, height, this);
		}*/
		for(int i = 0; i < layerList.size(); i++){
			GameObject obj = layerList.get(i);;
			
			//如果播放动画不是无，即需要绘制贴图
			String animName = obj.getCurAnimName();
			if(!animName.equals("无")){
				Position tPos = new Position(obj.getPosition().x, obj.getPosition().y + obj.getImgHeight());
				Position screenPos = posTransToScreenPos(camPos, tPos);
				int screenX = (int)screenPos.x;
				int screenY = (int)screenPos.y;
				int width = obj.getImgWidth();
				int height = obj.getImgHeight();
				//如果播放的是静态贴图，则不访问相关动画组件
				if(animName.equals("静态贴图")){
					g.drawImage(obj.getStaticImage(), screenX, screenY, width, height, this);
				}else{
					//如果所寻找的动画资源存在
					ImageSeries is = obj.getImageContainer().getAnim(animName);
					if(is != null){
						//绘制当前贴图帧
						g.drawImage(is.getImgList().get(is.curImgFrame), screenX, screenY, width, height, this);
						//更新所需绘制的图片帧
						is.curFrame++;
						if(is.curFrame % is.getPlayInterval() == 0){
							is.curImgFrame++;;
							is.curFrame = 0;
							//播放到序列尾，则重置为序列头
							if(is.curImgFrame >= is.getImgList().size()){
								is.curImgFrame = 0;
							}
						}
					}else{
						System.out.println("<" + obj.getName() + ">所需的动画资源不存在(" + animName + ")");
					}
					
				}
			}
		}
	}
	
	//数据更新相关
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		//以下填入数据更新代码
		//layer_1.get(0).setPosition(Position.Add(layer_1.get(0).getPosition(), new Position(0, -2)));
		
		
		physisLoop();
		//——————————————//
		timer.start();
	}
	
	//物理相关数据更新
	public void physisLoop(){
		gravityUpdate();
		collisionDetection();
		velocityUpdate();
	}
	
	//重力影响更新
	public void gravityUpdate(){
		for(int i = 0; i < allObjs.size(); i++){
			PhysicalController pc = allObjs.get(i).getPhysicalController();
			//若物理控件存在则进行物理更新，否则不受影响
			if(pc != null && pc.getIsGravity()){
				pc.addVelocity(0 , pc.getGravity());
			}
		}
	}
	
	//速度影响更新
	public void velocityUpdate(){
		for(int i = 0; i < allObjs.size(); i++){
			PhysicalController pc = allObjs.get(i).getPhysicalController();
			//若物理控件存在则进行物理更新，否则不受影响
			if(pc != null){
				pc.getVelocity().updatePos();
			}
		}
	}
	
	//碰撞检测
	public void collisionDetection(){
		ArrayList<GameObject> colList = new ArrayList<GameObject>();
		for(int i = 0; i < allObjs.size(); i++){
			if(allObjs.get(i).getIsCollision()){
				colList.add(allObjs.get(i));
			}
		}
		for(int i = 0; i < colList.size(); i++){
			for(int j = i + 1; j < colList.size(); j++){
				//进行碰撞检测的两物体必须都具有物理控件
				GameObject obj1 = colList.get(i);
				GameObject obj2 = colList.get(j);
				PhysicalController pc1 = obj1.getPhysicalController();
				PhysicalController pc2 = obj2.getPhysicalController();
				if(pc1 != null && pc2 != null){
					//如果产生碰撞，则相互产生影响
					CollisionBox cb1 = obj1.getCollisionBox();
					CollisionBox cb2 = obj2.getCollisionBox();
					if(CollisionBox.isOnCollision(cb1, cb2)){
						//如果不是静态物体，则产生影响
						System.out.println("<"+obj1.getName()+">"+"<"+obj2.getName()+">OnCollision");
						int dir = collideDir(cb1, cb2);
						
						if(!pc1.getIsStatic()){
							calVel(obj1,obj2);
						}
						if(!pc2.getIsStatic()){
							calVel(obj2,obj1);
						}
						separateObj(obj1, obj2, dir);
					}
				}
				
			}
		}
	}
	
	public void calVel(GameObject obj1, GameObject obj2){
		PhysicalController pc1 = obj1.getPhysicalController();
		PhysicalController pc2 = obj2.getPhysicalController();
		CollisionBox cb1 = obj1.getCollisionBox();
		CollisionBox cb2 = obj2.getCollisionBox();
		int dir = collideDir(cb1, cb2);
		if(!pc1.getIsStatic()){
			//System.out.println("<"+obj1.getName()+">Velocity:"+pc1.getVelocity().toString());
			switch(dir){
				case 1:
					break;
				case 2:
					System.out.println("2");
					pc1.addVelocity(0, 
							-1*(pc1.getVelocity().getVy()-pc1.getGravity())
							-(pc1.getBounceFactor()+pc2.getBounceFactor())
							*(pc1.getVelocity().getVy()+pc2.getVelocity().getVy()));
					break;
				case 3:
					break;
				case 4:
					System.out.println("4");
					pc1.setVelocity(-(pc1.getVelocity().getVx()+pc2.getVelocity().getVx())
							*(pc1.getBounceFactor()+pc2.getBounceFactor()), 
							pc1.getVelocity().getVy());
					break;
				case 5:
					break;
				case 6:
					System.out.println("6");
					pc1.setVelocity(pc1.getVelocity().getVx(), 
							-(pc1.getVelocity().getVy()+pc2.getVelocity().getVy())
							*(pc1.getBounceFactor()+pc2.getBounceFactor()));
					break;
				case 7:
					break;
				case 8:
					System.out.println("8");
					pc1.setVelocity(-(pc1.getVelocity().getVx()+pc2.getVelocity().getVx())
							*(pc1.getBounceFactor()+pc2.getBounceFactor()), 
							pc1.getVelocity().getVy());
					break;
			}
			//System.out.println("<"+obj1.getName()+">VelocityA:"+pc1.getVelocity().toString());
		}
	}
	
	//将物体与被撞体分离
	public void separateObj(GameObject obj1, GameObject obj2, int dir){
		CollisionBox cb1 = obj1.getCollisionBox();
		CollisionBox cb2 = obj2.getCollisionBox();
		Position p1 = obj1.getPosition();
		switch(dir){
		case 0:
			//System.out.println("无碰撞产生");
			break;
		case 1:
			break;
		case 2:
			obj1.setPosition(new Position(p1.x, p1.y+cb2.upRight.y-cb1.downLeft.y));
			break;
		case 3:
			break;
		case 4:
			obj1.setPosition(new Position(p1.x+cb2.upRight.x-cb1.downLeft.x, p1.y));
			break;
		case 5:
			break;
		case 6:
			obj1.setPosition(new Position(p1.x, p1.y-cb1.upRight.y+cb2.downLeft.y));
			break;
		case 7:
			break;
		case 8:
			obj1.setPosition(new Position(p1.x-cb1.upRight.x+cb2.downLeft.x, p1.y));
			break;
		default:
			System.out.println("碰撞检测出错！");
			break;
		}
	}
	
	//计算产生碰撞时，碰撞的方向
	public int collideDir(CollisionBox cb1, CollisionBox cb2){
		Position center1 = Position.GetCenter(cb1.downLeft, cb1.upRight);
		Position center2 = Position.GetCenter(cb2.downLeft, cb2.upRight);
		
		if(!CollisionBox.isOnCollision(cb1, cb2)){
			return 0;
		}
		
		if(center1.x < cb2.downLeft.x){
			if(center2.y < cb1.downLeft.y){
				return 1;
			}else if(center2.y > cb1.upRight.y){
				return 7;
			}
			return 8;
		}else if(center1.x > cb2.upRight.x){
			if(center2.y < cb1.downLeft.y){
				return 3;
			}else if(center2.y > cb1.upRight.y){
				return 5;
			}
			return 4;
		}else if(center1.y > cb2.upRight.y){
			return 2;
		}else if(center1.y < cb2.downLeft.y){
			return 6;
		}
		
		//异常
		return -1;
	}
	
	//逻辑坐标到屏幕坐标的映射
	public Position posTransToScreenPos(Position camera, Position p1){
		Position trans = new Position(p1.x, StartGame.offsetHEIGHT-p1.y);
		return Position.Minus(trans, new Position(camera.x, camera.y));
	}
	//基于摄像机当前位置进行偏移
	public void moveCamera(Position offset){
		offset = new Position(-offset.x, offset.y);
		camPos = Position.Minus(camPos, offset);
		//每次移动都维护一次显示列表
		updateLists();
	}
	//设置摄像机位置
	public void setCamera(Position pos){
		camPos = pos;
		updateLists();
	}
	//维护当前显示列表
	public void updateLists(){
		updateList(layer_0);
		updateList(layer_1);
		updateList(layer_2);
		updateList(layer_3);
		updateList(layer_4);
		//添加进入视野的物体
		for(int i = 0; i < allObjs.size(); i++){
			//摄像机碰撞盒
			CollisionBox camCb = new CollisionBox(camPos, new Position(camPos.x + 1280, camPos.y + 720));
			//物体碰撞盒
			CollisionBox itemCb = new CollisionBox(allObjs.get(i).getPosition(),
					new Position(allObjs.get(i).getPosition().x + allObjs.get(i).getImgWidth()
							, allObjs.get(i).getPosition().y + allObjs.get(i).getImgHeight()));
			//如果产生碰撞且图层列表不存在该元素，添加列表
			if(CollisionBox.isOnCollision(camCb, itemCb)){
				switch(allObjs.get(i).getLayerLevel()){
					case 0:
						if(!isExistInLayer(allObjs.get(i), layer_0)){
							layer_0.add(allObjs.get(i));
							//System.out.println("添加" + allObjs.get(i).getName());
						}
						break;
					case 1:
						if(!isExistInLayer(allObjs.get(i), layer_1)){
							layer_1.add(allObjs.get(i));
							//System.out.println("添加" + allObjs.get(i).getName());
						}
						break;
					case 2:
						if(!isExistInLayer(allObjs.get(i), layer_2)){
							layer_2.add(allObjs.get(i));
							//System.out.println("添加" + allObjs.get(i).getName());
						}
						break;
					case 3:
						if(!isExistInLayer(allObjs.get(i), layer_3)){
							layer_3.add(allObjs.get(i));
							//System.out.println("添加" + allObjs.get(i).getName());
						}
						break;
					case 4:
						if(!isExistInLayer(allObjs.get(i), layer_4)){
							layer_4.add(allObjs.get(i));
							//System.out.println("添加" + allObjs.get(i).getName());
						}
						break;
				}
			}
		}
	}
	//维护图层列表
	public void updateList(ArrayList<GameObject> layerList){
		//删除视野外物体
		for(int i = 0; i < layerList.size(); i++){
			//摄像机碰撞盒
			CollisionBox camCb = new CollisionBox(camPos, new Position(camPos.x + 1280, camPos.y + 720));
			//物体碰撞盒
			CollisionBox itemCb = new CollisionBox(layerList.get(i).getPosition(),
					new Position(layerList.get(i).getPosition().x + layerList.get(i).getImgWidth()
							, layerList.get(i).getPosition().y + layerList.get(i).getImgHeight()));
			//如果不产生碰撞，则说明在视野范围外，从列表中删除
			if(!CollisionBox.isOnCollision(camCb, itemCb)){
				//System.out.println("删除" + layerList.get(i).getName());
				layerList.remove(i);
			}
		}
	}
	//搜索图层是否存在元素
	public boolean isExistInLayer(GameObject obj, ArrayList<GameObject> layerList){
		for(int i = 0; i < layerList.size(); i++){
			if(layerList.get(i).equals(obj))
				return true;
		}
		return false;
	}
	//将对象添加至物体队列
	public void addObjToList(ArrayList<GameObject> list){
		for(int i = 0; i < list.size(); i++){
			allObjs.add(list.get(i));
		}
		updateLists();
	}
	public void addObjToList(GameObject obj){
		allObjs.add(obj);
		updateLists();
	}
	
	//删除物体
	public void destroy(GameObject obj){
		for(int i = 0; i < allObjs.size(); i++){
			if(obj.equals(allObjs.get(i))){
				allObjs.remove(i);
			}
		}
		for(int i = 0; i < layer_0.size(); i++){
			if(obj.equals(layer_0.get(i))){
				layer_0.remove(i);
			}
		}
		for(int i = 0; i < layer_1.size(); i++){
			if(obj.equals(layer_1.get(i))){
				layer_1.remove(i);
			}
		}
		for(int i = 0; i < layer_2.size(); i++){
			if(obj.equals(layer_2.get(i))){
				layer_2.remove(i);
			}
		}
		for(int i = 0; i < layer_3.size(); i++){
			if(obj.equals(layer_3.get(i))){
				layer_3.remove(i);
			}
		}
		for(int i = 0; i < layer_4.size(); i++){
			if(obj.equals(layer_4.get(i))){
				layer_4.remove(i);
			}
		}
	}
	public void destroy(String objName){
		for(int i = 0; i < allObjs.size(); i++){
			if(allObjs.get(i).getName().equals(objName)){
				allObjs.remove(i);
			}
		}
		for(int i = 0; i < layer_0.size(); i++){
			if(layer_0.get(i).getName().equals(objName)){
				layer_0.remove(i);
			}
		}
		for(int i = 0; i < layer_1.size(); i++){
			if(layer_1.get(i).getName().equals(objName)){
				layer_1.remove(i);
			}
		}
		for(int i = 0; i < layer_2.size(); i++){
			if(layer_2.get(i).getName().equals(objName)){
				layer_2.remove(i);
			}
		}
		for(int i = 0; i < layer_3.size(); i++){
			if(layer_3.get(i).getName().equals(objName)){
				layer_3.remove(i);
			}
		}
		for(int i = 0; i < layer_4.size(); i++){
			if(layer_4.get(i).getName().equals(objName)){
				layer_4.remove(i);
			}
		}
	}
	
	public void debugInfo(){
		for(int i = 0; i < allObjs.size(); i++){
			System.out.println("<" + allObjs.get(i).getName() + ">坐标:" + allObjs.get(i).getPosition().toString());
			if(allObjs.get(i).getCollisionBox()!=null){
				System.out.println("<" + allObjs.get(i).getName() + ">碰撞盒:" + allObjs.get(i).getCollisionBox().toString());
			}
		}
		System.out.println("<CamPos>" + camPos.toString());
	}
	
}