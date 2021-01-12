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
	Dim judul As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private ImageView1 As ImageView
	Private Label1 As Label
	Private Label2 As Label
	Private Button1 As Button
	Private SV_Info As ScrollView
	Private pnlDetail As Panel
	Private ButtonWA As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("pnl_SVInfo")
	SV_Info.Panel.LoadLayout("layInfo")
	SV_Info.Panel.Height = pnlDetail.Height
	SV_Info.Panel.Width = pnlDetail.Width
	FeedTest(judul)

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub FeedTest(juduls As String)
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("FeedTesting",Array(juduls))
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)
		For Each row() As Object In res.Rows
			Label1.Text = row(0)
			Label2.Text = row(1)
			Dim gambar As Bitmap = req.BytesToImage(row(5))
			ImageView1.Bitmap = gambar
		Next
	Else
		Log(j.ErrorMessage)
	End If
	j.Release
End Sub

Sub ButtonWA_Click
	Try
		Dim in3 As Intent
		in3.Initialize(in3.ACTION_VIEW, "https://api.whatsapp.com/send?phone=6282111316385&text=Hallo Min%20Saya Mau Menanyakan Perihal Tournament yang terdapat pada Aplikasi Futsal.ID %20Termakasih")
		Dim job As JavaObject = in3
		job.RunMethod("setPackage", Array("com.whatsapp"))
		StartActivity(in3)
		Activity.Finish
	Catch
		Msgbox("Whatsapp Application is not Found !!","Warning")
	End Try
End Sub