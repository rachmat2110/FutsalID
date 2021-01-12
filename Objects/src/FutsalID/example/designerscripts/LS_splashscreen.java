package FutsalID.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_splashscreen{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("imageview1").vw.setLeft((int)((50d / 100 * width) - (views.get("imageview1").vw.getWidth() / 2)));
views.get("imageview1").vw.setTop((int)((45d / 100 * height) - (views.get("imageview1").vw.getHeight() / 2)));
views.get("labeljdl").vw.setTop((int)((views.get("imageview1").vw.getTop() + views.get("imageview1").vw.getHeight())-35d));
views.get("labeljdl").vw.setLeft((int)((50d / 100 * width) - (views.get("labeljdl").vw.getWidth() / 2)));

}
}