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

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private btnback As Button
	Private lblLokasi As Label
	Private btnPesan As Button
	Private satOption, sunOption As Int
	Private startMonday As Boolean
	Private ListView1LV As ListView
	Private lblPukul As Label
	Private lblWaktu As Label
	Private lblTanggal As Label
	Private lblAtasNama As Label
	Private Label1 As Label
	Private txtAtasNama As EditText
	Private pnlriview As Panel
	Private pnl_SVriview As ScrollView
	Private txtemail As EditText
	Dim BGMail As BackgroundMail
	Private Logo As ImageView
	Private Label3 As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("Booking")
	lblLokasi.Text= TempatFutsal.nama
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub btnback_Click
	Activity.Finish
	StartActivity(Main)
End Sub

Sub lblLokasi_Click
	lblLokasi.Text = TempatFutsal.nama
	StartActivity(TempatFutsal)
	Activity.Finish
End Sub

Sub btnPesan_Click
	Label3.Text = (GetRandomString("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",6))
	cek(lblLokasi.Text, lblPukul.Text, lblWaktu.Text, lblTanggal.Text, txtAtasNama.Text, txtemail.Text,Label3.Text)
End Sub

Sub lblPukul_Click
	Dim td As TimeDialog
	Dim txt As String
	td.Hour = DateTime.GetHour(DateTime.Now)
	td.Minute = DateTime.GetMinute(DateTime.Now)
	td.Is24Hours = True
	ret = td.Show("Set the required time", "futsalID Time Dialog", "Yes", "No", "Maybe", Null)
	lblPukul.Text = td.Hour & ":" & td.Minute
End Sub

Sub lblTanggal_Click
	Dim I As Long
	Dim D As DateDialogs
	
	Log(DateTime.DateFormat)
	D.Initialize(Activity, DateTime.Now)
	D.RedSaturday=satOption
	D.RedSunday=sunOption
	D.StartOnMonday=startMonday
	I=D.Show("Select Date")
	Log(DateTime.DateFormat)
	
	lblTanggal.Text = DateTime.GetYear(D.DateSelected) & "-" & DateTime.GetMonth(D.DateSelected) & "-" & DateTime.GetDayOfMonth(D.DateSelected)
	Dim temp As String = DateTime.DateFormat
	DateTime.DateFormat = "yyyy - MM - dd"
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	Dim i As Int
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		i = Msgbox2("Yakin Ingin keluar?", "Info", "Ok", "Cancel"," ",Null)
		Select i
			Case DialogResponse.POSITIVE
				Activity.Finish
				StartActivity(Main)
			Case DialogResponse.CANCEL
				Return True
		End Select
	End If
End Sub

Sub lblWaktu_Click
	Dim nd As NumberDialog
	Dim ret As Object
	nd.Digits = 0
	nd.Number = 1
	nd.Decimal = 0
	ret = nd.Show("Jumlah Waktu Bermain", "OKE", "", "", Null)
	lblWaktu.Text = nd.Number
	Label1.Text = "Jam"
End Sub

Sub Register(lokasi As String, jam As String, jml_waktu As Int, tanggal As String, nama As String, email As String, kode As String)
	Dim cmd As DBCommand = util.CreateCommand("DaftarBooking",Array(lokasi,jam,jml_waktu,tanggal,nama,email,kode))
	Dim j As HttpJob = util.CreateRequest(Me).ExecuteBatch(Array(cmd),Null)
	Wait For (j) JobDone(j As HttpJob)
	If j.Success Then
		If txtemail.Text.Trim <> "" Then
			Try
				BGMail.Initialize("")
				BGMail.GmailUserName = "rachmat2110@gmail.com"
				BGMail.GmailPassword = "Validasimamat123*"
				BGMail.FormSubject = "Pemesanan via FutsalID"
				BGMail.MailTo = txtemail.Text.Trim
				BGMail.FormBody= "Pemesanan Booking Tempat Futsal" & CRLF & "Atas Nama : " & txtAtasNama.Text & CRLF & "Bermain Pada Pukul : " & lblPukul.Text & CRLF & "Tempat pemesanan : " & lblLokasi.Text & CRLF & "Dengan Kode pemesanan : " & Label3.Text
				BGMail.SendingMessageSuccess = "Sent Successfully"
				BGMail.ProcessVisibility = True
				BGMail.send
			Catch
				Log(LastException)
			End Try
		Else
			ToastMessageShow("Email Connot Be Empty",True)
			Return
		End If
		Msgbox2("Pemesanan anda sudah terverifikasi","          Notifikasi        ","YES","","",LoadBitmap(File.DirAssets, "Lambang(1).png.jpg"))
	Else
		Log(j.ErrorMessage)
	End If
	j.Release
End Sub

Sub cek (lokasi As String, jam As String, jml_waktu As Int, tanggal As String, nama As String, email As String, kode As String)
	Dim cmd As DBCommand = util.CreateCommand("BookingTest",Array(lokasi,jam))
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Wait For (req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		If res.Rows.Size > 2 Then
			ToastMessageShow("Lokasi pada jam sudah penuh",True)
			If res.Rows.Size > 0 Then
				ToastMessageShow("Jam sudah ada yang booking",True)
			Else
				Register(lokasi,jam,jml_waktu,tanggal,nama,email,kode)
			End If
		Else
			Register(lokasi,jam,jml_waktu,tanggal,nama,email,kode)
		End If
	Else
		Log(j.ErrorMessage)
	End If
	j.Release
End Sub

Sub GetRandomString(Source As String,counts As Int) As String
	Dim Str As StringBuilder
	Str.Initialize
	For i = 0 To counts -1
		Str.Append(Source.CharAt(Rnd(0,Source.Length)))
	Next
	Return Str.ToString
	txtAtasNama.Text = Str.ToString
End Sub
