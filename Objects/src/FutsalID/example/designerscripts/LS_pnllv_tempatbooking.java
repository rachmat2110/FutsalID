package FutsalID.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_pnllv_tempatbooking{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("pnljudulbookinglv").vw.setHeight((int)((45d * scale)));
views.get("imageview1").vw.setTop((int)((views.get("listview1lv").vw.getTop() + views.get("listview1lv").vw.getHeight())-(50d * scale)));
views.get("imageview1").vw.setLeft((int)((50d / 100 * width) - (views.get("imageview1").vw.getWidth() / 2)));

}
}