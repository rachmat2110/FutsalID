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

public class unggul extends Activity implements B4AActivity{
	public static unggul mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "FutsalID.example", "FutsalID.example.unggul");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (unggul).");
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
		activityBA = new BA(this, layout, processBA, "FutsalID.example", "FutsalID.example.unggul");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "FutsalID.example.unggul", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (unggul) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (unggul) Resume **");
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
		return unggul.class;
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
        BA.LogInfo("** Activity (unggul) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            unggul mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (unggul) Resume **");
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
public static anywheresoftware.b4a.objects.RuntimePermissions _rp = null;
public static int _nil = 0;
public static float _val = 0f;
public static float[] _valbuf = null;
public static String _tm = "";
public static String[] _tmbuf = null;
public anywheresoftware.b4a.objects.MapFragmentWrapper.GoogleMapWrapper _gmap = null;
public static String[] _pic = null;
public static int _count = 0;
public anywheresoftware.b4a.objects.TabStripViewPager _tsunggul = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlgrafikunggul = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _sv_unggul = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlpageunggul = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnpetaunggul = null;
public anywheresoftware.b4a.objects.MapFragmentWrapper _mapfragment6 = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _pnl_svriviewunggul = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl_riviewunggul = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lv_riviewunggul = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _sv_insertunggul = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl_insertkomenunggul = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblrating = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtkomen = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtnama = null;
public FutsalID.example.main _main = null;
public FutsalID.example.booking _booking = null;
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
 //BA.debugLineNum = 45;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 47;BA.debugLine="Activity.LoadLayout(\"Unggul\")";
mostCurrent._activity.LoadLayout("Unggul",mostCurrent.activityBA);
 //BA.debugLineNum = 48;BA.debugLine="TSUnggul.LoadLayout(\"pnl_SVUnggul\", \"    Informsi";
mostCurrent._tsunggul.LoadLayout("pnl_SVUnggul",BA.ObjectToCharSequence("    Informsi Tempat    "));
 //BA.debugLineNum = 49;BA.debugLine="SV_Unggul.Panel.LoadLayout(\"Page1Unggul\")";
mostCurrent._sv_unggul.getPanel().LoadLayout("Page1Unggul",mostCurrent.activityBA);
 //BA.debugLineNum = 50;BA.debugLine="SV_Unggul.Panel.Height = pnlPageUnggul.Height";
mostCurrent._sv_unggul.getPanel().setHeight(mostCurrent._pnlpageunggul.getHeight());
 //BA.debugLineNum = 51;BA.debugLine="SV_Unggul.Panel.Width = pnlPageUnggul.Width";
mostCurrent._sv_unggul.getPanel().setWidth(mostCurrent._pnlpageunggul.getWidth());
 //BA.debugLineNum = 52;BA.debugLine="TSUnggul.LoadLayout(\"pnl_SVRiviewUnggul\", \"   Riv";
mostCurrent._tsunggul.LoadLayout("pnl_SVRiviewUnggul",BA.ObjectToCharSequence("   Riview   "));
 //BA.debugLineNum = 53;BA.debugLine="pnl_SVriviewUnggul.Panel.LoadLayout(\"layRiviewUng";
mostCurrent._pnl_svriviewunggul.getPanel().LoadLayout("layRiviewUnggul",mostCurrent.activityBA);
 //BA.debugLineNum = 54;BA.debugLine="pnl_SVriviewUnggul.Panel.Height = pnl_RiviewUnggu";
mostCurrent._pnl_svriviewunggul.getPanel().setHeight(mostCurrent._pnl_riviewunggul.getHeight());
 //BA.debugLineNum = 55;BA.debugLine="pnl_SVriviewUnggul.Panel.Width = pnl_RiviewUnggul";
mostCurrent._pnl_svriviewunggul.getPanel().setWidth(mostCurrent._pnl_riviewunggul.getWidth());
 //BA.debugLineNum = 56;BA.debugLine="TSUnggul.LoadLayout(\"SV_InsertKomenUnggul\", \"";
