package FutsalID.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_pnl_svinfo{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("imageview1").vw.setTop((int)((5d * scale)));
views.get("imageview1").vw.setLeft((int)((48d / 100 * width) - (views.get("imageview1").vw.getWidth() / 2)));
views.get("imageview1").vw.setWidth((int)((150d * scale)));
views.get("imageview1").vw.setHeight((int)((200d * scale)));
views.get("label1").vw.setTop((int)((views.get("imageview1").vw.getTop() + views.get("imageview1").vw.getHeight())));
views.get("label1").vw.setLeft((int)((50d / 100 * width) - (views.get("label1").vw.getWidth() / 2)));
views.get("sv_info").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())));
views.get("sv_info").vw.setHeight((int)((470d * scale)));
views.get("buttonwa").vw.setHeight((int)((50d * scale)));
views.get("buttonwa").vw.setLeft((int)((50d / 100 * width) - (views.get("buttonwa").vw.getWidth() / 2)));
views.get("buttonwa").vw.setTop((int)((views.get("sv_info").vw.getTop() + views.get("sv_info").vw.getHeight())+(10d * scale)));

}
}