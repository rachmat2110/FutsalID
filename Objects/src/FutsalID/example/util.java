package FutsalID.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class util {
private static util mostCurrent = new util();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
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
public FutsalID.example.unggul _unggul = null;
public FutsalID.example.viva _viva = null;
public FutsalID.example.httputils2service _httputils2service = null;
public static FutsalID.example.main._dbcommand  _createcommand(anywheresoftware.b4a.BA _ba,String _name,Object[] _parameters) throws Exception{
FutsalID.example.main._dbcommand _cmd = null;
 //BA.debugLineNum = 15;BA.debugLine="public Sub CreateCommand(Name As String, Parameter";
 //BA.debugLineNum = 16;BA.debugLine="Dim cmd As DBCommand";
_cmd = new FutsalID.example.main._dbcommand();
 //BA.debugLineNum = 17;BA.debugLine="cmd.Initialize";
_cmd.Initialize();
 //BA.debugLineNum = 18;BA.debugLine="cmd.Name = Name";
_cmd.Name /*String*/  = _name;
 //BA.debugLineNum = 19;BA.debugLine="If Parameters <> Null Then cmd.Parameters = Param";
if (_parameters!= null) { 
_cmd.Parameters /*Object[]*/  = _parameters;};
 //BA.debugLineNum = 20;BA.debugLine="Return cmd";
if (true) return _cmd;
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return null;
}
public static FutsalID.example.dbrequestmanager  _createrequest(anywheresoftware.b4a.BA _ba,Object _modul) throws Exception{
FutsalID.example.dbrequestmanager _req = null;
 //BA.debugLineNum = 9;BA.debugLine="public Sub CreateRequest(modul As Object) As DBReq";
 //BA.debugLineNum = 10;BA.debugLine="Dim req As DBRequestManager";
_req = new FutsalID.example.dbrequestmanager();
 //BA.debugLineNum = 11;BA.debugLine="req.Initialize(modul, Main.rdcLink)";
_req._initialize /*String*/ ((_ba.processBA == null ? _ba : _ba.processBA),_modul,mostCurrent._main._rdclink /*String*/ );
 //BA.debugLineNum = 12;BA.debugLine="Return req";
if (true) return _req;
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
}
