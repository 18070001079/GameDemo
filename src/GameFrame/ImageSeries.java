/**
 * 
 */
package GameFrame;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
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
public class ImageSeries {
	
	public int curImgFrame = 0;		//当前播放的序列数
	protected String animName;
	protected ArrayList<Image> imgList = new ArrayList<Image>();
	protected int playInterval = 1;	//每帧贴图播放的间隔帧数,不能为0
	public int curFrame;			//当前帧数
	
	//设置动画序列和动画标识名
	public ImageSeries(String[] URL, String animName){
		for(int i = 0; i < URL.length; i++){
			//添加前检测文件是否存在
			File file = new File(URL[i]);
			if (!file.exists()) {
			    System.out.println("文件路径不存在:" + URL[i]);
			}else{
				imgList.add(Toolkit.getDefaultToolkit().getImage(URL[i]));
				System.out.println("加载贴图资源成功:" + URL[i]);
			}
		}
		this.animName = animName;
	}
	
	public String getAnimName(){
		return animName;
	}
	public void setAnimName(String animName){
		this.animName = animName;
	}
	
	public int getPlayInterval(){
		return playInterval;
	}
	public void setPlayInterval(int playInterval){
		if(playInterval != 0)
			this.playInterval = playInterval;
		else
			System.out.println("动画间隔不能为0");
	}
	
	public ArrayList<Image> getImgList(){
		return imgList;
	}
	
	/*
	public void setAnimName(String animName){
		this.animName = animName;
	}
	public String getAnimName(){
		return animName;
	}*/
}
