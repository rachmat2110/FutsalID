package FutsalID.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class booking extends Activity implements B4AActivity{
	public static booking mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "FutsalID.example", "FutsalID.example.booking");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (booking).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "FutsalID.example", "FutsalID.example.booking");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "FutsalID.example.booking", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (booking) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (booking) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return booking.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (booking) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            booking mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (booking) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnback = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbllokasi = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnpesan = null;
public static int _satoption = 0;
public static int _sunoption = 0;
public static boolean _startmonday = false;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1lv = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblpukul = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblwaktu = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltanggal = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblatasnama = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtatasnama = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlriview = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _pnl_svriview = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtemail = null;
public cm.jahswant.backmail.BackgroundMailWrapper _bgmail = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _logo = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public FutsalID.example.main _main = null;
public FutsalID.example.riview _riview = null;
public FutsalID.example.alibaba _alibaba = null;
public FutsalID.example.feed _feed = null;
public FutsalID.example.info _info = null;
public FutsalID.example.charts _charts = null;
public FutsalID.example.estadio _estadio = null;
public FutsalID.example.halim _halim = null;
public FutsalID.example.next_1 _next_1 = null;
public FutsalID.example.starter _starter = null;
public FutsalID.example.tempatfutsal _tempatfutsal = null;
public FutsalID.example.town _town = null;
public FutsalID.example.unggul _unggul = null;
public FutsalID.example.util _util = null;
public FutsalID.example.viva _viva = null;
public FutsalID.example.httputils2service _httputils2service = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 36;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 38;BA.debugLine="Activity.LoadLayout(\"Booking\")";
mostCurrent._activity.LoadLayout("Booking",mostCurrent.activityBA);
 //BA.debugLineNum = 39;BA.debugLine="lblLokasi.Text= TempatFutsal.nama";
mostCurrent._lbllokasi.setText(BA.ObjectToCharSequence(mostCurrent._tempatfutsal._nama /*String*/ ));
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
int _i = 0;
 //BA.debugLineNum = 94;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 95;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 96;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 97;BA.debugLine="i = Msgbox2(\"Yakin Ingin keluar?\", \"Info\", \"Ok\",";
