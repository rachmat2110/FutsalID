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

public class main extends android.support.v7.app.AppCompatActivity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "FutsalID.example", "FutsalID.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "FutsalID.example", "FutsalID.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "FutsalID.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
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
public static String _rdclink = "";
public anywheresoftware.b4a.objects.ButtonWrapper _btnbar = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmenu = null;
public anywheresoftware.b4a.objects.SlidingMenuWrapper _sm = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnbooked = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnriview = null;
public static String[] _pic = null;
public static int _count = 0;
public anywheresoftware.b4a.objects.ButtonWrapper _btnvideo = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imageview1 = null;
public anywheresoftware.b4a.agraham.reflection.Reflection _obj = null;
public anywheresoftware.b4a.objects.Timer _timer1 = null;
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
public FutsalID.example.unggul _unggul = null;
public FutsalID.example.util _util = null;
public FutsalID.example.viva _viva = null;
public FutsalID.example.httputils2service _httputils2service = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (booking.mostCurrent != null);
vis = vis | (riview.mostCurrent != null);
vis = vis | (alibaba.mostCurrent != null);
vis = vis | (feed.mostCurrent != null);
vis = vis | (info.mostCurrent != null);
vis = vis | (estadio.mostCurrent != null);
vis = vis | (halim.mostCurrent != null);
vis = vis | (next_1.mostCurrent != null);
vis = vis | (tempatfutsal.mostCurrent != null);
vis = vis | (town.mostCurrent != null);
vis = vis | (unggul.mostCurrent != null);
vis = vis | (viva.mostCurrent != null);
return vis;}
public static class _dbresult{
public boolean IsInitialized;
public Object Tag;
public anywheresoftware.b4a.objects.collections.Map Columns;
public anywheresoftware.b4a.objects.collections.List Rows;
public void Initialize() {
IsInitialized = true;
Tag = new Object();
Columns = new anywheresoftware.b4a.objects.collections.Map();
Rows = new anywheresoftware.b4a.objects.collections.List();
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _dbcommand{
public boolean IsInitialized;
public String Name;
public Object[] Parameters;
public void Initialize() {
IsInitialized = true;
Name = "";
Parameters = new Object[0];
{
int d0 = Parameters.length;
for (int i0 = 0;i0 < d0;i0++) {
Parameters[i0] = new Object();
}
}
;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 52;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 53;BA.debugLine="Activity.LoadLayout(\"SplashScreen\")";
mostCurrent._activity.LoadLayout("SplashScreen",mostCurrent.activityBA);
 //BA.debugLineNum = 54;BA.debugLine="timer1.Initialize(\"timer1\",40)";
mostCurrent._timer1.Initialize(processBA,"timer1",(long) (40));
 //BA.debugLineNum = 55;BA.debugLine="timer1.Enabled=True";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 56;BA.debugLine="count=0";
_count = (int) (0);
 //BA.debugLineNum = 57;BA.debugLine="obj.Target=ImageView1.Background";
mostCurrent._obj.Target = (Object)(mostCurrent._imageview1.getBackground());
 //BA.debugLineNum = 58;BA.debugLine="obj.RunMethod2(\"setAlpha\",0,\"java.lang.int\")";
mostCurrent._obj.RunMethod2("setAlpha",BA.NumberToString(0),"java.lang.int");
 };
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 100;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static String  _btnbar_click() throws Exception{
 //BA.debugLineNum = 121;BA.debugLine="Sub btnBar_Click";
 //BA.debugLineNum = 122;BA.debugLine="sm.ShowMenu";
mostCurrent._sm.ShowMenu();
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return "";
}
public static String  _btnbooked_click() throws Exception{
 //BA.debugLineNum = 126;BA.debugLine="Sub btnBooked_Click";
 //BA.debugLineNum = 127;BA.debugLine="StartActivity(Booking)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._booking.getObject()));
 //BA.debugLineNum = 128;BA.debugLine="End Sub";
return "";
}
public static String  _btnriview_click() throws Exception{
 //BA.debugLineNum = 130;BA.debugLine="Sub btnRiview_Click";
 //BA.debugLineNum = 131;BA.debugLine="StartActivity(Riview)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._riview.getObject()));
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _btnvideo_click() throws Exception{
 //BA.debugLineNum = 134;BA.debugLine="Sub btnVIdeo_Click";
 //BA.debugLineNum = 135;BA.debugLine="StartActivity(Feed)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._feed.getObject()));
 //BA.debugLineNum = 136;BA.debugLine="End Sub";
return "";
}
public static void  _getkomen() throws Exception{
ResumableSub_GetKomen rsub = new ResumableSub_GetKomen(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_GetKomen extends BA.ResumableSub {
public ResumableSub_GetKomen(FutsalID.example.main parent) {
this.parent = parent;
}
FutsalID.example.main parent;
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
 //BA.debugLineNum = 104;BA.debugLine="Dim req As DBRequestManager = util.CreateRequest(";
_req = parent.mostCurrent._util._createrequest /*FutsalID.example.dbrequestmanager*/ (mostCurrent.activityBA,main.getObject());
 //BA.debugLineNum = 105;BA.debugLine="Dim cmd As DBCommand = util.CreateCommand(\"GetAll";
_cmd = parent.mostCurrent._util._createcommand /*FutsalID.example.main._dbcommand*/ (mostCurrent.activityBA,"GetAllBook",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 106;BA.debugLine="Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_req._executequery /*FutsalID.example.httpjob*/ (_cmd,(int) (0),anywheresoftware.b4a.keywords.Common.Null)));
this.state = 11;
return;
case 11:
//C
this.state = 1;
_j = (FutsalID.example.httpjob) result[0];
;
 //BA.debugLineNum = 107;BA.debugLine="If j.Success Then";
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
 //BA.debugLineNum = 108;BA.debugLine="req.HandleJobAsync(j,\"req\")";
_req._handlejobasync /*void*/ (_j,"req");
 //BA.debugLineNum = 109;BA.debugLine="Wait For(req) req_Result(res As DBResult)";
anywheresoftware.b4a.keywords.Common.WaitFor("req_result", processBA, this, (Object)(_req));
this.state = 12;
return;
case 12:
//C
this.state = 4;
_res = (FutsalID.example.main._dbresult) result[0];
;
 //BA.debugLineNum = 110;BA.debugLine="For Each row() As Object In res.Rows";
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
 //BA.debugLineNum = 111;BA.debugLine="count = Rnd(0,5)";
parent._count = anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (5));
 //BA.debugLineNum = 112;BA.debugLine="ListView1.AddTwoLinesAndBitmap(row(0) ,row(3) &";
parent.mostCurrent._listview1.AddTwoLinesAndBitmap(BA.ObjectToCharSequence(_row[(int) (0)]),BA.ObjectToCharSequence(BA.ObjectToString(_row[(int) (3)])+" ("+BA.ObjectToString(_row[(int) (2)])+" jam"+" )"+" ("+BA.ObjectToString(_row[(int) (1)])+" )"),(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),parent.mostCurrent._pic[parent._count]).getObject()));
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
 //BA.debugLineNum = 115;BA.debugLine="Log(j.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.LogImpl("8851980",_j._errormessage /*String*/ ,0);
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 117;BA.debugLine="j.Release";
_j._release /*String*/ ();
 //BA.debugLineNum = 118;BA.debugLine="CallSub(Booking,\"cek\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(processBA,(Object)(parent.mostCurrent._booking.getObject()),"cek");
 //BA.debugLineNum = 119;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 26;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 30;BA.debugLine="Private btnBar As Button";
mostCurrent._btnbar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private pnlMenu As Panel";
mostCurrent._pnlmenu = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim sm As SlidingMenu";
mostCurrent._sm = new anywheresoftware.b4a.objects.SlidingMenuWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private btnBooked As Button";
mostCurrent._btnbooked = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private btnRiview As Button";
mostCurrent._btnriview = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Dim pic() As String";
mostCurrent._pic = new String[(int) (0)];
java.util.Arrays.fill(mostCurrent._pic,"");
 //BA.debugLineNum = 39;BA.debugLine="Dim count As Int";
_count = 0;
 //BA.debugLineNum = 40;BA.debugLine="Private btnVIdeo As Button";
mostCurrent._btnvideo = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Dim ImageView1 As ImageView";
mostCurrent._imageview1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Dim count As Int";
_count = 0;
 //BA.debugLineNum = 45;BA.debugLine="Dim obj As Reflector";
mostCurrent._obj = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 46;BA.debugLine="Dim timer1 As Timer";
mostCurrent._timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
booking._process_globals();
riview._process_globals();
alibaba._process_globals();
feed._process_globals();
info._process_globals();
charts._process_globals();
estadio._process_globals();
halim._process_globals();
next_1._process_globals();
starter._process_globals();
tempatfutsal._process_globals();
town._process_globals();
unggul._process_globals();
util._process_globals();
viva._process_globals();
httputils2service._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 17;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 20;BA.debugLine="Type DBResult (Tag As Object, Columns As Map, Row";
;
 //BA.debugLineNum = 21;BA.debugLine="Type DBCommand (Name As String, Parameters() As O";
;
 //BA.debugLineNum = 22;BA.debugLine="Public const rdcLink As String = \"http://192.168.";
_rdclink = "http://192.168.1.5:17178/rdc";
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_end() throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bitmap1 = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bitmap2 = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bitmap3 = null;
 //BA.debugLineNum = 70;BA.debugLine="Sub timer1_end";
 //BA.debugLineNum = 71;BA.debugLine="timer1.Enabled=False";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 72;BA.debugLine="Activity.LoadLayout(\"DashboardMenu\")";
mostCurrent._activity.LoadLayout("DashboardMenu",mostCurrent.activityBA);
 //BA.debugLineNum = 73;BA.debugLine="GetKomen";
_getkomen();
 //BA.debugLineNum = 75;BA.debugLine="Dim bitmap1 As Bitmap";
_bitmap1 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 76;BA.debugLine="Dim bitmap2 As Bitmap";
_bitmap2 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 77;BA.debugLine="Dim bitmap3 As Bitmap";
_bitmap3 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 79;BA.debugLine="sm.Initialize(\"sm\")";
mostCurrent._sm.Initialize(mostCurrent.activityBA,"sm");
 //BA.debugLineNum = 80;BA.debugLine="sm.Mode = sm.LEFT";
mostCurrent._sm.setMode(mostCurrent._sm.LEFT);
 //BA.debugLineNum = 81;BA.debugLine="sm.BehindOffset = 70dip";
mostCurrent._sm.setBehindOffset(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)));
 //BA.debugLineNum = 83;BA.debugLine="pnlMenu.Initialize(\"\")";
mostCurrent._pnlmenu.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 84;BA.debugLine="sm.Menu.AddView(pnlMenu,0,0,100%x,100%y)";
mostCurrent._sm.getMenu().AddView((android.view.View)(mostCurrent._pnlmenu.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 85;BA.debugLine="pnlMenu.LoadLayout(\"SlideMenu\")";
mostCurrent._pnlmenu.LoadLayout("SlideMenu",mostCurrent.activityBA);
 //BA.debugLineNum = 87;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.TextColor = Col";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 88;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.Height =";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setHeight((int) (100));
 //BA.debugLineNum = 89;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.TextSize = 15";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setTextSize((float) (15));
 //BA.debugLineNum = 90;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.TextSize";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setTextSize((float) (13));
 //BA.debugLineNum = 91;BA.debugLine="pic =Array As String (\"user1.png\",\"user2.png\", \"u";
mostCurrent._pic = new String[]{"user1.png","user2.png","user3.png","user4.png","user5.png","user6.png"};
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_tick() throws Exception{
 //BA.debugLineNum = 64;BA.debugLine="Sub timer1_Tick";
 //BA.debugLineNum = 65;BA.debugLine="count = count +3";
_count = (int) (_count+3);
 //BA.debugLineNum = 66;BA.debugLine="obj.RunMethod2(\"setAlpha\",Min(count,255),\"java.la";
mostCurrent._obj.RunMethod2("setAlpha",BA.NumberToString(anywheresoftware.b4a.keywords.Common.Min(_count,255)),"java.lang.int");
 //BA.debugLineNum = 67;BA.debugLine="If count > 314 Then timer1_end";
if (_count>314) { 
_timer1_end();};
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
}