mostCurrent._tsunggul.LoadLayout("SV_InsertKomenUnggul",BA.ObjectToCharSequence("      Beri Komen     "));
 //BA.debugLineNum = 57;BA.debugLine="SV_InsertUnggul.Panel.LoadLayout(\"layInsertKomenU";
mostCurrent._sv_insertunggul.getPanel().LoadLayout("layInsertKomenUnggul",mostCurrent.activityBA);
 //BA.debugLineNum = 58;BA.debugLine="SV_InsertUnggul.Panel.Height = pnl_InsertKomenUng";
mostCurrent._sv_insertunggul.getPanel().setHeight(mostCurrent._pnl_insertkomenunggul.getHeight());
 //BA.debugLineNum = 59;BA.debugLine="SV_InsertUnggul.Panel.Width = pnl_InsertKomenUngg";
mostCurrent._sv_insertunggul.getPanel().setWidth(mostCurrent._pnl_insertkomenunggul.getWidth());
 //BA.debugLineNum = 61;BA.debugLine="LV_RiviewUnggul.TwoLinesAndBitmap.Label.TextColor";
mostCurrent._lv_riviewunggul.getTwoLinesAndBitmap().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 62;BA.debugLine="LV_RiviewUnggul.TwoLinesAndBitmap.SecondLabel.Hei";
mostCurrent._lv_riviewunggul.getTwoLinesAndBitmap().SecondLabel.setHeight((int) (100));
 //BA.debugLineNum = 63;BA.debugLine="LV_RiviewUnggul.TwoLinesAndBitmap.Label.TextSize";
mostCurrent._lv_riviewunggul.getTwoLinesAndBitmap().Label.setTextSize((float) (15));
 //BA.debugLineNum = 64;BA.debugLine="LV_RiviewUnggul.TwoLinesAndBitmap.SecondLabel.Tex";
mostCurrent._lv_riviewunggul.getTwoLinesAndBitmap().SecondLabel.setTextSize((float) (13));
 //BA.debugLineNum = 65;BA.debugLine="pic =Array As String (\"user1.png\",\"user2.png\", \"u";
mostCurrent._pic = new String[]{"user1.png","user2.png","user3.png","user4.png","user5.png","user6.png"};
 //BA.debugLineNum = 66;BA.debugLine="GetKomen";
_getkomen();
 //BA.debugLineNum = 67;BA.debugLine="apaja";
