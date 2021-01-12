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

public class tempatfutsal extends Activity implements B4AActivity{
	public static tempatfutsal mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "FutsalID.example", "FutsalID.example.tempatfutsal");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (tempatfutsal).");
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
		activityBA = new BA(this, layout, processBA, "FutsalID.example", "FutsalID.example.tempatfutsal");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "FutsalID.example.tempatfutsal", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (tempatfutsal) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (tempatfutsal) Resume **");
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
		return tempatfutsal.class;
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
        BA.LogInfo("** Activity (tempatfutsal) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            tempatfutsal mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (tempatfutsal) Resume **");
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
public static String _nama = "";
public anywheresoftware.b4a.objects.ListViewWrapper _listview1lv = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnbacklv = null;
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
String _tempat = "";
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bitmap1 = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bitmap2 = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bitmap3 = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bitmap4 = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bitmap5 = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bitmap6 = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bitmap7 = null;
 //BA.debugLineNum = 20;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 22;BA.debugLine="Activity.LoadLayout(\"pnlLV_tempatBooking\")";
mostCurrent._activity.LoadLayout("pnlLV_tempatBooking",mostCurrent.activityBA);
 //BA.debugLineNum = 24;BA.debugLine="Dim Tempat As String";
_tempat = "";
 //BA.debugLineNum = 26;BA.debugLine="Dim Bitmap1 As Bitmap";
_bitmap1 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Dim Bitmap2 As Bitmap";
_bitmap2 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim Bitmap3 As Bitmap";
_bitmap3 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Dim Bitmap4 As Bitmap";
_bitmap4 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Dim Bitmap5 As Bitmap";
_bitmap5 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim Bitmap6 As Bitmap";
_bitmap6 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim Bitmap7 As Bitmap";
_bitmap7 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Bitmap1.Initialize(File.DirAssets, \"alibaba.png\")";
_bitmap1.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"alibaba.png");
 //BA.debugLineNum = 35;BA.debugLine="Bitmap2.Initialize(File.DirAssets, \"estadio.png\")";
_bitmap2.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"estadio.png");
 //BA.debugLineNum = 36;BA.debugLine="Bitmap3.Initialize(File.DirAssets, \"next.png\")";
_bitmap3.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"next.png");
 //BA.debugLineNum = 37;BA.debugLine="Bitmap4.Initialize(File.DirAssets, \"town.png\")";
_bitmap4.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"town.png");
 //BA.debugLineNum = 38;BA.debugLine="Bitmap5.Initialize(File.DirAssets, \"viva.png\")";
_bitmap5.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"viva.png");
 //BA.debugLineNum = 39;BA.debugLine="Bitmap6.Initialize(File.DirAssets, \"unggul.png\")";
_bitmap6.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"unggul.png");
 //BA.debugLineNum = 40;BA.debugLine="Bitmap7.Initialize(File.DirAssets, \"halim.png\")";
_bitmap7.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"halim.png");
 //BA.debugLineNum = 42;BA.debugLine="ListView1LV.TwoLinesAndBitmap.Label.TextColor = C";
mostCurrent._listview1lv.getTwoLinesAndBitmap().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 43;BA.debugLine="ListView1LV.TwoLinesAndBitmap.Label.TextSize = 16";
mostCurrent._listview1lv.getTwoLinesAndBitmap().Label.setTextSize((float) (16));
 //BA.debugLineNum = 44;BA.debugLine="ListView1LV.TwoLinesAndBitmap.Label.Left = 75dip";
mostCurrent._listview1lv.getTwoLinesAndBitmap().Label.setLeft(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75)));
 //BA.debugLineNum = 45;BA.debugLine="ListView1LV.TwoLinesAndBitmap.Label.Typeface = Ty";
mostCurrent._listview1lv.getTwoLinesAndBitmap().Label.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 46;BA.debugLine="ListView1LV.TwoLinesAndBitmap.ItemHeight = 60dip";
mostCurrent._listview1lv.getTwoLinesAndBitmap().setItemHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
 //BA.debugLineNum = 47;BA.debugLine="ListView1LV.TwoLinesAndBitmap.ImageView.Height =";
mostCurrent._listview1lv.getTwoLinesAndBitmap().ImageView.setHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (52)));
 //BA.debugLineNum = 48;BA.debugLine="ListView1LV.TwoLinesAndBitmap.ImageView.Width  =";
mostCurrent._listview1lv.getTwoLinesAndBitmap().ImageView.setWidth(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (52)));
 //BA.debugLineNum = 49;BA.debugLine="ListView1LV.TwoLinesAndBitmap.ImageView.Top = 3di";
