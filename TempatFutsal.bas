B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim nama As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Private ListView1LV As ListView

	Private btnbackLV As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("pnlLV_tempatBooking")
	
	Dim Tempat As String
	
	Dim Bitmap1 As Bitmap
	Dim Bitmap2 As Bitmap
	Dim Bitmap3 As Bitmap
	Dim Bitmap4 As Bitmap
	Dim Bitmap5 As Bitmap
	Dim Bitmap6 As Bitmap
	Dim Bitmap7 As Bitmap
	
	Bitmap1.Initialize(File.DirAssets, "alibaba.png")
	Bitmap2.Initialize(File.DirAssets, "estadio.png")
	Bitmap3.Initialize(File.DirAssets, "next.png")
	Bitmap4.Initialize(File.DirAssets, "town.png")
	Bitmap5.Initialize(File.DirAssets, "viva.png")
	Bitmap6.Initialize(File.DirAssets, "unggul.png")
	Bitmap7.Initialize(File.DirAssets, "halim.png")

	ListView1LV.TwoLinesAndBitmap.Label.TextColor = Colors.Black
	ListView1LV.TwoLinesAndBitmap.Label.TextSize = 16
	ListView1LV.TwoLinesAndBitmap.Label.Left = 75dip
	ListView1LV.TwoLinesAndBitmap.Label.Typeface = Typeface.DEFAULT_BOLD
	ListView1LV.TwoLinesAndBitmap.ItemHeight = 60dip
	ListView1LV.TwoLinesAndBitmap.ImageView.Height = 52dip
	ListView1LV.TwoLinesAndBitmap.ImageView.Width  = 52dip
	ListView1LV.TwoLinesAndBitmap.ImageView.Top = 3dip
	ListView1LV.TwoLinesAndBitmap.ImageView.Left = 18dip
	ListView1LV.TwoLinesAndBitmap.ImageView.Gravity = Gravity.FILL
	ListView1LV.TwoLinesAndBitmap.SecondLabel.Left = 75dip
	ListView1LV.TwoLinesAndBitmap.SecondLabel.TextSize = 14
	
	ListView1LV.AddTwoLinesAndBitmap("Alibaba Futsal","Pekayon, Bekasi Selatan",Bitmap1)
	ListView1LV.AddTwoLinesAndBitmap("Estadio Futsal","JL. Perjuangan, Bekasi Utara",Bitmap2)
	ListView1LV.AddTwoLinesAndBitmap("Next Futsal","Kalimalang, Bekasi Selatan",Bitmap3)
	ListView1LV.AddTwoLinesAndBitmap("Town Futsal","Pekayon, Bekasi Selatan",Bitmap4)
	ListView1LV.AddTwoLinesAndBitmap("Viva Futsal","Pekayon, Bekasi Selatan",Bitmap5)
	ListView1LV.AddTwoLinesAndBitmap("Unggul Futsal","Jatiwaringin, Bekasi Selatan",Bitmap6)
	ListView1LV.AddTwoLinesAndBitmap("Halim Futsal","Pondok Gede, Bekasi Selatan",Bitmap7)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub ListView1LV_ItemClick (Position As Int, Value As Object)
	nama = Value
	Activity.Finish
	StartActivity(Booking)
End Sub

Sub btnbackLV_Click
	Activity.Finish
	StartActivity(Booking)
End Sub