_apaja();
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 75;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 71;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 73;BA.debugLine="End Sub";
return "";
}
public static void  _apaja() throws Exception{
ResumableSub_apaja rsub = new ResumableSub_apaja(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_apaja extends BA.ResumableSub {
public ResumableSub_apaja(FutsalID.example.unggul parent) {
this.parent = parent;
}
FutsalID.example.unggul parent;
FutsalID.example.dbrequestmanager _req = null;
FutsalID.example.main._dbcommand _cmd = null;
FutsalID.example.httpjob _j = null;
FutsalID.example.main._dbresult _res = null;
Object[] _row = null;
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
 //BA.debugLineNum = 157;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*FutsalID.example.dbrequestmanager*/ (mostCurrent.activityBA,unggul.getObject());
 //BA.debugLineNum = 158;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"AvgRat";
_cmd = parent.mostCurrent._util._createcommand /*FutsalID.example.main._dbcommand*/ (mostCurrent.activityBA,"AvgRateUnggul",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 159;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*FutsalID.example.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 11;
return;
case 11:
//C
this.state = 1;
_j = (FutsalID.example.httpjob) result[0];
;
 //BA.debugLineNum = 160;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 161;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 162;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 12;
return;
case 12:
//C
this.state = 4;
_res = (FutsalID.example.main._dbresult) result[0];
;
 //BA.debugLineNum = 163;BA.debugLine="For Each row() As Object In res.Rows";
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
 //BA.debugLineNum = 164;BA.debugLine="nil = row(0)";
parent._nil = (int)(BA.ObjectToNumber(_row[(int) (0)]));
 //BA.debugLineNum = 165;BA.debugLine="Val = Round2(nil,1)";
parent._val = (float) (anywheresoftware.b4a.keywords.Common.Round2(parent._nil,(int) (1)));
 //BA.debugLineNum = 166;BA.debugLine="ValBuf(0) = ValBuf(1)";
parent._valbuf[(int) (0)] = parent._valbuf[(int) (1)];
 //BA.debugLineNum = 167;BA.debugLine="ValBuf(1) = ValBuf(2)";
parent._valbuf[(int) (1)] = parent._valbuf[(int) (2)];
 //BA.debugLineNum = 168;BA.debugLine="ValBuf(2) = ValBuf(3)";
parent._valbuf[(int) (2)] = parent._valbuf[(int) (3)];
 //BA.debugLineNum = 169;BA.debugLine="ValBuf(3) = ValBuf(4)";
parent._valbuf[(int) (3)] = parent._valbuf[(int) (4)];
 //BA.debugLineNum = 170;BA.debugLine="ValBuf(4) = ValBuf(5)";
parent._valbuf[(int) (4)] = parent._valbuf[(int) (5)];
 //BA.debugLineNum = 171;BA.debugLine="ValBuf(5) = ValBuf(6)";
parent._valbuf[(int) (5)] = parent._valbuf[(int) (6)];
 //BA.debugLineNum = 172;BA.debugLine="ValBuf(6) = ValBuf(7)";
parent._valbuf[(int) (6)] = parent._valbuf[(int) (7)];
 //BA.debugLineNum = 173;BA.debugLine="ValBuf(7) = ValBuf(8)";
parent._valbuf[(int) (7)] = parent._valbuf[(int) (8)];
 //BA.debugLineNum = 174;BA.debugLine="ValBuf(8) = ValBuf(9)";
parent._valbuf[(int) (8)] = parent._valbuf[(int) (9)];
 //BA.debugLineNum = 175;BA.debugLine="ValBuf(9) = ValBuf(10)";
parent._valbuf[(int) (9)] = parent._valbuf[(int) (10)];
 //BA.debugLineNum = 176;BA.debugLine="ValBuf(10) = ValBuf(11)";
parent._valbuf[(int) (10)] = parent._valbuf[(int) (11)];
 //BA.debugLineNum = 177;BA.debugLine="ValBuf(11) = ValBuf(12)";
parent._valbuf[(int) (11)] = parent._valbuf[(int) (12)];
 //BA.debugLineNum = 178;BA.debugLine="ValBuf(12) = Val";
parent._valbuf[(int) (12)] = parent._val;
 //BA.debugLineNum = 180;BA.debugLine="tmbuf(0) = tmbuf(1)";
parent.mostCurrent._tmbuf[(int) (0)] = parent.mostCurrent._tmbuf[(int) (1)];
 //BA.debugLineNum = 181;BA.debugLine="tmbuf(1) = tmbuf(2)";
parent.mostCurrent._tmbuf[(int) (1)] = parent.mostCurrent._tmbuf[(int) (2)];
 //BA.debugLineNum = 182;BA.debugLine="tmbuf(2) = tmbuf(3)";
parent.mostCurrent._tmbuf[(int) (2)] = parent.mostCurrent._tmbuf[(int) (3)];
 //BA.debugLineNum = 183;BA.debugLine="tmbuf(3) = tmbuf(4)";
parent.mostCurrent._tmbuf[(int) (3)] = parent.mostCurrent._tmbuf[(int) (4)];
 //BA.debugLineNum = 184;BA.debugLine="tmbuf(4) = tmbuf(5)";
parent.mostCurrent._tmbuf[(int) (4)] = parent.mostCurrent._tmbuf[(int) (5)];
 //BA.debugLineNum = 185;BA.debugLine="tmbuf(5) = tmbuf(6)";
parent.mostCurrent._tmbuf[(int) (5)] = parent.mostCurrent._tmbuf[(int) (6)];
 //BA.debugLineNum = 186;BA.debugLine="tmbuf(6) = tmbuf(7)";
parent.mostCurrent._tmbuf[(int) (6)] = parent.mostCurrent._tmbuf[(int) (7)];
 //BA.debugLineNum = 187;BA.debugLine="tmbuf(7) = tmbuf(8)";
parent.mostCurrent._tmbuf[(int) (7)] = parent.mostCurrent._tmbuf[(int) (8)];
 //BA.debugLineNum = 188;BA.debugLine="tmbuf(8) = tmbuf(9)";
parent.mostCurrent._tmbuf[(int) (8)] = parent.mostCurrent._tmbuf[(int) (9)];
 //BA.debugLineNum = 189;BA.debugLine="tmbuf(9) = tmbuf(10)";
parent.mostCurrent._tmbuf[(int) (9)] = parent.mostCurrent._tmbuf[(int) (10)];
 //BA.debugLineNum = 190;BA.debugLine="tmbuf(10) = tmbuf(11)";
parent.mostCurrent._tmbuf[(int) (10)] = parent.mostCurrent._tmbuf[(int) (11)];
 //BA.debugLineNum = 191;BA.debugLine="tmbuf(11) = tmbuf(12)";
parent.mostCurrent._tmbuf[(int) (11)] = parent.mostCurrent._tmbuf[(int) (12)];
 //BA.debugLineNum = 192;BA.debugLine="tmbuf(12) = tm";
parent.mostCurrent._tmbuf[(int) (12)] = parent.mostCurrent._tm;
 if (true) break;
if (true) break;

case 7:
//C
this.state = 10;
;
 //BA.debugLineNum = 194;BA.debugLine="graphdraw";
_graphdraw();
 if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 196;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("813369384",_j._errormessage /*String*/ ,0);
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 198;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 199;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _jobdone(FutsalID.example.httpjob _j) throws Exception{
}
public static void  _req_result(FutsalID.example.main._dbresult _res) throws Exception{
}
public static String  _btnpetaunggul_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _in2 = null;
anywheresoftware.b4j.object.JavaObject _job = null;
 //BA.debugLineNum = 93;BA.debugLine="Sub btnPetaUnggul_Click";
 //BA.debugLineNum = 94;BA.debugLine="ToastMessageShow(\"Opening Maps\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Opening Maps"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 95;BA.debugLine="Try";
try { //BA.debugLineNum = 96;BA.debugLine="Dim in2 As Intent";
_in2 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 97;BA.debugLine="in2.Initialize(in2.ACTION_VIEW, \"https://www.goo";
_in2.Initialize(_in2.ACTION_VIEW,"https://www.google.com/maps/dir//Unggul+Futsal,+Jl.+Raya+Kranggan,+RT.001%2FRW.007,+Jatiraden,+Kec.+Jatisampurna,+Kota+Bks,+Jawa+Barat+17433/@-6.3514244,106.886757,13z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e699330d00e96ef:0x6cac6af6862b14b!2m2!1d106.9217767!2d-6.3515109");
 //BA.debugLineNum = 98;BA.debugLine="Dim job As JavaObject = in2";
_job = new anywheresoftware.b4j.object.JavaObject();
_job.setObject((java.lang.Object)(_in2.getObject()));
 //BA.debugLineNum = 99;BA.debugLine="job.RunMethod(\"setPackage\", Array(\"com.google.an";
_job.RunMethod("setPackage",new Object[]{(Object)("com.google.android.apps.maps")});
 //BA.debugLineNum = 100;BA.debugLine="StartActivity(in2)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_in2.getObject()));
 } 
       catch (Exception e9) {
			processBA.setLastException(e9); //BA.debugLineNum = 102;BA.debugLine="Msgbox(\"Google Maps Application is not Found !!\"";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Google Maps Application is not Found !!"),BA.ObjectToCharSequence("Warning"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 104;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 249;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 250;BA.debugLine="InsertKomen(txtNama.Text,lblRating.Text,txtKomen.";
_insertkomen(mostCurrent._txtnama.getText(),(int)(Double.parseDouble(mostCurrent._lblrating.getText())),mostCurrent._txtkomen.getText());
 //BA.debugLineNum = 251;BA.debugLine="End Sub";
return "";
}
public static void  _getkomen() throws Exception{
ResumableSub_GetKomen rsub = new ResumableSub_GetKomen(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetKomen extends BA.ResumableSub {
public ResumableSub_GetKomen(FutsalID.example.unggul parent) {
this.parent = parent;
}
FutsalID.example.unggul parent;
FutsalID.example.dbrequestmanager _req = null;
FutsalID.example.main._dbcommand _cmd = null;
FutsalID.example.httpjob _j = null;
FutsalID.example.main._dbresult _res = null;
Object[] _row = null;
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
 //BA.debugLineNum = 138;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*FutsalID.example.dbrequestmanager*/ (mostCurrent.activityBA,unggul.getObject());
 //BA.debugLineNum = 139;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"GetCom";
_cmd = parent.mostCurrent._util._createcommand /*FutsalID.example.main._dbcommand*/ (mostCurrent.activityBA,"GetCommentUnggul",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 140;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*FutsalID.example.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 11;
return;
case 11:
//C
this.state = 1;
_j = (FutsalID.example.httpjob) result[0];
;
 //BA.debugLineNum = 141;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 142;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 143;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 12;
return;
case 12:
//C
this.state = 4;
_res = (FutsalID.example.main._dbresult) result[0];
;
 //BA.debugLineNum = 144;BA.debugLine="For Each row() As Object In res.Rows";
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
 //BA.debugLineNum = 145;BA.debugLine="count = Rnd(0,5)";
parent._count = anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (5));
 //BA.debugLineNum = 146;BA.debugLine="LV_RiviewUnggul.AddTwoLinesAndBitmap(row(0) ,ro";
parent.mostCurrent._lv_riviewunggul.AddTwoLinesAndBitmap(BA.ObjectToCharSequence(_row[(int) (0)]),BA.ObjectToCharSequence(_row[(int) (2)]),(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),parent.mostCurrent._pic[parent._count]).getObject()));
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
 //BA.debugLineNum = 149;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("813303820",_j._errormessage /*String*/ ,0);
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 151;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 152;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Dim Val As Float";
_val = 0f;
 //BA.debugLineNum = 18;BA.debugLine="Dim ValBuf(14) As Float";
_valbuf = new float[(int) (14)];
;
 //BA.debugLineNum = 19;BA.debugLine="Dim tm,tmbuf(14) As String";
mostCurrent._tm = "";
mostCurrent._tmbuf = new String[(int) (14)];
java.util.Arrays.fill(mostCurrent._tmbuf,"");
 //BA.debugLineNum = 22;BA.debugLine="Dim gmap As GoogleMap";
mostCurrent._gmap = new anywheresoftware.b4a.objects.MapFragmentWrapper.GoogleMapWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Dim pic() As String";
mostCurrent._pic = new String[(int) (0)];
java.util.Arrays.fill(mostCurrent._pic,"");
 //BA.debugLineNum = 26;BA.debugLine="Dim count As Int";
_count = 0;
 //BA.debugLineNum = 28;BA.debugLine="Private TSUnggul As TabStrip";
mostCurrent._tsunggul = new anywheresoftware.b4a.objects.TabStripViewPager();
 //BA.debugLineNum = 29;BA.debugLine="Private pnlGrafikUnggul As Panel";
mostCurrent._pnlgrafikunggul = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private SV_Unggul As ScrollView";
mostCurrent._sv_unggul = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private pnlPageUnggul As Panel";
mostCurrent._pnlpageunggul = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private btnPetaUnggul As Button";
mostCurrent._btnpetaunggul = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private MapFragment6 As MapFragment";
mostCurrent._mapfragment6 = new anywheresoftware.b4a.objects.MapFragmentWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private pnl_SVriviewUnggul As ScrollView";
mostCurrent._pnl_svriviewunggul = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private pnl_RiviewUnggul As Panel";
mostCurrent._pnl_riviewunggul = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private LV_RiviewUnggul As ListView";
mostCurrent._lv_riviewunggul = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private SV_InsertUnggul As ScrollView";
mostCurrent._sv_insertunggul = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private pnl_InsertKomenUnggul As Panel";
mostCurrent._pnl_insertkomenunggul = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private lblRating As Label";
mostCurrent._lblrating = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private txtKomen As EditText";
mostCurrent._txtkomen = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private txtNama As EditText";
mostCurrent._txtnama = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return "";
}
public static String  _graphdraw() throws Exception{
FutsalID.example.charts._linedata _ld = null;
FutsalID.example.charts._graph _g = null;
 //BA.debugLineNum = 202;BA.debugLine="Sub graphdraw";
 //BA.debugLineNum = 203;BA.debugLine="Dim LD As LineData";
_ld = new FutsalID.example.charts._linedata();
 //BA.debugLineNum = 204;BA.debugLine="LD.Initialize";
_ld.Initialize();
 //BA.debugLineNum = 205;BA.debugLine="LD.Target = pnlGrafikUnggul";
_ld.Target /*anywheresoftware.b4a.objects.PanelWrapper*/  = mostCurrent._pnlgrafikunggul;
 //BA.debugLineNum = 206;BA.debugLine="Charts.AddLineColor(LD,Colors.rgb(46,204,113))'Co";
mostCurrent._charts._addlinecolor /*String*/ (mostCurrent.activityBA,_ld,anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (46),(int) (204),(int) (113)));
 //BA.debugLineNum = 208;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(0),ValBuf(0),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (0)],_valbuf[(int) (0)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 209;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(1),ValBuf(1),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (1)],_valbuf[(int) (1)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 210;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(2),ValBuf(2),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (2)],_valbuf[(int) (2)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 211;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(3),ValBuf(3),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (3)],_valbuf[(int) (3)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 212;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(4),ValBuf(4),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (4)],_valbuf[(int) (4)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 213;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(5),ValBuf(5),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (5)],_valbuf[(int) (5)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 214;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(6),ValBuf(6),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (6)],_valbuf[(int) (6)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 215;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(7),ValBuf(7),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (7)],_valbuf[(int) (7)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 216;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(8),ValBuf(8),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (8)],_valbuf[(int) (8)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 217;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(9),ValBuf(9),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (9)],_valbuf[(int) (9)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 218;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(10),ValBuf(10),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (10)],_valbuf[(int) (10)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 219;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(11),ValBuf(11),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (11)],_valbuf[(int) (11)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 220;BA.debugLine="Charts.AddLinePoint(LD,tmbuf(12),ValBuf(12),True)";
mostCurrent._charts._addlinepoint /*String*/ (mostCurrent.activityBA,_ld,mostCurrent._tmbuf[(int) (12)],_valbuf[(int) (12)],anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 221;BA.debugLine="Dim G As Graph";
_g = new FutsalID.example.charts._graph();
 //BA.debugLineNum = 222;BA.debugLine="G.Initialize";
_g.Initialize();
 //BA.debugLineNum = 224;BA.debugLine="G.YStart = 0";
_g.YStart /*float*/  = (float) (0);
 //BA.debugLineNum = 225;BA.debugLine="G.YEnd = 10";
_g.YEnd /*float*/  = (float) (10);
 //BA.debugLineNum = 226;BA.debugLine="G.YInterval = 1";
_g.YInterval /*float*/  = (float) (1);
 //BA.debugLineNum = 228;BA.debugLine="G.AxisColor = Colors.ARGB(100,0,0,0)";
_g.AxisColor /*int*/  = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (0),(int) (0),(int) (0));
 //BA.debugLineNum = 229;BA.debugLine="Charts.DrawLineChart(G,LD,Colors.White)";
mostCurrent._charts._drawlinechart /*String*/ (mostCurrent.activityBA,_g,_ld,anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 230;BA.debugLine="End Sub";
return "";
}
public static void  _insertkomen(String _nama,int _rating,String _komen) throws Exception{
ResumableSub_InsertKomen rsub = new ResumableSub_InsertKomen(null,_nama,_rating,_komen);
rsub.resume(processBA, null);
}
public static class ResumableSub_InsertKomen extends BA.ResumableSub {
public ResumableSub_InsertKomen(FutsalID.example.unggul parent,String _nama,int _rating,String _komen) {
this.parent = parent;
this._nama = _nama;
this._rating = _rating;
this._komen = _komen;
}
FutsalID.example.unggul parent;
String _nama;
int _rating;
String _komen;
FutsalID.example.main._dbcommand _cmd = null;
FutsalID.example.httpjob _j = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 120;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"Bookin";
_cmd = parent.mostCurrent._util._createcommand /*FutsalID.example.main._dbcommand*/ (mostCurrent.activityBA,"BookingUnggul",new Object[]{(Object)(_nama),(Object)(_rating),(Object)(_komen)});
 //BA.debugLineNum = 121;BA.debugLine="Dim j As HttpJob = util.CreateRequest(Me).Execute";
_j = parent.mostCurrent._util._createrequest /*FutsalID.example.dbrequestmanager*/ (mostCurrent.activityBA,unggul.getObject())._executebatch /*FutsalID.example.httpjob*/ (anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_cmd)}),anywheresoftware.b4a.keywords.Common.Null);
 //BA.debugLineNum = 122;BA.debugLine="Wait For (j) JobDone(j As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_j));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_j = (FutsalID.example.httpjob) result[0];
;
 //BA.debugLineNum = 123;BA.debugLine="If j.Success Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_j._success /*boolean*/ ) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 124;BA.debugLine="ToastMessageShow(\"Rating sudah dimasukkan\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Rating sudah dimasukkan"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 125;BA.debugLine="LV_RiviewUnggul.Clear";
parent.mostCurrent._lv_riviewunggul.Clear();
 //BA.debugLineNum = 126;BA.debugLine="apaja";
_apaja();
 //BA.debugLineNum = 127;BA.debugLine="GetKomen";
_getkomen();
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 129;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("813238282",_j._errormessage /*String*/ ,0);
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 131;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 133;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _lblrating_click() throws Exception{
anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog _nd = null;
Object _ret = null;
 //BA.debugLineNum = 239;BA.debugLine="Sub lblRating_Click";
 //BA.debugLineNum = 240;BA.debugLine="Dim nd As NumberDialog";
_nd = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 241;BA.debugLine="Dim ret As Object";
_ret = new Object();
 //BA.debugLineNum = 242;BA.debugLine="nd.Digits = 0";
_nd.setDigits((int) (0));
 //BA.debugLineNum = 243;BA.debugLine="nd.Number = 1";
_nd.setNumber((int) (1));
 //BA.debugLineNum = 244;BA.debugLine="nd.Decimal = 0";
_nd.setDecimal((int) (0));
 //BA.debugLineNum = 245;BA.debugLine="ret = nd.Show(\"Rating Untuk Tempat (9/9)\", \"OKE\",";
_ret = (Object)(_nd.Show("Rating Untuk Tempat (9/9)","OKE","","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 246;BA.debugLine="lblRating.Text = nd.Number";
mostCurrent._lblrating.setText(BA.ObjectToCharSequence(_nd.getNumber()));
 //BA.debugLineNum = 247;BA.debugLine="End Sub";
return "";
}
public static String  _mapfragment6_click(anywheresoftware.b4a.objects.MapFragmentWrapper.LatLngWrapper _point) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _in2 = null;
anywheresoftware.b4j.object.JavaObject _job = null;
 //BA.debugLineNum = 80;BA.debugLine="Sub MapFragment6_Click (Point As LatLng)";
 //BA.debugLineNum = 81;BA.debugLine="ToastMessageShow(\"Opening Maps\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Opening Maps"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 82;BA.debugLine="Try";
try { //BA.debugLineNum = 83;BA.debugLine="Dim in2 As Intent";
_in2 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 84;BA.debugLine="in2.Initialize(in2.ACTION_VIEW, \"https://www.goo";
_in2.Initialize(_in2.ACTION_VIEW,"https://www.google.com/maps/dir//Unggul+Futsal,+Jl.+Raya+Kranggan,+RT.001%2FRW.007,+Jatiraden,+Kec.+Jatisampurna,+Kota+Bks,+Jawa+Barat+17433/@-6.3514244,106.886757,13z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e699330d00e96ef:0x6cac6af6862b14b!2m2!1d106.9217767!2d-6.3515109");
 //BA.debugLineNum = 85;BA.debugLine="Dim job As JavaObject = in2";
_job = new anywheresoftware.b4j.object.JavaObject();
_job.setObject((java.lang.Object)(_in2.getObject()));
 //BA.debugLineNum = 86;BA.debugLine="job.RunMethod(\"setPackage\", Array(\"com.google.an";
_job.RunMethod("setPackage",new Object[]{(Object)("com.google.android.apps.maps")});
 //BA.debugLineNum = 87;BA.debugLine="StartActivity(in2)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_in2.getObject()));
 } 
       catch (Exception e9) {
			processBA.setLastException(e9); //BA.debugLineNum = 89;BA.debugLine="Msgbox(\"Google Maps Application is not Found !!\"";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Google Maps Application is not Found !!"),BA.ObjectToCharSequence("Warning"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}
public static void  _mapfragment6_ready() throws Exception{
ResumableSub_MapFragment6_Ready rsub = new ResumableSub_MapFragment6_Ready(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_MapFragment6_Ready extends BA.ResumableSub {
public ResumableSub_MapFragment6_Ready(FutsalID.example.unggul parent) {
this.parent = parent;
}
FutsalID.example.unggul parent;
String _permission = "";
boolean _result = false;
anywheresoftware.b4a.objects.MapFragmentWrapper.MarkerWrapper _m1 = null;
anywheresoftware.b4a.objects.MapFragmentWrapper.CameraPositionWrapper _cp = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 107;BA.debugLine="gmap = MapFragment6.GetMap";
parent.mostCurrent._gmap = parent.mostCurrent._mapfragment6.GetMap();
 //BA.debugLineNum = 108;BA.debugLine="rp.CheckAndRequest(rp.PERMISSION_ACCESS_FINE_LOCA";
parent._rp.CheckAndRequest(processBA,parent._rp.PERMISSION_ACCESS_FINE_LOCATION);
 //BA.debugLineNum = 109;BA.debugLine="Wait For Activity_PermissionResult (Permission As";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 1;
return;
case 1:
//C
this.state = -1;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 110;BA.debugLine="gmap.MyLocationEnabled = Result";
parent.mostCurrent._gmap.setMyLocationEnabled(_result);
 //BA.debugLineNum = 111;BA.debugLine="gmap.MyLocationEnabled = False";
parent.mostCurrent._gmap.setMyLocationEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 112;BA.debugLine="Dim m1 As Marker = gmap.AddMarker(-6.35151, 106.9";
_m1 = new anywheresoftware.b4a.objects.MapFragmentWrapper.MarkerWrapper();
_m1 = parent.mostCurrent._gmap.AddMarker(-6.35151,106.92178,"Unggul Futsal");
 //BA.debugLineNum = 113;BA.debugLine="m1.Snippet=\"Jatisampurna, Bekasi\"";
_m1.setSnippet("Jatisampurna, Bekasi");
 //BA.debugLineNum = 114;BA.debugLine="Dim cp As CameraPosition";
_cp = new anywheresoftware.b4a.objects.MapFragmentWrapper.CameraPositionWrapper();
 //BA.debugLineNum = 115;BA.debugLine="cp.Initialize(-6.35151, 106.92178, 19)";
_cp.Initialize(-6.35151,106.92178,(float) (19));
 //BA.debugLineNum = 116;BA.debugLine="gmap.MoveCamera(cp)";
parent.mostCurrent._gmap.MoveCamera((com.google.android.gms.maps.model.CameraPosition)(_cp.getObject()));
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _activity_permissionresult(String _permission,boolean _result) throws Exception{
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim rp As RuntimePermissions";
_rp = new anywheresoftware.b4a.objects.RuntimePermissions();
 //BA.debugLineNum = 10;BA.debugLine="Dim nil As Int";
_nil = 0;
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static String  _timer_tick() throws Exception{
 //BA.debugLineNum = 232;BA.debugLine="Sub timer_Tick";
 //BA.debugLineNum = 236;BA.debugLine="End Sub";
return "";
}
}
