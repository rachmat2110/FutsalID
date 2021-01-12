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

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private LV_feed As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("layfeed")
	LV_feed.TwoLinesAndBitmap.Label.TextSize = 16
	LV_feed.TwoLinesAndBitmap.SecondLabel.TextSize = 14
	LV_feed.TwoLinesAndBitmap.Label.Typeface = Typeface.DEFAULT_BOLD
	LV_feed.TwoLinesAndBitmap.Label.TextColor = Colors.DarkGray
	LV_feed.TwoLinesAndBitmap.SecondLabel.TextColor = Colors.ARGB(255,104,104,104)
	FeedTest

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub FeedTest
	Dim req As DBRequestManager = util.CreateRequest(Me)
	Dim cmd As DBCommand = util.CreateCommand("tourinfo",Null)
	Wait For(req.ExecuteQuery(cmd,0,Null)) JobDone(j As HttpJob)
	If j.Success Then
		req.HandleJobAsync(j,"req")
		Wait For(req) req_Result(res As DBResult)

		For Each row() As Object In res.Rows
			Dim photo As Bitmap = req.BytesToImage(row(5))
			LV_feed.AddTwoLinesAndBitmap(row(0),row(3) & "  |  " & row(2),photo)
			
		Next
		
	Else
		Log(j.ErrorMessage)
	End If
	j.Release
End Sub

Sub LV_feed_ItemClick (Position As Int, Value As Object)
	Info.judul = Value
	StartActivity(Info)
End Sub