_i = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("Yakin Ingin keluar?"),BA.ObjectToCharSequence("Info"),"Ok","Cancel"," ",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 98;BA.debugLine="Select i";
switch (BA.switchObjectToInt(_i,anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE,anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL)) {
case 0: {
 //BA.debugLineNum = 100;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 101;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 break; }
case 1: {
 //BA.debugLineNum = 103;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 break; }
}
;
 };
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 42;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public static String  _btnback_click() throws Exception{
 //BA.debugLineNum = 51;BA.debugLine="Sub btnback_Click";
 //BA.debugLineNum = 52;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 53;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static String  _btnpesan_click() throws Exception{
 //BA.debugLineNum = 62;BA.debugLine="Sub btnPesan_Click";
 //BA.debugLineNum = 63;BA.debugLine="Label3.Text = (GetRandomString(\"ABCDEFGHIJKLMNOPQ";
mostCurrent._label3.setText(BA.ObjectToCharSequence((_getrandomstring("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",(int) (6)))));
 //BA.debugLineNum = 64;BA.debugLine="cek(lblLokasi.Text, lblPukul.Text, lblWaktu.Text,";
_cek(mostCurrent._lbllokasi.getText(),mostCurrent._lblpukul.getText(),(int)(Double.parseDouble(mostCurrent._lblwaktu.getText())),mostCurrent._lbltanggal.getText(),mostCurrent._txtatasnama.getText(),mostCurrent._txtemail.getText(),mostCurrent._label3.getText());
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static void  _cek(String _lokasi,String _jam,int _jml_waktu,String _tanggal,String _nama,String _email,String _kode) throws Exception{
ResumableSub_cek rsub = new ResumableSub_cek(null,_lokasi,_jam,_jml_waktu,_tanggal,_nama,_email,_kode);
rsub.resume(processBA, null);
}
public static class ResumableSub_cek extends BA.ResumableSub {
public ResumableSub_cek(FutsalID.example.booking parent,String _lokasi,String _jam,int _jml_waktu,String _tanggal,String _nama,String _email,String _kode) {
this.parent = parent;
this._lokasi = _lokasi;
this._jam = _jam;
this._jml_waktu = _jml_waktu;
this._tanggal = _tanggal;
this._nama = _nama;
this._email = _email;
this._kode = _kode;
}
FutsalID.example.booking parent;
String _lokasi;
String _jam;
int _jml_waktu;
String _tanggal;
String _nama;
String _email;
String _kode;
FutsalID.example.main._dbcommand _cmd = null;
FutsalID.example.dbrequestmanager _req = null;
FutsalID.example.httpjob _j = null;
FutsalID.example.main._dbresult _res = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 150;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Bookin";
_cmd = parent.mostCurrent._util._createcommand /*FutsalID.example.main._dbcommand*/ (mostCurrent.activityBA,"BookingTest",new Object[]{(Object)(_lokasi),(Object)(_jam)});
 //BA.debugLineNum = 151;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*FutsalID.example.dbrequestmanager*/ (mostCurrent.activityBA,booking.getObject());
 //BA.debugLineNum = 152;BA.debugLine="Wait For (req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*FutsalID.example.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 19;
return;
case 19:
//C
this.state = 1;
_j = (FutsalID.example.httpjob) result[0];
;
 //BA.debugLineNum = 153;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 18;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}else {
this.state = 17;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 154;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 155;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 20;
return;
case 20:
//C
this.state = 4;
_res = (FutsalID.example.main._dbresult) result[0];
;
 //BA.debugLineNum = 156;BA.debugLine="If res.Rows.Size > 2 Then";
if (true) break;

case 4:
//if
this.state = 15;
if (_res.Rows /*anywheresoftware.b4a.objects.collections.List*/ .getSize()>2) { 
this.state = 6;
}else {
this.state = 14;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 157;BA.debugLine="ToastMessageShow(\"Lokasi pada jam sudah penuh\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Lokasi pada jam sudah penuh"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 158;BA.debugLine="If res.Rows.Size > 0 Then";
if (true) break;

case 7:
//if
this.state = 12;
if (_res.Rows /*anywheresoftware.b4a.objects.collections.List*/ .getSize()>0) { 
this.state = 9;
}else {
this.state = 11;
}if (true) break;

case 9:
//C
this.state = 12;
 //BA.debugLineNum = 159;BA.debugLine="ToastMessageShow(\"Jam sudah ada yang booking\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Jam sudah ada yang booking"),anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 161;BA.debugLine="Register(lokasi,jam,jml_waktu,tanggal,nama,ema";
_register(_lokasi,_jam,_jml_waktu,_tanggal,_nama,_email,_kode);
 if (true) break;

case 12:
//C
this.state = 15;
;
 if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 164;BA.debugLine="Register(lokasi,jam,jml_waktu,tanggal,nama,emai";
_register(_lokasi,_jam,_jml_waktu,_tanggal,_nama,_email,_kode);
 if (true) break;

case 15:
//C
this.state = 18;
;
 if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 167;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("82031634",_j._errormessage /*String*/ ,0);
 if (true) break;

case 18:
//C
this.state = -1;
;
 //BA.debugLineNum = 169;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _jobdone(FutsalID.example.httpjob _j) throws Exception{
}
public static void  _req_result(FutsalID.example.main._dbresult _res) throws Exception{
}
public static String  _getrandomstring(String _source,int _counts) throws Exception{
anywheresoftware.b4a.keywords.StringBuilderWrapper _str = null;
int _i = 0;
 //BA.debugLineNum = 172;BA.debugLine="Sub GetRandomString(Source As String,counts As Int";
 //BA.debugLineNum = 173;BA.debugLine="Dim Str As StringBuilder";
_str = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 174;BA.debugLine="Str.Initialize";
_str.Initialize();
 //BA.debugLineNum = 175;BA.debugLine="For i = 0 To counts -1";
{
final int step3 = 1;
final int limit3 = (int) (_counts-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 176;BA.debugLine="Str.Append(Source.CharAt(Rnd(0,Source.Length)))";
_str.Append(BA.ObjectToString(_source.charAt(anywheresoftware.b4a.keywords.Common.Rnd((int) (0),_source.length()))));
 }
};
 //BA.debugLineNum = 178;BA.debugLine="Return Str.ToString";
if (true) return _str.ToString();
 //BA.debugLineNum = 179;BA.debugLine="txtAtasNama.Text = Str.ToString";
mostCurrent._txtatasnama.setText(BA.ObjectToCharSequence(_str.ToString()));
 //BA.debugLineNum = 180;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private btnback As Button";
mostCurrent._btnback = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private lblLokasi As Label";
mostCurrent._lbllokasi = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private btnPesan As Button";
mostCurrent._btnpesan = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private satOption, sunOption As Int";
_satoption = 0;
_sunoption = 0;
 //BA.debugLineNum = 20;BA.debugLine="Private startMonday As Boolean";
_startmonday = false;
 //BA.debugLineNum = 21;BA.debugLine="Private ListView1LV As ListView";
mostCurrent._listview1lv = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private lblPukul As Label";
mostCurrent._lblpukul = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private lblWaktu As Label";
mostCurrent._lblwaktu = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private lblTanggal As Label";
mostCurrent._lbltanggal = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private lblAtasNama As Label";
mostCurrent._lblatasnama = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private txtAtasNama As EditText";
mostCurrent._txtatasnama = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private pnlriview As Panel";
mostCurrent._pnlriview = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private pnl_SVriview As ScrollView";
mostCurrent._pnl_svriview = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private txtemail As EditText";
mostCurrent._txtemail = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim BGMail As BackgroundMail";
mostCurrent._bgmail = new cm.jahswant.backmail.BackgroundMailWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private Logo As ImageView";
mostCurrent._logo = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public static String  _lbllokasi_click() throws Exception{
 //BA.debugLineNum = 56;BA.debugLine="Sub lblLokasi_Click";
 //BA.debugLineNum = 57;BA.debugLine="lblLokasi.Text = TempatFutsal.nama";
mostCurrent._lbllokasi.setText(BA.ObjectToCharSequence(mostCurrent._tempatfutsal._nama /*String*/ ));
 //BA.debugLineNum = 58;BA.debugLine="StartActivity(TempatFutsal)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._tempatfutsal.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public static String  _lblpukul_click() throws Exception{
anywheresoftware.b4a.agraham.dialogs.InputDialog.TimeDialog _td = null;
String _txt = "";
String _ret = "";
 //BA.debugLineNum = 67;BA.debugLine="Sub lblPukul_Click";
 //BA.debugLineNum = 68;BA.debugLine="Dim td As TimeDialog";
_td = new anywheresoftware.b4a.agraham.dialogs.InputDialog.TimeDialog();
 //BA.debugLineNum = 69;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 70;BA.debugLine="td.Hour = DateTime.GetHour(DateTime.Now)";
_td.setHour(anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 71;BA.debugLine="td.Minute = DateTime.GetMinute(DateTime.Now)";
_td.setMinute(anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 72;BA.debugLine="td.Is24Hours = True";
_td.setIs24Hours(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 73;BA.debugLine="ret = td.Show(\"Set the required time\", \"futsalID";
_ret = BA.NumberToString(_td.Show("Set the required time","futsalID Time Dialog","Yes","No","Maybe",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 74;BA.debugLine="lblPukul.Text = td.Hour & \":\" & td.Minute";
mostCurrent._lblpukul.setText(BA.ObjectToCharSequence(BA.NumberToString(_td.getHour())+":"+BA.NumberToString(_td.getMinute())));
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static String  _lbltanggal_click() throws Exception{
long _i = 0L;
FutsalID.example.datedialogs _d = null;
String _temp = "";
 //BA.debugLineNum = 77;BA.debugLine="Sub lblTanggal_Click";
 //BA.debugLineNum = 78;BA.debugLine="Dim I As Long";
_i = 0L;
 //BA.debugLineNum = 79;BA.debugLine="Dim D As DateDialogs";
_d = new FutsalID.example.datedialogs();
 //BA.debugLineNum = 81;BA.debugLine="Log(DateTime.DateFormat)";
anywheresoftware.b4a.keywords.Common.LogImpl("81769476",anywheresoftware.b4a.keywords.Common.DateTime.getDateFormat(),0);
 //BA.debugLineNum = 82;BA.debugLine="D.Initialize(Activity, DateTime.Now)";
_d._initialize /*String*/ (mostCurrent.activityBA,mostCurrent._activity,anywheresoftware.b4a.keywords.Common.DateTime.getNow());
 //BA.debugLineNum = 83;BA.debugLine="D.RedSaturday=satOption";
_d._setredsaturday(_satoption);
 //BA.debugLineNum = 84;BA.debugLine="D.RedSunday=sunOption";
_d._setredsunday(_sunoption);
 //BA.debugLineNum = 85;BA.debugLine="D.StartOnMonday=startMonday";
_d._setstartonmonday(_startmonday);
 //BA.debugLineNum = 86;BA.debugLine="I=D.Show(\"Select Date\")";
_i = (long) (_d._show /*int*/ ("Select Date"));
 //BA.debugLineNum = 87;BA.debugLine="Log(DateTime.DateFormat)";
anywheresoftware.b4a.keywords.Common.LogImpl("81769482",anywheresoftware.b4a.keywords.Common.DateTime.getDateFormat(),0);
 //BA.debugLineNum = 89;BA.debugLine="lblTanggal.Text = DateTime.GetYear(D.DateSelected";
mostCurrent._lbltanggal.setText(BA.ObjectToCharSequence(BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetYear(_d._dateselected /*long*/ ))+"-"+BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetMonth(_d._dateselected /*long*/ ))+"-"+BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfMonth(_d._dateselected /*long*/ ))));
 //BA.debugLineNum = 90;BA.debugLine="Dim temp As String = DateTime.DateFormat";
_temp = anywheresoftware.b4a.keywords.Common.DateTime.getDateFormat();
 //BA.debugLineNum = 91;BA.debugLine="DateTime.DateFormat = \"yyyy - MM - dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy - MM - dd");
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _lblwaktu_click() throws Exception{
anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog _nd = null;
Object _ret = null;
 //BA.debugLineNum = 108;BA.debugLine="Sub lblWaktu_Click";
 //BA.debugLineNum = 109;BA.debugLine="Dim nd As NumberDialog";
_nd = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 110;BA.debugLine="Dim ret As Object";
_ret = new Object();
 //BA.debugLineNum = 111;BA.debugLine="nd.Digits = 0";
_nd.setDigits((int) (0));
 //BA.debugLineNum = 112;BA.debugLine="nd.Number = 1";
_nd.setNumber((int) (1));
 //BA.debugLineNum = 113;BA.debugLine="nd.Decimal = 0";
_nd.setDecimal((int) (0));
 //BA.debugLineNum = 114;BA.debugLine="ret = nd.Show(\"Jumlah Waktu Bermain\", \"OKE\", \"\",";
_ret = (Object)(_nd.Show("Jumlah Waktu Bermain","OKE","","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 115;BA.debugLine="lblWaktu.Text = nd.Number";
mostCurrent._lblwaktu.setText(BA.ObjectToCharSequence(_nd.getNumber()));
 //BA.debugLineNum = 116;BA.debugLine="Label1.Text = \"Jam\"";
mostCurrent._label1.setText(BA.ObjectToCharSequence("Jam"));
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static void  _register(String _lokasi,String _jam,int _jml_waktu,String _tanggal,String _nama,String _email,String _kode) throws Exception{
ResumableSub_Register rsub = new ResumableSub_Register(null,_lokasi,_jam,_jml_waktu,_tanggal,_nama,_email,_kode);
rsub.resume(processBA, null);
}
public static class ResumableSub_Register extends BA.ResumableSub {
public ResumableSub_Register(FutsalID.example.booking parent,String _lokasi,String _jam,int _jml_waktu,String _tanggal,String _nama,String _email,String _kode) {
this.parent = parent;
this._lokasi = _lokasi;
this._jam = _jam;
this._jml_waktu = _jml_waktu;
this._tanggal = _tanggal;
this._nama = _nama;
this._email = _email;
this._kode = _kode;
}
FutsalID.example.booking parent;
String _lokasi;
String _jam;
int _jml_waktu;
String _tanggal;
String _nama;
String _email;
String _kode;
FutsalID.example.main._dbcommand _cmd = null;
FutsalID.example.httpjob _j = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 120;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Daftar";
_cmd = parent.mostCurrent._util._createcommand /*FutsalID.example.main._dbcommand*/ (mostCurrent.activityBA,"DaftarBooking",new Object[]{(Object)(_lokasi),(Object)(_jam),(Object)(_jml_waktu),(Object)(_tanggal),(Object)(_nama),(Object)(_email),(Object)(_kode)});
 //BA.debugLineNum = 121;BA.debugLine="Dim j As HttpJob = util.CreateRequest(Me).Execute";
_j = parent.mostCurrent._util._createrequest /*FutsalID.example.dbrequestmanager*/ (mostCurrent.activityBA,booking.getObject())._executebatch /*FutsalID.example.httpjob*/ (anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_cmd)}),anywheresoftware.b4a.keywords.Common.Null);
 //BA.debugLineNum = 122;BA.debugLine="Wait For (j) JobDone(j As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_j));
this.state = 19;
return;
case 19:
//C
this.state = 1;
_j = (FutsalID.example.httpjob) result[0];
;
 //BA.debugLineNum = 123;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 18;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}else {
this.state = 17;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 124;BA.debugLine="If txtemail.Text.Trim <> \"\" Then";
if (true) break;

case 4:
//if
this.state = 15;
if ((parent.mostCurrent._txtemail.getText().trim()).equals("") == false) { 
this.state = 6;
}else {
this.state = 14;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 125;BA.debugLine="Try";
if (true) break;

case 7:
//try
this.state = 12;
this.catchState = 11;
this.state = 9;
if (true) break;

case 9:
//C
this.state = 12;
this.catchState = 11;
 //BA.debugLineNum = 126;BA.debugLine="BGMail.Initialize(\"\")";
parent.mostCurrent._bgmail.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 127;BA.debugLine="BGMail.GmailUserName = \"rachmat2110@gmail.com\"";
parent.mostCurrent._bgmail.setGmailUserName("rachmat2110@gmail.com");
 //BA.debugLineNum = 128;BA.debugLine="BGMail.GmailPassword = \"Validasimamat123*\"";
parent.mostCurrent._bgmail.setGmailPassword("Validasimamat123*");
 //BA.debugLineNum = 129;BA.debugLine="BGMail.FormSubject = \"Pemesanan via FutsalID\"";
parent.mostCurrent._bgmail.setFormSubject("Pemesanan via FutsalID");
 //BA.debugLineNum = 130;BA.debugLine="BGMail.MailTo = txtemail.Text.Trim";
parent.mostCurrent._bgmail.setMailTo(parent.mostCurrent._txtemail.getText().trim());
 //BA.debugLineNum = 131;BA.debugLine="BGMail.FormBody= \"Pemesanan Booking Tempat Fut";
parent.mostCurrent._bgmail.setFormBody("Pemesanan Booking Tempat Futsal"+anywheresoftware.b4a.keywords.Common.CRLF+"Atas Nama : "+parent.mostCurrent._txtatasnama.getText()+anywheresoftware.b4a.keywords.Common.CRLF+"Bermain Pada Pukul : "+parent.mostCurrent._lblpukul.getText()+anywheresoftware.b4a.keywords.Common.CRLF+"Tempat pemesanan : "+parent.mostCurrent._lbllokasi.getText()+anywheresoftware.b4a.keywords.Common.CRLF+"Dengan Kode pemesanan : "+parent.mostCurrent._label3.getText());
 //BA.debugLineNum = 132;BA.debugLine="BGMail.SendingMessageSuccess = \"Sent Successfu";
parent.mostCurrent._bgmail.setSendingMessageSuccess("Sent Successfully");
 //BA.debugLineNum = 133;BA.debugLine="BGMail.ProcessVisibility = True";
parent.mostCurrent._bgmail.setProcessVisibility(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 134;BA.debugLine="BGMail.send";
parent.mostCurrent._bgmail.send();
 if (true) break;

case 11:
//C
this.state = 12;
this.catchState = 0;
 //BA.debugLineNum = 136;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("81966097",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 12:
//C
this.state = 15;
this.catchState = 0;
;
 if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 139;BA.debugLine="ToastMessageShow(\"Email Connot Be Empty\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Email Connot Be Empty"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 140;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 15:
//C
this.state = 18;
;
 //BA.debugLineNum = 142;BA.debugLine="Msgbox2(\"Pemesanan anda sudah terverifikasi\",\"";
anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("Pemesanan anda sudah terverifikasi"),BA.ObjectToCharSequence("          Notifikasi        "),"YES","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Lambang(1).png.jpg").getObject()),mostCurrent.activityBA);
 if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 144;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("81966105",_j._errormessage /*String*/ ,0);
 if (true) break;

case 18:
//C
this.state = -1;
;
 //BA.debugLineNum = 146;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
}
