B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
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
	Private SV_Estadio As ScrollView
	Private pnlPageEstadio As Panel
	Private TSEstadio As TabStrip
	Private pnl_SVriviewEstadio As ScrollView
	Private pnl_RiviewEstadio As Panel
	Private LV_RiviewEstadio As ListView
	
	'grafik
	Private pnlGrafikEstadio As Panel
	Dim Val As Float
	Dim ValBuf(14) As Float
	Dim tm,tmbuf(14) As String
	
	'maps
	Private btnPetaEstadio As Button
	Private MapFragment3 As MapFragment
	Dim gmap As GoogleMap
	
	'gambar akun
	Dim pic() As String
	Dim count As Int

	Private pnl_InsertKomenEstadio As Panel
	Private SV_InsertEstadio As ScrollView
	Private lblRating As Label
	Private Button1 As Button
	Private txtKomen As EditText
	Private txtNama As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("Estadio")
	TSEstadio.LoadLayout("pnl_SVEstadio", "    Informsi Tempat    ")
	SV_Estadio.Panel.LoadLayout("Page1Estadio")
	SV_Estadio.Panel.Height = pnlPageEstadio.Height
	SV_Estadio.Panel.Width = pnlPageEstadio.Width
	TSEstadio.LoadLayout("pnl_SVRiviewEstadio", "   Riview   ")
	pnl_SVriviewEstadio.Panel.LoadLayout("layRiviewEstadio")
	pnl_SVriviewEstadio.Panel.Height = pnl_RiviewEstadio.Height
	pnl_SVriviewEstadio.Panel.Width = pnl_RiviewEstadio.Width
	TSEstadio.LoadLayout("SV_InsertKomenEstadio", "      Beri Komen     ")
	SV_InsertEstadio.Panel.LoadLayout("layInsertKomenEstadio")
	SV_InsertEstadio.Panel.Height = pnl_InsertKomenEstadio.Height
	SV_InsertEstadio.Panel.Width = pnl_InsertKomenEstadio.Width
	
	LV_RiviewEstadio.TwoLinesAndBitmap.Label.TextColor = Colors.Black
	LV_RiviewEstadio.TwoLinesAndBitmap.SecondLabel.Height = 100
	LV_RiviewEstadio.TwoLinesAndBitmap.Label.TextSize = 15
	LV_RiviewEstadio.TwoLinesAndBitmap.SecondLabel.TextSize = 13
	pic =Array As String ("user1.png","user2.png", "user3.png","user4.png","user5.png","user6.png")
	GetKomen
	apaja

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

'=============================== penentuan dan pembuatan maps ============================'
Sub MapFragment3_Click (Point As LatLng)
	ToastMessageShow("Opening Maps",False)
	Try
		Dim in2 As Intent
		in2.Initialize(in2.ACTION_VIEW, "https://www.google.com/maps/dir//Estadio+Futsal,+Jl.+Perjuangan+No.66,+RT.003%2FRW.008,+Marga+Mulya,+Bekasi+Utara,+Bekasi+City,+West+Java+17143/@-6.2314522,107.0004765,17z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e698c1f689ec3b9:0x97b2ba38af7411b6!2m2!1d107.0026652!2d-6.2314575")
		Dim job As JavaObject = in2
		job.RunMethod("setPackage", Array("com.google.android.apps.maps"))
		StartActivity(in2)
		Activity.Finish
	Catch
		Msgbox("Google Maps Application is not Found !!","Warning")
	End Try
End Sub

Sub btnPetaEstadio_Click
	ToastMessageShow("Opening Maps",False)
	Try
		Dim in2 As Intent
		in2.Initialize(in2.ACTION_VIEW, "https://www.google.com/maps/dir//Estadio+Futsal,+Jl.+Perjuangan+No.66,+RT.003%2FRW.008,+Marga+Mulya,+Bekasi+Utara,+Bekasi+City,+West+Java+17143/@-6.2314522,107.0004765,17z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e698c1f689ec3b9:0x97b2ba38af7411b6!2m2!1d107.0026652!2d-6.2314575")
		Dim job As JavaObject = in2
		job.RunMethod("setPackage", Array("com.google.android.apps.maps"))
		StartActivity(in2)
		Activity.Finish
	Catch
		Msgbox("Google Maps Application is not Found !!","Warning")
	End Try
End Sub

Sub MapFragment3_Ready
	gmap = MapFragment3.GetMap
	rp.CheckAndRequest(rp.PERMISSION_ACCESS_FINE_LOCATION)
	Wait For Activity_PermissionResult (Permission As String, Result As Boolean)
	gmap.MyLocationEnabled = Result
	gmap.MyLocationEnabled = False
	Dim m1 As Marker = gmap.AddMarker(-6.231457, 107.002675,"Estadio Futsal")
	m1.Snippet="Marga Mulia, Bekasi"
	Dim cp As CameraPosition
	cp.Initialize(-6.231457, 107.002675, 17)
	gmap.MoveCamera(cp)
End Sub
'=============================== akhir penentuan dan pembuatan maps ============================'

Sub InsertKomen (nama As String, rating As Int, komen As String)
	Dim cmd As DBCommand = util.CreateCommand("BookingEstadio",Array(nama,rating,komen))
	Dim j As HttpJob = util.CreateRequest(Me).ExecuteBatch(Array(cmd),Null)
	Wait For (j) JobDone(j As HttpJob)
	If j.Success Then
		ToastMessageShow("Rating sudah dimasukkan",True)
		LV_RiviewEstadio.Clear
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
	Dim cmd As DBCommand = util.CreateCommand("GetCommentEstadio",Null)
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			count = Rnd(0,5)
			LV_RiviewEstadio.AddTwoLinesAndBitmap(row(0) ,row(2) ,LoadBitmap(File.DirAssets, pic(count)))
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
	Dim cmd As DBCommand = util.CreateCommand("AvgRateEstadio",Null)
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
	LD.Target = pnlGrafikEstadio
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