/**
 * 
 */
package GameFrame;

import java.awt.Image;
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
public class ImageContainer {
	private ArrayList<ImageSeries> imgList = new ArrayList<ImageSeries>();
	
	public ImageContainer(){
		super();
	}
	
	public void addAnimImages(String[] URL, String animName){
		ImageSeries is = new ImageSeries(URL, animName);
		imgList.add(is);
	}
	
	public ImageSeries getAnim(String animName){
		for(int i = 0; i < imgList.size(); i++){
			if(imgList.get(i).animName.equals(animName))
				return imgList.get(i);
		}
		return null;
	}
	
}
