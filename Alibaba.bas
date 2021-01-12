B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=8
@EndOfDesignText@
	#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: false
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	'maps
	Dim rp As RuntimePermissions
	Dim nil As Int
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private pnlGrafikAlibaba As Panel
	Private TSAlibaba As TabStrip
	Private SV_Alibaba As ScrollView
	Private pnlPageAlibaba As Panel
	Private pnl_SVriviewAlibaba As ScrollView
	Private pnl_RiviewAL As Panel
	Private SV_InsertAL As ScrollView
	Private pnl_InsertKomen As Panel
	
	'maps
	Private btnPetaAlibaba As Button
	Private MapFragment1 As MapFragment
	Dim gmap As GoogleMap
	Private ImageView2 As ImageView
	Private LV_RiviewAL As ListView
	
	'gambar akun
	Dim pic() As String
	Dim count As Int
	
	'untuk charts
	Dim Val As Float
	Dim ValBuf(14) As Float
	Dim tm,tmbuf(14) As String
	Private pnlGrafikAlibaba As Panel
	Private lblRating As Label
	Private Button1 As Button
	Private txtKomen As EditText
	Private txtNama As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("Alibaba")
	TSAlibaba.LoadLayout("pnl_SVAlibaba", "    Informsi Tempat    ")
	SV_Alibaba.Panel.LoadLayout("Page1Alibaba")
	SV_Alibaba.Panel.Height = pnlPageAlibaba.Height
	SV_Alibaba.Panel.Width = pnlPageAlibaba.Width
	TSAlibaba.LoadLayout("pnl_SVRiviewAlibaba", "   Riview   ")
	pnl_SVriviewAlibaba.Panel.LoadLayout("layRiviewAlibaba")
	pnl_SVriviewAlibaba.Panel.Height = pnl_RiviewAL.Height
	pnl_SVriviewAlibaba.Panel.Width = pnl_RiviewAL.Width
	TSAlibaba.LoadLayout("SV_InsertKomen", "      Beri Komen     ")
	SV_InsertAL.Panel.LoadLayout("layInsertKomenAlibaba")
	SV_InsertAL.Panel.Height = pnl_InsertKomen.Height
	SV_InsertAL.Panel.Width = pnl_InsertKomen.Width
	
	LV_RiviewAL.TwoLinesAndBitmap.Label.TextColor = Colors.Black
	LV_RiviewAL.TwoLinesAndBitmap.SecondLabel.Height = 100
	LV_RiviewAL.TwoLinesAndBitmap.Label.TextSize = 15
	LV_RiviewAL.TwoLinesAndBitmap.SecondLabel.TextSize = 13
	pic =Array As String ("user1.png","user2.png", "user3.png","user4.png","user5.png","user6.png")
	GetKomen
	apaja
	

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

'=============================== penentuan dan pembuatan maps ============================'
Sub MapFragment1_Click (Point As LatLng)
	ToastMessageShow("Opening Maps",False)
	Try
		Dim in2 As Intent
		in2.Initialize(in2.ACTION_VIEW, "https://www.google.com/maps/place/Alibaba+II+Futsal+Center/@-6.2848047,106.9970758,19z/data=!4m5!3m4!1s0x2e698dfac1cdf14d:0x2d73202374bc72f3!8m2!3d-6.28484!4d106.9976051")
		Dim job As JavaObject = in2
		job.RunMethod("setPackage", Array("com.google.android.apps.maps"))
		StartActivity(in2)
		Activity.Finish
	Catch
		Msgbox("Google Maps Application is not Found !!","Warning")
	End Try
End Sub

Sub btnPetaAlibaba_Click
	ToastMessageShow("Opening Maps",False)
	Try
		Dim in2 As Intent
		in2.Initialize(in2.ACTION_VIEW, "https://www.google.com/maps/dir//Alibaba+II+Futsal+Center,+Jalan+Lumbu+Utara+2,+RT.007%2FRW.005,+Bojong+Rawalumbu,+Bekasi+City,+West+Java/@-6.2847532,106.9625851,13z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e698dfac1cdf14d:0x2d73202374bc72f3!2m2!1d106.9976048!2d-6.2848397")
		Dim job As JavaObject = in2
		job.RunMethod("setPackage", Array("com.google.android.apps.maps"))
		StartActivity(in2)
	Catch
		Msgbox("Google Maps Application is not Found !!","Warning")
	End Try
End Sub

Sub MapFragment1_Ready
	gmap = MapFragment1.GetMap
	rp.CheckAndRequest(rp.PERMISSION_ACCESS_FINE_LOCATION)
	Wait For Activity_PermissionResult (Permission As String, Result As Boolean)
	gmap.MyLocationEnabled = Result
	gmap.MyLocationEnabled = False
	Dim m1 As Marker = gmap.AddMarker(-6.284824, 106.997613,"Alibaba Futsal")
	m1.Snippet="Pekayon Jaya, Bekasi"
	Dim cp As CameraPosition
	cp.Initialize(-6.284824, 106.997613, 17)
	gmap.MoveCamera(cp)
