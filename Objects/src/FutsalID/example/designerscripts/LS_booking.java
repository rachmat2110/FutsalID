package FutsalID.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_booking{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[Booking/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 4;BA.debugLine="pnljudulBooking.Height = 45dip"[Booking/General script]
views.get("pnljudulbooking").vw.setHeight((int)((45d * scale)));
//BA.debugLineNum = 5;BA.debugLine="Logo.Top = pnljudulBooking.Bottom"[Booking/General script]
views.get("logo").vw.setTop((int)((views.get("pnljudulbooking").vw.getTop() + views.get("pnljudulbooking").vw.getHeight())));
//BA.debugLineNum = 6;BA.debugLine="Logo.HorizontalCenter = 50%x"[Booking/General script]
views.get("logo").vw.setLeft((int)((50d / 100 * width) - (views.get("logo").vw.getWidth() / 2)));
//BA.debugLineNum = 7;BA.debugLine="Logo.SetLeftAndRight (30%x,70%x)"[Booking/General script]
views.get("logo").vw.setLeft((int)((30d / 100 * width)));
views.get("logo").vw.setWidth((int)((70d / 100 * width) - ((30d / 100 * width))));
//BA.debugLineNum = 8;BA.debugLine="Logo.SetTopAndBottom (5%y,25%y)"[Booking/General script]
views.get("logo").vw.setTop((int)((5d / 100 * height)));
views.get("logo").vw.setHeight((int)((25d / 100 * height) - ((5d / 100 * height))));
//BA.debugLineNum = 11;BA.debugLine="imgLokasi.Left = 40dip"[Booking/General script]
views.get("imglokasi").vw.setLeft((int)((40d * scale)));
//BA.debugLineNum = 12;BA.debugLine="imgLokasi.Top = 206dip"[Booking/General script]
views.get("imglokasi").vw.setTop((int)((206d * scale)));
//BA.debugLineNum = 13;BA.debugLine="imgLokasi.SetLeftAndRight (7%x,16%x)"[Booking/General script]
views.get("imglokasi").vw.setLeft((int)((7d / 100 * width)));
views.get("imglokasi").vw.setWidth((int)((16d / 100 * width) - ((7d / 100 * width))));
//BA.debugLineNum = 14;BA.debugLine="imgLokasi.SetTopAndBottom (30%y,35%y)"[Booking/General script]
views.get("imglokasi").vw.setTop((int)((30d / 100 * height)));
views.get("imglokasi").vw.setHeight((int)((35d / 100 * height) - ((30d / 100 * height))));
//BA.debugLineNum = 15;BA.debugLine="pnlLokasi.SetTopAndBottom (30%y,35%y)"[Booking/General script]
views.get("pnllokasi").vw.setTop((int)((30d / 100 * height)));
views.get("pnllokasi").vw.setHeight((int)((35d / 100 * height) - ((30d / 100 * height))));
//BA.debugLineNum = 16;BA.debugLine="lblLokasi.SetTopAndBottom(-1%y,6%y)"[Booking/General script]
views.get("lbllokasi").vw.setTop((int)(0-(1d / 100 * height)));
views.get("lbllokasi").vw.setHeight((int)((6d / 100 * height) - (0-(1d / 100 * height))));
//BA.debugLineNum = 19;BA.debugLine="imgPukul.Left = 40dip"[Booking/General script]
views.get("imgpukul").vw.setLeft((int)((40d * scale)));
//BA.debugLineNum = 20;BA.debugLine="imgPukul.Top = 254dip"[Booking/General script]
views.get("imgpukul").vw.setTop((int)((254d * scale)));
//BA.debugLineNum = 21;BA.debugLine="imgPukul.SetLeftAndRight (7%x,16%x)"[Booking/General script]
views.get("imgpukul").vw.setLeft((int)((7d / 100 * width)));
views.get("imgpukul").vw.setWidth((int)((16d / 100 * width) - ((7d / 100 * width))));
//BA.debugLineNum = 22;BA.debugLine="imgPukul.SetTopAndBottom(38%y,43%y)"[Booking/General script]
views.get("imgpukul").vw.setTop((int)((38d / 100 * height)));
views.get("imgpukul").vw.setHeight((int)((43d / 100 * height) - ((38d / 100 * height))));
//BA.debugLineNum = 23;BA.debugLine="pnlPukul.SetTopAndBottom(38%y,43%y)"[Booking/General script]
views.get("pnlpukul").vw.setTop((int)((38d / 100 * height)));
views.get("pnlpukul").vw.setHeight((int)((43d / 100 * height) - ((38d / 100 * height))));
//BA.debugLineNum = 26;BA.debugLine="imgWaktu.Left = 40dip"[Booking/General script]
views.get("imgwaktu").vw.setLeft((int)((40d * scale)));
//BA.debugLineNum = 27;BA.debugLine="imgWaktu.Top = 302dip"[Booking/General script]
views.get("imgwaktu").vw.setTop((int)((302d * scale)));
//BA.debugLineNum = 28;BA.debugLine="imgWaktu.SetLeftAndRight(7%x,16%x)"[Booking/General script]
views.get("imgwaktu").vw.setLeft((int)((7d / 100 * width)));
views.get("imgwaktu").vw.setWidth((int)((16d / 100 * width) - ((7d / 100 * width))));
//BA.debugLineNum = 29;BA.debugLine="imgWaktu.SetTopAndBottom(46%y,51%y)"[Booking/General script]
views.get("imgwaktu").vw.setTop((int)((46d / 100 * height)));
views.get("imgwaktu").vw.setHeight((int)((51d / 100 * height) - ((46d / 100 * height))));
//BA.debugLineNum = 30;BA.debugLine="pnlWaktu.SetTopAndBottom(46%y,51%y)"[Booking/General script]
views.get("pnlwaktu").vw.setTop((int)((46d / 100 * height)));
views.get("pnlwaktu").vw.setHeight((int)((51d / 100 * height) - ((46d / 100 * height))));
//BA.debugLineNum = 33;BA.debugLine="imgTanggal.Left = 40dip"[Booking/General script]
views.get("imgtanggal").vw.setLeft((int)((40d * scale)));
//BA.debugLineNum = 34;BA.debugLine="imgTanggal.Top = 349dip"[Booking/General script]
views.get("imgtanggal").vw.setTop((int)((349d * scale)));
//BA.debugLineNum = 35;BA.debugLine="imgTanggal.SetLeftAndRight(7%x,16%x)"[Booking/General script]
views.get("imgtanggal").vw.setLeft((int)((7d / 100 * width)));
views.get("imgtanggal").vw.setWidth((int)((16d / 100 * width) - ((7d / 100 * width))));
//BA.debugLineNum = 36;BA.debugLine="imgTanggal.SetTopAndBottom(54%y,59%y)"[Booking/General script]
views.get("imgtanggal").vw.setTop((int)((54d / 100 * height)));
views.get("imgtanggal").vw.setHeight((int)((59d / 100 * height) - ((54d / 100 * height))));
//BA.debugLineNum = 37;BA.debugLine="pnlTanggal.SetTopAndBottom(54%y,59%y)"[Booking/General script]
views.get("pnltanggal").vw.setTop((int)((54d / 100 * height)));
views.get("pnltanggal").vw.setHeight((int)((59d / 100 * height) - ((54d / 100 * height))));
//BA.debugLineNum = 40;BA.debugLine="imgAtasNama.Left = 40dip"[Booking/General script]
views.get("imgatasnama").vw.setLeft((int)((40d * scale)));
//BA.debugLineNum = 41;BA.debugLine="imgAtasNama.Top = 401dip"[Booking/General script]
views.get("imgatasnama").vw.setTop((int)((401d * scale)));
//BA.debugLineNum = 42;BA.debugLine="imgAtasNama.SetLeftAndRight(7%x,16%x)"[Booking/General script]
views.get("imgatasnama").vw.setLeft((int)((7d / 100 * width)));
views.get("imgatasnama").vw.setWidth((int)((16d / 100 * width) - ((7d / 100 * width))));
//BA.debugLineNum = 43;BA.debugLine="imgAtasNama.SetTopAndBottom(62%y,67%y)"[Booking/General script]
views.get("imgatasnama").vw.setTop((int)((62d / 100 * height)));
views.get("imgatasnama").vw.setHeight((int)((67d / 100 * height) - ((62d / 100 * height))));
//BA.debugLineNum = 44;BA.debugLine="pnlAtasNama.SetTopAndBottom(62%y,67%y)"[Booking/General script]
views.get("pnlatasnama").vw.setTop((int)((62d / 100 * height)));
views.get("pnlatasnama").vw.setHeight((int)((67d / 100 * height) - ((62d / 100 * height))));
//BA.debugLineNum = 45;BA.debugLine="txtAtasNama.SetTopAndBottom(-2.5%y,8%y)"[Booking/General script]
views.get("txtatasnama").vw.setTop((int)(0-(2.5d / 100 * height)));
views.get("txtatasnama").vw.setHeight((int)((8d / 100 * height) - (0-(2.5d / 100 * height))));
//BA.debugLineNum = 47;BA.debugLine="imgEmail.Left = 40dip"[Booking/General script]
views.get("imgemail").vw.setLeft((int)((40d * scale)));
//BA.debugLineNum = 48;BA.debugLine="imgEmail.Top = 401dip"[Booking/General script]
views.get("imgemail").vw.setTop((int)((401d * scale)));
//BA.debugLineNum = 49;BA.debugLine="imgEmail.SetLeftAndRight(7%x,15%x)"[Booking/General script]
views.get("imgemail").vw.setLeft((int)((7d / 100 * width)));
views.get("imgemail").vw.setWidth((int)((15d / 100 * width) - ((7d / 100 * width))));
//BA.debugLineNum = 50;BA.debugLine="imgEmail.SetTopAndBottom(70%y,75%y)"[Booking/General script]
views.get("imgemail").vw.setTop((int)((70d / 100 * height)));
views.get("imgemail").vw.setHeight((int)((75d / 100 * height) - ((70d / 100 * height))));
//BA.debugLineNum = 51;BA.debugLine="pnlEmail.SetTopAndBottom(70%y,75%y)"[Booking/General script]
views.get("pnlemail").vw.setTop((int)((70d / 100 * height)));
views.get("pnlemail").vw.setHeight((int)((75d / 100 * height) - ((70d / 100 * height))));
//BA.debugLineNum = 52;BA.debugLine="txtemail.SetTopAndBottom(-2.5%y,8%y)"[Booking/General script]
views.get("txtemail").vw.setTop((int)(0-(2.5d / 100 * height)));
views.get("txtemail").vw.setHeight((int)((8d / 100 * height) - (0-(2.5d / 100 * height))));
//BA.debugLineNum = 54;BA.debugLine="btnPesan.Top = pnlEmail.Bottom + 38dip"[Booking/General script]
views.get("btnpesan").vw.setTop((int)((views.get("pnlemail").vw.getTop() + views.get("pnlemail").vw.getHeight())+(38d * scale)));
//BA.debugLineNum = 55;BA.debugLine="btnPesan.Left = 27dip"[Booking/General script]
views.get("btnpesan").vw.setLeft((int)((27d * scale)));
//BA.debugLineNum = 56;BA.debugLine="btnPesan.SetLeftAndRight(8%x,88%x)"[Booking/General script]
views.get("btnpesan").vw.setLeft((int)((8d / 100 * width)));
views.get("btnpesan").vw.setWidth((int)((88d / 100 * width) - ((8d / 100 * width))));

}
}