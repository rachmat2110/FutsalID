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

	Private pnlriview As Panel
	Private pnl_SVriview As ScrollView
	Private ImageView1 As ImageView
	Private btn_riviewAlibaba As Button
	Private btn_riviewTown As Button
	Private btn_riviewEstadio As Button
	Private btn_riviewHalim As Button
	Private btn_riviewNext As Button
	Private btn_riviewUnggul As Button
	Private btn_riviewViva As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("pnl_SVriview")
	pnl_SVriview.Panel.LoadLayout("layCardRiview")
	pnl_SVriview.Panel.Height = pnlriview.Height
	pnl_SVriview.Panel.Width = pnlriview.Width
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	
End Sub


Sub ImageView1_Click
	Activity.Finish
	StartActivity(Main)
End Sub

Sub btn_riviewAlibaba_Click
	StartActivity(Alibaba)
End Sub

Sub btn_riviewTown_Click
	StartActivity(Town)
End Sub

Sub btn_riviewEstadio_Click
	StartActivity(Estadio)
End Sub

Sub btn_riviewHalim_Click
	StartActivity(Halim)
End Sub

Sub btn_riviewNext_Click
	StartActivity(Next_1)
End Sub

Sub btn_riviewUnggul_Click
	StartActivity(Unggul)
End Sub

Sub btn_riviewViva_Click
	StartActivity(Viva)
End Sub