End Sub

'==================== akhir penentuan dan pembuatan maps =========================='

Sub InsertKomen (nama As String, rating As Int, komen As String)
	Dim cmd As DBCommand = util.CreateCommand("BookingAlibaba",Array(nama,rating,komen))
	Dim j As HttpJob = util.CreateRequest(Me).ExecuteBatch(Array(cmd),Null)
	Wait For (j) JobDone(j As HttpJob)
	If j.Success Then
		ToastMessageShow("Rating sudah dimasukkan",True)
		LV_RiviewAL.Clear
		apaja
		GetKomen
	Else
		Log(j.ErrorMessage)
	End If
	j.Release

End Sub
'==================== akhir memasukkan komen ke database ==================== '

'==================== awal memndapatkan komen dari database ke listview ==================== '
Sub GetKomen
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("GetCommentAlibaba",Null)
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			count = Rnd(0,5)
			LV_RiviewAL.AddTwoLinesAndBitmap(row(0) ,row(2) ,LoadBitmap(File.DirAssets, pic(count)))
		Next
	Else
		Log(j.ErrorMessage)
	End If
	j.Release
End Sub
'==================== akhir memndapatkan komen dari database ke listview ==================== '

'============================= pembuatan graph ==============================='

Sub apaja
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("AvgRate",Null)
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			nil = row(0)
			Val = Round2(nil,1)
			ValBuf(0) = ValBuf(1)
			ValBuf(1) = ValBuf(2)
			ValBuf(2) = ValBuf(3)
			ValBuf(3) = ValBuf(4)
			ValBuf(4) = ValBuf(5)
			ValBuf(5) = ValBuf(6)
			ValBuf(6) = ValBuf(7)
			ValBuf(7) = ValBuf(8)
			ValBuf(8) = ValBuf(9)
			ValBuf(9) = ValBuf(10)
			ValBuf(10) = ValBuf(11)
			ValBuf(11) = ValBuf(12)
			ValBuf(12) = Val
	
			tmbuf(0) = tmbuf(1)
			tmbuf(1) = tmbuf(2)
			tmbuf(2) = tmbuf(3)
			tmbuf(3) = tmbuf(4)
			tmbuf(4) = tmbuf(5)
			tmbuf(5) = tmbuf(6)
			tmbuf(6) = tmbuf(7)
			tmbuf(7) = tmbuf(8)
			tmbuf(8) = tmbuf(9)
			tmbuf(9) = tmbuf(10)
			tmbuf(10) = tmbuf(11)
			tmbuf(11) = tmbuf(12)
			tmbuf(12) = tm
		Next
		graphdraw
	Else
		Log(j.ErrorMessage)
	End If
	j.Release
End Sub


Sub graphdraw
	Dim LD As LineData
	LD.Initialize
	LD.Target = pnlGrafikAlibaba
	Charts.AddLineColor(LD,Colors.rgb(46,204,113))'Colors.rgb(152,51,81) for red
	
	Charts.AddLinePoint(LD,tmbuf(0),ValBuf(0),True)
	Charts.AddLinePoint(LD,tmbuf(1),ValBuf(1),True)
	Charts.AddLinePoint(LD,tmbuf(2),ValBuf(2),True)
	Charts.AddLinePoint(LD,tmbuf(3),ValBuf(3),True)
	Charts.AddLinePoint(LD,tmbuf(4),ValBuf(4),True)
	Charts.AddLinePoint(LD,tmbuf(5),ValBuf(5),True)
	Charts.AddLinePoint(LD,tmbuf(6),ValBuf(6),True)
	Charts.AddLinePoint(LD,tmbuf(7),ValBuf(7),True)
	Charts.AddLinePoint(LD,tmbuf(8),ValBuf(8),True)
	Charts.AddLinePoint(LD,tmbuf(9),ValBuf(9),True)
	Charts.AddLinePoint(LD,tmbuf(10),ValBuf(10),True)
	Charts.AddLinePoint(LD,tmbuf(11),ValBuf(11),True)
	Charts.AddLinePoint(LD,tmbuf(12),ValBuf(12),True)
	Dim G As Graph
	G.Initialize
	'G.Title = "Rating"
	G.YStart = 0
	G.YEnd = 10
	G.YInterval = 1
	'G.AxisColor = Colors.rgb(152,51,81)
	G.AxisColor = Colors.ARGB(100,0,0,0)
	Charts.DrawLineChart(G,LD,Colors.White)
End Sub

Sub timer_Tick
	
	
	
End Sub
'===================== akhir pembuatan graph ======================='


Sub lblRating_Click
	Dim nd As NumberDialog
	Dim ret As Object
	nd.Digits = 0
	nd.Number = 1
	nd.Decimal = 0
	ret = nd.Show("Rating Untuk Tempat (9/9)", "OKE", "", "", Null)
	lblRating.Text = nd.Number
End Sub

Sub Button1_Click
	InsertKomen(txtNama.Text,lblRating.Text,txtKomen.Text)
End Sub