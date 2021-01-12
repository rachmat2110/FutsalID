package FutsalID.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_page1unggul{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("imgpukul").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())-(40d * scale)));
views.get("lblpukul").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())-(40d * scale)));
views.get("lblpukul2").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())-(40d * scale)));
views.get("lblisi").vw.setWidth((int)((290d * scale)));
views.get("imgpukul").vw.setLeft((int)((30d * scale)));
views.get("imgpukul").vw.setWidth((int)((35d * scale)));
views.get("imgpukul").vw.setHeight((int)((35d * scale)));
views.get("lblpukul").vw.setLeft((int)((views.get("imgpukul").vw.getLeft() + views.get("imgpukul").vw.getWidth())+(15d * scale)));
views.get("lblpukul").vw.setHeight((int)((35d * scale)));
views.get("lblpukul2").vw.setLeft((int)((views.get("lblpukul").vw.getLeft() + views.get("lblpukul").vw.getWidth())+(10d * scale)));
views.get("lblpukul2").vw.setHeight((int)((35d * scale)));
views.get("imglokasi").vw.setTop((int)((views.get("imgpukul").vw.getTop() + views.get("imgpukul").vw.getHeight())+(10d * scale)));
views.get("imglokasi").vw.setLeft((int)((30d * scale)));
views.get("imglokasi").vw.setWidth((int)((35d * scale)));
views.get("imglokasi").vw.setHeight((int)((35d * scale)));
views.get("lbllokasi").vw.setTop((int)((views.get("lblpukul").vw.getTop() + views.get("lblpukul").vw.getHeight())+(8d * scale)));
views.get("lbllokasi").vw.setLeft((int)((views.get("imglokasi").vw.getLeft() + views.get("imglokasi").vw.getWidth())+(15d * scale)));
views.get("lbllokasi").vw.setHeight((int)((55d * scale)));
views.get("lbllokasi").vw.setWidth((int)((280d * scale)));
views.get("mapfragment6").vw.setTop((int)((views.get("lbllokasi").vw.getTop() + views.get("lbllokasi").vw.getHeight())+(5d * scale)));
views.get("label1").vw.setTop((int)((views.get("btnpetaunggul").vw.getTop() + views.get("btnpetaunggul").vw.getHeight())-(15d * scale)));
views.get("label1").vw.setLeft((int)((50d / 100 * width)-(2d * scale) - (views.get("label1").vw.getWidth() / 2)));
views.get("btnpetaunggul").vw.setLeft((int)((50d / 100 * width) - (views.get("btnpetaunggul").vw.getWidth() / 2)));
views.get("btnpetaunggul").vw.setWidth((int)((49.5d * scale)));
views.get("btnpetaunggul").vw.setHeight((int)((49.5d * scale)));
views.get("btnpetaunggul").vw.setTop((int)((views.get("lblisi").vw.getTop() + views.get("lblisi").vw.getHeight())+(15d * scale)));
views.get("label1").vw.setTop((int)((views.get("btnpetaunggul").vw.getTop() + views.get("btnpetaunggul").vw.getHeight())-(10d * scale)));

}
}