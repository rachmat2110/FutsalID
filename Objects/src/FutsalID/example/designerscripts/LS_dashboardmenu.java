package FutsalID.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_dashboardmenu{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("btnbar").vw.setLeft((int)((10d * scale)));
views.get("btnbar").vw.setTop((int)((10d * scale)));
views.get("lbljudul").vw.setLeft((int)((50d / 100 * width) - (views.get("lbljudul").vw.getWidth() / 2)));
views.get("lbljudul").vw.setTop((int)((75.8d * scale)));
views.get("btnbooked").vw.setLeft((int)((50d / 100 * width) - (views.get("btnbooked").vw.getWidth() / 2)));
views.get("btnbooked").vw.setTop((int)((views.get("lbljudul").vw.getTop() + views.get("lbljudul").vw.getHeight())+(43d * scale)));
views.get("lblbooking").vw.setLeft((int)((50d / 100 * width) - (views.get("lblbooking").vw.getWidth() / 2)));
views.get("lblbooking").vw.setTop((int)((views.get("btnbooked").vw.getTop() + views.get("btnbooked").vw.getHeight())+(0d * scale)-(10d * scale)));
views.get("btnriview").vw.setLeft((int)((50d / 100 * width) - (views.get("btnriview").vw.getWidth() / 2)));
views.get("btnriview").vw.setTop((int)((views.get("lblbooking").vw.getTop() + views.get("lblbooking").vw.getHeight())+(20d * scale)));
views.get("lblreview").vw.setLeft((int)((50d / 100 * width) - (views.get("lblreview").vw.getWidth() / 2)));
views.get("lblreview").vw.setTop((int)((views.get("btnriview").vw.getTop() + views.get("btnriview").vw.getHeight())+(0d * scale)-(10d * scale)));
views.get("btnvideo").vw.setLeft((int)((50d / 100 * width) - (views.get("btnvideo").vw.getWidth() / 2)));
views.get("btnvideo").vw.setTop((int)((views.get("lblreview").vw.getTop() + views.get("lblreview").vw.getHeight())+(20d * scale)));
views.get("lblvideo").vw.setLeft((int)((50d / 100 * width) - (views.get("lblvideo").vw.getWidth() / 2)));
views.get("lblvideo").vw.setTop((int)((views.get("btnvideo").vw.getTop() + views.get("btnvideo").vw.getHeight())+(0d * scale)-(10d * scale)));
views.get("label1").vw.setLeft((int)((50d / 100 * width) - (views.get("label1").vw.getWidth() / 2)));
views.get("label1").vw.setTop((int)((90d / 100 * height)));
views.get("imageview1").vw.setLeft((int)((50d / 100 * width) - (views.get("imageview1").vw.getWidth() / 2)));
views.get("imageview1").vw.setTop((int)((95d / 100 * height)));
views.get("imageview1").vw.setLeft((int)((45d / 100 * width)));
views.get("imageview1").vw.setWidth((int)((55d / 100 * width) - ((45d / 100 * width))));
views.get("imageview1").vw.setTop((int)((95d / 100 * height)));
views.get("imageview1").vw.setHeight((int)((95.5d / 100 * height) - ((95d / 100 * height))));

}
}