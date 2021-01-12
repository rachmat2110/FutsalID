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

	Private TSHalim As TabStrip
	Private SV_Halim As ScrollView
	Private pnlPageHalim As Panel
	Private LV_RiviewHalim As ListView
	Private pnl_RiviewHalim As Panel
	Private pnl_SVriviewHalim As ScrollView
	Private lblRating As Label
	Private txtKomen As EditText
	Private txtNama As EditText
	Private Button1 As Button
	Private SV_InsertHalim As ScrollView
	Private pnl_InsertKomenHalim As Panel
	
	'grafik
	Private pnlGrafikHalim As Panel
	Dim Val As Float
	Dim ValBuf(14) As Float
	Dim tm,tmbuf(14) As String
	
	'maps
	Private btnPetaHalim As Button
	Private MapFragment4 As MapFragment
	Dim gmap As GoogleMap
	
	'gambar akun
	Dim pic() As String
	Dim count As Int
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("Halim")
	TSHalim.LoadLayout("pnl_SVHalim", "    Informsi Tempat    ")
	SV_Halim.Panel.LoadLayout("Page1Halim")
	SV_Halim.Panel.Height = pnlPageHalim.Height
	SV_Halim.Panel.Width = pnlPageHalim.Width
	TSHalim.LoadLayout("pnl_SVRiviewHalim", "   Riview   ")
	pnl_SVriviewHalim.Panel.LoadLayout("layRiviewHalim")
	pnl_SVriviewHalim.Panel.Height = pnl_RiviewHalim.Height
	pnl_SVriviewHalim.Panel.Width = pnl_RiviewHalim.Width
	TSHalim.LoadLayout("SV_InsertKomenHalim", "      Beri Komen     ")
	SV_InsertHalim.Panel.LoadLayout("layInsertKomenHalim")
	SV_InsertHalim.Panel.Height = pnl_InsertKomenHalim.Height
	SV_InsertHalim.Panel.Width = pnl_InsertKomenHalim.Width
	
	LV_RiviewHalim.TwoLinesAndBitmap.Label.TextColor = Colors.Black
	LV_RiviewHalim.TwoLinesAndBitmap.SecondLabel.Height = 100
	LV_RiviewHalim.TwoLinesAndBitmap.Label.TextSize = 15
	LV_RiviewHalim.TwoLinesAndBitmap.SecondLabel.TextSize = 13
	pic =Array As String ("user1.png","user2.png", "user3.png","user4.png","user5.png","user6.png")
	GetKomen
	apaja
	

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

'=============================== penentuan dan pembuatan maps ============================'
Sub MapFragment4_Click (Point As LatLng)
	ToastMessageShow("Opening Maps",False)
	Try
		Dim in2 As Intent
		in2.Initialize(in2.ACTION_VIEW, "https://www.google.com/maps/dir//Halim+Futsal+%26+Badminton,+Jalan+Raya+Pondok+Gede,+RW.3,+Halim+Perdana+Kusumah,+East+Jakarta+City,+Jakarta/@-6.2842163,106.8718681,13z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e69f2b24906a207:0x3b0e44110ca6a640!2m2!1d106.9068878!2d-6.2843028")
		Dim job As JavaObject = in2
		job.RunMethod("setPackage", Array("com.google.android.apps.maps"))
		StartActivity(in2)
	Catch
		Msgbox("Google Maps Application is not Found !!","Warning")
	End Try
End Sub

Sub btnPetaHalim_Click
	ToastMessageShow("Opening Maps",False)
	Try
		Dim in2 As Intent
		in2.Initialize(in2.ACTION_VIEW, "https://www.google.com/maps/dir//Halim+Futsal+%26+Badminton,+Jalan+Raya+Pondok+Gede,+RW.3,+Halim+Perdana+Kusumah,+East+Jakarta+City,+Jakarta/@-6.2842163,106.8718681,13z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e69f2b24906a207:0x3b0e44110ca6a640!2m2!1d106.9068878!2d-6.2843028")
		Dim job As JavaObject = in2
		job.RunMethod("setPackage", Array("com.google.android.apps.maps"))
		StartActivity(in2)
	Catch
		Msgbox("Google Maps Application is not Found !!","Warning")
	End Try
End Sub

Sub MapFragment4_Ready
	gmap = MapFragment4.GetMap
	rp.CheckAndRequest(rp.PERMISSION_ACCESS_FINE_LOCATION)
	Wait For Activity_PermissionResult (Permission As String, Result As Boolean)
	gmap.MyLocationEnabled = Result
	gmap.MyLocationEnabled = False
	Dim m1 As Marker = gmap.AddMarker(-6.2843, 106.90689,"Halim Futsal")
	m1.Snippet="Halim Perdana Kusuma, Jakarta timur"
	Dim cp As CameraPosition
	cp.Initialize(-6.2843, 106.90689, 17)
	gmap.MoveCamera(cp)
End Sub
'=============================== akhir penentuan dan pembuatan maps ============================'

Sub InsertKomen (nama As String, rating As Int, komen As String)
	Dim cmd As DBCommand = util.CreateCommand("BookingHalim",Array(nama,rating,komen))
	Dim j As HttpJob = util.CreateRequest(Me).ExecuteBatch(Array(cmd),Null)
	Wait For (j) JobDone(j As HttpJob)
	If j.Success Then
		ToastMessageShow("Rating sudah dimasukkan",True)
		LV_RiviewHalim.Clear
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
	Dim cmd As DBCommand = util.CreateCommand("GetCommentHalim",Null)
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			count = Rnd(0,5)
			LV_RiviewHalim.AddTwoLinesAndBitmap(row(0) ,row(2) ,LoadBitmap(File.DirAssets, pic(count)))
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
	Dim cmd As DBCommand = util.CreateCommand("AvgRateHalim",Null)
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
	LD.Target = pnlGrafikHalim
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