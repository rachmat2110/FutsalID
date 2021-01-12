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
	Dim rp As RuntimePermissions
	Dim nil As Int
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	'grafik
	Dim Val As Float
	Dim ValBuf(14) As Float
	Dim tm,tmbuf(14) As String
	
	'maps
	Dim gmap As GoogleMap
	
	'gambar akun
	Dim pic() As String
	Dim count As Int
	
	Private TSUnggul As TabStrip
	Private pnlGrafikUnggul As Panel
	Private SV_Unggul As ScrollView
	Private pnlPageUnggul As Panel
	Private btnPetaUnggul As Button
	Private MapFragment6 As MapFragment
	Private pnl_SVriviewUnggul As ScrollView
	Private pnl_RiviewUnggul As Panel
	Private LV_RiviewUnggul As ListView
	Private SV_InsertUnggul As ScrollView
	Private pnl_InsertKomenUnggul As Panel
	Private lblRating As Label
	Private Button1 As Button
	Private txtKomen As EditText
	Private txtNama As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("Unggul")
	TSUnggul.LoadLayout("pnl_SVUnggul", "    Informsi Tempat    ")
	SV_Unggul.Panel.LoadLayout("Page1Unggul")
	SV_Unggul.Panel.Height = pnlPageUnggul.Height
	SV_Unggul.Panel.Width = pnlPageUnggul.Width
	TSUnggul.LoadLayout("pnl_SVRiviewUnggul", "   Riview   ")
	pnl_SVriviewUnggul.Panel.LoadLayout("layRiviewUnggul")
	pnl_SVriviewUnggul.Panel.Height = pnl_RiviewUnggul.Height
	pnl_SVriviewUnggul.Panel.Width = pnl_RiviewUnggul.Width
	TSUnggul.LoadLayout("SV_InsertKomenUnggul", "      Beri Komen     ")
	SV_InsertUnggul.Panel.LoadLayout("layInsertKomenUnggul")
	SV_InsertUnggul.Panel.Height = pnl_InsertKomenUnggul.Height
	SV_InsertUnggul.Panel.Width = pnl_InsertKomenUnggul.Width
	
	LV_RiviewUnggul.TwoLinesAndBitmap.Label.TextColor = Colors.Black
	LV_RiviewUnggul.TwoLinesAndBitmap.SecondLabel.Height = 100
	LV_RiviewUnggul.TwoLinesAndBitmap.Label.TextSize = 15
	LV_RiviewUnggul.TwoLinesAndBitmap.SecondLabel.TextSize = 13
	pic =Array As String ("user1.png","user2.png", "user3.png","user4.png","user5.png","user6.png")
	GetKomen
	apaja

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub MapFragment6_Click (Point As LatLng)
	ToastMessageShow("Opening Maps",False)
	Try
		Dim in2 As Intent
		in2.Initialize(in2.ACTION_VIEW, "https://www.google.com/maps/dir//Unggul+Futsal,+Jl.+Raya+Kranggan,+RT.001%2FRW.007,+Jatiraden,+Kec.+Jatisampurna,+Kota+Bks,+Jawa+Barat+17433/@-6.3514244,106.886757,13z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e699330d00e96ef:0x6cac6af6862b14b!2m2!1d106.9217767!2d-6.3515109")
		Dim job As JavaObject = in2
		job.RunMethod("setPackage", Array("com.google.android.apps.maps"))
		StartActivity(in2)
	Catch
		Msgbox("Google Maps Application is not Found !!","Warning")
	End Try
End Sub

Sub btnPetaUnggul_Click
	ToastMessageShow("Opening Maps",False)
	Try
		Dim in2 As Intent
		in2.Initialize(in2.ACTION_VIEW, "https://www.google.com/maps/dir//Unggul+Futsal,+Jl.+Raya+Kranggan,+RT.001%2FRW.007,+Jatiraden,+Kec.+Jatisampurna,+Kota+Bks,+Jawa+Barat+17433/@-6.3514244,106.886757,13z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e699330d00e96ef:0x6cac6af6862b14b!2m2!1d106.9217767!2d-6.3515109")
		Dim job As JavaObject = in2
		job.RunMethod("setPackage", Array("com.google.android.apps.maps"))
		StartActivity(in2)
	Catch
		Msgbox("Google Maps Application is not Found !!","Warning")
	End Try
End Sub

Sub MapFragment6_Ready
	gmap = MapFragment6.GetMap
	rp.CheckAndRequest(rp.PERMISSION_ACCESS_FINE_LOCATION)
	Wait For Activity_PermissionResult (Permission As String, Result As Boolean)
	gmap.MyLocationEnabled = Result
	gmap.MyLocationEnabled = False
	Dim m1 As Marker = gmap.AddMarker(-6.35151, 106.92178,"Unggul Futsal")
	m1.Snippet="Jatisampurna, Bekasi"
	Dim cp As CameraPosition
	cp.Initialize(-6.35151, 106.92178, 19)
	gmap.MoveCamera(cp)
End Sub

Sub InsertKomen (nama As String, rating As Int, komen As String)
	Dim cmd As DBCommand = util.CreateCommand("BookingUnggul",Array(nama,rating,komen))
	Dim j As HttpJob = util.CreateRequest(Me).ExecuteBatch(Array(cmd),Null)
	Wait For (j) JobDone(j As HttpJob)
	If j.Success Then
		ToastMessageShow("Rating sudah dimasukkan",True)
		LV_RiviewUnggul.Clear
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
	Dim cmd As DBCommand = util.CreateCommand("GetCommentUnggul",Null)
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			count = Rnd(0,5)
			LV_RiviewUnggul.AddTwoLinesAndBitmap(row(0) ,row(2) ,LoadBitmap(File.DirAssets, pic(count)))
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
	Dim cmd As DBCommand = util.CreateCommand("AvgRateUnggul",Null)
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
	LD.Target = pnlGrafikUnggul
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