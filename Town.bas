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

	Private pnlGrafikTown As Panel
	Private TSTown As TabStrip
	Private SV_Town As ScrollView
	Private pnlPageTown As Panel
	Private pnl_RiviewTown As Panel
	Private pnl_SVriviewTown As ScrollView
	Private pnl_InsertKomenTown As Panel
	Private SV_InsertTown As ScrollView
	Private LV_RiviewTown As ListView
	
	'maps
	Dim gmap As GoogleMap
	Private MapFragment2 As MapFragment
	
	'gambar akun
	Dim pic() As String
	Dim count As Int
	
	'untuk charts
	Dim Val As Float
	Dim ValBuf(14) As Float
	Dim tm,tmbuf(14) As String
	Private pnlGrafikTown As Panel

	Private lblRating As Label
	Private txtKomen As EditText
	Private txtNama As EditText
	Private Button1 As Button
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("Town")
	TSTown.LoadLayout("pnl_SVTown", "    Informsi Tempat    ")
	SV_Town.Panel.LoadLayout("Page1Town")
	SV_Town.Panel.Height = pnlPageTown.Height
	SV_Town.Panel.Width = pnlPageTown.Width
	TSTown.LoadLayout("pnl_SVRiviewTown", "   Riview   ")
	pnl_SVriviewTown.Panel.LoadLayout("layRiviewTown")
	pnl_SVriviewTown.Panel.Height = pnl_RiviewTown.Height
	pnl_SVriviewTown.Panel.Width = pnl_RiviewTown.Width
	TSTown.LoadLayout("SV_InsertKomenTown", "      Beri Komen     ")
	SV_InsertTown.Panel.LoadLayout("layInsertKomenTown")
	SV_InsertTown.Panel.Height = pnl_InsertKomenTown.Height
	SV_InsertTown.Panel.Width = pnl_InsertKomenTown.Width
	
	LV_RiviewTown.TwoLinesAndBitmap.Label.TextColor = Colors.Black
	LV_RiviewTown.TwoLinesAndBitmap.SecondLabel.Height = 100
	LV_RiviewTown.TwoLinesAndBitmap.Label.TextSize = 15
	LV_RiviewTown.TwoLinesAndBitmap.SecondLabel.TextSize = 13
	pic =Array As String ("user1.png","user2.png", "user3.png","user4.png","user5.png","user6.png")
	GetKomen
	apaja
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

'=============================== penentuan dan pembuatan maps ============================'
Sub MapFragment2_Click (Point As LatLng)
	ToastMessageShow("Opening Maps",False)
	Try
		Dim in2 As Intent
		in2.Initialize(in2.ACTION_VIEW, "https://www.google.com/maps/dir//Futsal+Town,+Gang+Hj+Didi,+Jl.+Kemandoran,+RT.003%2FRW.022,+Pekayon+Jaya,+Kec.+Bekasi+Sel.,+Kota+Bks,+Jawa+Barat+17148/@-6.2599342,106.9832881,17z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e698dc9aa691b2b:0x494258b19afbde83!2m2!1d106.9854768!2d-6.2599395")
		Dim job As JavaObject = in2
		job.RunMethod("setPackage", Array("com.google.android.apps.maps"))
		StartActivity(in2)
	Catch
		Msgbox("Google Maps Application is not Found !!","Warning")
	End Try
End Sub

Sub btnPetaTown_Click
	ToastMessageShow("Opening Maps",False)
	Try
		Dim in2 As Intent
		in2.Initialize(in2.ACTION_VIEW, "https://www.google.com/maps/dir//Futsal+Town,+Gang+Hj+Didi,+Jl.+Kemandoran,+RT.003%2FRW.022,+Pekayon+Jaya,+Kec.+Bekasi+Sel.,+Kota+Bks,+Jawa+Barat+17148/@-6.2599342,106.9832881,17z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e698dc9aa691b2b:0x494258b19afbde83!2m2!1d106.9854768!2d-6.2599395")
		Dim job As JavaObject = in2
		job.RunMethod("setPackage", Array("com.google.android.apps.maps"))
		StartActivity(in2)
	Catch
		Msgbox("Google Maps Application is not Found !!","Warning")
	End Try
End Sub

Sub MapFragment2_Ready
	gmap = MapFragment2.GetMap
	rp.CheckAndRequest(rp.PERMISSION_ACCESS_FINE_LOCATION)
	Wait For Activity_PermissionResult (Permission As String, Result As Boolean)
	gmap.MyLocationEnabled = Result
	gmap.MyLocationEnabled = False
	Dim m1 As Marker = gmap.AddMarker(-6.25994, 106.98548,"Town Futsal")
	m1.Snippet="Pekayon Jaya, Bekasi"
	Dim cp As CameraPosition
	cp.Initialize(-6.25994, 106.98548, 18)
	gmap.MoveCamera(cp)
End Sub
'==================== akhir penentuan dan pembuatan maps =========================='

Sub InsertKomen (nama As String, rating As Int, komen As String)
	Dim cmd As DBCommand = util.CreateCommand("BookingTown",Array(nama,rating,komen))
	Dim j As HttpJob = util.CreateRequest(Me).ExecuteBatch(Array(cmd),Null)
	Wait For (j) JobDone(j As HttpJob)
	If j.Success Then
		ToastMessageShow("Rating sudah dimasukkan",True)
		LV_RiviewTown.Clear
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
	Dim cmd As DBCommand = util.CreateCommand("GetCommentTown",Null)
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			count = Rnd(0,5)
			LV_RiviewTown.AddTwoLinesAndBitmap(row(0) ,row(2) ,LoadBitmap(File.DirAssets, pic(count)))
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
	Dim cmd As DBCommand = util.CreateCommand("AvgRateTown",Null)
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
	LD.Target = pnlGrafikTown
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