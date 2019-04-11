package zuma;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Shape3D;

import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 *
 *
 *  @version ： 1.1
 *  
 *  @author  ： 苏若年         <a href="mailto:DennisIT@163.com">发送邮件</a>
 *    
 *  @since   ： 1.0      创建时间:    2013-5-6 下午02:57:47
 *     
 *  TODO     : 
 *
 */
public class LineShape extends Shape3D{

    // 直线的定点坐标
    private float vert[ ]={
                0.5f,0.5f,0.0f, -0.5f,0.5f,0.0f,
                0.3f,0.0f,0.0f, -0.3f,0.0f,0.0f,
                -0.5f,-0.5f,0.0f, 0.5f,-0.5f,0.0f};
    // 各定点的颜色
    private float color[ ]={
               0.0f,0.5f,1.0f,  0.0f,0.5f,1.0f,
               0.0f,0.8f,2.0f,  1.0f,0.0f,0.3f,
               0.0f,1.0f,0.3f,  0.3f,0.8f,0.0f};
    
    public LineShape( ) {
        // 创建直线数组对象
        LineArray line=new LineArray(6,LineArray.COORDINATES|LineArray.COLOR_3);
        // 设置直线对象的坐标数组
        line.setCoordinates(0,vert);
        // 设置直线对象的颜色数组
        line.setColors(0,color);
        // 创建直线属性对象
        LineAttributes linea=new LineAttributes( );
        // 设置线宽
        linea.setLineWidth(10.0f);
        // 设置直线的渲染效果
        linea.setLineAntialiasingEnable(true);
 
        Appearance app=new Appearance( );  
        app.setLineAttributes(linea);
        this.setGeometry(line);
        this.setAppearance(app);
    }
    public static void main(String[] args) {
    	 // 创建一个虚拟空间
        SimpleUniverse universe = new  SimpleUniverse();
        // 创建一个用来包含对象的数据结构
        BranchGroup group = new BranchGroup();
        // 创建直线形状对象把它加入到group中
        Shape3D shape=new LineShape();
        group.addChild(shape);
        universe.getViewingPlatform().setNominalViewingTransform();
        // 把group加入到虚拟空间中
        universe.addBranchGraph(group);
	}
}