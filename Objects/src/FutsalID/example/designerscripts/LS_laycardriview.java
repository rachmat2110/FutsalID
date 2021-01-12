package FutsalID.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_laycardriview{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("pnlalibaba").vw.setTop((int)((5d * scale)));
views.get("pnlalibaba").vw.setLeft((int)((15d * scale)));
views.get("pnlalibaba").vw.setWidth((int)((45d / 100 * width)));
views.get("pnlalibaba").vw.setHeight((int)((37.5d / 100 * height)));
views.get("imageview1").vw.setTop((int)((2d * scale)));
views.get("imageview1").vw.setWidth((int)((43d / 100 * width)));
views.get("imageview1").vw.setHeight((int)((105d * scale)));
views.get("label1").vw.setLeft((int)((24d / 100 * width) - (views.get("label1").vw.getWidth() / 2)));
views.get("label1").vw.setTop((int)((views.get("imageview1").vw.getTop() + views.get("imageview1").vw.getHeight())));
views.get("label2").vw.setLeft((int)((24d / 100 * width) - (views.get("label2").vw.getWidth() / 2)));
views.get("label2").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())-(8d * scale)));
views.get("btn_riviewalibaba").vw.setLeft((int)((24d / 100 * width) - (views.get("btn_riviewalibaba").vw.getWidth() / 2)));
views.get("btn_riviewalibaba").vw.setTop((int)((views.get("label2").vw.getTop() + views.get("label2").vw.getHeight())));
views.get("pnltown").vw.setLeft((int)((views.get("pnlalibaba").vw.getLeft() + views.get("pnlalibaba").vw.getWidth())+(10d * scale)));
views.get("pnltown").vw.setTop((int)((5d * scale)));
views.get("pnltown").vw.setWidth((int)((45d / 100 * width)));
views.get("pnltown").vw.setHeight((int)((37.5d / 100 * height)));
views.get("imageview2").vw.setTop((int)((4d * scale)));
views.get("imageview2").vw.setLeft((int)((6d * scale)));
views.get("imageview2").vw.setWidth((int)((41.5d / 100 * width)));
views.get("imageview2").vw.setHeight((int)((101d * scale)));
views.get("label3").vw.setLeft((int)((24d / 100 * width) - (views.get("label3").vw.getWidth() / 2)));
views.get("label3").vw.setTop((int)((views.get("imageview2").vw.getTop() + views.get("imageview2").vw.getHeight())));
views.get("label4").vw.setLeft((int)((24d / 100 * width) - (views.get("label4").vw.getWidth() / 2)));
views.get("label4").vw.setTop((int)((views.get("label3").vw.getTop() + views.get("label3").vw.getHeight())-(8d * scale)));
views.get("btn_riviewtown").vw.setLeft((int)((24d / 100 * width) - (views.get("btn_riviewtown").vw.getWidth() / 2)));
views.get("btn_riviewtown").vw.setTop((int)((views.get("label4").vw.getTop() + views.get("label4").vw.getHeight())));
views.get("pnlestadio").vw.setTop((int)((views.get("pnlalibaba").vw.getTop() + views.get("pnlalibaba").vw.getHeight())+(15d * scale)));
views.get("pnlestadio").vw.setLeft((int)((15d * scale)));
views.get("pnlestadio").vw.setWidth((int)((45d / 100 * width)));
views.get("pnlestadio").vw.setHeight((int)((37.5d / 100 * height)));
views.get("imageview3").vw.setTop((int)((4d * scale)));
views.get("imageview3").vw.setLeft((int)((6d * scale)));
views.get("imageview3").vw.setWidth((int)((41.5d / 100 * width)));
views.get("imageview3").vw.setHeight((int)((101d * scale)));
views.get("label5").vw.setLeft((int)((24d / 100 * width) - (views.get("label5").vw.getWidth() / 2)));
views.get("label5").vw.setTop((int)((views.get("imageview3").vw.getTop() + views.get("imageview3").vw.getHeight())));
views.get("label6").vw.setLeft((int)((24d / 100 * width) - (views.get("label6").vw.getWidth() / 2)));
views.get("label6").vw.setTop((int)((views.get("label5").vw.getTop() + views.get("label5").vw.getHeight())-(8d * scale)+(2d * scale)));
views.get("label6").vw.setLeft((int)((25d / 100 * width)/2d-(1d / 100 * width) - (views.get("label6").vw.getWidth() / 2)));
//BA.debugLineNum = 46;BA.debugLine="Label6.Width = 50%x"[layCardRiview/General script]
views.get("label6").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 47;BA.debugLine="btn_riviewEstadio.HorizontalCenter = 24%x"[layCardRiview/General script]
views.get("btn_riviewestadio").vw.setLeft((int)((24d / 100 * width) - (views.get("btn_riviewestadio").vw.getWidth() / 2)));
//BA.debugLineNum = 48;BA.debugLine="btn_riviewEstadio.Top = Label6.Bottom"[layCardRiview/General script]
views.get("btn_riviewestadio").vw.setTop((int)((views.get("label6").vw.getTop() + views.get("label6").vw.getHeight())));
//BA.debugLineNum = 50;BA.debugLine="pnlHalim.Left = pnlEstadio.Right + 10dip"[layCardRiview/General script]
views.get("pnlhalim").vw.setLeft((int)((views.get("pnlestadio").vw.getLeft() + views.get("pnlestadio").vw.getWidth())+(10d * scale)));
//BA.debugLineNum = 51;BA.debugLine="pnlHalim.Top = pnlTown.Bottom + 15dip"[layCardRiview/General script]
views.get("pnlhalim").vw.setTop((int)((views.get("pnltown").vw.getTop() + views.get("pnltown").vw.getHeight())+(15d * scale)));
//BA.debugLineNum = 52;BA.debugLine="pnlHalim.Width = 45%x"[layCardRiview/General script]
views.get("pnlhalim").vw.setWidth((int)((45d / 100 * width)));
//BA.debugLineNum = 53;BA.debugLine="pnlHalim.Height = 37.5%y"[layCardRiview/General script]
views.get("pnlhalim").vw.setHeight((int)((37.5d / 100 * height)));
//BA.debugLineNum = 54;BA.debugLine="ImageView4.Top = 4dip"[layCardRiview/General script]
views.get("imageview4").vw.setTop((int)((4d * scale)));
//BA.debugLineNum = 55;BA.debugLine="ImageView4.Left = 6dip"[layCardRiview/General script]
views.get("imageview4").vw.setLeft((int)((6d * scale)));
//BA.debugLineNum = 56;BA.debugLine="ImageView4.Width = 41.5%x"[layCardRiview/General script]
views.get("imageview4").vw.setWidth((int)((41.5d / 100 * width)));
//BA.debugLineNum = 57;BA.debugLine="ImageView4.Height = 101dip"[layCardRiview/General script]
views.get("imageview4").vw.setHeight((int)((101d * scale)));
//BA.debugLineNum = 58;BA.debugLine="Label7.HorizontalCenter = 24%x"[layCardRiview/General script]
views.get("label7").vw.setLeft((int)((24d / 100 * width) - (views.get("label7").vw.getWidth() / 2)));
//BA.debugLineNum = 59;BA.debugLine="Label7.Top = ImageView4.Bottom"[layCardRiview/General script]
views.get("label7").vw.setTop((int)((views.get("imageview4").vw.getTop() + views.get("imageview4").vw.getHeight())));
//BA.debugLineNum = 60;BA.debugLine="Label8.Top = Label7.Bottom - 8dip + 2dip"[layCardRiview/General script]
views.get("label8").vw.setTop((int)((views.get("label7").vw.getTop() + views.get("label7").vw.getHeight())-(8d * scale)+(2d * scale)));
//BA.debugLineNum = 61;BA.debugLine="Label8.HorizontalCenter = 25%x/2"[layCardRiview/General script]
views.get("label8").vw.setLeft((int)((25d / 100 * width)/2d - (views.get("label8").vw.getWidth() / 2)));
//BA.debugLineNum = 62;BA.debugLine="Label8.Width = 50%x"[layCardRiview/General script]
views.get("label8").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 63;BA.debugLine="btn_riviewHalim.HorizontalCenter = 24%x"[layCardRiview/General script]
views.get("btn_riviewhalim").vw.setLeft((int)((24d / 100 * width) - (views.get("btn_riviewhalim").vw.getWidth() / 2)));
//BA.debugLineNum = 64;BA.debugLine="btn_riviewHalim.Top = Label8.Bottom"[layCardRiview/General script]
views.get("btn_riviewhalim").vw.setTop((int)((views.get("label8").vw.getTop() + views.get("label8").vw.getHeight())));
//BA.debugLineNum = 67;BA.debugLine="pnlNext.Top = pnlEstadio.Bottom + 15dip"[layCardRiview/General script]
views.get("pnlnext").vw.setTop((int)((views.get("pnlestadio").vw.getTop() + views.get("pnlestadio").vw.getHeight())+(15d * scale)));
//BA.debugLineNum = 68;BA.debugLine="pnlNext.Left = 15dip"[layCardRiview/General script]
views.get("pnlnext").vw.setLeft((int)((15d * scale)));
//BA.debugLineNum = 69;BA.debugLine="pnlNext.Width = 45%x"[layCardRiview/General script]
views.get("pnlnext").vw.setWidth((int)((45d / 100 * width)));
//BA.debugLineNum = 70;BA.debugLine="pnlNext.Height = 37.5%y"[layCardRiview/General script]
views.get("pnlnext").vw.setHeight((int)((37.5d / 100 * height)));
//BA.debugLineNum = 71;BA.debugLine="ImageView5.Top = 4dip"[layCardRiview/General script]
views.get("imageview5").vw.setTop((int)((4d * scale)));
//BA.debugLineNum = 72;BA.debugLine="ImageView5.Left = 6dip"[layCardRiview/General script]
views.get("imageview5").vw.setLeft((int)((6d * scale)));
//BA.debugLineNum = 73;BA.debugLine="ImageView5.Width = 41.5%x"[layCardRiview/General script]
views.get("imageview5").vw.setWidth((int)((41.5d / 100 * width)));
//BA.debugLineNum = 74;BA.debugLine="ImageView5.Height = 101dip"[layCardRiview/General script]
views.get("imageview5").vw.setHeight((int)((101d * scale)));
//BA.debugLineNum = 75;BA.debugLine="Label9.HorizontalCenter = 24%x"[layCardRiview/General script]
views.get("label9").vw.setLeft((int)((24d / 100 * width) - (views.get("label9").vw.getWidth() / 2)));
//BA.debugLineNum = 76;BA.debugLine="Label9.Top = ImageView5.Bottom"[layCardRiview/General script]
views.get("label9").vw.setTop((int)((views.get("imageview5").vw.getTop() + views.get("imageview5").vw.getHeight())));
//BA.debugLineNum = 77;BA.debugLine="Label10.HorizontalCenter = 24%x"[layCardRiview/General script]
views.get("label10").vw.setLeft((int)((24d / 100 * width) - (views.get("label10").vw.getWidth() / 2)));
//BA.debugLineNum = 78;BA.debugLine="Label10.Top = Label9.Bottom - 8dip + 2dip"[layCardRiview/General script]
views.get("label10").vw.setTop((int)((views.get("label9").vw.getTop() + views.get("label9").vw.getHeight())-(8d * scale)+(2d * scale)));
//BA.debugLineNum = 79;BA.debugLine="Label10.HorizontalCenter = 25%x/2 - 1%x"[layCardRiview/General script]
views.get("label10").vw.setLeft((int)((25d / 100 * width)/2d-(1d / 100 * width) - (views.get("label10").vw.getWidth() / 2)));
//BA.debugLineNum = 80;BA.debugLine="Label10.Width = 50%x"[layCardRiview/General script]
views.get("label10").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 81;BA.debugLine="btn_riviewNext.HorizontalCenter = 24%x"[layCardRiview/General script]
views.get("btn_riviewnext").vw.setLeft((int)((24d / 100 * width) - (views.get("btn_riviewnext").vw.getWidth() / 2)));
//BA.debugLineNum = 82;BA.debugLine="btn_riviewNext.Top = Label10.Bottom"[layCardRiview/General script]
views.get("btn_riviewnext").vw.setTop((int)((views.get("label10").vw.getTop() + views.get("label10").vw.getHeight())));
//BA.debugLineNum = 84;BA.debugLine="pnlUnggul.Left = pnlNext.Right + 10dip"[layCardRiview/General script]
views.get("pnlunggul").vw.setLeft((int)((views.get("pnlnext").vw.getLeft() + views.get("pnlnext").vw.getWidth())+(10d * scale)));
//BA.debugLineNum = 85;BA.debugLine="pnlUnggul.Top = pnlHalim.Bottom + 15dip"[layCardRiview/General script]
views.get("pnlunggul").vw.setTop((int)((views.get("pnlhalim").vw.getTop() + views.get("pnlhalim").vw.getHeight())+(15d * scale)));
//BA.debugLineNum = 86;BA.debugLine="pnlUnggul.Width = 45%x"[layCardRiview/General script]
views.get("pnlunggul").vw.setWidth((int)((45d / 100 * width)));
//BA.debugLineNum = 87;BA.debugLine="pnlUnggul.Height = 37.5%y"[layCardRiview/General script]
views.get("pnlunggul").vw.setHeight((int)((37.5d / 100 * height)));
//BA.debugLineNum = 88;BA.debugLine="ImageView6.Top = 4dip"[layCardRiview/General script]
views.get("imageview6").vw.setTop((int)((4d * scale)));
//BA.debugLineNum = 89;BA.debugLine="ImageView6.Left = 6dip"[layCardRiview/General script]
views.get("imageview6").vw.setLeft((int)((6d * scale)));
//BA.debugLineNum = 90;BA.debugLine="ImageView6.Width = 41.5%x"[layCardRiview/General script]
views.get("imageview6").vw.setWidth((int)((41.5d / 100 * width)));
//BA.debugLineNum = 91;BA.debugLine="ImageView6.Height = 101dip"[layCardRiview/General script]
views.get("imageview6").vw.setHeight((int)((101d * scale)));
//BA.debugLineNum = 92;BA.debugLine="Label11.HorizontalCenter = 24%x"[layCardRiview/General script]
views.get("label11").vw.setLeft((int)((24d / 100 * width) - (views.get("label11").vw.getWidth() / 2)));
//BA.debugLineNum = 93;BA.debugLine="Label11.Top = ImageView6.Bottom"[layCardRiview/General script]
views.get("label11").vw.setTop((int)((views.get("imageview6").vw.getTop() + views.get("imageview6").vw.getHeight())));
//BA.debugLineNum = 94;BA.debugLine="Label12.Top = Label11.Bottom - 8dip + 2dip"[layCardRiview/General script]
views.get("label12").vw.setTop((int)((views.get("label11").vw.getTop() + views.get("label11").vw.getHeight())-(8d * scale)+(2d * scale)));
//BA.debugLineNum = 95;BA.debugLine="Label12.HorizontalCenter = 25%x/2"[layCardRiview/General script]
views.get("label12").vw.setLeft((int)((25d / 100 * width)/2d - (views.get("label12").vw.getWidth() / 2)));
//BA.debugLineNum = 96;BA.debugLine="Label12.Width = 50%x"[layCardRiview/General script]
views.get("label12").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 97;BA.debugLine="btn_riviewUnggul.HorizontalCenter = 24%x"[layCardRiview/General script]
views.get("btn_riviewunggul").vw.setLeft((int)((24d / 100 * width) - (views.get("btn_riviewunggul").vw.getWidth() / 2)));
//BA.debugLineNum = 98;BA.debugLine="btn_riviewUnggul.Top = Label12.Bottom"[layCardRiview/General script]
views.get("btn_riviewunggul").vw.setTop((int)((views.get("label12").vw.getTop() + views.get("label12").vw.getHeight())));
//BA.debugLineNum = 100;BA.debugLine="pnlViva.HorizontalCenter = 50%x - 1%x"[layCardRiview/General script]
views.get("pnlviva").vw.setLeft((int)((50d / 100 * width)-(1d / 100 * width) - (views.get("pnlviva").vw.getWidth() / 2)));
//BA.debugLineNum = 101;BA.debugLine="pnlViva.Top = pnlNext.Bottom + 15dip"[layCardRiview/General script]
views.get("pnlviva").vw.setTop((int)((views.get("pnlnext").vw.getTop() + views.get("pnlnext").vw.getHeight())+(15d * scale)));
//BA.debugLineNum = 102;BA.debugLine="pnlViva.Width = 45%x"[layCardRiview/General script]
views.get("pnlviva").vw.setWidth((int)((45d / 100 * width)));
//BA.debugLineNum = 103;BA.debugLine="pnlViva.Height = 37.5%y"[layCardRiview/General script]
views.get("pnlviva").vw.setHeight((int)((37.5d / 100 * height)));
//BA.debugLineNum = 104;BA.debugLine="ImageView7.Top = 4dip"[layCardRiview/General script]
views.get("imageview7").vw.setTop((int)((4d * scale)));
//BA.debugLineNum = 105;BA.debugLine="ImageView7.Left = 6dip"[layCardRiview/General script]
views.get("imageview7").vw.setLeft((int)((6d * scale)));
//BA.debugLineNum = 106;BA.debugLine="ImageView7.Width = 41.5%x"[layCardRiview/General script]
views.get("imageview7").vw.setWidth((int)((41.5d / 100 * width)));
//BA.debugLineNum = 107;BA.debugLine="ImageView7.Height = 101dip"[layCardRiview/General script]
views.get("imageview7").vw.setHeight((int)((101d * scale)));
//BA.debugLineNum = 108;BA.debugLine="Label13.HorizontalCenter = 24%x"[layCardRiview/General script]
views.get("label13").vw.setLeft((int)((24d / 100 * width) - (views.get("label13").vw.getWidth() / 2)));
//BA.debugLineNum = 109;BA.debugLine="Label13.Top = ImageView7.Bottom"[layCardRiview/General script]
views.get("label13").vw.setTop((int)((views.get("imageview7").vw.getTop() + views.get("imageview7").vw.getHeight())));
//BA.debugLineNum = 110;BA.debugLine="Label14.Top = Label13.Bottom - 8dip + 2dip"[layCardRiview/General script]
views.get("label14").vw.setTop((int)((views.get("label13").vw.getTop() + views.get("label13").vw.getHeight())-(8d * scale)+(2d * scale)));
//BA.debugLineNum = 111;BA.debugLine="Label14.HorizontalCenter = 25%x/2"[layCardRiview/General script]
views.get("label14").vw.setLeft((int)((25d / 100 * width)/2d - (views.get("label14").vw.getWidth() / 2)));
//BA.debugLineNum = 112;BA.debugLine="Label14.Width = 50%x"[layCardRiview/General script]
views.get("label14").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 113;BA.debugLine="btn_riviewViva.HorizontalCenter = 24%x"[layCardRiview/General script]
views.get("btn_riviewviva").vw.setLeft((int)((24d / 100 * width) - (views.get("btn_riviewviva").vw.getWidth() / 2)));
//BA.debugLineNum = 114;BA.debugLine="btn_riviewViva.Top = Label14.Bottom"[layCardRiview/General script]
views.get("btn_riviewviva").vw.setTop((int)((views.get("label14").vw.getTop() + views.get("label14").vw.getHeight())));

}
}