mostCurrent._listview1lv.getTwoLinesAndBitmap().ImageView.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)));
 //BA.debugLineNum = 50;BA.debugLine="ListView1LV.TwoLinesAndBitmap.ImageView.Left = 18";
mostCurrent._listview1lv.getTwoLinesAndBitmap().ImageView.setLeft(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (18)));
 //BA.debugLineNum = 51;BA.debugLine="ListView1LV.TwoLinesAndBitmap.ImageView.Gravity =";
mostCurrent._listview1lv.getTwoLinesAndBitmap().ImageView.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 52;BA.debugLine="ListView1LV.TwoLinesAndBitmap.SecondLabel.Left =";
mostCurrent._listview1lv.getTwoLinesAndBitmap().SecondLabel.setLeft(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75)));
 //BA.debugLineNum = 53;BA.debugLine="ListView1LV.TwoLinesAndBitmap.SecondLabel.TextSiz";
mostCurrent._listview1lv.getTwoLinesAndBitmap().SecondLabel.setTextSize((float) (14));
 //BA.debugLineNum = 55;BA.debugLine="ListView1LV.AddTwoLinesAndBitmap(\"Alibaba Futsal\"";
mostCurrent._listview1lv.AddTwoLinesAndBitmap(BA.ObjectToCharSequence("Alibaba Futsal"),BA.ObjectToCharSequence("Pekayon, Bekasi Selatan"),(android.graphics.Bitmap)(_bitmap1.getObject()));
 //BA.debugLineNum = 56;BA.debugLine="ListView1LV.AddTwoLinesAndBitmap(\"Estadio Futsal\"";
mostCurrent._listview1lv.AddTwoLinesAndBitmap(BA.ObjectToCharSequence("Estadio Futsal"),BA.ObjectToCharSequence("JL. Perjuangan, Bekasi Utara"),(android.graphics.Bitmap)(_bitmap2.getObject()));
 //BA.debugLineNum = 57;BA.debugLine="ListView1LV.AddTwoLinesAndBitmap(\"Next Futsal\",\"K";
mostCurrent._listview1lv.AddTwoLinesAndBitmap(BA.ObjectToCharSequence("Next Futsal"),BA.ObjectToCharSequence("Kalimalang, Bekasi Selatan"),(android.graphics.Bitmap)(_bitmap3.getObject()));
 //BA.debugLineNum = 58;BA.debugLine="ListView1LV.AddTwoLinesAndBitmap(\"Town Futsal\",\"P";
mostCurrent._listview1lv.AddTwoLinesAndBitmap(BA.ObjectToCharSequence("Town Futsal"),BA.ObjectToCharSequence("Pekayon, Bekasi Selatan"),(android.graphics.Bitmap)(_bitmap4.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="ListView1LV.AddTwoLinesAndBitmap(\"Viva Futsal\",\"P";
mostCurrent._listview1lv.AddTwoLinesAndBitmap(BA.ObjectToCharSequence("Viva Futsal"),BA.ObjectToCharSequence("Pekayon, Bekasi Selatan"),(android.graphics.Bitmap)(_bitmap5.getObject()));
 //BA.debugLineNum = 60;BA.debugLine="ListView1LV.AddTwoLinesAndBitmap(\"Unggul Futsal\",";
mostCurrent._listview1lv.AddTwoLinesAndBitmap(BA.ObjectToCharSequence("Unggul Futsal"),BA.ObjectToCharSequence("Jatiwaringin, Bekasi Selatan"),(android.graphics.Bitmap)(_bitmap6.getObject()));
 //BA.debugLineNum = 61;BA.debugLine="ListView1LV.AddTwoLinesAndBitmap(\"Halim Futsal\",\"";
mostCurrent._listview1lv.AddTwoLinesAndBitmap(BA.ObjectToCharSequence("Halim Futsal"),BA.ObjectToCharSequence("Pondok Gede, Bekasi Selatan"),(android.graphics.Bitmap)(_bitmap7.getObject()));
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 68;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 70;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 64;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return "";
}
public static String  _btnbacklv_click() throws Exception{
 //BA.debugLineNum = 79;BA.debugLine="Sub btnbackLV_Click";
 //BA.debugLineNum = 80;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 81;BA.debugLine="StartActivity(Booking)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._booking.getObject()));
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 15;BA.debugLine="Private ListView1LV As ListView";
mostCurrent._listview1lv = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private btnbackLV As Button";
mostCurrent._btnbacklv = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _listview1lv_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 73;BA.debugLine="Sub ListView1LV_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 74;BA.debugLine="nama = Value";
_nama = BA.ObjectToString(_value);
 //BA.debugLineNum = 75;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 76;BA.debugLine="StartActivity(Booking)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._booking.getObject()));
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim nama As String";
_nama = "";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
}
