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

public class info extends Activity implements B4AActivity{
	public static info mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "FutsalID.example", "FutsalID.example.info");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (info).");
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
		activityBA = new BA(this, layout, processBA, "FutsalID.example", "FutsalID.example.info");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "FutsalID.example.info", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (info) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (info) Resume **");
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
		return info.class;
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
        BA.LogInfo("** Activity (info) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            info mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (info) Resume **");
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
public static String _judul = "";
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _sv_info = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnldetail = null;
public anywheresoftware.b4a.objects.ButtonWrapper _buttonwa = null;
public FutsalID.example.main _main = null;
public FutsalID.example.booking _booking = null;
public FutsalID.example.riview _riview = null;
public FutsalID.example.alibaba _alibaba = null;
public FutsalID.example.feed _feed = null;
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
 //BA.debugLineNum = 25;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 27;BA.debugLine="Activity.LoadLayout(\"pnl_SVInfo\")";
mostCurrent._activity.LoadLayout("pnl_SVInfo",mostCurrent.activityBA);
 //BA.debugLineNum = 28;BA.debugLine="SV_Info.Panel.LoadLayout(\"layInfo\")";
mostCurrent._sv_info.getPanel().LoadLayout("layInfo",mostCurrent.activityBA);
 //BA.debugLineNum = 29;BA.debugLine="SV_Info.Panel.Height = pnlDetail.Height";
mostCurrent._sv_info.getPanel().setHeight(mostCurrent._pnldetail.getHeight());
 //BA.debugLineNum = 30;BA.debugLine="SV_Info.Panel.Width = pnlDetail.Width";
mostCurrent._sv_info.getPanel().setWidth(mostCurrent._pnldetail.getWidth());
 //BA.debugLineNum = 31;BA.debugLine="FeedTest(judul)";
_feedtest(_judul);
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 39;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 35;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return "";
}
public static String  _buttonwa_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _in3 = null;
anywheresoftware.b4j.object.JavaObject _job = null;
 //BA.debugLineNum = 62;BA.debugLine="Sub ButtonWA_Click";
 //BA.debugLineNum = 63;BA.debugLine="Try";
try { //BA.debugLineNum = 64;BA.debugLine="Dim in3 As Intent";
_in3 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 65;BA.debugLine="in3.Initialize(in3.ACTION_VIEW, \"https://api.wha";
_in3.Initialize(_in3.ACTION_VIEW,"https://api.whatsapp.com/send?phone=6282111316385&text=Hallo Min%20Saya Mau Menanyakan Perihal Tournament yang terdapat pada Aplikasi Futsal.ID %20Termakasih");
 //BA.debugLineNum = 66;BA.debugLine="Dim job As JavaObject = in3";
_job = new anywheresoftware.b4j.object.JavaObject();
_job.setObject((java.lang.Object)(_in3.getObject()));
 //BA.debugLineNum = 67;BA.debugLine="job.RunMethod(\"setPackage\", Array(\"com.whatsapp\"";
_job.RunMethod("setPackage",new Object[]{(Object)("com.whatsapp")});
 //BA.debugLineNum = 68;BA.debugLine="StartActivity(in3)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_in3.getObject()));
 //BA.debugLineNum = 69;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 } 
       catch (Exception e9) {
			processBA.setLastException(e9); //BA.debugLineNum = 71;BA.debugLine="Msgbox(\"Whatsapp Application is not Found !!\",\"W";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Whatsapp Application is not Found !!"),BA.ObjectToCharSequence("Warning"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 73;BA.debugLine="End Sub";
return "";
}
public static void  _feedtest(String _juduls) throws Exception{
ResumableSub_FeedTest rsub = new ResumableSub_FeedTest(null,_juduls);
rsub.resume(processBA, null);
}
public static class ResumableSub_FeedTest extends BA.ResumableSub {
public ResumableSub_FeedTest(FutsalID.example.info parent,String _juduls) {
this.parent = parent;
this._juduls = _juduls;
}
FutsalID.example.info parent;
String _juduls;
FutsalID.example.dbrequestmanager _req = null;
FutsalID.example.main._dbcommand _cmd = null;
FutsalID.example.httpjob _j = null;
FutsalID.example.main._dbresult _res = null;
Object[] _row = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _gambar = null;
anywheresoftware.b4a.BA.IterableList group7;
int index7;
int groupLen7;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 44;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*FutsalID.example.dbrequestmanager*/ (mostCurrent.activityBA,info.getObject());
 //BA.debugLineNum = 45;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"FeedTe";
_cmd = parent.mostCurrent._util._createcommand /*FutsalID.example.main._dbcommand*/ (mostCurrent.activityBA,"FeedTesting",new Object[]{(Object)(_juduls)});
 //BA.debugLineNum = 46;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*FutsalID.example.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 11;
return;
case 11:
//C
this.state = 1;
_j = (FutsalID.example.httpjob) result[0];
;
 //BA.debugLineNum = 47;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 10;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}else {
this.state = 9;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 48;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 49;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 12;
return;
case 12:
//C
this.state = 4;
_res = (FutsalID.example.main._dbresult) result[0];
;
 //BA.debugLineNum = 50;BA.debugLine="For Each row() As Object In res.Rows";
if (true) break;

case 4:
//for
this.state = 7;
group7 = _res.Rows /*anywheresoftware.b4a.objects.collections.List*/ ;
index7 = 0;
groupLen7 = group7.getSize();
this.state = 13;
if (true) break;

case 13:
//C
this.state = 7;
if (index7 < groupLen7) {
this.state = 6;
_row = (Object[])(group7.Get(index7));}
if (true) break;

case 14:
//C
this.state = 13;
index7++;
if (true) break;

case 6:
//C
this.state = 14;
 //BA.debugLineNum = 51;BA.debugLine="Label1.Text = row(0)";
parent.mostCurrent._label1.setText(BA.ObjectToCharSequence(_row[(int) (0)]));
 //BA.debugLineNum = 52;BA.debugLine="Label2.Text = row(1)";
parent.mostCurrent._label2.setText(BA.ObjectToCharSequence(_row[(int) (1)]));
 //BA.debugLineNum = 53;BA.debugLine="Dim gambar As Bitmap = req.BytesToImage(row(5))";
_gambar = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_gambar = _req._bytestoimage /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ ((byte[])(_row[(int) (5)]));
 //BA.debugLineNum = 54;BA.debugLine="ImageView1.Bitmap = gambar";
parent.mostCurrent._imageview1.setBitmap((android.graphics.Bitmap)(_gambar.getObject()));
 if (true) break;
if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 57;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("84784142",_j._errormessage /*String*/ ,0);
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 59;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _jobdone(FutsalID.example.httpjob _j) throws Exception{
}
public static void  _req_result(FutsalID.example.main._dbresult _res) throws Exception{
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private ImageView1 As ImageView";
mostCurrent._imageview1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private SV_Info As ScrollView";
mostCurrent._sv_info = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private pnlDetail As Panel";
mostCurrent._pnldetail = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private ButtonWA As Button";
mostCurrent._buttonwa = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim judul As String";
_judul = "";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
}
